package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import utils.Dao;

public class Delete {

	Dao dao = new Dao();
	private Connection con;
	private PreparedStatement pst;
	
	public boolean usuario(boolean pesquisa, String id) {
		if (!pesquisa) {
			JOptionPane.showMessageDialog(null, "Faça uma pesquisa antes");
			return false;
		}
		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faça uma pesquisa antes");
			return false;
		}
		int confirmaExclusao = JOptionPane.showConfirmDialog(null, "deseja excluir o registro?", "Atenção!",JOptionPane.YES_NO_OPTION);
		if (confirmaExclusao == JOptionPane.YES_OPTION) {
			String delete = "DELETE FROM usuarios WHERE id=?";
			try {
				con = dao.conexao();
				pst = con.prepareStatement(delete);
				pst.setString(1, id);
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Excluido com sucesso");
					return true;
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
			    try {
			        if (pst != null) pst.close();
			        if (con != null) con.close();
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
			return false;
		} else {
			return false;
		}
	}
}
