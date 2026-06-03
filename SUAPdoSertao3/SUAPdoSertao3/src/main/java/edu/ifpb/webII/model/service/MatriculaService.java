package edu.ifpb.webII.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import edu.ifpb.webII.model.Aluno;
import edu.ifpb.webII.model.Disciplina;
import edu.ifpb.webII.model.MatriculaDisciplina;
import edu.ifpb.webII.repository.MatriculaDisciplinaRepository;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaDisciplinaRepository matriculaRepository;

    public Map<String, List<Disciplina>> buscarDisciplinasporPeriodo(Aluno aluno) {
        List<MatriculaDisciplina> alunoMatricula = matriculaRepository.findByAluno(aluno);
        
        return alunoMatricula.stream()
                .collect(Collectors.groupingBy(
                        MatriculaDisciplina::getPeriodo,
                        Collectors.mapping(MatriculaDisciplina::getDisciplina, Collectors.toList())
                ));
    }
    
    public void salvar(MatriculaDisciplina matricula) {
        matriculaRepository.save(matricula);
    }
}