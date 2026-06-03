package edu.ifpb.webII.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import edu.ifpb.webII.model.Disciplina;
import edu.ifpb.webII.repository.DisciplinaRepositoryPagin;
import edu.ifpb.webII.util.PaginacaoUtil;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepositoryPagin dRepository;

    public List<Disciplina> listarDisciplinas() {
        return dRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PaginacaoUtil<Disciplina> buscarPorPagina(int paginaAtual) {
        int tamanho = 5; 
        Pageable pagina = PageRequest.of(paginaAtual - 1, tamanho); 
        
        List<Disciplina> disciplinas = dRepository.findAll(pagina).toList(); 
        long totalRegistros = dRepository.count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho; 
        
        return new PaginacaoUtil<>(tamanho, paginaAtual, totalDePaginas, disciplinas); 
    }

    @Transactional
    public void salvarDisciplina(Disciplina disciplina) {
        dRepository.save(disciplina);
    }

    public Disciplina buscarPorId(Long codigo) {
        return dRepository.findById(codigo).orElse(null); 
    }

    public void excluir(Long codigo) {
        dRepository.deleteById(codigo);
    }
}