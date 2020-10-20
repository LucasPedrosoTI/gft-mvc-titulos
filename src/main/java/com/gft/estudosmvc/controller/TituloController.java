package com.gft.estudosmvc.controller;

import java.util.Arrays;
import java.util.List;

import com.gft.estudosmvc.model.StatusTitulo;
import com.gft.estudosmvc.model.Titulo;
import com.gft.estudosmvc.repository.Titulos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")
	public ModelAndView pageNovoTitulo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject(new Titulo());
		return mv;
	}

	@PostMapping
	public ModelAndView save(@Validated Titulo titulo, Errors errors) {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		if (errors.hasErrors()) {
			return mv;
		}

		titulos.save(titulo);
		mv.addObject("mensagem", "Titulo salvo com sucesso!");
		return mv;
	}

	@GetMapping
	public ModelAndView pesquisar() {
		List<Titulo> allTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", allTitulos);
		return mv;
	}

	@ModelAttribute("allStatusTitulo")
	public List<StatusTitulo> allStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}
