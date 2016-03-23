package classes;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class ListaDeProdutos extends ArrayList {
	private final static long serialVersionUID = 2;
	private ArrayList<Produto> prods;
	
	public ListaDeProdutos(){
		this.prods = new ArrayList<Produto>();
	}
	
	public void adicionar(String nome, int qtd, String id){
		Produto prod = this.obter(nome);
		if(prod == null){
			this.prods.add(new Produto(nome, qtd, id));
		} else {
			prod.setQtd(prod.getQtd() + qtd);
		}
	}
	
	public void remover(String nome){
		Produto removido = obter(nome);
		
		if(removido.qtd == 1){
			int prod = procurarIndice(nome);
		 	if(prod != -1){
		 		prods.remove(prod);
		 	} else {
		 		throw new ArrayIndexOutOfBoundsException("indice invalido");
		 	}
		} else {
			removido.qtd--;
		}
	}
	
	public Produto obter(String nome){
		if(nome.equals("") || nome == null){
			throw new IllegalArgumentException("Nome inválido.");
		}
		for(Produto prod : prods){
			if(prod.nome.equals(nome)) return prod;
		}
		return null;
	}
	
	public Produto obter(int i){
		if(i < 0 || i > this.prods.size()){
			throw new IllegalArgumentException("Chave inválida.");
		}
		return prods.get(i);
	}
	
	public int procurarIndice(String nome){
		if(nome.equals("") || nome == null){
			throw new IllegalArgumentException("Nome inválido.");
		}
		for(Produto prod : prods){
			if(prod.nome.equals(nome)) return prods.indexOf(prod);
		}
		return -1;
	}
	
	public int tam(){
		return prods.size();
	}
	
	//Necess�rio pra ser iterado no vitrine.jsp
	public ArrayList<Produto> obterLista(){
		return this.prods;
	}
}
