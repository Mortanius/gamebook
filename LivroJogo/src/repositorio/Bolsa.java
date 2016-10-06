package repositorio;

import java.io.*;

import beans.Item;

@SuppressWarnings("serial")
public class Bolsa implements Serializable {

	private Item[] itens;
	private int p;
	private int number;

	public Bolsa() {
		super();
		this.itens = new Item[100];
		this.p = 0;
		this.number = 1;
	}

	public Item[] getItens() {
		return itens;
	}

	public void setItens(Item[] itens) {
		this.itens = itens;
	}

	public void addItem(Item item) {
		for (int i = 0; i < p; i++) {
			if(itens[i] != null)
			if (itens[i].equals(item)) {
				itens[i].setQuantidade(+1);
				return;
			}
		}
				
		if (item != null) {
			this.itens[p] = item;
			itens[p].setCodigo(number);
			number++;
			p++;
			//System.out.println("Item guardado!!");
			return;
		}
		return;
		/*
		 * for (int i = 0; i < itens.length; i++) { if (this.itens[i] == null) {
		 * this.itens[i] = item; this.itens[i].setCodigo(number++); return; }
		 * else if (this.itens[i].equals(item.getNome())) {
		 * this.itens[i].setQuantidade(this.itens[i].getQuantidade() + 1);
		 * return; } } return;
		 */
	}

	public void removeItem(Item item) {

		for (int i = 0; i < p; i++) {
			if(itens[i]!=null)
			if (itens[i].equals(item)) {
				if (itens[i].getQuantidade() > 1) {
					this.itens[i].setQuantidade(-1);
					System.out.println("Item removido");
					return;
				} else if (itens[i].getQuantidade() == 1) {
					this.itens[i] = null;
					this.itens[i] = this.itens[p];
					this.itens[p] = null;
					p--;
					System.out.println("Item removido!");
					return;
				}
			}
		}

		/*
		 * 
		 * Item o = new Item(); o = null; for (int i = 0; i < itens.length; i++)
		 * { if (this.itens[i].getCodigo() == code) { if
		 * (this.itens[i].getQuantidade() > 0) {
		 * this.itens[i].setQuantidade(this.itens[i].getQuantidade() - 1); if
		 * (this.itens[i].getQuantidade() <= 0) { this.itens[i] = o; return; }
		 * return; } } }
		 */
		return;
	}

	public void listarItens() {
		for (int i = 0; i < itens.length; i++) {
			if (this.itens[i] != null) {
				System.out.println(this.itens[i]);
			}

		}
	}

	public boolean checarItem(Item item) {
		for (Item iten : itens) {
			if (iten != null) {
				if (iten.equals(item)) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}
		return false;
	}

	public Item buscarItem(int cod) {
		Item i = null;
		for (Item item : itens) {
			if (item != null) {
				if (item.getCodigo() == cod) {
					i = item;
					return i;
				}
			}
		}
		System.out.println("Item não encontrado!");
		return i;
	}

}
