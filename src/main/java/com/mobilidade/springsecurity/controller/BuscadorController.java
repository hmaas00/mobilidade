package com.mobilidade.springsecurity.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mobilidade.auxiliar.AvaliadorRelacaoSimetrica;
import com.mobilidade.auxiliar.DFS;
import com.mobilidade.auxiliar.NodeRelevanciaSimetrica;
import com.mobilidade.dao.PessoaDao;
import com.mobilidade.entidade.Pessoa;

@Controller
public class BuscadorController {



	@GetMapping("/todosOutrosSolicitantes")
	public String showTodosOutrosSolicitantes(HttpServletRequest request, Principal principal, Model model) {

		// pegar a pessoa do usuario logado
		//login name
		String ln = principal.getName();

		System.out.println("SolicitantesDeUmaPracaController-permuta: principal - " + ln);
		//dao Pessoa
		PessoaDao pesDao = new PessoaDao();

		Pessoa p = pesDao.findByLoginName(ln);

		// buscar no bd todos os solicitantes com solicitações!

		List <Pessoa> listPessoa = 
				pesDao.findAllComSolicitacao();

		// eliminar o proprio usuario logado da lista

		List<Pessoa> listSemLogado = AvaliadorRelacaoSimetrica.eliminarReflexividade(p, listPessoa);

		// novo: atribuir relevancia

		AvaliadorRelacaoSimetrica avaliador = new AvaliadorRelacaoSimetrica();

		avaliador.setListNodeRelevanciaSimetrica(listSemLogado, p);

		// atualizar a lista

		List<NodeRelevanciaSimetrica> listNodes = avaliador.getListNodeRelevanciaSimetrica();

		// set model


		model.addAttribute("tamanho", listNodes.size());
		model.addAttribute("pessoas", listNodes);

		return "solicitantes-de-uma-praca-relevancia";

	}


	@GetMapping("/processaDFS")
	public String showBuscaDFS(HttpServletRequest request, Principal principal, Model model) {

		// pegar a pessoa do usuario logado
		//login name
		String ln = principal.getName();

		//dao Pessoa
		PessoaDao pesDao = new PessoaDao();

		Pessoa p = pesDao.findByLoginName(ln);
		
		DFS dfs = new DFS();
		
		dfs.startDFS(p);
		
		List relacoes = dfs.getListaRelacoes();
		
		List simetricas = dfs.getListasSimetricas();
		List triangulares = dfs.getListasTriangulares();
		List quadrangulares = dfs.getListasQuadrangulares();
		List pentangulares = dfs.getListasPentangulares();
		
		boolean possuiSimetricas = (simetricas.size() > 0);
		boolean possuiTriangulares = (triangulares.size() > 0);
		boolean possuiQuadrangulares = (quadrangulares.size() > 0);
		boolean possuiPentangulares = ( pentangulares.size() > 0);
		
		model.addAttribute("tamanho", relacoes.size());
		model.addAttribute("relacoes", relacoes);
		
		model.addAttribute("possuiSimetricas", possuiSimetricas);
		model.addAttribute("possuiTriangulares", possuiTriangulares);
		model.addAttribute("possuiQuadrangulares", possuiQuadrangulares);
		model.addAttribute("possuiPentangulares", possuiPentangulares);
		
		model.addAttribute("simetricas", simetricas);
		model.addAttribute("triangulares", triangulares);
		model.addAttribute("quadrangulares", quadrangulares);
		model.addAttribute("pentangulares", pentangulares);

		return "busca-circular";
	}


	@GetMapping("/solicitantesDeUmaPraca")
	public String showSolicitantesDeUmaPraca(HttpServletRequest request, Principal principal, Model model) {


		// pegar a pessoa do usuario logado
		//login name
		String ln = principal.getName();

		System.out.println("SolicitantesDeUmaPracaController-permuta: principal - " + ln);
		//dao Pessoa
		PessoaDao pesDao = new PessoaDao();

		Pessoa p = pesDao.findByLoginName(ln);


		// pegar os parametros enviados do request
		String idPracaAtualStr = request.getParameter("pracaAtual");
		String idPracaDesejadaStr =	request.getParameter("pracaDesejada");
		int idPracaAtual = Integer.parseInt(idPracaAtualStr);
		int idPracaDesejada = Integer.parseInt(idPracaDesejadaStr);

		// buscar no bd as pessoas na praca desejada que tenham alguma solicitação para a praca atual


		List <Pessoa> listPessoa = 
				pesDao.getAllByPracaIdWherePracaDesejadaId(idPracaAtual, idPracaDesejada);

		//System.out.println("SolicitantesDeUmaPracaController - teste lista" + listPessoa.get(0).getPraca().getNomePraca());

		// eliminar o proprio usuario logado da lista


		List<Pessoa> listSemLogado = AvaliadorRelacaoSimetrica.eliminarReflexividade(p, listPessoa);

		// set model
		model.addAttribute("tamanho", listSemLogado.size());
		model.addAttribute("pessoas", listSemLogado);

		return "solicitantes-de-uma-praca";
	}

	//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

	// solicitantes que preencheram processo desejado ou unidade desejada
	@GetMapping("/solicitantesDeUmaPracaRelevante")
	public String showSolicitantesDeUmaPracaRelevante(HttpServletRequest request, Principal principal, Model model) {


		// pegar a pessoa do usuario logado
		//login name
		String ln = principal.getName();

		System.out.println("SolicitantesDeUmaPracaController-permuta: principal - " + ln);
		//dao Pessoa
		PessoaDao pesDao = new PessoaDao();

		Pessoa p = pesDao.findByLoginName(ln);




		// pegar os parametros enviados do request
		String idPracaAtualStr = request.getParameter("pracaAtual");
		String idPracaDesejadaStr =	request.getParameter("pracaDesejada");
		int idPracaAtual = Integer.parseInt(idPracaAtualStr);
		int idPracaDesejada = Integer.parseInt(idPracaDesejadaStr);

		// setar parametros nulos com 0 para unidade e processo de trabalho!!!!!!!!!!
		/*
		pracaAtual=9
		pracaDesejada=1
		pracaDesejada=
		unidadeDesejada=
		processoDesejado=
		 */
		String idUnidadeDesejadaStr = request.getParameter("unidadeDesejada");
		int idUnidadeDesejada = 0;

		// Se existe unidade desejada
		if( idUnidadeDesejadaStr != null) {
			// se a unidade desejada não estiver 'em branco'
			if(! idUnidadeDesejadaStr.equals("")) {
				idUnidadeDesejada = Integer.parseInt(idUnidadeDesejadaStr);	
			}

		}

		String idProcessoDesejadoStr = request.getParameter("processoDesejado");
		int idProcessoDesejado = 0;

		// ! idProcessoDesejadoStr.equals("")
		//se existe processo desejado
		if( idProcessoDesejadoStr != null ) {
			// se o processo desejado não estiver 'em branco'
			if(! idProcessoDesejadoStr.equals("")) {
				idProcessoDesejado = Integer.parseInt(idProcessoDesejadoStr);	
			}

		}


		// buscar no bd as pessoas na praca desejada que tenham alguma solicitação para a praca atual

		pesDao = new PessoaDao();

		List <Pessoa> listPessoa = 
				pesDao.getAllByPracaIdWherePracaDesejadaIdRelevante(idPracaAtual, idPracaDesejada, idUnidadeDesejada, idProcessoDesejado);

		//System.out.println("SolicitantesDeUmaPracaController - teste lista" + listPessoa.get(0).getPraca().getNomePraca());




		// eliminar o proprio usuario logado da lista


		List<Pessoa> listSemLogado = AvaliadorRelacaoSimetrica.eliminarReflexividade(p, listPessoa);

		// novo: atribuir relevancia

		AvaliadorRelacaoSimetrica avaliador = new AvaliadorRelacaoSimetrica();

		avaliador.setListNodeRelevanciaSimetrica(listSemLogado, p);

		// atualizar a lista

		List<NodeRelevanciaSimetrica> listNodes = avaliador.getListNodeRelevanciaSimetrica();

		// set model


		model.addAttribute("tamanho", listNodes.size());
		model.addAttribute("pessoas", listNodes);

		return "solicitantes-de-uma-praca-relevancia";
	}

}
