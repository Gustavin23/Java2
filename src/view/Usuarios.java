package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuId;
	private JTextField txtUsuNome;
	private JTextField txtUsuLogin;
	private JPasswordField txtUsuSenha;

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
		lblNewLabel.setBounds(46, 87, 17, 14);
		getContentPane().add(lblNewLabel);

		txtUsuId = new JTextField();
		txtUsuId.setEditable(false);
		txtUsuId.setBounds(85, 83, 86, 20);
		getContentPane().add(txtUsuId);
		txtUsuId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(36, 58, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtUsuNome = new JTextField();
		txtUsuNome.setColumns(10);
		txtUsuNome.setBounds(85, 55, 136, 20);
		getContentPane().add(txtUsuNome);

		JLabel lblNewLabel_1_1 = new JLabel("Login");
		lblNewLabel_1_1.setBounds(36, 30, 46, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Senha");
		lblNewLabel_1_2.setBounds(36, 114, 46, 14);
		getContentPane().add(lblNewLabel_1_2);

		txtUsuLogin = new JTextField();
		txtUsuLogin.setColumns(10);
		txtUsuLogin.setBounds(85, 27, 136, 20);
		getContentPane().add(txtUsuLogin);

		txtUsuSenha = new JPasswordField();
		txtUsuSenha.setBounds(85, 111, 207, 20);
		getContentPane().add(txtUsuSenha);

		JLabel lblNewLabel_1_2_1 = new JLabel("Perfil");
		lblNewLabel_1_2_1.setBounds(36, 142, 46, 14);
		getContentPane().add(lblNewLabel_1_2_1);

		cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboUsuPerfil.setBounds(85, 138, 86, 22);
		getContentPane().add(cboUsuPerfil);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setDefaultCapable(false);
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBackground(new Color(240, 240, 240));
		btnAdicionar.setToolTipText("Adicionar Usu\u00E1rio");
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/adicionar.png")));
		btnAdicionar.setBounds(83, 171, 64, 64);
		getContentPane().add(btnAdicionar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setDefaultCapable(false);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBackground(UIManager.getColor("Button.background"));
		btnExcluir.setToolTipText("Remover Usu\u00E1rio");
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/remover.png")));
		btnExcluir.setBounds(157, 171, 64, 64);
		getContentPane().add(btnExcluir);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// l�gica para verificar o checkbox
				if (chkSenha.isSelected()) {
					alterarUsuarioSenha();
				} else {
					alterarUsuario();
				}
			}
		});
		btnAlterar.setDefaultCapable(false);
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setBackground(UIManager.getColor("Button.background"));
		btnAlterar.setToolTipText("Alterar Usu\u00E1rio");
		btnAlterar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/alterar.png")));
		btnAlterar.setBounds(231, 171, 64, 64);
		getContentPane().add(btnAlterar);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setSelected(true);
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/search1.png")));
		btnPesquisar.setBounds(231, 15, 32, 32);
		getContentPane().add(btnPesquisar);

		// Validação com o uso da biblioteca Atxy2k
		// txtUsuId
		RestrictedTextField validarId = new RestrictedTextField(txtUsuId);
		validarId.setOnlyNums(true);
		validarId.setLimit(4);
		// txtUsuNome
		RestrictedTextField validarNome = new RestrictedTextField(txtUsuNome);
		validarNome.setLimit(50);
		// txtUsuLogin
		RestrictedTextField validarLogin = new RestrictedTextField(txtUsuLogin);
		validarLogin.setLimit(50);
		// txtUsuPassword
		RestrictedTextField validarSenha = new RestrictedTextField(txtUsuSenha);
		
		chkSenha = new JCheckBox("Alterar Senha");
		chkSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsuSenha.setText(null);
				txtUsuSenha.requestFocus();
				txtUsuSenha.setEditable(true);
			}
		});
		chkSenha.setVisible(false);
		chkSenha.setBounds(298, 110, 136, 23);
		getContentPane().add(chkSenha);
		validarSenha.setLimit(255);
		
	}// Fim do construtor

	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JCheckBox chkSenha;

	/**
	 * M�todo responsável pela pesquisa de usu�rios
	 */
	private void pesquisarUsuario() {
		// Validação
		if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Login do usu�rio");
			txtUsuLogin.requestFocus();
		} else {
			// Lógica principal
			// Query (instrução SQL)
			String read = "select * from usuarios where login = ?";
			// tratar exceções sempre que lidar com o banco
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtUsuLogin.getText());
				// Executar a query e exibir o resultado no formulario
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de usuário)
				// rs.next() -> existência de usuário
				if (rs.next()) {
					// Preencher(setar) os campos do formulário
					txtUsuId.setText(rs.getString(1));
					txtUsuNome.setText(rs.getString(2));
					txtUsuLogin.setText(rs.getString(3));
					txtUsuSenha.setText(rs.getString(4));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
					chkSenha.setVisible(true);
					txtUsuSenha.setEditable(false);
				} else {
					JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
					txtUsuNome.setEditable(true);
					cboUsuPerfil.setEnabled(true);
					txtUsuSenha.setEditable(true);
					txtUsuNome.requestFocus();
					btnAdicionar.setEnabled(true);
				}
				// NUNCA esquecer de encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	/**
	 * M�todo responsável por adicionar um novo usuário no banco
	 */
	private void adicionarUsuario() {
		String capturaSenha = new String(txtUsuSenha.getPassword());
		// Validação
		
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do usu�rio");
			txtUsuNome.requestFocus();
		}	else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usu�rio");
			txtUsuLogin.requestFocus();
		}	else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Digite a senha do usu�rio");
			txtUsuSenha.requestFocus();
		}	else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usu�rio");
			cboUsuPerfil.requestFocus();
		}	else {
			// L�gica Principal
			String create = "insert into usuarios(usuario,login,senha,perfil) values(?,?,md5(?),?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ???? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
				limparCampos();
				con.close();
			} catch(SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
			
	}
	
	/**
	 * M�todo respons�vel por alterar os dados de um usu�rio no banco exceto a senha
	 */
	private void alterarUsuario() {
		
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do usu�rio");
			txtUsuNome.requestFocus();
		}	else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usu�rio");
			txtUsuLogin.requestFocus();
		}	else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Digite a senha do usu�rio");
			txtUsuSenha.requestFocus();
		}	else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usu�rio");
			cboUsuPerfil.requestFocus();
		}	else {
			// L�gica Principal
			String update = "update usuarios set usuario=?, login=?, perfil=? where idusu=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ???? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(4, txtUsuId.getText());
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// Encerrar a conex�o
				JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso");
				limparCampos();
				con.close();
			} catch(SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
			
	}
	
	/**
	 * M�todo respons�vel por alterar os dados de um usu�rio no banco e sua respectiva senha
	 */
	private void alterarUsuarioSenha() {
		String capturaSenha = new String(txtUsuSenha.getPassword());
		// Valida��o
		
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do usu�rio");
			txtUsuNome.requestFocus();
		}	else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usu�rio");
			txtUsuLogin.requestFocus();
		}	else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Digite a senha do usu�rio");
			txtUsuSenha.requestFocus();
		}	else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usu�rio");
			cboUsuPerfil.requestFocus();
		}	else {
			// L�gica Principal
			String update2 = "update usuarios set usuario=?, login=?, senha=md5(?), perfil=? where idusu=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(update2);
				// Substituir o ???? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(5, txtUsuId.getText());

				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// Encerrar a conex�o
				JOptionPane.showMessageDialog(null, "Usu�rio alterado com sucesso");
				limparCampos();
				con.close();
			} catch(SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
			
	}
	
	/**
	 * M�todo respons�vel por excluir os dados de um usu�rio no banco
	 */
	private void excluirUsuario() {
		// Valida��o (confirma��o de exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja confirmar a exclus�o do usu�rio ?","Aten��o!",JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// L�gica principal 
			String delete = "delete from usuarios where idusu=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtUsuId.getText());
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// Encerrar a conex�o
				JOptionPane.showMessageDialog(null, "Usu�rio excluido com sucesso!");
				limparCampos();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
	}
	
	private void limparCampos() {
		txtUsuNome.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
		txtUsuSenha.setBackground(Color.white);
		cboUsuPerfil.setSelectedItem("");
		txtUsuId.setText(null);
		cboUsuPerfil.setSelectedItem("");
		btnAdicionar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		chkSenha.setVisible(false);
		chkSenha.setSelected(false);
	}
}// Fim do c�digo
