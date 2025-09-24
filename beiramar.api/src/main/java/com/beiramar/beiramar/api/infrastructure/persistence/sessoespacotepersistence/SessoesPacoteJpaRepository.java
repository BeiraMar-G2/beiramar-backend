package com.beiramar.beiramar.api.infrastructure.persistence.sessoespacotepersistence;

import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessoesPacoteJpaRepository extends JpaRepository<SessoesPacoteEntity, Integer> {

    @Query(value = "select s.*, sessoes_pacote.qtd_sessoes from sessoes_pacote join servico as s on id_servico=fk_servico where fk_pacote = :idPacote", nativeQuery = true)
    List<ServicoEntity> buscarServicosPorPacote(@Param("idPacote") Integer idPacote);
}
