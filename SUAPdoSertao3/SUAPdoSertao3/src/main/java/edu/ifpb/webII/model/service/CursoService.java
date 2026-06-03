package edu.ifpb.webII.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso listarCurso(Long codigo) {
        return cursoRepository.findById(codigo).orElse(null);
    }

    public void cadastrarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    public void atualizarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    public void deletarCursoporID(Long codigo) {
        cursoRepository.deleteById(codigo);
    }
}