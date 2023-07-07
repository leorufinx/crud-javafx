package crudreceituario;

public class Receituario {
	private String nome;
	private int repouso;
    private String remedio;
    private int cro; 
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getRepouso() {
		return repouso;
	}
	public void setRepouso(int repouso) {
		this.repouso = repouso;
	}
	public String getRemedio() {
		return remedio;
	}
	public void setRemedio(String remedio) {
		this.remedio = remedio;
	}
	public int getCro() {
		return cro;
	}
	public void setCro(int cro) {
		this.cro = cro;
	}
}
