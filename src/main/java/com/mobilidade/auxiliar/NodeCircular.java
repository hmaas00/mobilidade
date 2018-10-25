package com.mobilidade.auxiliar;

import java.util.ArrayList;
import java.util.List;

import com.mobilidade.entidade.Pessoa;
import com.mobilidade.entidade.SolicitacaoPermuta;

public class NodeCircular {
	
	
	public NodeCircular() {
		relacao = new ArrayList<NodeCircular>(1000);
	}
	
	
	// pessoa a que o no se refere
	private Pessoa p = null;
	// profundidade do no
	private int profundidade = 1000000;
	// quantidade de passos até chegar no solicitante originario
	private int chegaEm = 1000000;
	// mantem a memoria da relação que esta sendo construída na busca atual
	private List relacao = null;
	// lista de listas de adjacencias, fruto de cada solicitacao dessa pessoa
	// deve ser preenchida somente se existir a solicitação e esta possuir unidade desejada
	// ou processo desejado
	private ArrayList <ArrayList> listOfListAdj = null;

	
	// GETS SETS
	
	
	
	
	public Pessoa getP() {
		return p;
	}

	public void setP(Pessoa p) {
		this.p = p;
	}


	public ArrayList getListOfListAdj() {
		return listOfListAdj;
	}

	public int getProfundidade() {
		return profundidade;
	}



	public void setProfundidade(int profundidade) {
		this.profundidade = profundidade;
	}



	public int getChegaEm() {
		return chegaEm;
	}



	public void setChegaEm(int chegaEm) {
		this.chegaEm = chegaEm;
	}
	
	
	
	public List getRelacao() {
		return relacao;
	}



	public void setRelacao(List relacao) {
		this.relacao = relacao;
	}



	// para cada solicitação dessa pessoa, com processo ou unidade desejada, uma lista de adjacencias sera criada
	// a partir de uma lista fornecida com todos as pessoas que tem solicitacoes
	public void fillAdjLists( List<NodeCircular> listaGeral) {
		
		listOfListAdj = new ArrayList<ArrayList>();
		// para cada solicitacao...
		for(SolicitacaoPermuta sol : p.getListSolicitacaoPermuta()) {
			// avaliar premissa: ha processo desejado ou unidade desejada na solicitação
			if(sol.getCadeiaValorSubgrupo() != null || sol.getUnidade() != null) {
				//TODO
				listOfListAdj.add(criaListaAdjacencias( p, sol, listaGeral ));
			}
		}
		
	}



	// cria uma lista de adjacencias baseado em uma solicitação e a lista de todas as pessoas com solicitação
	// retirando a propria pessoa -reflexividade-
	//**************************************************************************
	// premissas: a solicitação possui ou processo desejado ou unidade desejada ou ambos
	//**************************************************************************
	public ArrayList <NodeCircular> criaListaAdjacencias( Pessoa pes, SolicitacaoPermuta sol, List<NodeCircular> listaGeral ) {
		
		// lista de adjacencias a retornar 
		ArrayList <NodeCircular> auxList = new ArrayList<NodeCircular>();
		// percorrer lista geral em busca de possiveis relacoes
		for(NodeCircular p : listaGeral) {
			// se não for a propria pessoa; e se estiver na praça desejada; e se possuir processo desejado; e se possuir undade desejada...
			if(pes.getIdPessoa() != p.getP().getIdPessoa()) {
				
				// se a pessoa em questao esta na praca desejada...
				if(p.getP().getPraca().getIdPraca() == sol.getPraca().getIdPraca()) {
				
					// pela premissa, se nao possuir processo desejado, ha unidade desejada
					if( sol.getCadeiaValorSubgrupo() == null ) {
						if(p.getP().getComponenteAdministrativo().getUnidade().getIdUnidade() == sol.getUnidade().getIdUnidade()) {
							// p faz parte da lista de adjacencias por essa solicitação!
							auxList.add(p);
						}	
					}
					
					// pela premissa, se nao possuir unidade desejada, ha processo desejado
					else if(sol.getUnidade() == null) {
					
						if(p.getP().getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo() == sol.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo()) {
							auxList.add(p);
						}
					}
					// se nenhum for nulo, é porque possui ambos
					//TODO
					else {
						if(p.getP().getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo() == sol.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo()
								&&
								p.getP().getComponenteAdministrativo().getUnidade().getIdUnidade() == sol.getUnidade().getIdUnidade()) {
							auxList.add(p);
						}
					}
				}	
			}
		}
		
		return auxList;
	}
	
	public String toString() {
		return String.format( this.getP().getNomePessoa() + " prof: " + this.getProfundidade()+" chega em: "+ this.getChegaEm());
	}

	

}
