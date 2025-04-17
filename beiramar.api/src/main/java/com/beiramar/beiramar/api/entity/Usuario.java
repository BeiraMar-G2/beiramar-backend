package com.beiramar.beiramar.api.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String cargo;
    private String senha;
    private LocalDate dtNasc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuarioEnum tipoUsuario;

    @OneToMany(mappedBy = "cliente")
    private List<Agendamento> agendamentosCliente;

    @OneToMany(mappedBy = "funcionario")
    private List<Agendamento> agendamentosFuncionario;

    @OneToMany(mappedBy = "funcionario")
    private List<Disponibilidade> disponibilidades;

    @OneToMany(mappedBy = "cliente")
    private List<PacoteDisponivel> pacotesAdquiridos;


    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public TipoUsuarioEnum getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Agendamento> getAgendamentosCliente() {
        return agendamentosCliente;
    }

    public void setAgendamentosCliente(List<Agendamento> agendamentosCliente) {
        this.agendamentosCliente = agendamentosCliente;
    }

    public List<Agendamento> getAgendamentosFuncionario() {
        return agendamentosFuncionario;
    }

    public void setAgendamentosFuncionario(List<Agendamento> agendamentosFuncionario) {
        this.agendamentosFuncionario = agendamentosFuncionario;
    }

    public List<Disponibilidade> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }

    public List<PacoteDisponivel> getPacotesAdquiridos() {
        return pacotesAdquiridos;
    }

    public void setPacotesAdquiridos(List<PacoteDisponivel> pacotesAdquiridos) {
        this.pacotesAdquiridos = pacotesAdquiridos;
    }
}
