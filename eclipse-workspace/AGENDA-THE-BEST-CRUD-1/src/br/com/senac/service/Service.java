package br.com.senac.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.bo.PessoaBO;
import br.com.senac.bo.ContatoBO;
import br.com.senac.bo.IContatoBO;
import br.com.senac.bo.IPessoaBO;
import br.com.senac.bo.PessoaBO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.PessoaVO;
import br.com.senac.vo.ContatoVO;

public class Service implements IService {

    private IPessoaBO pessoaBO;
    private IContatoBO contatoBO;

    public Service() {
        pessoaBO = new PessoaBO();
        contatoBO = new ContatoBO();
    }

    @Override
    public PessoaVO buscarPessoaPorId(PessoaVO pessoaVO) throws BOException {
        return pessoaBO.buscarPessoaPorId(pessoaVO);
    }

    @Override
    public List<PessoaVO> listarPessoa(PessoaVO pessoaVO, BigInteger id, String descri, Date datnas, String observ)
            throws BOException {
        return pessoaBO.listarPessoa(pessoaVO, id, descri, datnas, observ);
    }

    @Override
    public void salvar(PessoaVO pessoaVO) throws BOValidationException, BOException {
        pessoaBO.salvar(pessoaVO);
    }

    @Override
    public void excluir(PessoaVO pessoaVO) throws BOValidationException, BOException {
        pessoaBO.excluir(pessoaVO);
    }

    @Override
    public ContatoVO buscarContatoPorId(ContatoVO contatoVO) throws BOException {
        return contatoBO.buscarContatoPorId(contatoVO);
    }

    @Override
    public List<ContatoVO> listarContato(ContatoVO contatoVO, BigInteger id, String numero, String dddnum,
            String emails) throws BOException {
        return contatoBO.listarContato(contatoVO, id, numero, dddnum, emails);
    }

    @Override
    public void salvar(ContatoVO contatoVO) throws BOValidationException, BOException {
        contatoBO.salvar(contatoVO);
    }

    @Override
    public void excluir(ContatoVO contatoVO) throws BOValidationException, BOException {
        contatoBO.excluir(contatoVO);
    }

	@Override
	public List<ContatoVO> listarContato(PessoaVO pessoaSelecionada, BigInteger id, String numero, String dddnum,
			String emails) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}
}
