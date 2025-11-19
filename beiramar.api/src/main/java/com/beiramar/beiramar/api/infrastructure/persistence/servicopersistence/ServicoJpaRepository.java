package com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence;

import com.beiramar.beiramar.api.core.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServicoJpaRepository extends JpaRepository<ServicoEntity, Integer> {

    @Query(value =
            "SELECT ROW_NUMBER() OVER (ORDER BY COUNT(a.id_agendamento) DESC) AS posicao, " +
                    "s.nome, COUNT(a.id_agendamento) AS total " +
                    "FROM servico s " +
                    "LEFT JOIN agendamento a ON s.id_servico = a.fk_servico " +
                    "AND a.dt_hora BETWEEN :dataInicio AND :dataFim " +
                    "GROUP BY s.id_servico, s.nome " +
                    "ORDER BY total DESC " +
                    "LIMIT 3",
            nativeQuery = true)
    List<Object[]> findTop3ServicosMaisAgendados(
            LocalDateTime dataInicio,
            LocalDateTime dataFim);


    @Query(value =
            "SELECT ROW_NUMBER() OVER (ORDER BY COUNT(a.id_agendamento) ASC) AS posicao, " +
                    "s.nome, COUNT(a.id_agendamento) AS total " +
                    "FROM servico s " +
                    "LEFT JOIN agendamento a ON s.id_servico = a.fk_servico " +
                    "AND a.dt_hora BETWEEN :dataInicio AND :dataFim " +
                    "GROUP BY s.id_servico, s.nome " +
                    "ORDER BY total ASC " +
                    "LIMIT 3",
            nativeQuery = true)
    List<Object[]> findTop3ServicosMenosAgendados(
            LocalDateTime dataInicio,
            LocalDateTime dataFim);

    @Query(value =
            "SELECT s.nome, COUNT(CASE WHEN a.status = 'Cancelado' THEN 1 END) AS total_cancelamentos " +
                    "FROM servico s " +
                    "LEFT JOIN agendamento a ON s.id_servico = a.fk_servico " +
                    "AND a.dt_hora BETWEEN :dataInicio AND :dataFim " +
                    "WHERE 1=1 " +
                    "GROUP BY s.id_servico, s.nome " +
                    "ORDER BY total_cancelamentos DESC " +
                    "LIMIT 5",
            nativeQuery = true)
    List<Object[]> findServicosMaisCancelados(
            LocalDateTime dataInicio,
            LocalDateTime dataFim);

    @Query(value = "SELECT CASE DAYOFWEEK(a.dt_hora) " +
            "WHEN 1 THEN 'Domingo' " +
            "WHEN 2 THEN 'Segunda-feira' " +
            "WHEN 3 THEN 'Terça-feira' " +
            "WHEN 4 THEN 'Quarta-feira' " +
            "WHEN 5 THEN 'Quinta-feira' " +
            "WHEN 6 THEN 'Sexta-feira' " +
            "WHEN 7 THEN 'Sábado' " +
            "END as dia_semana, COUNT(a.id_agendamento) as total_agendamentos " +
            "FROM servico s " +
            "INNER JOIN agendamento a ON s.id_servico = a.fk_servico " +
            "WHERE s.nome = ?1 AND a.status = 'Agendado' " +
            "AND a.dt_hora >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) " +
            "GROUP BY CASE DAYOFWEEK(a.dt_hora) " +
            "WHEN 1 THEN 'Domingo' " +
            "WHEN 2 THEN 'Segunda-feira' " +
            "WHEN 3 THEN 'Terça-feira' " +
            "WHEN 4 THEN 'Quarta-feira' " +
            "WHEN 5 THEN 'Quinta-feira' " +
            "WHEN 6 THEN 'Sexta-feira' " +
            "WHEN 7 THEN 'Sábado' " +
            "END, DAYOFWEEK(a.dt_hora) " +
            "ORDER BY DAYOFWEEK(a.dt_hora)", nativeQuery = true)
    List<Object[]> findAgendamentosPorDiaSemanaPorNomeServico(String nomeServico);

    @Query(value = "SELECT CASE DAYOFWEEK(a.dt_hora) " +
            "WHEN 1 THEN 'Domingo' " +
            "WHEN 2 THEN 'Segunda-feira' " +
            "WHEN 3 THEN 'Terça-feira' " +
            "WHEN 4 THEN 'Quarta-feira' " +
            "WHEN 5 THEN 'Quinta-feira' " +
            "WHEN 6 THEN 'Sexta-feira' " +
            "WHEN 7 THEN 'Sábado' " +
            "END as dia_semana, COUNT(a.id_agendamento) as total_cancelamentos " +
            "FROM servico s " +
            "INNER JOIN agendamento a ON s.id_servico = a.fk_servico " +
            "WHERE s.nome = ?1 AND a.status = 'Cancelado' " +
            "AND a.dt_hora >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) " +
            "GROUP BY CASE DAYOFWEEK(a.dt_hora) " +
            "WHEN 1 THEN 'Domingo' " +
            "WHEN 2 THEN 'Segunda-feira' " +
            "WHEN 3 THEN 'Terça-feira' " +
            "WHEN 4 THEN 'Quarta-feira' " +
            "WHEN 5 THEN 'Quinta-feira' " +
            "WHEN 6 THEN 'Sexta-feira' " +
            "WHEN 7 THEN 'Sábado' " +
            "END, DAYOFWEEK(a.dt_hora) " +
            "ORDER BY DAYOFWEEK(a.dt_hora)", nativeQuery = true)
    List<Object[]> findCancelamentosPorDiaSemanaPorNomeServico(String nomeServico);

}
