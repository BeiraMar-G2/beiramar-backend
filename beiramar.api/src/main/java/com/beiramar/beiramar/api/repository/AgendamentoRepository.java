package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.AgendamentoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Integer> {

    List<AgendamentoEntity> findByStatus(String status);
    List<AgendamentoEntity> findByDtHoraAfter(LocalDateTime data);
    List<AgendamentoEntity> findByDtHoraBefore(LocalDateTime data);
    List<AgendamentoEntity> findByCliente(UsuarioEntity cliente);

    //@Query("SELECT a FROM Agendamento a WHERE a.dtHora BETWEEN :start AND :end")
    //List<AgendamentoEntity> findByMes(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
