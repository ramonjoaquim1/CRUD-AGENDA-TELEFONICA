package br.com.senac.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipalView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalView frame = new TelaPrincipalView();
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
	public TelaPrincipalView() {
		setTitle("Projeto ADS3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);
		
		JMenuItem mnSair = new JMenuItem("Sair");
		mnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sairSistema();
			}
		});
		mnSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnArquivo.add(mnSair);
		
		JMenu mnManutencao = new JMenu("Manutenção");
		mnManutencao.setMnemonic('M');
		menuBar.add(mnManutencao);
		
		JMenuItem mnProduto = new JMenuItem("Produto");
		mnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaProduto();
			}
		});
		mnProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK));
		mnManutencao.add(mnProduto);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setMnemonic('u');
		menuBar.add(mnAjuda);
		
		JMenuItem mnSobre = new JMenuItem("Sobre");
		mnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirSobre();
			}
		});
		mnSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		mnAjuda.add(mnSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btProduto = new JButton("Produto");
		btProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaProduto();
			}
		});
		btProduto.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/util/img/products.png")));
		toolBar.add(btProduto);
		
		try {
			
			ImageIcon imageIcon = new ImageIcon(
				TelaPrincipalView.class.getResource(
						"/br/com/senac/view/util/img/senac_logo.png"));
			
			JLabel centerLabel = new JLabel(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/senac/view/util/img/minecraf.jpg")));
			
			getContentPane().add(centerLabel, BorderLayout.CENTER);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	protected void abrirSobre() {
		
		JOptionPane.showMessageDialog(
				this, 
				"Este sistema foi desenvolvido por XXXXXXXXX",
				"Sobre",
				JOptionPane.INFORMATION_MESSAGE);
		
	}

	protected void sairSistema() {
		
		Object[] options = {"Sim!", "Não"};
		int n = JOptionPane.showOptionDialog(
				this,
				"Deseja realmente sair do sistema?", 
				"Confirmação", 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		
		if(n == 0) {
			System.exit(0);
		}
		
	}

	protected void abrirTelaProduto() {
		
		ConsultarProdutoView cpv = new ConsultarProdutoView();
		cpv.setVisible(true);
		
	}

}
