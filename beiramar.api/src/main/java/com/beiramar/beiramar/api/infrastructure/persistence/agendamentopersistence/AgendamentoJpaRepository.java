package com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgendamentoJpaRepository extends JpaRepository<AgendamentoEntity, Integer> {


    List<AgendamentoEntity> findByClienteIdUsuario(Integer idCliente);

    @Query("SELECT a FROM AgendamentoEntity a WHERE MONTH(a.dtHora) = :mes AND YEAR(a.dtHora) = :ano")
    List<AgendamentoEntity> findByMes(Integer mes, Integer ano);
}
