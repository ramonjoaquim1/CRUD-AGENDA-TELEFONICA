package br.com.senac.view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	public JFrame frmSistemaDeAgenda;
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
//	 */
//	public static void main(String[] args) 
//	{
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login window = new Login();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmSistemaDeAgenda = new JFrame();
		frmSistemaDeAgenda.setBounds(100, 100, 450, 300);
		frmSistemaDeAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeAgenda.setTitle("Sistema de Agenda  ");
		frmSistemaDeAgenda.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		label.setBounds(152, 5, 151, 41);
		frmSistemaDeAgenda.getContentPane().add(label);
		
		JLabel lblLoginScreen = new JLabel("Tela Login");
		lblLoginScreen.setFont(new Font("Dialog", Font.BOLD, 13));
		lblLoginScreen.setBounds(171, 5, 101, 23);
		frmSistemaDeAgenda.getContentPane().add(lblLoginScreen);
		
		JLabel lblUsername = new JLabel("Usuario:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(26, 64, 68, 23);
		frmSistemaDeAgenda.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Senha:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(26, 123, 64, 23);
		frmSistemaDeAgenda.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(105, 66, 86, 20);
		frmSistemaDeAgenda.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("admin");
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(105, 125, 86, 20);
		frmSistemaDeAgenda.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String user,pass;
				textField.setText("admin");
				user="admin";
				//user=textField.getText();
				pass=textField_1.getText();
				if((user.equals("admin")&&(pass.equals("admin"))))
						{
							JOptionPane.showMessageDialog(frmSistemaDeAgenda.getComponent(0), "Login Successfully");
							frmSistemaDeAgenda.setVisible(false);
							
							GUIForm.menu.setVisible(true);
							
						}
				else
				{
					JOptionPane.showMessageDialog(frmSistemaDeAgenda.getComponent(0), "Login Failed");
				}
			}
		});
		btnLogin.setBounds(53, 183, 89, 23);
		frmSistemaDeAgenda.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("IMAGEM PRA DIVISAO DE PAGINA AQUI");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 6));
		lblNewLabel.setBounds(275, 110, 138, 47);
		frmSistemaDeAgenda.getContentPane().add(lblNewLabel);
	}
}