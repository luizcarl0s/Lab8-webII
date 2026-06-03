package edu.ifpb.webII;

import edu.ifpb.webII.model.Perfil;
import edu.ifpb.webII.model.Usuario;
import edu.ifpb.webII.model.service.UsuarioService;
import edu.ifpb.webII.repository.PerfilRepository;
import edu.ifpb.webII.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public void run(String... args) throws Exception {

        if (usuarioRepository.findByUsername("admin@suap.edu.br").isEmpty()) {

            Perfil admin       = perfilRepository.save(new Perfil()); admin.setNome("ADMIN");       perfilRepository.save(admin);
            Perfil coordenador = perfilRepository.save(new Perfil()); coordenador.setNome("COORDENADOR"); perfilRepository.save(coordenador);
            Perfil professor   = perfilRepository.save(new Perfil()); professor.setNome("PROFESSOR");   perfilRepository.save(professor);
            Perfil estudante   = perfilRepository.save(new Perfil()); estudante.setNome("ESTUDANTE");   perfilRepository.save(estudante);

            Usuario u1 = new Usuario();
            u1.setUsername("admin@suap.edu.br");
            u1.setNome("Administrador");
            u1.setSenha("admin123");
            u1.setStatus("ATIVO");
            u1.setPerfis(Arrays.asList(admin));
            usuarioService.salvarUsuario(u1);

            Usuario u2 = new Usuario();
            u2.setUsername("professor@suap.edu.br");
            u2.setNome("Professor Teste");
            u2.setSenha("prof123");
            u2.setStatus("ATIVO");
            u2.setPerfis(Arrays.asList(professor));
            usuarioService.salvarUsuario(u2);

            Usuario u3 = new Usuario();
            u3.setUsername("coordenador@suap.edu.br");
            u3.setNome("Coordenador Teste");
            u3.setSenha("coord123");
            u3.setStatus("ATIVO");
            u3.setPerfis(Arrays.asList(coordenador));
            usuarioService.salvarUsuario(u3);

            Usuario u4 = new Usuario();
            u4.setUsername("aluno@suap.edu.br");
            u4.setNome("Aluno Teste");
            u4.setSenha("aluno123");
            u4.setStatus("ATIVO");
            u4.setPerfis(Arrays.asList(estudante));
            usuarioService.salvarUsuario(u4);
        }
    }
}