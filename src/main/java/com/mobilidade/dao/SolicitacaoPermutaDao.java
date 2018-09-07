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
	
	public void save( SolicitacaoPermuta permuta) {
		try {
			initDao();
			session.beginTransaction();
			
			session.save(permuta);
			
			//localiza pessoa associada ao user
			session.getTransaction().commit();
			
			System.out.println("SolicitacaoPermutaDao salvou: xxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n" + permuta);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			session.close();
			fac.close();
			
		}
		
	}
	
	public List <SolicitacaoPermuta> findByPessoaId(int id) {
		try {
			initDao();
			session.beginTransaction();
			
			//select e from Employee e inner join e.team
			List <SolicitacaoPermuta> solicitacoes =
					session.createQuery("select s from SolicitacaoPermuta s inner join s.pessoa p where p.idPessoa =" + id).getResultList();
			
			// Assegurar carregamento dos dados das tabelas referenciadas
			
			for(SolicitacaoPermuta s : solicitacoes) {
				s.getPessoa();
				s.getPraca();
				s.getUnidade();
				s.getCadeiaValorSubgrupo();
			}
			
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
			System.out.println();
			System.out.println("closing resources<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			System.out.println();
			session.close();
			fac.close();
			
		}
	}
	
	public void deleteAllByPessoa(Pessoa p) {
		try {
			initDao();
			session.beginTransaction();
			
			//delete todas solicitações de uma pessoa
			session.createQuery("delete from SolicitacaoPermuta s where s.pessoa.idPessoa =" + p.getIdPessoa())
					.executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Delete terminado<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			System.out.println();
			System.out.println("SolicitacaoPermutaDao closing resources<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			System.out.println();
			session.close();
			fac.close();
			
		}
	}

}
