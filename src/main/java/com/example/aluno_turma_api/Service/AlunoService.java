package com.example.aluno_turma_api.Service;

import com.example.aluno_turma_api.DTOs.aluno.AlunoAtualizarDTO;
import com.example.aluno_turma_api.DTOs.aluno.AlunoDTO;
import com.example.aluno_turma_api.DTOs.aluno.AlunoDadosCompletosDTO;
import com.example.aluno_turma_api.Model.AlunoModel;
import com.example.aluno_turma_api.Repository.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Page<AlunoDadosCompletosDTO> getAllAlunos(Pageable pageable){
        var page = alunoRepository.findAll(pageable).map(AlunoDadosCompletosDTO::new);
        return page;
    }

    public Page<AlunoDadosCompletosDTO> getAllAlunosAtivos(Pageable pageable){
        var page = alunoRepository.findAllByAtivoTrue(pageable).map(AlunoDadosCompletosDTO::new);
        return page;
    }

    public AlunoDTO insertAluno(AlunoDTO alunoDTO){
        var alunoModel = new AlunoModel();
        BeanUtils.copyProperties(alunoDTO,alunoModel);
        alunoRepository.save(alunoModel);
        return alunoDTO;
    }

    public void updateAluno(AlunoAtualizarDTO dados){
        var aluno = alunoRepository.getReferenceById(dados.id());
        aluno.atualizarAluno(dados);
        alunoRepository.save(aluno);
    }

    public void deleteAluno(Long id){
        var aluno = alunoRepository.getReferenceById(id);
        aluno.excluir();
    }

}
