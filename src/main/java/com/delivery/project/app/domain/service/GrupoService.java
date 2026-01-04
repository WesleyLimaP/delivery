package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.model.dto.grupoDto.request.GrupoRequestDto;
import com.delivery.project.app.api.model.dto.grupoDto.response.GrupoResponseDto;
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

        @Transactional(readOnly = true)
        public List<GrupoResponseDto> getAll() {
            List<Grupo>  grupos = repository.findAll();
            return grupos.stream().map(GrupoResponseDto::new).toList();
        }

        @Transactional(readOnly = true)
        public GrupoResponseDto getById(@PathVariable Long id) {
            Grupo grupo = getOrElseThrow(id);
            return new GrupoResponseDto(grupo);
        }

        private Grupo getOrElseThrow(Long id) {
            return repository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(GRUPO_NAO_ENCONTRADO_MSG));
        }

        @Transactional
        public GrupoResponseDto insert(GrupoRequestDto dto) {
            Grupo grupo = new Grupo();
            margeDtoToEntity(dto, grupo);

            grupo = repository.save(grupo);
            return new GrupoResponseDto(grupo);
        }
        private void margeDtoToEntity(GrupoRequestDto dto, Grupo grupo){
            grupo.setNome(dto.nome());
        }
        @Transactional
        public GrupoResponseDto update(GrupoRequestDto dto, Long id) {
            Grupo grupo = getOrElseThrow(id);
            margeDtoToEntity(dto, grupo);
            return new GrupoResponseDto(grupo);
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
    public List<Permissao>findAllPermissoes(Long id) {
            return getOrElseThrow(id).getPermissoes();
    }

    @Transactional(readOnly = true)
    public Object findByIdPermissoes(Long grupoId, Long permissaoId) {
            Grupo grupo = getOrElseThrow(grupoId);
            Permissao permissao = getPermissaoOrElseThrow(permissaoId);
            verificarPermissao(grupo, permissao);
        return permissao;
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
