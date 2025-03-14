package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Boolean existsByEmail (String email);
    Boolean existsByTelefone (String telefone);



    Cliente findByEmail (String email);
    Cliente findByTelefone (String telefone);
}
