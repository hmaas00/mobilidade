package com.mobilidade.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="componente_administrativo")
public class ComponenteAdministrativo {

	public ComponenteAdministrativo() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_componente_administrativo")
	private int idComponenteAdministrativo;


	//relação com pessoa
	@OneToMany(mappedBy="componenteAdministrativo",
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH})
	private List<Pessoa> listPessoa;


	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="id_unidade_fk")
	private Unidade unidade; //TODO fk

	@Column(name="nome_componente")
	private String nomeComponente;

	@Column(name="limite_liberar_analista")
	private int limiteLiberarAnalista;

	@Column(name="limite_liberar_tecnico")
	private int limiteLiberarTecnico;

	@Column(name="regra_minimo_um_analista")
	private int regraMinimoUmAnalista;

	@Column(name="regra_minimo_um_tecnico")
	private int regraMinimoUmTecnico;

	//gets sets
	public int getIdComponenteAdministrativo() {
		return idComponenteAdministrativo;
	}

	public void setIdComponenteAdministrativo(int idComponenteAdministrativo) {
		this.idComponenteAdministrativo = idComponenteAdministrativo;
	}

	public List<Pessoa> getListPessoa() {
		return listPessoa;
	}

	public void setListPessoa(List<Pessoa> listPessoa) {
		this.listPessoa = listPessoa;
	}


	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getNomeComponente() {
		return nomeComponente;
	}

	public void setNomeComponente(String nomeComponente) {
		this.nomeComponente = nomeComponente;
	}

	public int getLimiteLiberarAnalista() {
		return limiteLiberarAnalista;
	}

	public void setLimiteLiberarAnalista(int limiteLiberarAnalista) {
		this.limiteLiberarAnalista = limiteLiberarAnalista;
	}

	public int getLimiteLiberarTecnico() {
		return limiteLiberarTecnico;
	}

	public void setLimiteLiberarTecnico(int limiteLiberarTecnico) {
		this.limiteLiberarTecnico = limiteLiberarTecnico;
	}

	public int getRegraMinimoUmAnalista() {
		return regraMinimoUmAnalista;
	}

	public void setRegraMinimoUmAnalista(int regraMinimoUmAnalista) {
		this.regraMinimoUmAnalista = regraMinimoUmAnalista;
	}

	public int getRegraMinimoUmTecnico() {
		return regraMinimoUmTecnico;
	}

	public void setRegraMinimoUmTecnico(int regraMinimoUmTecnico) {
		this.regraMinimoUmTecnico = regraMinimoUmTecnico;
	}
	
	//to string
	public String toString() {
		return ("Comp tostring: " + this.getNomeComponente()  );
	}


}
