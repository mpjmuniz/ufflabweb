package classes;

import java.io.Serializable;

public class Produto implements Serializable{
	
	private final static long serialVersionUID = 1;
	String nome;
	String id;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	int qtd;
	
	public Produto(String nome, int qtd, String id) {
		
		super();
		this.nome = nome;
		this.qtd = qtd;
		this.id = id;
	}
	
	public Produto(String nome, String id){
		this.qtd = 1;
		this.nome = nome;
		
	}
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the qtd
	 */
	public int getQtd() {
		return qtd;
	}
	/**
	 * @param qtd the qtd to set
	 */
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	@Override
	public String toString(){
		return this.qtd + " | " + this.nome;
	}
}
