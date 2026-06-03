package edu.ifpb.webII.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Professor {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prof_seq_gerador")
    @SequenceGenerator(name = "prof_seq_gerador", sequenceName = "professor_seq", allocationSize = 1)
    private Long matricula;
    private String nome;
    private String area;

    @ManyToOne
    @JoinColumn(name = "curso_codigo")
    private Curso curso;

    @JsonIgnore
    @OneToMany(mappedBy = "coordenador")
    private List<Projeto> projetosCoordenados;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<Disciplina> disciplinas;

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Projeto> getProjetosCoordenados() {
		return projetosCoordenados;
	}

	public void setProjetosCoordenados(List<Projeto> projetosCoordenados) {
		this.projetosCoordenados = projetosCoordenados;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}