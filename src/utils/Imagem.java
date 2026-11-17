package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Imagem {
	
	private FileInputStream fis;
	private long tamanho;
	private Boolean fotoinserida = false;
	
	public FileInputStream getFis() { return this.fis; }
	public long getTamanho() { return this.tamanho; }
	public boolean getFotoInserida() { return this.fotoinserida; }
	
	public BufferedImage imagem() {
	    JFileChooser jfc = new JFileChooser();
	    jfc.setDialogTitle("Escolher Arquivo");
	    jfc.setFileFilter(
	        new FileNameExtensionFilter("Imagens (*.PNG, *.JPG, *.JPEG)", "png", "jpg", "jpeg")
	    );

	    int resultado = jfc.showOpenDialog(null);

	    if (resultado == JFileChooser.APPROVE_OPTION) {
	        File file = jfc.getSelectedFile();

	        try {
	            this.fis = new FileInputStream(file);
	            this.tamanho = file.length();
	            System.out.println("Arquivo: " + file.getName());
	            System.out.println("Tamanho do arquivo: " + this.tamanho);
	            System.out.println("FileInputStream: " + (this.fis != null));
	            this.fotoinserida = true;

	            BufferedImage img = ImageIO.read(file);
	            if (img == null) {
	                throw new IOException("Formato de imagem não suportado");
	            }
	            return img;
	        } catch (Exception e) {
	            fotoinserida = false;
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Erro ao carregar imagem");	
	        }
	    }
	    return null;
	}
	
	public void resetImagem() {
		this.fis = null;
		this.tamanho = 0;
		this.fotoinserida = false;
	}
	
}
