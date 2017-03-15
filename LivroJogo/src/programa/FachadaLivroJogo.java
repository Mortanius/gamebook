package programa;

import beans.Heroi;
import beans.Item;
import beans.Livro;
import beans.Npc;
import programa.negocio.Interacoes;
import beans.Loja;

public class FachadaLivroJogo {
	private Interacoes interacoes;
	private static FachadaLivroJogo instancia;
	
	private FachadaLivroJogo () {
		interacoes = new Interacoes();	
	}
	
	public static FachadaLivroJogo getInstancia () {
		if (instancia == null) {
			instancia = new FachadaLivroJogo();
		}
		return instancia;
	}
	
	public boolean usarSorte(Heroi jogador) {
		return interacoes.usarSorte(jogador);
	}
	public void batalha(Heroi jogador) {
		interacoes.batalha(jogador);
	}
	public Livro novoJogo(String nomeLivro, String nomeHeroi, int opcao) {
		return interacoes.novoJogo(nomeLivro, nomeHeroi, opcao);
	}
	public Npc criarNpc() {
		return interacoes.criarNpc();
	}
	public Item gerarItem() {
		return interacoes.gerarItem();
	}
	public void usarPocao (Heroi jogador) {
		try {
			interacoes.usarPocao(jogador);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void equiparItem(Heroi jogador) throws Exception {
		interacoes.equiparItem(jogador);
	}
	public Loja lojaYaztromo() {
		return interacoes.lojaYaztromo();
	}
	public void comprar(Heroi jogador, Loja loja) throws Exception {
		interacoes.comprar(jogador, loja);
	}
	
	
}

