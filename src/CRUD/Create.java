package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import utils.Dao;
import utils.Imagem;

public class Create {
	
	Dao dao = new Dao();
	private Connection con;
	private PreparedStatement pst;
	
	private void smd(String texto) {JOptionPane.showMessageDialog(null, "Campo " + texto + " está vazio");}
	
	public boolean usuarios(String nome, String sobrenome, int idade, Imagem imagem) {
		if (nome.isEmpty()) {
			smd("nome");
			return false;
		}
		if (sobrenome.isEmpty()) {
			smd("sobrenome");
			return false;
		}
		if (idade <= 0) {
			smd("idade");
			return false;
		}
		if (!imagem.getFotoInserida()) {
			smd("imagem");
			return false;
		}
		String insert = "INSERT INTO usuarios(nome, sobrenome, idade, foto) VALUES(?, ?, ?, ?)";
		try {
		    con = dao.conexao();
		    pst = con.prepareStatement(insert);
		    pst.setString(1, nome);
		    pst.setString(2, sobrenome);
		    pst.setInt(3, idade);
		    pst.setBinaryStream(4, imagem.getFis());
		    int verifica = pst.executeUpdate();
		    System.out.println("Linhas inseridas: " + verifica);
		    if (verifica == 1) {
		        JOptionPane.showMessageDialog(null, "Salvo com sucesso");
		        return true;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    return false;
		} finally {
		    try {
		        if (pst != null) pst.close();
		        if (con != null) con.close();
		        if (imagem.getFis() != null) imagem.getFis().close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		return false;
	}
}
