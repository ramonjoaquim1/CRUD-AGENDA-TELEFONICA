package br.com.senac.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.Service;
import br.com.senac.vo.ClienteVO;
import br.com.senac.vo.ProdutoVO;
import br.com.senac.vo.StatusEnum;

public class ProdutoView extends JFrame {

	private JPanel contentPane;
	
	private JFormattedTextField ftfCodigo;
	private JFormattedTextField ftfDescri;
	private JFormattedTextField ftfCodbar;
	private JFormattedTextField ftfQtdest;
	private JFormattedTextField ftfVlrcom;
	private JFormattedTextField ftfVlrven;
	private JComboBox cbStatus;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public ProdutoView() throws ParseException {
		setResizable(false);
		setTitle("Manutenção de produto");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(10, 11, 85, 14);
		contentPane.add(lblCodigo);
		
		JLabel lblDescricao = new JLabel("Descrição: *");
		lblDescricao.setBounds(10, 36, 85, 14);
		contentPane.add(lblDescricao);
		
		JLabel lblCodBar = new JLabel("Cód. barras:");
		lblCodBar.setBounds(10, 61, 85, 14);
		contentPane.add(lblCodBar);
		
		JLabel lblQtdEst = new JLabel("Qtd. estoque: *");
		lblQtdEst.setBounds(10, 86, 85, 14);
		contentPane.add(lblQtdEst);
		
		JLabel lblVlrcom = new JLabel("Vlr. compra:");
		lblVlrcom.setBounds(10, 111, 85, 14);
		contentPane.add(lblVlrcom);
		
		JLabel lblVlrven = new JLabel("Vlr. venda:");
		lblVlrven.setBounds(10, 136, 85, 14);
		contentPane.add(lblVlrven);
		
		JLabel lblStatus = new JLabel("Status: *");
		lblStatus.setBounds(10, 161, 85, 14);
		contentPane.add(lblStatus);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setMnemonic('S');
		btnSalvar.setBounds(158, 198, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setMnemonic('F');
		btnFechar.setBounds(257, 198, 89, 23);
		contentPane.add(btnFechar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 186, 356, 2);
		contentPane.add(panel);
		
		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setEditable(false);
		ftfCodigo.setBounds(105, 8, 85, 20);
		contentPane.add(ftfCodigo);
		
		ftfDescri = new JFormattedTextField(
			new MaskFormatter("****************************************************************************************************"));
					
		ftfDescri.setBounds(105, 33, 241, 20);
		contentPane.add(ftfDescri);
		
		ftfCodbar = new JFormattedTextField(
				new MaskFormatter("********************"));
		ftfCodbar.setBounds(105, 58, 241, 20);
		contentPane.add(ftfCodbar);
		
		ftfQtdest = new JFormattedTextField();
		MascaraJFormattedTextField
			.formatNumericField("######0.000", ftfQtdest);
		ftfQtdest.setBounds(105, 83, 67, 20);
		contentPane.add(ftfQtdest);
		
		ftfVlrcom = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField(ftfVlrcom);
		ftfVlrcom.setBounds(105, 108, 67, 20);
		contentPane.add(ftfVlrcom);
		
		ftfVlrven = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField(ftfVlrven);
		ftfVlrven.setBounds(105, 133, 67, 20);
		contentPane.add(ftfVlrven);
		
		cbStatus = new JComboBox();
		DefaultComboBoxModel defaultComboBoxModel 
			= new DefaultComboBoxModel(StatusEnum.values());
		cbStatus.setModel(defaultComboBoxModel);
		defaultComboBoxModel.insertElementAt(null, 0);
		cbStatus.setSelectedIndex(0);
		
		cbStatus.setBounds(105, 157, 101, 22);
		contentPane.add(cbStatus);
	}

	protected void salvar() {
		
		try {
			
			Service servico = new Service();
			
			ProdutoVO produtoVO = new ProdutoVO();
			
			String codigo = ftfCodigo.getText();
			String descri = ftfDescri.getText().trim();
			String vlrCom = ftfVlrcom.getText().trim();
			String vlrVen = ftfVlrven.getText().trim();
			String qtdest = ftfQtdest.getText().trim();
			String codbar = ftfCodbar.getText().trim();
			String status = null;
			StatusEnum statusEnum = (StatusEnum)cbStatus.getSelectedItem();
			if(statusEnum != null) {
				status = statusEnum.name();
			}
			
			//Deveria vir de uma tela de seleção de cliente.
			ClienteVO c1 = new ClienteVO(BigInteger.ONE);
			
			produtoVO.setClient(c1);
			
			if(codigo != null && codigo.length() > 0) {
				produtoVO.setId(new BigInteger(codigo));
				produtoVO = servico.buscarProdutoPorId(produtoVO);
			}
			
			produtoVO.setDescri(descri);
			
			if(vlrCom != null && vlrCom.length() > 0) {
				vlrCom = vlrCom.replaceAll("\\.", "")
						.replaceAll(",", ".");
				produtoVO.setValcom(new BigDecimal(vlrCom));
			}
			
			if(vlrVen != null && vlrVen.length() > 0) {
				vlrVen = vlrVen.replaceAll("\\.", "")
						.replaceAll(",", ".");
				produtoVO.setValven(new BigDecimal(vlrVen));
			}
			
			if(qtdest != null && qtdest.length() > 0) {
				qtdest = qtdest.replaceAll("\\.", "")
						.replaceAll(",", ".");
				produtoVO.setQtdest(new BigDecimal(qtdest));
			}
			
			produtoVO.setCodbar(codbar);
			
			if(status != null) {
				produtoVO.setStatus(status);
			}
			
			servico.salvar(produtoVO);
			ftfCodigo.setText(produtoVO.getId().toString());
			
			JOptionPane.showMessageDialog(null, 
					"Operação realizada com sucesso!");			
			
		}catch (BOValidationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
				e.getMessage(),
				"Mensagem de aviso",
				JOptionPane.WARNING_MESSAGE);
		}catch (BOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
				"Ocorreu um erro ao realizar a operação!",
				"Erro",
				JOptionPane.ERROR_MESSAGE);
		}
		
	}

	protected void fechar() {
		this.setVisible(false);
		this.dispose();
	}

	public void editar(ProdutoVO aux) {
		
		this.ftfCodigo.setText(aux.getId().toString());
		this.ftfDescri.setText(aux.getDescri());
		this.ftfCodbar.setText(aux.getCodbar());
		
		this.ftfVlrcom.setText(
			aux.getValcom()
			.toPlainString()
			.replaceAll("\\.", ","));
		
		this.ftfVlrven.setText(
				aux.getValven()
				.toPlainString()
				.replaceAll("\\.", ","));
		
		this.ftfQtdest.setText(aux.getQtdest().toPlainString()
				.replaceAll("\\.", ","));
		
		if(aux.getStatus() != null) {
			if(aux.getStatus().equals("A")) {
				this.cbStatus.setSelectedIndex(1);
			}else if(aux.getStatus().equals("I")) {
				this.cbStatus.setSelectedIndex(2);
			}
		}
		
	}
}







