package crudreceituario;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReceituarioControl {
	private StringProperty nome = new SimpleStringProperty("");
	private IntegerProperty repouso = new SimpleIntegerProperty(0);
	private StringProperty remedio = new SimpleStringProperty("");
	private IntegerProperty cro = new SimpleIntegerProperty(0);

	private ObservableList<Receituario> lista = FXCollections.observableArrayList();

	public void novareceita() {
		nome.set("");
		repouso.set(0);
		remedio.set("");		
	}

	public void removerreceita(Receituario r) {
		lista.remove(r);
	}

	public void adicionarreceita() {

		Receituario r = new Receituario();
		r.setNome(nome.get());
		r.setRepouso(repouso.get());
		r.setRemedio(remedio.get());
		r.setCro(cro.get());
		lista.add(r);

	}

	public StringProperty NomeProperty() {
		return nome;
	}

	public IntegerProperty RepousoProperty() {
		return repouso;
	}

	public StringProperty RemedioProperty() {
		return remedio;
	}

	public IntegerProperty CroProperty() {
		return cro;
	}

	public ObservableList<Receituario> getLista() {
		return lista;
	}
}
