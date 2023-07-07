package crudreceituario;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
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

public class ReceituarioBoundary extends Application {

	private TextField txtnome = new TextField();
	private TextField txtrepouso = new TextField();
	private TextField txtremedio = new TextField();
	private TextField txtcro = new TextField();

	private ReceituarioControl control = new ReceituarioControl();
	private TableView<Receituario> tableview = new TableView<>();

	public void ligacoes() {

		Bindings.bindBidirectional(txtnome.textProperty(), control.NomeProperty());

		Bindings.bindBidirectional(txtrepouso.textProperty(), control.RepousoProperty(),
				(StringConverter) new IntegerStringConverter());

		Bindings.bindBidirectional(txtremedio.textProperty(), control.RemedioProperty());

		Bindings.bindBidirectional(txtcro.textProperty(), control.CroProperty(),
				(StringConverter) new IntegerStringConverter());

	}

	public void abastecerTable() {
		TableColumn<Receituario, String> ColunaNome = new TableColumn<>("Nome do Paciente");
		ColunaNome.setCellValueFactory(new PropertyValueFactory<Receituario, String>("nome"));

		TableColumn<Receituario, String> ColunaRepouso = new TableColumn<>("Dias de Repouso");
		ColunaRepouso.setCellValueFactory(new PropertyValueFactory<Receituario, String>("repouso"));

		TableColumn<Receituario, String> ColunaRemedio = new TableColumn<>("Prescrição");
		ColunaRemedio.setCellValueFactory(new PropertyValueFactory<Receituario, String>("remedio"));

		TableColumn<Receituario, String> ColunaCRO = new TableColumn<>("CRO");
		ColunaCRO.setCellValueFactory(new PropertyValueFactory<Receituario, String>("cro"));

		TableColumn<Receituario, Void> ColunaAcoes = new TableColumn<>("Remover");
		Callback<TableColumn<Receituario, Void>, TableCell<Receituario, Void>> callBack = new Callback<>() {

			@Override
			public TableCell<Receituario, Void> call(TableColumn<Receituario, Void> coluna) {
				// TODO Auto-generated method stub

				TableCell<Receituario, Void> tablecell = new TableCell<>() {
					Button botaoremover = new Button("Remover");
					{
						botaoremover.setOnAction(evento -> {
							Receituario r = tableview.getItems().get(getIndex());
							control.removerreceita(r);

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
		double divisao = 1000.0 / 5.0;

		ColunaNome.setPrefWidth(divisao);
		ColunaNome.setStyle("-fx-background-color: F1D4E5");
		ColunaRepouso.setPrefWidth(divisao);
		ColunaRepouso.setStyle("-fx-background-color: F1D4E5");
		ColunaRemedio.setPrefWidth(divisao);
		ColunaRemedio.setStyle("-fx-background-color: F1D4E5");
		ColunaCRO.setPrefWidth(divisao);
		ColunaCRO.setStyle("-fx-background-color: F1D4E5");
		ColunaAcoes.setPrefWidth(divisao);
		ColunaAcoes.setStyle("-fx-background-color: F1D4E5");

		tableview.getColumns().addAll(ColunaNome, ColunaRepouso, ColunaRemedio, ColunaCRO, ColunaAcoes);
		tableview.setItems(control.getLista());
			
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Cadastro de Prescrição Médica");
		Image image = new Image(
				"file:///C:/Users/Leonardo/eclipse-workspace/crudpaciente/src/crudpaciente/image/dentista_icon.png");
		primaryStage.getIcons().add(image);

		BorderPane borderpane = new BorderPane();
		Scene cena = new Scene(borderpane, 1000, 500);
		GridPane gridpane = new GridPane();
		borderpane.setTop(gridpane);
		borderpane.setCenter(tableview);
		borderpane.setStyle("-fx-background-color: FCE9F1");
		tableview.setStyle("-fx-background-color: FCE9F1");


		gridpane.add(new Label("Nome do paciente:  "), 0, 0);
		gridpane.add(txtnome, 1, 0);

		gridpane.add(new Label("Dias de repouso:  "), 0, 1);
		gridpane.add(txtrepouso, 1, 1);

		gridpane.add(new Label("Prescrição:  "), 0, 2);
		gridpane.add(txtremedio, 1, 2);

		gridpane.add(new Label("CRO do Médico:  "), 0, 3);
		gridpane.add(txtcro, 1, 3);

		Button botaonovo = new Button("Nova Receita");
		botaonovo.setOnAction(evento -> control.novareceita());

		Button botaoadicionar = new Button("Prescrever Receita");
		botaoadicionar.setOnAction(evento -> control.adicionarreceita());
		
		botaonovo.setStyle("-fx-background-color: F1D4E5");
		botaoadicionar.setStyle("-fx-background-color: F1D4E5");

		FlowPane painelbotoes = new FlowPane();
		painelbotoes.getChildren().addAll(botaonovo, botaoadicionar);

		gridpane.add(painelbotoes, 1, 4);

		ligacoes();
		abastecerTable();

		primaryStage.setScene(cena);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);

	}
}
