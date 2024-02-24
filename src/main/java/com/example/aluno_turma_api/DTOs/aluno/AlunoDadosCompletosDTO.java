package com.example.aluno_turma_api.DTOs.aluno;

import com.example.aluno_turma_api.Model.AlunoModel;

public record AlunoDadosCompletosDTO(Long id, String nome, String sobrenome, String cpf, String email,Boolean ativo) {

    public AlunoDadosCompletosDTO(AlunoModel alunoModel){
       this(alunoModel.getId(), alunoModel.getNome(), alunoModel.getSobrenome(), alunoModel.getCpf(), alunoModel.getEmail(),alunoModel.getAtivo());
    }

}
