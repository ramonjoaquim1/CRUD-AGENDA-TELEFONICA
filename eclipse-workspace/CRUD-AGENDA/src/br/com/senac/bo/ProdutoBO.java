package br.com.senac.bo;

import java.math.BigInteger;
import java.util.List;

import br.com.senac.dao.IProdutoDAO;
import br.com.senac.dao.ProdutoDAO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ClienteVO;
import br.com.senac.vo.ProdutoVO;

public class ProdutoBO implements IProdutoBO {
	
	private IProdutoDAO produtoDAO;
	
	public ProdutoBO() {
		produtoDAO = new ProdutoDAO();
	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException {
		
		if(produto == null || produto.getId() == null) {
			//GRAVAR EM ARQUIVO DE LOG. 
			throw new BOException("Código identificador inválido.");
		}
		
		return produtoDAO.buscarProdutoPorId(produto);
	}

	@Override
	public List<ProdutoVO> listarProduto(ClienteVO cliente, 
			BigInteger id, String descri, String status, String codbar,
			Integer quanti, Integer pagina) throws BOException {
		
		if(cliente == null || cliente.getId() == null) {
			throw new BOException("Cliente não pode ser nulo na "
				+ "consuta de produtos.");
		}
		
		return produtoDAO.listarProduto(cliente, id, descri, 
				status, codbar, quanti, pagina);
	}

	@Override
	public void salvar(ProdutoVO produto) throws BOValidationException, BOException {
		
		if(produto == null) {
			throw new BOException("Produto nulo ou inválido.");
		}else if(produto.getDescri() == null) { 
			throw new BOException("Decrição: erro de validação: "
				+ "descrição do produto nula. ");
		}else if(produto.getDescri().length() < 2) {
			throw new BOValidationException("Decrição: erro de validação: "
				+ "a descrição do produto é muito curta.");
		}else if(produto.getStatus() == null) {
			throw new BOException("Status: erro de validação: "
					+ "status do produto nulo. ");
		}else if(produto.getStatus().equals("A") == false &&
				 produto.getStatus().equals("I") == false) {
			throw new BOException("Status: erro de validação: "
					+ "status inválido.");
		}
		
		produtoDAO.salvar(produto);
		
	}

	@Override
	public void excluir(ProdutoVO produto) throws BOValidationException, BOException {
		
		if(produto == null || produto.getId() == null) {
			throw new BOException("Produto nulo ou inválido. "
				+ "Impossível de excluir.");
		}
		
		produtoDAO.excluir(produto);
		
	}
	
	

}
