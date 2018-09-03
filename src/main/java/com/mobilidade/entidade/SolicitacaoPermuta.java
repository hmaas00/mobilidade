package com.mobilidade.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="solicitacao_permuta")
public class SolicitacaoPermuta {
	
	public SolicitacaoPermuta() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_solicitacao_permuta")
	private int idSolicitacaoPermuta;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="id_pessoa_fk")
	private Pessoa pessoa;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="id_praca_fk")
	private Praca praca;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="id_unidade_fk")
	private Unidade unidade;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="id_cadeia_vlr_subgrupo_fk")
	private CadeiaValorSubgrupo cadeiaValorSubgrupo;

	public int getIdSolicitacaoPermuta() {
		return idSolicitacaoPermuta;
	}

	public void setIdSolicitacaoPermuta(int idSolicitacaoPermuta) {
		this.idSolicitacaoPermuta = idSolicitacaoPermuta;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Praca getPraca() {
		return praca;
	}

	public void setPraca(Praca praca) {
		this.praca = praca;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public CadeiaValorSubgrupo getCadeiaValorSubgrupo() {
		return cadeiaValorSubgrupo;
	}

	public void setCadeiaValorSubgrupo(CadeiaValorSubgrupo cadeiaValorSubgrupo) {
		this.cadeiaValorSubgrupo = cadeiaValorSubgrupo;
	}
	
	//to string
	public String toString() {
		return ("unidade da solicitacao: " + this.unidade.getNomeUnidade() +" \n"
		 + "praca da solicitacao: " + this.praca.getNomePraca() +" \n"
		 + "cadeia valor da solicitacao: " + this.cadeiaValorSubgrupo.getDescricaoSubgrupo() +" \n"
		 + "pessoa da solicitacao: " + this.pessoa.getNomePessoa() +" \n"
				);
	}

}
