package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;

import model.DAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fornecedor extends JDialog {
	private JTextField txtPesquisarFornecedor;
	private JTextField txtForCNPJ;
	private JTextField txtForIM;
	private JTextField txtForFantasia;
	private JTextField txtForFone;
	private JTextField txtForRazao;
	private JTextField txtForSite;
	private JTextField txtForContato;
	private JTextField txtForEmail;
	private JTextField txtForEndereco;
	private JTextField txtForComplemento;
	private JTextField txtForCEP;
	private JTextField txtForNumero;
	private JTextField txtForBairro;
	private JTextField txtForCidade;
	private JTextField txtForIE;
	private JTable tblFornecedores;
	private JTextField txtForId;
	private JComboBox cboForUF;
	private JButton btnBuscarCep;

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
		
		txtPesquisarFornecedor = new JTextField();
		txtPesquisarFornecedor.setColumns(10);
		txtPesquisarFornecedor.setBounds(135, 35, 140, 20);
		getContentPane().add(txtPesquisarFornecedor);
		
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
		
		txtForCNPJ = new JTextField();
		txtForCNPJ.setColumns(10);
		txtForCNPJ.setBounds(135, 171, 170, 20);
		getContentPane().add(txtForCNPJ);
		
		JLabel lblIe = new JLabel("I.M");
		lblIe.setBounds(50, 205, 69, 14);
		getContentPane().add(lblIe);
		
		txtForIM = new JTextField();
		txtForIM.setColumns(10);
		txtForIM.setBounds(135, 202, 170, 20);
		getContentPane().add(txtForIM);
		
		JLabel lblIm = new JLabel("Fantasia");
		lblIm.setBounds(50, 233, 69, 14);
		getContentPane().add(lblIm);
		
		txtForFantasia = new JTextField();
		txtForFantasia.setColumns(10);
		txtForFantasia.setBounds(135, 230, 170, 20);
		getContentPane().add(txtForFantasia);
		
		JLabel lblRazo = new JLabel("Fone");
		lblRazo.setBounds(50, 264, 69, 14);
		getContentPane().add(lblRazo);
		
		txtForFone = new JTextField();
		txtForFone.setColumns(10);
		txtForFone.setBounds(135, 261, 170, 20);
		getContentPane().add(txtForFone);
		
		txtForRazao = new JTextField();
		txtForRazao.setColumns(10);
		txtForRazao.setBounds(371, 202, 385, 20);
		getContentPane().add(txtForRazao);
		
		txtForSite = new JTextField();
		txtForSite.setColumns(10);
		txtForSite.setBounds(371, 230, 271, 20);
		getContentPane().add(txtForSite);
		
		txtForContato = new JTextField();
		txtForContato.setColumns(10);
		txtForContato.setBounds(371, 261, 140, 20);
		getContentPane().add(txtForContato);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(50, 295, 69, 14);
		getContentPane().add(lblEmail);
		
		txtForEmail = new JTextField();
		txtForEmail.setColumns(10);
		txtForEmail.setBounds(135, 292, 170, 20);
		getContentPane().add(txtForEmail);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(50, 377, 69, 14);
		getContentPane().add(lblEndereo);
		
		txtForEndereco = new JTextField();
		txtForEndereco.setColumns(10);
		txtForEndereco.setBounds(135, 374, 207, 20);
		getContentPane().add(txtForEndereco);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(50, 408, 81, 14);
		getContentPane().add(lblComplemento);
		
		txtForComplemento = new JTextField();
		txtForComplemento.setColumns(10);
		txtForComplemento.setBounds(135, 405, 207, 20);
		getContentPane().add(txtForComplemento);
		
		txtForCEP = new JTextField();
		txtForCEP.setColumns(10);
		txtForCEP.setBounds(135, 337, 140, 20);
		getContentPane().add(txtForCEP);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(352, 377, 49, 14);
		getContentPane().add(lblNmero);
		
		txtForNumero = new JTextField();
		txtForNumero.setColumns(10);
		txtForNumero.setBounds(411, 374, 140, 20);
		getContentPane().add(txtForNumero);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(352, 408, 44, 14);
		getContentPane().add(lblBairro);
		
		txtForBairro = new JTextField();
		txtForBairro.setColumns(10);
		txtForBairro.setBounds(411, 405, 140, 20);
		getContentPane().add(txtForBairro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(50, 436, 69, 14);
		getContentPane().add(lblCidade);
		
		txtForCidade = new JTextField();
		txtForCidade.setColumns(10);
		txtForCidade.setBounds(135, 433, 170, 20);
		getContentPane().add(txtForCidade);
		
		JLabel lblUf = new JLabel("U.F");
		lblUf.setBounds(352, 436, 44, 14);
		getContentPane().add(lblUf);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/search1.png")));
		lblNewLabel.setBounds(285, 23, 32, 32);
		getContentPane().add(lblNewLabel);
		
		cboForUF = new JComboBox();
		cboForUF.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboForUF.setBounds(398, 432, 57, 22);
		getContentPane().add(cboForUF);
		
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
		
		btnBuscarCep = new JButton("Buscar CEP");
		btnBuscarCep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscarCep.setBounds(297, 336, 140, 23);
		getContentPane().add(btnBuscarCep);
		
		txtForIE = new JTextField();
		txtForIE.setColumns(10);
		txtForIE.setBounds(371, 171, 170, 20);
		getContentPane().add(txtForIE);
		
		JTextArea txtForOBS = new JTextArea();
		txtForOBS.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtForOBS.setBounds(83, 464, 374, 53);
		getContentPane().add(txtForOBS);
		
		JLabel lblObs = new JLabel("OBS:");
		lblObs.setBounds(50, 469, 37, 14);
		getContentPane().add(lblObs);
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.setDefaultCapable(false);
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/adicionar.png")));
		btnAdicionar.setToolTipText("Adicionar Fornecedor");
		btnAdicionar.setBounds(496, 464, 64, 64);
		getContentPane().add(btnAdicionar);
		
		JButton btnAlterar = new JButton("");
		btnAlterar.setDefaultCapable(false);
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/alterar.png")));
		btnAlterar.setToolTipText("Alterar Fornecedor");
		btnAlterar.setBounds(670, 464, 64, 64);
		getContentPane().add(btnAlterar);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setDefaultCapable(false);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/remover.png")));
		btnExcluir.setToolTipText("Remover Fornecedor");
		btnExcluir.setBounds(583, 464, 64, 64);
		getContentPane().add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 63, 595, 84);
		getContentPane().add(scrollPane);
		
		tblFornecedores = new JTable();
		scrollPane.setViewportView(tblFornecedores);
		
		txtForId = new JTextField();
		txtForId.setColumns(10);
		txtForId.setBounds(496, 35, 55, 20);
		getContentPane().add(txtForId);
		
		JButton btnPesquisar = new JButton("Buscar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarFornecedor();
			}
		});
		btnPesquisar.setBounds(561, 34, 89, 23);
		getContentPane().add(btnPesquisar);
		
		JLabel lblIe_1_1 = new JLabel("ID");
		lblIe_1_1.setBounds(454, 38, 32, 14);
		getContentPane().add(lblIe_1_1);

	} //Fim do construtor
	
	
	DAO dao = new DAO();
	
	/**
	 * Método responsável pela pesquisa de Fornecedores
	 */
	private void pesquisarFornecedor() {
		// Validação
		if (txtForId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do usuário");
			txtForId.requestFocus();
		} else {
			String read = "select * from fornecedores where idfor = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtForId.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					txtForId.setText(rs.getString(1));
					txtForCNPJ.setText(rs.getString(2));
					txtForIE.setText(rs.getString(3));
					txtForIM.setText(rs.getString(4));
					txtForRazao.setText(rs.getString(5));
					txtForFantasia.setText(rs.getString(6));
					txtForSite.setText(rs.getString(7));
					txtForFone.setText(rs.getString(8));
					txtForContato.setText(rs.getString(9));
					txtForEmail.setText(rs.getString(10));
					txtForCEP.setText(rs.getString(11));
					txtForEndereco.setText(rs.getString(12));
					txtForNumero.setText(rs.getString(13));
					txtForComplemento.setText(rs.getString(14));
					txtForBairro.setText(rs.getString(15));
					txtForCidade.setText(rs.getString(16));
					cboForUF.setSelectedItem(rs.getString(17));
				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
} // Fim do código
