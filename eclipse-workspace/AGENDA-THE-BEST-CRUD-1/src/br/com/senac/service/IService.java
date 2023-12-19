package br.com.senac.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.PessoaVO;
import br.com.senac.vo.ContatoVO;

public interface IService {

    public abstract PessoaVO buscarPessoaPorId(PessoaVO pessoaVO) throws BOException;

    public abstract List<PessoaVO> listarPessoa(PessoaVO pessoaVO, BigInteger id, String descri, Date datnas,
            String observ) throws BOException;

    public abstract void salvar(PessoaVO pessoaVO) throws BOValidationException, BOException;

    public abstract void excluir(PessoaVO pessoaVO) throws BOValidationException, BOException;

    public abstract ContatoVO buscarContatoPorId(ContatoVO contatoVO) throws BOException;

    public abstract List<ContatoVO> listarContato(PessoaVO pessoaSelecionada, BigInteger id, String numero, String dddnum,
            String emails) throws BOException;

    public abstract void salvar(ContatoVO contatoVO) throws BOValidationException, BOException;

    public abstract void excluir(ContatoVO contatoVO) throws BOValidationException, BOException;

	List<ContatoVO> listarContato(ContatoVO contatoVO, BigInteger id, String numero, String dddnum, String emails)
			throws BOException;
}
