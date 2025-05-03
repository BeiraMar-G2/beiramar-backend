package com.beiramar.beiramar.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Schema(description = "Usuário do sistema", example = "Funcionário/Cliente")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private LocalDate dtNasc;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<LogSenha>logsSenha;


    @ManyToOne
    @JoinColumn(name = "fkCargo")
    private Cargo cargo;

    @OneToMany(mappedBy = "cliente")
    private List<Agendamento> agendamentosComoCliente;

    @OneToMany(mappedBy = "funcionario")
    private List<Agendamento> agendamentosComoFuncionario;

    @OneToMany(mappedBy = "funcionario")
    private List<Disponibilidade> disponibilidades;

    @OneToMany(mappedBy = "usuario")
    private List<ValorPacote> pacotes;


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Agendamento> getAgendamentosComoCliente() {
        return agendamentosComoCliente;
    }

    public void setAgendamentosComoCliente(List<Agendamento> agendamentosComoCliente) {
        this.agendamentosComoCliente = agendamentosComoCliente;
    }

    public List<Agendamento> getAgendamentosComoFuncionario() {
        return agendamentosComoFuncionario;
    }

    public void setAgendamentosComoFuncionario(List<Agendamento> agendamentosComoFuncionario) {
        this.agendamentosComoFuncionario = agendamentosComoFuncionario;
    }

    public List<Disponibilidade> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }

    public List<ValorPacote> getPacotes() {
        return pacotes;
    }

    public void setPacotes(List<ValorPacote> pacotes) {
        this.pacotes = pacotes;
    }

    public List<LogSenha> getLogsSenha() {
        return logsSenha;
    }

    public void setLogsSenha(List<LogSenha> logsSenha) {
        this.logsSenha = logsSenha;
    }
}
