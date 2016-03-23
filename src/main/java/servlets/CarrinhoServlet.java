package servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.ListaDeProdutos;
import classes.Produto;
/*
 * 
 * TODO: Implementar via Cookies
 * 
 */
@WebServlet(urlPatterns={"/carrinho/CarrinhoServlet"})
public class CarrinhoServlet extends HttpServlet{
	private final static long serialVersionUID = 1;
	
	@Override
	public void doGet (HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		Cookie carCookie = null;
		String proxima;
		ListaDeProdutos carrinho;
		
		if (cookies != null){	
			for(int i=0; i < cookies.length; i++){	
				if(cookies[i].getName().equals("Carrinho")){	
					carCookie = cookies[i];
					break;
				}
			}
		}
		
		if(carCookie != null){
			//Recuperar lista do cookie
			String tempCarrinho = URLDecoder.decode(carCookie.getValue(), "UTF-8"),
					nome, quantidade, id;
			String[] produtos = tempCarrinho.split("_");
			String[] infos;
			int tamanho = Integer.parseInt(produtos[0]);
			carrinho = new ListaDeProdutos(); 
			
			for(int i = 1; i < tamanho; i++){
				infos = produtos[i].split("-");
				nome = infos[0];
				quantidade = infos[1];
				id = infos[2];
				carrinho.adicionar(nome, Integer.parseInt(quantidade), id);
			}
			
		} else {
			//Utilizar objeto sessão
			HttpSession sessao = request.getSession();
			carrinho = (ListaDeProdutos) sessao.getAttribute("carrinho");
			
			if (carrinho == null){	
	        	carrinho = new ListaDeProdutos();
	        	
	        	sessao.setAttribute("carrinho", carrinho);
	        }
		}
		
		String produto = request.getParameter("prod");
		String cookieProd = "";
		Produto atual;
		proxima = "../menu/VitrineServlet";
		
		if(produto != null){
			carrinho.adicionar("prod" + produto, 1, produto);
		} else{
			proxima = "../jsp/carrinho.jsp";
			produto = request.getParameter("dp");
			if(produto != null){
				carrinho.remover("prod" + produto);
			}
			
			//Caminho via Cookies 
			cookieProd += carrinho.tam();
			for(int i = 0; i < carrinho.tam(); i++){
				atual = carrinho.obter(i);
				cookieProd += "_";
				cookieProd += atual.getNome() + "-";
				cookieProd += atual.getQtd() + "-";
				cookieProd += atual.getId();
			}
			carCookie = new Cookie("Carrinho", URLEncoder.encode(cookieProd, "UTF-8"));
			carCookie.setMaxAge(7*24*60*60);
			response.addCookie(carCookie);    
			/*Caminho para ambos*/
			
			request.setAttribute("carrinho", carrinho.obterLista());	
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(proxima);
		rd.forward(request, response);
	}
}