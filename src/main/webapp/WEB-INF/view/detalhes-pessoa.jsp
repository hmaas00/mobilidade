<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Mobilidade home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Mobilidade de RH</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Mobilidade<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Candidatura</a></li>
						<li><a href="#">Gerenciamento de componente</a></li>
						<li><a href="#">Gerenciamento de unidade</a></li>
						<li><a href="#">Gerenciamento da mobilidade</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Permuta<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/todosOutrosSolicitantes">Visualizar
								todos os outros solicitantes de permuta</a></li>
						<li><a
							href="${pageContext.request.contextPath}/solicitar-permuta">Solicita��o
								de permuta</a></li>
								<li><a
							href="${pageContext.request.contextPath}/opcoes-busca">Op��es de busca de possibilidades</a></li>	
						<security:authorize access="hasRole('DEPES')">
							<li><a href="#">Gerenciamento da permuta</a></li>
							<li><a href="${pageContext.request.contextPath}/depes/motivos">Motivos das Solicita��es</a></li>
						</security:authorize>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Usu�rio: <security:authentication
							property="principal.username" /></a></li>
				<li><a href="${pageContext.request.contextPath}/logout"><span
						class="glyphicon glyphicon-log-out"></span>logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="jumbotron">
			<p class="text-center">Detalhes do solicitante:</p>
		</div>
		<table class="table table-condensed">
			<thead>

			</thead>
			<tbody>
				<tr>
					<td>Nome:</td>
					<td>${pessoa.nomePessoa}</td>
					<td></td>
				</tr>
				<tr>
					<td>Matr�cula:</td>
					<td>${pessoa.matricula }</td>
				</tr>
				<tr>
					<td>Cargo:</td>
					<td>${pessoa.cargo }</td>
				</tr>
				<tr>
					<td>Processo de trabalho na cadeia de valor:</td>
					<td>${pessoa.cadeiaValorSubgrupo.descricaoSubgrupo }</td>
				</tr>
				<tr>
					<td>Pra�a:</td>
					<td>${pessoa.praca.nomePraca }</td>
				</tr>
				<tr>
					<td>Componente Administrativo:</td>
					<td>${pessoa.componenteAdministrativo.nomeComponente }</td>
				</tr>
				<tr>
					<td>Unidade:</td>
					<td>${pessoa.componenteAdministrativo.unidade.nomeUnidade }</td>
				</tr>
			</tbody>
		</table>

		<hr>
		<h3 class="text-center">Solicita��es:</h3>
		<br>

		<table class="table table-condensed">
			<thead>

				<th></th>
				<th>Pra�a desejada</th>
				<th>Unidade desejada</th>
				<th>Processo de trabalho desejado</th>

			</thead>
			<tbody>
				<c:forEach items="${permutas}" var="per" varStatus="i">
					<tr>
						<td>Solicita��o ${i.index + 1 }:</td>

						<td>${per.praca.nomePraca}</td>
						<td>${per.unidade.nomeUnidade}</td>
						<td>${per.cadeiaValorSubgrupo.descricaoSubgrupo}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<br> <br>
		<hr>


	</div>
</body>
</html>
