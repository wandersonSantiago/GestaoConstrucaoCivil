app.factory('CotacaoEmpresaService', function($rootScope, toastr, $http,$q){
	
	var url = "/rest/almoxarifado/cotacao";
	
	return{
		insert: function(cotacaoEmpresa){	
			return $http.post(url, cotacaoEmpresa)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		update: function(cotacaoEmpresa){			
			return $http.put(url, cotacaoEmpresa)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		launch: function(cotacaoEmpresa){	
			return $http.post(url +"/empresa", cotacaoEmpresa)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		findByConcorrentes: function(idCotacao){	
			return $http.get(url +"/empresa/concorrentes/" + idCotacao)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		findByVencedores: function(idCotacao){	
			return $http.get(url +"/empresa/buscaGanhdores/"+ idCotacao)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		findByTextAndPagination: function(text , page ,  orderBy, direction , maxResults){
			var config = {params: {tema : text, page: page , maxResults : maxResults, orderBy : orderBy, direction : direction}};
			return $http.get(url + '/buscar', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		filter: function(cotacaoFilter){
			return $http.post(url + '/filter', cotacaoFilter)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		pdf: function(cotacaoFilter){
			var config =  {cotacaoFilter : cotacaoFilter, responseType: 'arraybuffer'};
				return $http.post(url + '/imprimir',cotacaoFilter, { responseType: 'arraybuffer'} )
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});			
		},
		findById: function(param){
			return $http.get(url +'/'+ param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		findByCotacaoEmpresaId: function(param){
			return $http.get(url +'/empresa/'+ param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		findByDescricao: function(texto){
			var config = {params: {tema:texto}};
			return $http.get(url + '/buscar', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		status: function(){
			return $http.get(url +'/status')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		closeCotacao: function(idCotacao){
			return $http.put(url + '/close/' + idCotacao)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
	}
});