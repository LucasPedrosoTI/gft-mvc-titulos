package com.gft.estudosmvc.controller;

import javax.servlet.http.HttpServletRequest;

import com.gft.estudosmvc.model.Usuario;
import com.gft.estudosmvc.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

  public static final String USUARIO_CADASTRO_VIEW = "Cadastrar";

  @Autowired
  private UsuarioService usuarioService;

  @GetMapping("/login")
  public String loginView() {
    return "Login";
  }

  @GetMapping("/cadastrar")
  public ModelAndView cadastrar() {
    ModelAndView mv = new ModelAndView(USUARIO_CADASTRO_VIEW);
    mv.addObject(new Usuario());
    return mv;
  }

  @PostMapping("/usuarios/novo")
  public String salvar(HttpServletRequest request, @Validated Usuario usuario, Errors errors,
      RedirectAttributes attributes) {
    if (errors.hasErrors()) {
      return USUARIO_CADASTRO_VIEW;
    }

    try {
      Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario, request);
      attributes.addAttribute(novoUsuario);

      return "redirect:/titulos";
    } catch (Exception e) {
      errors.rejectValue("email", null, e.getMessage());
      return USUARIO_CADASTRO_VIEW;
    }

  }

  @PostMapping("/login")
  public String logar() throws Exception {
    return "redirect:/titulos";
  }
}
