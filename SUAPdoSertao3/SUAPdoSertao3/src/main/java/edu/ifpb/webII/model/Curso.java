package edu.ifpb.webII.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Curso {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_seq_gerador")
    @SequenceGenerator(name = "curso_seq_gerador", sequenceName = "curso_seq", allocationSize = 1)
    private Long codigo;
    private String nome;
    private String area;

    @JsonIgnore
    @OneToMany(mappedBy = "curso")
    private List<Aluno> alunos;

    @JsonIgnore
    @OneToMany(mappedBy = "curso")
    private List<Professor> professores;

    @JsonIgnore
    @OneToMany(mappedBy = "curso")
    private List<Disciplina> disciplinas;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}