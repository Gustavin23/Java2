package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class Relatorio extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Relatorio dialog = new Relatorio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Relatorio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorio.class.getResource("/img/Relatorio.png")));
		setTitle("Relat\u00F3rios");
		setResizable(false);
		setModal(true);
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
	}

}
