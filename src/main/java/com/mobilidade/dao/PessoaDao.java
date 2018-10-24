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

	// TODO TESTE
	// lista de todas as pessoas que possuem, ao menos, uma solicitação
	public List <Pessoa> findAllComSolicitacao() {
		try {
			initDao();
			session.beginTransaction();
			
			//###########Localiza user na tabela users pelo loginName##############
			List <Pessoa> listPessoa = session.createQuery(
					"select distinct p "
					+ "from Pessoa p "
					+ "inner join p.componenteAdministrativo c "
					+ "inner join c.unidade u "
					+ "inner join p.listSolicitacaoPermuta s order by p.idPessoa").getResultList();
			// força montagem das solicitações
			for(Pessoa pes : listPessoa) {
				for(SolicitacaoPermuta s : pes.getListSolicitacaoPermuta()) {
					s.getPessoa();
				}
				pes.getListSolicitacaoPermuta();
			}
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


	// TODO TESTE
	// lista de todas as pessoas que possuem, ao menos, uma solicitação
	public List <Pessoa> findAll() {
		try {
			initDao();
			session.beginTransaction();
			
			//###########Localiza user na tabela users pelo loginName##############
			List <Pessoa> listPessoa = session.createQuery(
					"select distinct p "
					+ "from Pessoa p "
					+ "inner join p.componenteAdministrativo c "
					+ "inner join c.unidade u "
					+ "inner join p.listSolicitacaoPermuta s order by p.idPessoa").getResultList();
			// força montagem das solicitações
			for(Pessoa pes : listPessoa) {
				for(SolicitacaoPermuta s : pes.getListSolicitacaoPermuta()) {
					s.getPessoa();
				}
				pes.getListSolicitacaoPermuta();
			}
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

	public Pessoa findById(int id) {
		// TODO Auto-generated method stub
		try {
			initDao();
			session.beginTransaction();
			
			
			Pessoa p = (Pessoa)session.createQuery("from Pessoa p where p.idPessoa = '"+ id +"'").uniqueResult();
			
			// força recuperação das solicitações
			for(SolicitacaoPermuta permuta : p.getListSolicitacaoPermuta() ) {
				permuta.getPraca().getNomePraca();
				if(permuta.getUnidade() != null) {
					permuta.getUnidade().getNomeUnidade();
				}
				if(permuta.getCadeiaValorSubgrupo() != null) {
					permuta.getCadeiaValorSubgrupo().getDescricaoSubgrupo();
				}
				
				
			}
			
			
			session.getTransaction().commit();
			return p;
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
	
	// retorna null se não encontrar
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
			System.out.println("\n\n\nSolicitações de permuta: \n" + pessoaResult.getListSolicitacaoPermuta());
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
					"select distinct p "
					+ "from SolicitacaoPermuta s "
					+ "inner join s.pessoa p "
					+ "inner join s.praca pra_s "
					+ "inner join p.praca pra_p "
					+ "where "
					+ "pra_p.idPraca = '"+ idPracaDesejada + "' "
					+ "and "
					+ "pra_s.idPraca = '"+ idPracaAtual + "' ").getResultList();
			
			
			System.out.println("\n\ngetAllByPracaIdWherePracaDesejadaId: pessoas de uma praça...  \n\n" +listPessoa);
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

	
	// requisito:**********************************
	//int deve ser 0 para parametros nulos
	//*********************************************
	public List<Pessoa> getAllByPracaIdWherePracaDesejadaIdRelevante(int idPracaAtual, int idPracaDesejada ,
			int idUnidadeDesejada, int idProcessoDesejado) {
		try {
			initDao();
			session.beginTransaction();
			
			String whereClause = null;
			
			// ambos parametros nulos
			if (idUnidadeDesejada == 0 && idProcessoDesejado == 0) {
				whereClause = "where "
								+ "pra_p.idPraca = '"+ idPracaDesejada + "' "
								+ "and "
								+ "pra_s.idPraca = '"+ idPracaAtual + "' ";
			}
			// unidade nula apenas
			else if(idUnidadeDesejada == 0) {
				whereClause = "where "
						+ "pra_p.idPraca = '"+ idPracaDesejada + "' "
						+ "and "
						+ "pra_s.idPraca = '"+ idPracaAtual + "' "
						+ "and "
						+ "cadeia.idCadeiaValorSubgrupo = '"+ idProcessoDesejado + "' ";
			}
			// processo nulo apenas
			else if (idProcessoDesejado == 0) {
				whereClause = "where "
						+ "pra_p.idPraca = '"+ idPracaDesejada + "' "
						+ "and "
						+ "pra_s.idPraca = '"+ idPracaAtual + "' "
						+ "and "
						+ "u.idUnidade = '"+ idUnidadeDesejada + "' ";
			}
			// nenhum nulo : 
			else {
				whereClause = "where "
						+ "pra_p.idPraca = '"+ idPracaDesejada + "' "
						+ "and "
						+ "pra_s.idPraca = '"+ idPracaAtual + "' "
						+ "and "
						+ "( "
						+ "u.idUnidade = '"+ idUnidadeDesejada + "' "
						+ "or "
						+ "cadeia.idCadeiaValorSubgrupo = '"+ idProcessoDesejado + "' "
						+ ") ";
				
			}
			
			//###########Localiza user na tabela users pelo loginName##############
			List <Pessoa> listPessoa = session.createQuery(
					"select distinct p "
					+ "from SolicitacaoPermuta s "
					+ "inner join s.pessoa p "
					+ "inner join p.praca pra_p "
					+ "inner join p.cadeiaValorSubgrupo cadeia "
					+ "inner join p.componenteAdministrativo componente "
					+ "inner join componente.unidade u "
					+ "inner join s.praca pra_s "
					
					+ whereClause ).getResultList();
			
			
			// forçar carregamento das solicitações dessas pessoas
			
			for(Pessoa p : listPessoa) {
				for(SolicitacaoPermuta s : p.getListSolicitacaoPermuta()) {
				
					if(s.getCadeiaValorSubgrupo() != null) {
					
						s.getCadeiaValorSubgrupo().getIdCadeiaValorSubgrupo();
					}
					if(s.getUnidade() != null) {
					
						s.getUnidade().getIdUnidade();
					}
					
					
				}
				
			}
			
			
			System.out.println("\n\ngetAllByPracaIdWherePracaDesejadaIdRelevante: pessoas de uma praça...  \n\n" +listPessoa);
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
