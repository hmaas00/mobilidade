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
		<div class="jumbotron">

			<!-- Nenhuma solicitação... -->
			<c:choose>

				<c:when test="${quantidade == 0}">
					<p class="text-center">Você não cadastrou nenhuma solicitação
						de permuta.</p>
					<p class="text-center">
						A qualquer momento você pode castrar suas solicitações nessa <a
							href="${pageContext.request.contextPath}/solicitar-permuta">página.</a>
					</li></p>





				</c:when>


				<c:when test="${quantidade == 1}">
					<p class="text-center">
						Você cadastrou <strong>uma</strong> solicitação de permuta:
					</p>
					<br>
					<p>
						<strong>Praça desejada:</strong> ${permutas[0].praca.nomePraca }
					</p>
					<c:choose>
						<c:when test="${empty permutas[0].unidade.nomeUnidade }">
							<p>
								<strong>Unidade desejada:</strong> Você não informou nenhuma
								unidade para essa solicitação.
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
								<strong>Processo de trabalho desejado:</strong> Você não
								informou nenhum processo de trabalho.
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
						<p>Quanto maiores as informações sobre o posto que você
							deseja, maior será a relevância da sugestão do sistema.</p>
					</c:if>
					
					<c:if
							test="${ empty permutas[0].unidade.nomeUnidade and empty permutas[0].cadeiaValorSubgrupo.descricaoSubgrupo }">
							<p>Para essa solicitação você definiu apenas a praça
								desejada, dessa forma, só é possível lhe mostrar a lista de
								pessoas que desejam permutar e estão atualmente nessa praça e
								desejam vir para a sua.</p>


							<form class="form-horizontal"
								action="${pageContext.request.contextPath}/solicitantesDeUmaPraca">
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


				</c:when>

				<c:otherwise>
					<p class="text-center">
						Você cadastrou <strong>${quantidade}</strong> solicitações de
						permuta.
					</p>
					<c:forEach items="${permutas}" var="permuta" varStatus="i">
						<p>
							<strong>Solicitação ${i.index + 1}:</strong>
						</p>




						<br>
						<p>
							<strong>Praça desejada:</strong>
							${permutas[i.index].praca.nomePraca }
						</p>
						<c:choose>
							<c:when test="${empty permutas[i.index].unidade.nomeUnidade }">
								<p>
									<strong>Unidade desejada:</strong> Você não informou nenhuma
									unidade para essa solicitação.
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
									<strong>Processo de trabalho desejado:</strong> Você não
									informou nenhum processo de trabalho.
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
							<p>Quanto maiores as informações sobre o posto que você
								deseja, maior será a relevância da sugestão do sistema.</p>
						</c:if>
						<c:if
							test="${ empty permutas[i.index].unidade.nomeUnidade and empty permutas[i.index].cadeiaValorSubgrupo.descricaoSubgrupo }">
							<p>Para essa solicitação você definiu apenas a praça
								desejada, dessa forma, só é possível lhe mostrar a lista de
								pessoas que desejam permutar e estão atualmente nessa praça e
								desejam vir para a sua.</p>


							<form class="form-horizontal"
								action="${pageContext.request.contextPath}/solicitantesDeUmaPraca">
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

						<hr>



					</c:forEach>

				</c:otherwise>
			</c:choose>




			<c:if test="${quantidade > 0}">
				<hr>
				<p class="text-center">
					Você informou que seu processo de trabalho <strong>atual</strong>
					na cadeia de valor é: <strong>${processo}.</strong>
				</p>
				<p class="text-center">Essa informação é importante porque vai
					ajudar a aumentar a relevância das sugestões do sistema no caso em
					que outros solicitantes estiverem desejando realizar essa processo.</p>
				<p class="text-center">
					Seu <strong>motivo principal</strong> para tentar uma permuta foi:
					<strong>"${motivo}".</strong>
				</p>
				<p class="text-center">
					Essa informação será visível <strong>somente pelo DEPES</strong> e
					será usada para estudos das movimentações internas.
				</p>
			</c:if>



		</div>
	</div>
</body>
</html>
