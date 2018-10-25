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
						<security:authorize access="hasRole('DEPES')">
							<li><a href="#">Gerenciamento da permuta</a></li>
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

			<!-- Nenhuma solicita��o... -->
			<c:choose>

				<c:when test="${quantidade == 0}">
					<p class="text-center">Voc� n�o cadastrou nenhuma solicita��o
						de permuta.</p>
					<p class="text-center">
						A qualquer momento voc� pode castrar suas solicita��es nessa <a
							href="${pageContext.request.contextPath}/solicitar-permuta">p�gina.</a>
					</li>
					</p>

				</c:when>


				<c:when test="${quantidade == 1}">
					<p class="text-center">
						Voc� cadastrou <strong>uma</strong> solicita��o de permuta:
					</p>
					<hr>
					<p class="text-center">
						Voc� informou que seu processo de trabalho <strong>atual</strong>
						na cadeia de valor �: <strong>${processo}.</strong>
					</p>
					<p class="text-center">Outros solicitantes que desejem realizar
						esse processo na cadeia de valor poder�o encontr�-lo.</p>
					<p class="text-center">
						Seu <strong>motivo principal</strong> para tentar uma permuta foi:
						<strong>"${motivo}".</strong>
					</p>
					<p class="text-center">
						Essa informa��o ser� vis�vel <strong>somente pelo DEPES</strong> e
						ser� usada para estudos das movimenta��es internas.
					</p>
					<br>
					<p>
						<strong>Pra�a desejada:</strong> ${permutas[0].praca.nomePraca }
					</p>
					<c:choose>
						<c:when test="${empty permutas[0].unidade.nomeUnidade }">
							<p>
								<strong>Unidade desejada:</strong> N�o informada.
							</p>
						</c:when>
						<c:otherwise>
							<p>
								<strong>Unidade desejada:</strong>
								${permutas[0].unidade.nomeUnidade }
							</p>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when
							test="${empty permutas[0].cadeiaValorSubgrupo.descricaoSubgrupo }">
							<p>
								<strong>Processo de trabalho desejado:</strong> N�o informado.
							</p>
						</c:when>
						<c:otherwise>
							<p>
								<strong>Processo de trabalho desejado:</strong>
								${permutas[0].cadeiaValorSubgrupo.descricaoSubgrupo}
							</p>
						</c:otherwise>
					</c:choose>
					<c:if
						test="${ empty permutas[0].unidade.nomeUnidade or empty permutas[0].cadeiaValorSubgrupo.descricaoSubgrupo }">
						<p>Quanto maiores as informa��es sobre o posto que voc�
							deseja, maior ser� a chance do sistema encontrar uma rela��o
							circular significativa envolvendo voc� e maior ser� a
							reciprocidade que os outros solicitantes ir�o ver em voc�.</p>

					</c:if>

					<c:if
						test="${ empty permutas[0].unidade.nomeUnidade and empty permutas[0].cadeiaValorSubgrupo.descricaoSubgrupo }">
						<p>Para esta solicita��o voc� definiu apenas a pra�a desejada,
							dessa forma, s� � poss�vel lhe mostrar a lista de pessoas que
							desejam permutar e est�o atualmente nesta pra�a e desejam vir
							para a sua. Esta solicita��o ser� ignorada em buscas circulares.</p>


						<form class="form-horizontal"
							action="${pageContext.request.contextPath}/solicitantesDeUmaPracaRelevante">
							<div class="form-group">
								<input type="hidden" id="pracaAtual" name="pracaAtual"
									value="${pracaAtualId}"> <input type="hidden"
									id="pracaDesejada" name="pracaDesejada"
									value="${ permutas[0].praca.idPraca}">
								<div class="text-center">
									<input type="submit" class="btn btn-primary"
										value="Buscar servidores de ${ permutas[0].praca.nomePraca} que queiram vir para ${pracaAtual} ">
								</div>
							</div>
						</form>
					</c:if>


					<c:if
						test="${ not empty permutas[0].unidade.nomeUnidade or not empty permutas[0].cadeiaValorSubgrupo.descricaoSubgrupo }">
						<p>Como voc� forneceu unidade desejada ou processo de trabalho
							desejado, esta solicita��o poder� participar de rela��es
							circulares.</p>


						<form class="form-horizontal"
							action="${pageContext.request.contextPath}/solicitantesDeUmaPracaRelevante">
							<div class="form-group">
								<input type="hidden" id="pracaAtual" name="pracaAtual"
									value="${pracaAtualId}"> <input type="hidden"
									id="pracaDesejada" name="pracaDesejada"
									value="${ permutas[0].praca.idPraca}"> <input
									type="hidden" id="unidadeDesejada" name="unidadeDesejada"
									value="${ permutas[0].unidade.idUnidade}"> <input
									type="hidden" id="processoDesejado" name="processoDesejado"
									value="${ permutas[0].cadeiaValorSubgrupo.idCadeiaValorSubgrupo}">
								<div class="text-center">
									<input type="submit" class="btn btn-primary"
										value="Buscar servidores de ${ permutas[0].praca.nomePraca} que queiram vir para ${pracaAtual} filtrados pela sua solicita��o">
								</div>
							</div>
						</form>
						<form class="form-horizontal"
								action="${pageContext.request.contextPath}/processaDFS">
								<div class="form-group">
									<input type="hidden" id="idPessoa" name="idPessoa"
										value="${pracaAtualId}"> 
									<div class="text-center">
										<input type="submit" class="btn btn-success"
											value="Buscar circular de rela��es">
									</div>
								</div>
							</form>
					</c:if>



				</c:when>

				<c:otherwise>
					<p class="text-center">
						Voc� cadastrou <strong>${quantidade}</strong> solicita��es de
						permuta.
					</p>
					<hr>
					<p class="text-center">
						Voc� informou que seu processo de trabalho <strong>atual</strong>
						na cadeia de valor �: <strong>${processo}.</strong>
					</p>
					<p class="text-center">Outros solicitantes que desejem realizar
						esse processo na cadeia de valor poder�o encontr�-lo.</p>
					<p class="text-center">
						Seu <strong>motivo principal</strong> para tentar uma permuta foi:
						<strong>"${motivo}".</strong>
					</p>
					<p class="text-center">
						Essa informa��o ser� vis�vel <strong>somente pelo DEPES</strong> e
						ser� usada para estudos das movimenta��es internas.
					</p>
				
					<hr>

					<c:forEach items="${permutas}" var="permuta" varStatus="i">
						<p>
							<strong>Solicita��o ${i.index + 1}:</strong>
						</p>




						<br>
						<p>
							<strong>Pra�a desejada:</strong>
							${permutas[i.index].praca.nomePraca }
						</p>
						<c:choose>
							<c:when test="${empty permutas[i.index].unidade.nomeUnidade }">
								<p>
									<strong>Unidade desejada:</strong> N�o informada.
								</p>
							</c:when>
							<c:otherwise>
								<p>
									<strong>Unidade desejada:</strong>
									${permutas[i.index].unidade.nomeUnidade }
								</p>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when
								test="${empty permutas[i.index].cadeiaValorSubgrupo.descricaoSubgrupo }">
								<p>
									<strong>Processo de trabalho desejado:</strong> N�o informado.
								</p>
							</c:when>
							<c:otherwise>
								<p>
									<strong>Processo de trabalho desejado:</strong>
									${permutas[i.index].cadeiaValorSubgrupo.descricaoSubgrupo}
								</p>
							</c:otherwise>
						</c:choose>
						<c:if
							test="${ empty permutas[i.index].unidade.nomeUnidade or empty permutas[i.index].cadeiaValorSubgrupo.descricaoSubgrupo }">
							<p>Quanto maiores as informa��es sobre o posto que voc�
								deseja, maior ser� a chance do sistema encontrar uma rela��o
								circular significativa envolvendo voc� e maior ser� a
								reciprocidade que os outros solicitantes ir�o ver em voc�.</p>
						</c:if>
						<c:if
							test="${ empty permutas[i.index].unidade.nomeUnidade and empty permutas[i.index].cadeiaValorSubgrupo.descricaoSubgrupo }">
							<p>Para esta solicita��o voc� definiu apenas a pra�a
								desejada, dessa forma, s� � poss�vel lhe mostrar a lista de
								pessoas que desejam permutar e est�o atualmente nessa pra�a e
								desejam vir para a sua. Esta solicita��o ser� ignorada em buscas
								circulares.</p>


							<form class="form-horizontal"
								action="${pageContext.request.contextPath}/solicitantesDeUmaPracaRelevante">
								<div class="form-group">
									<input type="hidden" id="pracaAtual" name="pracaAtual"
										value="${pracaAtualId}"> <input type="hidden"
										id="pracaDesejada" name="pracaDesejada"
										value="${ permutas[i.index].praca.idPraca}">
									<div class="text-center">
										<input type="submit" class="btn btn-primary"
											value="Buscar servidores de ${ permutas[i.index].praca.nomePraca} que queiram vir para ${pracaAtual} ">
									</div>
								</div>
							</form>
						</c:if>

						<c:if
							test="${ not empty permutas[i.index].unidade.nomeUnidade or not empty permutas[i.index].cadeiaValorSubgrupo.descricaoSubgrupo }">
							<p>Como voc� forneceu unidade desejada ou processo de
								trabalho desejado, esta solicita��o poder� participar de
								rela��es circulares.</p>


							<form class="form-horizontal"
								action="${pageContext.request.contextPath}/solicitantesDeUmaPracaRelevante">
								<div class="form-group">
									<input type="hidden" id="pracaAtual" name="pracaAtual"
										value="${pracaAtualId}"> <input type="hidden"
										id="pracaDesejada" name="pracaDesejada"
										value="${ permutas[i.index].praca.idPraca}"> <input
										type="hidden" id="unidadeDesejada" name="unidadeDesejada"
										value="${ permutas[i.index].unidade.idUnidade}"> <input
										type="hidden" id="processoDesejado" name="processoDesejado"
										value="${ permutas[i.index].cadeiaValorSubgrupo.idCadeiaValorSubgrupo}">
									<div class="text-center">
										<input type="submit" class="btn btn-primary"
											value="Buscar servidores de ${ permutas[i.index].praca.nomePraca} que queiram vir para ${pracaAtual} filtrados pela sua solicita��o">
									</div>
								</div>
							</form>
							<form class="form-horizontal"
								action="${pageContext.request.contextPath}/processaDFS">
								<div class="form-group">
									<input type="hidden" id="idPessoa" name="idPessoa"
										value="${pracaAtualId}"> 
									<div class="text-center">
										<input type="submit" class="btn btn-success"
											value="Buscar circular de rela��es">
									</div>
								</div>
							</form>
						</c:if>

						<hr>



					</c:forEach>

				</c:otherwise>
			</c:choose>

		</div>
	</div>
</body>
</html>
