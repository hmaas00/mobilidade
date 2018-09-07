package com.mobilidade.springsecurity.controller;

import java.security.Principal;

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

public class ProcessaSolicitacaoController {
	///processa-solicitacao-permuta


	/*PARAMETERS
	 * processo=1
		&motivo=0

		&pracaEscolhida1=0
		&unidadeEscolhida1=0
		&processoEscolhido1=0
	 */
	@GetMapping("/processa-solicitacao-permuta")
	public String processaSolicitacao( HttpServletRequest request, Principal principal, Model model) {

		// testar se existem as solicitações, quantas são...
		// se não foi escolhida uma praça, desconsiderar os outros campos...

		// variaveis auxiliares
		int quantidadeSolicitacoes = 0;
		boolean solicitou1 = false;
		boolean solicitou2 = false;
		boolean solicitou3 = false;
		// Daos
		PracaDao pracaDao = new PracaDao();
		UnidadeDao unidadeDao = new UnidadeDao();
		PessoaDao pesDao = new PessoaDao();
		CadeiaValorSubgrupoDao cadeiaValorSubgrupoDao = new CadeiaValorSubgrupoDao();
		SolicitacaoPermutaDao permutaDao = new SolicitacaoPermutaDao();
		// classes temporarias
		Praca praca1 =null;
		Unidade unidade1 =null;
		CadeiaValorSubgrupo cadeiaValorSubgrupo1 =null;
		SolicitacaoPermuta permuta1 =null;

		Praca praca2 =null;
		Unidade unidade2 =null;
		CadeiaValorSubgrupo cadeiaValorSubgrupo2 =null;
		SolicitacaoPermuta permuta2 =null;

		Praca praca3 =null;
		Unidade unidade3 =null;
		CadeiaValorSubgrupo cadeiaValorSubgrupo3 =null;
		SolicitacaoPermuta permuta3 =null;

		// 

		// carregar pessoa
		String ln = principal.getName();

		System.out.println("controller processaSolicitacao: principal - " + ln);


		Pessoa p = pesDao.findByLoginName(ln);
		
		// deletar todas as solicitações anteriores
		
		permutaDao.deleteAllByPessoa(p);

		// carregar processo de trabalho selecionado
		CadeiaValorSubgrupo processoAtual = new CadeiaValorSubgrupo();

		processoAtual = cadeiaValorSubgrupoDao.findById(Integer.parseInt(request.getParameter("processo")));


		// carrega motivo da solicitação

		String motivo = Motivo.getListaMotivos().get(   Integer.parseInt(request.getParameter("motivo"))     );

		System.out.println("controller processaSolicitacao: motivo - " + motivo);

		// Update motivo e processo atual

		pesDao.updateProcessoMotivo(p, processoAtual, motivo);

		if( Integer.parseInt(request.getParameter("pracaEscolhida1")) != 0 ) {
			// pegar praça, unidade e processo de trabalho desejado
			praca1 = pracaDao.findById( Integer.parseInt(request.getParameter("pracaEscolhida1"))  );
			
			if( Integer.parseInt(request.getParameter("unidadeEscolhida1")) != 0 ) {
				unidade1 = unidadeDao.findById( Integer.parseInt(request.getParameter("unidadeEscolhida1")) );
			}
			if( Integer.parseInt(request.getParameter("processoEscolhido1")) != 0 ) {
				cadeiaValorSubgrupo1 = cadeiaValorSubgrupoDao.findById( Integer.parseInt(request.getParameter("processoEscolhido1")) );
			}
			
			// montar solicitação e salvar
			permuta1 = new SolicitacaoPermuta(p, praca1, unidade1, cadeiaValorSubgrupo1);
			permutaDao.save(permuta1);
			solicitou1 = true;
			
			quantidadeSolicitacoes++;
		}
		if( Integer.parseInt(request.getParameter("pracaEscolhida2")) != 0 ) {
			// pegar praça, unidade e processo de trabalho desejado
			praca2 = pracaDao.findById(      Integer.parseInt(request.getParameter("pracaEscolhida2")) );
			if( Integer.parseInt(request.getParameter("unidadeEscolhida2")) != 0 ) {
				unidade2 = unidadeDao.findById(  Integer.parseInt(request.getParameter("unidadeEscolhida2")) );
			}
			if( Integer.parseInt(request.getParameter("processoEscolhido2")) != 0 ) {
				cadeiaValorSubgrupo2 = cadeiaValorSubgrupoDao.findById( Integer.parseInt(request.getParameter("processoEscolhido2")) );
			}
			// montar solicitação e salvar			
			permuta2 = new SolicitacaoPermuta(p, praca2, unidade2, cadeiaValorSubgrupo2);
			permutaDao.save(permuta2);
			solicitou2 = true;
			quantidadeSolicitacoes++;
		}
		if( Integer.parseInt(request.getParameter("pracaEscolhida3")) != 0 ) {
			// pegar praça, unidade e processo de trabalho desejado
			praca3 = pracaDao.findById(      Integer.parseInt(request.getParameter("pracaEscolhida3"))         );
			if( Integer.parseInt(request.getParameter("unidadeEscolhida3")) != 0 ) {
				unidade3 = unidadeDao.findById(  Integer.parseInt(request.getParameter("unidadeEscolhida3"))  );
			}
			
			if( Integer.parseInt(request.getParameter("processoEscolhido3")) != 0 ) {
				cadeiaValorSubgrupo3 = cadeiaValorSubgrupoDao.findById( Integer.parseInt(request.getParameter("processoEscolhido3")) );
			}
			
			// montar solicitação e salvar
			permuta3 = new SolicitacaoPermuta(p, praca3, unidade3, cadeiaValorSubgrupo3);
			permutaDao.save(permuta3);
			solicitou3 = true;
			quantidadeSolicitacoes++;
		}

		System.out.println("controller processaSolicitacao: quantidade de solicitações - " + quantidadeSolicitacoes);
		
		
		
		//System.out.println("\n\ns='': " + s.equals(""));
		
		return "confirmacao-solicitacao";
	}

}
