package cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecuperaNomeServlet extends HttpServlet 
{	
	private static final long serialVersionUID = 1L;

	public void doGet (HttpServletRequest request,
                       HttpServletResponse response)
    	throws ServletException, IOException
	{	
		// O método getCookies() retorna um array de objetos cookie. 
		Cookie[] cookies = request.getCookies();
		Cookie umCookie = null;

		// Não há um método na API Servlet que retorne um cookie pelo seu nome.
		if (cookies != null)
		{	for(int i=0; i < cookies.length; i++) 
			{	if(cookies[i].getName().equals("Identificacao"))
				{	umCookie = cookies[i];
					break;
				}
			}
		}
		
		response.setContentType( "text/html; charset=ISO-8859-1" );
		PrintWriter out = response.getWriter();  

		out.println( "<!DOCTYPE html>" );
		out.println( "<html>" );
    	out.println( "<head>" );
    	out.println( "<title>Recuperando Cookies</title>" );
    	out.println( "</head>" );
    	out.println( "<body>" );
    	if (umCookie != null)
		{	
    		String nome = URLDecoder.decode(umCookie.getValue(), "UTF-8"); 
				
    		out.println("<b>Oi " + nome + ". É um prazer tê-lo de volta.</b><br/><br/>"); 
    		out.println("Se você não é " + nome + " clique " + 
    			"<a href='/servlet-05/exercicio/Identificacao.html'>aqui</a>.<br/><br/>");

		    out.println("Para não ser mais reconhecido pelo site clique " + 
		    	"<a href='/servlet-05/exercicio/RemoveRegistroServlet'>aqui</a>.<br/><br/>");
		}
		else
    	{	out.println("Como você ainda não está registrado, por favor clique " + 
    			"<a href='/servlet-05/exercicio/Identificacao.html'>aqui</a> para se registrar.<br/><br/>");
    	}	

    	out.println("Bla, Bla, Bla...<br/><br/>"); 
    	out.println("<a href='/servlet-05/exercicio/Menu.html'>Menu Principal</a>");
    		 
    	out.println( "</body>" );		
    	out.println( "</html>" );		
  	}
}