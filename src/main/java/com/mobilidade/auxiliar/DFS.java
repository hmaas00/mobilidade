package com.mobilidade.auxiliar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.mobilidade.dao.PessoaDao;
import com.mobilidade.entidade.Pessoa;
import com.mobilidade.entidade.SolicitacaoPermuta;

public class DFS {
	
	// PROFUNDIADE MAXIMA DA DFS
	private final int MAX_PROFUND = 5;
	
	private final int TOP_NUMBER = 100000;
	
	// guarda referencia para o ator solicitante
	NodeCircular nodeAtor = null;
	
	// controla a profundidade da busca
	//private int timer = 0;
	
	// guarda todas as relações possíveis
	private List listaRelacoes = null;
	
	// lista de todas as pessoas
	private List <NodeCircular> listaGeral = null;
	// GETS E SETS
	public List getListaRelacoes() {
		return listaRelacoes;
	}

	public void setListaRelacoes(List listaRelacoes) {
		this.listaRelacoes = listaRelacoes;
	}

	public List<NodeCircular> getListaGeral() {
		return listaGeral;
	}

	// monta a lista geral de nos com todas as pessoas que possuem solicitações
	public void setListaGeral() {
		
		listaGeral = new ArrayList<NodeCircular>();
		PessoaDao pesDao = new PessoaDao();
		List <Pessoa> auxList = pesDao.findAllComSolicitacao(); // pessoas com, ao menos, uma solicitação!
		for(Pessoa a : auxList) {
			NodeCircular node = new NodeCircular();
			node.setP(a);
			node.setChegaEm(this.TOP_NUMBER);
			node.setProfundidade(this.TOP_NUMBER);
			listaGeral.add(node);
			
		}
		
//		//teste
//		System.out.println();
//		
//		NodeCircular nodeAux = listaGeral.get(0);
//		nodeAux.setProfundidade(69);
//		System.out.println("nodeAux prof: " + nodeAux.getProfundidade() + " listaGeral prof: "+ listaGeral.get(0).getProfundidade());
//		System.out.println();
		 
	}
	
	public NodeCircular getNodeById(int id) {
		for(NodeCircular n : listaGeral) {
			if(n.getP().getIdPessoa() == id) {
				return n;
			}
		}
		return null;
	}
	
	public void startDFS(Pessoa ator) {
		
		
		listaRelacoes = new ArrayList(); // inicializa a lista de relações
		// monta os nos do grafo
		setListaGeral();
		// busca na lista geral o no do ator pelo seu ID
		nodeAtor = getNodeById(ator.getIdPessoa());
		// seta profundidade inicial '0'
		int t = 0;
		nodeAtor.setProfundidade(t);
		// seta relacao que esta sendo construida ao longo da busca
		Stack stack = new Stack<NodeCircular>();
		//List r = new ArrayList(10);
		
		nodeAtor.setRelacao(null);
		
		// preenche listas de adjacencia com base na lista geral
		nodeAtor.fillAdjLists(listaGeral);
		
		// para cada lista de adjacencia do no...
		
		for( int i = 0 ; i < nodeAtor.getListOfListAdj().size(); i++) {
			
			ArrayList  auxList =  (ArrayList)nodeAtor.getListOfListAdj().get(i);
			// para cada no na lista de adjacencia...
			for(int j = 0; j < auxList.size() ; j++) {
				
				NodeCircular n = (NodeCircular)auxList.get(j);
//				System.out.println(n.getP().getNomePessoa());
				
				// condições para visitar o node
				
				// a profundidade do no em analise deve ser maior que o timer,
				// pois, se for menor e nunca tiver chegado no ator, com uma profundidade maior
				// isso tambem nao vai acontecer;
				// a nao ser que esse no chegue no ator, caso em que temos que avaliar
				// se e possivel chegar no ator dentro do limite de profundiade MAX_PROFUND
				// avaliando a profundidade atual, representada pelo timer, e os passos necessarios
				// para chegar no ator:
				//
//				r.clear();
//				r.add(nodeAtor);
				stack.push(nodeAtor);
				visitDFS(n, t, stack);
				stack.pop();
			}
		}
		for(int k = 0 ; k < this.getListaRelacoes().size(); k++) {
			System.out.println(this.getListaRelacoes().get(k));
		}
	}
	
	public void visitDFS( NodeCircular n, int t, Stack <NodeCircular> antecessores) {
		
		t++; // incrementa o timer que controla a profundidade atual
		// verificar se vale a pena avaliar este no
		// sua profundidade deve ser maior que o timer, se ele nunca tiver participado de uma relacao de sucesso
		// caso já tenha tido sucesso: a soma de sua profundidade com os passos necessários para chegar ao final
		// deve ser menor ou igual a profundidade maxima avaliada
		if( (n.getProfundidade() > t || (t + n.getChegaEm() <= this.MAX_PROFUND ) ) && t < this.MAX_PROFUND) {
			n.setProfundidade(t);
//			Collections.copy(n.getRelacao(), antecessores);
//			ArrayList auxRel = new ArrayList<NodeCircular>(100);
//			Collections.copy(auxRel, antecessores);
//			n.setRelacao(auxRel);
			//n.setRelacao(antecessores); // atualiza relacao de antecessores deste no
//			antecessores.add(n); // adiciona este no na relacao, pois ele e antecessor do proximo!
			n.fillAdjLists(listaGeral); // cria a(s) lista(s) de adjacencias
			for( int i = 0 ; i < n.getListOfListAdj().size(); i++) {
				ArrayList  auxList =  (ArrayList)n.getListOfListAdj().get(i);
				// para cada no na lista de adjacencia...
				for(int j = 0; j < auxList.size() ; j++) {
					NodeCircular nodeAux = (NodeCircular)auxList.get(j);
					// avaliar se esse no e o ator - sucesso
					if(nodeAux.getP().getIdPessoa() == nodeAtor.getP().getIdPessoa()) {
						System.out.println("sucesso");
						antecessores.push(n);
						ArrayList  listRelacao = new ArrayList();
						for(NodeCircular nodeSucesso : antecessores) {
							listRelacao.add(nodeSucesso);
						}
						listaRelacoes.add( listRelacao);
						antecessores.pop();
					}
					else {
						antecessores.push(n);
						visitDFS(nodeAux, t, antecessores);
						antecessores.pop();
					}
				}
			}
		}
	}
}
