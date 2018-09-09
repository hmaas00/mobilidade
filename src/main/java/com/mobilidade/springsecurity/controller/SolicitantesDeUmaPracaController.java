package com.mobilidade.springsecurity.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mobilidade.dao.PessoaDao;
import com.mobilidade.entidade.Pessoa;

@Controller
public class SolicitantesDeUmaPracaController {
	
	@GetMapping("/solicitantesDeUmaPraca")
	public String showSolicitantesDeUmaPraca(HttpServletRequest request, Principal principal, Model model) {
		
		// pegar os parametros enviados do request
		String idPracaAtualStr = request.getParameter("pracaAtual");
		String idPracaDesejadaStr =	request.getParameter("pracaDesejada");
		int idPracaAtual = Integer.parseInt(idPracaAtualStr);
		int idPracaDesejada = Integer.parseInt(idPracaDesejadaStr);
		
		// buscar no bd as pessoas na praca desejada que tenham alguma solicitação para a praca atual
		
		PessoaDao pesDao = new PessoaDao();
		
		List <Pessoa> listPessoa = 
				pesDao.getAllByPracaIdWherePracaDesejadaId(idPracaAtual, idPracaDesejada);
		
		//System.out.println("SolicitantesDeUmaPracaController - teste lista" + listPessoa.get(0).getPraca().getNomePraca());
		
		model.addAttribute("tamanho", listPessoa.size());
		model.addAttribute("pessoas", listPessoa);
		
		return "solicitantes-de-uma-praca";
	}

}
