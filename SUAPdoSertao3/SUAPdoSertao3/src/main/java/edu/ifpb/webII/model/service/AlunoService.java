package edu.ifpb.webII.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import edu.ifpb.webII.model.Aluno;
import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public List<Aluno> listarAlunosNome(String nome) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Aluno> listarAlunosCodigo(Curso curso) {
        return alunoRepository.findByCurso(curso);
    }
    
    public Aluno listarAluno(Long matricula) {
        return alunoRepository.findById(matricula).orElse(null);
    }

	public void salvarAluno(Aluno aluno) {
    alunoRepository.save(aluno);
	}

	public void excluirAluno(Long matricula) {
    alunoRepository.deleteById(matricula);
	}
}
