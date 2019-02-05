<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<title>Cambiar email</title>
</head>

<body>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title" align="center">Cambiar email</div>
				</div>

				<div style="padding-top: 1em;"></div>
				<form action="/change_email_form" method="post">
					<!-- User Name -->
					<c:if
						test="${email_addr_error != null && email_addr_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<div class="col-sm-8">
								<span class="inputError">${email_addr_error}</span>
							</div>
						</div>
					</c:if>
					<div class="row">
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<label>Email Actual: ${current_email}</label>
						</div>
					</div>
					<div class="form-group row">
						<label for="title" class="col-sm-4 col-form-label">Email:</label>
						<div class="col-sm-8">
							<input type="email" id="email_addr" name="email_addr" placeholder="Nuevo email">
						</div>
					</div>
					<!--  form-group row -->
					<div class="form-group row">
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<span>
								<button type="submit" class="btn btn-primary">Cambiar email</button>
							</span> 
							<span> <a href="<c:url value="/application" />" id="cancel" name="cancel" class="btn btn-primary">Cancelar</a>
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>