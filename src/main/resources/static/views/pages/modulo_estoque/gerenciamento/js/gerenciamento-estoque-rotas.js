
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	$routeProvider

	
	.when('/estoque/gerenciamento/cancelamentoNota', {
		templateUrl:"views/pages/modulo_estoque/gerenciamento/cancelamentoNota.html",
	})
	.when('/estoque/gerenciamento/iventario', {
		templateUrl:"views/pages/modulo_estoque/gerenciamento/iventario.html",
	})	

}]);




