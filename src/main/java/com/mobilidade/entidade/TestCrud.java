package com.mobilidade.entidade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mobilidade.auxiliar.DFS;
import com.mobilidade.auxiliar.NodeCircular;
import com.mobilidade.dao.PessoaDao;

public class TestCrud {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PessoaDao pesDao = new PessoaDao();
		DFS dfs = new DFS();
		dfs.setListaGeral();
		List listAdj = new ArrayList();
		
		NodeCircular node = new NodeCircular();
		NodeCircular nodeAux = new NodeCircular();
		
//		node.setP( pesDao.findByLoginName("harlanm"));
//		node.setP( pesDao.findByLoginName("alexandref"));
		
		node.setP( pesDao.findByLoginName("luizk"));
		
		node.fillAdjLists( dfs.getListaGeral());
		//listAdj = node.criaListaAdjacencias(dfs.getListaGeral().get(0), 
			//	dfs.getListaGeral().get(0).getListSolicitacaoPermuta().get(0), dfs.getListaGeral());
		
		 System.out.println();
		/*
		ArrayList <Integer> ar = new ArrayList();
		ar.add(2);
		ar.add(30);
		
		Integer i = ar.get(0);
		i++;
		System.out.println(i + " " +  ar.get(0));
		*/
		 dfs.startDFS(node.getP());
		 
		 
		for(int k = 0 ; k < dfs.getListaRelacoes().size(); k++) {
			System.out.println(dfs.getListaRelacoes().get(k));
		}
		System.out.println("fim");
		 
		 

	}
		

}
