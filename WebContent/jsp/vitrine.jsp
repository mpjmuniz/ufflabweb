<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=ISO-8859-1">
		<title>Vitrine de Produtos</title>
	</head>
	<body>
		<a href='<c:url value="/carrinho/CarrinhoServlet"/>'> Veja seu carrinho </a>
		
		<h3><i>Vitrine de Produtos</i></h3>
		
		<h4> Produto | Quantidade</h4> 
		<c:forEach items="${requestScope.estoque}" var="prod">
			<a href='<c:url value="/carrinho/CarrinhoServlet?prod=${prod.id}"/>' >${prod.nome} | ${prod.qtd}</a>
		 	<br>
		</c:forEach>
	</body>
</html>