package com.example.aluno_turma_api.Model;

import com.example.aluno_turma_api.DTOs.aluno.AlunoAtualizarDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity(name = "aluno")
@Table(name = "Alunos")
@EqualsAndHashCode(of = "id")
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    @Column(unique = true)
    private String cpf;
    @Email
    private String email;
    private Boolean ativo = true;

    @OneToOne
    private TurmaModel turmaModel;

    public AlunoModel(Long id, String nome, String sobrenome, String cpf, String email) {
        this.ativo = true;
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
    }

    public AlunoModel() {

    }

    public void excluir(){
        this.ativo = false;
    }

    public void atualizarAluno(AlunoAtualizarDTO dados){
        this.email = dados.email();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public TurmaModel getTurmaModel() {
        return turmaModel;
    }

    public void setTurmaModel(TurmaModel turmaModel) {
        this.turmaModel = turmaModel;
    }
}
