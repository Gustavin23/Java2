package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class Cliente extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente dialog = new Cliente();
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
	public Cliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cliente.class.getResource("/img/Clients.png")));
		setTitle("Clientes");
		setModal(true);
		setResizable(false);
		setBounds(150, 150, 740, 592);
		getContentPane().setLayout(null);
		
		JLabel lblFornecedor = new JLabel("ID do Cliente");
		lblFornecedor.setBounds(72, 41, 85, 14);
		getContentPane().add(lblFornecedor);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(157, 38, 140, 20);
		getContentPane().add(textField);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(72, 137, 69, 14);
		getContentPane().add(lblNome);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(171, 134, 204, 20);
		getContentPane().add(textField_1);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(72, 168, 121, 14);
		getContentPane().add(lblDataDeNascimento);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(207, 165, 168, 20);
		getContentPane().add(textField_2);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(72, 199, 69, 14);
		getContentPane().add(lblFone);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(173, 193, 168, 20);
		getContentPane().add(textField_3);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(72, 230, 69, 14);
		getContentPane().add(lblCpf);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(173, 227, 168, 20);
		getContentPane().add(textField_4);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(72, 261, 69, 14);
		getContentPane().add(lblEmail);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(171, 258, 204, 20);
		getContentPane().add(textField_5);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(76, 344, 49, 14);
		getContentPane().add(lblCep);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(171, 341, 140, 20);
		getContentPane().add(textField_7);
		
		JButton btnNewButton = new JButton("Buscar CEP");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(321, 340, 140, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(76, 381, 69, 14);
		getContentPane().add(lblEndereo);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(171, 378, 251, 20);
		getContentPane().add(textField_8);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(432, 381, 49, 14);
		getContentPane().add(lblNmero);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(491, 378, 140, 20);
		getContentPane().add(textField_9);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(76, 412, 81, 14);
		getContentPane().add(lblComplemento);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(171, 409, 251, 20);
		getContentPane().add(textField_10);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(432, 412, 44, 14);
		getContentPane().add(lblBairro);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(491, 409, 140, 20);
		getContentPane().add(textField_11);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(76, 440, 69, 14);
		getContentPane().add(lblCidade);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(171, 437, 170, 20);
		getContentPane().add(textField_12);
		
		JLabel lblUf = new JLabel("U.F");
		lblUf.setBounds(432, 440, 44, 14);
		getContentPane().add(lblUf);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setBounds(490, 436, 57, 22);
		getContentPane().add(comboBox);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(Cliente.class.getResource("/img/adicionar.png")));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setDefaultCapable(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setToolTipText("Adicionar Cliente");
		btnNewButton_1.setBounds(72, 478, 64, 64);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setDefaultCapable(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setIcon(new ImageIcon(Cliente.class.getResource("/img/remover.png")));
		btnNewButton_2.setToolTipText("Remover Cliente");
		btnNewButton_2.setBounds(168, 478, 64, 64);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setDefaultCapable(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setIcon(new ImageIcon(Cliente.class.getResource("/img/alterar.png")));
		btnNewButton_3.setToolTipText("Alterar Cliente");
		btnNewButton_3.setBounds(264, 478, 64, 64);
		getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Cliente.class.getResource("/img/search1.png")));
		lblNewLabel.setToolTipText("Pesquisar");
		lblNewLabel.setBounds(307, 27, 32, 32);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "SIM", "N\u00C3O"}));
		comboBox_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_1.setBounds(171, 289, 81, 22);
		getContentPane().add(comboBox_1);
		
		JLabel lblMarketing = new JLabel("Marketing");
		lblMarketing.setBounds(72, 293, 69, 14);
		getContentPane().add(lblMarketing);

	}
}
