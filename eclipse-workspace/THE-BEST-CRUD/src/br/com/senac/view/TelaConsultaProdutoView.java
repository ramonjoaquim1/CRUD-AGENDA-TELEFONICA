package br.com.senac.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.Service;
import br.com.senac.vo.StatusEnum;

public class TelaConsultaProdutoView extends JFrame {

	private JPanel contentPane;
	private JTextField tfDescricao;
	private JTextField tfCodBar;
	private JTable table;
	private JComboBox cbStatus;
	private TelaConsultaProdutoView tableModel;
	private JFormattedTextField ftfCodigo;
	
	private JComboBox cbQuantidade;
	private JLabel lblPaginacao;
	
	private Integer paginaAtual = 1;
	private Integer totalRegistros;
	private Integer tamanhoPaginacao = 5;

	
	/**
	 * Create the frame.
	 */
	public TelaConsultaProdutoView() {
//		setResizable(false);
//		setTitle("Manutenção de produto");
//		setBounds(100, 100, 672, 396);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JPanel panel = new JPanel();
//		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel.setBounds(10, 11, 635, 104);
//		contentPane.add(panel);
//		panel.setLayout(null);
//		
//		JButton btnPesquisar = new JButton("Pesquisar");
//		btnPesquisar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				paginaAtual = 1;
//				pesquisar();
//			}
//		});
//		btnPesquisar.setIcon(new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/pesquisar.png")));
//		btnPesquisar.setBounds(487, 70, 136, 23);
//		panel.add(btnPesquisar);
//		
//		JButton btnLimpar = new JButton("Limpar");
//		btnLimpar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				limparPesquisa();
//			}
//		});
//		btnLimpar.setIcon(new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/data-cleaning.png")));
//		btnLimpar.setBounds(328, 70, 149, 23);
//		panel.add(btnLimpar);
//		
//		JLabel lblCodigo = new JLabel("Código");
//		lblCodigo.setBounds(10, 11, 71, 14);
//		panel.add(lblCodigo);
//		
//		ftfCodigo = new JFormattedTextField();
//		String mascaraCodigo = "##########";
//		MascaraJFormattedTextField.formatField(mascaraCodigo, ftfCodigo);
//		ftfCodigo.setFocusLostBehavior(JFormattedTextField.PERSIST);
//		
//		ftfCodigo.setBounds(99, 8, 123, 20);
//		panel.add(ftfCodigo);
//		
//		JLabel lblDescricao = new JLabel("Descrição");
//		lblDescricao.setBounds(240, 11, 107, 14);
//		panel.add(lblDescricao);
//		
//		tfDescricao = new JTextField();
//		tfDescricao.setBounds(365, 8, 258, 20);
//		panel.add(tfDescricao);
//		tfDescricao.setColumns(10);
//		
//		JLabel lblStatus = new JLabel("Status");
//		lblStatus.setBounds(10, 36, 71, 14);
//		panel.add(lblStatus);
//		
//		cbStatus = new JComboBox();
//		DefaultComboBoxModel dcbm = 
//				new DefaultComboBoxModel<>(StatusEnum.values());
//		cbStatus.setModel(dcbm);
//		dcbm.insertElementAt(null, 0);
//		cbStatus.setSelectedIndex(0);
//		
//		
//		cbStatus.setBounds(99, 33, 123, 22);
//		panel.add(cbStatus);
//		
//		JLabel lblCodBar = new JLabel("Cód. barras");
//		lblCodBar.setBounds(240, 36, 107, 14);
//		panel.add(lblCodBar);
//		
//		tfCodBar = new JTextField();
//		tfCodBar.setBounds(365, 33, 258, 20);
//		panel.add(tfCodBar);
//		tfCodBar.setColumns(10);
//		
//		JButton btnFechar = new JButton("Fechar");
//		btnFechar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				fecharJanela();
//			}
//		});
//		btnFechar.setIcon(new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/cancel.png")));
//		btnFechar.setBounds(470, 324, 175, 25);
//		contentPane.add(btnFechar);
//		
//		JButton btnAdicionar = new JButton("Adicionar");
//		btnAdicionar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				abrirTelaEdicaoProduto();
//			}
//		});
//		btnAdicionar.setIcon(new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/adicionar.png")));
//		btnAdicionar.setBounds(10, 118, 139, 23);
//		contentPane.add(btnAdicionar);
//		
//		JButton btnEditar = new JButton("Editar");
//		btnEditar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				editarRegistro();
//			}
//		});
//		btnEditar.setIcon(new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/editar.png")));
//		btnEditar.setBounds(161, 118, 137, 23);
//		contentPane.add(btnEditar);
//		
//		JButton btnExcluir = new JButton("Excluir");
//		btnExcluir.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				excluirRegistro();
//			}
//		});
//		btnExcluir.setIcon(new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/Fechar.png")));
//		btnExcluir.setBounds(310, 118, 139, 23);
//		contentPane.add(btnExcluir);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setVerticalScrollBarPolicy(
//				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		
//		scrollPane.setHorizontalScrollBarPolicy(
//				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		
//		scrollPane.setBounds(10, 152, 635, 160);
//		contentPane.add(scrollPane);
//		
//		tableModel = new TableModel();
//		tableModel.addColumn("Código");
//		tableModel.addColumn("Descrição");
//		tableModel.addColumn("Qtd. Estoque");
//		tableModel.addColumn("Status");
//		tableModel.addColumn("Vlr. compra");
//		tableModel.addColumn("Vlr. venda");
//		
//		table = new JTable(tableModel);
//		table.setAutoscrolls(true);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		
//		TableColumnModel tableColumnModel = table.getColumnModel();
//		tableColumnModel.getColumn(0).setPreferredWidth(100);
//		tableColumnModel.getColumn(1).setPreferredWidth(180);
//		tableColumnModel.getColumn(2).setPreferredWidth(110);
//		tableColumnModel.getColumn(3).setPreferredWidth(100);
//		tableColumnModel.getColumn(4).setPreferredWidth(80);
//		tableColumnModel.getColumn(5).setPreferredWidth(80);
//		
//		scrollPane.setViewportView(table);
//		
//		JButton btAnterior = new JButton("<<");
//		btAnterior.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				paginarPaginaAnterior();
//			}
//		});
//		btAnterior.setBounds(32, 324, 60, 25);
//		contentPane.add(btAnterior);
//		
//		JButton btProximo = new JButton(">>");
//		btProximo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				paginarProximaPagina();
//			}
//		});
//		btProximo.setBounds(244, 324, 54, 25);
//		contentPane.add(btProximo);
//		
//		lblPaginacao = new JLabel("New label");
//		lblPaginacao.setHorizontalAlignment(SwingConstants.CENTER);
//		lblPaginacao.setBounds(97, 329, 145, 15);
//		contentPane.add(lblPaginacao);
//		
//		cbQuantidade = new JComboBox();
//		cbQuantidade.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				pesquisar();
//			}
//		});
//		cbQuantidade.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "20", "30", "50", "100"}));
//		cbQuantidade.setBounds(312, 324, 81, 24);
//		contentPane.add(cbQuantidade);
//		
//		JButton btnImprimir = new JButton("Relatório");
//		btnImprimir.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				imprimirRelatorio();
//			}
//		});
//		btnImprimir.setIcon(new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/printer.png")));
//		btnImprimir.setBounds(490, 118, 155, 25);
//		contentPane.add(btnImprimir);
//		
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		this.setLocation(dim.width / 2 - this.getSize().width /2, 
//				dim.height / 2 - this.getSize().height / 2);
//		
//		pesquisar();
	}
	
	private void imprimirRelatorio() {
		
//		FileFilter filter = new FileNameExtensionFilter("PDF File", "pdf");
//
//		JFileChooser file = new JFileChooser();
//		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		file.setMultiSelectionEnabled(false);
//		file.setFileFilter(filter);
//
//		int i = file.showSaveDialog(null);
//
//		if (i == JFileChooser.APPROVE_OPTION) {
//
//			File arquivo = file.getSelectedFile();
//			
//			if(arquivo.getName().endsWith(".pdf") == false && arquivo.getName().endsWith(".PDF") ==  false) {
//				arquivo = new File(arquivo.getAbsolutePath() + ".pdf");
//			}
//			
//			Service servico = new Service();
//			try {
//				
//				servico.imprimirRelatorioProdutos(DadosConstantesVO.getClienteVO(), arquivo);
//				
//				JOptionPane.showMessageDialog(this, 
//						"Operação realizada com sucesso!",
//						"Mensagem de aviso", 
//						JOptionPane.INFORMATION_MESSAGE);
//				
//			} catch (BOValidationException e) {
//				e.printStackTrace();
//				JOptionPane.showMessageDialog(this, e.getMessage(),
//						"Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
//			} catch (BOException e) {			
//				e.printStackTrace();
//				JOptionPane.showMessageDialog(null, "Ocorreu um erro "
//						+ "ao realizar a operação.", "Erro", 
//						JOptionPane.ERROR_MESSAGE);
//			}
//			
//		}
//		
	}
	
	private void paginarPaginaAnterior() {
		
		if(this.paginaAtual > 1) {
			this.paginaAtual--;
			pesquisar();
		}
		
	}
	
	private void paginarProximaPagina() {
		
		if(this.paginaAtual < ((int)(totalRegistros / this.tamanhoPaginacao))) {
			this.paginaAtual++;
			pesquisar();
		}
		
	}
	
	public void editarRegistro() {
		
//		if(table.getSelectedRow() < 0) {
//			JOptionPane.showMessageDialog(this, 
//				"É necessário selecionar um registro!", 
//				"Mensagem de aviso",
//				JOptionPane.WARNING_MESSAGE);
//		}else {
//			
//			try {
//				
//				TelaProdutoView tpv = new TelaProdutoView();
//				ProdutoVO aux = (ProdutoVO)tableModel.getRows()
//						.get(table.getSelectedRow()).getElement();
//				//TODO Nesse ponto deveria buscar o produto por ID novamente.
//				tpv.editar(aux);
//				tpv.setVisible(true);
//				
//			}catch (BOException e) {
//				JOptionPane.showMessageDialog(null, "Ocorreu um erro "
//						+ "ao realizar a operação.", "Erro", 
//						JOptionPane.ERROR_MESSAGE);
//			}
//			
//		}
		
	}
	
	public void excluirRegistro() {
//		
//		//Se alguma linha foi selecionada.
//		if(table.getSelectedRow() < 0) {
//			JOptionPane.showMessageDialog(this, "É necessário selecionar um "
//					+ "registro!", "Mensagem de aviso",
//					JOptionPane.WARNING_MESSAGE);			
//		}else {
//			Object[] options = {"Sim!", "Não"};
//			int n = JOptionPane.showOptionDialog(null, 
//					"Deseja realmente excluir o registro?", 
//					"Confirmação", 
//					JOptionPane.YES_NO_OPTION, 
//					JOptionPane.QUESTION_MESSAGE,
//					null, 
//					options, 
//					options[1]);
//			
//			if(n == 0) {
//				ProdutoVO produto = (ProdutoVO)tableModel.getRows()
//						.get(table.getSelectedRow()).getElement();
//				try {
//					Service servico = new Service();
//					servico.excluirProduto(produto);
//					
//					JOptionPane.showMessageDialog(this, 
//							"Registro excluído com sucesso!",
//							"Mensagem de aviso", 
//							JOptionPane.INFORMATION_MESSAGE);
//					
//					pesquisar();
//				}catch (BOException e) {
//					e.printStackTrace();
//					JOptionPane.showMessageDialog(this, e.getMessage(),
//							"Ocorreu um erro ao executar a operação!", 
//							JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		}
		
	}
	
	public void limparPesquisa() {
		paginaAtual = 1;
		this.tfCodBar.setText(null);
		this.tfDescricao.setText(null);
		this.cbStatus.setSelectedIndex(0);
		this.ftfCodigo.setText(null);
		pesquisar();
	}
	
	
	/**
	 * Realiza a consulta e carrega os dados na tela.
	 */
	public void pesquisar() {
//		
//		Service service = new Service();
//		TableModel tableModel = (TableModel) table.getModel();
//		tableModel.clearTable();
//		
//		try {
//			
//			Map<String, Object> filters = new HashMap<>();
//			
//			if(this.ftfCodigo.getText() != null && 
//				this.ftfCodigo.getText().trim().length() > 0) {
//				try {
//					
//					BigInteger id = new BigInteger(this.ftfCodigo.getText().trim());
//					filters.put("id", id);					
//				}catch (Exception e) {
//					throw new 
//						BOValidationException("Código: Erro de validação: "
//								+ "Valor incorreto.");
//				}
//			}
//			
//			if(tfDescricao.getText() != null && 
//				tfDescricao.getText().trim().length() > 0) {				
//				filters.put("descri", tfDescricao.getText().trim());
//			}
//			
//			if(cbStatus.getSelectedItem() != null) {
//				StatusEnum statusEnum = 
//					(StatusEnum)this.cbStatus.getSelectedItem();
//				filters.put("status", statusEnum.name());
//			}
//			
//			if(tfCodBar.getText() != null &&
//				tfCodBar.getText().trim().length() > 0) {
//				filters.put("codbar", tfCodBar.getText().trim());
//			}
//			
//			this.tamanhoPaginacao = Integer.parseInt((String)cbQuantidade.getSelectedItem());
//			
//			this.totalRegistros = service.consultarProdutoCount(filters, DadosConstantesVO.getClienteVO());
//			
//			this.lblPaginacao.setText("Página " + this.paginaAtual + " de " + ((int)(totalRegistros / this.tamanhoPaginacao)) );
//			
//			List<ProdutoVO> produtoVOs = service
//				.consultarProduto(
//						((this.paginaAtual * this.tamanhoPaginacao) - this.tamanhoPaginacao), 
//						this.tamanhoPaginacao, 
//						null, 
//						null, 
//						filters, 
//						DadosConstantesVO.getClienteVO());
//			
//			if(produtoVOs != null) {
//				DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
//				DecimalFormat decimalFormatQtd = 
//						new DecimalFormat("###,##0.000");
//				
//				for (ProdutoVO produtoVO : produtoVOs) {
//					RowData rowData = new RowData();
//					rowData.getValues().put(0, produtoVO.getId().toString());
//					rowData.getValues().put(1, produtoVO.getDescri());
//					
//					if(produtoVO.getQtdest() != null) {
//						rowData.getValues().put(2, 
//								decimalFormatQtd.format(produtoVO.getQtdest()));
//					}
//					if(produtoVO.getStatus().equals("A")) {
//						rowData.getValues().put(3, "Ativo");
//					}else if(produtoVO.getStatus().equals("I")) {
//						rowData.getValues().put(3, "Inativo");
//					}
//					
//					if(produtoVO.getValcom() != null) {
//						rowData.getValues().put(4, decimalFormat
//								.format(produtoVO.getValcom()));
//					}
//					
//					if(produtoVO.getValven() != null) {
//						rowData.getValues().put(5, decimalFormat
//								.format(produtoVO.getValven()));
//					}
//					
//					rowData.setElement(produtoVO);
//					tableModel.addRow(rowData);
//				}
//			}
//			
//		}catch (BOValidationException e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(this, e.getMessage(),
//					"Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
//		}catch (BOException e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(this, e.getMessage(),
//					"Ocorreu um erro ao executar a operação!", 
//					JOptionPane.ERROR_MESSAGE);
//		}		
}
//	
	public void fecharJanela() {
		this.setVisible(false);
	}
	
//	public void abrirTelaEdicaoProduto() {
//		TelaProdutoView tpv = new TelaProdutoView();
//		tpv.setVisible(true);
//	}
}
