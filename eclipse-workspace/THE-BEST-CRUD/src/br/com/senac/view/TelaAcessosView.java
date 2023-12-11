package br.com.senac.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAcessosView extends JFrame {

	private JPanel contentPane;


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaAcessosView frame = new TelaAcessosView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public TelaAcessosView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaAcessosView.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		setTitle("AGENDA - NOVA GERAÇÃO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPessoa = new JButton("PESSOAS");
		btnPessoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPessoa.setBounds(39, 267, 196, 63);
		contentPane.add(btnPessoa);
		
		JButton btnContatos = new JButton("CONTATOS");
		btnContatos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnContatos.setBounds(264, 267, 196, 63);
		contentPane.add(btnContatos);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		btnNewButton.setBounds(131, 29, 226, 217);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("<-");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
				LoginView frame = new LoginView();
				frame.setVisible(true);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVoltar.setIcon(null);
		btnVoltar.setBounds(10, 10, 60, 41);
		contentPane.add(btnVoltar);
	}

}
