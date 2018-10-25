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


	// COMPARABLE - ordem inversa de relevancia e crescente alfab�tica
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
		// desempatar pela compara��o dos nomes das pessoas
		return this.getP().getNomePessoa().compareTo(other.getP().getNomePessoa());
	}

	// GETs SETs

	public int getRelevancia() {
		return relevancia;
	}
	
	
	// setar relevancia desse n� com base nas suas rela��es com o
	// usu�rio logado que est� buscando rela��es sim�tricas com expectativas rec�procas
	// entrada: 
	// o usu�rio logado

	public void setRelevancia(Pessoa principal) {

		// ver se as expectativas quanto ao processo de trabalho s�o rec�procas
		// do ponto de vista das pessoas apresentadas na lista de possibilidades

		// ver lista de solicita��es desta pessoa e ver se ela tem reciprocidade com o 
		// usu�rio logado


		for (SolicitacaoPermuta sol : p.getListSolicitacaoPermuta() ) {

			// solicita��o possui processo e unidade desejados
			if(sol.getCadeiaValorSubgrupo() != null && sol.getUnidade() != null) {
				// se a solicita��o deseja o processo atual do logado
				if(sol.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo() ==
						principal.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo()) {

					relevancia+= 1;

				}
				if(sol.getUnidade().getIdUnidade() ==
						principal.getComponenteAdministrativo().getUnidade().getIdUnidade()) {

					relevancia+= 1;

				}


				// se tem s� processo desejado
			} else if(sol.getCadeiaValorSubgrupo() != null) {

				if(sol.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo() ==
						principal.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo()) {

					relevancia+= 1;

				}
				// tem s� unidade desejada
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
