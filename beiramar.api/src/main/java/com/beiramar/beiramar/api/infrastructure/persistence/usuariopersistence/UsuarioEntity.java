package com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence;

import com.beiramar.beiramar.api.entity.FilesEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence.AgendamentoEntity;
import com.beiramar.beiramar.api.entity.DisponibilidadeEntity;
import com.beiramar.beiramar.api.entity.LogSenhaEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.valorpacotepersistence.ValorPacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Schema(description = "Usuário do sistema", example = "Funcionário/Cliente")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private LocalDate dtNasc;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkCargo")
    private CargoEntity cargo;

    // Relacionamentos

    @OneToMany(mappedBy = "funcionario")
    private List<DisponibilidadeEntity> disponibilidades;

    @OneToMany(mappedBy = "cliente")
    private List<AgendamentoEntity> agendamentosComoCliente;

    @OneToMany(mappedBy = "funcionario")
    private List<AgendamentoEntity> agendamentosComoFuncionario;

    @OneToMany(mappedBy = "usuario")
    private List<LogSenhaEntity> logsSenha;

    @OneToMany(mappedBy = "usuario")
    private List<ValorPacoteEntity> pacotes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fotoPerfilId")
    private FilesEntity fotoPerfil;

    public FilesEntity getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(FilesEntity fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

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

    public CargoEntity getCargo() {
        return cargo;
    }

    public void setCargo(CargoEntity cargo) {
        this.cargo = cargo;
    }

    public List<AgendamentoEntity> getAgendamentosComoCliente() {
        return agendamentosComoCliente;
    }

    public void setAgendamentosComoCliente(List<AgendamentoEntity> agendamentosComoCliente) {
        this.agendamentosComoCliente = agendamentosComoCliente;
    }

    public List<AgendamentoEntity> getAgendamentosComoFuncionario() {
        return agendamentosComoFuncionario;
    }

    public void setAgendamentosComoFuncionario(List<AgendamentoEntity> agendamentosComoFuncionario) {
        this.agendamentosComoFuncionario = agendamentosComoFuncionario;
    }

    public List<DisponibilidadeEntity> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<DisponibilidadeEntity> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }

    public List<ValorPacoteEntity> getPacotes() {
        return pacotes;
    }

    public void setPacotes(List<ValorPacoteEntity> pacotes) {
        this.pacotes = pacotes;
    }

    public List<LogSenhaEntity> getLogsSenha() {
        return logsSenha;
    }

    public void setLogsSenha(List<LogSenhaEntity> logsSenha) {
        this.logsSenha = logsSenha;
    }
}
