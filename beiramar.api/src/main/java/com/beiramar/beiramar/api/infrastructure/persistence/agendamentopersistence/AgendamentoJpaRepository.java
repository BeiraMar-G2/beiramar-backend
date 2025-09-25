package com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgendamentoJpaRepository extends JpaRepository<AgendamentoEntity, Integer> {

    List<AgendamentoEntity> findByClienteIdUsuario(Integer idCliente);

    @Query("SELECT a FROM AgendamentoEntity a WHERE MONTH(a.dtHora) = :mes AND YEAR(a.dtHora) = :ano")
    List<AgendamentoEntity> findByMes(Integer mes, Integer ano);


    @Query(value = "SELECT COUNT(*) FROM Agendamento a WHERE a.status = 'Agendado' AND a.dt_hora >= DATE_SUB(CURDATE(), INTERVAL ?1 DAY)", nativeQuery = true)
    Long countByStatusAgendado(Integer dias);


    @Query(value = "SELECT COUNT(*) FROM Agendamento a WHERE a.status = 'Cancelado' AND a.dt_hora >= DATE_SUB(CURDATE(), INTERVAL ?1 DAY)", nativeQuery = true)
    Long countByStatusCancelado(Integer dias);
}
