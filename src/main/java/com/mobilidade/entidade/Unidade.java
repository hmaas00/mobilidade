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
@Table(name="unidade")
public class Unidade {

	public Unidade() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_unidade")
	private int idUnidade = 0 ;

	@Column(name="nome_unidade")
	private String nomeUnidade;

	@Column(name="limite_liberar_analista")
	private int limiteLiberarAnalista;

	@Column(name="limite_liberar_tecnico")
	private int limiteLiberarTecnico;

	@Column(name="limite_receber_analista")
	private int limiteReceberAnalista;

	@Column(name="limite_receber_tecnico")
	private int limiteReceberTecnico;

	@Column(name="regra_minimo_um_analista")
	private int regraMinimoUmAnalista;

	@Column(name="regra_minimo_um_tecnico")
	private int regraMinimoUmTecnico;

	//componentes administrativos
	//relação com pessoa
	@OneToMany(mappedBy="unidade",
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH})
	private List<ComponenteAdministrativo> listComponentes;

	//relação com solicitacao de permuta	
	@OneToMany(mappedBy="unidade",
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH})
	private List<SolicitacaoPermuta> listSolicitacaoPermuta;
	
	//gets sets
	

	public List<SolicitacaoPermuta> getListSolicitacaoPermuta() {
		return listSolicitacaoPermuta;
	}

	public void setListSolicitacaoPermuta(List<SolicitacaoPermuta> listSolicitacaoPermuta) {
		this.listSolicitacaoPermuta = listSolicitacaoPermuta;
	}
	
	public int getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(int idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		if(nomeUnidade == null) {
			return null;
		}
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
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

	public int getLimiteReceberAnalista() {
		return limiteReceberAnalista;
	}

	public void setLimiteReceberAnalista(int limiteReceberAnalista) {
		this.limiteReceberAnalista = limiteReceberAnalista;
	}

	public int getLimiteReceberTecnico() {
		return limiteReceberTecnico;
	}

	public void setLimiteReceberTecnico(int limiteReceberTecnico) {
		this.limiteReceberTecnico = limiteReceberTecnico;
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

	public List<ComponenteAdministrativo> getListComponentes() {
		return listComponentes;
	}

	public void setListComponentes(List<ComponenteAdministrativo> listComponentes) {
		this.listComponentes = listComponentes;
	}
	
	//to string
	public String toString() {
		return ("toString nome unidade: " + this.getNomeUnidade());
	}

}
