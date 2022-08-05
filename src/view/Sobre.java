package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextPane;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
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
	public Sobre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/sobre.png")));
		setTitle("Sobre");
		setResizable(false);
		setModal(true);
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("@author Gustavo Rene");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 26, 190, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Sobre.class.getResource("/img/mit.png")));
		lblNewLabel_1.setToolTipText("licen\u00E7a MIT");
		lblNewLabel_1.setBounds(10, 51, 64, 64);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("License MIT");
		lblNewLabel_2.setBounds(84, 75, 70, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
		btnNewButton.setToolTipText("Github");
		btnNewButton.setBounds(360, 26, 64, 64);
		getContentPane().add(btnNewButton);
		
		JTextPane txtpnCopyrightcA = new JTextPane();
		txtpnCopyrightcA.setText("\u00C9 uma licen\u00E7a de programas de computadores, criada pelo Instituto de Tecnologia de Massachusetts. Ela \u00E9 uma licen\u00E7a permissiva utilizada tanto em software livre quanto em software propriet\u00E1rio.");
		txtpnCopyrightcA.setBounds(10, 110, 285, 62);
		getContentPane().add(txtpnCopyrightcA);

	}
}
