// Correção na interface IContatoBO
package br.com.senac.bo;

import java.math.BigInteger;
import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public interface IContatoBO {

    public abstract ContatoVO buscarContatoPorId(ContatoVO contatoVO) throws BOException;

    public abstract List<ContatoVO> listarContato(ContatoVO contatoVO, BigInteger id, String numero, String dddnum,
            String emails) throws BOException;

    public abstract void salvar(ContatoVO contatoVO) throws BOValidationException, BOException;

    public abstract void excluir(ContatoVO contatoVO) throws BOValidationException, BOException;
}
