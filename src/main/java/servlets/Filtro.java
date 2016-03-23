package servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/carrinho/*" })
public class Filtro implements Filter{	
	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) {	
		this.filterConfig = filterConfig;		
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession sessao = request.getSession();
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuario");
		
		if (usuario != null) {
			chain.doFilter(req, res);
		} else {
			String destino = request.getRequestURI() + "?" + request.getQueryString();
			sessao.setAttribute("destino", destino);
			response.sendRedirect(response.encodeRedirectURL("/TrabalhoFinal/jsp/paginaDeLogin.jsp"));
		}
	}

	public void destroy()	{	
	}
}