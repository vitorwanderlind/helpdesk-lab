
package br.com.helpdesklab.backend.controller;

import br.com.helpdesklab.backend.model.Chamado;
import br.com.helpdesklab.backend.service.ChamadoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @GetMapping
    public List<Chamado> listar() {
        return chamadoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chamado criar(@RequestBody  @Valid CriarChamadoRequest request) {
        return chamadoService.criar(
            request.titulo(),
            request.descricao()
        );
    }

    
    public record CriarChamadoRequest(

        @NotBlank(message = "O titulo e obrigatorio")
        @Size(max = 200, message = "O titulo deve ter no maximo 200 caracteres")
        String titulo,

        @NotBlank(message = "A descricao e obrigatoria")
        String descricao

    ) {}



    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscarPorId(@PathVariable Long id) {

        return chamadoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Chamado> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarChamadoRequest request) {

        return chamadoService.atualizar(
                id,
                request.titulo(),
                request.descricao(),
                request.status()
            )
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        boolean excluido = chamadoService.excluir(id);

        if (!excluido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }



}
