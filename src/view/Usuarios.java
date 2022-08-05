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

import model.DAO;

import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		txtUsuSenha.setBounds(85, 111, 162, 20);
		getContentPane().add(txtUsuSenha);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Perfil");
		lblNewLabel_1_2_1.setBounds(36, 142, 46, 14);
		getContentPane().add(lblNewLabel_1_2_1);
		
		cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "admin", "user"}));
		cboUsuPerfil.setBounds(85, 138, 86, 22);
		getContentPane().add(cboUsuPerfil);
		
		JButton btnAdicionar = new JButton("");
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

	}// Fim do construtor
	
	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;
	/**
	 * Método responsável pela pesquisa de usuários
	 */
	private void pesquisarUsuario() {
		// Validação
		if (txtUsuId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do usuário");
			txtUsuId.requestFocus();
		} else {
			// Lógica principal
			// Query (instrução SQL)
			String read = "select * from usuarios where idusu = ?";
			// tratar exceções sempre que lidar com o banco
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtUsuId.getText());
				// Executar a query e exibir o resultado no formulario
				ResultSet rs = pst.executeQuery();
				// Validação (existência de usuário)
				// rs.next() -> existência de usuário
				if (rs.next()) {
					// Preencher(setar) os campos do formulário
					txtUsuNome.setText(rs.getString(2));
					txtUsuLogin.setText(rs.getString(3));
					txtUsuSenha.setText(rs.getString(4));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
				} else {
					JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
				}
				// NUNCA esquecer de encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
}// Fim do código






