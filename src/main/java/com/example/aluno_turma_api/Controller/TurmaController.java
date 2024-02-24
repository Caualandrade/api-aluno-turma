package com.example.aluno_turma_api.Controller;

import com.example.aluno_turma_api.DTOs.turma.ManipularAlunoNaTurmaDTO;
import com.example.aluno_turma_api.DTOs.turma.TurmaDTO;
import com.example.aluno_turma_api.DTOs.turma.TurmaDadosCompletosDTO;
import com.example.aluno_turma_api.Model.AlunoModel;
import com.example.aluno_turma_api.Model.TurmaModel;
import com.example.aluno_turma_api.Repository.AlunoRepository;
import com.example.aluno_turma_api.Repository.TurmaRepository;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        List<TurmaModel> turmaModels = turmaRepository.findAll();
        Boolean existente = false;
        for (TurmaModel t : turmaModels) {
            if (t.getNome().equals(turmaDTO.nome())) {
                existente = true;
            }
        }
        if (existente) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaRepository.save(turma));
    }

    @PutMapping("/adicionarAluno")
    @Transactional
    public ResponseEntity addAlunoNaTurma(@RequestBody @Valid ManipularAlunoNaTurmaDTO dados) {
        Boolean possivel = true;
        var turma = turmaRepository.getReferenceById(dados.idTurma());
        var aluno = alunoRepository.getReferenceById(dados.idAluno());
        List<AlunoModel> listaDeAlunosNaTurma = turma.getAlunos();
        for(AlunoModel a: listaDeAlunosNaTurma){
            if(a.getId().equals(aluno.getId())){
               possivel=false;
            }
        }
        if(possivel && aluno.getAtivo()){
            turma.addAluno(aluno);
            turmaRepository.save(turma);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/removerAluno")
    @Transactional
    public ResponseEntity removerAlunoDaTurma(@RequestBody @Valid ManipularAlunoNaTurmaDTO dados){
        var turma = turmaRepository.getReferenceById(dados.idTurma());
        var aluno = alunoRepository.getReferenceById(dados.idAluno());
        turma.removerAluno(aluno);
        turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<Page<TurmaDadosCompletosDTO>> turmas(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = turmaRepository.findAll(pageable).map(TurmaDadosCompletosDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTurma(@PathVariable Long id){
        var turma = turmaRepository.getReferenceById(id);
        turmaRepository.delete(turma);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
