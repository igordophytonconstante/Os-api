package br.kge.eti.OSApiAaplication.domain.repository;

import br.kge.eti.OSApiAaplication.domain.model.OrdemServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sesi3dia
 */
public interface OrdemServicoRepository  extends JpaRepository<OrdemServico, Long> {

    // Desafio Errado.
    //List<OrdemServico> findByClienteId(Long clienteId);
    
}
