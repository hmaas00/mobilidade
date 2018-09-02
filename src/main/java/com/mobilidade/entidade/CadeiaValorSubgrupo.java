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
@Table(name="cadeia_valor_subgrupo")
public class CadeiaValorSubgrupo {


	public CadeiaValorSubgrupo() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_cadeia_valor_subgrupo")
	private int idCadeiaValorSubgrupo;

	@Column(name="descricao_subgrupo")
	private String descricaoSubgrupo;

	@Column(name="subgrupo_cadeia_valor_fk")
	private int cadeiaValorFk;

	//relação com pessoa
	@OneToMany(mappedBy="cadeiaValorSubgrupo",
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH})
	private List<Pessoa> listPessoa;

	public int getIdCadeiaValorSubgrupo() {
		return idCadeiaValorSubgrupo;
	}

	public void setIdCadeiaValorSubgrupo(int idCadeiaValorSubgrupo) {
		this.idCadeiaValorSubgrupo = idCadeiaValorSubgrupo;
	}

	public String getDescricaoSubgrupo() {
		
		return descricaoSubgrupo;
	}

	public void setDescricaoSubgrupo(String descricaoSubgrupo) {
		this.descricaoSubgrupo = descricaoSubgrupo;
	}

	public List<Pessoa> getListPessoa() {
		return listPessoa;
	}

	public void setListPessoa(List<Pessoa> listPessoa) {
		this.listPessoa = listPessoa;
	}
	
	public String toString() {
		return ("toString cadeia valor subgrupo: "+this.descricaoSubgrupo);
	}



}
