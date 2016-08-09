app.factory('fornecedorService', function($rootScope, toastr, $http,$q){
	
	
	return{
		fornecedorCreate: function(fornecedor){
			return $http.post('/rest/almoxarifado/cadastrarFornecedor', fornecedor)
			
			.then(function(response){
			
				toastr.info('Fornecedor cadastrado');
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar o fornecedor');
				toastr.error('fornecedor não cadastrado');
				return $q.reject(errResponse);
				
			});
		},
		fornecedorFindAll: function(){
			return $http.get('rest/almoxarifado/listarFornecedor')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('erro ao buscar fornecedores');
				console.error('Erro ao tentar buscar os fornecedores');
				return $q.reject(errResponse);
			});
		},
		
	}
});