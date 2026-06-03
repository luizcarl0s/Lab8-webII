package edu.ifpb.webII.security;

import edu.ifpb.webII.util.PerfilTipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();

            if (role.equals(PerfilTipo.ADMIN.getDesc())) {
                response.sendRedirect("/home");
                return;
            } else if (role.equals(PerfilTipo.COORDENADOR.getDesc())) {
                response.sendRedirect("/homeCoordenador");
                return;
            }
        }

        // Redirecionamento padrão para outros perfis
        response.sendRedirect("/home");
    }
}