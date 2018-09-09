package com.mobilidade.springsecurity.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mobilidade.dao.PessoaDao;
import com.mobilidade.entidade.Pessoa;

@Controller
public class DetalhesPessoaController {
	
	@GetMapping("/detalharPessoa")
	public String showDetalhes(HttpServletRequest request, Model model) {
		
		// buscar informações de uma pessoa pelo seu id
		
		//dao Pessoa
		PessoaDao pesDao = new PessoaDao();
		int id = Integer.parseInt(request.getParameter("idPessoa"));
		Pessoa p = pesDao.findById(id);
		
		//System.out.println("DetalhesPessoaController - pessoa: " + p.getListSolicitacaoPermuta().get(0).getPraca());
		
		model.addAttribute("pessoa", p);
		model.addAttribute("permutas", p.getListSolicitacaoPermuta());
		
		return "detalhes-pessoa";
	}

}
