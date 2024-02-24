package com.example.aluno_turma_api.Model;

import com.example.aluno_turma_api.DTOs.turma.AtualizarTurmaAdicionandoAlunoDTO;
import com.example.aluno_turma_api.Repository.AlunoRepository;
import com.example.aluno_turma_api.Repository.TurmaRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    public void verificarAluno(List<AlunoModel> alunos, AlunoModel alunoModel){
        for(AlunoModel a: alunos){
            if(a.getId() != alunoModel.getId() && alunoModel.getAtivo()){
                this.addAluno(alunoModel);
            }
        }
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

    public List<AlunoModel> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoModel> alunos) {
        this.alunos = alunos;
    }


}
