package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cliente extends JDialog {
	private JTextField txtCliId;
	private JTextField txtCliNome;
	private JTextField txtCliData;
	private JTextField txtCliFone;
	private JTextField txtCliCPF;
	private JTextField txtCliEmail;
	private JTextField txtCliCEP;
	private JTextField txtCliEndereco;
	private JTextField txtCliNumero;
	private JTextField txtCliComplemento;
	private JTextField txtCliBairro;
	private JTextField txtCliCidade;

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
		lblFornecedor.setBounds(395, 141, 85, 14);
		getContentPane().add(lblFornecedor);
		
		txtCliId = new JTextField();
		txtCliId.setEditable(false);
		txtCliId.setColumns(10);
		txtCliId.setBounds(490, 138, 69, 20);
		getContentPane().add(txtCliId);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(72, 141, 69, 14);
		getContentPane().add(lblNome);
		
		txtCliNome = new JTextField();
		txtCliNome.setColumns(10);
		txtCliNome.setBounds(171, 138, 204, 20);
		getContentPane().add(txtCliNome);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(72, 168, 121, 14);
		getContentPane().add(lblDataDeNascimento);
		
		txtCliData = new JTextField();
		txtCliData.setColumns(10);
		txtCliData.setBounds(207, 165, 168, 20);
		getContentPane().add(txtCliData);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(72, 199, 69, 14);
		getContentPane().add(lblFone);
		
		txtCliFone = new JTextField();
		txtCliFone.setColumns(10);
		txtCliFone.setBounds(173, 193, 168, 20);
		getContentPane().add(txtCliFone);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(72, 230, 69, 14);
		getContentPane().add(lblCpf);
		
		txtCliCPF = new JTextField();
		txtCliCPF.setColumns(10);
		txtCliCPF.setBounds(173, 227, 168, 20);
		getContentPane().add(txtCliCPF);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(72, 261, 69, 14);
		getContentPane().add(lblEmail);
		
		txtCliEmail = new JTextField();
		txtCliEmail.setColumns(10);
		txtCliEmail.setBounds(171, 258, 235, 20);
		getContentPane().add(txtCliEmail);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(76, 344, 49, 14);
		getContentPane().add(lblCep);
		
		txtCliCEP = new JTextField();
		txtCliCEP.setColumns(10);
		txtCliCEP.setBounds(155, 341, 156, 20);
		getContentPane().add(txtCliCEP);
		
		btnCliCEP = new JButton("Buscar CEP");
		btnCliCEP.setEnabled(false);
		btnCliCEP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCliCEP.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCliCEP.requestFocus();
				} else {
					buscarCEP();
				}
			}
		});
		btnCliCEP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliCEP.setBounds(321, 340, 140, 23);
		getContentPane().add(btnCliCEP);
		
		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(76, 381, 69, 14);
		getContentPane().add(lblEndereco);
		
		txtCliEndereco = new JTextField();
		txtCliEndereco.setColumns(10);
		txtCliEndereco.setBounds(155, 378, 267, 20);
		getContentPane().add(txtCliEndereco);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setBounds(432, 381, 49, 14);
		getContentPane().add(lblNumero);
		
		txtCliNumero = new JTextField();
		txtCliNumero.setColumns(10);
		txtCliNumero.setBounds(491, 378, 140, 20);
		getContentPane().add(txtCliNumero);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(76, 412, 81, 14);
		getContentPane().add(lblComplemento);
		
		txtCliComplemento = new JTextField();
		txtCliComplemento.setColumns(10);
		txtCliComplemento.setBounds(165, 409, 257, 20);
		getContentPane().add(txtCliComplemento);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(432, 412, 44, 14);
		getContentPane().add(lblBairro);
		
		txtCliBairro = new JTextField();
		txtCliBairro.setColumns(10);
		txtCliBairro.setBounds(491, 409, 161, 20);
		getContentPane().add(txtCliBairro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(76, 440, 69, 14);
		getContentPane().add(lblCidade);
		
		txtCliCidade = new JTextField();
		txtCliCidade.setColumns(10);
		txtCliCidade.setBounds(155, 437, 186, 20);
		getContentPane().add(txtCliCidade);
		
		JLabel lblUf = new JLabel("U.F");
		lblUf.setBounds(432, 440, 44, 14);
		getContentPane().add(lblUf);
		
		cboCliUF = new JComboBox();
		cboCliUF.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboCliUF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboCliUF.setBounds(490, 436, 57, 22);
		getContentPane().add(cboCliUF);
		
		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Cliente.class.getResource("/img/adicionar.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setDefaultCapable(false);
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setToolTipText("Adicionar Cliente");
		btnAdicionar.setBounds(72, 478, 64, 64);
		getContentPane().add(btnAdicionar);
		
		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setDefaultCapable(false);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(Cliente.class.getResource("/img/remover.png")));
		btnExcluir.setToolTipText("Remover Cliente");
		btnExcluir.setBounds(168, 478, 64, 64);
		getContentPane().add(btnExcluir);
		
		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setDefaultCapable(false);
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setIcon(new ImageIcon(Cliente.class.getResource("/img/alterar.png")));
		btnAlterar.setToolTipText("Alterar Cliente");
		btnAlterar.setBounds(264, 478, 64, 64);
		getContentPane().add(btnAlterar);
		
		cboCliMarketing = new JComboBox();
		cboCliMarketing.setModel(new DefaultComboBoxModel(new String[] {"", "SIM", "N\u00C3O"}));
		cboCliMarketing.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboCliMarketing.setBounds(171, 289, 81, 22);
		getContentPane().add(cboCliMarketing);
		
		JLabel lblMarketing = new JLabel("Marketing");
		lblMarketing.setBounds(72, 293, 69, 14);
		getContentPane().add(lblMarketing);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(72, 41, 64, 14);
		getContentPane().add(lblCliente);
		
		txtPesquisarCliente = new JTextField();
		txtPesquisarCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarClienteTabela();
			}
		});
		txtPesquisarCliente.setColumns(10);
		txtPesquisarCliente.setBounds(116, 38, 140, 20);
		getContentPane().add(txtPesquisarCliente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 66, 559, 64);
		getContentPane().add(scrollPane);
		
		tblClientes = new JTable();
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Evento clicar com o mouse na tabela
				limparCampos();
				setarCaixasTexto();
				limparCamposCliente();
			}
		});
		scrollPane.setViewportView(tblClientes);
		
		btnPesquisar = new JButton("Buscar");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarCliente();
			}
		});
		btnPesquisar.setBounds(266, 37, 89, 23);
		getContentPane().add(btnPesquisar);
		
		// Validação com o uso da biblioteca Atxy2k
		RestrictedTextField validarId = new RestrictedTextField(txtCliId);
		validarId.setOnlyNums(true);
		validarId.setLimit(4);
		RestrictedTextField validarNome = new RestrictedTextField(txtCliNome);
		validarNome.setLimit(50);
		RestrictedTextField validarFone = new RestrictedTextField(txtCliFone);
		validarFone.setOnlyNums(true);
		validarFone.setLimit(10);
		RestrictedTextField validarData = new RestrictedTextField(txtCliData);
		validarData.setOnlyNums(true);
		validarData.setLimit(8);
		RestrictedTextField validarCPF = new RestrictedTextField(txtCliCPF);
		validarCPF.setOnlyNums(true);
		validarCPF.setLimit(11);
		RestrictedTextField validarEmail = new RestrictedTextField(txtCliEmail);
		validarEmail.setLimit(50);
		RestrictedTextField validarCEP = new RestrictedTextField(txtCliCEP);
		validarCEP.setOnlyNums(true);
		validarCEP.setLimit(8);
		RestrictedTextField validarEndereco = new RestrictedTextField(txtCliEndereco);
		validarEndereco.setLimit(100);
		RestrictedTextField validarNumero = new RestrictedTextField(txtCliNumero);
		validarNumero.setOnlyNums(true);
		validarNumero.setLimit(10);
		RestrictedTextField validarComplemento = new RestrictedTextField(txtCliComplemento);
		validarComplemento.setLimit(100);
		RestrictedTextField validarBairro = new RestrictedTextField(txtCliBairro);
		validarBairro.setLimit(50);
		RestrictedTextField validarCidade = new RestrictedTextField(txtCliCidade);
		validarCidade.setLimit(50);
		
		getRootPane().setDefaultButton(btnPesquisar);

	}//fim do construtor
	
	DAO dao = new DAO();
	private JTextField txtPesquisarCliente;
	private JButton btnPesquisar;
	private JTable tblClientes;
	private JComboBox cboCliUF;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnCliCEP;
	private JComboBox cboCliMarketing;
	
	/**
	 * Método responsável por buscar o CEP
	 */
	private void buscarCEP() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCliCEP.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
		        Element element = it.next();
		        if (element.getQualifiedName().equals("cidade")) {
		        	txtCliCidade.setText(element.getText());
		        }
		        if (element.getQualifiedName().equals("bairro")) {
		        	txtCliBairro.setText(element.getText());
		        }
		        if (element.getQualifiedName().equals("uf")) {
		        	cboCliUF.setSelectedItem(element.getText()); 
		        }
		        if (element.getQualifiedName().equals("tipo_logradouro")) {
		        	tipoLogradouro = element.getText();
		        }
		        if (element.getQualifiedName().equals("logradouro")) {
		        	logradouro = element.getText();
		        }
		        if (element.getQualifiedName().equals("resultado")) {
		        	resultado = element.getText();
		        	if (resultado.equals("1")) {
		
		        	} else {
		        		JOptionPane.showMessageDialog(null, "CEP não encontrado");
		        	}
		        }

		    }
			// Setar Campo Endereço
			txtCliEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Método responsável pela pesquisa avançada do Fornecedor
	 * usando o nome de fantasia e a biblioteca rs2xml
	 */
	private void pesquisarClienteTabela() {
		String readT = "select idcli as ID, nome as Nome, fone as Telefone from clientes where nome like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(readT);
			pst.setString(1, txtPesquisarCliente.getText() + "%");
			ResultSet rs = pst.executeQuery();
			//if(rs.next()) {
				// Uso da biblioteca rs2xml para "popular" a tabela
				tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
				
			//} else {
			//	btnAdicionar.setEnabled(true);
			//}
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Método responsável por setar as caixas de texto
	 * de acordo com os campos da tabela
	 */
	private void setarCaixasTexto() {
		// Criar uma variável para receber a linha da tabela
		int setar = tblClientes.getSelectedRow();
		txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
		txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
		txtPesquisarCliente.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
	}
	
	/**
	 * Limpar Campos
	 */
	private void limparCamposCliente() {
		((DefaultTableModel)tblClientes.getModel()).setRowCount(0);
	}
	
	/**
	 * Método responsável de pesquisar
	 */
	private void pesquisarCliente() {
		// Validação
		if (txtPesquisarCliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do cliente");
			txtPesquisarCliente.requestFocus();
		} else {
			String read = "select * from clientes where nome = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtPesquisarCliente.getText());
				ResultSet rs = pst.executeQuery();
				limparCampos();
				if (rs.next()) {
					txtCliId.setText(rs.getString(1));
					txtCliNome.setText(rs.getString(2));
					txtCliFone.setText(rs.getString(3));
					txtCliData.setText(rs.getString(4));
					txtCliCPF.setText(rs.getString(5));
					txtCliEmail.setText(rs.getString(6));
					cboCliMarketing.setSelectedItem(rs.getString(7));
					txtCliCEP.setText(rs.getString(8));
					txtCliEndereco.setText(rs.getString(9));
					txtCliNumero.setText(rs.getString(10));
					txtCliComplemento.setText(rs.getString(11));
					txtCliBairro.setText(rs.getString(12));
					txtCliCidade.setText(rs.getString(13));
					cboCliUF.setSelectedItem(rs.getString(14));
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnCliCEP.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
					limparCamposCliente();
					btnCliCEP.setEnabled(true);
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
	 * Método responsável por adicionar
	 */
	private void adicionarCliente() {
		// Validação
		
		if (txtCliNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do cliente");
			txtCliNome.requestFocus();
		}	else if (txtCliFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o telefone do cliente");
			txtCliFone.requestFocus();			
		}	else if (txtCliData.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a data de nascimento do cliente");
			txtCliData.requestFocus();	
		}	else if (cboCliMarketing.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a opção de marketing do cliente");
			cboCliMarketing.requestFocus();			
		}	else {
			// Lógica Principal
			String create = "insert into clientes(nome, fone, datanasc, cpf, email, marketing, cep, endereco, numero, complemento, bairro, cidade, uf) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execuçãoo da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ???? pelo conteúdo da caixa de texto
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliFone.getText());
				pst.setString(3, txtCliData.getText());
				pst.setString(4, txtCliCPF.getText());
				pst.setString(5, txtCliEmail.getText());
				pst.setString(6, cboCliMarketing.getSelectedItem().toString());
				pst.setString(7, txtCliCEP.getText());
				pst.setString(8, txtCliEndereco.getText());
				pst.setString(9, txtCliNumero.getText());
				pst.setString(10, txtCliComplemento.getText());
				pst.setString(11, txtCliBairro.getText());
				pst.setString(12, txtCliCidade.getText());
				pst.setString(13, cboCliUF.getSelectedItem().toString());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
				limparCampos();
				con.close();
			} catch(SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CPF em uso.\nEscolha outro CPF.");
				txtCliCPF.setText(null);
				txtCliCPF.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
			
	}
	
	/**
	 * Método responsável por alterar
	 */
	private void alterarCliente() {
		// Validação
		
		if (txtCliNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do cliente");
			txtCliNome.requestFocus();
		}	else if (txtCliFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o telefone do cliente");
			txtCliFone.requestFocus();			
		}	else if (txtCliData.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a data de nascimento do cliente");
			txtCliData.requestFocus();	
		}	else if (cboCliMarketing.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a opção de marketing do cliente");
			cboCliMarketing.requestFocus();			
		}	else {
			// Lógica Principal
			String update = "update clientes set nome=?, fone=?, datanasc=?, cpf=?, email=?, marketing=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, uf=? where idcli=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ???? pelo conteúdo da caixa de texto
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliFone.getText());
				pst.setString(3, txtCliData.getText());
				pst.setString(4, txtCliCPF.getText());
				pst.setString(5, txtCliEmail.getText());
				pst.setString(6, cboCliMarketing.getSelectedItem().toString());
				pst.setString(7, txtCliCEP.getText());
				pst.setString(8, txtCliEndereco.getText());
				pst.setString(9, txtCliNumero.getText());
				pst.setString(10, txtCliComplemento.getText());
				pst.setString(11, txtCliBairro.getText());
				pst.setString(12, txtCliCidade.getText());
				pst.setString(13, cboCliUF.getSelectedItem().toString());
				pst.setString(14, txtCliId.getText());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
				limparCamposCliente();
				limparCampos();
				con.close();
			} catch(SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CPF em uso.\nEscolha outro CPF.");
				txtCliCPF.setText(null);
				txtCliCPF.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
			
	}
	
	private void excluirCliente() {
		// Validação (confirmação de exclusão)
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja confirmar a exclusão do cliente ?","Atenção!",JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// Lógica principal 
			String delete = "delete from clientes where idcli=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtCliId.getText());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
				limparCamposCliente();
				limparCampos();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
	}
	
	
	
	
	private void limparCampos() {
		txtPesquisarCliente.setText(null);
		txtCliNome.setText(null);
		txtCliFone.setText(null);
		txtCliData.setText(null);
		txtCliCPF.setText(null);
		txtCliEmail.setText(null);
		cboCliMarketing.setSelectedItem("");
		txtCliCEP.setText(null);
		txtCliEndereco.setText(null);
		txtCliNumero.setText(null);
		txtCliComplemento.setText(null);
		txtCliBairro.setText(null);
		txtCliCidade.setText(null);
		txtCliId.setText(null);
		cboCliUF.setSelectedItem("");
		btnAdicionar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}// fim do código
