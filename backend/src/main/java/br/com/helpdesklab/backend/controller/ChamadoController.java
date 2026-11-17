
package br.com.helpdesklab.backend.controller;

import br.com.helpdesklab.backend.model.Chamado;
import br.com.helpdesklab.backend.service.ChamadoService;

import org.springframework.http.HttpStatus;
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
    public Chamado criar(@RequestBody CriarChamadoRequest request) {
        return chamadoService.criar(
            request.titulo(),
            request.descricao()
        );
    }

    public record CriarChamadoRequest(
        String titulo,
        String descricao
    ) {}
}
