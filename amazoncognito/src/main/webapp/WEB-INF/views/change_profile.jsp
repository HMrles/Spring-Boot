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

<title>Cambiar información del perfil</title>
</head>

<body>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title" align="center">Cambiar información del perfil</div>
				</div>

				<div style="padding-top: 1em;"></div>
				<form action="/change_profile_form" method="post">
					<!-- Location -->
					<c:if test="${location_error != null && location_error.length() > 0}">
						<div class="form-group row">
							<div class="col-sm-4"></div>
							<div class="col-sm-8">
								<span class="inputError">${location_error}</span>
							</div>
						</div>
					</c:if>
					<div class="form-group row">
						<label for="location" class="col-sm-4 col-form-label">Region</label>
						<select class="custom-select col-sm-8" name="location" id="location">
							<option selected>${user_info.getLocation() }</option>
							<option value="US East (N. Virginia)">US East (N.Virginia)</option>
								<option value="EE.UU. Este (Ohio)">EE.UU. Este (Ohio)</option>
								<option value="EE.UU. Oeste (Norte de California)">EE.UU. Oeste (Norte de California)</option>
								<option value="EE.UU. Oeste (Oregón)">EE.UU. Oeste (Oregón)</option>
								<option value="EU (Stockholm)">EU (Stockholm)</option>
								<option value="América del Sur (São Paulo)">América del Sur (São Paulo)</option>
						</select>
						
					</div>
					<!-- form-group row -->
					<div class="form-group row">
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<span>
								<button type="submit" class="btn btn-primary">Actualizar</button>
							</span> 
							<span> <a href="/application" id="cancel" name="cancel" class="btn btn-primary">Cancelar</a>
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>