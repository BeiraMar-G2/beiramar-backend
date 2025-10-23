package com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoJpaRepository extends JpaRepository<AgendamentoEntity, Integer> {

    List<AgendamentoEntity> findByClienteIdUsuario(Integer idCliente);

    Page<AgendamentoEntity> findByClienteIdUsuario(Integer idCliente, Pageable pageable);

    @Query("SELECT a FROM AgendamentoEntity a " +
            "WHERE (a.cliente.id = :idUsuario OR a.funcionario.id = :idUsuario) " +
            "AND a.dtHora < :dataHora " +
            "ORDER BY a.dtHora DESC")
    List<AgendamentoEntity> findHistoricoPorCliente(@Param("idUsuario") Integer idUsuario,
                                                    @Param("dataHora") LocalDateTime dataHora);


    @Query("SELECT a FROM AgendamentoEntity a " +
            "WHERE (a.cliente.id = :idUsuario OR a.funcionario.id = :idUsuario) " +
            "AND a.dtHora < :dataHora " +
            "ORDER BY a.dtHora DESC")
    Page<AgendamentoEntity> findHistoricoPorClientePaginado(@Param("idUsuario") Integer idUsuario,
                                                            @Param("dataHora") LocalDateTime dataHora,
                                                            Pageable pageable);


    @Query("SELECT a FROM AgendamentoEntity a WHERE MONTH(a.dtHora) = :mes AND YEAR(a.dtHora) = :ano")
    List<AgendamentoEntity> findByMes(Integer mes, Integer ano);

    @Query("SELECT a FROM AgendamentoEntity a WHERE MONTH(a.dtHora) = :mes AND YEAR(a.dtHora) = :ano")
    Page<AgendamentoEntity> findByMes(Integer mes, Integer ano, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM agendamento a WHERE a.status = 'Agendado' AND a.dt_hora >= DATE_SUB(CURDATE(), INTERVAL ?1 DAY)", nativeQuery = true)
    Long countByStatusAgendado(Integer dias);


    @Query(value = "SELECT COUNT(*) FROM agendamento a WHERE a.status = 'Cancelado' AND a.dt_hora >= DATE_SUB(CURDATE(), INTERVAL ?1 DAY)", nativeQuery = true)
    Long countByStatusCancelado(Integer dias);
}
