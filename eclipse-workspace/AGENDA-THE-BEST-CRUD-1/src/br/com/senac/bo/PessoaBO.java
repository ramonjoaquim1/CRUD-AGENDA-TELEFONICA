package br.com.senac.bo;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.dao.PessoaDAO;
import br.com.senac.dao.IPessoaDao;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.PessoaVO;

public class PessoaBO implements IPessoaBO {

    private IPessoaDao pessoaDao;

    public PessoaBO() {
        pessoaDao = new PessoaDAO();
    }

    @Override
    public PessoaVO buscarPessoaPorId(PessoaVO pessoaVO) throws BOException {
        if (pessoaVO == null || pessoaVO.getId() == null) {
            throw new BOException("Código identificador inválido.");
        }
        return pessoaDao.buscarPessoaPorId(pessoaVO);
    }

    @Override
    public List<PessoaVO> listarPessoa(PessoaVO pessoaVO, BigInteger id, String descri, Date datnas, String observ) throws BOException {
        if (pessoaVO == null || pessoaVO.getId() == null) {
            throw new BOException("Cliente não pode ser nulo na Consulta de pessoas.");
        }
        return pessoaDao.listarPessoa(pessoaVO, id, descri, datnas, observ);
    }

    @Override
    public void salvar(PessoaVO pessoaVO) throws BOValidationException, BOException {
        if (pessoaVO == null) {
            throw new BOException("Pessoa nula ou inválida.");
        } else if (pessoaVO.getNome() == null) {
            throw new BOException("Descrição: Erro de validação: descrição da pessoa nula.");
        } else if (pessoaVO.getNome().length() < 2) {
            throw new BOValidationException("Descrição: Erro de validação: a descrição da pessoa é muito curta.");
        } else if (pessoaVO.getDatnas() == null) {
            throw new BOException("Data: Erro de validação: data da pessoa nula.");
        } else if (pessoaVO.getObserv() == null) {
            throw new BOException("Observação: Erro de validação: observação da pessoa nula.");
        }

        pessoaDao.salvar(pessoaVO);
    }

    @Override
    public void excluir(PessoaVO pessoaVO) throws BOValidationException, BOException {
        if (pessoaVO == null || pessoaVO.getId() == null) {
            throw new BOException("Produto nulo ou inválido. Impossível de excluir.");
        }

        pessoaDao.excluir(pessoaVO);
    }
}
