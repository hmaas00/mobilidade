package com.mobilidade.auxiliar;

import java.util.ArrayList;
import java.util.List;

import com.mobilidade.entidade.Pessoa;

public class AvaliadorRelacaoSimetrica {
	
	// funcao elimina propria pessoa interessada da lista
	// impossibilita relacoes reflexivas
	// entradas: 
	// 1- o identificador do usuario logado
	// 2- a lista de pessoas retornadas pelo dao
	// retorno:
	// lista de entrada sem a pessoa logada
	
	List <NodeRelevanciaSimetrica> listNodeRelevanciaSimetrica = null;
	
	public List<NodeRelevanciaSimetrica> getListNodeRelevanciaSimetrica() {
		return listNodeRelevanciaSimetrica;
	}

	public void setListNodeRelevanciaSimetrica(List<Pessoa> listaEncontrados, Pessoa principal) {
		
		listNodeRelevanciaSimetrica = new ArrayList();
		
		for( Pessoa encontrado : listaEncontrados) {
			
			NodeRelevanciaSimetrica node = new NodeRelevanciaSimetrica(encontrado, principal);
			listNodeRelevanciaSimetrica.add(node);
		}
	}

	public static List <Pessoa> eliminarReflexividade(Pessoa principal, List<Pessoa> pessoas) {
		
		List <Pessoa> tempPessoas = pessoas;
		
		for(int i = 0 ; i < pessoas.size() ; i++) {
			
			if(  tempPessoas.get(i).getIdPessoa() == principal.getIdPessoa()  ) {
				// 
				tempPessoas.remove(i);
			}
		}
		
		return tempPessoas;
		
	}

}
