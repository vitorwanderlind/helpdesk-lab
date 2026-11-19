
package br.com.helpdesklab.backend.service;

import br.com.helpdesklab.backend.model.Chamado;
import br.com.helpdesklab.backend.repository.ChamadoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Chamado> buscarPorId(Long id) {
        return chamadoRepository.findById(id);
    }

    public Optional<Chamado> atualizar(
            Long id,
            String titulo,
            String descricao,
            String status) {

        return chamadoRepository.findById(id)
            .map(chamado -> {
                chamado.setTitulo(titulo);
                chamado.setDescricao(descricao);
                chamado.setStatus(status);

                return chamadoRepository.save(chamado);
            });
    }


    public boolean excluir(Long id) {

        if (!chamadoRepository.existsById(id)) {
            return false;
        }

        chamadoRepository.deleteById(id);

        return true;
    }



}
