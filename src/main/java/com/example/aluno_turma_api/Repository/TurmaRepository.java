package com.example.aluno_turma_api.Repository;

import com.example.aluno_turma_api.Model.TurmaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaModel,Long> {
}
