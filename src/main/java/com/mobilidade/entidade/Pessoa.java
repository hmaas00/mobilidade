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
@Table(name = "pessoa")
public class Pessoa {
	
	public Pessoa(){
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_pessoa")
	private int idPessoa;
	@Column(name="nome_pessoa")
	private String nomePessoa;
	@Column(name="cargo")
	private String cargo;
	@Column(name="componente_fk")
	private int componenteFk;
	@Column(name="cod_cadeia_vlr_subgrupo_fk")
	private int codCadeiaVlrSubgrupoFk;
	//@Column(name="cod_praca_fk")
	//private int codPracaFk;
	@Column(name="motivo_principal")
	private String motivoPrincipal;
	@Column(name="estado_permuta")
	private String estadoPermuta;
	@Column(name="estado_mobilidade")
	private String estadoMobilidade;
	@Column(name="matricula")
	private int matricula;
	
	//Chave estrangeira - outras tabelas!!!
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="cod_praca_fk")
	private Praca praca;
	
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getComponenteFk() {
		return componenteFk;
	}
	public void setComponenteFk(int componenteFk) {
		this.componenteFk = componenteFk;
	}
	public int getCodCadeiaVlrSubgrupoFk() {
		return codCadeiaVlrSubgrupoFk;
	}
	public void setCodCadeiaVlrSubgrupoFk(int codCadeiaVlrSubgrupoFk) {
		this.codCadeiaVlrSubgrupoFk = codCadeiaVlrSubgrupoFk;
	}
	/*
	public int getCodPracaFk() {
		return codPracaFk;
	}
	public void setCodPracaFk(int codPracaFk) {
		this.codPracaFk = codPracaFk;
	}
	*/
	public String getMotivoPrincipal() {
		return motivoPrincipal;
	}
	public void setMotivoPrincipal(String motivoPrincipal) {
		this.motivoPrincipal = motivoPrincipal;
	}
	public String getEstadoPermuta() {
		return estadoPermuta;
	}
	public void setEstadoPermuta(String estadoPermuta) {
		this.estadoPermuta = estadoPermuta;
	}
	public String getEstadoMobilidade() {
		return estadoMobilidade;
	}
	public void setEstadoMobilidade(String estadoMobilidade) {
		this.estadoMobilidade = estadoMobilidade;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public Praca getPraca() {
		return praca;
	}
	public void setPraca(Praca praca) {
		this.praca = praca;
	}
	 public String toString() {
	        return "Id: " + this.idPessoa + "\n" +
	        		"Nome: " + this.nomePessoa + "\n"+
	                "Cargo: " + this.cargo + "\n"+
	                "Estado na Permuta: " + this.estadoPermuta+ "\n"+
	                "Estado na Mobilidade: " + this.estadoMobilidade + "\n"+
	                "Motivo principal: " + this.motivoPrincipal + "\n"+
	                "Matr�cula: " + this.matricula + "\n"+
	                "Fks:\n"+
	                //"Cod pra�a: " + this.codPracaFk + "\n"+
	                "Cod cadeia_vlr_subgrupo: " + this.codCadeiaVlrSubgrupoFk + "\n"+
	                "Cod componente: " + this.componenteFk + "\n"
	                ;
	                
	    }
}
