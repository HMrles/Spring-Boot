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

<title>Cambiar password</title>
</head>

<body>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title" align="center">Cambiar Password</div>
				</div>

				<div style="padding-top: 1em;"></div>
				<c:if test="${change_password_error != null && change_password_error.length() > 0}">
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<div class="box inputError">${change_password_error}</div>
						</div>
						<div class="col-md-2"></div>
					</div>
				</c:if>
				<form action="/change_password_form" method="post">					
					<c:if test="${user_name_error != null && user_name_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<span class="inputError">${user_name_error}</span>
							<div class="col-sm-8"></div>
						</div>
					</c:if>
					<c:choose>
						<c:when test='${change_type != null && change_type.equals("change_password")}'>
							<input class="invisible" type="text" id="user_name" name="user_name" value="${user_name_val}">
						</c:when>
						<c:otherwise>
							<div class="form-group row">
								<label for="title" class="col-sm-4 col-form-label">Usuario:</label>

								<div class="col-sm-8">
									<c:choose>
										<c:when test="${user_name_val != null && user_name_val.length() > 0}">
											<input type="text" id="user_name" name="user_name" value="${user_name_val}">
										</c:when>
										<c:otherwise>
											<input type="text" id="user_name" name="user_name" placeholder="User name">
										</c:otherwise>
									</c:choose>
								</div>
								
							</div>
							
						</c:otherwise>
					</c:choose>
					
					<c:if test="${old_password_error != null && old_password_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<div class="col-sm-8">
								<span class="inputError">${old_password_error}</span>
							</div>
						</div>
					</c:if>
					<div class="form-group row">
						<label for="title" class="col-sm-4 col-form-label">Password actual:</label>
						<div class="col-sm-8">
							<input type="password" id="old_password" name="old_password" placeholder="Password">
						</div>
						
					</div>
				
					<c:if
						test="${new_password_error != null && new_password_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<div class="col-sm-8">
								<span class="inputError">${new_password_error}</span>
							</div>
						</div>
					</c:if>
					<div class="form-group row">
						<label for="title" class="col-sm-4 col-form-label">Nuevo Password:</label>
						<div class="col-sm-8">
							<input type="password" id="new_password" name="new_password" placeholder="Password">
						</div>
						<!-- col-sm-8 -->
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
				
					<div class="form-group row">
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<span>
								<button type="submit" class="btn btn-primary">Cambiar Password</button>
							</span> <span> <a href="<c:url value="/application" />"
								id="cancel" name="cancel" class="btn btn-primary">Cancelar</a>
							</span>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>