package br.com.senac.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.dao.HibernateUtil;
import br.com.senac.vo.ContatoVO;

public class ContatoDAO implements IContatoDAO {

	@Override
    public ContatoVO buscarContatoPorId(BigInteger id) throws BOException {
        EntityManager em = HibernateUtil.getEntityManager();
        ContatoVO contato = em.find(ContatoVO.class, id);
        em.close();
        return contato;
    }

	 @Override
	    public List<ContatoVO> listarContato(ContatoVO contatoVO, BigInteger id, String numero, String dddnum,
	            String emails) throws BOException {
        EntityManager em = HibernateUtil.getEntityManager();
        TypedQuery<ContatoVO> query = em.createQuery("SELECT c FROM ContatoVO c", ContatoVO.class);
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
            em.getTransaction().rollback();
            throw new BOException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(ContatoVO contatoVO) throws BOValidationException, BOException {        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            ContatoVO contatoParaExcluir = em.find(ContatoVO.class, contatoVO.getId());
            em.remove(contatoParaExcluir);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new BOException(e);
        } finally {
            em.close();
        }
    }
}
