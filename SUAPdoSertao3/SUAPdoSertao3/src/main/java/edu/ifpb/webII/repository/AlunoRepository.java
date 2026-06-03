package edu.ifpb.webII.repository;

import edu.ifpb.webII.model.Aluno;
import edu.ifpb.webII.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
    List<Aluno> findByCurso(Curso curso);
}