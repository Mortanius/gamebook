package repositorio;
import java.io.*;
//import java.util.*;

import beans.Heroi;

public class Arquivo {
	
	
	public static void salvaArquivo(Heroi jogador){
		//Scanner s = new Scanner(System.in);
		String caminho = "C:\\Users\\" + System.getProperty("user.name").toString() + "\\Desktop\\" + "LivroJogo"+".dat";
		
		try {
			FileOutputStream arquivoGrav = new FileOutputStream(caminho);
			ObjectOutputStream objGrav = new ObjectOutputStream(arquivoGrav);
			objGrav.writeObject(jogador);
			objGrav.flush();
			objGrav.close();
			System.out.println("Arquivo Salvo");
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static Object lerArquivo(){
		
		String caminho = "C:\\Users\\" + System.getProperty("user.name").toString() + "\\Desktop\\" + "LivroJogo"+".dat";
		
		try {
			FileInputStream arquivoLeitura = new FileInputStream(caminho);
			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
			Object jogador = new Heroi();
			jogador = objLeitura.readObject();
			objLeitura.close();
			arquivoLeitura.close();
			return jogador;
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return caminho;
		
	}
	
}
