package com.example.aluno_turma_api.Controller;

import com.example.aluno_turma_api.DTOs.aluno.AlunoAtualizarDTO;
import com.example.aluno_turma_api.DTOs.aluno.AlunoDTO;
import com.example.aluno_turma_api.DTOs.aluno.AlunoDadosCompletosDTO;
import com.example.aluno_turma_api.Model.AlunoModel;
import com.example.aluno_turma_api.Repository.AlunoRepository;
import com.example.aluno_turma_api.Service.AlunoService;
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

    @Autowired
    AlunoService alunoService;

    @GetMapping
    public ResponseEntity<Page<AlunoDadosCompletosDTO>> obterTodosAlunos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = alunoService.getAllAlunos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<AlunoDadosCompletosDTO>> obterTodosAlunosAtivos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = alunoService.getAllAlunosAtivos(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AlunoDTO> addAluno(@RequestBody @Valid AlunoDTO alunoDTO) {
       return ResponseEntity.ok(alunoService.insertAluno(alunoDTO));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDadosAluno(@RequestBody @Valid AlunoAtualizarDTO alunoAtualizadoDTO) {
        alunoService.updateAluno(alunoAtualizadoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarAluno(@PathVariable Long id) {
        alunoService.deleteAluno(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
