package edu.ifpb.webII.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluno_seq_gerador")
    @SequenceGenerator(name = "aluno_seq_gerador", sequenceName = "aluno_seq", allocationSize = 1)
    private Long matricula;
    
    private String nome;

    @Embedded
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "cod_curso") 
    private Curso curso;

    @ManyToMany
    @JoinTable(name = "aluno_projeto",
        joinColumns = @JoinColumn(name = "aluno_matricula"),
        inverseJoinColumns = @JoinColumn(name = "projeto_codigo"))
    private List<Projeto> projetos;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno")
    private List<MatriculaDisciplina> matriculasDisciplina;

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public List<MatriculaDisciplina> getMatriculasDisciplina() {
		return matriculasDisciplina;
	}

	public void setMatriculasDisciplina(List<MatriculaDisciplina> matriculasDisciplina) {
		this.matriculasDisciplina = matriculasDisciplina;
	}
}
