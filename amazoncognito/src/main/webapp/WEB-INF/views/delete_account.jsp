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

<title>Eliminar cuenta</title>
</head>

<body>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title" align="center">Eliminar Cuenta</div>
				</div>

				<div style="padding-top: 1em;"></div>

				<form action="/delete_account_form" method="post">
					<!-- Password -->
					<c:if
						test="${password_error != null && password_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<span class="inputError">${password_error}</span>
							<div class="col-sm-8"></div>
						</div>
					</c:if>

					<div class="form-group row">
						<label for="password" class="col-sm-2 col-form-label">Password:</label>
						<div class="col-sm-6">
							<input type="password" id="password" name="password" placeholder="Password">
						</div>
						<!-- col-sm-6 -->
						<div class="col-sm-4"></div>
					</div>
				
					<div class="form-group row">
						<div class="col-sm-2"></div>
						<div class="col-sm-6">
							<span>
								<button type="submit" class="btn btn-primary">Eliminar Cuenta</button>
							</span> <span> <a href="<c:url value="/" />" id="cancel"
								name="cancel" class="btn btn-primary">Cancelar</a>
							</span>
						</div>
						<div class="col-sm-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>