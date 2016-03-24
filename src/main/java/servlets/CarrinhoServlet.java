package servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

@WebServlet(urlPatterns={"/carrinho/CarrinhoServlet"})
public class CarrinhoServlet extends HttpServlet{
	private final static long serialVersionUID = 1;
	
	@Override
	public void doGet (HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		Cookie carCookie = obterCookie(request, "Carrinho"), finalCookie;
		String proxima;
		ListaDeProdutos carrinho = null;
		boolean aceitaCookies = false;

		if(carCookie != null){
			//Recuperar lista do cookie
			aceitaCookies = true;
			carrinho = parseCarrinho(carCookie.getValue());
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
		proxima = "../menu/VitrineServlet";
		
		if(produto != null){
			carrinho.adicionar("prod" + produto, 1, produto);
		} else{
			
			proxima = "../jsp/carrinho.jsp";
			produto = request.getParameter("dp");
			if(produto != null){
				carrinho.remover("prod" + produto);
			}	
		}
		
		if(aceitaCookies){
			finalCookie = new Cookie("Carrinho", encodeCarrinho(carrinho));
			finalCookie.setMaxAge(7*24*60*60);
			response.addCookie(finalCookie);
		}else {
			request.setAttribute("carrinho", carrinho.obterLista());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(proxima);
		rd.forward(request, response);
	}
	
	private ListaDeProdutos parseCarrinho(String fonte){
		try {
			String tempCarrinho = URLDecoder.decode(fonte, "UTF-8"),
					nome, quantidade, id;
			
			String[] produtos = tempCarrinho.split("_");
			String[] infos;
			int tamanho = Integer.parseInt(produtos[0]);
			ListaDeProdutos carrinho = new ListaDeProdutos(); 
			
			for(int i = 1; i < tamanho; i++){
				infos = produtos[i].split("-");
				nome = infos[0];
				quantidade = infos[1];
				id = infos[2];
				carrinho.adicionar(nome, Integer.parseInt(quantidade), id);
			}
			return carrinho;
		} catch (UnsupportedEncodingException e) {
			return null;
		}		
		
	}
	
	private Cookie obterCookie(HttpServletRequest request, String nome){
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null){	
			for(int i = 0; i < cookies.length; i++){	
				if(cookies[i].getName().equals(nome)){	
					return cookies[i];
				}
			}
		}
		return null;
	}
	
	private String encodeCarrinho(ListaDeProdutos carrinho) throws UnsupportedEncodingException{
		String cookieProd = "";
		Produto atual;
		//Caminho via Cookies 
		cookieProd += carrinho.tam();
		for(int i = 0; i < carrinho.tam(); i++){
			atual = carrinho.obter(i);
			cookieProd += "_";
			cookieProd += atual.getNome() + "-";
			cookieProd += atual.getQtd() + "-";
			cookieProd += atual.getId();
		}
		
		return URLEncoder.encode(cookieProd, "UTF-8");
	}
}