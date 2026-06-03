package edu.ifpb.webII.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import edu.ifpb.webII.model.Professor;
import edu.ifpb.webII.repository.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor listarProfessor(Long matricula) {
        return professorRepository.findById(matricula).orElse(null);
    }

    public void cadastrarProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    public void atualizarProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    public void deletarProfessorPorID(Long matricula) {
        professorRepository.deleteById(matricula);
    }
}