package br.com.senac.bo;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.dao.ContatoDAO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public class ContatoBO implements IContatoBO {

    private ContatoDAO contatoDAO;

    public ContatoBO() {
        this.contatoDAO = new ContatoDAO();
    }

    @Override
    public ContatoVO buscarProdutoPorId(ContatoVO contatoVO) throws BOException {
        try {
            validarId(contatoVO); // Validação do ID
            return contatoDAO.buscarContatoPorId(contatoVO);
        } catch (BOValidationException e) {
            throw new BOException("Erro ao buscar o contato pelo ID", e);
        }
    }

    @Override
    public List<ContatoVO> listarContato(ContatoVO contatoVO, BigInteger id, String descri, String status,
            Date datnas, String observ) throws BOException {
        try {
            // Lógica para listar contatos com base nos parâmetros fornecidos
            return contatoDAO.listarContatos(id, descri, status, observ);
        } catch (Exception e) {
            throw new BOException("Erro ao listar contatos", e);
        }
    }

    @Override
    public void salvar(ContatoVO contatoVO) throws BOValidationException, BOException {
        try {
            validarDados(contatoVO); // Validação dos dados do contato
            contatoDAO.salvarContato(contatoVO);
        } catch (BOValidationException e) {
            throw e; // Jogue novamente exceções de validação
        } catch (Exception e) {
            throw new BOException("Erro ao salvar o contato", e);
        }
    }

    @Override
    public void excluir(ContatoVO contatoVO) throws BOValidationException, BOException {
        try {
            validarExclusao(contatoVO); // Validação para exclusão
            contatoDAO.excluirContato(contatoVO.getId());
        } catch (BOValidationException e) {
            throw e; // Jogue novamente exceções de validação
        } catch (Exception e) {
            throw new BOException("Erro ao excluir o contato", e);
        }
    }

    // Métodos de validação podem ser adicionados conforme necessário

    private void validarDados(ContatoVO contatoVO) throws BOValidationException {
        // Lógica de validação dos dados do contato
        if (contatoVO.getDescri() == null || contatoVO.getDescri().isEmpty()) {
            throw new BOValidationException("Nome é obrigatório");
        }
        // Adicione mais validações conforme necessário
    }

    private void validarExclusao(ContatoVO contatoVO) throws BOValidationException {
        // Lógica de validação para exclusão de contatos
        if (contatoVO.getId() == null) {
            throw new BOValidationException("ID do contato é obrigatório para exclusão");
        }
        // Adicione mais validações conforme necessário
    }

    private void validarId(ContatoVO contatoVO) throws BOValidationException {
        // Lógica de validação para o ID do contato
        if (contatoVO == null || contatoVO.getId() == null) {
            throw new BOValidationException("Código identificador inválido.");
        }
    }

	@Override
	public List<ContatoVO> listarProduto(ContatoVO contatVO, BigInteger id, String descri, String status, Date datnas,
			String observ) throws BOException {
		try {
            // Lógica para listar contatos com base nos parâmetros fornecidos
            return contatoDAO.listarContatos(id, descri, status, observ);
        } catch (Exception e) {
            throw new BOException("Erro ao listar produtos", e);
        }
	}
}

