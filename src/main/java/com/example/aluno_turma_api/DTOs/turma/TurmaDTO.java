package com.example.aluno_turma_api.DTOs.turma;

import com.example.aluno_turma_api.Model.AlunoModel;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TurmaDTO(@NotBlank String nome, List<AlunoModel> alunos, Integer vagas) {
}
