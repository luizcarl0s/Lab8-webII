package edu.ifpb.webII.controller;

import edu.ifpb.webII.model.Professor;
import edu.ifpb.webII.model.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/cadastrar")
    public String cadastrar(Professor professor) {
        return "professores/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        List<Professor> professores = professorService.listarProfessores();
        model.addAttribute("professores", professores);
        return "professores/lista";
    }

    @GetMapping("/editar/{matricula}")
    public String preEditar(@PathVariable("matricula") Long matricula, ModelMap model) {
        Professor professor = professorService.listarProfessor(matricula);
        model.addAttribute("professor", professor);
        return "professores/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Professor professor, RedirectAttributes attr) {
        professorService.atualizarProfessor(professor);
        attr.addFlashAttribute("sucesso", "Professor editado com sucesso!");
        return "redirect:/professores/cadastro";
    }

    @GetMapping("/excluir/{matricula}")
    public String excluir(@PathVariable("matricula") Long matricula, ModelMap model) {
        professorService.deletarProfessorPorID(matricula);
        model.addAttribute("sucesso", "Professor excluído com sucesso!");
        return listar(model);
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Professor professor, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "professores/cadastro";
        }
        professorService.cadastrarProfessor(professor);
        attr.addFlashAttribute("sucesso", "Professor salvo com sucesso!");
        return "redirect:/professores/cadastro";
    }
}