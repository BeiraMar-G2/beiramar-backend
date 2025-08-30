package com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence;

import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Cargo")
public class CargoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCargo;

    private String nome;

    @OneToMany(mappedBy = "cargo")
    private List<UsuarioEntity> usuarios;

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }
}