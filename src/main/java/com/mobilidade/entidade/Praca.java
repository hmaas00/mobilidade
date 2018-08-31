package com.mobilidade.entidade;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="praca")
public class Praca {
	
	public Praca() {
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_praca")
	private int idPraca;
	
	@Column(name="nome_praca")
	private String nomePraca;
	
	//relação com pessoa
	@OneToMany(mappedBy="praca",
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH})
	private List<Pessoa> listPessoa;
	
	public int getIdPraca() {
		return idPraca;
	}
	public void setIdPraca(int idPraca) {
		this.idPraca = idPraca;
	}
	public String getNomePraca() {
		return nomePraca;
	}
	public void setNomePraca(String nomePraca) {
		this.nomePraca = nomePraca;
	}
	//relação!!!
	public List<Pessoa> getListPessoa() {
		return listPessoa;
	}
	public void setListPessoa(List<Pessoa> listPessoa) {
		this.listPessoa = listPessoa;
	}
	public String toString() {
		return "Praça: " + this.nomePraca + " código: " + this.idPraca;
	}

}
