package edu.ifpb.webII.repository;

import edu.ifpb.webII.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepositoryPagin extends JpaRepository<Disciplina, Long> {
    
}