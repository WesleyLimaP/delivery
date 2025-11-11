package com.delivery.project.app;

import com.delivery.project.app.domain.model.Cozinha;
import com.delivery.project.app.domain.service.CozinhaService;
import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.exceptions.EntidadeNaoEncontradaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppApplicationTests {
    @Autowired
    private CozinhaService service;

	@Test
	void contextLoads() {
	}

    @Test
    void insertShouldSaveWhenValidParams(){
        Cozinha cozinha = new Cozinha();
        CozinhaDto dto;
        cozinha.setNome("cozinhaTeste");

        dto = service.insert(new CozinhaDto(cozinha));

        Assertions.assertNotNull(dto);
    }
    @Test
    void deleteShouldThrowEntidadeEmUsoExceptionWhenEntityIsInUse(){
        Long invalidId = 1L;

        Assertions.assertThrows(EntidadeEmUsoException.class, () ->{
            service.delete(invalidId);
        });


    }    @Test
    void deleteShouldThrowEntidadeNaoEncontradaExceptionWhenInvalidId(){
        Long invalidId = 100L;

        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () ->{
            service.delete(invalidId);
        });


    }

}
