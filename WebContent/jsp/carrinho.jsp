<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=ISO-8859-1">
		<title>Seu Carrinho</title>
	</head>
	<body>
		<a href='<c:url value="/menu/VitrineServlet"/>'> Voltar para a Vitrine </a>
		
		<h3><i>Seu Carrinho</i></h3>
		
		<h4> Atenção: Clicando nos produtos resultará na remoção deles!</h4>
		<c:forEach items="${requestScope.carrinho}" var="produto">
        	<a href="<c:url value='/carrinho/CarrinhoServlet?dp=${produto.id}'/>">
        		${produto.nome} | ${produto.qtd}
        	</a>
        	<br><br>
		</c:forEach>
	</body>
</html>