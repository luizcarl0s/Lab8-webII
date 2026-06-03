package edu.ifpb.webII.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

import edu.ifpb.webII.model.Aluno;
import edu.ifpb.webII.model.Disciplina;
import edu.ifpb.webII.model.MatriculaDisciplina;
import edu.ifpb.webII.model.service.AlunoService;
import edu.ifpb.webII.model.service.DisciplinaService;
import edu.ifpb.webII.model.service.MatriculaService;

@Controller
@RequestMapping("/matriculas") 
public class MatriculaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private MatriculaService matriculaService; 

    @Autowired
    private AlunoService alunoService; 
    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model) {
        model.addAttribute("matricula", new MatriculaDisciplina());
        return "matriculas/cadastro"; 
    }


    @PostMapping("/salvar")
    public String salvar(MatriculaDisciplina matricula, RedirectAttributes attr) {
        matriculaService.salvar(matricula); 
        attr.addFlashAttribute("sucesso", "Matrícula realizada com sucesso!");
        return "redirect:/matriculas/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        Map<String, List<Disciplina>> disciplinasPorPeriodo = matriculaService.buscarDisciplinasporPeriodo(alunoService.listarAluno(4L));
        model.addAttribute("disciplinasPorPeriodo", disciplinasPorPeriodo); 
        return "matriculas/lista";
    }

    @ModelAttribute("listaDisciplinas")
    public List<Disciplina> getDisciplinas(){
        return disciplinaService.listarDisciplinas();
    }

    @ModelAttribute("listaAlunos")
    public List<Aluno> getAlunos(){
        return alunoService.listarAlunos();
    }
}