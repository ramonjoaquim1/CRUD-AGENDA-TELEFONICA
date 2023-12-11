package br.com.senac.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.bo.ContatoBO;
import br.com.senac.bo.IContatoBO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public class Service implements IService {

	@Override
	public ContatoVO buscarProdutoPorId(ContatoVO contatVO) throws BOException {
			
		IContatoBO contatoBO = new ContatoBO();
		return contatoBO.buscarProdutoPorId(contatVO);
	}

	@Override
	public List<ContatoVO> listarContato(ContatoVO contatVO, BigInteger id, String descri, String status, Date datnas,
			String observ) throws BOException {
		IContatoBO contatoBO = new ContatoBO();
		return contatoBO.listarContato(contatVO, id, descri, status, datnas, observ);
	}
	
	@Override
	public void salvar(ContatoVO contatVO) throws BOValidationException, BOException {
		IContatoBO contatoBO = new ContatoBO();
		contatoBO.salvar(contatVO);
	}

	@Override
	public void excluir(ContatoVO contatVO) throws BOValidationException, BOException {
		IContatoBO contatoBO = new ContatoBO();
		contatoBO.excluir(contatVO);
	}

	@Override
	public List<ContatoVO> listarProduto(ContatoVO contatVO, BigInteger id, String descri, String status, Date datnas,
			String observ) throws BOException {
		IContatoBO contatoBO = new ContatoBO();
		return contatoBO.listarContato(contatVO, id, descri, status, datnas, observ);
	}
}
