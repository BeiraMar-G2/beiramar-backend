package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento> findByStatus(String status);
    List<Agendamento> findByDtHoraAfter(LocalDateTime data);
    List<Agendamento> findByDtHoraBefore(LocalDateTime data);
}
