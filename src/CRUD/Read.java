package CRUD;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import utils.Dao;

public class Read {

	Dao dao = new Dao();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private String id;
	private String nome;
	private String sobrenome;
	private int idade;
	private boolean readUsuario;
	private BufferedImage imagemUsuario;
	
	public String getUsuarioId() { return this.id; }
	public String getUsuarioNome() { return this.nome; }
	public String getUsuarioSobrenome() { return this.sobrenome; }
	public int getUsuarioIdade() { return this.idade; }
	public boolean getReadUsuario() { return this.readUsuario; }
	public void setReadUsuario(boolean read) { this.readUsuario = read; }
	public BufferedImage getImgUsuario() { return this.imagemUsuario; }

	private void smd(String texto) {JOptionPane.showMessageDialog(null, "Campo " + texto + " está vazio");}
	


	public boolean usuario(String id, String nome) {

	    if (id.isEmpty() && nome.isEmpty()) {
	        smd("id ou nome");
	        return false;
	    }
	    try {
	        String pesquisar;

	        if (!id.isEmpty()) {
	            pesquisar = "SELECT id, nome, sobrenome, idade, foto FROM usuarios WHERE id = ?";
	        } else {
	            pesquisar = "SELECT id, nome, sobrenome, idade, foto FROM usuarios WHERE nome LIKE ?";
	        }
	        con = dao.conexao();
	        pst = con.prepareStatement(pesquisar);
	        if (!id.isEmpty()) {
	            pst.setString(1, id);
	        } else {
	            pst.setString(1, "%" + nome + "%");
	        }
	        rs = pst.executeQuery();
	        if (rs.next()) {
	            this.id = rs.getString("id");
	            this.nome = rs.getString("nome");
	            this.sobrenome = rs.getString("sobrenome");
	            this.idade = rs.getInt("idade");
	            this.readUsuario = true;
	            Blob blob = rs.getBlob("foto");
	            if (blob != null && blob.length() > 0) {
	                byte[] img = blob.getBytes(1, (int) blob.length());
	                this.imagemUsuario = ImageIO.read(
	                    new ByteArrayInputStream(img)
	                );
	            } else {
	                this.imagemUsuario = null;
	            }
	            con.close();
	            return true;
	        } else {
	            JOptionPane.showMessageDialog(null, "Item não encontrado");
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
