package com.mobilidade.springsecurity.controller;

import java.security.Principal;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mobilidade.dao.PessoaDao;
import com.mobilidade.entidade.Pessoa;
import com.mobilidade.entidade.Praca;
import com.mobilidade.entidade.User;

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
	public String showPermutaForm(Principal principal, Model model) {
		
		//login name
		String ln = principal.getName();
		
		System.out.println("controller solicitar-permuta: principal - " + ln);
		//dao Pessoa
		PessoaDao pesDao = new PessoaDao();
		
		Pessoa p = pesDao.findByLoginName(ln);
		
		System.out.println("controller solicitar-permuta: nome pessoa - " + p.getNomePessoa());
		System.out.println("controller solicitar-permuta: praca pessoa - " + p.getPraca().getNomePraca());
		System.out.println("controller solicitar-permuta: componente pessoa - " + p.getComponenteAdministrativo().getNomeComponente());
		System.out.println("controller solicitar-permuta: unidade - " + p.getComponenteAdministrativo().getUnidade());
		
		//add model - pessoa
		model.addAttribute("pessoa", p);
		return "solicitacao-permuta";
		
	}
		
}









