package com.example.aluno_turma_api.DTOs.aluno;

import jakarta.validation.constraints.NotBlank;

public record AlunoDTO(@NotBlank String nome,@NotBlank String sobrenome, @NotBlank String cpf,@NotBlank String email) {
}
