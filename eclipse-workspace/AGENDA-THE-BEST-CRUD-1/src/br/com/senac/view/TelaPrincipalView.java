package br.com.senac.view;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.awt.event.InputEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipalView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public TelaPrincipalView() {
		setTitle("AGENDA GROW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 428);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/folder.png")));
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);

		JMenuItem miSair = new JMenuItem("Sair");
		miSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sairSistema();
			}
		});
		miSair.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/sair.png")));
		miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnArquivo.add(miSair);

		JMenu mnManutencao = new JMenu("Manutenção");
		mnManutencao.setIcon(
				new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/maintenance.png")));
		mnManutencao.setMnemonic('M');
		menuBar.add(mnManutencao);
		
				JMenuItem miProduto = new JMenuItem("Pessoa");
				miProduto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						abrirTelaCadastroPessoa();
					}
				});
				miProduto.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/edit.png")));
				miProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
				mnManutencao.add(miProduto);
		
		JMenuItem mntmContato = new JMenuItem("Contato");
		mntmContato.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
		mntmContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaCadastroContato();
			}
		});
		mntmContato.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/contato.png")));
		miProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		mnManutencao.add(mntmContato);

		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/ajuda.png")));
		mnAjuda.setMnemonic('j');
		menuBar.add(mnAjuda);

		JMenuItem mntmNewMenuItem = new JMenuItem("Ajuda");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirSobre();
			}
		});
		mntmNewMenuItem
				.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/ajuda.png")));
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnAjuda.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		try {

//			InputStream streamLogo = getClass().getResourceAsStream("senac_logo.png");

//			BufferedImage img = ImageIO.read(streamLogo);
//			ImageIcon imageIcon = new ImageIcon(img);
//
			JLabel centerLabel = new JLabel(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/pilpficcao.png")));

			JPanel main = new JPanel(new BorderLayout());
			main.add(centerLabel, BorderLayout.CENTER);

			getContentPane().add(main, BorderLayout.CENTER);

			JToolBar toolBar = new JToolBar();
			main.add(toolBar, BorderLayout.NORTH);

			JButton btnProduto = new JButton("Contato");
			btnProduto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirTelaCadastroContato();
				}
			});
			
			JButton btnPessoa = new JButton("Pessoa");
			btnPessoa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirTelaCadastroPessoa();
				}
			});
			btnPessoa.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/edit.png")));
			toolBar.add(btnPessoa);
			btnProduto.setIcon(
					new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/img/contato.png")));
			toolBar.add(btnProduto);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abrir a tela.", "Erro!", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void abrirSobre() {

		JOptionPane.showMessageDialog(this, "Este sistema foi desenvolvido pelos mestres Ramon e Isac. ", "Sobre",
				JOptionPane.INFORMATION_MESSAGE);

	}

	protected void sairSistema() {

		Object[] options = { "Sim!", "Não" };
		int n = JOptionPane.showOptionDialog(this, "Deseja realmente sair do sistema?", "Confirmação",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (n == 0) {
			System.exit(0);
		}

	}

	public void abrirTelaCadastroContato() {
		CadastroContatoView telacadacont  = new CadastroContatoView(null);
		telacadacont.setVisible(true);
	}

	public void abrirTelaCadastroPessoa() {
		CadastroPessoaView telacadapess = new CadastroPessoaView();
		telacadapess.setVisible(true);
	}
	
}
