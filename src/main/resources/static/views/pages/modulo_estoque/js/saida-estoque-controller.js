app.controller('saidaEstoqueController', function($scope, saidaEstoqueService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		$scope.listaProdutos = [];
		self.produto = [];
		self.produtos = [];
		self.objeto = [];
		$scope.produto = [{produto : "", quantidade :""}];
		var quantidadeMaior = true;
		var produtoMesmoImovel = true;		
			self.buscaPorCodigoBarras = function(codigoBarras){
				saidaEstoqueService.buscaPorCodigoBarras(codigoBarras).
				then(function(p){					
					self.listaProdutosComEstoques = p;
					
								$scope.quantidade = self.listaProdutosComEstoques.quantidade;
								$scope.produto = self.listaProdutosComEstoques.produto;
													
								
				});
			};
			self.listaProdutosComEstoque = function(){
				saidaEstoqueService.listaProdutosComEstoque().
					then(function(t){
						self.listaProdutosComEstoques = t;
						//console.log(self.listaProdutosComEstoques);
						self.listaProdutos = [{produto:"",quantidade:""}];											
						for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
						
								self.produto = self.listaProdutosComEstoques[i].produto;
								self.quantidade = self.listaProdutosComEstoques[i].quantidade;
								self.listaProdutos.push({
										produto : self.produto,
										quantidade : self.quantidade									
								});	
						}	
							}, function(errResponse){
					});
				};
			
		self.ativarExcluirLote = function(listaProduto){
			self.listaProduto.filter(function(produto){
			if(produto.selecionado){
				$scope.ativadoExcluirLote = true;
			}
			});
		}
			
			self.apagarProduto = function(listaProduto){
				
				self.listaProduto = listaProduto.filter(function(produto){
				if(!produto.selecionado) return produto;
				$scope.ativadoExcluirLote = null;				
				});
				
				if(self.listaProduto.length == 0){					
					$scope.ativaTabela = false;
				}
		}
			
			
			self.funcaoListaProdutoEdificio = function(objeto){
				
				self.listaProduto.push({
					quantidadeEstoque : objeto.produto.quantidade,
					produto : objeto.produto.produto,
					areaProduto : objeto.areaProduto,
					quantidadeSaida : objeto.quantidadeSaida,
					andar : objeto.andar,
					torre : objeto.torre,
					apartamento : objeto.apartamento					
				});
				
			}
			self.funcaoListaProdutoCasa = function(objeto){
							
					self.listaProduto.push({
						quantidadeEstoque : objeto.produto.quantidade,
						produto : objeto.produto.produto,
						areaProduto : objeto.areaProduto,
						quantidadeSaida : objeto.quantidadeSaida,
						andar : objeto.andar,						
						casa : objeto.casa												
					});
					
				}
			self.funcaoListaProdutoEdificacoesComunitaria = function(objeto){
				
					self.listaProduto.push({
						quantidadeEstoque : objeto.produto.quantidade,
						produto : objeto.produto.produto,
						areaProduto : objeto.areaProduto,
						quantidadeSaida : objeto.quantidadeSaida,					
						descricao : objeto.edificacaoComunitaria.descricao						
					});	
					console.log(self.listaProduto);
			}
		
			verificaProdutoRepetido = function(objeto){
				for(i = 0; i < self.listaProduto.length; i++ ){
					var produto2 = self.listaProduto[i].produto.id;
					
					if(produto2 != objeto.produto.produto.id){					
					
						}
					else{
						
						objeto.produto.quantidade = objeto.produto.quantidade - objeto.quantidadeSaida;	
						i = self.listaProduto.length + 1;
						
						}
						
					}			
			}
		
	
		self.adicionarProdutoEdificio = function(objeto){
			if(!$scope.habilitaBuscaPorDescricao){objeto.produto = {produto : $scope.produto, quantidade : $scope.quantidade};}
			verificaQuantidade(objeto);	
			if(quantidadeMaior){
				$scope.ativaTabela = true;
					verificaProdutoRepetido(objeto);
					self.funcaoListaProdutoEdificio(objeto);
			}
		}
		self.adicionarProdutoCasa = function(objeto){
			if(!$scope.habilitaBuscaPorDescricao){objeto.produto = {produto : $scope.produto, quantidade : $scope.quantidade};}		
			verificaQuantidade(objeto);	
			if(quantidadeMaior){
				$scope.ativaTabela = true;
					verificaProdutoRepetido(objeto);
					self.funcaoListaProdutoCasa(objeto);				
				
			}
		}
		self.adicionarProdutoEdificacaoComunitaria = function(objeto){		    
			if(!$scope.habilitaBuscaPorDescricao){objeto.produto = {produto : $scope.produto, quantidade : $scope.quantidade};}			
			console.log(objeto);
			verificaQuantidade(objeto);			
			
			if(quantidadeMaior){
				$scope.ativaTabela = true;
					verificaProdutoRepetido(objeto);
					
					self.funcaoListaProdutoEdificacoesComunitaria(objeto);
				
			}
		}
		

		verificaQuantidade = function(objeto){
			
			if(objeto.produto.quantidade < objeto.quantidadeSaida){
				sweetAlert({ timer : 6000,  text :"Quantidade Superior ao estoque",  type : "info", width: 300, higth: 300, padding: 20});
				quantidadeMaior = false;
			}
			else if(objeto.quantidadeSaida < 1 || objeto.quantidadeSaida == null){
					sweetAlert({ timer : 6000,  text :"Quantidade tem que ser maior que zero",  type : "info", width: 300, higth: 300, padding: 20});
				quantidadeMaior = false;	
			}
		}
			
		
		
		self.salvaEdificio = function(){
			self.baixaEstoque = self.listaProduto;
			saidaEstoqueService.salvaEdificio(self.baixaEstoque)
			.then($timeout(function(response){
				self.listaProdutosComEstoque();
				self.limpaCampo();
			},500), function(errResponse){					
			});			
		}
		
		self.salvaCasa = function(){
			self.baixaEstoqueCasa = self.listaProduto;
			saidaEstoqueService.salvaCasa(self.baixaEstoqueCasa)
			.then($timeout(function(response){
				self.listaProdutosComEstoque();
				self.limpaCampo();
			},500), function(errResponse){					
			});
		}
		
		self.salvaEdificacoesComunitaria = function(){
			self.baixaEstoqueOutros = self.listaProduto;
			saidaEstoqueService.salvaOutros(self.baixaEstoqueOutros)
			.then( $timeout(function(response){
				self.listaProdutosComEstoque();
				self.limpaCampo();
			},500), function(errResponse){		
			});
		}
		
		
		self.limpaCampo = function(){
			self.objeto = null;
			$scope.quantidade = null;
			$scope.produto = null;
			$scope.codigo;
			self.listaProduto = self.listaProduto=[];			
			$scope.ativadoExcluirLote = false;
			$scope.ativaTabela = false;
			
			
			
		}
		
		$scope.tipo = {
			tipo : ''	
		};		
		self.tipoConf = function(){
		if($scope.tipo.tipo == "edificio"){
			$scope.casa = false;
			$scope.comunitaria = false;
			$scope.edificio = true;
			self.limpaCampo();
			
	}
		if($scope.tipo.tipo == "comunitaria"){
			$scope.casa = false;
			$scope.edificio = false;
			$scope.comunitaria = true;
			self.limpaCampo();
			
		}
		if($scope.tipo.tipo == "casa"){
			$scope.comunitaria = false;
			$scope.edificio = false;
			$scope.casa = true;
			self.limpaCampo();
			
			
		}
		}
		

});