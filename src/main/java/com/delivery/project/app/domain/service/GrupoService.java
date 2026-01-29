package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.assembler.GrupoAssembler;
import com.delivery.project.app.api.assembler.PermissaoAssembler;
import com.delivery.project.app.api.model.dto.grupoDto.request.GrupoRequestDto;
import com.delivery.project.app.api.model.dto.grupoDto.response.GrupoResponseDto;
import com.delivery.project.app.api.model.dto.permissaoDto.PermissaoDto;
import com.delivery.project.app.domain.model.Grupo;
import com.delivery.project.app.domain.model.Permissao;
import com.delivery.project.app.domain.exceptions.AssociacaoException;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.GrupoNaoEncontradoException;
import com.delivery.project.app.domain.exceptions.PermissaoNaoEncontradaException;
import com.delivery.project.app.domain.repository.GrupoRepository;
import com.delivery.project.app.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class GrupoService {
        public static final String GRUPO_NAO_ENCONTRADO_MSG = "Grupo nao encontrado";
        public static final String INTEGRIDADE_REFERENCIAL_MSG = "A entidade nao pode ser apagada pois existe dependencia com outras classes";
        @Autowired
        private GrupoRepository repository;
        @Autowired
        private PermissaoRepository permissaoRepository;
        @Autowired
        private GrupoAssembler assembler;
        @Autowired
        private PermissaoAssembler permissaoAssembler;

        @Transactional(readOnly = true)
        public CollectionModel<GrupoResponseDto> getAll() {
            List<Grupo> grupos = repository.findAll();
            return assembler.toCollectionModel(grupos);
        }

        @Transactional(readOnly = true)
        public GrupoResponseDto getById(@PathVariable Long id) {
           return assembler.toModel(getOrElseThrow(id));
        }

        private Grupo getOrElseThrow(Long id) {
            return repository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(GRUPO_NAO_ENCONTRADO_MSG));
        }

        @Transactional
        public GrupoResponseDto insert(GrupoRequestDto dto) {
           var grupo = assembler.toEntity(dto);
           grupo = repository.save(grupo);
           return assembler.toModel(grupo);
        }

        @Transactional
        public GrupoResponseDto update(GrupoRequestDto dto, Long id) {
            var grupo = getOrElseThrow(id);
            assembler.update(grupo, dto);
            return assembler.toModel(grupo);
        }

        @Transactional
        public void delete(Long id) {
            try {
                Grupo grupo = getOrElseThrow(id);
                repository.delete(grupo);
                repository.flush();
            }catch (DataIntegrityViolationException e){
                throw new EntidadeEmUsoException(INTEGRIDADE_REFERENCIAL_MSG);
            }
        }

        @Transactional(readOnly = true)
    public CollectionModel<PermissaoDto>findAllPermissoes(Long grupoid) {
            var grupo = getOrElseThrow(grupoid);
            return permissaoAssembler.toCollectionModel(grupo.getPermissoes(), grupoid);
    }

    @Transactional(readOnly = true)
    public PermissaoDto findByIdPermissoes(Long grupoId, Long permissaoId) {
            Grupo grupo = getOrElseThrow(grupoId);
            Permissao permissao = getPermissaoOrElseThrow(permissaoId);
            verificarPermissao(grupo, permissao);
        return permissaoAssembler.toModel(permissao, grupoId);
    }

    private void verificarPermissao(Grupo grupo, Permissao permissao) {
        if(!grupo.getPermissoes().contains(permissao)){
            throw new AssociacaoException("o grupo nao possui essa permissao");
        }
    }

    private Permissao getPermissaoOrElseThrow(Long permissaoId) {
        return permissaoRepository.findById(permissaoId).orElseThrow(() -> new PermissaoNaoEncontradaException(" a permissao nao foi encontrada"));
    }

    @Transactional
    public Object associarPermissao(Long grupoId, Long permissaoId) {
            Grupo grupo = getOrElseThrow(grupoId);
            Permissao permissao = getPermissaoOrElseThrow(permissaoId);
            grupo.getPermissoes().add(permissao);
            return grupo.getPermissoes();
    }
    @Transactional
    public Object desassociarPermissao(Long grupoId, Long permissaoId) {
            Grupo grupo = getOrElseThrow(grupoId);
            Permissao permissao = getPermissaoOrElseThrow(permissaoId);
            verificarPermissao(grupo, permissao);
            grupo.getPermissoes().remove(permissao);
            return grupo.getPermissoes();
    }
}
