package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utils.Imagem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;

import CRUD.Create;
import CRUD.Delete;
import CRUD.Read;
import CRUD.Update;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TesteCrud extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBuscaID;
	private JTextField txtBuscaNome;
	private JLabel lblNewLabel;
	private JButton btnBusca;
	private JButton BtnLimpar;
	private JLabel lblFoto;
	private JLabel lblInformaes;
	private JLabel lblNewLabel_3;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblID;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnDeletar;
	private JButton btnAdicionarFt;
	private JButton btnRemoverFt;
	private JSpinner spnIdade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteCrud frame = new TesteCrud();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Imagem imagem = new Imagem();
	Create create = new Create();
	Read read = new Read();
	Update update = new Update();
	Delete delete = new Delete();
	/**
	 * Create the frame.
	 */
	public TesteCrud() {
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtBuscaID = new JTextField();
		txtBuscaID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtNome.requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					txtBuscaNome.requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscar();
				}
			}
		});
		txtBuscaID.setBounds(10, 56, 50, 25);
		contentPane.add(txtBuscaID);
		txtBuscaID.setColumns(10);
		
		txtBuscaNome = new JTextField();
		txtBuscaNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtNome.requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					txtBuscaID.requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscar();
				}
			}
		});
		txtBuscaNome.setBounds(70, 56, 300, 25);
		contentPane.add(txtBuscaNome);
		txtBuscaNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("- ID");
		lblNewLabel_1.setBounds(10, 36, 50, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("- Nome");
		lblNewLabel_2.setBounds(70, 36, 300, 20);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("Pesquisar Usuarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 360, 20);
		contentPane.add(lblNewLabel);
		
		btnBusca = new JButton("");
		btnBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBusca.setIcon(new ImageIcon(TesteCrud.class.getResource("/icons/procurar.png")));
		btnBusca.setToolTipText("Pesquisar");
		btnBusca.setBounds(380, 35, 45, 45);
		contentPane.add(btnBusca);
		
		BtnLimpar = new JButton("");
		BtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		BtnLimpar.setIcon(new ImageIcon(TesteCrud.class.getResource("/icons/seta-pequena-esquerda.png")));
		BtnLimpar.setBounds(435, 35, 45, 45);
		contentPane.add(BtnLimpar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 92, 470, 2);
		contentPane.add(separator);
		
		lblFoto = new JLabel("");
		lblFoto.setOpaque(true);
		lblFoto.setBackground(Color.LIGHT_GRAY);
		lblFoto.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(524, 11, 450, 450);
		contentPane.add(lblFoto);
		
		btnAdicionarFt = new JButton("  Adicionar Foto");
		btnAdicionarFt.setIcon(new ImageIcon(TesteCrud.class.getResource("/icons/adicionar-ficheiro.png")));
		btnAdicionarFt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdicionarFt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage img = imagem.imagem();
				if (imagem.getFotoInserida()) {
		            Image foto = img.getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(),Image.SCALE_SMOOTH);
		            lblFoto.setIcon(new ImageIcon(foto));
				} else {
		            lblFoto.setIcon(null);
				}
			}
		});
		btnAdicionarFt.setBounds(524, 472, 220, 45);
		contentPane.add(btnAdicionarFt);
		
		btnRemoverFt = new JButton("  Remover Foto");
		btnRemoverFt.setIcon(new ImageIcon(TesteCrud.class.getResource("/icons/seta-pequena-esquerda.png")));
		btnRemoverFt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemoverFt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            lblFoto.setIcon(null);
	            imagem.resetImagem();
			}
		});
		btnRemoverFt.setBounds(754, 472, 220, 45);
		contentPane.add(btnRemoverFt);
		
		lblInformaes = new JLabel("Informações");
		lblInformaes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInformaes.setBounds(10, 105, 360, 20);
		contentPane.add(lblInformaes);
		
		lblNewLabel_3 = new JLabel("- Nome");
		lblNewLabel_3.setBounds(10, 191, 470, 20);
		contentPane.add(lblNewLabel_3);
		
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtSobrenome.requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtBuscaID.requestFocus();
				}
			}
		});
		txtNome.setColumns(10);
		txtNome.setBounds(10, 211, 470, 25);
		contentPane.add(txtNome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER) {
					spnIdade.requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					txtNome.requestFocus();
				}
			}
		});
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(10, 267, 470, 25);
		contentPane.add(txtSobrenome);
		
		lblNewLabel_4 = new JLabel("- Sobrenome");
		lblNewLabel_4.setBounds(10, 247, 470, 20);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("- Idade");
		lblNewLabel_5.setBounds(10, 303, 470, 20);
		contentPane.add(lblNewLabel_5);
		
		spnIdade = new JSpinner();
		spnIdade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					adicionar();
				}
			}
		});
		spnIdade.setBounds(10, 320, 470, 25);
		contentPane.add(spnIdade);
		
		btnAdicionar = new JButton("  Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(TesteCrud.class.getResource("/icons/adicionar-ficheiro.png")));
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdicionar.setBounds(10, 472, 150, 45);
		contentPane.add(btnAdicionar);
		
		btnAlterar = new JButton("  Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TesteCrud.class.getResource("/icons/alterar.png")));
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAlterar.setBounds(170, 472, 150, 45);
		contentPane.add(btnAlterar);
		
		btnDeletar = new JButton("  Deletar");
		btnDeletar.setEnabled(false);
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDeletar.setIcon(new ImageIcon(TesteCrud.class.getResource("/icons/botao-apagar.png")));
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeletar.setBounds(330, 472, 150, 45);
		contentPane.add(btnDeletar);
		
		JLabel lblNewLabel_3_1 = new JLabel("- ID");
		lblNewLabel_3_1.setBounds(10, 136, 470, 20);
		contentPane.add(lblNewLabel_3_1);
		
		lblID = new JLabel("");
		lblID.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblID.setOpaque(true);
		lblID.setBackground(Color.WHITE);
		lblID.setBounds(10, 155, 470, 25);
		contentPane.add(lblID);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 459, 470, 2);
		contentPane.add(separator_1);

	}
	
	private void buscar() {
		boolean DCT = read.usuario(txtBuscaID.getText(), txtBuscaNome.getText());
		if (DCT) {
			txtBuscaID.setText(read.getUsuarioId());
			txtBuscaNome.setText(read.getUsuarioNome());
			lblID.setText(read.getUsuarioId());
			txtNome.setText(read.getUsuarioNome());
			txtSobrenome.setText(read.getUsuarioSobrenome());
			spnIdade.setValue(read.getUsuarioIdade());
			BufferedImage img = read.getImgUsuario();
			System.out.println("Imagem recebida pela tela: " + (img != null));
			if (img != null) {
			    Image foto = img.getScaledInstance(
			        lblFoto.getWidth(),
			        lblFoto.getHeight(),
			        Image.SCALE_SMOOTH
			    );
			    lblFoto.setIcon(new ImageIcon(foto));
			} else {
			    lblFoto.setIcon(null);
			}
			txtBuscaID.setEditable(false);
			txtBuscaNome.setEditable(false);
			btnAdicionar.setEnabled(false);
			btnAlterar.setEnabled(true);
			btnDeletar.setEnabled(true);
			
		}
		
	}
	
	private void adicionar () {
		boolean DCT = create.usuarios(
			    txtNome.getText(),
			    txtSobrenome.getText(),
			    (int) spnIdade.getValue(),
			    imagem
			);
		if (!DCT) {
			System.out.println("deu ruim");
		} else {
			
			limpar();
		}
	}
	
	private void alterar() {
		boolean DCT = update.usuario(
				read.getReadUsuario(), lblID.getText(),
				txtNome.getText(), txtSobrenome.getText(),
				(int) spnIdade.getValue(),
				imagem
			);
			if (!DCT) {
				System.out.println("deu ruim");
			} else {
				read.setReadUsuario(false);
				limpar();
			}
	}
	
	private void deletar() {
		boolean DCT = delete.usuario(
				read.getReadUsuario(),
				lblID.getText()
			);
		if (!DCT) {
			System.out.println("Deu ruim");
		} else {
			read.setReadUsuario(false);
			limpar();
		}
	}
	
	private void limpar() {
		txtBuscaID.setText(null);
		txtBuscaNome.setText(null);
		lblID.setText(null);
		txtNome.setText(null);
		txtSobrenome.setText(null);
		spnIdade.setValue(0);
		lblFoto.setIcon(null);
		txtBuscaID.setEditable(true);
		txtBuscaNome.setEditable(true);
		btnAdicionar.setEnabled(true);
		btnAlterar.setEnabled(false);
		btnDeletar.setEnabled(false);
	}
}
