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
						<security:authorize access="hasRole('ADMIN')">
							<li><a href="#">Gerenciamento da permuta</a></li>
						</security:authorize>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/logout"><span
						class="glyphicon glyphicon-log-out"></span>logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="jumbotron">
			<h2 class="text-center">Solicitação de Permuta</h2>
			<br>
			<p class="text-center">
				Escolha até 3 opções de <strong>Permuta:</strong>
			</p>
		</div>
		<form class="form-horizontal" action="/action_page.php">
			<div class="form-group">
				<label class="control-label col-sm-3" for="matricula">Matrícula:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="matricula"
						placeholder="" name="matricula" value="${pessoa.matricula }" disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="nome">Nome:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="nome" placeholder=""
						name="nome" value="${pessoa.nomePessoa }" disabled >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="cargo">Cargo:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="cargo" placeholder=""
						name="cargo" value="${pessoa.cargo }" disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="unidade">Unidade:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="unidade" placeholder=""
						name="unidade" value="${pessoa.componenteAdministrativo.unidade.nomeUnidade }" disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="componente">Componente
					Administrativo:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="componente"
						placeholder="" name="componente" value="${pessoa.componenteAdministrativo.nomeComponente }" disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="praca">Praça:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="praca" placeholder=""
						name="praca" value="${pessoa.praca.nomePraca }" disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="processo">Processo de Trabalho Atual:</label>
				<div class="col-sm-6">
					<select class="form-control" id="processo" name="processo">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="motivo">Motivo Principal:</label>
				<div class="col-sm-6">
					<select class="form-control" id="motivo" name="motivo">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
					</select>
				</div>
			</div>
		</form>

	</div>

</body>
</html>
