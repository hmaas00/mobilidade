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

		// lista de cadeias de valor

		CadeiaValorSubgrupoDao cadeiaDao = new CadeiaValorSubgrupoDao();

		List <CadeiaValorSubgrupo> listaCadValor = cadeiaDao.findAll();

		// lista de motivos

		List<String> motivosLista = Motivo.getListaMotivos();

		// cadeia de valor já escolhida
		String cadeiaJaEscolhida = p.getCadeiaValorSubgrupo().getDescricaoSubgrupo();

		//lista pracas
		PracaDao pracaDao = new PracaDao();
		List<Praca> listPraca = pracaDao.findAll();
		//lista unidades
		UnidadeDao unidadeDao = new UnidadeDao();
		List<Unidade> listUnidade = unidadeDao.findAll();
		
		//lista solicitacoes por id pessoa
		SolicitacaoPermutaDao solicitacaoPermutaDao = new SolicitacaoPermutaDao();
		List<SolicitacaoPermuta>  listSolicitacoes = solicitacaoPermutaDao.findByPessoaId(p.getIdPessoa());
		//testes

		System.out.println("controller solicitar-permuta: nome pessoa - " + p.getNomePessoa());
		System.out.println("controller solicitar-permuta: motivo pessoa - " + p.getMotivoPrincipal());
		System.out.println("controller solicitar-permuta: praca pessoa - " + p.getPraca().getNomePraca());
		System.out.println("controller solicitar-permuta: componente pessoa - " + p.getComponenteAdministrativo().getNomeComponente());
		System.out.println("controller solicitar-permuta: unidade - " + p.getComponenteAdministrativo().getUnidade());
		System.out.println("controller solicitar-permuta: cadeia valor - " + p.getCadeiaValorSubgrupo().getDescricaoSubgrupo());
		System.out.println("\n\n\nCadeias de valor: \n" + listaCadValor);
		//System.out.println("\n\n\nSolicitações de permuta: \n" + p.getListSolicitacaoPermuta());
		//add model - pessoa
		model.addAttribute("pessoa", p);

		//add model - cadeias valor
		model.addAttribute("cadeiasValor", listaCadValor);

		// add model - lista de motivos

		model.addAttribute("motivos", motivosLista);

		// add model - cadeia de valor já escolhida
		model.addAttribute("cadeiaValorPreviamenteEscolhida",cadeiaJaEscolhida);		

		// add model - motivo já escolhido

		model.addAttribute("motivoPreviamenteEscolhido", p.getMotivoPrincipal());

		// add model - pracas
		model.addAttribute("pracas", listPraca);

		// add model - unidades
				model.addAttribute("unidades", listUnidade);
		
		return "solicitacao-permuta";

	}

}









