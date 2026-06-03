package edu.ifpb.webII.security;

import edu.ifpb.webII.util.PerfilTipo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    private static final String ADMIN       = PerfilTipo.ADMIN.getDesc();
    private static final String COORDENADOR = PerfilTipo.COORDENADOR.getDesc();
    private static final String PROFESSOR   = PerfilTipo.PROFESSOR.getDesc();
    private static final String ESTUDANTE   = PerfilTipo.ESTUDANTE.getDesc();

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> authorize

            // ── Libera recursos estáticos ──────────────────────────────
            .requestMatchers(
                "/webjars/**",
                "/css/**",
                "/images/**",
                "/js/**"
            ).permitAll()

            // ── Controle de autorização por perfil ─────────────────────
            .requestMatchers("/alunos/**")
                .hasAnyAuthority(ADMIN, ESTUDANTE)

            .requestMatchers("/cursos/**")
                .hasAnyAuthority(ADMIN, COORDENADOR)

            .requestMatchers("/disciplinas/**")
                .hasAnyAuthority(ADMIN, COORDENADOR)

            .requestMatchers("/matriculas/**")
                .hasAnyAuthority(ADMIN, ESTUDANTE)

            .requestMatchers("/professores/**")
                .hasAnyAuthority(ADMIN, PROFESSOR)

            .requestMatchers("/home", "/")
                .hasAnyAuthority(ADMIN, COORDENADOR, PROFESSOR, ESTUDANTE)

            // Qualquer outra rota exige autenticação
            .anyRequest().authenticated()
        )

        // ── Login ──────────────────────────────────────────────────────
        .formLogin((formLogin) -> formLogin
            .loginPage("/login")
            .defaultSuccessUrl("/home", true)
            // Use esta linha se quiser redirecionar por perfil:
            // .successHandler(new CustomAuthenticationSuccessHandler())
            .failureUrl("/login-error")
            .permitAll()
        )

        // ── Logout ────────────────────────────────────────────────────
        .logout((logout) -> logout
            .logoutSuccessUrl("/login")
            .deleteCookies("JSESSIONID")   // boa prática: limpar cookie
        )

        // ── Controle de sessão ────────────────────────────────────────
        .sessionManagement((session) -> session
            .invalidSessionUrl("/expired")
            .maximumSessions(1)
            .expiredUrl("/expired")
            .maxSessionsPreventsLogin(true)
            .sessionRegistry(sessionRegistry())
        );

        return http.build();
    }

    // Criptografia de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Necessário para controle de múltiplas sessões
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}