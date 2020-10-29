package com.gft.estudosmvc.controller;

import java.util.Arrays;
import java.util.List;

import com.gft.estudosmvc.model.StatusTitulo;
import com.gft.estudosmvc.model.Titulo;
import com.gft.estudosmvc.repository.TituloFilter;
import com.gft.estudosmvc.service.CadastroTituloService;
import com.gft.estudosmvc.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String CADASTRO_VIEW = "CadastroTitulo";

	@Autowired
	private CadastroTituloService cadastroTituloService;

	@Autowired
	private UsuarioService usuarioService;

	public Long getUsuarioId(String email) {
		return usuarioService.getUsuarioIdByEmail(email);
	}

	@GetMapping("")
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro,
			@AuthenticationPrincipal UserDetails user) {

		List<Titulo> allTitulos = cadastroTituloService.filtrar(getUsuarioId(user.getUsername()), filtro);

		ModelAndView mv = new ModelAndView("PesquisaTitulos");

		mv.addObject("titulos", allTitulos);

		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView paginaNovoTitulo() {

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);

		mv.addObject(new Titulo());
		return mv;
	}

	@PostMapping("/novo")
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes,
			@AuthenticationPrincipal UserDetails user) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		try {

			cadastroTituloService.salvar(getUsuarioId(user.getUsername()), titulo);

			attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso!");

			return "redirect:/titulos";

		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@GetMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Titulo titulo) {

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);

		return mv;
	}

	@DeleteMapping("{id}")
	public String deletar(Titulo titulo, RedirectAttributes attributes) {

		cadastroTituloService.deletar(titulo.getId());

		attributes.addFlashAttribute("mensagem", "Titulo excluído com sucesso");

		return "redirect:/titulos";
	}

	@PutMapping("{id}/receber")
	public @ResponseBody String receber(@PathVariable("id") Long id) {
		return cadastroTituloService.receber(id);
	}

	@ModelAttribute("allStatusTitulo")
	public List<StatusTitulo> allStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

	@ModelAttribute("usuarioId")
	public Long usuarioId(@AuthenticationPrincipal UserDetails user) {
		return usuarioService.getUsuarioIdByEmail(user.getUsername());
	}
}
