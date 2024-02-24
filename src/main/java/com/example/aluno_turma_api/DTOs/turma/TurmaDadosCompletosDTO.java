package com.example.aluno_turma_api.DTOs.turma;

import com.example.aluno_turma_api.Model.AlunoModel;
import com.example.aluno_turma_api.Model.TurmaModel;

import java.util.List;

public record TurmaDadosCompletosDTO(Long id, String nome, List<AlunoModel> alunoModels) {

    public TurmaDadosCompletosDTO(TurmaModel turmaModel){
        this(turmaModel.getId(),turmaModel.getNome(),turmaModel.getAlunos());
    }
}
