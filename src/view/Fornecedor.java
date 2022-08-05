package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;

public class Fornecedor extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fornecedor dialog = new Fornecedor();
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
	public Fornecedor() {
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedor.class.getResource("/img/fornecimento.png")));
		setTitle("Fornecedores");
		setBounds(150, 150, 793, 607);
		getContentPane().setLayout(null);
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(50, 38, 69, 14);
		getContentPane().add(lblFornecedor);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(135, 35, 140, 20);
		getContentPane().add(textField);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setToolTipText("Pesquisar");
		btnNewButton_3.setSelected(true);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setBounds(179, 23, 32, 32);
		getContentPane().add(btnNewButton_3);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(50, 174, 69, 14);
		getContentPane().add(lblCnpj);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(135, 171, 170, 20);
		getContentPane().add(textField_1);
		
		JLabel lblIe = new JLabel("I.M");
		lblIe.setBounds(50, 205, 69, 14);
		getContentPane().add(lblIe);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(135, 202, 170, 20);
		getContentPane().add(textField_2);
		
		JLabel lblIm = new JLabel("Fantasia");
		lblIm.setBounds(50, 233, 69, 14);
		getContentPane().add(lblIm);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(135, 230, 170, 20);
		getContentPane().add(textField_3);
		
		JLabel lblRazo = new JLabel("Fone");
		lblRazo.setBounds(50, 264, 69, 14);
		getContentPane().add(lblRazo);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(135, 261, 170, 20);
		getContentPane().add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(371, 202, 271, 20);
		getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(371, 230, 271, 20);
		getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(371, 261, 140, 20);
		getContentPane().add(textField_8);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(50, 295, 69, 14);
		getContentPane().add(lblEmail);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(135, 292, 170, 20);
		getContentPane().add(textField_9);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(50, 377, 69, 14);
		getContentPane().add(lblEndereo);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(135, 374, 207, 20);
		getContentPane().add(textField_10);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(50, 408, 81, 14);
		getContentPane().add(lblComplemento);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(135, 405, 207, 20);
		getContentPane().add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(135, 337, 140, 20);
		getContentPane().add(textField_12);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(352, 377, 49, 14);
		getContentPane().add(lblNmero);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(411, 374, 140, 20);
		getContentPane().add(textField_13);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(352, 408, 44, 14);
		getContentPane().add(lblBairro);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(411, 405, 140, 20);
		getContentPane().add(textField_14);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(50, 436, 69, 14);
		getContentPane().add(lblCidade);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(135, 433, 170, 20);
		getContentPane().add(textField_15);
		
		JLabel lblUf = new JLabel("U.F");
		lblUf.setBounds(352, 436, 44, 14);
		getContentPane().add(lblUf);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/search1.png")));
		lblNewLabel.setBounds(285, 23, 32, 32);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBox.setBounds(398, 432, 57, 22);
		getContentPane().add(comboBox);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(50, 340, 49, 14);
		getContentPane().add(lblCep);
		
		JLabel lblContato = new JLabel("Contato");
		lblContato.setBounds(315, 264, 49, 14);
		getContentPane().add(lblContato);
		
		JLabel lblSite = new JLabel("Site");
		lblSite.setBounds(315, 233, 49, 14);
		getContentPane().add(lblSite);
		
		JLabel lblRazo_1 = new JLabel("Raz\u00E3o");
		lblRazo_1.setBounds(316, 205, 49, 14);
		getContentPane().add(lblRazo_1);
		
		JLabel lblIe_1 = new JLabel("I.E");
		lblIe_1.setBounds(316, 174, 49, 14);
		getContentPane().add(lblIe_1);
		
		JButton btnNewButton = new JButton("Buscar CEP");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(297, 336, 140, 23);
		getContentPane().add(btnNewButton);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(371, 171, 170, 20);
		getContentPane().add(textField_16);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setBounds(83, 464, 374, 53);
		getContentPane().add(textArea);
		
		JLabel lblObs = new JLabel("OBS:");
		lblObs.setBounds(50, 469, 37, 14);
		getContentPane().add(lblObs);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setDefaultCapable(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/adicionar.png")));
		btnNewButton_1.setToolTipText("Adicionar Fornecedor");
		btnNewButton_1.setBounds(496, 453, 64, 64);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setDefaultCapable(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/alterar.png")));
		btnNewButton_2.setToolTipText("Alterar Fornecedor");
		btnNewButton_2.setBounds(672, 453, 64, 64);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setDefaultCapable(false);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/remover.png")));
		btnNewButton_4.setToolTipText("Remover Fornecedor");
		btnNewButton_4.setBounds(584, 453, 64, 64);
		getContentPane().add(btnNewButton_4);

	}
}
