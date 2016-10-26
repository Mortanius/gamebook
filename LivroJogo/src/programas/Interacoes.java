package programas;

import java.util.*;

import beans.EquipItem;
import beans.Heroi;
import beans.Item;
import beans.Loja;
import beans.Npc;
import repositorio.Bolsa;

public class Interacoes {

	private Npc npc;
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
			jogador.setSorteAtual(-1);
			System.out.println("Passou! \nSorte Atual: " + jogador.getSorteAtual() + "/" + jogador.getSorteMax());
			return sorte;
		} else {
			jogador.setSorteAtual(-1);
			System.out.println("Deu ruim! \nSorte Atual: " + jogador.getSorteAtual() + "/" + jogador.getSorteMax());
			return sorte;
		}
	}

	public void mostrarVidaEHab(Heroi jogador, Npc npc) {
		System.out.println(jogador.getNome() + "\nEnergia: " + jogador.getEnergiaAtual() + "/" + jogador.getEnergiaMax()
				+ " Força:" + jogador.getAtaqueMax() + jogador.incremento() + "\n");
		System.out.println(npc.getNome() + "\nEnergia: " + npc.getEnergiaAtual() + "/" + npc.getEnergiaMax()
				+ " Força: " + npc.getAtaqueAtual() + "\n");
	}

	public void batalha(Heroi jogador) {
		s = new Scanner(System.in);
		dado = new Random();
		npc = criarNpc();
		do {
			mostrarVidaEHab(jogador, npc);
			int fM = (dado.nextInt(12) + 1) + npc.getAtaqueAtual();
			int fJ = (dado.nextInt(12) + 1) + jogador.getAtaqueAtual();

			System.out.println(npc.getNome() + " ataca com: " + fM + " x " + fJ + " de " + jogador.getNome());

			if (fM > fJ) {
				System.out.println(npc.getNome() + " ataca com " + fM + " de força e fere " + jogador.getNome() + ".");
				System.out.println("Deseja usar a sorte? 1-Sim/2-Não");
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
						// System.out.println("Ops, má sorte!");
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
				System.out.println(jogador.getNome() + " ataca com " + fJ + " de força e fere " + npc.getNome() + ".");
				System.out.println("Deseja usar a sorte? 1-Sim/2-Não");
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
						System.out.println("Ops, má sorte!");
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
			System.out.println("Você foi derrotado!\n   Game Over!!!");
			System.exit(0);
		} else if (npc.getEnergiaAtual() == 0) {
			System.out.println("Vitória!! Inimigo derrotado!");
		}

	}

	public Heroi criarPersonagem() {
		Bolsa b = new Bolsa();
		s = new Scanner(System.in);
		dado = new Random();

		System.out.println("Qual o nome do heroi?");
		Heroi h = new Heroi(s.nextLine(), dado.nextInt(12) + 13, dado.nextInt(6) + 7, dado.nextInt(6) + 7, b);
		Item i = new Item("Espada", "Espada sem nada demais", 1, 0);
		b.addItem(i);
		i = new Item("Armadura de Couro", "Armadura simples", 1, 0);
		b.addItem(i);
		i = new Item("Escudo", "Escudo simples sem nada demais", 1, 0);
		b.addItem(i);
		i = new Item("Provisões", "Cura +4 de Energia", 10, 0);
		i.setMobEne(4);
		b.addItem(i);
		i = new Item("Lampião", "Para iluminar os caminhos escuros", 1, 0);

		// b.listarItens();

		System.out.println("Escolha uma  poção abaixo:\n1- Poção da Habilidade\nRestaura seus pontos de Força."
				+ "\n2- Poção do Vigor\nRestaura toda sua energia.\n3- Poção da Fortuna\nRestaura sua sorte e aumenta em +1.");
		int opcao = s.nextInt();
		while ((opcao <= 0) || (opcao >= 4)) {
			System.out.println("Opção Inválida, digite novamente");
			opcao = s.nextInt();
		}

		switch (opcao) {
		case 1:
			i = new Item("Poção da Habilidade", "Restaura seus pontos de Força.", 2, 0);
			i.setModHab(h.getAtaqueMax());
			b.addItem(i);
			break;
		case 2:
			i = new Item("Poção do Vigor", "Restaura toda sua energia", 2, 0);
			i.setMobEne(h.getEnergiaMax());
			b.addItem(i);
			break;
		default:
			i = new Item("Poção da Fortuna", "Restaura sua sorte e aumenta em +1", 2, 0);
			i.setMobSorMax(1);
			i.setMobSor(h.getSorteMax() + 1);
			b.addItem(i);
			break;
		}

		System.out.println(h);
		b.listarItens();

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
		 * System.out.println("Escreva uma breve descrição:");
		 * i.setDescriao(s.nextLine()); System.out.println("Quantos você tem?");
		 * i.setQuantidade(s.nextInt()); System.out.println("Quanto custou?");
		 * i.setPreco(s.nextInt());
		 */
		System.out.println("Qual o tipo do item? \n1- Consumivel\n2- Equipamento\n3- Item chave");
		mod = s.nextInt();
		if (mod == 1) {
			System.out.println("Qual o nome do Item?");
			i.setNome(s.nextLine());
			System.out.println("Escreva uma breve descrição:");
			i.setDescriao(s.nextLine());
			System.out.println("Quantos você tem?");
			i.setQuantidade(s.nextInt());
			System.out.println("Quanto custou?");
			i.setPreco(s.nextInt());
			System.out.println("Qual Habilidade modifica?\n1- Habilidade\n2- Energia\n3- Sorte");
			mod = s.nextInt();
			switch (mod) {
			case 1:
				System.out.println("Qual o modificador de Habilidade?");
				i.setModHab(s.nextInt());
				return i;
			case 2:
				System.out.println("Qual o modificador de Energia?");
				i.setMobEne(s.nextInt());
				return i;
			case 3:
				System.out.println("Qual o modificador de Sorte?");
				i.setMobSor(s.nextInt());
				return i;
			default:
				break;
			}
		} else if (mod == 2) {
			EquipItem equip = new EquipItem();
			System.out.println("Qual o nome do Item?");
			s.nextLine();
			equip.setNome(s.nextLine());
			System.out.println("Escreva uma breve descrição:");
			equip.setDescriao(s.nextLine());
			s.nextLine();
			System.out.println("Quantos você tem?");
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
				return i;
			case 2:
				System.out.println("Qual o modificador de Energia?");
				equip.setMobEne(s.nextInt());
				i = equip;
				return i;
			case 3:
				System.out.println("Qual o modificador de Sorte?");
				equip.setMobSor(s.nextInt());
				i = equip;
				return i;
			default:
				break;
			}
		}
		System.out.println("Item Criado com Sucesso!");
		return i;
	}

	public void UsarPocao(Heroi jogador) throws Exception{
		System.out.println("Digite o código da poção");
		int cod = s.nextInt();
		jogador.modificador(jogador.getBolsa().buscarItem(cod));
		jogador.getBolsa().removeItem(jogador.getBolsa().buscarItem(cod));
		System.out.println(jogador);
		return;
	}

	public void equipamento(Heroi heroi)  throws Exception{
		heroi.getBolsa().listarItens();
		System.out.println("Digite o código do equipamento!");
		int cod = s.nextInt();
		heroi.equiparItem((EquipItem) heroi.getBolsa().buscarItem(cod));
	}

	public Loja lojaYaztromo() {
		Bolsa artigos = new Bolsa();
		Item i = new Item("Poçao de Cura", "Sem descrição", 1, 3);
		artigos.addItem(i);
		i = new Item("Poçao de Controle de Plantas", "Sem descrição", 1, 2);
		artigos.addItem(i);
		i = new Item("Poçao de Serenidade", "Sem descrição", 1, 3);
		artigos.addItem(i);
		i = new Item("Poçao de Controle de Insetos", "Sem descrição", 1, 2);
		artigos.addItem(i);
		i = new Item("Antídoto", "Sem descrição", 1, 2);
		artigos.addItem(i);
		i = new Item("Àgua benta", "Sem descrição", 1, 3);
		artigos.addItem(i);
		i = new Item("Anel de luz", "Sem descrição", 1, 3);
		artigos.addItem(i);
		i = new Item("Botas Saltitantes", "Sem descrição", 1, 2);
		artigos.addItem(i);
		i = new Item("Corda de Escalada", "Sem descrição", 1, 3);
		artigos.addItem(i);
		i = new Item("Rede de Aprisionamento", "Sem descrição", 1, 3);
		artigos.addItem(i);
		i = new Item("Braçadeira de força", "Sem descrição", 1, 3);
		artigos.addItem(i);
		i = new Item("Luva de Arremesso", "Sem descrição", 1, 2);
		artigos.addItem(i);
		i = new Item("Varinha Localizadora de Àgua", "Sem descrição", 1, 2);
		artigos.addItem(i);
		i = new Item("Alho", "Sem descrição", 1, 2);
		artigos.addItem(i);
		i = new Item("Tiara de concentração", "Sem descrição", 1, 3);
		artigos.addItem(i);
		i = new Item("Capsula de Fogo", "Sem descrição", 1, 3);
		artigos.addItem(i);
		i = new Item("Filtors Nasais", "Sem descrição", 1, 3);
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
			loja.getArtigos().listarItens();
			do {
				System.out.println("Qual você dejesa? (Digite o código do Item / 0- Sair da loja)");
				cod = s.nextInt();
				if (cod == 0) {
					System.out.println("Obrigado por comprar conosco!");
					return;
				} else if (cod > 0) {
					i = loja.getArtigos().buscarItem(cod);
				}
			} while (i == null);
			System.out.println("Este é o Item que você quer?(1- Sim / 2- Não)\n" + i.getNome() + " - Preço: "
					+ i.getPreco() + "g");
			cod = s.nextInt();
			while (cod < 0 || cod > 2) {
				System.out.println("Opção inválida");
				cod = s.nextInt();
			}
			switch (cod) {
			case 1:
				if (condicaoDeCompra((i.getPreco() * i.getQuantidade()), jogador)) {
					i.setCodigo(0);
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
				System.out.println(("Opção Inválida"));
				cod = 0;
				break;
			}
		} while (cod != 0);
		System.out.println("Obrigado por comprar conosco!");
		return;
	}

}
