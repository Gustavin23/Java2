package view;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Relatorio extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Relatorio dialog = new Relatorio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Relatorio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorio.class.getResource("/img/Relatorio.png")));
		setTitle("Relat\u00F3rios");
		setResizable(false);
		setModal(true);
		setBounds(150, 150, 450, 362);
		getContentPane().setLayout(null);
		
		JButton btnRelClientes = new JButton("");
		btnRelClientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRelClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelClientes.setDefaultCapable(false);
		btnRelClientes.setContentAreaFilled(false);
		btnRelClientes.setToolTipText("Clientes");
		btnRelClientes.setIcon(new ImageIcon(Relatorio.class.getResource("/img/cliente.png")));
		btnRelClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnRelClientes.setBounds(41, 31, 70, 70);
		getContentPane().add(btnRelClientes);
		
		JButton btnRelFornecedores = new JButton("");
		btnRelFornecedores.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRelFornecedores.setContentAreaFilled(false);
		btnRelFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelFornecedores.setDefaultCapable(false);
		btnRelFornecedores.setToolTipText("Fornecedores");
		btnRelFornecedores.setIcon(new ImageIcon(Relatorio.class.getResource("/img/fornecedor2.png")));
		btnRelFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioFornecedores();
			}
		});
		btnRelFornecedores.setBounds(41, 112, 70, 70);
		getContentPane().add(btnRelFornecedores);
		
		JButton btnRelVencidos = new JButton("");
		btnRelVencidos.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRelVencidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelVencidos.setDefaultCapable(false);
		btnRelVencidos.setContentAreaFilled(false);
		btnRelVencidos.setToolTipText("Produtos Vencidos");
		btnRelVencidos.setIcon(new ImageIcon(Relatorio.class.getResource("/img/expirado.png")));
		btnRelVencidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioProdVencido();
			}
		});
		btnRelVencidos.setBounds(135, 112, 70, 70);
		getContentPane().add(btnRelVencidos);
		
		JButton btnRelReposicao = new JButton("");
		btnRelReposicao.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRelReposicao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelReposicao.setDefaultCapable(false);
		btnRelReposicao.setContentAreaFilled(false);
		btnRelReposicao.setToolTipText("Recupera\u00E7\u00E3o de Estoque");
		btnRelReposicao.setIcon(new ImageIcon(Relatorio.class.getResource("/img/recuperacao.png")));
		btnRelReposicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioRecuperacao();
			}
		});
		btnRelReposicao.setBounds(135, 193, 70, 70);
		getContentPane().add(btnRelReposicao);
		
		JButton btnRelInventario = new JButton("");
		btnRelInventario.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRelInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelInventario.setDefaultCapable(false);
		btnRelInventario.setContentAreaFilled(false);
		btnRelInventario.setToolTipText("Invent\u00E1rio");
		btnRelInventario.setIcon(new ImageIcon(Relatorio.class.getResource("/img/inventario.png")));
		btnRelInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioInventario();
			}
		});
		btnRelInventario.setBounds(135, 31, 70, 70);
		getContentPane().add(btnRelInventario);
		
		JButton btnRelEmail = new JButton("");
		btnRelEmail.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRelEmail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelEmail.setDefaultCapable(false);
		btnRelEmail.setContentAreaFilled(false);
		btnRelEmail.setToolTipText("Email Marketing");
		btnRelEmail.setIcon(new ImageIcon(Relatorio.class.getResource("/img/marketing.png")));
		btnRelEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioMarketing();
			}
		});
		btnRelEmail.setBounds(41, 193, 70, 70);
		getContentPane().add(btnRelEmail);
	}// fim do construtor
	
	DAO dao = new DAO();
	
	//método responsável pela impressão do relatório de clientes
		private void relatorioClientes() {
			//criar objeto para construir a página pdf
			Document document = new Document();
			//gerar o documento pdf
			try {
				//cria um documento pdf em branco de nome clientes.pdf
				PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
				document.open();
				//gerar o conteúdo do documento
				Date data = new Date();			
		        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				document.add(new Paragraph(new Paragraph(formatador.format(data))));
				document.add(new Paragraph(" "));
				document.add(new Paragraph("Clientes cadastrados"));
				document.add(new Paragraph(" "));
				//... Demais conteúdos (imagem, tabela, gráfico, etc)
				PdfPTable tabela = new PdfPTable(4);
				PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
				PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				// Acessar o banco de dados
				 
				document.add(tabela);
			} catch (Exception e) {
				System.out.println(e);
			} finally { //executa o código independente do resultado OK ou não
				document.close();
			}
			
			//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
			try {
				Desktop.getDesktop().open(new File("clientes.pdf"));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
	private void relatorioInventario() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("inventário.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Itens Cadastrados"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("inventário.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void relatorioProdVencido() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("produtosVencidos.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Produtos Vencidos"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Data de Entrada"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Data de Validade"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("produtosVencidos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	private void relatorioFornecedores() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("fornecedores.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Fornecedores Cadastrados"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void relatorioMarketing() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("marketing.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Email Cadastrados"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("marketing.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void relatorioRecuperacao() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("recuperação.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Estoque Recuperado"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("recuperação.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}// fim do código
