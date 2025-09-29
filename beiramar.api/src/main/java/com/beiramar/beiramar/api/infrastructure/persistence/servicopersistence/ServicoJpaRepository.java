package com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence;

import com.beiramar.beiramar.api.core.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoJpaRepository extends JpaRepository<ServicoEntity, Integer> {

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY COUNT(a.id_agendamento) DESC) as posicao_ranking, " +
            "s.nome, COUNT(a.id_agendamento) as total_agendamentos " +
            "FROM Servico s " +
            "LEFT JOIN Agendamento a ON s.id_servico = a.fk_servico " +
            "GROUP BY s.id_servico, s.nome " +
            "ORDER BY total_agendamentos DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Object[]> findTop3ServicosMaisAgendados();

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY COUNT(a.id_agendamento) ASC) as posicao_ranking, " +
            "s.nome, COUNT(a.id_agendamento) as total_agendamentos " +
            "FROM Servico s " +
            "LEFT JOIN Agendamento a ON s.id_servico = a.fk_servico " +
            "GROUP BY s.id_servico, s.nome " +
            "ORDER BY total_agendamentos ASC " +
            "LIMIT 3", nativeQuery = true)
    List<Object[]> findTop3ServicosMenosAgendados();

    @Query(value = "SELECT s.nome, COUNT(CASE WHEN a.status = 'Cancelado' THEN 1 END) as total_cancelamentos " +
            "FROM Servico s " +
            "LEFT JOIN Agendamento a ON s.id_servico = a.fk_servico " +
            "GROUP BY s.id_servico, s.nome " +
            "ORDER BY total_cancelamentos DESC", nativeQuery = true)
    List<Object[]> findServicosMaisCancelados();

}
