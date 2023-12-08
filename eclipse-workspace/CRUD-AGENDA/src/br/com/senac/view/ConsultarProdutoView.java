package br.com.senac.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
//import br.com.senac.service.IService;
//import br.com.senac.service.Service;
//import br.com.senac.vo.ClienteVO;
//import br.com.senac.vo.ProdutoVO;
//import br.com.senac.vo.StatusEnum;
import br.com.senac.vo.StatusEnum;

public class ConsultarProdutoView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private JFormattedTextField ftfCodigo;
	private JFormattedTextField ftfDescricao;
	private JFormattedTextField ftfCodbar;
	private JComboBox cbStatus;
	private TableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarProdutoView frame = new ConsultarProdutoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultarProdutoView() {
		setTitle("Manutenção de produtos");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(10, 11, 559, 115);
		contentPane.add(panel);
		panel.setLayout(null);
		
		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setBounds(10, 32, 96, 20);
		panel.add(ftfCodigo);
		
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 11, 96, 14);
		panel.add(lblCodigo);
		
		ftfDescricao = new JFormattedTextField();
		ftfDescricao.setBounds(116, 32, 280, 20);
		panel.add(ftfDescricao);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(116, 11, 96, 14);
		panel.add(lblDescricao);
		
		ftfCodbar = new JFormattedTextField();
		ftfCodbar.setBounds(406, 32, 141, 20);
		panel.add(ftfCodbar);
		
		JLabel lblCodbar = new JLabel("Código barras");
		lblCodbar.setBounds(407, 11, 89, 14);
		panel.add(lblCodbar);
		
		
		
		
		
		
		cbStatus = new JComboBox();
		DefaultComboBoxModel defaultComboBoxModel 
			= new DefaultComboBoxModel(StatusEnum.values());
		cbStatus.setModel(defaultComboBoxModel);
		defaultComboBoxModel.insertElementAt(null, 0);
		cbStatus.setSelectedIndex(0);
		
		
		
		
		
		
		cbStatus.setBounds(10, 82, 96, 22);
		panel.add(cbStatus);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 63, 96, 14);
		panel.add(lblStatus);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(418, 82, 129, 23);
		panel.add(btnPesquisar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaEdicao();
			}
		});
		btnAdicionar.setBounds(10, 137, 107, 23);
		contentPane.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarRegistro();
			}
		});
		btnEditar.setBounds(127, 137, 107, 23);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(244, 137, 107, 23);
		contentPane.add(btnExcluir);
		
		JButton btnRelatorio = new JButton("Relatório");
		btnRelatorio.setBounds(440, 137, 122, 23);
		contentPane.add(btnRelatorio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 552, 226);
		contentPane.add(scrollPane);
		
		//Diz que sempre deve haver barra de rolagem vertical.
		scrollPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Qtd. Estoque");
		tableModel.addColumn("Status");
		tableModel.addColumn("Vlr. Compra");
		tableModel.addColumn("Vlr. Venda");
		
		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(1).setPreferredWidth(180);
		tcm.getColumn(2).setPreferredWidth(110);
		tcm.getColumn(3).setPreferredWidth(100);
		tcm.getColumn(4).setPreferredWidth(80);
		tcm.getColumn(5).setPreferredWidth(80);
		
		
		scrollPane.setViewportView(table);
	}
	
	protected void editarRegistro() {
		
		if(table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(
				this, 
				"É necessário selecionar um registro!",
				"Mensagem de aviso",
				JOptionPane.WARNING_MESSAGE);
		}else {
			
			try {
				
				ProdutoView pv = new ProdutoView();
				ProdutoVO aux = (ProdutoVO)tableModel.getRows()
						.get(table.getSelectedRow()).getElement();
				
				pv.editar(aux);
				pv.setVisible(true);
				
			}catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(
						null, 
						"Ocorreu um erro",
						"Erro",
						JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}

	protected void abrirTelaEdicao() {
		
		try {
			ProdutoView produtoView = new ProdutoView();
			produtoView.setVisible(true);
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(
				this, 
				"Ocorreu um erro ao realizar a operação!",
				"Mensagem de erro",
				JOptionPane.ERROR_MESSAGE);
		}
		
	}

	protected void excluir() {
		
		if(table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, 
				"É necessário selecionar um registro!",
				"Mensagem de aviso",
				JOptionPane.WARNING_MESSAGE);
		}else {
			
			Object[] options = {"Sim!", "Não"};
			int n = JOptionPane.showOptionDialog(
					this,
					"Deseja realmente excluir o registro?", 
					"Confirmação", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[1]);
			
			if(n == 0) {
				ProdutoVO produto = (ProdutoVO)tableModel.getRows()
					.get(table.getSelectedRow()).getElement();
				
				Service servico = new Service();
				try {
					servico.excluir(produto);
					
					JOptionPane.showMessageDialog(
						this, 
						"Registro excluído com sucesso!",
						"Mensagem de aviso",
						JOptionPane.INFORMATION_MESSAGE);
					
					pesquisar();
					
				} catch (BOValidationException e) {					
					e.printStackTrace();
					JOptionPane.showMessageDialog(
						this, 
						e.getMessage(),
						"Mensagem de aviso",
						JOptionPane.WARNING_MESSAGE);
				} catch (BOException e) {					
					e.printStackTrace();
					JOptionPane.showMessageDialog(
							this, 
							"Ocorreu um erro ao realizar a operação!",
							"Mensagem de erro",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}
		
		
	}

	protected void pesquisar() {
		
		tableModel.clearTable();
		
		BigInteger id = null;
		String descri = null;
		String status = null;
		String codbar = null;
		
		try {
		
			if(this.ftfCodigo.getText() != null && 
					this.ftfCodigo.getText().trim().length() > 0) {
				try {
					id = new BigInteger(ftfCodigo.getText().trim());
				}catch (Exception e) {				
		throw new BOValidationException("Código: erro de validação: "
				+ "valor inválido.");
				}
			}
			
			if(this.ftfDescricao.getText() != null && 
					this.ftfDescricao.getText().trim().length() > 0) {
				descri = ftfDescricao.getText().trim();
			}
			
			if(this.ftfCodbar.getText() != null && 
					this.ftfCodbar.getText().trim().length() > 0) {
				codbar = ftfCodbar.getText().trim();
			}
			
			if(cbStatus.getSelectedItem() != null) {
				StatusEnum se = (StatusEnum)this.cbStatus.getSelectedItem();
				status = se.name();
			}
			
			IService service = new Service();
			ClienteVO c1 = new ClienteVO(BigInteger.ONE);			
			List<ProdutoVO> produtos = service.listarProduto(
					c1, id, 
					descri, status, codbar, 
					Integer.MAX_VALUE, 
					0);
			DecimalFormat df = new DecimalFormat("#,##0.00");
			DecimalFormat dfQtd = new DecimalFormat("###,##0.000");
			for(ProdutoVO produtoVO : produtos) {
				
				RowData rowData = new RowData();
				
				rowData.getValues().put(0, produtoVO.getId().toString());
				rowData.getValues().put(1, produtoVO.getDescri());
				rowData.getValues().put(2, dfQtd.format(produtoVO.getQtdest()));
				
				if(produtoVO.getStatus().equals("A")) {
					rowData.getValues().put(3, "Ativo");
				}else if(produtoVO.getStatus().equals("I")) {
					rowData.getValues().put(3, "Inativo");
				}
				rowData.getValues().put(4, df.format(produtoVO.getValcom()));
				rowData.getValues().put(5, df.format(produtoVO.getValven()));
				rowData.setElement(produtoVO);
				tableModel.addRow(rowData);
			}
			
		}catch (BOValidationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(),
				"Erro de validação.", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Erro de sistema.", JOptionPane.ERROR_MESSAGE);
		}
			
		
	}
}
