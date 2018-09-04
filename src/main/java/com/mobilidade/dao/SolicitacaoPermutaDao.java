package com.mobilidade.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mobilidade.entidade.CadeiaValorSubgrupo;
import com.mobilidade.entidade.ComponenteAdministrativo;
import com.mobilidade.entidade.Pessoa;
import com.mobilidade.entidade.Praca;
import com.mobilidade.entidade.SolicitacaoPermuta;
import com.mobilidade.entidade.Unidade;
import com.mobilidade.entidade.User;

public class SolicitacaoPermutaDao {

	private SessionFactory fac;
	private Session session;
	
	public SolicitacaoPermutaDao() {
		
		fac = new Configuration()
				.configure()
				.addAnnotatedClass(Pessoa.class)
				.addAnnotatedClass(Praca.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(ComponenteAdministrativo.class)
				.addAnnotatedClass(Unidade.class)
				.addAnnotatedClass(CadeiaValorSubgrupo.class)
				.addAnnotatedClass(SolicitacaoPermuta.class)
				.buildSessionFactory();
		session = fac.getCurrentSession();
	}
	
	public List <SolicitacaoPermuta> findByPessoaId(int id) {
		try {
			session.beginTransaction();
			
			//select e from Employee e inner join e.team
			List <SolicitacaoPermuta> solicitacoes =
					session.createQuery("select s from SolicitacaoPermuta s inner join s.pessoa p where p.idPessoa =" + id).getResultList();
			//localiza pessoa associada ao user
			session.getTransaction().commit();
			
			System.out.println("SolicitacaoPermutaDao achou a lista: xxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n" + solicitacoes);
			return solicitacoes;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			session.close();
			fac.close();
			
		}
	}

}
