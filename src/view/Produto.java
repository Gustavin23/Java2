package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Produto extends JDialog {
	private JTextField txtBarcode;
	private JTextField txtID;
	private JTextField txtFPesquisar;
	private JTextField txtForID;
	private JTable tblFornecedores;
	private JTextField txtProduto;
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
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(112, 50, 294, 20);
		getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(48, 99, 64, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtID = new JTextField();
		txtID.setBounds(112, 96, 188, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		JButton btnPPesquisar = new JButton("Pesquisar");
		btnPPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProduto();
			}
		});
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
		
		txtProduto = new JTextField();
		txtProduto.setColumns(10);
		txtProduto.setBounds(112, 134, 294, 20);
		getContentPane().add(txtProduto);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_1_1_1.setBounds(48, 179, 64, 14);
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
		btnAdicionar.setBounds(457, 364, 64, 64);
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
		btnAlterar.setBounds(543, 364, 64, 64);
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
		btnRemover.setBounds(628, 364, 64, 64);
		getContentPane().add(btnRemover);
		
		txtFabricante = new JTextField();
		txtFabricante.setColumns(10);
		txtFabricante.setBounds(112, 272, 294, 20);
		getContentPane().add(txtFabricante);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Fabricante");
		lblNewLabel_1_1_1_1.setBounds(48, 275, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Estoque");
		lblNewLabel_1_1_1_1_1.setBounds(48, 321, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		txtEstoque = new JTextField();
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(112, 318, 78, 20);
		getContentPane().add(txtEstoque);
		
		JLabel ex = new JLabel("Estoque M\u00EDnimo");
		ex.setBounds(216, 321, 116, 14);
		getContentPane().add(ex);
		
		txtEstoqueMinimo = new JTextField();
		txtEstoqueMinimo.setColumns(10);
		txtEstoqueMinimo.setBounds(310, 318, 96, 20);
		getContentPane().add(txtEstoqueMinimo);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Unidade");
		lblNewLabel_1_1_1_1_1_2.setBounds(48, 364, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_2);
		
		cboUnidade = new JComboBox();
		cboUnidade.setModel(new DefaultComboBoxModel(new String[] {"", "UN", "CX", "P\u00C7", "KG", "PCT", "M"}));
		cboUnidade.setBounds(112, 360, 68, 22);
		getContentPane().add(cboUnidade);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Local");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(48, 408, 86, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);
		
		txtLocal = new JTextField();
		txtLocal.setColumns(10);
		txtLocal.setBounds(112, 405, 294, 20);
		getContentPane().add(txtLocal);
		
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
		
		txtCusto = new JTextField();
		txtCusto.setColumns(10);
		txtCusto.setBounds(521, 251, 96, 20);
		getContentPane().add(txtCusto);
		
		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Custo");
		lblNewLabel_1_1_1_2_2.setBounds(457, 254, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2_2);
		
		JLabel lblNewLabel_1_1_1_2_2_1 = new JLabel("Lucro(%)");
		lblNewLabel_1_1_1_2_2_1.setBounds(647, 254, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2_2_1);
		
		txtLucro = new JTextField();
		txtLucro.setColumns(10);
		txtLucro.setBounds(711, 251, 96, 20);
		getContentPane().add(txtLucro);
		
		txtDescricao = new JTextArea();
		txtDescricao.setBounds(112, 176, 294, 85);
		getContentPane().add(txtDescricao);
		
		JLabel lblNewLabel_1_1_1_1_1_2_1 = new JLabel("Setor");
		lblNewLabel_1_1_1_1_1_2_1.setBounds(216, 364, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_2_1);
		
		cboSetor = new JComboBox();
		cboSetor.setModel(new DefaultComboBoxModel(new String[] {"", "Rolamento", "Roda", "Shape", "Truck", "Lixa", "Borracha", "N\u00E3o Obrigat\u00F3rio"}));
		cboSetor.setBounds(310, 360, 96, 22);
		getContentPane().add(cboSetor);
		
		JLabel lblNewLabel_1_1_1_2_2_2 = new JLabel("Venda");
		lblNewLabel_1_1_1_2_2_2.setBounds(457, 289, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1_2_2_2);
		
		txtVenda = new JTextField();
		txtVenda.setColumns(10);
		txtVenda.setBounds(521, 286, 96, 20);
		getContentPane().add(txtVenda);

		
		// Validação com o uso da biblioteca Atxy2k
		RestrictedTextField validarId = new RestrictedTextField(txtID);
		validarId.setOnlyNums(true);
		validarId.setLimit(4);
		RestrictedTextField validarCNPJ = new RestrictedTextField(txtBarcode);
		validarCNPJ.setOnlyNums(true);
		validarCNPJ.setLimit(13);
		RestrictedTextField validarIE = new RestrictedTextField(txtProduto);
		validarIE.setLimit(100);
		RestrictedTextField validarIM = new RestrictedTextField(txtDescricao);
		validarIM.setLimit(100);
		RestrictedTextField validarRazao = new RestrictedTextField(txtFabricante);
		validarRazao.setLimit(100);
		RestrictedTextField validarSite = new RestrictedTextField(txtEstoque);
		validarCNPJ.setOnlyNums(true);
		validarSite.setLimit(5);
		RestrictedTextField validarFone = new RestrictedTextField(txtEstoqueMinimo);
		validarFone.setOnlyNums(true);
		validarFone.setLimit(5);
		RestrictedTextField validarContato = new RestrictedTextField(txtLocal);
		validarContato.setLimit(50);
		RestrictedTextField validarEmail = new RestrictedTextField(txtCusto);
		validarEmail.setLimit(50);
		RestrictedTextField validarCEP = new RestrictedTextField(txtLucro);
		validarCEP.setOnlyNums(true);
		validarCEP.setLimit(8);
		RestrictedTextField validarEndereco = new RestrictedTextField(txtVenda);
		validarEndereco.setLimit(100);
		RestrictedTextField validarNumero = new RestrictedTextField(txtForID);
		validarNumero.setOnlyNums(true);
		validarNumero.setLimit(10);

		
	}//Fim do construtor
	
	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JComboBox cboSetor;
	private JComboBox cboUnidade;
	private JTextArea txtDescricao;
	private JTextField txtVenda;
	private JButton btnNewButton;
	private JButton btnProAlterar;
	private JButton btnRemover;
	
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
	 * Método responsável pela pesquisa de Fornecedores
	 */
	private void pesquisarProduto() {
			String read = "select * from produtos where id = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtID.getText());
				ResultSet rs = pst.executeQuery();
				limparCampos();
				if (rs.next()) {
					txtBarcode.setText(rs.getString(2));
					txtProduto.setText(rs.getString(3));
					txtDescricao.setText(rs.getString(4));
					txtFabricante.setText(rs.getString(5));
					cboSetor.setSelectedItem(rs.getString(8));
					txtEstoque.setText(rs.getString(9));
					txtEstoqueMinimo.setText(rs.getString(10));
					cboUnidade.setSelectedItem(rs.getString(11));
					txtLocal.setText(rs.getString(12));
					txtCusto.setText(rs.getString(13));
					txtLucro.setText(rs.getString(14));
					txtVenda.setText(rs.getString(15));
					txtForID.setText(rs.getString(16));
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
	/**
	 * Método Responsável por 
	 */
	private void adicionarProduto() {
		// Validação
		
		if (txtBarcode.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o barcode do produto");
			txtBarcode.requestFocus();
		}	else if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do produto");
			txtProduto.requestFocus();
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
		}	else if (txtForID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a id do fornecedor");
			txtForID.requestFocus();
		}
		else {
			// Lógica Principal
			String create = "insert into produtos(barcode, produto, descricao,fabricante,setor,estoque,estoquemin,unidade,localizacao,custo,lucro,venda,idfor) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ???? pelo conteúdo da caixa de texto
				pst.setString(1, txtBarcode.getText());
				pst.setString(2, txtProduto.getText());
				pst.setString(3, txtDescricao.getText());
				pst.setString(4, txtFabricante.getText());
				pst.setString(5, cboSetor.getSelectedItem().toString());
				pst.setString(6, txtEstoque.getText());
				pst.setString(7, txtEstoqueMinimo.getText());
				pst.setString(8, cboUnidade.getSelectedItem().toString());
				pst.setString(9, txtLocal.getText());
				pst.setString(10, txtCusto.getText());
				pst.setString(11, txtLucro.getText());
				pst.setString(12, txtVenda.getText());
				pst.setString(13, txtForID.getText());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso");
				limparCampos();
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
		
		if (txtBarcode.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o barcode do produto");
			txtBarcode.requestFocus();
		}	else if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do produto");
			txtProduto.requestFocus();
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
		}	else if (txtForID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a id do fornecedor");
			txtForID.requestFocus();
		}
		else {
			// Lógica Principal
			String update = "update produtos set barcode=?, produto=?, descricao=?, fabricante=?, setor=?, estoque=?, estoquemin=?, unidade=?, localizacao=?, custo=?, lucro=?, venda=?, idfor=? where id=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ???? pelo conteúdo da caixa de texto
				pst.setString(1, txtBarcode.getText());
				pst.setString(2, txtProduto.getText());
				pst.setString(3, txtDescricao.getText());
				pst.setString(4, txtFabricante.getText());
				pst.setString(5, cboSetor.getSelectedItem().toString());
				pst.setString(6, txtEstoque.getText());
				pst.setString(7, txtEstoqueMinimo.getText());
				pst.setString(8, cboUnidade.getSelectedItem().toString());
				pst.setString(9, txtLocal.getText());
				pst.setString(10, txtCusto.getText());
				pst.setString(11, txtLucro.getText());
				pst.setString(12, txtVenda.getText());
				pst.setString(13, txtForID.getText());
				pst.setString(14, txtID.getText());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
				limparCampos();
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
				limparCampos();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
	}
	
	private void limparCampos() {
		txtBarcode.setText(null);
		txtProduto.setText(null);
		txtDescricao.setText(null);
		txtFabricante.setText(null);
		cboSetor.setSelectedItem("");
		txtEstoque.setText(null);
		txtEstoqueMinimo.setText(null);
		cboUnidade.setSelectedItem("");
		txtLocal.setText(null);
		txtCusto.setText(null);
		txtLucro.setText(null);
		txtVenda.setText(null);
		btnAdicionar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnRemover.setEnabled(false);
	}
}// Fim do código
