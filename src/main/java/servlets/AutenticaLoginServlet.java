package servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns={"/login/AutenticaLoginServlet"})
public class AutenticaLoginServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
    	throws ServletException, IOException {
		
		String conta = req.getParameter("conta");
		String senha = req.getParameter("senha");
	
		if ("conta".equals(conta) && "senha".equals(senha))	{
			/*String carrinho = "";
			Cookie umCookie = new Cookie("carrinho", URLEncoder.encode(carrinho, "UTF-8"));
			umCookie.setMaxAge(7*24*60*60);
			res.addCookie(umCookie);*/
			
			HttpSession sessao = req.getSession();
			Usuario usuario = new Usuario();
			usuario.setNome(conta);
			
			sessao.setAttribute("usuario", usuario);
			String destino = (String)sessao.getAttribute("destino");
			
			if (destino != null){
				res.sendRedirect(destino);
			} else {
				res.sendRedirect("/TrabalhoFinal/menu/VitrineServlet");
			}
		} else	{
			res.sendRedirect("/TrabalhoFinal/jsp/paginaDeLogin.jsp?erro=1");
		}
	}
}