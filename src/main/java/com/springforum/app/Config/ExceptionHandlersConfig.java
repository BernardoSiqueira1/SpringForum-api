package com.springforum.app.Config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ControllerAdvice
public class ExceptionHandlersConfig {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleInvalidParametersException(){
        return ResponseEntity.status(400).body("Erro nos parâmetros do caminho da requisição, verifique e tente novamente.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException validationException){
        List<String> errorList = validationException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ResponseEntity.status(400).body("Erro nos parâmetros do corpo da requisição: \n" + errorList);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException notFoundException){
        return ResponseEntity.status(404).body("Informação buscada não encontrada na base de dados");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleViolatedConstraint(DataIntegrityViolationException integrityViolationException){
        return ResponseEntity.status(500).body(integrityViolationException.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationError(){
        return ResponseEntity.status(401).body("Credenciais inválidas, verifique usuário/senha");
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<?> handleAuthorizationError(){
        return ResponseEntity.status(403).body("Usuário não está autorizado, verifique token/autenticação");
    }

}
