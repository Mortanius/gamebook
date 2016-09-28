package beans;

import java.io.*;

import repositorio.Bolsa;

@SuppressWarnings("serial")
public class Heroi implements Serializable {

	private String nome;
	private int energiaMax;
	private int energiaAtual;
	private int ataqueMax;
	private int ataqueAtual;
	private int sorteMax;
	private int sorteAtual;
	private Bolsa bolsa;
	private int ouro;
	private int posicao;

	public Heroi() {
		this.ouro = 30;
		this.energiaAtual = energiaMax;
		this.ataqueAtual = ataqueMax;
		this.sorteAtual = sorteMax;
	}

	public Heroi(String nome, int energiaMax, int ataqueMax, int sorteMax, Bolsa bolsa) {
		super();
		this.nome = nome;
		this.energiaMax = energiaMax;
		this.energiaAtual = energiaMax;
		this.ataqueMax = ataqueMax;
		this.ataqueAtual = ataqueMax;
		this.sorteMax = sorteMax;
		this.sorteAtual = sorteMax;
		this.setBolsa(bolsa);
		this.ouro = 30;
		this.posicao = 1;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEnergiaMax() {
		return energiaMax;
	}

	public void setEnergiaMax(int energiaMax) {
		this.energiaMax = energiaMax;
	}

	public int getEnergiaAtual() {
		return energiaAtual;
	}

	public void setEnergiaAtual(int energiaAtual) {
		if (energiaAtual >= 0 && energiaAtual <= this.getEnergiaMax()) {
			this.energiaAtual = energiaAtual;
			if (this.energiaAtual > this.energiaMax) {
				this.energiaAtual = this.energiaMax;
			}
		} else if (energiaAtual < 0) {
			this.energiaAtual = 0;
		}

	}

	public int getAtaqueMax() {
		return ataqueMax;
	}

	public void setAtaqueMax(int ataqueMax) {
		this.ataqueMax = ataqueMax;
	}

	public int getAtaqueAtual() {
		return ataqueAtual;
	}

	public void setAtaqueAtual(int ataqueAtual) {
		if (ataqueAtual >= 0 && ataqueAtual<this.ataqueMax) {
			this.ataqueAtual += ataqueAtual;
		} else if (ataqueAtual < 0) {
			this.ataqueAtual = 0;
		}else if(ataqueAtual == this.ataqueMax){
			this.ataqueAtual = this.ataqueMax;
		}

	}

	public int getSorteMax() {
		return sorteMax;
	}

	public void setSorteMax(int sorteMax) {
		this.sorteMax = sorteMax;
	}

	public int getSorteAtual() {
		return sorteAtual;
	}

	public void setSorteAtual(int sorteAtual) {
		if (sorteAtual >= 0) {
			this.sorteAtual += sorteAtual;
			if (this.sorteAtual > this.sorteMax) {
				this.sorteAtual = this.sorteMax;
			}
		} else if (this.sorteAtual < 0) {
			this.sorteAtual = 0;
		}
	}

	public Bolsa getBolsa() {
		return bolsa;
	}

	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}

	public int getOuro() {
		return ouro;
	}

	public void setOuro(int ouro) {
		this.ouro += ouro;
	}

	public String incremento() {
		String incremento = null;
		if (this.ataqueAtual - this.ataqueMax > 0) {
			incremento = "(+" + (this.ataqueAtual - this.ataqueMax) + ")";
		} else if (this.ataqueAtual - this.ataqueMax <= 0) {
			incremento = "(" + (this.ataqueAtual - this.ataqueMax) + ")";
		}
		return incremento;
	}

	public void modificador(Item item) {
		this.setAtaqueAtual(this.ataqueAtual + item.getModHab());
		this.setEnergiaAtual(item.getMobEne() + item.getMobEne());
		this.sorteMax = (this.getSorteMax() + item.getMobSorMax());
		this.setSorteAtual(this.getSorteAtual() + item.getMobSor());
		return;
	}

	@Override
	public String toString() {
		return nome + "\nEnergia " + energiaAtual + "/" + energiaMax + "\nAtaque " + ataqueMax + incremento()
				+ "\nSorte " + sorteAtual + "/" + sorteMax + "\nOuro " + ouro + "g\n";
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

}