package gui.continuarjogo;

import arquivo.Arquivo;
import beans.Livro;
import gui.LivroJogoApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContinuarJogoController {
	private LivroJogoApp lvapp;
	@FXML private TableView<String[]> listaArquivos;
	@FXML private TableColumn<String[], String> colunaNomeL;
	@FXML private TableColumn<String[], String> colunaNomeH;
	@FXML private TableColumn<String[], String> colunaPos;
	@FXML private Button bt1;
	
	
	@FXML private void initialize () {
		colunaNomeL.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
		colunaNomeH.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
		colunaPos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));
		bt1.setDisable(true);
	}

	
	public void setFiles(ObservableList<String[]> saves) {
		listaArquivos.setItems(saves);
	}
	
	public void setLivroJogoApp(LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}
	
	@FXML private void checkSelected() {
		if ( listaArquivos.getSelectionModel().getSelectedItem() != null ) {
			bt1.setDisable(false);
		}
	}
	
	@FXML private void carregarArquivo() {
		String s[] = listaArquivos.getSelectionModel().getSelectedItem();
		String fileName = s[3];
		Livro livro = Arquivo.lerArquivo(fileName);
		lvapp.carregarTelaInicial(livro);
	}
	
}
