package com.example.aluno_turma_api.DTOs.aluno;

import com.example.aluno_turma_api.Model.TurmaModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoDTO(@NotBlank String nome,@NotBlank String sobrenome, @NotBlank String cpf,@NotBlank String email) {
}
