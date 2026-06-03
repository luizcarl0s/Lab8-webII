package edu.ifpb.webII.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import edu.ifpb.webII.model.Projeto;
import edu.ifpb.webII.repository.ProjetoRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    public Projeto listarProjeto(Long codigo) {
        return projetoRepository.findById(codigo).orElse(null);
    }

    public void cadastrarProjeto(Projeto projeto) {
        projetoRepository.save(projeto);
    }

    public void atualizarProjeto(Projeto projeto) {
        projetoRepository.save(projeto);
    }

    public void deletarProjetoPorID(Long codigo) {
        projetoRepository.deleteById(codigo);
    }
}