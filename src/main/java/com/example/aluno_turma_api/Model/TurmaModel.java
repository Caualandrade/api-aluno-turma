package com.example.aluno_turma_api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "turma")
@Table(name="turmas")
@AllArgsConstructor
@NoArgsConstructor
public class TurmaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany
    private List<AlunoModel> alunos;

    public void addAluno(AlunoModel alunoModel){
        this.alunos.add(alunoModel);
    }

    public void removerAluno(AlunoModel alunoModel){this.alunos.remove(alunoModel);}

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

    public List<AlunoModel> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoModel> alunos) {
        this.alunos = alunos;
    }


}
