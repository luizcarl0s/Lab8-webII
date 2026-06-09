package edu.ifpb.webII.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView usuarioNaoEncontradoException(UsernameNotFoundException ex) {
        log.warn("Tentativa de login com usuário inexistente: {}", ex.getMessage());

        ModelAndView model = new ModelAndView("error");
        model.addObject("status", 404);
        model.addObject("error", "Operação não pode ser realizada.");
        model.addObject("message", ex.getMessage());
        return model;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView violacaoIntegridadeException(DataIntegrityViolationException ex) {

        log.error("Violação na integridade dos dados.", ex);

        ModelAndView model = new ModelAndView("error");
        model.addObject("status", 409);
        model.addObject("error", "Operação não pode ser realizada.");
        model.addObject("message", "Existem registros vinculados a este item. Remova-os antes de excluir.");
        return model;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView acessoNegadoException(AccessDeniedException ex) {
        log.warn("Acesso negado: {}", ex.getMessage());

        ModelAndView model = new ModelAndView("error");
        model.addObject("status", 403);
        model.addObject("error", "Acesso negado.");
        model.addObject("message", "Você não tem permissão para realizar esta operação.");
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView erroInesperado(Exception ex) {
        log.error("Erro inesperado no sistema.", ex);

        ModelAndView model = new ModelAndView("error");
        model.addObject("status", 500);
        model.addObject("error", "Erro interno do servidor.");
        model.addObject("message", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
        return model;
    }
}