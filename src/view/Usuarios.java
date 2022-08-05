package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.Color;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
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
	public Usuarios() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/Cadastro.png")));
		setTitle("Usuarios");
		setResizable(false);
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(36, 30, 17, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(85, 27, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(36, 58, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(85, 55, 162, 20);
		getContentPane().add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Login");
		lblNewLabel_1_1.setBounds(36, 86, 46, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Senha");
		lblNewLabel_1_2.setBounds(36, 114, 46, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(85, 83, 162, 20);
		getContentPane().add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(85, 111, 162, 20);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Perfil");
		lblNewLabel_1_2_1.setBounds(36, 142, 46, 14);
		getContentPane().add(lblNewLabel_1_2_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Admin", "User"}));
		comboBox.setBounds(85, 138, 86, 22);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setDefaultCapable(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setToolTipText("Adicionar Usu\u00E1rio");
		btnNewButton.setIcon(new ImageIcon(Usuarios.class.getResource("/img/adicionar.png")));
		btnNewButton.setBounds(35, 171, 64, 64);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setDefaultCapable(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1.setToolTipText("Remover Usu\u00E1rio");
		btnNewButton_1.setIcon(new ImageIcon(Usuarios.class.getResource("/img/remover.png")));
		btnNewButton_1.setBounds(109, 171, 64, 64);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setDefaultCapable(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_2.setToolTipText("Alterar Usu\u00E1rio");
		btnNewButton_2.setIcon(new ImageIcon(Usuarios.class.getResource("/img/alterar.png")));
		btnNewButton_2.setBounds(183, 171, 64, 64);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setSelected(true);
		btnNewButton_3.setToolTipText("Pesquisar");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setIcon(new ImageIcon(Usuarios.class.getResource("/img/search1.png")));
		btnNewButton_3.setBounds(181, 15, 32, 32);
		getContentPane().add(btnNewButton_3);

	}// Fim do construtor
}
