package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;

public class Caixa extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Caixa dialog = new Caixa();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Caixa() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Caixa.class.getResource("/img/Caixa.png")));
		setTitle("Caixa");
		setResizable(false);
		setModal(true);
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);

	}

}
