package edu.ifpb.webII.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Disciplina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disc_seq_gerador")
    @SequenceGenerator(name = "disc_seq_gerador", sequenceName = "disciplina_seq", allocationSize = 1)
    private Long codigo;
    
    private String nome;
    private Integer cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "cod_curso") // Corrigido para bater com o SQL
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "cod_prof") // Corrigido para bater com o SQL
    private Professor professor;

    @JsonIgnore
    @OneToMany(mappedBy = "disciplina")
    private List<MatriculaDisciplina> matriculas;

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

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<MatriculaDisciplina> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<MatriculaDisciplina> matriculas) {
		this.matriculas = matriculas;
	}
}