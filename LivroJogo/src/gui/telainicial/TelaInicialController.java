package gui.telainicial;

import beans.Livro;
import gui.LivroJogoApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaInicialController {
	@FXML private Label lDetalhes;
	@FXML private TextField tf;
	private Livro livro;
	private LivroJogoApp lvapp;
	
	public void refresh () {
		lDetalhes.setText(livro.toString());
	}
	public void setLivro (Livro livro) {
		this.livro = livro;
	}
	public void setLivroJogoApp(LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}
}
