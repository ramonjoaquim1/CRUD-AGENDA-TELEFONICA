import java.awt.EventQueue;

import br.com.senac.view.LoginView;
import br.com.senac.view.TelaPrincipalView;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LoginView frame = new LoginView();
//					TelaPrincipalView frame = new TelaPrincipalView();
					
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
