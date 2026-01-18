package com.delivery.project.app.api.controller;

import com.delivery.project.app.api.controller.doc.CidadeControllerDoc;
import com.delivery.project.app.api.util.LocationBulder;
import com.delivery.project.app.domain.service.CidadeService;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeUpdateDto;
import com.delivery.project.app.domain.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.MvcLink;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/cidades")
public class CidadeController implements CidadeControllerDoc {
    @Autowired
    private CidadeService service;


    @GetMapping
    public ResponseEntity<List<CidadeDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CidadeDto> findById(@PathVariable Long id){
        CidadeDto response = service.findById(id);
        response.add(WebMvcLinkBuilder.linkTo(CidadeController.class).slash(id).withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withRel("all").withSelfRel());
        response.getEstadoDto().add(WebMvcLinkBuilder.linkTo(EstadoController.class).slash(id).withSelfRel());
       return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<CidadeDto> insert (@RequestBody CidadeDto dto){
        var respoonse = service.insert(dto);
        var location = LocationBulder.create(respoonse.getId());
        return ResponseEntity.created(location).body(respoonse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        try {
            service.delete(id);
            return status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeEmUsoException e) {
            return status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CidadeDto> update(@PathVariable Long id, @RequestBody CidadeUpdateDto dto){
        return ResponseEntity.ok(service.update(id, dto ));
    }
}
