// Correção na classe ContatoBO
package br.com.senac.bo;

import java.math.BigInteger;
import java.util.List;

import br.com.senac.dao.IContatoDAO;
import br.com.senac.dao.ContatoDAO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public class ContatoBO implements IContatoBO {

    private IContatoDAO contatoDAO;

    public ContatoBO() {
        contatoDAO = new ContatoDAO();
    }

    @Override
    public ContatoVO buscarContatoPorId(ContatoVO contatoVO) throws BOException {
        if (contatoVO == null || contatoVO.getId() == null) {
            throw new BOException("Código identificador inválido.");
        }
        return contatoDAO.buscarContatoPorId(contatoVO.getId());
    }


    @Override
    public List<ContatoVO> listarContato(ContatoVO contatoVO, BigInteger id, String numero, String dddnum,
            String emails) throws BOException {
        if (contatoVO == null || contatoVO.getContat() == null) {
            throw new BOException("Cliente não pode ser nulo na consulta de contatos.");
        }
        return contatoDAO.listarContato(contatoVO, id, numero, dddnum, emails);
    }

    @Override
    public void salvar(ContatoVO contatoVO) throws BOValidationException, BOException {
        if (contatoVO == null) {
            throw new BOException("Contato nulo ou inválido.");
        } else if (contatoVO.getNumero() == null) {
            throw new BOException("Número: Erro de validação: número do contato nulo.");
        }
        // Adicione outras validações conforme necessário

        contatoDAO.salvar(contatoVO);
    }

    @Override
    public void excluir(ContatoVO contatoVO) throws BOValidationException, BOException {
        if (contatoVO == null || contatoVO.getId() == null) {
            throw new BOException("Contato nulo ou inválido. Impossível excluir.");
        }
        contatoDAO.excluir(contatoVO);
    }
}
