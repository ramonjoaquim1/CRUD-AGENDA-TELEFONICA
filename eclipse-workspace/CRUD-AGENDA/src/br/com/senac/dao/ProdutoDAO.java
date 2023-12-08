package br.com.senac.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.senac.exception.BOException;
import br.com.senac.vo.ClienteVO;
import br.com.senac.vo.ProdutoVO;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();

		ProdutoVO aux = em.find(ProdutoVO.class, produto.getId());

		System.out.println("Produto>> " + produto.getId() + " - " + produto.getDescri());

		em.close();
		
		return aux;
	}

	@Override
	public List<ProdutoVO> listarProduto(ClienteVO cliente, 
			BigInteger id, String descri, String status, String codbar,
			Integer quanti, Integer pagina) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// Cláusula From
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// Essa classe é do pacote javax.persistence
		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), cliente);
		
		if(id != null) {
			produtoWhere = cb.and(produtoWhere, 
				cb.equal(produtoFrom.get("id"), id));
		}
		
		if(descri != null) {
			produtoWhere = cb.and(produtoWhere,
				cb.like(cb.lower(produtoFrom.get("descri")), 
						"%" + descri.toLowerCase() + "%"));
		}
		
		if(codbar != null) {
			produtoWhere = cb.and(produtoWhere,
				cb.like(cb.lower(produtoFrom.get("codbar")), 
						"%" + codbar.toLowerCase() + "%"));
		}
		
		if(status != null) {
			produtoWhere = cb.and(produtoWhere, 
				cb.equal(produtoFrom.get("status"), status));
		}
		

		// Cláusula OrderBy
		Order produtoOrderBy = cb.desc(produtoFrom.get("descri"));

		criteria.select(produtoFrom);
		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBy);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);

		// Limita a quantidade de registros;
		query.setMaxResults(quanti);

		// Similar ao offset
		query.setFirstResult(pagina);

		// List do pacote util.
		List<ProdutoVO> listaProdutos = query.getResultList();

		em.close();
		
		return listaProdutos;

	}

	@Override
	public void salvar(ProdutoVO produto) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		try {

			em.getTransaction().begin();
			if(produto.getId() == null) {
				em.persist(produto);
			}else {
				em.merge(produto);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);
		}
		
	}

	@Override
	public void excluir(ProdutoVO produto) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			ProdutoVO produtoVO = em.find(ProdutoVO.class, produto.getId());
			em.remove(produtoVO);
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
