
package br.com.helpdesklab.backend.repository;

import br.com.helpdesklab.backend.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
