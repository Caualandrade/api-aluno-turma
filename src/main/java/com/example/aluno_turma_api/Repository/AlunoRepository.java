package com.example.aluno_turma_api.Repository;

import com.example.aluno_turma_api.Model.AlunoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository <AlunoModel,Long> {

    Page<AlunoModel> findAllByAtivoTrue(Pageable paginacao);
}
