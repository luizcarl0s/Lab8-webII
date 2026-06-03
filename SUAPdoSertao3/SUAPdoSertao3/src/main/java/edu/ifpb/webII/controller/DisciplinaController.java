package edu.ifpb.webII.controller;

import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.model.Disciplina;
import edu.ifpb.webII.model.Professor;
import edu.ifpb.webII.model.service.CursoService;
import edu.ifpb.webII.model.service.DisciplinaService;
import edu.ifpb.webII.model.service.ProfessorService;
import edu.ifpb.webII.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService dService;

    @Autowired
    private CursoService cService;

    @Autowired
    private ProfessorService pService;

    @GetMapping("/listar")
    public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page) {
        int paginaAtual = page.orElse(1);
        PaginacaoUtil<Disciplina> pageDisciplina = dService.buscarPorPagina(paginaAtual);
        model.addAttribute("pageDisciplina", pageDisciplina);
        return "disciplinas/lista";
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(ModelMap model) {
        model.addAttribute("disciplina", new Disciplina());
        return "disciplinas/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(Disciplina disciplina, RedirectAttributes attributes) {
        dService.salvarDisciplina(disciplina);
        attributes.addFlashAttribute("mensagem", "Disciplina salva com sucesso!");
        return "redirect:/disciplinas/listar";
    }
    
    @ModelAttribute("cursos")
    public List<Curso> getCursos() {
        return cService.listarCursos();
    }

    @ModelAttribute("professores")
    public List<Professor> getProfessores() {
        return pService.listarProfessores();
    }
    
    @GetMapping("/editar/{codigo}")
    public String preEdicao(@PathVariable("codigo") Long codigo, ModelMap model) {
        model.addAttribute("disciplina", dService.buscarPorId(codigo)); 
        return "disciplinas/cadastro";
    }

    @GetMapping("/excluir/{codigo}")
    public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
        dService.excluir(codigo); 
        attr.addFlashAttribute("sucesso", "Disciplina excluída com sucesso.");
        return "redirect:/disciplinas/listar";
    }
}