package crudpaciente;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PacienteControl {
	private StringProperty nome = new SimpleStringProperty("");
	private IntegerProperty rg = new SimpleIntegerProperty(0);
	private IntegerProperty telefone = new SimpleIntegerProperty(0);

	private ObservableList<Paciente> lista = FXCollections.observableArrayList();

	public void novopaciente() {
		nome.set("");
		rg.set(0);
		telefone.set(0);
	}

	public void removerpaciente(Paciente p) {
		lista.remove(p);
	}

	public void removerlista (Paciente p) {
		Paciente paciente = new Paciente();
		lista.remove(paciente);
	}
	
	public void adicionarpaciente() {
		
		Paciente p = new Paciente();
		p.setNome(nome.get());
		p.setRg(rg.get());
		p.setTelefone(telefone.get());
		lista.add(p);

	}

	public StringProperty NomeProperty() {
		return nome;
	}

	public IntegerProperty RgProperty() {
		return rg;
	}

	public IntegerProperty TelefoneProperty() {
		return telefone;
	}

	public ObservableList<Paciente> getLista() {
		return lista;
	}
}