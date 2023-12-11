package br.com.senac.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public interface IContatoDao {
	
	public abstract ContatoVO buscarProdutoPorId( ContatoVO contatVO)
			throws BOException;
		
		public abstract List<ContatoVO> listarProduto(
				ContatoVO contatVO, BigInteger id, String descri, String status, Date datnas, Integer pagina)
						throws BOException;
		
		public abstract void salvar(ContatoVO produtoVO) 
				throws BOValidationException, BOException;
		
		
		public abstract void excluir(ContatoVO produtoVO) 
				throws BOValidationException, BOException;

		void excluir(BigInteger id) throws BOException;

		void salvarContato(ContatoVO contatoVO) throws BOException;

		void excluirContato(BigInteger id) throws BOException;

		List<ContatoVO> listarContatos(BigInteger id, String nome, String email, String telefone) throws BOException;

		ContatoVO buscarContatoPorId(ContatoVO contatoVO) throws BOException;

}
