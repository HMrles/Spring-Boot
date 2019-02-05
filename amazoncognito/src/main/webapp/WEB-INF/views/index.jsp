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

<title>Cognito Demo Application Login</title>
</head>

<body>

	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Login con AWS Cognito</div>
					<div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="<c:url value="/forgot_password" />">Olvidaste tu password</a>
					</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>


					<c:if test="${login_error != null && login_error.length() > 0}">
						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="box inputError">${login_error}</div>
							</div>
							<div class="col-md-2"></div>
						</div>
					</c:if>
					<c:if test="${login_message != null && login_message.length() > 0}">
						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="box">${login_message}</div>
							</div>
							<div class="col-md-2"></div>
						</div>
					</c:if>


					<form action="login_form" method="post">
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
							<!--  if the user name is OK, fill it in so the user doesn't have to keep retyping it if, for
			          example, the password and the duplicate password don't match. -->
							<div class="col-sm-8">
								<c:choose>
									<c:when
										test="${user_name_val != null && user_name_val.length() > 0}">
										<input type="text" id="user_name" name="user_name"
											value="${user_name_val}">
									</c:when>
									<c:otherwise>
										<input type="text" id="user_name" name="user_name"
											placeholder="Usuario">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<!--  form-group row -->
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
							<label for="password" class="col-sm-4 col-form-label">Password:</label>
							<div class="col-sm-8">
								<input type="password" id="password" name="password"
									placeholder="Password">
							</div>
							<!-- col-sm-8 -->
						</div>
						<!--  form-group row -->
						<!--  form-group row -->
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<div class="col-sm-8">
								<button type="submit" class="btn btn-primary">Login</button>
							</div>
						</div>


						<div class="form-group">
							<div class="col-md-12 control">
								<div style="border-top: 1px solid #888; padding-top: 15px;">
									<a href="<c:url value="/create_user" />"> Crear una cuenta!
									</a> <br> <a href="<c:url value="/username_lookup" />">
										Buscar Usuario </a> <br> <a
										href="<c:url value="/reset_password" />"> Restablecer la
										contraseña olvidada con el código enviado por correo
										electrónico </a>
								</div>
							</div>
						</div>


					</form>



				</div>
			</div>
		</div>

	</div>
</body>
</html>