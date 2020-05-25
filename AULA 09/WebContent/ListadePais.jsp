<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ListadePais</title>
</head>
<body>
	<c:forEach var="cliente" items="${lista}">
${cliente.id}
${cliente.pais}
${cliente.populacao}
${cliente.area}
	</c:forEach>	
</body>
</html>