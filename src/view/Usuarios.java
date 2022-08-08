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
		lblNewLabel.setBounds(36, 30, 17, 14);
		getContentPane().add(lblNewLabel);

		txtUsuId = new JTextField();
		txtUsuId.setBounds(85, 27, 86, 20);
		getContentPane().add(txtUsuId);
		txtUsuId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(36, 58, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtUsuNome = new JTextField();
		txtUsuNome.setColumns(10);
		txtUsuNome.setBounds(85, 55, 162, 20);
		getContentPane().add(txtUsuNome);

		JLabel lblNewLabel_1_1 = new JLabel("Login");
		lblNewLabel_1_1.setBounds(36, 86, 46, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Senha");
		lblNewLabel_1_2.setBounds(36, 114, 46, 14);
		getContentPane().add(lblNewLabel_1_2);

		txtUsuLogin = new JTextField();
		txtUsuLogin.setColumns(10);
		txtUsuLogin.setBounds(85, 83, 162, 20);
		getContentPane().add(txtUsuLogin);

		txtUsuSenha = new JPasswordField();
		txtUsuSenha.setBounds(85, 111, 225, 20);
		getContentPane().add(txtUsuSenha);

		JLabel lblNewLabel_1_2_1 = new JLabel("Perfil");
		lblNewLabel_1_2_1.setBounds(36, 142, 46, 14);
		getContentPane().add(lblNewLabel_1_2_1);

		cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboUsuPerfil.setBounds(85, 138, 86, 22);
		getContentPane().add(cboUsuPerfil);

		JButton btnAdicionar = new JButton("");
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
		btnAdicionar.setBounds(35, 171, 64, 64);
		getContentPane().add(btnAdicionar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setDefaultCapable(false);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBackground(UIManager.getColor("Button.background"));
		btnExcluir.setToolTipText("Remover Usu\u00E1rio");
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/remover.png")));
		btnExcluir.setBounds(109, 171, 64, 64);
		getContentPane().add(btnExcluir);

		JButton btnAlterar = new JButton("");
		btnAlterar.setDefaultCapable(false);
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setBackground(UIManager.getColor("Button.background"));
		btnAlterar.setToolTipText("Alterar Usu\u00E1rio");
		btnAlterar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/alterar.png")));
		btnAlterar.setBounds(183, 171, 64, 64);
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
		btnPesquisar.setBounds(181, 15, 32, 32);
		getContentPane().add(btnPesquisar);

		// Valida��o com o uso da biblioteca Atxy2k
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
		validarSenha.setLimit(255);
		
	}// Fim do construtor

	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;

	/**
	 * M�todo respons�vel pela pesquisa de usu�rios
	 */
	private void pesquisarUsuario() {
		// Valida��o
		if (txtUsuId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do usu�rio");
			txtUsuId.requestFocus();
		} else {
			// L�gica principal
			// Query (instru��o SQL)
			String read = "select * from usuarios where idusu = ?";
			// tratar exce��es sempre que lidar com o banco
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuId.getText());
				// Executar a query e exibir o resultado no formulario
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de usu�rio)
				// rs.next() -> exist�ncia de usu�rio
				if (rs.next()) {
					// Preencher(setar) os campos do formul�rio
					txtUsuNome.setText(rs.getString(2));
					txtUsuLogin.setText(rs.getString(3));
					txtUsuSenha.setText(rs.getString(4));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
				} else {
					JOptionPane.showMessageDialog(null, "Usu�rio n�o cadastrado");
				}
				// NUNCA esquecer de encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	/**
	 * M�todo respons�vel por adicionar um novo usu�rio no banco
	 */
	private void adicionarUsuario() {
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
			String create = "insert into usuarios(usuario,login,senha,perfil) values(?,?,md5(?),?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ???? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, txtUsuSenha.getText());
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// Encerrar a conex�o
				JOptionPane.showMessageDialog(null, "Usu�rio cadastrado com sucesso");
				limparCampos();
				con.close();
			} catch(SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
			} catch (Exception e) {
				System.out.println(e);
			}

		}
			
	}
	
	private void limparCampos() {
		txtUsuNome.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
		cboUsuPerfil.setSelectedItem("");
	}
}// Fim do c�digo
