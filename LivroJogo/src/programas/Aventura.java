package programas;
import java.util.*;


import beans.Heroi;
import beans.Item;
import beans.Loja;
import repositorio.Arquivo;

public class Aventura {

	private static Scanner s;
	//private static Random dado;
	private static Heroi h;
	private static int opcao;
	//private static String texTemp;
	private static Item i;
	private static Loja loja;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interacoes mundo = new Interacoes();
		new Arquivo();
		s = new Scanner(System.in);
		//texTemp = new String();
		i = new Item();
		
		loja = mundo.lojaYaztromo();
		
		System.out.println("Bem vindo ao sistema Livro-Jogo\n"
				+ "uma ferramenta que veio auxiliar sua leitura!\n");
		
		System.out.println("Escolha sua opção:\n1- Novo Jogo\n2- Continuar");
		opcao = s.nextInt();
		while (opcao<1 || opcao>3) {
		System.out.println("Opção inválida! Digite novamente!");
			opcao = s.nextInt();
		}
		//do{
			switch (opcao) {
			case 1:
				h = new Heroi();
				h = mundo.criarPersonagem();
				break;
			case 2:
				h = Arquivo.lerArquivo();
			break;
			}
		//}while(opcao = =2);
		
		do{
			System.out.println("Olá! "+ h.getNome()+ " Eu sou o seu assistente de Aventura.\n- Em que posso ajudar?(Ultima posição salva: "+ h.getPosicao()+")");
			System.out.println("1- Gerar Item?\n2- Listar Itens\n3- Loja Yaztromo\n4- Batalhar\n5- Mostrar Personagem\n6- Salvar Jogo\n7- Testar Sorte\n8- Usar Poção/Provisão\n9- Usar Ouro\n10- Sair");
			opcao = s.nextInt();
			while(opcao<1||opcao>10){
				System.out.println("Opcão In válida! Digite novamente");
				opcao = s.nextInt();
			}
			
			switch (opcao) {
			case 1:
				i = mundo.gerarItem();
				h.setOuro(-(i.getPreco()*i.getQuantidade()));
				h.getBolsa().addItem(i);
				break;
			case 2:
				System.out.println("--------------- ITENS -------------------");
				h.getBolsa().listarItens();
				break;
			case 3:
				mundo.comprar(h, loja);
				break;
			case 4:
				mundo.batalha(h);
				break;
			case 5:
				System.out.println(h);
				break;
			case 6:
				System.out.println("Digite a posição atual:");
				h.setPosicao(s.nextInt());
				Arquivo.salvaArquivo(h);
				break;
			case 7:
				mundo.usarSorte(h);
				break;
			case 8:
				h.getBolsa().listarItens();
				mundo.UsarPocao(h);
				break;
			case 9:
				System.out.println("O quanto de ouro você recebeu/doou?");
				h.setOuro(s.nextInt());
				break;
			case 10:
				System.out.println("Deseja salvar antes de ir?(1- Sim / 2- Não)");
				if (s.nextInt()==1) {
					System.out.println("Digite a posição atual:");
					h.setPosicao(s.nextInt());
					Arquivo.salvaArquivo(h);
					System.out.println("Obrigado por usar o programa!");
					System.exit(0);
				} else {
					System.out.println("Obrigado por usar o programa!");
					System.exit(0);
				}
				break;
			default:
				System.out.println("Opção inválida");
		}
		
		}while(h.getEnergiaAtual()>0);
		
	}

}
