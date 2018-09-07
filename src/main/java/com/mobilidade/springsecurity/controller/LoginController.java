package com.mobilidade.springsecurity.controller;

import java.security.Principal;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

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
}