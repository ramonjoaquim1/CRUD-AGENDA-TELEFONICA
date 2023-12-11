package br.com.senac.view;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    public JFrame frmSistemaDeAgenda;
    private JTextField textField;
    private JPasswordField passwordField;

    public Login() {
        initialize();
    }

    private void initialize() {
    	
        frmSistemaDeAgenda = new JFrame();
        frmSistemaDeAgenda.setBounds(100, 100, 624, 483);
        frmSistemaDeAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSistemaDeAgenda.setTitle("Sistema de Agenda");
        frmSistemaDeAgenda.getContentPane().setLayout(null);

        JLabel label = new JLabel("");
        label.setFont(new Font("Tahoma", Font.BOLD, 17));
        label.setBounds(152, 5, 151, 41);
        frmSistemaDeAgenda.getContentPane().add(label);

        JLabel lblLoginScreen = new JLabel("Tela Login");
        lblLoginScreen.setFont(new Font("Dialog", Font.BOLD, 13));
        lblLoginScreen.setBounds(171, 5, 101, 23);
        frmSistemaDeAgenda.getContentPane().add(lblLoginScreen);

        JLabel lblUsername = new JLabel("Usuário:");
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

        passwordField = new JPasswordField();
        passwordField.setBounds(105, 125, 86, 20);
        frmSistemaDeAgenda.getContentPane().add(passwordField);
        passwordField.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = textField.getText();
                String pass = new String(passwordField.getPassword());

                if ("admin".equals(user) && "admin".equals(pass)) {
                    JOptionPane.showMessageDialog(frmSistemaDeAgenda.getComponent(0),
                            "Login Successfully");
                    frmSistemaDeAgenda.setVisible(false);
                    // Substitua GUIForm.menu por TelaConsultaProdutoView
                    TelaConsultaProdutoView telaConsultaProdutoView = new TelaConsultaProdutoView();
                    telaConsultaProdutoView.setVisible(true);
                } else {
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

    public static void main(String[] args) {
        Login window = new Login();
        window.frmSistemaDeAgenda.setVisible(true);
    }
}
