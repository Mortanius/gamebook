package arquivo;
import java.io.*;
//import java.util.*;
import java.util.ArrayList;
import java.util.List;

import beans.Livro;

public class Arquivo {
	
	private static String caminho = "saves/";
	private static String defaultFileName = "LivroJogo";
	private static String defaultFileExt = ".dat";
	public static void salvaArquivo(Livro livro, String fileName) {
		//Scanner s = new Scanner(System.in);
		File arquivo;
		try {
			if (fileName == null) {
				int i = 1;
				do {
					fileName = caminho+defaultFileName+i+defaultFileExt;
					arquivo = new File(fileName);
					if (!arquivo.exists()) {
						break;
					}
					i++;
				}while (true);
			}
			arquivo = new File(fileName);
			FileOutputStream arquivoGrav = new FileOutputStream(arquivo);
			ObjectOutputStream objGrav = new ObjectOutputStream(arquivoGrav);
			objGrav.writeObject(livro);
			objGrav.flush();
			objGrav.close();
			System.out.println("Arquivo Salvo");
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static Livro lerArquivo(String fileName){
		
//		String caminho = "C:\\Users\\" + System.getProperty("user.name").toString() + "\\Desktop\\" + "LivroJogo"+".dat";
		Livro livro = new Livro();
		try {
			String path = caminho+fileName;
			FileInputStream arquivoLeitura = new FileInputStream(path);
			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
			livro = (Livro) objLeitura.readObject();
			objLeitura.close();
			arquivoLeitura.close();
			return livro;
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return livro;
		
	}
	
	public static List<String> listarArquivos() {
		List<String> files = new ArrayList<String>();
		File f = new File(caminho);
		for (String s : f.list()) {
			if (s.startsWith(defaultFileName) && s.endsWith(defaultFileExt)) {
				files.add(s);
			}
		}
		return files;
	}
	//Vai retornar um Array de Strings contendo nome do livro, '' do personagem e posicao
	//para usar na tabela do CarregarJogo
	public static String[][] dadosArquivos() {
		List<String> files = listarArquivos();
		String[][] s = new String[files.size()][4];
		int i = 0;
		for (String fileName : files) {
			Livro l = lerArquivo(fileName);
			s[i][0] = new String(l.getNome());
			s[i][1] = new String(l.getJogador().getNome());
			s[i][2] = new String(""+l.getPosicao());
			s[i][3] = new String(fileName);
			i++;
		}
		return s;
	}
	
	
}
