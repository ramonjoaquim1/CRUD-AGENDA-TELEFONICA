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


import br.com.senac.vo.ContatVO;
import br.com.senac.vo.ContelVO;


public class TesteHibernate {
	
	
	// Incluir - Ok 
	// Editar  - OK
	// Excluir - OK
	
	// 3 consultas: 
	// 1º Listagem de todos números/emails de um determinado contato - OK
	// 2º listagem de todos os contatos que possuam um determinado nome (passado por parâmetro) - OK
	// 3º Todos os contatos que não possuam número telefônico atribuído/cadastrado. - OK
	
	
	
	
	public static void main(String[] args) {
		
		
		TesteHibernate th = new TesteHibernate();
		
		th.inserirContato();
		//th.inserirPorId();
		//th.excluirContato();
		//th.editar();
		
		//th.consultaNumEmails();
		th.consultaPorNome();
		//th.consultaCampoNulo();
		
		System.exit(0);
	}

	private void consultaCampoNulo() {
		System.out.println("********************Iniciando consulta por campo Nulo**********************");
		EntityManager em = HibernateUtil.getEntityManager();
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContelVO> criteria = cb.createQuery(ContelVO.class);
	
		//Clausula from
		Root<ContelVO> contelFrom = criteria.from(ContelVO.class);
		
		Predicate contelWhere = cb.isNull(contelFrom.get("numero"));
		
		criteria.select(contelFrom);
		criteria.where(contelWhere);

		
		TypedQuery<ContelVO> query = em.createQuery(criteria);
		
		//Limita quantidade de Registro
		query.setMaxResults(10);
		
		//List do pacote util
		List<ContelVO> listaContatoTel = query.getResultList();
		System.out.println("\n");
		
		for (ContelVO contelVO : listaContatoTel) {
			System.out.println("Contat: " + contelVO.getContat().getDescri() + 
					"\nNúmero: " + contelVO.getNumero() +
					"\nE-mails: " + contelVO.getEmails());
			System.out.println("\n");
		}
		System.out.println("********************Finalizando consulta por campo Nulo**********************");
		em.close();
	}
		
	

	private void consultaPorNome() {
		
		
		System.out.println("********************Iniciando consulta por Nome**********************");
		EntityManager em = HibernateUtil.getEntityManager();
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContelVO> criteria = cb.createQuery(ContelVO.class);
	
		//Clausula from
		Root<ContelVO> contelFrom = criteria.from(ContelVO.class);
		
		ContatVO cont = new ContatVO();
		cont.setId(BigInteger.ONE);
		
		//Class é do pacote Java.persistente
		Predicate contatWhere = cb.equal(contelFrom.get("contat"), cont);
		contatWhere = cb.and(contatWhere, cb.like(cb.lower(contelFrom.get("emails")), "%gmail%"));
		
		
		//Clausula OrderBy
		Order contatOrderBy = cb.asc(contelFrom.get("emails"));
		
		criteria.select(contelFrom);
		criteria.where(contatWhere);
		criteria.orderBy(contatOrderBy);
		
		TypedQuery<ContelVO> query = em.createQuery(criteria);
		
		//Limita quantidade de Registro
		query.setMaxResults(10);
		
	
		//List do pacote util
		List<ContelVO> listaContatos = query.getResultList();
		System.out.println("\n");
		
		for (ContelVO contelVO : listaContatos) {
			System.out.println("Contat: " + contelVO.getContat().getDescri() + 
					"\nNúmero: " + contelVO.getNumero() +
					"\nE-mails: " + contelVO.getEmails());
			System.out.println("\n");
		}
		System.out.println("********************Finalizando consulta por nome!**********************");
		em.close();
	}
		
		
		
		
		
	

	private void inserirPorId() {
		
		    System.out.println("********************Inserindo contel por ID**********************");

		    EntityManager em = HibernateUtil.getEntityManager();

		    // Verifica se o ContatVO com id 1 existe
		    ContatVO contatVO = em.find(ContatVO.class, new BigInteger("1"));

		    if (contatVO == null) {
		        System.out.println("ContatVO com id 1 não encontrado.");
		        em.close();
		        return;
		    }

		    ContelVO contelVO = new ContelVO();
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
		        System.out.println("********************Finalizando inserção....**********************");
		    }
		}

		
		
	

	private void editar() {
		System.out.println("********************Inciando edição**********************");   
		
		//objeto que Interagi com Banco de dados
		EntityManager em = HibernateUtil.getEntityManager();
		
		try {
			//contel
			em.getTransaction().begin();
			ContelVO contelVO = em.find(ContelVO.class, new BigInteger("1"));
			contelVO.setNumero("9999999");
			contelVO.setDddnum("49");
			contelVO.setEmails("ze@gmail.com.br");
			
			em.merge(contelVO);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
			System.out.println("********************Finalizando edição**********************");   
		}
	}

	
	
	
	
	
	private void excluirContato() {
		System.out.println("********************Inciando exclusão**********************");   
		
		//objeto que Interagi com Banco de dados
		EntityManager em = HibernateUtil.getEntityManager();
		
		try {
			//contel
			em.getTransaction().begin();
			ContelVO contelVO = em.find(ContelVO.class, new BigInteger("3"));
			
			em.remove(contelVO);
			em.getTransaction().commit();
			
			//contat
			em.getTransaction().begin();
			ContatVO contatVO = em.find(ContatVO.class, new BigInteger("3"));
			
			em.remove(contatVO);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
			System.out.println("********************Finalizando exclusão**********************");  
		}
	}

	
	
	
	private void consultaNumEmails() {
		
		System.out.println("********************Iniciando consulta de contat**********************");
		EntityManager em = HibernateUtil.getEntityManager();
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContelVO> criteria = cb.createQuery(ContelVO.class);
	
		//Clausula from
		Root<ContelVO> contelFrom = criteria.from(ContelVO.class);
		
		ContatVO cont = new ContatVO();
		cont.setId(BigInteger.ONE);
	
		//Class é do pacote Java.persistente
		Predicate contatWhere = cb.equal(contelFrom.get("contat"), cont);
		
		
		//Clausula OrderBy
		Order contatoOrderBy = cb.asc(contelFrom.get("emails"));
		
		criteria.select(contelFrom);
		criteria.where(contatWhere);
		criteria.orderBy(contatoOrderBy);
		
		TypedQuery<ContelVO> query = em.createQuery(criteria);
		
		//Limita quantidade de Registro
		query.setMaxResults(10);
		
	
		//List do pacote util
		List<ContelVO> listaContat = query.getResultList();
		System.out.println("\n");
		
		for (ContelVO contelVO : listaContat) {
			System.out.println("Contat: " + contelVO.getContat().getDescri() + 
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
		
		
		ContatVO contatVO = new ContatVO();
		contatVO.setDescri(" Contato - Joao");
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
		
		
		ContelVO contelVO = new ContelVO();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

