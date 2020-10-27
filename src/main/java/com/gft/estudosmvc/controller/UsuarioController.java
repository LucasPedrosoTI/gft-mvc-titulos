package com.gft.estudosmvc.controller;

import com.gft.estudosmvc.Constants;
import com.gft.estudosmvc.model.Usuario;
import com.gft.estudosmvc.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

  public static final String USUARIO_CADASTRO_VIEW = "Cadastrar";

  @Autowired
  private UsuarioService usuarioService;

  @GetMapping("/login")
  private String loginView() {
    return "Login";
  }

  @GetMapping("/cadastrar")
  private ModelAndView cadastrar() {
    ModelAndView mv = new ModelAndView(USUARIO_CADASTRO_VIEW);
    mv.addObject(new Usuario());
    return mv;
  }

  @PostMapping("/novo")
  public String salvar(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {
    if (errors.hasErrors()) {
      return USUARIO_CADASTRO_VIEW;
    }

    Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
    attributes.addAttribute(novoUsuario);

    return String.format(Constants.REDIRECT_USUARIO_TITULOS, novoUsuario.getId());
  }

  @PostMapping("/login")
  private String logar(Usuario usuario) throws Exception {
    try {
      Usuario user = usuarioService.logar(usuario);
      return String.format(Constants.REDIRECT_USUARIO_TITULOS, user.getId());
    } catch (Exception e) {
      return "redirect:/usuarios/login?erro=true";
    }
  }
}
