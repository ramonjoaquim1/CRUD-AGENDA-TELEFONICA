package br.com.senac.bo;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.PessoaVO;

public interface IPessoaBO {

    public abstract PessoaVO buscarPessoaPorId(PessoaVO pessoaVO) throws BOException;

    public abstract List<PessoaVO> listarPessoa(PessoaVO pessoaVO, BigInteger id, String descri, Date datnas, String observ) throws BOException;

    public abstract void salvar(PessoaVO pessoaVO) throws BOValidationException, BOException;

    public abstract void excluir(PessoaVO pessoaVO) throws BOValidationException, BOException;
}
