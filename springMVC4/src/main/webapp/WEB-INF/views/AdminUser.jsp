
<!DOCTYPE html>
<html lang="en">
<head>
<title>SpringMVC-AngularJS</title>
<meta charset="utf-8">
<!--  
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="css/app.css" rel="stylesheet">
-->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<body ng-app="myApp" class="ng-cloak">


	<div class="container" ng-controller="UserController as ctrl">



		<div class="panel panel-info">
			<div class="panel-heading">Registro de empleados</div>
			<div class="panel-body">

				<form ng-submit="ctrl.submit()" name="myForm">

					<input type="hidden" ng-model="ctrl.user.id" />

					<div class="form-group row col-md-12">
						<label class="col-md-2 control-lable" for="file">Nombre</label>
						<div class="col-md-7">
							<input type="text" ng-model="ctrl.user.username" name="uname"
								class="username form-control input-sm"
								placeholder="Ingrese su nombre" required ng-minlength="3" />
							<div class="has-error" ng-show="myForm.$dirty">
								<span ng-show="myForm.uname.$error.required">Requerido</span> <span
									ng-show="myForm.uname.$error.minlength">Longitud minima
									de 3</span> <span ng-show="myForm.uname.$invalid">El campo es
									invalido </span>
							</div>
						</div>
					</div>




					<div class="form-group row col-md-12">
						<label class="col-md-2 control-lable" for="file">Dirección</label>
						<div class="col-md-7">
							<input type="text" ng-model="ctrl.user.address"
								class="form-control input-sm" placeholder="Ingrese su dirección" />
						</div>
					</div>


					<div class="form-group has-danger">
						<div class="form-group row col-md-12">
							<label class="col-md-2 control-lable" for="file">Email</label>
							<div class="col-md-7">
								<input type="email" ng-model="ctrl.user.email" name="email"
									class="email form-control input-sm"
									placeholder="Ingrese su Email" required />
								<div class="form-control-feedback" ng-show="myForm.$dirty">
									<span ng-show="myForm.email.$error.required">Requerido</span> <span
										ng-show="myForm.email.$invalid">El campo es invalido </span>
								</div>
							</div>
						</div>
					</div>


					<div class="form-group row col-md-12">
						<input type="submit"
							value="{{!ctrl.user.id ? 'Guardar' : 'Actualizar'}}"
							class="btn btn-primary" ng-disabled="myForm.$invalid">
						<button type="button" ng-click="ctrl.reset()"
							class="btn btn-warning" ng-disabled="myForm.$pristine">Limpiar</button>
					</div>

				</form>


			</div>
		</div>

		<div class="container">
			<div class="panel panel-primary">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<div class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-search"></i></span> <input type="text"
							class="form-control" placeholder="Buscare Empleado"
							ng-model="buscar">
					</div>
				</div>
				<div class="tablecontainer">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Nombre</th>
								<th>Dirección</th>
								<th>Email</th>
								<th width="20%"></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="u in ctrl.users | filter:buscar">
								<td><span ng-bind="u.id"></span></td>
								<td><span ng-bind="u.username"></span></td>
								<td><span ng-bind="u.address"></span></td>
								<td><span ng-bind="u.email"></span></td>
								<td>
									<button type="button" ng-click="ctrl.edit(u.id)"
										class="btn btn-success custom-width">Editar</button>
									<button type="button" ng-click="ctrl.remove(u.id)"
										class="btn btn-danger custom-width">Eliminar</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>

	<script src="js/app.js"></script>
	<script src="js/service/user_service.js"></script>
	<script src="js/controller/user_controller.js"></script>
</body>
</body>
</html>




</html>
