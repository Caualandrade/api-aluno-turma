package com.example.aluno_turma_api.Service;

import com.example.aluno_turma_api.DTOs.turma.TurmaDTO;
import com.example.aluno_turma_api.DTOs.turma.TurmaDadosCompletosDTO;
import com.example.aluno_turma_api.Model.TurmaModel;
import com.example.aluno_turma_api.Repository.AlunoRepository;
import com.example.aluno_turma_api.Repository.TurmaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    public Page<TurmaDadosCompletosDTO> getAllTurmas(Pageable pageable) {
        var page = turmaRepository.findAll(pageable).map(TurmaDadosCompletosDTO::new);
        return page;
    }

    public TurmaDTO insertTurma(TurmaDTO turmaDTO) {
        var turma = new TurmaModel();
        BeanUtils.copyProperties(turmaDTO, turma);
        turmaRepository.save(turma);
        return turmaDTO;
    }

    public void addAlunoNaTurma(Long idTurma, Long idAluno) {
        var aluno = alunoRepository.getReferenceById(idAluno);
        var turma = turmaRepository.getReferenceById(idTurma);
        turma.addAluno(aluno);
    }

    public void removerAluno(Long idTurma, Long idAluno) {
        var aluno = alunoRepository.getReferenceById(idAluno);
        var turma = turmaRepository.getReferenceById(idTurma);
        turma.removerAluno(aluno);
    }

    public void removerTurma(Long idTurma) {
        var turma = turmaRepository.getReferenceById(idTurma);
        turmaRepository.delete(turma);
    }

    public Boolean verificarAlunoNaTurma(Long idTurma, Long idAluno) {
        var aluno = alunoRepository.getReferenceById(idAluno);
        var turma = turmaRepository.getReferenceById(idTurma);
        var alunosNaTurma = turma.getAlunos();
        Boolean alunoExistente = alunosNaTurma.stream().anyMatch(e -> e.getId().equals(aluno.getId()));
        return alunoExistente;
    }

    public Boolean verificarAlunoAtivo(Long idAluno) {
        var aluno = alunoRepository.getReferenceById(idAluno);
        Boolean ativo = aluno.getAtivo();
        return ativo;
    }

    public Boolean turmaExistente(TurmaDTO turmaDTO) {
        var turmas = turmaRepository.findAll();
        boolean turmaEncontrada = turmas.stream().anyMatch(e -> e.getNome().equals(turmaDTO.nome()));
        return turmaEncontrada;
    }

    public Boolean quantidadeExcedida(Long idTurma){
        var turma = turmaRepository.getReferenceById(idTurma);
        if(turma.getVagasRestantes()<=0){
            return true;
        }
        return false;
    }


}
