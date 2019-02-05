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

<title>Resetear Password</title>
</head>

<body>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title" align="center">Restablecer	contraseña olvidada</div>
				</div>
				<div class="row">
					<div class="col-md-12" align="center">
						Este formulario se utiliza para restablecer la contraseña de un
						usuario con un código enviado por correo electrónico. Puedes usar
						el <a href="<c:url value="/forgot_password" />"> Olvidaste tu
							password </a> formulario para solicitar un código de reinicio..
					</div>
				</div>
				<div style="padding-top: 1em;"></div>
				<c:if
					test="${reset_password_error != null && reset_password_error.length() > 0}">
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<div class="box inputError">${reset_password_error}</div>
						</div>
						<div class="col-md-2"></div>
					</div>
				</c:if>
				<form action="/reset_password_form" method="post">
					<!-- User Name -->
					<c:if
						test="${user_name_error != null && user_name_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<div class="col-sm-8">
								<span class="inputError">${user_name_error}</span>
							</div>
						</div>
					</c:if>
					<div class="form-group row">
						<label for="title" class="col-sm-4 col-form-label">Usuario:</label>
						
						<div class="col-sm-8">
							<c:choose>
								<c:when
									test="${user_name_val != null && user_name_val.length() > 0}">
									<input type="text" id="user_name" name="user_name" value="${user_name_val}">
								</c:when>
								<c:otherwise>
									<input type="text" id="user_name" name="user_name" placeholder="Usuario">
								</c:otherwise>
							</c:choose>
						</div>
						
					</div>				
					<c:if
						test="${reset_code_error != null && reset_code_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<div class="col-sm-8">
								<span class="inputError">${reset_code_error}</span>
							</div>
						</div>
					</c:if>
					<div class="form-group row">
						<label for="title" class="col-sm-4 col-form-label">Codigo Enviado:</label>
						<div class="col-sm-8">
							<input type="text" id="reset_code" name="reset_code" placeholder="Codigo">
						</div>
						
					</div>
					
					<c:if
						test="${new_password_error != null && new_password_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<span class="inputError">${new_password_error}</span>
							<div class="col-sm-8"></div>
						</div>
					</c:if>
					<div class="form-group row">
						<label for="title" class="col-sm-4 col-form-label">Nuevo
							Password:</label>
						<div class="col-sm-8">
							<input type="password" id="new_password" name="new_password"
								placeholder="Password">
						</div>
						
					</div>
				
					<c:if
						test="${verify_password_error != null && verify_password_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<span class="inputError">${verify_password_error}</span>
							<div class="col-sm-8"></div>
						</div>
					</c:if>
					<div class="form-group row">
						<label for="title" class="col-sm-4 col-form-label">Verificar Password:</label>
						<div class="col-sm-8">
							<input type="password" id="verify_password" name="verify_password" placeholder="Password">
						</div>
					
					</div>
					<!--  form-group row -->
					<div class="form-group row">
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<span>
								<button type="submit" class="btn btn-primary">Restablecer Password</button>
							</span> 
							<span> <a href="<c:url value="/" />" id="cancel" name="cancel" class="btn btn-primary">Cancelar</a>
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>