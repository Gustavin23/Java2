package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		addWindowListener(new WindowAdapter() {
			// Evento ativar janela
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblData.setText(formatador.format(data));
				
			}
		});
		setTitle("SkateshopRD ");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/iconskt.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/iconskt.png")));
		lblNewLabel.setBounds(595, 382, 128, 128);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setDefaultCapable(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobres = new Sobre();
				sobres.setVisible(true);
			}
		});
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setIcon(new ImageIcon(Principal.class.getResource("/img/sobre.png")));
		btnNewButton_3.setToolTipText("Sobre");
		btnNewButton_3.setBounds(595, 167, 128, 128);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setDefaultCapable(false);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Caixa caixas = new Caixa();
				caixas.setVisible(true);
			}
		});
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setIcon(new ImageIcon(Principal.class.getResource("/img/Caixa.png")));
		btnNewButton_4.setToolTipText("Caixa");
		btnNewButton_4.setBounds(53, 167, 128, 128);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setDefaultCapable(false);
		btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorio relatorios = new Relatorio();
				relatorios.setVisible(true);
			}
		});
		btnNewButton_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_5.setIcon(new ImageIcon(Principal.class.getResource("/img/Relatorio.png")));
		btnNewButton_5.setToolTipText("Relat\u00F3rio");
		btnNewButton_5.setBounds(229, 167, 128, 128);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.setDefaultCapable(false);
		btnNewButton_6.setBorderPainted(false);
		btnNewButton_6.setContentAreaFilled(false);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Backup backups = new Backup();
				backups.setVisible(true);
			}
		});
		btnNewButton_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_6.setIcon(new ImageIcon(Principal.class.getResource("/img/Backup.png")));
		btnNewButton_6.setToolTipText("Backup");
		btnNewButton_6.setBounds(412, 167, 128, 128);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setDefaultCapable(false);
		btnNewButton_7.setContentAreaFilled(false);
		btnNewButton_7.setBorderPainted(false);
		btnNewButton_7.addActionListener(new ActionListener() {
			// Evento clicar no botão
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnNewButton_7.setBackground(SystemColor.control);
		btnNewButton_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_7.setIcon(new ImageIcon(Principal.class.getResource("/img/Cadastro.png")));
		btnNewButton_7.setToolTipText("Usu\u00E1rios");
		btnNewButton_7.setBounds(53, 11, 128, 128);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_6_1 = new JButton("");
		btnNewButton_6_1.setDefaultCapable(false);
		btnNewButton_6_1.setContentAreaFilled(false);
		btnNewButton_6_1.setBorderPainted(false);
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedor fornecedores = new Fornecedor();
				fornecedores.setVisible(true);
			}
		});
		btnNewButton_6_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_6_1.setIcon(new ImageIcon(Principal.class.getResource("/img/fornecimento.png")));
		btnNewButton_6_1.setToolTipText("Fornecedores");
		btnNewButton_6_1.setBounds(229, 11, 128, 128);
		contentPane.add(btnNewButton_6_1);
		
		JButton btnNewButton_7_1 = new JButton("");
		btnNewButton_7_1.setDefaultCapable(false);
		btnNewButton_7_1.setContentAreaFilled(false);
		btnNewButton_7_1.setBorderPainted(false);
		btnNewButton_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente clientes = new Cliente();
				clientes.setVisible(true);
			}
		});
		btnNewButton_7_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_7_1.setIcon(new ImageIcon(Principal.class.getResource("/img/Clients.png")));
		btnNewButton_7_1.setToolTipText("Clientes");
		btnNewButton_7_1.setBounds(595, 11, 128, 128);
		contentPane.add(btnNewButton_7_1);
		
		JButton btnNewButton_6_1_1 = new JButton("");
		btnNewButton_6_1_1.setDefaultCapable(false);
		btnNewButton_6_1_1.setContentAreaFilled(false);
		btnNewButton_6_1_1.setBorderPainted(false);
		btnNewButton_6_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produtos = new Produto();
				produtos.setVisible(true);
			}
		});
		btnNewButton_6_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_6_1_1.setIcon(new ImageIcon(Principal.class.getResource("/img/produtos1.png")));
		btnNewButton_6_1_1.setToolTipText("Produtos");
		btnNewButton_6_1_1.setBounds(412, 11, 128, 128);
		contentPane.add(btnNewButton_6_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(0, 510, 784, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblData = new JLabel("");
		lblData.setForeground(SystemColor.textHighlightText);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblData.setBounds(515, 11, 259, 29);
		panel.add(lblData);
		lblData.setBackground(SystemColor.textHighlightText);
	}// fim do construtor
}
