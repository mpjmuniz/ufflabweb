<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Página de Login</title>
   </head>
   <body>
    	<b>Página de Login</b><br/><br/>
    	
		<c:choose>
		    <c:when test="${not empty param.erro}">
				<font color="red">
					Login Inválido!
				</font>
				<br/><br/>			
		    </c:when>
		    <c:otherwise>

		    </c:otherwise>
		</c:choose>

    	<form method="GET" action='<c:url value='/login/AutenticaLoginServlet'/>'>
    		Conta: <input type='text' name='conta' value=""/><br/>
    		Senha: <input type='password' name='senha' value=""/><br/>
    		<input type='submit' value="Enviar" /><br/>
    	</form>
    	<a href='<c:url value="/index.html"/>'>Vitrine</a><br/>
   </body>
</html>
