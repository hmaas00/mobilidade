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

public class PessoaDao {


	SessionFactory fac;
	Session session;
	
	public PessoaDao() {
		initDao();
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
	
	public void updateProcessoMotivo(Pessoa p, CadeiaValorSubgrupo processo,
			String motivo) {
		
		try {
			initDao();
			session.beginTransaction();
			
			p.setCadeiaValorSubgrupo(processo);
			p.setMotivoPrincipal(motivo);
			
			System.out.println("pessoaDao update - pessoa: " + p);
			
			session.update(p);
			
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
	
	// retorna null se n�o encontrar
	public Pessoa findByLoginName(String loginName) {
		// TODO Auto-generated method stub
		
		//UserDetails userDetails =
			//	 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//System.out.println(userDetails);
		
		System.out.println("pessoa dao findByLoginName: " + loginName);
		
		
		
		try {
			initDao();
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
			System.out.println("\n\n\nSolicita��es de permuta: \n" + pessoaResult.getListSolicitacaoPermuta());
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

	//select s from SolicitacaoPermuta s inner join s.pessoa p where p.idPessoa =
	
	public List<Pessoa> getAllByPracaIdWherePracaDesejadaId(int idPracaAtual, int idPracaDesejada) {
		try {
			initDao();
			session.beginTransaction();
			
			//###########Localiza user na tabela users pelo loginName##############
			List <Pessoa> listPessoa = session.createQuery(
					"select p "
					+ "from SolicitacaoPermuta s "
					+ "inner join s.pessoa p "
					+ "inner join s.praca pra_s "
					+ "inner join p.praca pra_p "
					+ "where "
					+ "pra_p.idPraca = '"+ idPracaDesejada + "' "
					+ "and "
					+ "pra_s.idPraca = '"+ idPracaAtual + "' ").getResultList();
			
			
			System.out.println("\n\ngetAllByPracaIdWherePracaDesejadaId: pessoas de uma pra�a...  \n\n" +listPessoa);
			session.getTransaction().commit();
			return listPessoa;
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
