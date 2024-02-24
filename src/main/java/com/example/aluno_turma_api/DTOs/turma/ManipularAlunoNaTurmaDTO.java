package com.example.aluno_turma_api.DTOs.turma;

import jakarta.validation.constraints.NotNull;

public record ManipularAlunoNaTurmaDTO(@NotNull Long idTurma, @NotNull Long idAluno) {
}
