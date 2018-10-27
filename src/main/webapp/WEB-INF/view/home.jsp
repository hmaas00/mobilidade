<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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
				<li class="active"><a
					href="${pageContext.request.contextPath}/">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Mobilidade<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Candidatura</a></li>
						<security:authorize access="hasRole('DEPES')">
						<li><a href="#">Gerenciamento de componente</a></li>
						<li><a href="#">Gerenciamento de unidade</a></li>
						<li><a href="#">Gerenciamento da mobilidade</a></li>
						</security:authorize>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Permuta<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/todosOutrosSolicitantes">Visualizar todos os outros solicitantes de permuta</a></li>
						<li><a
							href="${pageContext.request.contextPath}/solicitar-permuta">Solicitação
								de permuta</a></li>
						<li><a
							href="${pageContext.request.contextPath}/opcoes-busca">Opções de busca de possibilidades</a></li>		
								
						<security:authorize access="hasRole('DEPES')">
							<li><a href="#">Gerenciamento da permuta</a></li>
						</security:authorize>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Usuário: <security:authentication property="principal.username" /></a></li>
				<li><a href="${pageContext.request.contextPath}/logout"><span
						class="glyphicon glyphicon-log-out"></span>logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="jumbotron">
			<h2 class="text-center">Bem vindo ao Sistema de RH</h2>
			<br>
			<p class="text-center">
				Escolha com qual modalidade você deseja interagir: a <strong>Mobilidade</strong>
				é o processo em que o candidado escolhe vagas disponibilizadas nessa
				etapa enquanto a <strong>Permuta</strong> está sempre disponível
				para participação.
			</p>
		</div>
		<!-- Add a logout button -->
		<!-- logout antigo
      	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
		<button type="submit" class="btn btn-default btn-xs">Logout</button>
	</form:form>
       -->

	</div>

</body>
</html>
