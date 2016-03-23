package cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveRegistroServlet extends HttpServlet 
{	
	private static final long serialVersionUID = 1L;

	public void doGet (HttpServletRequest request,
                       HttpServletResponse response)
    	throws ServletException, IOException
	{	
		response.setContentType("text/html; charset=ISO-8859-1");
		PrintWriter out = response.getWriter();  

		out.println( "<!DOCTYPE html>" );
		out.println( "<html>" );
    	out.println( "<head>" );
    	out.println( "<title>Removendo o Registro</title>" );
    	out.println( "</head>" );
    	out.println( "<body>" );

		// O método getCookies() retorna um array de objetos cookie. 
		Cookie[] cookies = request.getCookies();
		Cookie umCookie = null;

		// Não há um método na API Servlet que retorne um cookie pelo seu nome.
		if (cookies != null)
		{	for(int i=0; i < cookies.length; i++) 
			{	if (cookies[i].getName().equals("Identificacao"))
				{	umCookie = cookies[i];
					break;
				}
			}
		}

        if (umCookie != null)
    	{	
        	umCookie.setMaxAge(0);          // Determinando que o cookie deve expirar imediatamente
        	response.addCookie(umCookie);   // Enviando o cookie
        	out.println("Registro removido com sucesso.<br/><br/>");
        }
        else
		{	out.println("Nenhum registro foi encontrado.<br/><br/>");
		}

        out.println("<a href='/servlet-05/exercicio/Menu.html'>Menu Principal</a>");
    	out.println( "</body>" );		
    	out.println( "</html>" );		
  	}
}