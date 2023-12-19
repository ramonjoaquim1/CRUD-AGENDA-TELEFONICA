package br.com.senac.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.PessoaVO;

public class PessoaDAO implements IPessoaDao {

	@Override
	public PessoaVO buscarPessoaPorId(PessoaVO pessoaVO) throws BOException {
		EntityManager em = HibernateUtil.getEntityManager();
		PessoaVO pessoa = em.find(PessoaVO.class, pessoaVO.getId());
		System.out.println("Pessoa: " + pessoa.getId() + " - " + pessoa.getNome());
		em.close();
		return pessoa;
	}

	@Override
	public List<PessoaVO> listarPessoa(PessoaVO pessoaVO, BigInteger id, String descri, Date datnas, String observ)
			throws BOException {
		System.out.println("********************Començando listagem**********************");
		EntityManager em = HibernateUtil.getEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PessoaVO> criteria = cb.createQuery(PessoaVO.class);
		
		Root<PessoaVO> pessoaFrom = criteria.from(PessoaVO.class);
		
		Predicate pessoaWhere = cb.equal(pessoaFrom.get("client"), pessoaVO);
		
		if (id != null) {
			pessoaWhere = cb.and(pessoaWhere, cb.equal(pessoaFrom.get("id"), id));
		}
		
		if (descri != null) {
			pessoaWhere = cb.and(pessoaWhere, cb.like(cb.lower(pessoaFrom.get("descri")),
					"%" + descri.toLowerCase() + "%"));
		}
		
		// Clausula OrderBy
		Order pessoaOrderBy = cb.asc(pessoaFrom.get("descri"));
		
		criteria.select(pessoaFrom);
		criteria.where(pessoaWhere);
		criteria.orderBy(pessoaOrderBy);
		
		TypedQuery<PessoaVO> query = em.createQuery(criteria);
		
		// List do pacote util
		List<PessoaVO> listaPessoas = query.getResultList();
		System.out.println("********************Encerrou!**********************");
		em.close();
		
		return listaPessoas;
	}

	@Override
	public void salvar(PessoaVO pessoaVO) throws BOValidationException, BOException {
		EntityManager em = HibernateUtil.getEntityManager();

		try {
			
			em.getTransaction().begin();
			
			if (pessoaVO.getId() == null) {
				em.persist(pessoaVO);
			} else {
				em.merge(pessoaVO);
			}
			em.getTransaction().commit();
			System.out.println("Pessoa inserida com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);
		} finally {
			em.close();
		}
	}

	@Override
	public void excluir(PessoaVO pessoaVO) throws BOValidationException, BOException {
		EntityManager em = HibernateUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			PessoaVO pessoa = em.find(PessoaVO.class, pessoaVO.getId());
			em.remove(pessoa); // merge edicao
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);
			
		} finally {
			em.close();
		}
	}
}
