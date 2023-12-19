package br.com.senac.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.view.*;
import br.com.senac.service.IService;
import br.com.senac.service.Service;
import br.com.senac.vo.PessoaVO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CadastroPessoaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	private JFormattedTextField ftfNome;
	private TelaPrincipalView telaPrincipal;
	private Object ftfCodigo;
    private IService service;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPessoaView frame = new CadastroPessoaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
	}
	

	public CadastroPessoaView() {
		this.service = new Service();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		

		table = new JTable();
		tableModel = new TableModel();
		table.setModel(tableModel);

		setTitle("Cadastro de pessoas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBounds(10, 21, 635, 79);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 33, 54, 13);
		panel.add(lblNome);

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(67, 30, 404, 19);
		panel.add(ftfNome);
		
		JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/pesquisar.png")));
        btnPesquisar.addActionListener(e -> pesquisar());
        btnPesquisar.setMnemonic('P');
        btnPesquisar.setBounds(483, 26, 140, 26);
        panel.add(btnPesquisar);
        
        		JLabel lbl_close = new JLabel("X");
        		lbl_close.setBounds(598, -23, 37, 27);
        		panel.add(lbl_close);
        		lbl_close.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
              				System.exit(0);
        			}
        		});
        		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
        		lbl_close.setForeground(new Color(241, 57, 83));
        		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
        

		JButton btnAdcionar = new JButton("Adicionar");
		btnAdcionar.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/adicionar.png")));
		btnAdcionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaEdicao();

			}
		});
		btnAdcionar.setMnemonic('A');
		btnAdcionar.setBounds(20, 112, 134, 34);
		contentPane.add(btnAdcionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/editar.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarRegistro();
			}
		});
		btnEditar.setBounds(153, 112, 134, 34);
		contentPane.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/apagar.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(286, 112, 134, 34);
		contentPane.add(btnExcluir);
		 
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 171, 652, 207);
		contentPane.add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Data");
		tableModel.addColumn("Observação");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(1).setPreferredWidth(180);
		tcm.getColumn(2).setPreferredWidth(120);
		tcm.getColumn(3).setPreferredWidth(180);

		scrollPane.setViewportView(table);
		
		JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltar());
        btnVoltar.setMnemonic('V');
        btnVoltar.setBounds(566, 112, 96, 34);
        contentPane.add(btnVoltar);
        
        JButton btnNumeros = new JButton("Ver no Contatos");
        btnNumeros.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		listarNumeros();
        	}
        });
        btnNumeros.setBounds(420, 112, 149, 34);
        contentPane.add(btnNumeros);

	}
	
	protected void listarNumeros() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "Favor selecionar um registro! ", "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		} else {
			
			PessoaVO contato = (PessoaVO) tableModel.getRows().get(table.getSelectedRow()).getElement();
			
			CadastroContatoView ccv = new CadastroContatoView(contato);
			ccv.setarContat(contato);
			ccv.setVisible(true);
			
		}
	}

	 
	protected void voltar() {
	        this.setVisible(false);
	        this.dispose();
	    }
	protected void excluir() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "Favor selecionar um registro! ", "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		} else {

			Object[] options = { "Sim!", "Não" };
			int n = JOptionPane.showOptionDialog(this, "Você Deseja exluir o registro? ", "Confirmação", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				PessoaVO contato = (PessoaVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				Service service = new Service();

				try {
					service.excluir(contato);

					JOptionPane.showMessageDialog(this, "O registro foi exluido com sucesso!", "Mensagem de aviso",
							JOptionPane.INFORMATION_MESSAGE);

					pesquisar();

				} catch (BOValidationException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso",
							JOptionPane.WARNING_MESSAGE);

				} catch (BOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Ocorreu um erro!", "Mensagem de erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	private void editarRegistro() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "Selecione um registro!", "Mensagem de aviso.",
					JOptionPane.WARNING_MESSAGE);
		} else {

			try {

				EditarPessoaView edt = new EditarPessoaView();
				PessoaVO aux = (PessoaVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				edt.editar(aux);
				edt.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocooreu um erro", "Erro.", JOptionPane.WARNING_MESSAGE);
			}
		}

	}
	   
	private void adicionarRegistro() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "Selecione um registro!", "Mensagem de aviso.",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				AdicionarPessoaView add = new AdicionarPessoaView();
				PessoaVO aux1 = (PessoaVO) tableModel.getRows().get(table.getSelectedRow()).getElement();
				add.editar(aux1);
				add.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocooreu um erro", "Erro.", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void pesquisar() {

		if (tableModel != null) {
			tableModel.clearTable();
		}
		BigInteger id = null;
		String nome = null;
		Date datnas = null;
		String observ = null;

		try {

			if (this.ftfNome.getText() != null && ftfNome.getText().trim().length() > 0) {
				nome = ftfNome.getText().trim();
			}

			

			System.out.println("********************Iniciando consulta de pessoa**********************");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<PessoaVO> criteria = cb.createQuery(PessoaVO.class);

			// Clausula from
			Root<PessoaVO> contatosFrom = criteria.from(PessoaVO.class);
			criteria.select(contatosFrom);

			if (id != null) {
				criteria.where(cb.equal(contatosFrom.get("id"), id));
			}

			if (nome != null) {
				String searchTerm = "%" + nome.toLowerCase() + "%";
				criteria.where(cb.like(cb.lower(contatosFrom.get("nome")), searchTerm));
			}
			
			
			TypedQuery<PessoaVO> query = em.createQuery(criteria);
			Order contatoOrderBy = cb.asc(contatosFrom.get("nome"));
			criteria.orderBy(contatoOrderBy);
			

			List<PessoaVO> listaContat = query.getResultList();

			IService service = new Service();

			System.out.println(listaContat);
			Collections.sort(listaContat, (contato1, contato2) -> contato1.getNome().compareTo(contato2.getNome()));

			for (PessoaVO contatoVO : listaContat) {
				RowData rowData = new RowData();
				rowData.getValues().put(0, contatoVO.getId().toString());
				rowData.getValues().put(1, contatoVO.getNome());
				rowData.getValues().put(2, contatoVO.getDatnas());
				rowData.getValues().put(3, contatoVO.getObserv());

				System.out.println();

				rowData.setElement(contatoVO);
				tableModel.addRow(rowData);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		}
	}
//
	private void abrirTelaAdicionar() {
		try {
			AdicionarPessoaView adicionarPessoa = new AdicionarPessoaView();
			adicionarPessoa.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de aviso.",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void abrirTelaEdicao() {
		try {
			AdicionarPessoaView adicionarPessoa = new AdicionarPessoaView();
			adicionarPessoa.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de aviso.",
					JOptionPane.ERROR_MESSAGE);
			}
	}
	protected void abrirTelaAcessos() {
		  if (telaPrincipal == null) {
			  telaPrincipal = new TelaPrincipalView();
	        }
		  telaPrincipal.setVisible(true);
	    }
	
	private void handleException(String message, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, message, "Mensagem de aviso.", JOptionPane.ERROR_MESSAGE);
    }
}
