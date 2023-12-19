package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.IService;
import br.com.senac.service.Service;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.PessoaVO;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.event.ActionEvent;

public class CadastroContatoView extends JFrame {

    private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    private TableModel tableModel;
    private static PessoaVO contato;
    private IService service;
    private PessoaVO pessoaSelecionada;
	private Object ftfNumero;
	private Object ftfDddnum;
	private Object ftfEmails; 
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CadastroContatoView frame = new CadastroContatoView(contato);//lembra aqui qualquer coisa mudar
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CadastroContatoView(PessoaVO pessoaSelecionada) {
    	 this.service = new Service();
    	 this.pessoaSelecionada = pessoaSelecionada;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        table = new JTable();
        tableModel = new TableModel();
        table.setModel(tableModel);

        setTitle("Cadastro de contatos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 669, 428);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(192, 192, 192)));
        panel.setBounds(10, 21, 635, 35);
        contentPane.add(panel);
        panel.setLayout(null);
                
                JButton btnAtualizar = new JButton("Atualizar");
                btnAtualizar.setBounds(244, 0, 134, 34);
                panel.add(btnAtualizar);
                btnAtualizar.addActionListener(new ActionListener() {
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		atualizarTabelaContatos();
                	}
                     });
                            
                                    JButton btnExcluir = new JButton("Excluir");
                                    btnExcluir.setBounds(376, 0, 134, 34);
                                    panel.add(btnExcluir);
                                    btnExcluir.setIcon(new ImageIcon(CadastroContatoView.class.getResource("/br/com/senac/view/img/apagar.png")));
                                    
                                        
JButton btnEditar = new JButton("Editar");
btnEditar.setBounds(125, 0, 121, 34);
panel.add(btnEditar);
btnEditar.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	editarRegistro();
	}
     });
btnEditar.setIcon(new ImageIcon(CadastroContatoView.class.getResource("/br/com/senac/view/img/editar.png")));                                                                                                   
		
        JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(0, 0, 134, 34);                                                      
		panel.add(btnAdicionar);                               
		btnAdicionar.setIcon(new ImageIcon(CadastroContatoView.class.getResource("/br/com/senac/view/img/adicionar.png")));                                                      
				
		JButton btnVoltar = new JButton("Voltar");
				btnVoltar.setBounds(501, 0, 134, 34);                                                           
				panel.add(btnVoltar);                                                                                                                                                                      
				btnVoltar.addActionListener(e -> voltar());
                                                                
btnVoltar.setMnemonic('V');
	btnAdicionar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			adicionarContato();}
		});
					btnAdicionar.addActionListener(e -> adicionarContato());
					btnEditar.addActionListener(e -> editarContato());
					btnExcluir.addActionListener(e -> excluirContato());
					btnAtualizar.addActionListener(e -> atualizarTabelaContatos());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 76, 635, 310);
        contentPane.add(scrollPane);

        tableModel = new TableModel();
        tableModel.addColumn("Código Pessoa");
        tableModel.addColumn("Número");
        tableModel.addColumn("DDD");
        tableModel.addColumn("E-mails");

        table = new JTable(tableModel);
        table.setAutoscrolls(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(1).setPreferredWidth(120);
        tcm.getColumn(2).setPreferredWidth(80);
        tcm.getColumn(3).setPreferredWidth(200);

        scrollPane.setViewportView(table);
    }
    protected void voltar() {
        this.setVisible(false);
        this.dispose();
    }
    

    private void adicionarContato() {
        try {
            // Abre a janela de cadastro de contato e passa a pessoa selecionada
            AdicionarContatoView cadastrarContato = new AdicionarContatoView(this, pessoaSelecionada);
            cadastrarContato.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de aviso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private void editarRegistro() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "Selecione um registro!", "Mensagem de aviso.",
					JOptionPane.WARNING_MESSAGE);
		} else {

			try {

				EditarContatoView edt = new EditarContatoView();
				ContatoVO aux = (ContatoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				edt.editar(aux);
				edt.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocooreu um erro", "Erro.", JOptionPane.WARNING_MESSAGE);
			}
		}

	}
    
    public void atualizarTabelaContatos() {
        if (tableModel != null) {
            tableModel.clearTable();
        }

        String numero = null;
        String dddnum = null;
        String emails = null;

        try {
        	if (ftfNumero instanceof JFormattedTextField) {
                numero = ((JFormattedTextField) ftfNumero).getText().trim();
            }

            if (ftfDddnum instanceof JFormattedTextField) {
                dddnum = ((JFormattedTextField) ftfDddnum).getText().trim();
            }

            if (ftfEmails instanceof JFormattedTextField) {
                emails = ((JFormattedTextField) ftfEmails).getText().trim();
            }

            EntityManager em = HibernateUtil.getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);

            Root<ContatoVO> contatosFrom = criteria.from(ContatoVO.class);
            criteria.select(contatosFrom);

            Predicate predicate = cb.conjunction();

            if (numero != null) {
                String searchTerm = "%" + numero.toLowerCase() + "%";
                predicate = cb.and(predicate, cb.like(cb.lower(contatosFrom.get("numero")), searchTerm));
            }

            if (dddnum != null) {
                predicate = cb.and(predicate, cb.equal(contatosFrom.get("dddnum"), dddnum));
            }

            if (emails != null) {
                predicate = cb.and(predicate, cb.like(cb.lower(contatosFrom.get("emails")), emails));
            }

            criteria.where(predicate);

            TypedQuery<ContatoVO> query = em.createQuery(criteria);
            List<ContatoVO> listaContatos = query.getResultList();

            System.out.println(listaContatos);
            Collections.sort(listaContatos, Comparator.comparing(ContatoVO::getNumero));

            for (ContatoVO contatoVO : listaContatos) {
                RowData rowData = new RowData();
                rowData.getValues().put(0, contatoVO.getId().toString());
                rowData.getValues().put(1, contatoVO.getNumero());
                rowData.getValues().put(2, contatoVO.getDddnum());
                rowData.getValues().put(3, contatoVO.getEmails());

                System.out.println();

                rowData.setElement(contatoVO);
                tableModel.addRow(rowData);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    
//    public void atualizarTabelaContatos() {
//        try {
//            List<ContatoVO> contatos = service.listarContato(pessoaSelecionada, null, null, null, null);
//            // Limpa a tabela
//            tableModel.clear();
//
//            // Adiciona os contatos à tabela
//            for (ContatoVO contato : contatos) {
//                Object[] rowData = { contato.getId(), contato.getNumero(), contato.getDddnum(), contato.getEmails() };
//                tableModel.addRow(rowData);
//            }
//
//        } catch (Exception e) {
//            handleException("Erro ao atualizar a tabela de contatos", e);
//        }
//    }

    private void editarContato() {
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um contato!", "Mensagem de aviso.",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            try {
            	EditarContatoView ect = new EditarContatoView();
				ContatoVO aux = (ContatoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

//				edt.editar(aux);
//				edt.setVisible(true);
            } catch (Exception e) {
                handleException("Erro ao editar contato", e);
            }
        }
    }

    private void excluirContato() {
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um contato!", "Mensagem de aviso.",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                // Implemente aqui a lógica para excluir um contato
                // Utilize tableModel.getRows().get(table.getSelectedRow()) para obter o contato selecionado
                // Atualize a tabela removendo o contato após a exclusão
            } catch (Exception e) {
                handleException("Erro ao excluir contato", e);
            }
        }
    }

    private void voltarTelaAcessos() {
        // Implemente aqui a lógica para voltar para a tela de acessos
        // Exemplo: dispose(); // fecha a janela atual
    }

    private void handleException(String message, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, message, "Mensagem de aviso.", JOptionPane.ERROR_MESSAGE);
    }

	public void setarContat(PessoaVO contato) {
		this.contato = contato;
		
	}
}
