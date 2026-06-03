package edu.ifpb.webII.controller;

import edu.ifpb.webII.model.Projeto;
import edu.ifpb.webII.model.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Projeto projeto) {
        return "projetos/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        List<Projeto> projetos = projetoService.listarProjetos();
        model.addAttribute("projetos", projetos);
        return "projetos/lista";
    }

    @GetMapping("/editar/{codigo}")
    public String preEditar(@PathVariable("codigo") Long codigo, ModelMap model) {
        Projeto projeto = projetoService.listarProjeto(codigo);
        model.addAttribute("projeto", projeto);
        return "projetos/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Projeto projeto, RedirectAttributes attr) {
        projetoService.atualizarProjeto(projeto);
        attr.addFlashAttribute("sucesso", "Projeto editado com sucesso!");
        return "redirect:/projetos/cadastro";
    }

    @GetMapping("/excluir/{codigo}")
    public String excluir(@PathVariable("codigo") Long codigo, ModelMap model) {
        projetoService.deletarProjetoPorID(codigo);
        model.addAttribute("sucesso", "Projeto excluído com sucesso!");
        return listar(model);
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Projeto projeto, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "projetos/cadastro";
        }
        projetoService.cadastrarProjeto(projeto);
        attr.addFlashAttribute("sucesso", "Projeto salvo com sucesso!");
        return "redirect:/projetos/cadastro";
    }
}