package com.mobilidade.auxiliar;

import com.mobilidade.entidade.Pessoa;
import com.mobilidade.entidade.SolicitacaoPermuta;

public class NodeRelevanciaSimetrica implements Comparable<NodeRelevanciaSimetrica>{

	public NodeRelevanciaSimetrica() {

	}
	
	public NodeRelevanciaSimetrica( Pessoa pessoa , Pessoa principal) {
		
		p = pessoa;
		setRelevancia(principal);
		System.out.println("NodeRelevanciaSimetrica - relevancia" + relevancia);
		
	}

	private Pessoa p = null;
	private int relevancia = 0;


	// COMPARABLE - ordem inversa de relevancia e crescente alfabética
	public int compareTo( NodeRelevanciaSimetrica other) {
	    
		// se esse objeto for menor que o outro - considerando a relevancia
		if(this.getRelevancia() < other.getRelevancia()) {
			return 1;
		}
		// se esse objeto for maior que o outro - considerando a relevancia
		if(this.getRelevancia() > other.getRelevancia()) {
			return -1;
		}
		// se esses objetos forem iguais - considerando a relevancia
		// desempatar pela comparação dos nomes das pessoas
		return this.getP().getNomePessoa().compareTo(other.getP().getNomePessoa());
	}

	// GETs SETs

	public int getRelevancia() {
		return relevancia;
	}
	
	
	// setar relevancia desse nó com base nas suas relações com o
	// usuário logado que está buscando relações simétricas com expectativas recíprocas
	// entrada: 
	// o usuário logado

	public void setRelevancia(Pessoa principal) {

		// ver se as expectativas quanto ao processo de trabalho são recíprocas
		// do ponto de vista das pessoas apresentadas na lista de possibilidades

		// ver lista de solicitações desta pessoa e ver se ela tem reciprocidade com o 
		// usuário logado


		for (SolicitacaoPermuta sol : p.getListSolicitacaoPermuta() ) {

			// solicitação possui processo e unidade desejados
			if(sol.getCadeiaValorSubgrupo() != null && sol.getUnidade() != null) {
				// se a solicitação deseja o processo atual do logado
				if(sol.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo() ==
						principal.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo()) {

					relevancia+= 1;

				}
				if(sol.getUnidade().getIdUnidade() ==
						principal.getComponenteAdministrativo().getUnidade().getIdUnidade()) {

					relevancia+= 1;

				}


				// se tem só processo desejado
			} else if(sol.getCadeiaValorSubgrupo() != null) {

				if(sol.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo() ==
						principal.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo()) {

					relevancia+= 1;

				}
				// tem só unidade desejada
			} else if(sol.getUnidade() != null) {

				if(sol.getUnidade().getIdUnidade() ==
						principal.getComponenteAdministrativo().getUnidade().getIdUnidade()) {

					relevancia+= 1;

				}
			}

		}

	}
	public Pessoa getP() {
		return p;
	}
	public void setP(Pessoa p) {
		this.p = p;
	}




}
