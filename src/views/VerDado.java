package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CRUD.Read;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VerDado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblID;
	private JLabel lblNome;
	private JLabel lblSobrenome;
	private JLabel lblIdade;
	private JLabel lblFoto;
	private String id;
	Read read = new Read();

	/**
	 * Create the dialog.
	 */
	public VerDado(String ID) {
		this.id = ID;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblFoto = new JLabel("");
			lblFoto.setForeground(Color.GRAY);
			lblFoto.setBounds(10, 11, 206, 206);
			contentPanel.add(lblFoto);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("-ID");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(225, 6, 199, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			lblID = new JLabel("");
			lblID.setBounds(226, 26, 198, 29);
			contentPanel.add(lblID);
		}
		{
			lblNome = new JLabel("");
			lblNome.setBounds(227, 81, 198, 29);
			contentPanel.add(lblNome);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("-Nome");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(226, 61, 199, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			lblIdade = new JLabel();
			lblIdade.setBounds(228, 191, 198, 29);
			contentPanel.add(lblIdade);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("-Idade");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(227, 171, 199, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			lblSobrenome = new JLabel("");
			lblSobrenome.setBounds(227, 136, 198, 29);
			contentPanel.add(lblSobrenome);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("-Sobrenome");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(226, 116, 199, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		buscar();
	}
	
	private void buscar() {
		boolean DCT = read.usuario(this.id, "");
		if (DCT) {
			lblID.setText(read.getUsuarioId());
			lblNome.setText(read.getUsuarioNome());
			lblSobrenome.setText(read.getUsuarioSobrenome());
			String idade = String.valueOf(read.getUsuarioIdade());
			lblIdade.setText(idade);
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
		}
		
	}
	
	

}
