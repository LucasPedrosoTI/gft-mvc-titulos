package com.gft.estudosmvc.controller;

import com.gft.estudosmvc.model.Titulo;
import com.gft.estudosmvc.repository.Titulos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")
	public String pageNovoTitulo() {
		return "CadastroTitulo";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(Titulo titulo) {

		titulos.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Titulo salvo com sucesso!");
		return mv;
	}
}
