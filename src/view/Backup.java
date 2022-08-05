package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;

public class Backup extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Backup dialog = new Backup();
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
	public Backup() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Backup.class.getResource("/img/Backup.png")));
		setTitle("Backup");
		setResizable(false);
		setModal(true);
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);

	}

}
