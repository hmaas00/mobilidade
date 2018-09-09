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
							href="${pageContext.request.contextPath}/solicitar-permuta">Solicitação
								de permuta</a></li>
						<security:authorize access="hasRole('DEPES')">
							<li><a href="#">Gerenciamento da permuta</a></li>
						</security:authorize>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Usuário: <security:authentication
							property="principal.username" /></a></li>
				<li><a href="${pageContext.request.contextPath}/logout"><span
						class="glyphicon glyphicon-log-out"></span>logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">

	</div>
	<c:choose>
		<c:when test="${tamanho == 0}">
			<div class="jumbotron">
				<p>Não houve resultados para a busca.</p>
			</div>
		</c:when>
		<c:otherwise>
			<div class="jumbotron">
				<p class="text-center">Foram encontrados ${tamanho}
					solicitante(s).</p>
			</div>
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Praça</th>
						<th>Unidade</th>
						<th>Processo de Trabalho</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pessoas}" var="p" varStatus="i">

						<tr>
							<td>${p.nomePessoa}</td>
							<td>${p.praca.nomePraca}</td>
							<td>${p.componenteAdministrativo.unidade.nomeUnidade}</td>
							<td>${p.cadeiaValorSubgrupo.descricaoSubgrupo}</td>
							<td>
								<form class="form-horizontal"
									action="${pageContext.request.contextPath}/">
									<div class="form-group">
										<input type="hidden" id="idPessoa" name="idPessoa" value="${p.idPessoa}"> 
										<button type="submit" class="btn btn-default">
											<span class="glyphicon glyphicon-info-sign"></span>
										</button>
									</div>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


		</c:otherwise>

	</c:choose>


</body>
</html>
