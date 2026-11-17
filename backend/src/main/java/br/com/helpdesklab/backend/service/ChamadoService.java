
package br.com.helpdesklab.backend.service;

import br.com.helpdesklab.backend.model.Chamado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ChamadoService {

    private final List<Chamado> chamados = new ArrayList<>();
    private final AtomicLong sequencia = new AtomicLong(1);

    public synchronized List<Chamado> listar() {
        return new ArrayList<>(chamados);
    }

    public synchronized Chamado criar(String titulo, String descricao) {

        Long id = sequencia.getAndIncrement();

        Chamado chamado = new Chamado(
            id,
            titulo,
            descricao,
            "ABERTO"
        );

        chamados.add(chamado);

        return chamado;
    }
}
