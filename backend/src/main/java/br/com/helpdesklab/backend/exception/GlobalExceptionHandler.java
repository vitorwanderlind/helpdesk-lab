
package br.com.helpdesklab.backend.exception;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(
            MethodArgumentNotValidException exception) {

        List<String> mensagens = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(erro -> erro.getDefaultMessage())
            .toList();

        Map<String, Object> resposta = Map.of(
            "status", 400,
            "erro", "Dados invalidos",
            "mensagens", mensagens
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(resposta);
    }
}
