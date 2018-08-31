package com.mobilidade.entidade;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestPessoaHibernate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory fac = new Configuration()
				.configure()
				.addAnnotatedClass(Pessoa.class)
				.addAnnotatedClass(Praca.class)
				.buildSessionFactory();
		Session session = fac.getCurrentSession();
		
		try {
			System.out.println("\nIniciando sessão...");
			session.beginTransaction();
			Pessoa p = session.get(Pessoa.class, 1);
			Praca praca = session.get(Praca.class, 1);
			System.out.println("\npessoa: " + p);
			System.out.println("\npraca: " + praca);
			System.out.println("\nlista pessoas: "+ praca.getListPessoa());
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			fac.close();
			
		}

	}

}
