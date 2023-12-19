package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.senac.dao.TesteHibernate;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int xx,xy;

	private JPanel contentPane;
	private JTextField tFUser;
	private JButton btnAcessar;
	private JPasswordField passwordField;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel label;
//	private TelaAcessosView telaAcessos;
	private TelaPrincipalView telaPrincipalView;



//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginView frame = new LoginView();
//					frame.setUndecorated(true);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	


	public LoginView() {
		setResizable(false);
		setTitle("Tela De Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 377, 446);
		contentPane.add(panel);
		
		lblNewLabel = new JLabel("ACESSE A AGENDA GROW");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Fira Sans", Font.BOLD, 18));
		lblNewLabel.setBounds(50, 0, 257, 27);
		panel.add(lblNewLabel);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/senac/view/img/istockphoto-1219250752-612x612.jpg")));
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setForeground(Color.BLACK);
		label.setBounds(-126, 0, 581, 597);
		panel.add(label);
		
		JLabel lblUser = new JLabel("ÚSUARIO");
		lblUser.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 14));
		lblUser.setBounds(377, 129, 79, 13);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("SENHA");
		lblPassword.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 14));
		lblPassword.setBounds(377, 187, 59, 13);
		contentPane.add(lblPassword);
		
		tFUser = new JTextField();
		tFUser.setBounds(454, 126, 220, 19);
		contentPane.add(tFUser);
		tFUser.setColumns(10);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.setBounds(696, 0, 37, 27);
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lbl_close);
		
		btnAcessar = new JButton("FAZER LOGIN");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validaSenha();
//				abrirTelaPrincipal();
			}
		});
		btnAcessar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAcessar.setBounds(454, 239, 220, 21);
		contentPane.add(btnAcessar);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(454, 183, 220, 21);
		contentPane.add(passwordField);
		
		JButton btnSair = new JButton("SAIR");
		btnSair.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSair.setBounds(454, 284, 220, 19);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				int input = 
						JOptionPane.showConfirmDialog(null, 
							"Deseja sair do sistema?", 
							"Selecione uma opção...",
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE);
					 if(input == 0) {
				System.exit(0);
			}}
		});
		btnSair.setToolTipText("Clique aqui para sair da aplicação");
		btnSair.setMnemonic('S');
		contentPane.add(btnSair);
		
	}
	//////////////
//	private void abrirTelaMenu() {
//		try {
//			EditarPessoaView editarPessoa = new EditarPessoaView();
//			editarPessoa.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de aviso.",
//					JOptionPane.ERROR_MESSAGE);
//		}
//	}
	protected void abrirTelaPrincipal() {
		  if (telaPrincipalView == null) {
			  telaPrincipalView = new TelaPrincipalView();
	        }
		  telaPrincipalView.setVisible(true);
	    }
	//////////////
	
	public void validaSenha() {
		String usuario= tFUser.getText();
		String senha= passwordField.getText();
		
		if (usuario.equals("admin") && senha.equals("admin")) {
			TelaPrincipalView telaPrincipalView= new TelaPrincipalView();
			telaPrincipalView.setVisible(true);			
			dispose();
		} else if(usuario.isEmpty() || senha.isEmpty() || usuario != "admin" || senha != "admin" ) { 
			JOptionPane.showMessageDialog(null,"Usuário e/ ou senha inválidos.", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}