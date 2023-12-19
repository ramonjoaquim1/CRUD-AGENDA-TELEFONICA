package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import br.com.senac.exception.BOValidationException;
import br.com.senac.service.Service;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.PessoaVO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class AdicionarContatoView extends JFrame {

    private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JFormattedTextField ftfCodigo;
    private JFormattedTextField ftfNumero;
    private JFormattedTextField ftfDdd;
    private JFormattedTextField ftfEmails;
    private JFormattedTextField ftfObservacao;
    private ContatoVO contatoAtual;
    private CadastroContatoView janelaPai;
    private PessoaVO pessoaSelecionada;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                AdicionarContatoView frame = new AdicionarContatoView();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//    public AdicionarContatoView(CadastroContatoView janelaPai, PessoaVO pessoaSelecionada) {
//        this.janelaPai = janelaPai;
//        this.pessoaSelecionada = pessoaSelecionada;
//    }
    public AdicionarContatoView(CadastroContatoView cadastroContatoView, PessoaVO pessoaSelecionada) {    	 
    		this.janelaPai = cadastroContatoView;
    	    this.pessoaSelecionada = pessoaSelecionada;
    	    this.janelaPai = janelaPai;
    	    contatoAtual = new ContatoVO();

        setTitle("Adicionar Contato");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 757, 551);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(12, 53, 60, 14);
        contentPane.add(lblCodigo);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(12, 117, 60, 14);
        contentPane.add(lblNumero);

        JLabel lblDdd = new JLabel("DDD:");
        lblDdd.setBounds(12, 183, 46, 14);
        contentPane.add(lblDdd);

        JLabel lblEmails = new JLabel("E-mails:");
        lblEmails.setBounds(12, 260, 60, 14);
        contentPane.add(lblEmails);

        JLabel lblObservacao = new JLabel("Observação:");
        lblObservacao.setBounds(12, 340, 94, 14);
        contentPane.add(lblObservacao);

        ftfCodigo = new JFormattedTextField();
        ftfCodigo.setEditable(false);
        ftfCodigo.setBounds(12, 74, 372, 20);
        contentPane.add(ftfCodigo);

        ftfNumero = new JFormattedTextField();
        ftfNumero.setBounds(12, 135, 372, 20);
        contentPane.add(ftfNumero);

        ftfDdd = new JFormattedTextField();
        ftfDdd.setBounds(12, 204, 148, 20);
        contentPane.add(ftfDdd);

        ftfEmails = new JFormattedTextField();
        ftfEmails.setBounds(12, 286, 372, 20);
        contentPane.add(ftfEmails);

        ftfObservacao = new JFormattedTextField();
        ftfObservacao.setBounds(12, 366, 695, 91);
        contentPane.add(ftfObservacao);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        
        btnSalvar.setMnemonic('C');
        btnSalvar.setBounds(65, 486, 127, 23);
        contentPane.add(btnSalvar);

        JButton btnCancelar = new JButton("Voltar");
        btnCancelar.addActionListener(e -> cancelar());
        btnCancelar.setMnemonic('C');
        btnCancelar.setBounds(503, 486, 127, 23);
        contentPane.add(btnCancelar);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
        panel.setBounds(12, 469, 695, 6);
        contentPane.add(panel);
    }

    protected void cancelar() {
        this.setVisible(false);
//        this.dispose();
    }

    protected void salvar() {
        try {
            Service service = new Service();
            ContatoVO contatoVO = new ContatoVO();

            String codigo = ftfCodigo.getText().trim();
            String numero = ftfNumero.getText().trim();
            String ddd = ftfDdd.getText().trim();
            String emails = ftfEmails.getText().trim();
            String observ = ftfObservacao.getText().trim();

            if (codigo != null && codigo.length() > 0) {
                contatoVO.setId(new BigInteger(codigo));
//                contatoVO = service.buscarContatoPorId(contatoVO);
            }

            contatoVO.setNumero(numero);
            contatoVO.setDddnum(ddd);
            contatoVO.setEmails(emails);
            contatoVO.setContat(pessoaSelecionada);
//            contatoVO.setObserv(observ);

//            service.salvar(contatoVO);
            service.salvar(contatoVO);
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");

        } catch (Exception b) {
            b.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
