package edu.ifpb.webII.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "MATRICULA") 
public class MatriculaDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    @Column(name = "PERIODO_MATRICULA") 
    private String periodo;

    @ManyToOne
    @JoinColumn(name = "MATRICULA_ALUNO")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "COD_DISCIPLINA") 
    private Disciplina disciplina;
    
    public String getPeriodo() {
        return this.periodo;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }
}