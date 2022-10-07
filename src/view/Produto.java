package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Produto extends JDialog {
	private JTextField txtBarcode;
	private JTextField txtID;
	private JTextField txtFPesquisar;
	private JTextField txtForID;
	private JTable tblFornecedores;
	private JTextField txtFabricante;
	private JTextField txtEstoque;
	private JTextField txtEstoqueMinimo;
	private JTextField txtLocal;
	private JTextField txtCusto;
	private JTextField txtLucro;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtBarcode.requestFocus();
			}
		});
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produto.class.getResource("/img/produtos1.png")));
		setTitle("Produtos");
		setBounds(150, 150, 866, 579);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Produto.class.getResource("/img/barcode.png")));
		lblNewLabel.setBounds(36, 198, 64, 45);
		getContentPane().add(lblNewLabel);
		
		txtBarcode = new JTextField();
		txtBarcode.addKeyListener(new KeyAdapter() {
			@Override
			//evento usado no leitor de código de barras
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				pesquisarProdutoBarcode();
				}
			}
		});
		txtBarcode.setBounds(110, 209, 294, 20);
		getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JPanel PanelFornecedor = new JPanel();
		PanelFornecedor.setBorder(new TitledBorder(null, "Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelFornecedor.setBounds(454, 177, 381, 161);
		getContentPane().add(PanelFornecedor);
		PanelFornecedor.setLayout(null);
		
		txtFPesquisar = new JTextField();
		txtFPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarFornecedorTabela();
			}
		});
		txtFPesquisar.setBounds(10, 27, 147, 20);
		PanelFornecedor.add(txtFPesquisar);
		txtFPesquisar.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(167, 15, 32, 32);
		lblNewLabel_2.setIcon(new ImageIcon(Produto.class.getResource("/img/search1.png")));
		PanelFornecedor.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(222, 30, 46, 14);
		PanelFornecedor.add(lblNewLabel_3);
		
		txtForID = new JTextField();
		txtForID.setBounds(253, 27, 103, 20);
		PanelFornecedor.add(txtForID);
		txtForID.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 361, 92);
		PanelFornecedor.add(scrollPane);
		
		tblFornecedores = new JTable();
		tblFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
				limparCamposFornecedor();
			}
		});
		scrollPane.setViewportView(tblFornecedores);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_1_1_1.setBounds(46, 257, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarProduto();
			}
		});
		btnAdicionar.setName("BtnAdicionar");
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setIcon(new ImageIcon(Produto.class.getResource("/img/create.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(454, 470, 64, 64);
		getContentPane().add(btnAdicionar);
		
		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarProduto();
			}
		});
		btnAlterar.setName("BtnAlterar");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setIcon(new ImageIcon(Produto.class.getResource("/img/update.png")));
		btnAlterar.setToolTipText("Atualizar");
		btnAlterar.setBounds(540, 470, 64, 64);
		getContentPane().add(btnAlterar);
		
		btnRemover = new JButton("");
		btnRemover.setEnabled(false);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
			}
		});
		btnRemover.setName("btnRemover");
		btnRemover.setContentAreaFilled(false);
		btnRemover.setBorderPainted(false);
		btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemover.setIcon(new ImageIcon(Produto.class.getResource("/img/delete.png")));
		btnRemover.setToolTipText("Remover");
		btnRemover.setBounds(625, 470, 64, 64);
		getContentPane().add(btnRemover);
		
		txtFabricante = new JTextField();
		txtFabricante.setColumns(10);
		txtFabricante.setBounds(110, 350, 294, 20);
		getContentPane().add(txtFabricante);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Fabricante");
		lblNewLabel_1_1_1_1.setBounds(46, 353, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Estoque");
		lblNewLabel_1_1_1_1_1.setBounds(46, 399, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		txtEstoque = new JTextField();
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(110, 396, 78, 20);
		getContentPane().add(txtEstoque);
		
		JLabel ex = new JLabel("Estoque M\u00EDnimo");
		ex.setBounds(214, 399, 116, 14);
		getContentPane().add(ex);
		
		txtEstoqueMinimo = new JTextField();
		txtEstoqueMinimo.setColumns(10);
		txtEstoqueMinimo.setBounds(308, 396, 96, 20);
		getContentPane().add(txtEstoqueMinimo);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Unidade");
		lblNewLabel_1_1_1_1_1_2.setBounds(46, 442, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_2);
		
		cboUnidade = new JComboBox();
		cboUnidade.setModel(new DefaultComboBoxModel(new String[] {"", "UN", "CX", "P\u00C7", "KG", "PCT", "M"}));
		cboUnidade.setBounds(110, 438, 68, 22);
		getContentPane().add(cboUnidade);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Local");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(46, 486, 86, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);
		
		txtLocal = new JTextField();
		txtLocal.setColumns(10);
		txtLocal.setBounds(110, 483, 294, 20);
		getContentPane().add(txtLocal);
		
		dataEntrada = new JDateChooser();
		dataEntrada.setBounds(518, 362, 171, 20);
		getContentPane().add(dataEntrada);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Entrada");
		lblNewLabel_1_1_1_2.setBounds(454, 368, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2);
		
		txtCusto = new JTextField();
		txtCusto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (somente números ao digitar)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCusto.setColumns(10);
		txtCusto.setBounds(518, 393, 96, 20);
		getContentPane().add(txtCusto);
		
		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Custo");
		lblNewLabel_1_1_1_2_2.setBounds(454, 396, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2_2);
		
		JLabel lblNewLabel_1_1_1_2_2_1 = new JLabel("Lucro(%)");
		lblNewLabel_1_1_1_2_2_1.setBounds(644, 396, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2_2_1);
		
		txtLucro = new JTextField();
		txtLucro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (somente números ao digitar)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtLucro.setColumns(10);
		txtLucro.setBounds(708, 393, 96, 20);
		getContentPane().add(txtLucro);
		
		txtDescricao = new JTextArea();
		txtDescricao.setBounds(110, 254, 294, 85);
		getContentPane().add(txtDescricao);
		
		JLabel lblNewLabel_1_1_1_1_1_2_1 = new JLabel("Setor");
		lblNewLabel_1_1_1_1_1_2_1.setBounds(214, 442, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_2_1);
		
		cboSetor = new JComboBox();
		cboSetor.setModel(new DefaultComboBoxModel(new String[] {"", "Rolamento", "Roda", "Shape", "Truck", "Lixa", "Borracha", "N\u00E3o Obrigat\u00F3rio"}));
		cboSetor.setBounds(308, 438, 96, 22);
		getContentPane().add(cboSetor);
		RestrictedTextField validarBarcode = new RestrictedTextField(txtBarcode);
		validarBarcode.setOnlyNums(true);
		validarBarcode.setLimit(13);
		RestrictedTextField validarFabricante = new RestrictedTextField(txtFabricante);
		validarFabricante.setLimit(50);
		RestrictedTextField validarEstoque = new RestrictedTextField(txtEstoque);
		validarEstoque.setOnlyNums(true);
		validarEstoque.setLimit(5);
		RestrictedTextField validarEstoqueMinimo = new RestrictedTextField(txtEstoqueMinimo);
		validarEstoqueMinimo.setOnlyNums(true);
		validarEstoqueMinimo.setLimit(5);
		RestrictedTextField validarLocal = new RestrictedTextField(txtLocal);
		validarLocal.setLimit(50);
		RestrictedTextField validarCusto = new RestrictedTextField(txtCusto);
		validarCusto.setLimit(10);
		RestrictedTextField validarLucro = new RestrictedTextField(txtLucro);
		validarLucro.setLimit(10);
		RestrictedTextField validarForId = new RestrictedTextField(txtForID);
		
		
		panelProdutos = new JPanel();
		panelProdutos.setBorder(new TitledBorder(null, "Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProdutos.setBounds(36, 11, 799, 161);
		getContentPane().add(panelProdutos);
		panelProdutos.setLayout(null);
		
		txtPesquisarProduto = new JTextField();
		txtPesquisarProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarProdutoTabela();
			}
		});
		txtPesquisarProduto.setColumns(10);
		txtPesquisarProduto.setBounds(10, 31, 266, 20);
		panelProdutos.add(txtPesquisarProduto);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 62, 779, 88);
		panelProdutos.add(scrollPane_1);
		
		tblProdutos = new JTable();
		tblProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTextoProduto();
				limparCamposProduto();
			}
		});
		scrollPane_1.setViewportView(tblProdutos);
		
		btnPPesquisar = new JButton("Pesquisar");
		btnPPesquisar.setBounds(286, 30, 96, 23);
		panelProdutos.add(btnPPesquisar);
		
		txtID = new JTextField();
		txtID.setBounds(466, 31, 96, 20);
		panelProdutos.add(txtID);
		txtID.setEditable(false);
		txtID.setColumns(10);
		
				
				// Validação com o uso da biblioteca Atxy2k
				RestrictedTextField validarId = new RestrictedTextField(txtID);
				validarForId.setOnlyNums(true);
				validarForId.setLimit(4);
				
				JLabel lblNewLabel_1 = new JLabel("C\u00F3digo");
				lblNewLabel_1.setBounds(402, 34, 64, 14);
				panelProdutos.add(lblNewLabel_1);
		btnPPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProduto();
			}
		});
		
		
	}//Fim do construtor
	
	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JComboBox cboSetor;
	private JComboBox cboUnidade;
	private JTextArea txtDescricao;
	private JButton btnNewButton;
	private JButton btnProAlterar;
	private JButton btnRemover;
	private JDateChooser dataEntrada;
	private JPanel panelProdutos;
	private JTextField txtPesquisarProduto;
	private JTable tblProdutos;
	private JButton btnPPesquisar;
	
	
	
	private void pesquisarProdutoTabela() {
		String readT = "select id as ID, produto as Produto, fabricante as Fabricante, estoque as Estoque from produtos where produto like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(readT);
			pst.setString(1, txtPesquisarProduto.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// Uso da biblioteca rs2xml para "popular" a tabela
			tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void setarCaixasTextoProduto() {
		// Criar uma variável para receber a linha da tabela
		int setar = tblProdutos.getSelectedRow();
		txtPesquisarProduto.setText(tblProdutos.getModel().getValueAt(setar, 1).toString());
		txtID.setText(tblProdutos.getModel().getValueAt(setar, 0).toString());
	}
	
	private void limparCamposProduto() {
		((DefaultTableModel)tblProdutos.getModel()).setRowCount(0);
	}
	
	
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
	
	/**
	 * Método responsavel por pesquisar por código de barra
	 */
	private void pesquisarProdutoBarcode() {
		if (txtBarcode.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Escaneie o código de barras do produto");
		txtBarcode.requestFocus();
		} else {
		String read2 = "select * from produtos where barcode = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtBarcode.getText());
			ResultSet rs = pst.executeQuery();
			limparCampos();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				txtBarcode.setText(rs.getString(2));
				txtPesquisarProduto.setText(rs.getString(3));
				txtDescricao.setText(rs.getString(4));
				txtFabricante.setText(rs.getString(5));
				// JCalendar - formatação para exibição
				String setarDataCad = rs.getString(6);
				Date dataVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCad);
				dataEntrada.setDate(dataVal);
				cboSetor.setSelectedItem(rs.getString(7));
				txtEstoque.setText(rs.getString(8));
				txtEstoqueMinimo.setText(rs.getString(9));
				cboUnidade.setSelectedItem(rs.getString(10));
				txtLocal.setText(rs.getString(11));
				txtCusto.setText(rs.getString(12));
				txtLucro.setText(rs.getString(13));
				txtForID.setText(rs.getString(15));
				btnAlterar.setEnabled(true);
				btnRemover.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Produto não cadastrado");
				limparCampos();
				btnAdicionar.setEnabled(true);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
	}
		}
}
	
	/**
	 * Método responsável pela pesquisa de Fornecedores
	 */
	private void pesquisarProduto() {
			if (txtPesquisarProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do produto");
			txtPesquisarProduto.requestFocus();
			} else {
			String read = "select * from produtos where produto = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtPesquisarProduto.getText());
				ResultSet rs = pst.executeQuery();
				limparCampos();
				if (rs.next()) {
					txtID.setText(rs.getString(1));
					txtBarcode.setText(rs.getString(2));
					txtDescricao.setText(rs.getString(4));
					txtFabricante.setText(rs.getString(5));
					// JCalendar - formatação para exibição
					String setarDataCad = rs.getString(6);
					Date dataVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCad);
					dataEntrada.setDate(dataVal);
					cboSetor.setSelectedItem(rs.getString(7));
					txtEstoque.setText(rs.getString(8));
					txtEstoqueMinimo.setText(rs.getString(9));
					cboUnidade.setSelectedItem(rs.getString(10));
					txtLocal.setText(rs.getString(11));
					txtCusto.setText(rs.getString(12));
					txtLucro.setText(rs.getString(13));
					txtForID.setText(rs.getString(15));
					btnAlterar.setEnabled(true);
					btnRemover.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Produto não cadastrado");
					limparCampos();
					txtBarcode.setText(null);
					btnAdicionar.setEnabled(true);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
		}
			}
	}
	
	
	
	/**
	 * Método Responsável por 
	 */
	private void adicionarProduto() {
		// Validação
		
		if (txtPesquisarProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do produto");
			txtPesquisarProduto.requestFocus();
		}	else if (txtDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a descrição do produto");
			txtDescricao.requestFocus();
		}	else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o fabricante do produto");
			txtFabricante.requestFocus();
		}	else if (cboSetor.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o setor do produto");
			cboSetor.requestFocus();
		}	else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o estoque do produto");
			txtEstoque.requestFocus();
		}	else if (txtEstoqueMinimo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o estoque mínimo do produto");
			txtEstoqueMinimo.requestFocus();
		}	else if (cboUnidade.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite a unidade do produto");
			cboUnidade.requestFocus();
		}	else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o custo do produto");
			txtCusto.requestFocus();
		}	else if (txtLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o lucro do produto");
			txtLucro.requestFocus();
		}	else if (txtForID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a id do fornecedor");
			txtForID.requestFocus();
		}
		else {
			// Lógica Principal
			String create = "insert into produtos(barcode, produto, descricao,fabricante,setor,estoque,estoquemin,unidade,localizacao,custo,lucro,idfor) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ???? pelo conteúdo da caixa de texto
				
				pst.setString(1, txtBarcode.getText());
				pst.setString(2, txtPesquisarProduto.getText());
				pst.setString(3, txtDescricao.getText());
				pst.setString(4, txtFabricante.getText());
				pst.setString(5, cboSetor.getSelectedItem().toString());
				pst.setString(6, txtEstoque.getText());
				pst.setString(7, txtEstoqueMinimo.getText());
				pst.setString(8, cboUnidade.getSelectedItem().toString());
				pst.setString(9, txtLocal.getText());
				pst.setString(10, txtCusto.getText());
				pst.setString(11, txtLucro.getText());
				pst.setString(12, txtForID.getText());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
				limparCamposFornecedor();
				txtPesquisarProduto.setText(null);
				limparCampos();
				txtBarcode.setText(null);
				con.close();
			} catch(SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Barcode duplicado escolha outro.");
				txtBarcode.setText(null);
				txtBarcode.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
			
	}
	
	
	private void alterarProduto() {
		// Validação
		
			if (txtPesquisarProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do produto");
			txtPesquisarProduto.requestFocus();
		}	else if (txtDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a descrição do produto");
			txtDescricao.requestFocus();
		}	else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o fabricante do produto");
			txtFabricante.requestFocus();
		}	else if (cboSetor.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o setor do produto");
			cboSetor.requestFocus();
		}	else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o estoque do produto");
			txtEstoque.requestFocus();
		}	else if (txtEstoqueMinimo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o estoque minimo do produto");
			txtEstoqueMinimo.requestFocus();
		}	else if (cboUnidade.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite a unidade do produto");
			cboUnidade.requestFocus();
		}	else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o custo do produto");
			txtCusto.requestFocus();
		}	else if (txtLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o lucro do produto");
			txtLucro.requestFocus();
		}	else if (txtForID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a id do fornecedor");
			txtForID.requestFocus();
		}	
		else {
			// Lógica Principal
			String update = "update produtos set barcode=?, produto=?, descricao=?, fabricante=?, datacad=?, setor=?, estoque=?, estoquemin=?, unidade=?, localizacao=?, custo=?, lucro=?, idfor=? where id=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(update);
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataVal = formatador.format(dataEntrada.getDate());
				// Substituir o ???? pelo conteúdo da caixa de texto
				pst.setString(1, txtBarcode.getText());
				pst.setString(2, txtPesquisarProduto.getText());
				pst.setString(3, txtDescricao.getText());
				pst.setString(4, txtFabricante.getText());
				pst.setString(5, dataVal);
				pst.setString(6, cboSetor.getSelectedItem().toString());
				pst.setString(7, txtEstoque.getText());
				pst.setString(8, txtEstoqueMinimo.getText());
				pst.setString(9, cboUnidade.getSelectedItem().toString());
				pst.setString(10, txtLocal.getText());
				pst.setString(11, txtCusto.getText());
				pst.setString(12, txtLucro.getText());
				pst.setString(13, txtForID.getText());
				pst.setString(14, txtID.getText());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
				limparCamposFornecedor();
				txtPesquisarProduto.setText(null);
				limparCampos();
				txtBarcode.setText(null);
				con.close();
			} catch(SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Barcode duplicado escolha outro.");
				txtBarcode.setText(null);
				txtBarcode.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
			
	}
	
	private void excluirProduto() {
		// Validação (confirmação de exclusão)
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja confirmar a exclusão do produto ?","Atenção!",JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// Lógica principal 
			String delete = "delete from produtos where id=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
				limparCamposFornecedor();
				limparCampos();
				txtPesquisarProduto.setText(null);
				txtBarcode.setText(null);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
	}
	
	private void limparCampos() {
		txtID.setText(null);
		txtForID.setText(null);
		txtDescricao.setText(null);
		txtFabricante.setText(null);
		cboSetor.setSelectedItem("");
		txtEstoque.setText(null);
		txtEstoqueMinimo.setText(null);
		cboUnidade.setSelectedItem("");
		txtLocal.setText(null);
		txtCusto.setText(null);
		txtLucro.setText(null);
		dataEntrada.setDate(null);
		btnAdicionar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnRemover.setEnabled(false);
	}
}// Fim do código
