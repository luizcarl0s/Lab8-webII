package edu.ifpb.webII.controller;

import edu.ifpb.webII.model.Aluno;
import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.model.Endereco;
import edu.ifpb.webII.model.service.AlunoService;
import edu.ifpb.webII.model.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model) {
        model.addAttribute("aluno", new Aluno());
        return "alunos/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(Aluno aluno, RedirectAttributes attr) {
        alunoService.salvarAluno(aluno);
        attr.addFlashAttribute("sucesso", "Aluno salvo com sucesso!");
        return "redirect:/alunos/lista";
    }

    @GetMapping("/editar/{matricula}")
    public String preEditar(@PathVariable("matricula") Long matricula, ModelMap model) {
        Aluno aluno = alunoService.listarAluno(matricula);
        
        if (aluno.getEndereco() == null) {
            aluno.setEndereco(new Endereco()); 
        }
 
        if (aluno.getCurso() == null) {
            aluno.setCurso(new Curso());
        }
        
        model.addAttribute("aluno", aluno);
        return "alunos/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Aluno aluno, RedirectAttributes attr) {
        alunoService.salvarAluno(aluno);
        attr.addFlashAttribute("sucesso", "Aluno atualizado com sucesso!");
        return "redirect:/alunos/lista";
    }
    
    @GetMapping("/excluir/{matricula}")
    public String excluir(@PathVariable("matricula") Long matricula, RedirectAttributes attr) {
        alunoService.excluirAluno(matricula); 
        attr.addFlashAttribute("sucesso", "Aluno excluído com sucesso.");
        return "redirect:/alunos/lista";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("alunos", alunoService.listarAlunos());
        return "alunos/lista";
    }

    @GetMapping("/buscar/nome")
    public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
        model.addAttribute("alunos", alunoService.listarAlunosNome(nome));
        return "alunos/lista";
    }

    @GetMapping("/buscar/curso")
    public String getPorCurso(@RequestParam("codigo") Long codigo, ModelMap model) {
        Curso cursoFiltro = new Curso();
        cursoFiltro.setCodigo(codigo);
        
        model.addAttribute("alunos", alunoService.listarAlunosCodigo(cursoFiltro));
        return "alunos/lista";
    }

    @ModelAttribute("cursos")
    public List<Curso> getCursos() {
        return cursoService.listarCursos(); 
    }
}