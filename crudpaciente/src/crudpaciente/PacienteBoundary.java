package crudpaciente;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class PacienteBoundary extends Application {

	private TextField txtnome = new TextField();
	private TextField txtrg = new TextField();
	private TextField txttelefone = new TextField();

	private PacienteControl control = new PacienteControl();
	private TableView<Paciente> tableview = new TableView<>();

	public void ligacoes() {
		
		Bindings.bindBidirectional(txtnome.textProperty(), control.NomeProperty());

		Bindings.bindBidirectional(txtrg.textProperty(), control.RgProperty(),
				(StringConverter) new IntegerStringConverter());


		Bindings.bindBidirectional(txttelefone.textProperty(), control.TelefoneProperty(),
				(StringConverter) new IntegerStringConverter());
		
		
	}

	public void abastecerTable() {
		TableColumn<Paciente, String> ColunaNome = new TableColumn<>("Nome");
		ColunaNome.setCellValueFactory(new PropertyValueFactory<Paciente, String>("Nome"));

		TableColumn<Paciente, Integer> ColunaRG = new TableColumn<>("RG");
		ColunaRG.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("rg"));

		TableColumn<Paciente, Integer> ColunaTelefone = new TableColumn<>("Telefone");
		ColunaTelefone.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("Telefone"));

		TableColumn<Paciente, Void> ColunaAcoes = new TableColumn<>("Remover");
		Callback<TableColumn<Paciente, Void>, TableCell<Paciente, Void>> callBack = new Callback<>() {	

			@Override
			public TableCell<Paciente, Void> call(TableColumn<Paciente, Void> coluna) {
				// TODO Auto-generated method stub

				TableCell<Paciente, Void> tablecell = new TableCell<>() {
					Button botaoremover = new Button("Remover");
					{
						botaoremover.setOnAction(evento -> {
							Paciente p = tableview.getItems().get(getIndex());
							control.removerpaciente(p);
						
						});
					}

					public void updateItem(Void item, boolean vazio) {
						super.updateItem(item, vazio);
						if (vazio) {
							setGraphic(null);
						} else {
							setGraphic(botaoremover);
						}
					}
				};
				return tablecell;
			}
		};

		ColunaAcoes.setCellFactory(callBack);
		double divisao = 600.0 / 4.0;

		ColunaNome.setPrefWidth(divisao);
		ColunaNome.setStyle("-fx-background-color: F1D4E5");
		ColunaRG.setPrefWidth(divisao);
		ColunaRG.setStyle("-fx-background-color: F1D4E5");
		ColunaTelefone.setPrefWidth(divisao);
		ColunaTelefone.setStyle("-fx-background-color: F1D4E5");
		ColunaAcoes.setPrefWidth(divisao);
		ColunaAcoes.setStyle("-fx-background-color: F1D4E5");

		tableview.getColumns().addAll(ColunaNome, ColunaRG, ColunaTelefone, ColunaAcoes);
		tableview.setItems(control.getLista());
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Cadastro de Paciente");
		Image image = new Image("file:///C:/Users/Leonardo/eclipse-workspace/crudpaciente/src/crudpaciente/image/dentista_icon.png");
        primaryStage.getIcons().add(image);
        
        
        
		BorderPane borderpane = new BorderPane();
		Scene cena = new Scene(borderpane,600,400);
		GridPane gridpane = new GridPane();
		borderpane.setTop(gridpane);
		borderpane.setCenter(tableview);
		borderpane.setStyle("-fx-background-color: FCE9F1");
		
		gridpane.add(new Label("Nome do paciente:  "), 0, 0);
		gridpane.add(txtnome, 1, 0);
		
		
		gridpane.add(new Label("RG do paciente:  "), 0, 1);
		gridpane.add(txtrg, 1, 1);
		
		gridpane.add(new Label("Telefone do paciente:  "), 0, 2);
		gridpane.add(txttelefone, 1, 2);
		
	
		Button botaonovo = new Button("Novo Registro");
		botaonovo.setOnAction(evento -> control.novopaciente());
		
		Button botaoadicionar = new Button("Salvar na lista");
		botaoadicionar.setOnAction(evento -> control.adicionarpaciente());
		
		botaonovo.setStyle("-fx-background-color: F1D4E5");
		botaoadicionar.setStyle("-fx-background-color: F1D4E5");
		
		tableview.setStyle("-fx-background-color: FCE9F1");
		
		FlowPane painelbotoes = new FlowPane();
		painelbotoes.getChildren().addAll(botaonovo,botaoadicionar);
		
		gridpane.add(painelbotoes, 1, 3);

		
		
		ligacoes();
		abastecerTable();
		
		primaryStage.setScene(cena);
		primaryStage.show();

	}


	public static void main(String[] args) {
		Application.launch(args);
	}	
}
