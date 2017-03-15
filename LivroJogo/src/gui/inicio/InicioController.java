package gui.inicio;
import gui.LivroJogoApp;
import javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioController {
	private LivroJogoApp lvapp;
	
	@FXML private void novoJogo() {
		System.out.println("novo jogo");
		lvapp.carregarNovoJogo();
	}
	@FXML private void carregarJogo() {
		System.out.println("carregar jogo");
		lvapp.carregarContinuarJogo();
	}
	
	public void setLivroJogoApp (LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}
	
	
}
