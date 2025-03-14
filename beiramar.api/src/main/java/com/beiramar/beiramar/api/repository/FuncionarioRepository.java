package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Boolean existsByEmail (String email);
    Boolean existsByCpf (String cpf);

    Funcionario findByEmail (String email);
    Funcionario findByCpf (String cpf);
}
