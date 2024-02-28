package com.example.aluno_turma_api.Controller;

import com.example.aluno_turma_api.DTOs.turma.ManipularAlunoNaTurmaDTO;
import com.example.aluno_turma_api.DTOs.turma.TurmaDTO;
import com.example.aluno_turma_api.DTOs.turma.TurmaDadosCompletosDTO;
import com.example.aluno_turma_api.Service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @GetMapping
    public ResponseEntity<Page<TurmaDadosCompletosDTO>> turmas(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = turmaService.getAllTurmas(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TurmaDTO> cadastrarTurma(@RequestBody @Valid TurmaDTO turmaDTO) {
        if (turmaService.turmaExistente(turmaDTO)) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.insertTurma(turmaDTO));
        }
    }

    @PutMapping("/adicionarAluno")
    @Transactional
    public ResponseEntity addAlunoNaTurma(@RequestBody @Valid ManipularAlunoNaTurmaDTO dados) {
        Boolean alunoEncontrado = turmaService.verificarAlunoNaTurma(dados.idTurma(), dados.idAluno());
        Boolean alunoAtivo = turmaService.verificarAlunoAtivo(dados.idAluno());
        Boolean quantidadeExcedida = turmaService.quantidadeExcedida(dados.idTurma());
        if (alunoEncontrado || !alunoAtivo || quantidadeExcedida) {
            return ResponseEntity.badRequest().build();
        } else {
            turmaService.addAlunoNaTurma(dados.idTurma(), dados.idAluno());
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/removerAluno")
    @Transactional
    public ResponseEntity removerAlunoDaTurma(@RequestBody @Valid ManipularAlunoNaTurmaDTO dados) {
        Boolean alunoEncontrado = turmaService.verificarAlunoNaTurma(dados.idTurma(), dados.idAluno());
        if (alunoEncontrado) {
            turmaService.removerAluno(dados.idTurma(), dados.idAluno());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTurma(@PathVariable Long id) {
        turmaService.removerTurma(id);
        return ResponseEntity.noContent().build();
    }

}
