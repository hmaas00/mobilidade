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

public class UnidadeDao {
	
	private SessionFactory fac;
	private Session session;
	
	public UnidadeDao() {
		
		
		
	}
	
	public void initDao() {
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
	
	
	public List <Unidade> findAll() {
		// TODO Auto-generated method stub
		initDao();
		
		try {
			session.beginTransaction();
			
			
			List <Unidade> unidades = session.createQuery("from Unidade u order by u.nomeUnidade").getResultList();
			
			session.getTransaction().commit();
			return unidades;
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
	
	public Unidade findById( int id ) {
		// TODO Auto-generated method stub
		initDao();
		
		try {
			session.beginTransaction();
			
			
			Unidade unidade = (Unidade)session.createQuery("from Unidade u where u.idUnidade =" + id).uniqueResult();
			
			session.getTransaction().commit();
			return unidade;
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
