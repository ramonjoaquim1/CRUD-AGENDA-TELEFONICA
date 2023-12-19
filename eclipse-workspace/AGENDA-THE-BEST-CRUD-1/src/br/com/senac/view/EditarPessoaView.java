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
import br.com.senac.vo.PessoaVO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class EditarPessoaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField ftfCodigo;
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfDataNasc;
	private JFormattedTextField ftfObservacao;
	private PessoaVO contatoAtual;
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarPessoaView frame = new EditarPessoaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public EditarPessoaView() {
		contatoAtual = new PessoaVO();

		setTitle("Editar  Contato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(12, 53, 60, 14);
		contentPane.add(lblCodigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 117, 46, 14);
		contentPane.add(lblNome);

		JLabel lblDatnas = new JLabel("Data de nascimento:");
		lblDatnas.setBounds(12, 183, 161, 14);
		contentPane.add(lblDatnas);

		JLabel lblObservacao = new JLabel("Observação:");
		lblObservacao.setBounds(12, 260, 94, 14);
		contentPane.add(lblObservacao);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setEditable(false);
		ftfCodigo.setBounds(12, 74, 372, 20);
		contentPane.add(ftfCodigo);

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(12, 135, 372, 20);
		contentPane.add(ftfNome);

		ftfDataNasc = new JFormattedTextField();
		ftfDataNasc.setBounds(12, 204, 148, 20);
		contentPane.add(ftfDataNasc);

		ftfObservacao = new JFormattedTextField();
		ftfObservacao.setBounds(12, 286, 695, 171);
		contentPane.add(ftfObservacao);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setMnemonic('C');
		btnSalvar.setBounds(65, 486, 127, 23);
		contentPane.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cancelar();
			}
		});
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
		this.dispose();

	}

	protected void salvar() {
		try {

			Service service = new Service();
			PessoaVO contatoVO = new PessoaVO();

			String codigo = ftfCodigo.getText().trim();
			String nome = ftfNome.getText().trim();
			String datNasc = ftfDataNasc.getText().trim();
			String observ = ftfObservacao.getText().trim();

			if (codigo != null && codigo.length() > 0) {
				contatoVO.setId(new BigInteger(codigo));
				contatoVO = service.buscarPessoaPorId(contatoVO);
			}

			String pattern = "dd/MM/yyyy HH:mm:ss";
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			
			try {
			    if (!datNasc.isEmpty()) {
			        Date date = dateFormat.parse(datNasc + " 00:00:00");
			        contatoVO.setDatnas(date);
			    } else {
			        JOptionPane.showMessageDialog(this, "Data de nascimento está vazia!", "Erro",
			                JOptionPane.ERROR_MESSAGE);
			        return;
			    }
			} catch (ParseException e) {
			    // Trate a exceção de formato de data inválido
			    e.printStackTrace();
			    JOptionPane.showMessageDialog(this, "Formato de data inválido! Formato correto dd/MM/yyyy HH:mm:ss", "Erro",
			            JOptionPane.ERROR_MESSAGE);
			    return;
			}

			contatoVO.setNome(nome);
			contatoVO.setObserv(observ);

			service.salvar(contatoVO);

			JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!");

		} catch (BOValidationException b) {
			b.printStackTrace();
			JOptionPane.showMessageDialog(this, b.getMessage(), "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);

		} catch (Exception b) {
			b.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			System.out.println("Finally");
		}
	}
	

	public void editar(PessoaVO cont) {

		contatoAtual = cont;

		this.ftfCodigo.setText(cont.getId().toString());
		this.ftfNome.setText(cont.getNome().toString());
		this.ftfDataNasc.setText(cont.getDatnas().toString());
		this.ftfObservacao.setText(cont.getObserv().toString());

	}

}
