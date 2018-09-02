package com.mobilidade.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mobilidade.entidade.CadeiaValorSubgrupo;
import com.mobilidade.entidade.ComponenteAdministrativo;
import com.mobilidade.entidade.Pessoa;
import com.mobilidade.entidade.Praca;
import com.mobilidade.entidade.Unidade;
import com.mobilidade.entidade.User;

public class PessoaDao {


	public PessoaDao() {
		
	}
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void delete(Pessoa arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteAll(Iterable<? extends Pessoa> arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteById(Integer arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean existsById(Integer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public List <Pessoa> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public Pessoa findById(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// retorna null se não encontrar
	public Pessoa findByLoginName(String loginName) {
		// TODO Auto-generated method stub
		
		//UserDetails userDetails =
			//	 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//System.out.println(userDetails);
		
		System.out.println("pessoa dao findByLoginName: " + loginName);
		
		SessionFactory fac = new Configuration()
				.configure()
				.addAnnotatedClass(Pessoa.class)
				.addAnnotatedClass(Praca.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(ComponenteAdministrativo.class)
				.addAnnotatedClass(Unidade.class)
				.addAnnotatedClass(CadeiaValorSubgrupo.class)
				.buildSessionFactory();
		Session session = fac.getCurrentSession();
		
		try {
			session.beginTransaction();
			//Query query = session.createQuery("select username from users where username = " + principal.getName());
			//List list = ((org.hibernate.query.Query) query).list();
			//System.out.println(list);
			//String prince = principal.getName();
			//int tam = prince.length();
			
			//System.out.println("substr query: " + str.substring(0, tam-1)+" "+str.substring(tam-1 ,tam) +".");
			//String where = prince.substring(0, tam-1)+" "+prince.substring(tam-1 ,tam);
			//List <Pessoa> list= session.createQuery("from Pessoa p where p.nomePessoa like '"+ where +"%'").getResultList();
			//System.out.println("\n\ndeu certo!!!!!!!!!! " + list.get(0).getCargo());
			
			//###########Localiza user na tabela users pelo loginName##############
			User u = (User)session.createQuery("from User u where u.userName = '"+ loginName +"'").uniqueResult();
			//localiza pessoa associada ao user
			Pessoa pessoaResult = u.getPessoa();
			System.out.println("\n\nPessoa retornada: " + pessoaResult);
			session.getTransaction().commit();
			return pessoaResult;
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


	public <S extends Pessoa> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Pessoa> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
