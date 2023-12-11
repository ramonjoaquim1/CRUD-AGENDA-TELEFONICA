package br.com.senac.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
//import br.com.senac.vo.ClienteVO;
import br.com.senac.vo.ContatoVO;

public class ContatoDAO implements IContatoDao {

    @Override
    public ContatoVO buscarContatoPorId(ContatoVO contatoVO) throws BOException {
        EntityManager em = HibernateUtil.getEntityManager();

        ContatoVO aux = em.find(ContatoVO.class, contatoVO.getId());

        System.out.println("Contato>> " + contatoVO.getId() + " - " + contatoVO.getDescri());

        em.close();

        return aux;
    }

    @Override
    public List<ContatoVO> listarContatos(BigInteger id, String nome, String email, String telefone) throws BOException {
        EntityManager em = HibernateUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);

        // Cláusula From
        Root<ContatoVO> contatoFrom = criteria.from(ContatoVO.class);

        // Essa classe é do pacote javax.persistence
        Predicate contatoWhere = cb.and();

        if (id != null) {
            contatoWhere = cb.and(contatoWhere, cb.equal(contatoFrom.get("id"), id));
        }

        if (nome != null) {
            contatoWhere = cb.and(contatoWhere, cb.like(cb.lower(contatoFrom.get("nome")),
                    "%" + nome.toLowerCase() + "%"));
        }

        if (email != null) {
            contatoWhere = cb.and(contatoWhere, cb.like(cb.lower(contatoFrom.get("email")),
                    "%" + email.toLowerCase() + "%"));
        }

        if (telefone != null) {
            contatoWhere = cb.and(contatoWhere, cb.like(cb.lower(contatoFrom.get("telefone")),
                    "%" + telefone.toLowerCase() + "%"));
        }

        // Cláusula OrderBy
        Order contatoOrderBy = cb.desc(contatoFrom.get("nome"));

        criteria.select(contatoFrom);
        criteria.where(contatoWhere);
        criteria.orderBy(contatoOrderBy);

        TypedQuery<ContatoVO> query = em.createQuery(criteria);

        // List do pacote util.
        List<ContatoVO> listaContatos = query.getResultList();

        em.close();

        return listaContatos;
    }

    @Override
    public void salvarContato(ContatoVO contatoVO) throws BOException {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            if (contatoVO.getId() == null) {
                em.persist(contatoVO);
            } else {
                em.merge(contatoVO);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BOException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void excluirContato(BigInteger id) throws BOException {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            ContatoVO contatoVO = em.find(ContatoVO.class, id);
            em.remove(contatoVO);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BOException(e);
        } finally {
            em.close();
        }
    }

	@Override
	public ContatoVO buscarProdutoPorId(ContatoVO contatVO) throws BOException {
		EntityManager em = HibernateUtil.getEntityManager();

        ContatoVO aux = em.find(ContatoVO.class, contatVO.getId());

        System.out.println("Contato>> " + contatVO.getId() + " - " + contatVO.getDescri());

        em.close();

        return aux;
	}

	@Override
	public List<ContatoVO> listarProduto(ContatoVO contatVO, BigInteger id, String descri, String status, Date datnas,
			Integer pagina) throws BOException {
		EntityManager em = HibernateUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);

        // Cláusula From
        Root<ContatoVO> contatoFrom = criteria.from(ContatoVO.class);

        // Essa classe é do pacote javax.persistence
        Predicate contatoWhere = cb.and();

        if (id != null) {
            contatoWhere = cb.and(contatoWhere, cb.equal(contatoFrom.get("id"), id));
        }

        if (descri != null) {
            contatoWhere = cb.and(contatoWhere, cb.like(cb.lower(contatoFrom.get("descrição")),
                    "%" + descri.toLowerCase() + "%"));
        }

        if (status != null) {
            contatoWhere = cb.and(contatoWhere, cb.like(cb.lower(contatoFrom.get("status")),
                    "%" + status.toLowerCase() + "%"));
        }

        if (datnas != null) {
            contatoWhere = cb.and(contatoWhere, cb.like(cb.lower(contatoFrom.get("data de nascimento")),
                    "%" + datnas.toLocalDate() + "%"));
        }
        if (pagina != null) {
            contatoWhere = cb.and(contatoWhere, cb.like(cb.lower(contatoFrom.get("pagina")),
                    "%" + pagina.toString() + "%"));
        }
        // Cláusula OrderBy
        Order contatoOrderBy = cb.desc(contatoFrom.get("nome"));

        criteria.select(contatoFrom);
        criteria.where(contatoWhere);
        criteria.orderBy(contatoOrderBy);

        TypedQuery<ContatoVO> query = em.createQuery(criteria);

        // List do pacote util.
        List<ContatoVO> listaContatos = query.getResultList();

        em.close();

        return listaContatos;
	}

	@Override
	public void salvar(ContatoVO contatoVO) throws BOValidationException, BOException {
		EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            if (contatoVO.getId() == null) {
                em.persist(contatoVO);
            } else {
                em.merge(contatoVO);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BOException(e);
        } finally {
            em.close();
        }
	}

	@Override
	public void excluir(BigInteger id) throws BOException {
		EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            ContatoVO contatoVO = em.find(ContatoVO.class, id);
            em.remove(contatoVO);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BOException(e);
        } finally {
            em.close();
        }
	}

	@Override
	public void excluir(ContatoVO contatoVO) throws BOValidationException, BOException {
		EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            ContatoVO contatoVO2 = em.find(ContatoVO.class, contatoVO);
            em.remove(contatoVO);

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
