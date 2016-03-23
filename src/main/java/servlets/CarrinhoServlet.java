package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.ListaDeProdutos;
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
		
		HttpSession sessao = request.getSession();
		ListaDeProdutos carrinho = (ListaDeProdutos) sessao.getAttribute("carrinho");
		
		if (carrinho == null){	
        	carrinho = new ListaDeProdutos();
        	
        	sessao.setAttribute("carrinho", carrinho);
        }
		
		String produto = request.getParameter("prod");
		String proxima = "../menu/VitrineServlet";
		
		if(produto != null){
			carrinho.adicionar("prod " + produto, 1, produto);
		} else{
			proxima = "../jsp/carrinho.jsp";
			produto = request.getParameter("dp");
			if(produto != null){
				carrinho.remover("prod " + produto);
			}
			request.setAttribute("carrinho", carrinho.obterLista());	
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(proxima);
		rd.forward(request, response);
	}
}