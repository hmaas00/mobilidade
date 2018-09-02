package com.mobilidade.entidade;

import java.security.Principal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

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
			List<Pessoa> listPessoa = session.createQuery("from Pessoa p where p.nomePessoa like 'har%'").getResultList();
			System.out.println("\nQuery criada...\n");
			System.out.println(listPessoa);
			
			//string query
			System.out.println("\nxxxxxxxxxxx\n");
			String str = "harlanm";
			int tam = str.length();
			System.out.println("len: " + tam);
			//System.out.println("pos space: " + str.indexOf(" "));
			//System.out.println("substr: " + str.substring(0, str.indexOf(" "))+".");
			System.out.println("substr query: " + str.substring(0, tam-1)+" "+str.substring(tam-1 ,tam) +".");
			//System.out.println("substr query to use: " + str.substring(0, str.indexOf(" "))+" "+str.substring(str.indexOf(" ")+1 ,str.indexOf(" ")+2));
			//de novo!
			
			//commit
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
