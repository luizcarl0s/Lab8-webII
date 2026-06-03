package edu.ifpb.webII.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.model.service.CursoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("cursos")
public class CursoController {
	
    @Autowired
    private CursoService cursoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Curso curso) {
        return "cursos/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        List<Curso> cursos = cursoService.listarCursos();
        model.addAttribute("cursos", cursos);
        return "cursos/lista";
    }
    
    @GetMapping("/editar/{codigo}")
    public String preEditar(@PathVariable("codigo") Long codigo, ModelMap model) {
        Curso curso = cursoService.listarCurso(codigo);
        model.addAttribute("curso", curso);
        return "cursos/cadastro";
    }
    
    @PostMapping("/editar")
    public String editar(Curso curso, RedirectAttributes attr) {
        cursoService.atualizarCurso(curso);
        attr.addFlashAttribute("sucesso", "Curso editado com sucesso!");
        return "redirect:/cursos/cadastro";
    }
    
    @GetMapping("/excluir/{codigo}")
    public String excluir(@PathVariable("codigo") Long codigo, ModelMap model) {
        cursoService.deletarCursoporID(codigo);
        model.addAttribute("sucesso", "Curso excluído com sucesso!");
        return listar(model);
    }
    
    @PostMapping("/salvar")
    public String salvar(@Valid Curso curso, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "cursos/cadastro";
        }
        cursoService.cadastrarCurso(curso);
        attr.addFlashAttribute("sucesso", "Curso salvo com sucesso!");
        return "redirect:/cursos/cadastro";
    }
}