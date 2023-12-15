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
import java.util.Collections;
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

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(0, 0, 134, 34);
        panel.add(btnAdicionar);
        btnAdicionar.setIcon(new ImageIcon(CadastroContatoView.class.getResource("/br/com/senac/view/img/adicionar.png")));btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarContato();
            }
        });
    
        
                JButton btnEditar = new JButton("Editar");
                btnEditar.setBounds(172, 0, 134, 34);
                panel.add(btnEditar);
                btnEditar.setIcon(new ImageIcon(CadastroContatoView.class.getResource("/br/com/senac/view/img/editar.png")));
                
                        JButton btnExcluir = new JButton("Excluir");
                        btnExcluir.setBounds(342, 0, 134, 34);
                        panel.add(btnExcluir);
                        btnExcluir.setIcon(new ImageIcon(CadastroContatoView.class.getResource("/br/com/senac/view/img/apagar.png")));
                        
                                JButton btnVoltar = new JButton("Voltar");
                                btnVoltar.setBounds(539, 0, 96, 34);
                                panel.add(btnVoltar);
                                btnVoltar.addActionListener(e -> voltar());
                                btnVoltar.setMnemonic('V');
                        btnExcluir.addActionListener(e -> excluirContato());
                btnEditar.addActionListener(e -> editarContato());
        btnAdicionar.addActionListener(e -> adicionarContato());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 68, 612, 310);
        contentPane.add(scrollPane);

        tableModel = new TableModel();
        tableModel.addColumn("ID");
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
//            AdicionarContatoView cadastrarContato = new AdicionarContatoView();
//            cadastrarContato.setVisible(true);
            AdicionarContatoView cadastrarContato = new AdicionarContatoView();
            cadastrarContato.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de aviso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void atualizarTabelaContatos() {
        try {
            List<ContatoVO> contatos = service.listarContato(null, null, null, null, null);

            // Limpa a tabela
            tableModel.clear();

            // Adiciona os contatos à tabela
            for (ContatoVO contato : contatos) {
                Object[] rowData = { contato.getId(), contato.getNumero(), contato.getDddnum(), contato.getEmails() };
                tableModel.addRow(rowData);
            }

        } catch (Exception e) {
            handleException("Erro ao atualizar a tabela de contatos", e);
        }
    }
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
