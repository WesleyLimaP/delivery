package com.delivery.project.app.api.handler;

import com.delivery.project.app.exceptions.AssociacaoException;
import com.delivery.project.app.exceptions.EntidadeEmUsoException;
import com.delivery.project.app.exceptions.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerHandlerExceptions extends ResponseEntityExceptionHandler {

    private final String MSG_CLIENT_ERROR = "ocorreu um error interno desconhecido no sistema. tente novamente e se o problema persistir, entre em contato com suporte";

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request) {
        Error error = Error.builder()
                .errorType(ProblemType.ENTIDADE_NAO_ENCONTRADA)
                .detalhe(e.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .userMessage(e.getMessage())
                .build();

        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }
    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
        Error error = Error.builder()
                .errorType(ProblemType.ENTIDADE_EM_USO)
                .detalhe(e.getMessage())
                .timestamp(LocalDateTime.now())
                .tittle("entidade em uso")
                .status(HttpStatus.CONFLICT.value())
                .userMessage(e.getMessage())
                .build();

        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.CONFLICT, request
        );
    }
    @ExceptionHandler(AssociacaoException.class)
    public ResponseEntity<?> tratarAssociacaooException(AssociacaoException e, WebRequest request) {
        Error error = Error.builder()
                .errorType(ProblemType.ERRO_DE_ASSOCIACAO)
                .detalhe(e.getMessage())
                .timestamp(LocalDateTime.now())
                .tittle(ProblemType.ERRO_DE_ASSOCIACAO.title)
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(e.getMessage())
                .build();

        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> tratarConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        for(ConstraintViolation violation: e.getConstraintViolations()){
            fieldErrors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        Error error = Error.builder()
                .errorType(ProblemType.ERRO_DE_CAMPO)
                .detalhe("um ou mais campos invalidos")
                .tittle("erro de campo")
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage("um ou mais campos invalidos")
                .fieldErrors(fieldErrors)
                .build();

        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request
        );
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Throwable exception = ExceptionUtils.getRootCause(ex);
        if (exception instanceof InvalidFormatException) {
            return HandleInvalidFormatException((InvalidFormatException) exception, headers, status, request);
        }
        Error error = Error.builder()
                .errorType(ProblemType.ERRO_SINTATICO)
                .detalhe("falha na estrutura do json")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .tittle("erro de sintaxe").build();

        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    private ResponseEntity<Object> HandleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String campo = ex.getPath().stream().map(JsonMappingException.Reference::getFieldName).toList().getLast();
        String tipo = ex.getTargetType().getSimpleName();
        Object outroTipo = ex.getValue().getClass().getSimpleName();
        Error error = Error.builder()
                .errorType(ProblemType.ERRO_SINTATICO)
                .detalhe("falha no campo " + campo + ". Era esperado um tipo " + tipo + " e foi passaado um " + outroTipo)
                .status(HttpStatus.BAD_REQUEST.value())
                .tittle("erro de sintaxe")
                .timestamp(LocalDateTime.now())
                .userMessage(MSG_CLIENT_ERROR)
                .build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        if(ex instanceof MethodArgumentTypeMismatchException){

            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        throw ex;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,HttpHeaders headers, HttpStatusCode status, WebRequest request){
        Error error = Error.builder()
                .tittle("parametro de recurso errado")
                .errorType(ProblemType.PARAMETRO_INVALIDO)
                .timestamp(LocalDateTime.now())
                .userMessage(MSG_CLIENT_ERROR)
                .status(HttpStatus.BAD_REQUEST.value())
                .detalhe("o parametro de url '" + ex.getPropertyName() + "' recebeu o valor '" + ex.getValue() + "', que é de um tipo invalido. Corrija e informe um valor compativel com o tipo " + ex.getName().getClass().getSimpleName())
                .build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
            NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Error error = Error.builder()
                .errorType(ProblemType.RECURSO_NAO_ENCONTRADO)
                .tittle("recurso nao enconrado")
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .detalhe("nao foi possivel acessar o recurso informado pois a url esta errada")
                .userMessage("nao foi possivel acessar o recurso informado pois a url esta errada")
                .build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleSystemError(Exception ex, WebRequest request){
        Error error = Error.builder()
                .detalhe("ocorreu um error interno desconhecido no sistema. tente novamente e se o problema persistir, entre em contato com suporte.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorType(ProblemType.ERRO_DE_SISTEMA)
                .userMessage(MSG_CLIENT_ERROR)
                .timestamp(LocalDateTime.now())
                .tittle("erro interno no sistema")
                .build();

       return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> filds = new HashMap<>();
        for(FieldError fildss : ex.getFieldErrors()){
            filds.put(fildss.getField(), fildss.getDefaultMessage());
        }
        Error error = Error.builder()
                .detalhe("um ou mais campos invalidos")
                .status(HttpStatus.BAD_REQUEST.value())
                .errorType(ProblemType.ERRO_SINTATICO)
                .userMessage("um ou mais campos invalidos")
                .timestamp(LocalDateTime.now())
                .tittle("erro de campo")
                .fieldErrors(filds)
                .build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }


}
