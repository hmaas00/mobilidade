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

public class PracaDao {
	
	
	private SessionFactory fac;
	private Session session;
	
	public PracaDao() {
		
		
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
	
	public List <Praca> findAll() {
		// TODO Auto-generated method stub
		
		initDao();
		
		try {
			session.beginTransaction();
			
			
			List <Praca>pracas = session.createQuery("from Praca p").getResultList();
			//localiza pessoa associada ao user
			session.getTransaction().commit();
			return pracas;
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
	
	public Praca findById( int id ) {
		// TODO Auto-generated method stub
		
		
		initDao();
		
		try {
			session.beginTransaction();
			
			
			Praca praca = (Praca) session.createQuery("from Praca p where p.idPraca = " + id).uniqueResult();
			//localiza pessoa associada ao user
			session.getTransaction().commit();
			return praca;
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
