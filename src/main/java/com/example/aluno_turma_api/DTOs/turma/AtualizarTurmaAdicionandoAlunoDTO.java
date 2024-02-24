package com.example.aluno_turma_api.DTOs.turma;

import com.example.aluno_turma_api.Model.AlunoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AtualizarTurmaAdicionandoAlunoDTO(@NotNull Long idTurma,@NotNull Long idAluno) {
}
