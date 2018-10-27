





package com.mobilidade.springsecurity.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mobilidade.auxiliar.Motivo;
import com.mobilidade.dao.CadeiaValorSubgrupoDao;
import com.mobilidade.dao.PessoaDao;
import com.mobilidade.dao.PracaDao;
import com.mobilidade.dao.SolicitacaoPermutaDao;
import com.mobilidade.dao.UnidadeDao;
import com.mobilidade.entidade.CadeiaValorSubgrupo;
import com.mobilidade.entidade.Pessoa;
import com.mobilidade.entidade.Praca;
import com.mobilidade.entidade.SolicitacaoPermuta;
import com.mobilidade.entidade.Unidade;

@Controller
public class OpcoesBuscaController {
	///processa-solicitacao-permuta


	/*PARAMETERS
	 * processo=1
		&motivo=0

		&pracaEscolhida1=0
		&unidadeEscolhida1=0
		&processoEscolhido1=0
	 */
	@GetMapping("/opcoes-busca")
	public String opcoesBusca( HttpServletRequest request, Principal principal, Model model) {

		
		PessoaDao pesDao = new PessoaDao();

		// 

		// carregar pessoa
		String ln = principal.getName();

		System.out.println("Controller opcoesBusca - " + ln);


		Pessoa p = pesDao.findByLoginNameEager(ln);

		
		// avaliar se ator possui ao menos uma solicitação com unidade ou processo preenchidos
		// para apresentar o botao de busca circular
		
		boolean permitirBuscaCircular = false;
		
		p = pesDao.findByLoginName(ln);
				
		for(SolicitacaoPermuta s : p.getListSolicitacaoPermuta()) {
			if(s.getUnidade() != null || s.getCadeiaValorSubgrupo() != null) {
				permitirBuscaCircular = true;
			}
		}
		
		

		// Adições no model...
		
		model.addAttribute("processo", p.getCadeiaValorSubgrupo().getDescricaoSubgrupo());
		model.addAttribute("motivo", p.getMotivoPrincipal());
		model.addAttribute("quantidade", p.getListSolicitacaoPermuta().size());
		model.addAttribute("permutas", p.getListSolicitacaoPermuta());
		System.out.println( " tttttttttttttttttttttttttttttttttttttttttttttttt"+ p.getPraca().getNomePraca());
		model.addAttribute("pracaAtual", p.getPraca().getNomePraca());
		model.addAttribute("pracaAtualId", p.getPraca().getIdPraca());
		model.addAttribute( "permitirBuscaCircular", permitirBuscaCircular);
		//System.out.println("\n\ns='': " + s.equals(""));

		return "buscas";
	}

}

