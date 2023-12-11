package br.com.senac.view;



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

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	int xx,xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// going to borrow code from a gist to move frame.
	

	/**
	 * Create the frame.
	 */
	public Home() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 476);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 346, 446);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setForeground(new Color(0, 0, 0));
		
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				 xx = e.getX();
			     xy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				int x = arg0.getXOnScreen();
	            int y = arg0.getYOnScreen();
	            Home.this.setLocation(x - xx, y - xy);  
			}
		});
		
		JLabel lblNewLabel = new JLabel("ACESSE A GROW");
		lblNewLabel.setBounds(105, 0, 135, 27);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Fira Sans", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.BLACK);
		///home/ramonjoaquim/Pictures/foto.png
		label.setBounds(-41, 0, 420, 448);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon("/home/ramonjoaquim/Pictures/tipo.jpg"));
		panel.add(label);
		
		JLabel lblWeGotYou = new JLabel("FAÇA SEU CADASTRO NA GROW.COM");
		lblWeGotYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeGotYou.setForeground(new Color(240, 248, 255));
		lblWeGotYou.setFont(new Font("Dialog", Font.BOLD, 12));
		lblWeGotYou.setBounds(29, 421, 291, 27);
		panel.add(lblWeGotYou);
		
		textField = new JTextField();
		textField.setBounds(395, 54, 283, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("USUARIO");
		lblUsername.setBounds(395, 28, 114, 14);
		contentPane.add(lblUsername);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(395, 102, 54, 14);
		contentPane.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(395, 128, 283, 36);
		contentPane.add(textField_1);
		
		JLabel lblPassword = new JLabel("SENHA");
		lblPassword.setBounds(395, 176, 96, 14);
		contentPane.add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("REPETIR SENHA");
		lblRepeatPassword.setBounds(393, 247, 185, 14);
		contentPane.add(lblRepeatPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(395, 199, 283, 36);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(395, 273, 283, 36);
		contentPane.add(passwordField_1);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(691, 0, 37, 27);
		contentPane.add(lbl_close);
		
		JButton btnFazerLogin = new JButton("FAZER LOGIN");
		btnFazerLogin.setBounds(395, 381, 283, 27);
		contentPane.add(btnFazerLogin);
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(395, 328, 283, 27);
		contentPane.add(btnCadastrar);
	}
}