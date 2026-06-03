package edu.ifpb.webII.repository;

import edu.ifpb.webII.model.Aluno;
import edu.ifpb.webII.model.MatriculaDisciplina;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaDisciplinaRepository extends JpaRepository<MatriculaDisciplina, Long> {
	List<MatriculaDisciplina> findByAluno(Aluno aluno);
}