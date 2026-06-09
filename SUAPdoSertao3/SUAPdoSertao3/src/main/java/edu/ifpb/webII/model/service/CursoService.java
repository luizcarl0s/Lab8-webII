package edu.ifpb.webII.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.repository.CursoRepository;

@Service
public class CursoService {

    private static final Logger log = LoggerFactory.getLogger(CursoService.class);

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        log.debug("Listando todos os cursos");
        return cursoRepository.findAll();
    }

    public Curso listarCurso(Long codigo) {
        log.debug("Buscando curso de código {}", codigo);
        return cursoRepository.findById(codigo).orElse(null);
    }

    @Transactional
    public void cadastrarCurso(Curso curso) {
        cursoRepository.save(curso);
        log.info("Curso cadastrado com sucesso: {}", curso.getNome());
    }

    @Transactional
    public void atualizarCurso(Curso curso) {
        cursoRepository.save(curso);
        log.info("Curso de código {} atualizado com sucesso", curso.getCodigo());
    }

    @Transactional
    public void deletarCursoporID(Long codigo) {
        log.debug("Tentando deletar o curso de código {}", codigo);
        cursoRepository.deleteById(codigo);
        log.info("Curso de código {} deletado com sucesso", codigo);
    }
}