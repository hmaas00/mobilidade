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

<script type="text/javascript">

	$( function(){
		
		$( document ).ready(function() {
		    //alert( "ready!" );
		    if ($('#pracaEscolhida1').val() == 0) {
				$("#unidadeEscolhida1").prop('disabled', true);
				$("#processoEscolhido1").prop('disabled', true);
			}
		    if ($('#pracaEscolhida2').val() == 0) {
				$("#unidadeEscolhida2").prop('disabled', true);
				$("#processoEscolhido2").prop('disabled', true);
			}
		    if ($('#pracaEscolhida3').val() == 0) {
				$("#unidadeEscolhida3").prop('disabled', true);
				$("#processoEscolhido3").prop('disabled', true);
			}
		});
		
		$("#btn-limpar").click(function(){
			$("#pracaEscolhida1").val(0);
			$("#pracaEscolhida2").val(0);
			$("#pracaEscolhida3").val(0);
			
			$("#unidadeEscolhida1").val(0);
			$("#unidadeEscolhida1").prop('disabled', true);
			$("#unidadeEscolhida2").val(0);
			$("#unidadeEscolhida2").prop('disabled', true);
			$("#unidadeEscolhida3").val(0);
			$("#unidadeEscolhida3").prop('disabled', true);
			
			$("#processoEscolhido1").val(0);
			$("#processoEscolhido1").prop('disabled', true);
			$("#processoEscolhido2").val(0);
			$("#processoEscolhido2").prop('disabled', true);
			$("#processoEscolhido3").val(0);
			$("#processoEscolhido3").prop('disabled', true);
		});
	
		$("#pracaEscolhida1").change(function() {
			if ($('#pracaEscolhida1').val() == 0) {
				$("#unidadeEscolhida1").prop('disabled', true);
				$("#unidadeEscolhida1").val(0);
				$("#processoEscolhido1").prop('disabled', true);
				$("#processoEscolhido1").val(0);
			}
			else{
				$("#unidadeEscolhida1").prop('disabled', false);
				
				$("#processoEscolhido1").prop('disabled', false);
				
			}
		});
		
		
		$("#pracaEscolhida2").change(function() {
			if ($('#pracaEscolhida2').val() == 0) {
				$("#unidadeEscolhida2").prop('disabled', true);
				$("#unidadeEscolhida2").val(0);
				$("#processoEscolhido2").prop('disabled', true);
				$("#processoEscolhido2").val(0);
			}
			else{
				$("#unidadeEscolhida2").prop('disabled', false);
				
				$("#processoEscolhido2").prop('disabled', false);
				
			}
		});
		
		
		$("#pracaEscolhida3").change(function() {
			if ($('#pracaEscolhida3').val() == 0) {
				$("#unidadeEscolhida3").prop('disabled', true);
				$("#unidadeEscolhida3").val(0);
				$("#processoEscolhido3").prop('disabled', true);
				$("#processoEscolhido3").val(0);
			}
			else{
				$("#unidadeEscolhida3").prop('disabled', false);
				
				$("#processoEscolhido3").prop('disabled', false);
				
			}
		});
		
		
		
		
		
		
		
		
		
		
	});

	

</script>
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
							href="${pageContext.request.contextPath}/todosOutrosSolicitantes">Visualizar todos os outros solicitantes de permuta</a></li>
						<li><a
							href="${pageContext.request.contextPath}/solicitar-permuta">Solicitação
								de permuta</a></li>
								<li><a
							href="${pageContext.request.contextPath}/opcoes-busca">Opções de busca de possibilidades</a></li>	
						<security:authorize access="hasRole('DEPES')">
							<li><a href="#">Gerenciamento da permuta</a></li>
							<li><a href="${pageContext.request.contextPath}/depes/motivos">Motivos das Solicitações</a></li>
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
		<div class="jumbotron">
			<h2 class="text-center">Solicitação de Permuta</h2>
			<br>
			<p class="text-center">
				Escolha até 3 opções de <strong>Permuta:</strong>
			</p>
			<p class="text-center">
				O motivo principal do seu pedido será visível <strong>somente</strong>
				pelo <strong>DEPES</strong>
			</p>
			<p class="text-center">
				Lembre que para uma solicitação ser <strong>válida</strong> é
				preciso que seja informada a <strong>Praça</strong> desejada. (
				unidade e processo de trabalho são opcionais)
			</p>
		</div>
		<form class="form-horizontal"
			action="${pageContext.request.contextPath}/processa-solicitacao-permuta">
			<div class="form-group">
				<label class="control-label col-sm-3" for="matricula">Matrícula:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="matricula"
						placeholder="" name="matricula" value="${pessoa.matricula }"
						disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="nome">Nome:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="nome" placeholder=""
						name="nome" value="${pessoa.nomePessoa }" disabled>
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
						name="unidade"
						value="${pessoa.componenteAdministrativo.unidade.nomeUnidade }"
						disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="componente">Componente
					Administrativo:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="componente"
						placeholder="" name="componente"
						value="${pessoa.componenteAdministrativo.nomeComponente }"
						disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="praca">Praça:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="praca" placeholder=""
						name="praca" value="${pessoa.praca.nomePraca }" disabled>
				</div>
			</div>
			<!-- Processo de trabalho -->
			<div class="form-group">
				<label class="control-label col-sm-3" for="processo">Processo
					de Trabalho Atual:</label>
				<div class="col-sm-6">
					<select class="form-control" id="processo" name="processo">
						<c:forEach items="${cadeiasValor}" var="processoTrabalho">
							<c:choose>

								<c:when
									test="${ cadeiaValorPreviamenteEscolhida == processoTrabalho.descricaoSubgrupo}">
									<option value="${processoTrabalho.idCadeiaValorSubgrupo}"
										selected>${processoTrabalho.descricaoSubgrupo}</option>
								</c:when>
								<c:otherwise>
									<option value="${processoTrabalho.idCadeiaValorSubgrupo}">${processoTrabalho.descricaoSubgrupo}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select>
				</div>
			</div>
			<!-- Motivo Principal -->
			<div class="form-group">
				<label class="control-label col-sm-3" for="motivo">Motivo
					Principal:</label>
				<div class="col-sm-6">
					<select class="form-control" id="motivo" name="motivo">
						<c:forEach items="${motivos}" var="m" varStatus="loop">
							<c:choose>
								<c:when test="${ motivoPreviamenteEscolhido == m}">
									<option value="${loop.index}" selected>${m}</option>
								</c:when>
								<c:otherwise>
									<option value="${loop.index}">${m}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<!-- Solicitações de permuta -->
			<br>
			<p class="text-center">
				<strong>Solicitações de Permuta:</strong>
			</p>
			<hr>
			<!-- Header solicitações-->
			<div class="row">
				<div class="col-md-3">
					<p class="text-center">
						<strong>Praça:</strong>
					</p>
				</div>
				<div class="col-md-3">
					<p class="text-center">
						<strong>Unidade:</strong>
					</p>
				</div>
				<div class="col-md-6">

					<p class="text-center">
						<strong>Processo de Trabalho:</strong>
					</p>
				</div>
			</div>
			<!-- Fim Header solicitações-->
			<hr>
			<!-- Linha 1 -->
			<div class="row">
				<p class="text-left">
					<strong>Solicitação 1</strong>
				</p>
				<br>

				<!-- Linha 1 - Coluna 1 -->
				<div class="col-md-3">

					<!-- Praça -->
					<div class="form-group">

						<div class="col-md-10">
							<select class="form-control" id="pracaEscolhida1"
								name="pracaEscolhida1">


								<!-- Caso exista uma solicitação... -->
								<c:choose>
									<c:when test="${totalSolicitacoes >= 1}">
										<option value="0"></option>
										<c:forEach items="${pracas}" var="p">
											<c:choose>
												<c:when
													test="${ p.nomePraca == solicitacoesFeitas[0].praca.nomePraca }">
													<option value="${p.idPraca}" selected>${p.nomePraca}</option>
												</c:when>
												<c:otherwise>
													<option value="${p.idPraca}">${p.nomePraca}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="0"></option>
										<c:forEach items="${pracas}" var="p">
											<option value="${p.idPraca}">${p.nomePraca}</option>
										</c:forEach>

									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
				<!-- Linha 1 - Coluna 2 -->
				<div class="col-md-3">
					<!-- Unidade -->
					<div class="form-group">

						<div class="col-md-10">
							<select class="form-control" id="unidadeEscolhida1"
								name="unidadeEscolhida1">

								<!-- Caso exista uma solicitação... -->
								<c:choose>
									<c:when test="${totalSolicitacoes >= 1}">
										<option value="0"></option>
										<c:forEach items="${unidades}" var="u">
											<c:choose>
												<c:when
													test="${ u.nomeUnidade == solicitacoesFeitas[0].unidade.nomeUnidade }">
													<option value="${u.idUnidade}" selected>${u.nomeUnidade}</option>
												</c:when>
												<c:otherwise>
													<option value="${u.idUnidade}">${u.nomeUnidade}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="0"></option>
										<c:forEach items="${unidades}" var="u">
											<option value="${u.idUnidade}">${u.nomeUnidade}</option>
										</c:forEach>

									</c:otherwise>
								</c:choose>

							</select>
						</div>
					</div>
				</div>
				<!-- Linha 1 - Coluna 3 -->
				<div class="col-md-6">
					<!-- Processo de trabalho -->
					<div class="form-group">

						<div class="col-md-10">
							<select class="form-control" id="processoEscolhido1"
								name="processoEscolhido1">


								<!-- Caso exista uma solicitação... -->
								<c:choose>
									<c:when test="${totalSolicitacoes >= 1}">
										<option value="0"></option>
										<c:forEach items="${cadeiasValor}" var="pt">
											<c:choose>
												<c:when
													test="${ pt.descricaoSubgrupo == solicitacoesFeitas[0].cadeiaValorSubgrupo.descricaoSubgrupo }">
													<option value="${pt.idCadeiaValorSubgrupo}" selected>${pt.descricaoSubgrupo}</option>
												</c:when>
												<c:otherwise>
													<option value="${pt.idCadeiaValorSubgrupo}">${pt.descricaoSubgrupo}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="0"></option>
										<c:forEach items="${cadeiasValor}" var="pt">
											<option value="${pt.idCadeiaValorSubgrupo}">${pt.descricaoSubgrupo}</option>
										</c:forEach>

									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</div>
			<!-- Fim Linha 1 -->
			<hr />
			<!-- Linha 2 -->
			<div class="row">
				<p class="text-left">
					<strong>Solicitação 2</strong>
				</p>
				<br>

				<!-- Linha 2 - Coluna 1 -->
				<div class="col-md-3">

					<!-- Praça -->
					<div class="form-group">

						<div class="col-md-10">
							<select class="form-control" id="pracaEscolhida2"
								name="pracaEscolhida2">

								<!-- Caso existam duas ou mais solicitações... -->
								<c:choose>
									<c:when test="${totalSolicitacoes >= 2}">
										<option value="0"></option>
										<c:forEach items="${pracas}" var="p">
											<c:choose>
												<c:when
													test="${ p.nomePraca == solicitacoesFeitas[1].praca.nomePraca }">
													<option value="${p.idPraca}" selected>${p.nomePraca}</option>
												</c:when>
												<c:otherwise>
													<option value="${p.idPraca}">${p.nomePraca}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="0"></option>
										<c:forEach items="${pracas}" var="p">
											<option value="${p.idPraca}">${p.nomePraca}</option>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
				<!-- Linha 2 - Coluna 2 -->
				<div class="col-md-3">

					<!-- Unidade -->
					<div class="form-group">

						<div class="col-md-10">
							<select class="form-control" id="unidadeEscolhida2"
								name="unidadeEscolhida2">

								<!-- Caso existam duas ou mais solicitações... -->
								<c:choose>
									<c:when test="${totalSolicitacoes >= 2}">
										<option value="0"></option>
										<c:forEach items="${unidades}" var="u">
											<c:choose>
												<c:when
													test="${ u.nomeUnidade == solicitacoesFeitas[1].unidade.nomeUnidade }">
													<option value="${u.idUnidade}" selected>${u.nomeUnidade}</option>
												</c:when>
												<c:otherwise>
													<option value="${u.idUnidade}">${u.nomeUnidade}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="0"></option>
										<c:forEach items="${unidades}" var="u">
											<option value="${u.idUnidade}">${u.nomeUnidade}</option>
										</c:forEach>

									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
				<!-- Linha 2 - Coluna 3 -->
				<div class="col-md-6">

					<!-- Processo de trabalho -->
					<div class="form-group">

						<div class="col-md-10">
							<select class="form-control" id="processoEscolhido2"
								name="processoEscolhido2">

								<!-- Caso existam duas ou mais  solicitações... -->
								<c:choose>
									<c:when test="${totalSolicitacoes >= 2}">
										<option value="0"></option>
										<c:forEach items="${cadeiasValor}" var="pt">
											<c:choose>
												<c:when
													test="${ pt.descricaoSubgrupo == solicitacoesFeitas[1].cadeiaValorSubgrupo.descricaoSubgrupo }">
													<option value="${pt.idCadeiaValorSubgrupo}" selected>${pt.descricaoSubgrupo}</option>
												</c:when>
												<c:otherwise>
													<option value="${pt.idCadeiaValorSubgrupo}">${pt.descricaoSubgrupo}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="0"></option>
										<c:forEach items="${cadeiasValor}" var="pt">
											<option value="${pt.idCadeiaValorSubgrupo}">${pt.descricaoSubgrupo}</option>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</div>
			<!-- Fim Linha 2 -->
			<hr />
			<!-- Linha 3 -->
			<div class="row">
				<p class="text-left">
					<strong>Solicitação 3</strong>
				</p>
				<br>

				<!-- Linha 3 - Coluna 1 -->
				<div class="col-md-3">

					<!-- Praça -->
					<div class="form-group">

						<div class="col-md-10">
							<select class="form-control" id="pracaEscolhida3"
								name="pracaEscolhida3">

								<!-- Caso existam 3 ou mais solicitações... -->
								<c:choose>
									<c:when test="${totalSolicitacoes >= 3}">
										<option value="0"></option>
										<c:forEach items="${pracas}" var="p">
											<c:choose>
												<c:when
													test="${ p.nomePraca == solicitacoesFeitas[2].praca.nomePraca }">
													<option value="${p.idPraca}" selected>${p.nomePraca}</option>
												</c:when>
												<c:otherwise>
													<option value="${p.idPraca}">${p.nomePraca}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="0"></option>
										<c:forEach items="${pracas}" var="p">
											<option value="${p.idPraca}">${p.nomePraca}</option>
										</c:forEach>
									</c:otherwise>
								</c:choose>


							</select>
						</div>
					</div>
				</div>
				<!-- Linha 3 - Coluna 2 -->
				<div class="col-md-3">
					<!-- Unidade -->
					<div class="form-group">

						<div class="col-md-10">
							<select class="form-control" id="unidadeEscolhida3"
								name="unidadeEscolhida3">

								<!-- Caso existam 3 ou mais solicitações... -->
								<c:choose>
									<c:when test="${totalSolicitacoes >= 3}">
										<option value="0"></option>
										<c:forEach items="${unidades}" var="u">
											<c:choose>
												<c:when
													test="${ u.nomeUnidade == solicitacoesFeitas[2].unidade.nomeUnidade }">
													<option value="${u.idUnidade}" selected>${u.nomeUnidade}</option>
												</c:when>
												<c:otherwise>
													<option value="${u.idUnidade}">${u.nomeUnidade}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="0"></option>
										<c:forEach items="${unidades}" var="u">
											<option value="${u.idUnidade}">${u.nomeUnidade}</option>
										</c:forEach>

									</c:otherwise>
								</c:choose>


							</select>
						</div>
					</div>
				</div>
				<!-- Linha 3 - Coluna 3 -->
				<div class="col-md-6">
					<!-- Processo de trabalho -->
					<div class="form-group">

						<div class="col-md-10">
							<select class="form-control" id="processoEscolhido3"
								name="processoEscolhido3">

								<!-- Caso existam duas ou mais  solicitações... -->
								<c:choose>
									<c:when test="${totalSolicitacoes >= 3}">
										<option value="0"></option>
										<c:forEach items="${cadeiasValor}" var="pt">
											<c:choose>
												<c:when
													test="${ pt.descricaoSubgrupo == solicitacoesFeitas[2].cadeiaValorSubgrupo.descricaoSubgrupo }">
													<option value="${pt.idCadeiaValorSubgrupo}" selected>${pt.descricaoSubgrupo}</option>
												</c:when>
												<c:otherwise>
													<option value="${pt.idCadeiaValorSubgrupo}">${pt.descricaoSubgrupo}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="0"></option>
										<c:forEach items="${cadeiasValor}" var="pt">
											<option value="${pt.idCadeiaValorSubgrupo}">${pt.descricaoSubgrupo}</option>
										</c:forEach>
									</c:otherwise>
								</c:choose>

							</select>
						</div>
					</div>
				</div>
			</div>
			<!-- Fim Linha 3 -->
			<hr />
			<div class="text-center">
				
				<input type="button" class="btn btn-default" value="limpar solicitações" id="btn-limpar">
				<input type="submit" class="btn btn-primary" value="Enviar">
			</div>
			<hr />
		</form>
	</div>
</body>
</html>
