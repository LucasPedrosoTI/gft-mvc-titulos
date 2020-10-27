package com.gft.estudosmvc.controller;

import java.util.Arrays;
import java.util.List;

import com.gft.estudosmvc.Constants;
import com.gft.estudosmvc.model.StatusTitulo;
import com.gft.estudosmvc.model.Titulo;
import com.gft.estudosmvc.repository.TituloFilter;
import com.gft.estudosmvc.service.CadastroTituloService;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/usuarios/{usuarioId}/titulos")
public class TituloController {

	private static final String CADASTRO_VIEW = "CadastroTitulo";

	@Autowired
	private CadastroTituloService cadastroTituloService;

	@RequestMapping("/novo")
	public ModelAndView paginaNovoTitulo(@PathVariable Long usuarioId) {

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);

		mv.addObject(new Titulo());
		return mv;
	}

	@PostMapping
	public String salvar(@PathVariable("usuarioId") Long usuarioId, @Validated Titulo titulo, Errors errors,
			RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		try {
			cadastroTituloService.salvar(usuarioId, titulo);

			attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso!");

			return String.format(Constants.REDIRECT_USUARIO_TITULOS, String.valueOf(usuarioId));

		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@GetMapping
	public ModelAndView pesquisar(@PathVariable("usuarioId") Long usuarioId,
			@ModelAttribute("filtro") TituloFilter filtro) {
		List<Titulo> allTitulos = cadastroTituloService.filtrar(usuarioId, filtro);

		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", allTitulos);
		return mv;
	}

	@GetMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Titulo titulo) {

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);

		return mv;
	}

	@DeleteMapping("{id}")
	public String deletar(@PathVariable("usuarioId") Long usuarioId, Titulo titulo, RedirectAttributes attributes) {

		cadastroTituloService.deletar(titulo.getId());

		attributes.addFlashAttribute("mensagem", "Titulo excluído com sucesso");
		return String.format(Constants.REDIRECT_USUARIO_TITULOS, String.valueOf(usuarioId));
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
	public Long usuarioId(@PathVariable Long usuarioId) {
		return usuarioId;
	}
}
