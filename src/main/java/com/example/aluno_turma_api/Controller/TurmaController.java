package com.example.aluno_turma_api.Controller;

import com.example.aluno_turma_api.DTOs.turma.AtualizarTurmaAdicionandoAlunoDTO;
import com.example.aluno_turma_api.DTOs.turma.TurmaDTO;
import com.example.aluno_turma_api.Model.AlunoModel;
import com.example.aluno_turma_api.Model.TurmaModel;
import com.example.aluno_turma_api.Repository.AlunoRepository;
import com.example.aluno_turma_api.Repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<TurmaModel> cadastrarTurma(@RequestBody @Valid TurmaDTO turmaDTO) {
        var turma = new TurmaModel();
        BeanUtils.copyProperties(turmaDTO, turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaRepository.save(turma));
    }

    @PutMapping
    public ResponseEntity addAlunoNaTurma(@RequestBody @Valid AtualizarTurmaAdicionandoAlunoDTO dados) {
        var turma = turmaRepository.getReferenceById(dados.idTurma());
        var aluno = alunoRepository.getReferenceById(dados.idAluno());
        List<AlunoModel> alunos = turma.getAlunos();
        /*
        for(AlunoModel a:alunos){
            if(a.getId()!=aluno.getId()){
                if(aluno.getAtivo()) {
                    turma.addAluno(aluno);
                    return ResponseEntity.status(HttpStatus.OK).body(turmaRepository.save(turma));
                }
            }
        }
         */

        turma.verificarAluno(alunos, aluno);
        return ResponseEntity.status(HttpStatus.OK).body(turmaRepository.save(turma));


    }

    @GetMapping
    public ResponseEntity<List<TurmaModel>> turmas(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaRepository.findAll());
    }

}
