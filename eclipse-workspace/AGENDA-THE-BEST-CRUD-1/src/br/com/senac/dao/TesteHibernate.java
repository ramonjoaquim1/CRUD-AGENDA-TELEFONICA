package br.com.senac.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.senac.view.LoginView;
import br.com.senac.vo.PessoaVO;
import br.com.senac.vo.ContatoVO;


public class TesteHibernate {
	
	
	// Incluir - 
	// Editar  -
	// Excluir - 
	
	// 3 consultas: 
	// 1º Listagem de todos números/emails de um determinado contato - 
	// 2º listagem de todos os contatos que possuam um determinado nome (passado por parâmetro) - 
	// 3º Todos os contatos que não possuam número telefônico atribuído/cadastrado. - 
	
	
	
	
	public static void main(String[] args) {
		
		LoginView frame = new LoginView();
		frame.setVisible(true);
		
/*		TesteHibernate th = new TesteHibernate();
		
		th.inserirContato();
		//th.inserirPorId();
		//th.excluirContato();
		//th.editar();
		
		//th.consultaNumEmails();
		th.consultaPorNome();
		//th.consultaCampoNulo();
		
		System.exit(0);    */
	}

	private void consultaCampoNulo() {
		System.out.println("Campo vazio");
		EntityManager em = HibernateUtil.getEntityManager();
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);
	
		//Clausula from
		Root<ContatoVO> contelFrom = criteria.from(ContatoVO.class);
		
		Predicate contelWhere = cb.isNull(contelFrom.get("numero"));
		
		criteria.select(contelFrom);
		criteria.where(contelWhere);

		
		TypedQuery<ContatoVO> query = em.createQuery(criteria);
		
		//Limita quantidade de Registro
		query.setMaxResults(10);
		
		//List do pacote util
		List<ContatoVO> listaContatoTel = query.getResultList();
		System.out.println("\n");
		
		for (ContatoVO contelVO : listaContatoTel) {
			System.out.println("Contat: " + contelVO.getContat().getNome() + 
					"\nNúmero: " + contelVO.getNumero() +
					"\nE-mails: " + contelVO.getEmails());
			System.out.println("\n");
		}
		System.out.println("Fim");
		em.close();
	}
		
	

	private void consultaPorNome() {
		
		
		System.out.println("Consulta por nome");
		EntityManager em = HibernateUtil.getEntityManager();
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);
	
		//Clausula from
		Root<ContatoVO> contelFrom = criteria.from(ContatoVO.class);
		
		PessoaVO cont = new PessoaVO();
		cont.setId(BigInteger.ONE);
		
		//Class é do pacote Java.persistente
		Predicate contatWhere = cb.equal(contelFrom.get("contat"), cont);
		contatWhere = cb.and(contatWhere, cb.like(cb.lower(contelFrom.get("emails")), "%gmail%"));
		
		
		//Clausula OrderBy
		Order contatOrderBy = cb.asc(contelFrom.get("emails"));
		
		criteria.select(contelFrom);
		criteria.where(contatWhere);
		criteria.orderBy(contatOrderBy);
		
		TypedQuery<ContatoVO> query = em.createQuery(criteria);
		
		//Limita quantidade de Registro
		query.setMaxResults(10);
		
	
		//List do pacote util
		List<ContatoVO> listaContatos = query.getResultList();
		System.out.println("\n");
		
		for (ContatoVO contelVO : listaContatos) {
			System.out.println("Contat: " + contelVO.getContat().getNome() + 
					"\nNúmero: " + contelVO.getNumero() +
					"\nE-mails: " + contelVO.getEmails());
			System.out.println("\n");
		}
		System.out.println("Fim");
		em.close();
	}
		
		
		
		
		
	

	private void inserirPorId() {
		
		    System.out.println("********************Inserindo contel por ID**********************");

		    EntityManager em = HibernateUtil.getEntityManager();

		    // Verifica se o ContatVO com id 1 existe
		    PessoaVO contatVO = em.find(PessoaVO.class, new BigInteger("1"));

		    if (contatVO == null) {
		        System.out.println("ContatVO com id 1 não encontrado.");
		        em.close();
		        return;
		    }

		    ContatoVO contelVO = new ContatoVO();
		    contelVO.setNumero("9933333");
		    contelVO.setDddnum("68");
		    contelVO.setEmails("asdrubol@gmail.com");
		    contelVO.setContat(contatVO); // Seta contato da chave estrangeira

		    try {
		        em.getTransaction().begin();
		        em.persist(contelVO);
		        em.getTransaction().commit();
		        System.out.println("Contel inserido por ID com sucesso!!!");

		    } catch (Exception e) {
		        e.printStackTrace();
		        em.getTransaction().rollback();
		    } finally {
		        em.close();
		        System.out.println("Fim");
		    }
		}

		
		
	

	private void editar() {
		System.out.println("Editar");   
		
		//objeto que Interagi com Banco de dados
		EntityManager em = HibernateUtil.getEntityManager();
		
		try {
			//contel
			em.getTransaction().begin();
			ContatoVO contelVO = em.find(ContatoVO.class, new BigInteger("1"));
			contelVO.setNumero("99848717");
			contelVO.setDddnum("48");
			contelVO.setEmails("ruan@email.com");
			
			em.merge(contelVO);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
			System.out.println("Fim");   
		}
	}

	
	
	
	
	
	private void excluirContato() {
		System.out.println("Exclusão");   
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		try {
			//contel
			em.getTransaction().begin();
			ContatoVO contelVO = em.find(ContatoVO.class, new BigInteger("3"));
			
			em.remove(contelVO);
			em.getTransaction().commit();
			
			//contat
			em.getTransaction().begin();
			PessoaVO contatVO = em.find(PessoaVO.class, new BigInteger("3"));
			
			em.remove(contatVO);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
			System.out.println("Fim");  
		}
	}

	
	
	
	private void consultaNumEmails() {
		
		System.out.println("Consultar contato");
		EntityManager em = HibernateUtil.getEntityManager();
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);
	
		//Clausula from
		Root<ContatoVO> contelFrom = criteria.from(ContatoVO.class);
		
		PessoaVO cont = new PessoaVO();
		cont.setId(BigInteger.ONE);
	
		//Class é do pacote Java.persistente
		Predicate contatWhere = cb.equal(contelFrom.get("contat"), cont);
		
		
		//Clausula OrderBy
		Order contatoOrderBy = cb.asc(contelFrom.get("emails"));
		
		criteria.select(contelFrom);
		criteria.where(contatWhere);
		criteria.orderBy(contatoOrderBy);
		
		TypedQuery<ContatoVO> query = em.createQuery(criteria);
		
		//Limita quantidade de Registro
		query.setMaxResults(10);
		
	
		//List do pacote util
		List<ContatoVO> listaContat = query.getResultList();
		System.out.println("\n");
		
		for (ContatoVO contelVO : listaContat) {
			System.out.println("Contat: " + contelVO.getContat().getNome() + 
					"\nNúmero: " + contelVO.getNumero() +
					"\nE-mails: " + contelVO.getEmails());
			System.out.println("\n");
		}
		System.out.println("********************Encerrou!**********************");
		em.close();
	}
		
	

	
	
	
	private void inserirContato() {
		
		System.out.println("********************Inserindo contat**********************");
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		
		PessoaVO contatVO = new PessoaVO();
		contatVO.setNome(" Contato - Joao");
		contatVO.setDatnas(new Date());
		contatVO.setObserv("Teste inserindo observação");	
		
		try {
			em.getTransaction().begin();
			em.persist(contatVO);
			em.getTransaction().commit();
			System.out.println("Contat inserido com sucesso!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			
		} 
		
		
		ContatoVO contelVO = new ContatoVO();
		contelVO.setNumero("9966331");
		contelVO.setDddnum("48");
		contelVO.setEmails("ana@gmail.com");
		contelVO.setContat(contatVO); // Seta contato da chave estrangeira
		
		try {
			em.getTransaction().begin();
			em.persist(contelVO);
			em.getTransaction().commit();
			System.out.println("Contel inserido com sucesso!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
			System.out.println("********************Finalizando inserção....**********************");
		}
	}
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

