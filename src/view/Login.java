package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/iconskt.png")));
		setResizable(true);
		setTitle("SkateshopRD - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(185, 88, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(128, 113, 150, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(72, 116, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setBounds(72, 147, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(128, 144, 150, 20);
		contentPane.add(txtSenha);
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnAcessar.setBounds(157, 175, 89, 23);
		contentPane.add(btnAcessar);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(10, 202, 48, 48);
		contentPane.add(lblStatus);
		
		// Valida��o atxy2k
		// txtLogin
		RestrictedTextField validarLogin = new RestrictedTextField(txtLogin);
		validarLogin.setLimit(20);
		// txtSenha
		RestrictedTextField validarSenha = new RestrictedTextField(txtSenha);
		validarSenha.setLimit(255);
		
		// Usar o Enter ao inv�s de "clicar" no bot�o para logar
		getRootPane().setDefaultButton(btnAcessar);
	}// fim do construtor
	
	// Cria��o de um objeto para acessar a camada model
	DAO dao = new DAO();
	private JLabel lblStatus;
	
	/**
	 * M�todo usado para verificar o status do servidor
	 */
	private void status() {
		try {
			// Abrir a conex�o
			Connection con = dao.conectar();
			if (con == null) {
				// escolher a imagem dboff
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
			} else {
				// escolher a imagem dbon
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dbon.png")));
			}
			// n�o esquecer de fechar a conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * M�todo usado para autentica��o de um usu�rio
	 */
	
	private void logar() {
		String capturaSenha = new String(txtSenha.getPassword());
		// Valida��o
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o login");
			txtLogin.requestFocus();
		} else if(txtSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Informe a senha");
			txtSenha.requestFocus();
		} else {
			// L�gica Principal
			String read = "select * from usuarios where login=? and senha = md5(?)";
			try {
				// Abrir a conex�o
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				ResultSet rs = pst.executeQuery();
				
				if (rs.next()) {
					// Verificar o perfil do usu�rio
					String perfil = rs.getString(5);
					//System.out.println(perfil);
					Principal principal = new Principal();
					if (perfil.equals("admin")) {
						// Abrir a tela principal
						principal.setVisible(true);
						// Habilitar recursos
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						// Fechar a tela de login
						principal.panelUsuario.setBackground(Color.black);
						principal.lblUsuario.setText("Usu�rio: " + rs.getString(2));
						principal.lblLogin.setText("Login: " + rs.getString(3));
						// encerrar a conex�o
						con.close();
						this.dispose();
					} else {
						// abrir a tela principal
						principal.setVisible(true);
						// setar o nome do usuario na tela principal
						principal.lblUsuario.setText("Usu�rio: " + rs.getString(2));
						principal.lblLogin.setText("Login: " + rs.getString(3));
						principal.panelUsuario.setBackground(Color.black);
						// fechar a tela de login 
						con.close();
						this.dispose();
					}
										
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha inv�lido(s)");
					txtLogin.setText(null);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
}// fim do c�digo











