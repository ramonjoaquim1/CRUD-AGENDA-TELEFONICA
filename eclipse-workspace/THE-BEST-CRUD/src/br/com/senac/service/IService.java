package br.com.senac.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public interface IService {
	
	public abstract ContatoVO buscarProdutoPorId( ContatoVO contatVO)
			throws BOException;
		
		public abstract List<ContatoVO> listarProduto(ContatoVO contatVO, 
													BigInteger id, 
													String descri, 
													String status, 
													Date datnas,
													String observ) throws BOException;
		
		public abstract void salvar(ContatoVO produtoVO) 
				throws BOValidationException, BOException;
		
		
		public abstract void excluir(ContatoVO produtoVO) 
				throws BOValidationException, BOException;

		List<ContatoVO> listarContato(ContatoVO contatVO, BigInteger id, String descri, String status, Date datnas,
				String observ) throws BOException;


}
