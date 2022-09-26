package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.toedter.calendar.JDateChooser;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Produto extends JDialog {
	private JTextField txtPBarra;
	private JTextField txtPCod;
	private JTextField txtFPesquisar;
	private JTextField txtForID;
	private JTable tblFornecedores;
	private JTextField txtPProduto;
	private JTextField textField_5;
	private JTextField txtPFabricante;
	private JTextField txtPEstoque;
	private JTextField txtPEstoqueM;
	private JTextField txtPLocal;
	private JTextField txtPCusto;
	private JTextField txtPLucro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produto dialog = new Produto();
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
	public Produto() {
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produto.class.getResource("/img/produtos1.png")));
		setTitle("Produtos");
		setBounds(150, 150, 884, 483);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Produto.class.getResource("/img/barcode.png")));
		lblNewLabel.setBounds(38, 37, 64, 45);
		getContentPane().add(lblNewLabel);
		
		txtPBarra = new JTextField();
		txtPBarra.setBounds(112, 50, 294, 20);
		getContentPane().add(txtPBarra);
		txtPBarra.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo");
		lblNewLabel_1.setBounds(48, 99, 64, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtPCod = new JTextField();
		txtPCod.setBounds(112, 96, 188, 20);
		getContentPane().add(txtPCod);
		txtPCod.setColumns(10);
		
		JButton btnPPesquisar = new JButton("Pesquisar");
		btnPPesquisar.setBounds(310, 95, 96, 23);
		getContentPane().add(btnPPesquisar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(457, 37, 381, 161);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtFPesquisar = new JTextField();
		txtFPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarFornecedorTabela();
			}
		});
		txtFPesquisar.setBounds(10, 27, 147, 20);
		panel.add(txtFPesquisar);
		txtFPesquisar.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(167, 15, 32, 32);
		lblNewLabel_2.setIcon(new ImageIcon(Produto.class.getResource("/img/search1.png")));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(222, 30, 46, 14);
		panel.add(lblNewLabel_3);
		
		txtForID = new JTextField();
		txtForID.setEditable(false);
		txtForID.setBounds(253, 27, 103, 20);
		panel.add(txtForID);
		txtForID.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 361, 92);
		panel.add(scrollPane);
		
		tblFornecedores = new JTable();
		tblFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
				limparCamposFornecedor();
			}
		});
		scrollPane.setViewportView(tblFornecedores);
		
		JLabel lblNewLabel_1_1 = new JLabel("Produto");
		lblNewLabel_1_1.setBounds(48, 137, 64, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		txtPProduto = new JTextField();
		txtPProduto.setColumns(10);
		txtPProduto.setBounds(112, 134, 294, 20);
		getContentPane().add(txtPProduto);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_1_1_1.setBounds(48, 179, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(112, 178, 294, 64);
		getContentPane().add(textField_5);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setName("BtnAdicionar");
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setIcon(new ImageIcon(Produto.class.getResource("/img/create.png")));
		btnNewButton_1.setToolTipText("Adicionar");
		btnNewButton_1.setBounds(457, 364, 64, 64);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setName("BtnAlterar");
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1.setIcon(new ImageIcon(Produto.class.getResource("/img/update.png")));
		btnNewButton_1_1.setToolTipText("Atualizar");
		btnNewButton_1_1.setBounds(543, 364, 64, 64);
		getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.setName("btnRemover");
		btnNewButton_1_2.setContentAreaFilled(false);
		btnNewButton_1_2.setBorderPainted(false);
		btnNewButton_1_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_2.setIcon(new ImageIcon(Produto.class.getResource("/img/delete.png")));
		btnNewButton_1_2.setToolTipText("Remover");
		btnNewButton_1_2.setBounds(628, 364, 64, 64);
		getContentPane().add(btnNewButton_1_2);
		
		txtPFabricante = new JTextField();
		txtPFabricante.setColumns(10);
		txtPFabricante.setBounds(112, 272, 294, 20);
		getContentPane().add(txtPFabricante);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Fabricante");
		lblNewLabel_1_1_1_1.setBounds(48, 275, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Estoque");
		lblNewLabel_1_1_1_1_1.setBounds(48, 321, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		txtPEstoque = new JTextField();
		txtPEstoque.setColumns(10);
		txtPEstoque.setBounds(112, 318, 78, 20);
		getContentPane().add(txtPEstoque);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Estoque M\u00EDnimo");
		lblNewLabel_1_1_1_1_1_1.setBounds(216, 321, 144, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1);
		
		txtPEstoqueM = new JTextField();
		txtPEstoqueM.setColumns(10);
		txtPEstoqueM.setBounds(310, 318, 96, 20);
		getContentPane().add(txtPEstoqueM);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Unidade");
		lblNewLabel_1_1_1_1_1_2.setBounds(48, 364, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_2);
		
		JComboBox cboPUnidade = new JComboBox();
		cboPUnidade.setModel(new DefaultComboBoxModel(new String[] {"", "UN", "CX", "P\u00C7", "KG", "PCT", "M"}));
		cboPUnidade.setBounds(112, 360, 68, 22);
		getContentPane().add(cboPUnidade);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Local");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(48, 408, 86, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);
		
		txtPLocal = new JTextField();
		txtPLocal.setColumns(10);
		txtPLocal.setBounds(112, 405, 294, 20);
		getContentPane().add(txtPLocal);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(521, 209, 108, 20);
		getContentPane().add(dateChooser);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Entrada");
		lblNewLabel_1_1_1_2.setBounds(457, 215, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Validade");
		lblNewLabel_1_1_1_2_1.setBounds(647, 215, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2_1);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(711, 209, 108, 20);
		getContentPane().add(dateChooser_1);
		
		txtPCusto = new JTextField();
		txtPCusto.setColumns(10);
		txtPCusto.setBounds(521, 251, 96, 20);
		getContentPane().add(txtPCusto);
		
		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Custo");
		lblNewLabel_1_1_1_2_2.setBounds(457, 254, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2_2);
		
		JLabel lblNewLabel_1_1_1_2_2_1 = new JLabel("Lucro(%)");
		lblNewLabel_1_1_1_2_2_1.setBounds(647, 254, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2_2_1);
		
		txtPLucro = new JTextField();
		txtPLucro.setColumns(10);
		txtPLucro.setBounds(711, 251, 96, 20);
		getContentPane().add(txtPLucro);

	}//Fim do construtor
	
	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnAlterar;
	
	/**
	 * Método responsável pela pesquisa avançada do Fornecedor
	 * usando o nome de fantasia e a biblioteca rs2xml
	 */
	private void pesquisarFornecedorTabela() {
		String readT = "select idfor as ID, fantasia as Fornecedor, fone as Fone, contato as Contato from fornecedores where fantasia like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(readT);
			pst.setString(1, txtFPesquisar.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// Uso da biblioteca rs2xml para "popular" a tabela
			tblFornecedores.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void setarCaixasTexto() {
		// Criar uma variável para receber a linha da tabela
		int setar = tblFornecedores.getSelectedRow();
		txtForID.setText(tblFornecedores.getModel().getValueAt(setar, 0).toString());
	}
	/**
	 * Limpar Campos
	 */
	private void limparCamposFornecedor() {
		((DefaultTableModel)tblFornecedores.getModel()).setRowCount(0);
	}
	
}// Fim do código
