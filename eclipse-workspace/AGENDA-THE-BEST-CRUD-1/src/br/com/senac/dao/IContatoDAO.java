// Correção na interface IContatoDAO
package br.com.senac.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public interface IContatoDAO {

    public abstract ContatoVO buscarContatoPorId(BigInteger id) throws BOException;

    public abstract List<ContatoVO> listarContato(ContatoVO contatoVO, BigInteger id, String numero, String dddnum,
            String emails) throws BOException;

    public abstract void salvar(ContatoVO contatoVO) throws BOValidationException, BOException;

    public abstract void excluir(ContatoVO contatoVO) throws BOValidationException, BOException;
}
