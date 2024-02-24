package com.example.aluno_turma_api.DTOs.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoAtualizarDTO(@NotNull Long id, @NotBlank String email) {
}
