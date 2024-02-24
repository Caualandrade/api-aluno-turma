package com.example.aluno_turma_api.Controller;

import com.example.aluno_turma_api.DTOs.aluno.AlunoAtualizarDTO;
import com.example.aluno_turma_api.DTOs.aluno.AlunoDTO;
import com.example.aluno_turma_api.DTOs.aluno.AlunoDadosCompletosDTO;
import com.example.aluno_turma_api.Model.AlunoModel;
import com.example.aluno_turma_api.Repository.AlunoRepository;
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

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<AlunoModel> addAluno(@RequestBody @Valid AlunoDTO alunoDTO) {
        var alunoModel = new AlunoModel();
        BeanUtils.copyProperties(alunoDTO, alunoModel);
        alunoRepository.save(alunoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoModel);
    }

    @GetMapping
    public ResponseEntity<Page<AlunoDadosCompletosDTO>> obterTodosAlunos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = alunoRepository.findAll(pageable).map(AlunoDadosCompletosDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<AlunoDadosCompletosDTO>> obterTodosAlunosAtivos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = alunoRepository.findAllByAtivoTrue(pageable).map(AlunoDadosCompletosDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarAluno(@PathVariable Long id) {
        var alunoModel = alunoRepository.getReferenceById(id);
        alunoModel.excluir();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDadosAluno(@RequestBody @Valid AlunoAtualizarDTO alunoAtualizadoDTO){
        var alunoModel = alunoRepository.getReferenceById(alunoAtualizadoDTO.id());
        alunoModel.atualizarAluno(alunoAtualizadoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
