var app = angular.module('lancheApp',[]);
            app.controller('LanchesController', function($scope, LanchesServices){
                var listaValores=[];
                var selectedLevel;
                $scope.valorAlface = 0.40;
                $scope.valorBacon = 2.0;
                $scope.valorHamburguer = 3.0;
                $scope.valorOvo = 0.8;
                $scope.valorQueijo = 1.50;
                $scope.qtdeAdicionais = [1,2,3,4,5,6]
                
                $scope.lanche ={};
                $scope.lancheCustom = [];
                
                $scope.selected = function(){
                    return selectedLevel;
                };
                
                LanchesServices.listarLanches().then(function(resposta){
                    $scope.lanches = resposta.data ;
                });
                    $scope.items = [{descritivo: 'Alface',id: 1,qtde:6,valor:$scope.valorAlface},
                        {descritivo: 'Bacon',id: 2, qtde:6,valor:$scope.valorBacon },
                        {descritivo: 'Hamburguer', id: 3, qtde:6,valor:$scope.valorHamburguer },
                        {descritivo: 'Ovo', id: 4, qtde:6,valor:$scope.valorOvo },
                        {descritivo: 'Queijo', id: 5, qtde:6,valor:$scope.valorQueijo }];
                $scope.selected = [];
                
                $scope.toggle = function (item, list) {
                    var idx = list.indexOf(item);
                    if (idx > -1) {
                        list.splice(idx, 1);
                    }
                    else {
                        list.push(item);
                    }
                };
                
                $scope.exists = function (item, list) {
                    $scope.lancheCustom = {descritivo:"X-Custom",
                            id:6,
                            ingredientes:$scope.selected,
                            total:0
                        };
                    return list.indexOf(item) > -1;
                };
                
                $scope.selecionarLanche = function(){
                        if ($scope.selectLanches === 0){
                        LanchesServices.buscarPrecoLanche($scope.lancheCustom).then(function(resposta){
                            $scope.lanche = resposta.data ;   
                        });
                    }else
                    {
                        LanchesServices.buscarPrecoLanche($scope.selectLanches).then(function(resposta){
                            $scope.lanche = resposta.data ;
                        });
                        $scope.selectLanches = 0;
                    }
                    
                };
                
                $scope.setvalores = function(){
                    listaValores.length = 0;
                    listaValores.push($scope.valorAlface);
                    listaValores.push($scope.valorBacon);
                    listaValores.push($scope.valorHamburguer);
                    listaValores.push($scope.valorOvo);
                    listaValores.push($scope.valorQueijo);
                    LanchesServices.inserirPrecos(listaValores).then(function(resposta){
                        $scope.lanches = resposta.data ;   
                    });
                };
                
            });
            
            app.service('LanchesServices', function($http){
                var selectedLanches =[];
                var api = 'http://localhost:8080/api/webresources/lanches';
                
                this.listarLanches = function(){
                    //return listalanches; 
                    return $http.get(api);  
                };
                this.listarLanchesSelec = function(){
                    return selectedLanches;  
                };
                
                this.buscarPrecoLanche = function(lanche){
                    return $http.post(api+'/precolanche',lanche);
                };
                this.inserirPrecos = function(listaValores){
                    //return listalanches;
                    return $http.post(api,listaValores);  
                };
            }); 
