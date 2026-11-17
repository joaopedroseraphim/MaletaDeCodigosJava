package views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import utils.Dao;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tabela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBusca;
	
	JTable table = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabela frame = new Tabela();
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
	public Tabela() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar por usuarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 500, 20);
		contentPane.add(lblNewLabel);
		
		txtBusca = new JTextField();
		txtBusca.setBounds(329, 42, 535, 35);
		contentPane.add(txtBusca);
		txtBusca.setColumns(10);
		
		JButton btnBusca = new JButton("");
		btnBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar(txtBusca.getText());
			}
		});
		btnBusca.setToolTipText("Pesquisar");
		btnBusca.setIcon(new ImageIcon(Tabela.class.getResource("/icons/procurar.png")));
		btnBusca.setBounds(874, 32, 45, 45);
		contentPane.add(btnBusca);
		
		JButton BtnLimpar = new JButton("");
		BtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar("");
				txtBusca.setText(null);
				txtBusca.requestFocus();
			}
		});
		BtnLimpar.setIcon(new ImageIcon(Tabela.class.getResource("/icons/atualizar.png")));
		BtnLimpar.setBounds(929, 32, 45, 45);
		contentPane.add(BtnLimpar);
		
		JLabel lblBusquePorid = new JLabel("Busque por: -ID - Nome -Sobrenome -Idade  -->");
		lblBusquePorid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBusquePorid.setBounds(10, 52, 326, 20);
		contentPane.add(lblBusquePorid);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 83, 964, 8);
		contentPane.add(separator);
		
		
		
		//adicionar scrollPane no editor de design.
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 964, 498);
		contentPane.add(scrollPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		//colar este codigo e alterar os campos.
		
		//referenciar a variavel:
		//JTable table = new JTable();
		
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Sobrenome", "Idade"
			}
		));
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) { // 1 clique
		            int linha = table.getSelectedRow();
		            int coluna = table.getColumnModel().getColumnIndex("ID");
		            String id = table.getValueAt(linha,  coluna).toString();
		            System.out.println(id);
		            VerDado verdado = new VerDado(id);
		            verdado.setVisible(true);
		        }
		    }
		});

		scrollPane.setViewportView(table);
		
		pesquisar("");
	}
	
	Dao dao = new Dao();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private void pesquisar(String pesquisa) {
	    String sql = "SELECT id, nome, sobrenome, idade FROM usuarios WHERE id= ? OR nome LIKE ? OR sobrenome LIKE ? OR idade = ?";
	    try {
	        con = dao.conexao();
	        pst = con.prepareStatement(sql);
	        pst.setString(1, pesquisa);
	        pst.setString(2, "%" + pesquisa + "%");
	        pst.setString(3, "%" + pesquisa + "%");
	        pst.setString(4, pesquisa);
	        rs = pst.executeQuery();
	        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	        modelo.setRowCount(0);
	        while (rs.next()) {
	            modelo.addRow(new Object[]{
	                rs.getString("id"),
	                rs.getString("nome"),
	                rs.getString("sobrenome"),
	                rs.getString("Idade")
	            });
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
