package com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence;

import com.beiramar.beiramar.api.core.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoJpaRepository extends JpaRepository<ServicoEntity, Integer> {

    @Query("SELECT sp.servico FROM SessoesPacote sp WHERE sp.pacote.id = :idPacote")
    List<Servico> buscarServicosPorPacote(Integer idPacote);

}
