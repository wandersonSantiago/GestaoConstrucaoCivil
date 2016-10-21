app.controller('usuarioController', function($scope, toastr, $rootScope, usuarioService, $http, $routeParams){
	
	var self = this;
		
	var idUsuario = $routeParams.idUsuario;
	$rootScope.tipoEmpreendimento = false;
	$scope.listaUsuario = [];
	
	
	self.user = function(){
		usuarioService.user().
			then(function(u){
				$rootScope.user = u;
				if($rootScope.user.usuario.empreendimento.tipoEmpreendimento = "CONDOMINIO_DE_EDIFICIO_RESIDENCIAL"){
					$rootScope.tipoEmpreendimento = true;
				}
				}, function(errResponse){
			});
		};

		if($rootScope.tipoEmpreendimento == false){
			self.user();
		}
		
	
	self.altera = function(usuario){
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			usuarioService.altera(self.usuario).
			then(function(response){
				self.usuario = null;
				}, function(errResponse){
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		
		}
	}
	
	 self.salva = function(usuario){
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			usuarioService.salva(self.usuario).
			then(function(response){
				self.usuario = null;
				}, function(errResponse){
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		
		}
	}
	 
	
	 self.lista = function(){
		 usuarioService.lista().
			then(function(u){
				if(u.ativo == true){
					u.ativo = "ativo";
					self.usuarios = u;
				}else{
					u.ativo = "inativo";
					self.usuarios = u;
				}
				console.log(self.usuarios.ativo);
				}, function(errResponse){
			});
		};
		//busca a usuario atraves do id
		self.buscaPorId = function(id){
			if(!id)return;
			usuarioService.buscaPorId(id).
			then(function(p){
				self.usuario = p;
				}, function(errResponse){
			});
		};
	//verifica se o params esta com o ide executa o metodo de busca 	
		if(idUsuario){
			self.buscaPorId(idUsuario);
			
		}
	
});