package com.mobilidade.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mobilidade.entidade.Pessoa;
import com.mobilidade.repos.PessoaRepository;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		// return "plain-login";

		return "fancy-login";
		
	}
	
	// add request mapping for /access-denied
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	// pagina de solicitacao de permuta
	@GetMapping("/solicitar-permuta")
	public String showPermutaForm() {
		
		return "solicitacao-permuta";
		
	}
		
}









