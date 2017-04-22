<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<c:url value="/resources/css" var="cssPath" />
<c:url value="/resources/js" var="jsPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>

<style type="text/css">
.cadastro {
	padding-top: 20px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Casa
					do Código</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${s:mvcUrl('PaC#listar').build()}">Lista de
							Produtos</a></li>
					<li><a href="${s:mvcUrl('PaC#form').build()}">Cadastro de
							Produtos</a></li>
				</ul>
				<security:authorize access="isAuthenticated()">		
						<li class="nav navbar-nav navbar-right"><a href="#"> <security:authentication
									property="principal" var="usuario" /> ${usuario.username}
						</a></li>
				</security:authorize>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>
	<div class="cadastro col-md-8 col-md-offset-2">
		<h1>Cadastro de Produto</h1>
		<form:form action="${s:mvcUrl('PaC#gravar').build() }" method="post"
			commandName="produto" enctype="multipart/form-data">

			<div class="form-group">
				<label>Título</label>
				<form:input cssClass="form-control" path="titulo" />
				<form:errors path="titulo" />
			</div>
			<div class="form-group">
				<label>Descrição</label>
				<form:textarea cssClass="form-control" rows="10" cols="20"
					path="descricao" />
				<form:errors path="descricao" />
			</div>
			<div class="form-group">
				<label>Páginas</label>
				<form:input cssClass="form-control" path="paginas" />
				<form:errors path="paginas" />
			</div>
			<div class="form-group">
				<label>Data de Lançamento</label>
				<form:input cssClass="form-control" path="dataLancamento" />
				<form:errors path="dataLancamento" />
			</div>
			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
				<div class="form-group">
					<label>${tipoPreco}</label>
					<form:input cssClass="form-control"
						path="precos[${status.index}].valor" />
					<form:hidden path="precos[${status.index}].tipo"
						value="${tipoPreco}" />
				</div>
			</c:forEach>

			<div>
				<label>Sumário</label> <input class="form-control" name="sumario"
					type="file" />
			</div>
			<button class="btn btn-default" type="submit">Cadastrar</button>

		</form:form>
	</div>
</body>
</html>