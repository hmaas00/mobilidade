package com.mobilidade.auxiliar;

import java.util.ArrayList;
import java.util.List;

import com.mobilidade.entidade.Pessoa;

// classe que guarda os motivos para buscar outros postos de trabalho
public class Motivo {
	
	public Motivo() {
		
	}

	private static List<String> listaMotivos;
	
	public  static List<String> getListaMotivos(){
		listaMotivos = new ArrayList<String>();
		listaMotivos.add("desejo mudar de pra�a");
		listaMotivos.add("desejo realizar novas atividades");
		listaMotivos.add("busca de flexibiliza��o de hor�rio");
		listaMotivos.add("compatibiliza��o perfil-atividades");
		listaMotivos.add("subaproveitamento");
		listaMotivos.add("falta de reconhecimento");
		listaMotivos.add("problemas de relacionamento interpessoal");
		listaMotivos.add("falta de perspectiva de desenvolvimento pessoal");
		listaMotivos.add("clima organizacional desfavor�vel");
		listaMotivos.add("insatisfa��o com estilo gerencial da chefia");
		listaMotivos.add("ociosidade");
		listaMotivos.sort(String.CASE_INSENSITIVE_ORDER);
		return listaMotivos;
	}

	public void setListaMotivos(List<String> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}
	
	
}
