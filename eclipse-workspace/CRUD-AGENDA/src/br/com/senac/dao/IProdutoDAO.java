package br.com.senac.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.vo.ClienteVO;
import br.com.senac.vo.ProdutoVO;

public interface IProdutoDAO {
	
	public abstract ProdutoVO buscarProdutoPorId(ProdutoVO produto)
		throws BOException;
	
	public abstract List<ProdutoVO> listarProduto(
		ClienteVO cliente,
		BigInteger id,
		String descri,
		String status,
		String codbar,
		Integer quanti,
		Integer pagina) throws BOException;
	
	public abstract void salvar(ProdutoVO produto) throws BOException;
	
	public abstract void excluir(ProdutoVO produto) throws BOException;

}
