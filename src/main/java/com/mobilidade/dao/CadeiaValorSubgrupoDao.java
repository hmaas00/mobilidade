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

public class CadeiaValorSubgrupoDao {

	
	SessionFactory fac;
	Session session;
	
	public CadeiaValorSubgrupoDao() {
		

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
	

	public List <CadeiaValorSubgrupo> findAll() {
		// TODO Auto-generated method stub

		System.out.println("CadeiaValorSubgrupo dao");

		try {
			initDao();
			session.beginTransaction();

			List <CadeiaValorSubgrupo> list = session.createQuery("from CadeiaValorSubgrupo cad").getResultList();

			System.out.println("\n\nCadeias de valor - subgrupos:\n" + list);
			session.getTransaction().commit();
			return list;
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
	
	public CadeiaValorSubgrupo findById( int id) {
		// TODO Auto-generated method stub

		System.out.println("CadeiaValorSubgrupo dao");

		try {
			
			initDao();
			
			session.beginTransaction();
			
			String query = "from CadeiaValorSubgrupo cad where cad.idCadeiaValorSubgrupo= " + id;

			CadeiaValorSubgrupo cadeiaValorSubgrupo = (CadeiaValorSubgrupo)session
					.createQuery( query ).uniqueResult();

			System.out.println("\n\nCadeias de valor - uniqueResult:\n" + cadeiaValorSubgrupo.getDescricaoSubgrupo());
			session.getTransaction().commit();
			return cadeiaValorSubgrupo;
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
