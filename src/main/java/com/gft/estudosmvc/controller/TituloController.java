package com.gft.estudosmvc.controller;

import com.gft.estudosmvc.model.Titulo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@RequestMapping("/novo")
	public String pageNovoTitulo() {
		return "CadastroTitulo";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(Titulo titulo) {
		System.out.println(titulo.toString());
		return "CadastroTitulo";
	}
}
