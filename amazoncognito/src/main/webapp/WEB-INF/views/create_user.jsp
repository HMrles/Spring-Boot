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

<title>Crear nuevo usuario</title>
</head>

<body>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title" align="center">Crear nuevo usuario</div>
				</div>

				<div class="row">
					<div class="col-md-12" align="center"> Se enviará un correo electrónico con un código de creación de cuenta a su dirección de correo electrónico.</div>
				</div>

				<div style="padding-top: 1em;"></div>


				<c:if
					test="${createUserError != null && createUserError.length() > 0}">
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<div class="box inputError">${createUserError}</div>
						</div>
						<div class="col-md-2"></div>
					</div>
				</c:if>

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


					<form action="/create_user_form" method="post">
						<c:if
							test="${userNameError != null && userNameError.length() > 0}">
							<div class="form-group row">
								<div class="col-sm-4"></div>
								<div class="col-sm-8">
									<span class="inputError">${userNameError}</span>
								</div>
							</div>
						</c:if>
						<div class="form-group row">
							<label for="title" class="col-sm-4 col-form-label">Usuario:</label>
							<c:choose>
								<c:when
									test="${userNameVal != null && userNameVal.length() > 0}">
									<input type="text" id="user_name" name="user_name" value="${userNameVal}">
								</c:when>
								<c:otherwise>
									<div class="col-sm-8">
										<input type="text" id="user_name" name="user_name" placeholder="User name">
									</div>
									
								</c:otherwise>
							</c:choose>
						</div>
						<!--  form-group row -->
						<c:if test="${emailError != null && emailError.length() > 0}">
							<div class="form-group row">
								<div class="col-sm-4"></div>
								<div class="col-sm-8">
									<span class="inputError">${emailError}</span>
								</div>
							</div>
						</c:if>
						<div class="form-group row">
							<label for="title" class="col-sm-4 col-form-label">Email:</label>
							<div class="col-sm-8">
								<input type="text" id="email" name="email" placeholder="Email">
							</div>
						
						</div>
						
						<c:if
							test="${locationError != null && locationError.length() > 0}">
							<div class="form-group row">
								<div class="col-sm-4"></div>
								<span class="inputError">${locationError}</span>
								<div class="col-sm-8"></div>
							</div>
						</c:if>
						<div class="form-group row">
							<label for="location" class="col-sm-4 col-form-label">Region</label>
							<select class="custom-select col-sm-8" name="location" id="location">
								<option value="US East (N. Virginia)">US East (N.Virginia)</option>
								<option value="EE.UU. Este (Ohio)">EE.UU. Este (Ohio)</option>
								<option value="EE.UU. Oeste (Norte de California)">EE.UU. Oeste (Norte de California)</option>
								<option value="EE.UU. Oeste (Oregón)">EE.UU. Oeste (Oregón)</option>
								<option value="EU (Stockholm)">EU (Stockholm)</option>
								<option value="América del Sur (São Paulo)">América del	Sur (São Paulo)</option>
							</select>
						
						</div>
						<!-- form-group row -->
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<div class="col-sm-8">
								<span>
									<button type="submit" class="btn btn-primary">Crear	Usuario</button>
								</span> <span> <a href="<c:url value="/" />" id="cancel"
									name="cancel" class="btn btn-primary">Cancelar</a>
								</span>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>
</body>
</html>