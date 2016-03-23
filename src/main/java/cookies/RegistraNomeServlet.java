package cookies;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistraNomeServlet extends HttpServlet 
{	
	private static final long serialVersionUID = 1L;

	public void service (HttpServletRequest request,
                         HttpServletResponse response)
    	throws ServletException, IOException
	{	
		String nome = request.getParameter("nome");			
								
    	Cookie umCookie = new Cookie("Identificacao", URLEncoder.encode(nome, "UTF-8"));
    	umCookie.setMaxAge(7*24*60*60);      // Uma semana.  
    	response.addCookie(umCookie);        // Envia o Cookie
    	
    	request.setAttribute("nome", nome);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/jsp/exibeNome.jsp");
    	rd.forward(request, response);
 	}
}