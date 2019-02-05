<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Aplicacion AWS Cognito</title>
</head>

<body>
	<div class="container">
		<div style="padding-top: 1em;"></div>
		<div class="row">
			<div class="col-md-12">
				<h2 align="center">Hola ${user_info.getUserName()}, Estas logueado en la aplicación</h2>
			</div>
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				<h4>Información de perfil</h4>
				<div>Email: ${user_info.getEmailAddr() }</div>
				<div>Region: ${user_info.getLocation() }</div>
			</div>
		</div>
		<div style="padding-top: 1em;"></div>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				<ul>
					<li><a href="<c:url value="/change_password" />">Cambiar password</a></li>
					<li><a href="<c:url value="/change_email" />">Cambiar Email</a></li>
					<li><a href="<c:url value="/change_profile" />">Cambiar información de perfil</a></li>
					<li><a href="<c:url value="/delete_account" />">Eliminar cuenta</a></li>
				</ul>
			</div>
		</div>
		<form action="/logout_form" method="post">
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-10">
					<button type="submit" class="btn btn-primary">Salir</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>