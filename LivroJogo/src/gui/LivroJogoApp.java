package gui;

import java.io.IOException;

import arquivo.Arquivo;
import beans.Livro;
import gui.continuarjogo.ContinuarJogoController;
import gui.inicio.InicioController;
import gui.novojogo.NovoJogoController;
import gui.telainicial.TelaInicialController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LivroJogoApp extends Application {
	private Stage primaryStage;
	private BorderPane rootScene;
	
	@Override
	public void start (Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Assistente Livro Jogo");
		this.rootScene = new BorderPane();
		
		Scene scene = new Scene(rootScene, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		carregarInicio();
		
	}
	public void carregarInicio() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("inicio/Inicio.fxml"));
			AnchorPane inicio = (AnchorPane) loader.load();
			
			this.rootScene.setCenter(inicio);
			
			InicioController controller = loader.getController();
			controller.setLivroJogoApp(this);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main (String[] args) {
		launch(args);
	}
	
	public void carregarNovoJogo () {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("novojogo/NovoJogo.fxml"));
			AnchorPane novoJogo = (AnchorPane) loader.load();
			
			this.rootScene.setCenter(novoJogo);
			
			NovoJogoController controller = loader.getController();
			controller.setLivroJogoApp(this);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarContinuarJogo () {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("continuarjogo/ContinuarJogo.fxml"));
			
			AnchorPane continuarJogo = (AnchorPane) loader.load();
			this.rootScene.setCenter(continuarJogo);
			
			ContinuarJogoController controller = loader.getController();
			controller.setLivroJogoApp(this);
			
			ObservableList<String[]> saves = FXCollections.observableArrayList();
			for (String[] s : Arquivo.dadosArquivos()) {
				saves.add(s);
			}
			controller.setFiles(saves);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void carregarTelaInicial (Livro livro) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("telainicial/TelaInicial.fxml"));
			AnchorPane telaInicial = (AnchorPane) loader.load();
			
			this.rootScene.setCenter(telaInicial);
			
			TelaInicialController controller = loader.getController();
			controller.setLivro(livro);
			controller.setLivroJogoApp(this);
			controller.refresh();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
