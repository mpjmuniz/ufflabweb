package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.ListaDeProdutos;

//Passa direto pelo filtro
@WebServlet(urlPatterns={"/menu/VitrineServlet"})
public class VitrineServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static ListaDeProdutos estoque;
	
	static {
		estoque = new ListaDeProdutos();
		
		/*
		 *  Poderia usar um arquivo para os produtos, para assim controlar suas quantidades.
		 *  Mas visto que na indústria se utilizam bancos de dados, a opçõa por ter as quantidades
		 *  estáticas foi uma decisão de projeto. 
		 */
		
		for(int i = 0; i < 100; i++){
			estoque.adicionar("prod " + i, 10, Integer.toString(i));
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
    	throws ServletException, IOException {	
		req.setAttribute("estoque", estoque.obterLista());
		
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/vitrine.jsp");
		rd.forward(req, res);
	}
}
