package programa.negocio;

import java.util.*;

import beans.EquipItem;
import beans.Heroi;
import beans.Item;
import beans.Livro;
import beans.Loja;
import beans.Npc;
import programa.dados.Bolsa;

public class Interacoes {

	private Random dado;
	private Scanner s;
	

	public Interacoes() {
		
	}

	public boolean usarSorte(Heroi jogador) {
		boolean sorte = false;
		dado = new Random();
		s = new Scanner(System.in);
		int valor = dado.nextInt(12) + 1;
		if (valor <= jogador.getSorteAtual()) {
			sorte = true;
			System.out.println("Passou! \nSorte Atual: " + jogador.getSorteAtual() + "/" + jogador.getSorteMax());
		} else {
			System.out.println("Deu ruim! \nSorte Atual: " + jogador.getSorteAtual() + "/" + jogador.getSorteMax());
		}
		jogador.setSorteAtual(-1);
		return sorte;
	}

	private void mostrarVidaEHab(Heroi jogador, Npc npc) {
		System.out.println(jogador.getNome() + "\nEnergia: " + jogador.getEnergiaAtual() + "/" + jogador.getEnergiaMax()
				+ " For�a:" + jogador.getAtaqueMax() + jogador.incremento() + "\n");
		System.out.println(npc.getNome() + "\nEnergia: " + npc.getEnergiaAtual() + "/" + npc.getEnergiaMax()
				+ " For�a: " + npc.getAtaqueAtual() + "\n");
	}

	public void batalha(Heroi jogador) {
		s = new Scanner(System.in);
		dado = new Random();
		Npc npc = criarNpc();
		do {
			mostrarVidaEHab(jogador, npc);
			int fM = (dado.nextInt(12) + 1) + npc.getAtaqueAtual();
			int fJ = (dado.nextInt(12) + 1) + jogador.getAtaqueAtual();

			System.out.println(npc.getNome() + " ataca com: " + fM + " x " + fJ + " de " + jogador.getNome());

			if (fM > fJ) {
				System.out.println(npc.getNome() + " ataca com " + fM + " de for�a e fere " + jogador.getNome() + ".");
				System.out.println("Deseja usar a sorte? 1-Sim/2-N�o");
				System.out.println("Sorte atual:" + jogador.getSorteAtual());
				int decisao = s.nextInt();
				switch (decisao) {
				case 1:
					boolean sorte = usarSorte(jogador);
					if (sorte) {
						// System.out.println("Ufa, que sorte!");
						System.out.println(jogador.getNome() + " Perde 1 de vida");
						jogador.setEnergiaAtual(-1);
					} else {
						// System.out.println("Ops, m� sorte!");
						System.out.println(jogador.getNome() + " Perde 3 de vida");
						jogador.setEnergiaAtual(-3);
					}
					break;
				default:
					System.out.println(jogador.getNome() + " Perde 2 de vida");
					jogador.setEnergiaAtual(-2);
					break;
				}

			} else if (fM < fJ) {
				System.out.println(jogador.getNome() + " ataca com " + fJ + " de for�a e fere " + npc.getNome() + ".");
				System.out.println("Deseja usar a sorte? 1-Sim/2-N�o");
				System.out.println("Sorte atual:" + jogador.getSorteAtual());
				int decisao = s.nextInt();
				switch (decisao) {
				case 1:
					boolean sorte = usarSorte(jogador);
					if (sorte) {
						System.out.println("Wow!, que sorte!");
						System.out.println(npc.getNome() + " Perde 4 de vida");
						npc.setEnergiaAtual(npc.getEnergiaAtual() - 4);
					} else {
						System.out.println("Ops, m� sorte!");
						System.out.println(npc.getNome() + " Perde 1 de vida");
						npc.setEnergiaAtual(npc.getEnergiaAtual() - 1);
					}
					break;
				default:
					System.out.println(npc.getNome() + " Perde 2 de vida");
					npc.setEnergiaAtual(npc.getEnergiaAtual() - 2);
					break;
				}
			}

		} while (jogador.getEnergiaAtual() > 0 && npc.getEnergiaAtual() > 0);

		if (jogador.getEnergiaAtual() == 0) {
			System.out.println("Voc� foi derrotado!\n   Game Over!!!");
			System.exit(0);
		} else if (npc.getEnergiaAtual() == 0) {
			System.out.println("Vit�ria!! Inimigo derrotado!");
		}

	}
	
	public Livro novoJogo(String nomeLivro, String nomeHeroi, int opcao) {
		Livro livro = new Livro();
		livro.setNome(nomeLivro);
		livro.setJogador( criarPersonagem(nomeHeroi, opcao) );
		return livro;
	}

	private Heroi criarPersonagem(String nome, int opcao) {
		Bolsa b = new Bolsa();
//		s = new Scanner(System.in);
		dado = new Random();
		
//		System.out.println("Qual o nome do heroi?");
		Heroi h = new Heroi(nome, dado.nextInt(12) + 13, dado.nextInt(6) + 7, dado.nextInt(6) + 7, b);
		Item i = new EquipItem("Espada", "Espada sem nada demais", 1, 0);
		b.addItem(i);
		i = new EquipItem("Armadura de Couro", "Armadura simples", 1, 0);
		b.addItem(i);
		i = new EquipItem("Escudo", "Escudo simples sem nada demais", 1, 0);
		b.addItem(i);
		i = new Item("Provis�es", "Cura +4 de Energia", 10, 0);
		i.setMobEne(4);
		b.addItem(i);
		i = new Item("Lampi�o", "Para iluminar os caminhos escuros", 1, 0);

		// b.listarItens();

		System.out.println("Escolha uma  po��o abaixo:\n1- Po��o da Habilidade\nRestaura seus pontos de For�a."
				+ "\n2- Po��o do Vigor\nRestaura toda sua energia.\n3- Po��o da Fortuna\nRestaura sua sorte e aumenta em +1.");
//		int opcao = s.nextInt();
		while ((opcao <= 0) || (opcao >= 4)) {
			System.out.println("Op��o Inv�lida, digite novamente");
			opcao = s.nextInt();
		}

		switch (opcao) {
		case 1:
			i = new Item("Po��o da Habilidade", "Restaura seus pontos de For�a.", 2, 0);
			i.setModHab(h.getAtaqueMax());
			b.addItem(i);
			break;
		case 2:
			i = new Item("Po��o do Vigor", "Restaura toda sua energia", 2, 0);
			i.setMobEne(h.getEnergiaMax());
			b.addItem(i);
			break;
		default:
			i = new Item("Po��o da Fortuna", "Restaura sua sorte e aumenta em +1", 2, 0);
			i.setMobSorMax(1);
			i.setMobSor(h.getSorteMax() + 1);
			b.addItem(i);
			break;
		}

		System.out.println(h);
		for (Item it : b.listarItens()) {
			System.out.println(it);
		}

		return h;
	}

	public Npc criarNpc() {
		s = new Scanner(System.in);
		Npc m = new Npc();
		System.out.println("Digite o nome do NPC:");
		m.setNome(s.nextLine());
		System.out.println("Qual o poder de Habilidade?:");
		m.setAtaqueMax(s.nextInt());
		m.setAtaqueAtual(m.getAtaqueMax());
		System.out.println("Qual a Energia?:");
		m.setEnergiaMax(s.nextInt());
		m.setEnergiaAtual(m.getEnergiaMax());
		return m;
	}

	public Item gerarItem() {
		Item i = new Item();
		int mod = 0;
		s = new Scanner(System.in);
		/*
		 * System.out.println("Qual o nome do Item?"); i.setNome(s.nextLine());
		 * System.out.println("Escreva uma breve descri��o:");
		 * i.setDescriao(s.nextLine()); System.out.println("Quantos voc� tem?");
		 * i.setQuantidade(s.nextInt()); System.out.println("Quanto custou?");
		 * i.setPreco(s.nextInt());
		 */
		System.out.println("Qual o tipo do item? \n1- Consumivel\n2- Equipamento\n3- Item chave");
		mod = s.nextInt();
		s.nextLine();
		if (mod == 1) {
			System.out.println("Qual o nome do Item?");
			i.setNome(s.nextLine());
			System.out.println("Escreva uma breve descri��o:");
			i.setDescriao(s.nextLine());
			System.out.println("Quantos voc� tem?");
			i.setQuantidade(s.nextInt());
			System.out.println("Quanto custou?");
			i.setPreco(s.nextInt());
			System.out.println("Qual Habilidade modifica?\n1- Habilidade\n2- Energia\n3- Sorte");
			mod = s.nextInt();
			switch (mod) {
			case 1:
				System.out.println("Qual o modificador de Habilidade?");
				i.setModHab(s.nextInt());
			case 2:
				System.out.println("Qual o modificador de Energia?");
				i.setMobEne(s.nextInt());
			case 3:
				System.out.println("Qual o modificador de Sorte?");
				i.setMobSor(s.nextInt());
			default:
				break;
			}
		} else if (mod == 2) {
			EquipItem equip = new EquipItem();
			System.out.println("Qual o nome do Item?");
			s.nextLine();
			equip.setNome(s.nextLine());
			System.out.println("Escreva uma breve descri��o:");
			equip.setDescriao(s.nextLine());
			s.nextLine();
			System.out.println("Quantos voc� tem?");
			int cust = s.nextInt();
			equip.setQuantidade(cust);
			System.out.println("Quanto custou?");
			cust = s.nextInt();
			equip.setPreco(cust);
			System.out.println("Qual Habilidade modifica?\n1- Habilidade\n2- Energia\n3- Sorte");
			mod = s.nextInt();
			switch (mod) {
			case 1:
				System.out.println("Qual o modificador de Habilidade?");
				equip.setModHab(s.nextInt());
				i = equip;
			case 2:
				System.out.println("Qual o modificador de Energia?");
				equip.setMobEne(s.nextInt());
				i = equip;
			case 3:
				System.out.println("Qual o modificador de Sorte?");
				equip.setMobSor(s.nextInt());
				i = equip;
			default:
				break;
			}
		}
		return i;
	}

	public void usarPocao(Heroi jogador) throws Exception{
		System.out.println("Digite o c�digo da po��o");
		int cod = s.nextInt();
		Item i = jogador.getBolsa().buscarItem(cod);
		jogador.modificador(i);
		jogador.getBolsa().removeItem(i);
		System.out.println(jogador);
		return;
	}

	public void equiparItem(Heroi heroi)  throws Exception{
		for (Item i : heroi.getBolsa().listarEquipItens()) {
			System.out.println(i.toString( heroi.getBolsa().indexOf(i) ));
		}
		System.out.println("Digite o c�digo do equipamento!");
		int cod = s.nextInt();
		try {
			heroi.equiparItem( (EquipItem) heroi.getBolsa().buscarItem(cod));
		}catch (ClassCastException | IndexOutOfBoundsException e) {
			System.out.println(e);
			System.out.println("Código informado não é válido");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Loja lojaYaztromo() {
		Bolsa artigos = new Bolsa();
		Item i = new Item("Po�ao de Cura", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		i = new Item("Po�ao de Controle de Plantas", "Sem descri��o", 1, 2);
		artigos.addItem(i);
		i = new Item("Po�ao de Serenidade", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		i = new Item("Po�ao de Controle de Insetos", "Sem descri��o", 1, 2);
		artigos.addItem(i);
		i = new Item("Ant�doto", "Sem descri��o", 1, 2);
		artigos.addItem(i);
		i = new Item("�gua benta", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		i = new Item("Anel de luz", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		i = new Item("Botas Saltitantes", "Sem descri��o", 1, 2);
		artigos.addItem(i);
		i = new Item("Corda de Escalada", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		i = new Item("Rede de Aprisionamento", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		i = new Item("Bra�adeira de for�a", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		i = new Item("Luva de Arremesso", "Sem descri��o", 1, 2);
		artigos.addItem(i);
		i = new Item("Varinha Localizadora de �gua", "Sem descri��o", 1, 2);
		artigos.addItem(i);
		i = new Item("Alho", "Sem descri��o", 1, 2);
		artigos.addItem(i);
		i = new Item("Tiara de concentra��o", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		i = new Item("Capsula de Fogo", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		i = new Item("Filtors Nasais", "Sem descri��o", 1, 3);
		artigos.addItem(i);
		Loja lYaztromo = new Loja(artigos);
		return lYaztromo;

	}

	public boolean condicaoDeCompra(int preco, Heroi jogador) {
		if (preco <= jogador.getOuro()) {
			return true;
		} else if (preco > jogador.getOuro()) {
			return false;
		}
		return false;
	}

	public void comprar(Heroi jogador, Loja loja)  throws Exception{
		s = new Scanner(System.in);
		int cod;
		Item i = new Item();
		do {
			System.out.println("Bem Vindo a loja  (SALDO: " + jogador.getOuro() + "g)");
			System.out.println("------------------------------");
			System.out.println("         ITENS A VENDA");
			System.out.println("------------------------------");
			for (Item it : loja.getArtigos().listarItens()) {
				System.out.println(it.toString( loja.getArtigos().indexOf(it) ));
			}
			do {
				System.out.println("Qual voc� dejesa? (Digite o c�digo do Item / 0- Sair da loja)");
				cod = s.nextInt();
				if (cod == 0) {
					System.out.println("Obrigado por comprar conosco!");
					return;
				} else if (cod > 0) {
					i = loja.getArtigos().buscarItem(cod);
				}
			} while (i == null);
			System.out.println("Este � o Item que voc� quer?(1- Sim / 2- N�o)\n" + i.getNome() + " - Pre�o: "
					+ i.getPreco() + "g");
			cod = s.nextInt();
			while (cod < 0 || cod > 2) {
				System.out.println("Op��o inv�lida");
				cod = s.nextInt();
			}
			switch (cod) {
			case 1:
				if (condicaoDeCompra((i.getPreco() * i.getQuantidade()), jogador)) {
					jogador.getBolsa().addItem(i);
					jogador.setOuro(-(i.getPreco() * i.getQuantidade()));
					loja.getArtigos().removeItem(i);
				} else {
					System.out.println("Saldo insuficiente");
				}
				break;
			case 2:
				cod = 0;
				break;
			default:
				System.out.println(("Op��o Inv�lida"));
				cod = 0;
				break;
			}
		} while (cod != 0);
		System.out.println("Obrigado por comprar conosco!");
		return;
	}

}
