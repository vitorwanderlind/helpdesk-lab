
package br.com.helpdesklab.backend.service;

import br.com.helpdesklab.backend.model.Chamado;
import br.com.helpdesklab.backend.repository.ChamadoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public List<Chamado> listar() {
        return chamadoRepository.findAll();
    }

    public Chamado criar(String titulo, String descricao) {

        Chamado chamado = new Chamado(
            null,
            titulo,
            descricao,
            "ABERTO"
        );

        return chamadoRepository.save(chamado);
    }
}
