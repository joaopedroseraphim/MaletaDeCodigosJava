package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Imagem;

import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class TesteImagem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteImagem frame = new TesteImagem();
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
	public TesteImagem() {
		setResizable(false);
		
		Imagem imagem = new Imagem();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(10, 11, 964, 539);
		lblFoto.setOpaque(true);
		lblFoto.setBackground(new Color(192, 192, 192));
		
		JButton btnInserir = new JButton("Inserir Imagem");
		btnInserir.setBounds(186, 582, 212, 51);
		btnInserir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInserir.addActionListener(new ActionListener() {
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
		
		JButton btnRemoverImagem = new JButton("Remover Imagem");
		btnRemoverImagem.setBounds(584, 582, 212, 51);
		btnRemoverImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            lblFoto.setIcon(null);
	            imagem.resetImagem();
			}
		});
		btnRemoverImagem.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.setLayout(null);
		contentPane.add(lblFoto);
		contentPane.add(btnInserir);
		contentPane.add(btnRemoverImagem);

	}
	
}
