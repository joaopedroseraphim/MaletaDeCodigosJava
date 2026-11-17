package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import utils.Dao;
import utils.Imagem;

public class Update {
	
	private void smd(String texto) {JOptionPane.showMessageDialog(null, "Campo " + texto + " está vazio");}

	Dao dao = new Dao();
	private Connection con;
	private PreparedStatement pst;
	
	public boolean usuario(boolean pesquisa, String id, String nome, String sobrenome, int idade, Imagem imagem) {
		if (!pesquisa) {
			JOptionPane.showMessageDialog(null, "Faça uma pesquisa antes");
			return false;
		}
		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faça uma pesquisa antes");
			return false;
		}
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
		if (imagem.getFotoInserida()) {
			//Alterar foto
			String update = "update usuarios set nome=?, sobrenome=?, idade=?, foto=? WHERE id = ?";
			try {
			    con = dao.conexao();
			    pst = con.prepareStatement(update);
			    pst.setString(1, nome);
			    pst.setString(2, sobrenome);
			    pst.setInt(3, idade);
			    pst.setBinaryStream(4, imagem.getFis());
			    pst.setString(5, id);
			    int verifica = pst.executeUpdate();
			    System.out.println("Linhas inseridas: " + verifica);
			    if (verifica == 1) {
			        JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			        return true;
			    }
			} catch (Exception e) {
				System.out.println(e);
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
		} else {
			//Não alterar foto
			String update = "update usuarios set nome=?, sobrenome=?, idade=? WHERE id = ?";
			try {
			    con = dao.conexao();
			    pst = con.prepareStatement(update);
			    pst.setString(1, nome);
			    pst.setString(2, sobrenome);
			    pst.setInt(3, idade);
			    pst.setString(4, id);
			    int verifica = pst.executeUpdate();
			    System.out.println("Linhas inseridas: " + verifica);
			    if (verifica == 1) {
			        JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			        return true;
			    }
			} catch (Exception e) {
				System.out.println(e);
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
}
