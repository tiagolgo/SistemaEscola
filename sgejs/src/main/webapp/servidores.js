'use strict';

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("servidores", ["ui.router"]);

app.config(function ($stateProvider, $urlRouterProvider) {
    //$locationProvider.html5Mode(true);
    //$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
    $urlRouterProvider.otherwise("/home");
    $stateProvider.state('home', {
        url: "/home"
    }).state('editar', {
        url: "/editar/:id",
        templateUrl: "views/servidores/formulario.html",
        controller: "CadastroController"
    }).state('visualizar', {
        url: "/visualizar/:id",
        templateUrl: "views/servidores/visualizar.html",
        controller: "VisualizarController"
    });
});

app.controller('VisualizarController', function ($scope, $state, $http) {
    $scope.servidores = [];
    $scope.selected = null;

    function inicializar() {
        var id = $state.params.id;
        $http.post("/sgejs/rh/visualizar", {"id": id}).then(function (response) {
            console.log('servidor retornado com sucesso', response.data.servidor)
            $scope.servidor = response.data.servidor;
        }, function (response) {
            console.log('erro ao retornar servidor')
        });
    }

    $scope.carregarServidores = function () {
        $http.get("/sgejs/rh/listar").then(function (response) {
            console.log('Lista carregada com sucesso!');
            $scope.servidores = response.data.lista;
        }, function (response) {
            console.log('Erro ao tentar carregar servidores!');
        });
    };
    $scope.carregarServidores();

    $scope.selecionarLinha = function ($index) {
        $scope.selected = $index;
        var id = null;
        angular.forEach($scope.servidores, function (item, index) {
            if ($index == index) {
                id = $scope.servidores[index].id;
            }
        });

        $http.post("/sgejs/rh/visualizar", {"id": id}).then(function (response) {
            $scope.servidor = response.data.servidor;
        }, function (response) {
            console.log('erro ao retornar servidor');
        });
    };

});

app.controller('HomeController', function ($scope, $state, $http) {
    $scope.servidores = [];
    $scope.selected = null;

    $scope.graduacao = {};
    $scope.dependente = {};
    $scope.funcao = {};
    $scope.servidor = {funcoes: [], graduacoes: [], dependentes: []};

    function carregarServidores() {
        console.log('carregar servidores...')
        $http.get("/sgejs/rh/listar").then(function (response) {
            console.log('Lista carregada com sucesso!');
            $scope.servidores = response.data.lista;
            console.log('total de servidores',$scope.servidores.length);
        }, function (response) {
            console.log('Erro ao tentar carregar servidores!');
        });
    }

    $scope.visualizarServidor = function () {
        console.log('visualizar');
        $state.go("visualizar", {id: $scope.servidores[$scope.selected].id});
    };

    $scope.selecionarLinha = function ($index) {
        $scope.selected = $index;
    };

    $scope.autoComplete = function ($event) {
        var tecla = $event.which;
        if (String.fromCharCode(tecla).match("[A-Z a-z]") || tecla === 8) {
            var size = $($event.currentTarget).val().length;
            if (size >= 3) {
                /*$http.post("/sgejs/aluno/autoComplete", {nome: $scope.aluno.nome}).then(function (response) {
                 $scope.resultado = response.data.nomes;
                 });*/
            }
        } else {
            $event.preventDefault();
        }
    };

    $scope.somenteTexto = function ($event) {
        if (!String.fromCharCode($event.which).match("[A-Z a-z]")) {
            $event.preventDefault();
        }
    };

    $scope.somenteNumero = function ($event) {
        if (!String.fromCharCode($event.which).match("[0-9]")) {
            $event.preventDefault();
        }
    };

    $scope.exibirCharm = function (id, pos) {
        toggleMetroCharm(id, pos);
    };

    carregarServidores();

    /*
     * Cadastro de Servidor
     */

    $scope.salvarServidor = function () {
        console.log("salvar servidor", $scope.servidor)
        $http.post("/sgejs/rh/salvar", {servidor: $scope.servidor}).then(function (response) {
            console.log('sucesso ao salvar servidor')
            $.Notify({caption: '', content: ' Servidor Salvo com sucesso!!! ', type: 'success'});
            $scope.servidor = {};
            carregarServidores();
        }, function () {
            console.log('erro ao tentar salvar servidor');
        });
    };

    $scope.editarServidor = function () {
        console.log('editar servidor', $scope.selected);
        if ($scope.selected != null) {
            $state.go("editar", {id: $scope.servidores[$scope.selected].id});
        }
    };

    $scope.excluirServidor = function () {
        if (confirm("Confirma a Exclusão do Servidor?")) {
            $http.post("/sgejs/rh/remover", {"id": $scope.servidores[$scope.selected].id}).then(function (response) {
                console.log('servidor removido com sucesso!')
                carregarServidores();
                $.Notify({caption: '', content: ' Servidor Removido com Sucesso!!! ', type: 'success'});
            });
        }
    };

    /*
     * GRADUAÇÃO
     */
    $scope.adicionarGraduacao = function () {
        $scope.servidor.graduacoes.push(angular.copy($scope.graduacao));
        console.log('adicionar graduacao', $scope.servidor.graduacoes);
    };
    
    $scope.editarGraduacao=function($index){
        console.log('Editar Graduação',$index)
    };
    
    $scope.removerGraduacao=function($index){
        console.log('Remover Graduação',$index)
        
    };
    //End Graduacao

    /*
     * DEPENDENTES
     */
    $scope.adicionarDependente = function () {
        var valido = true;
        var msg = "";
        angular.forEach($scope.servidor.dependentes, function (item, index) {
            if (item.nome == $scope.dependente.nome) {
                valido = false;
                msg = msg + "Já existe um aluno com esse mesmo nome!";
            }
            if (item.rg != null & item.rg == $scope.dependente.rg) {
                valido = false;
                msg = msg + "\nJá existe um dependente com esse RG!"
            }
        });
        if (valido) {
            $scope.servidor.dependentes.push(angular.copy($scope.dependente));
        } else {
            alert(msg);
        }
        console.log('adicionar dependente', $scope.servidor.dependentes);
    };

    $scope.editarDependente = function ($index) {
        console.log('Editar dependente',$index)
    };

    $scope.removerDependente = function ($index) {
        console.log('Remover dependente',$index)

    };
    // End ações sobre dependente

    /*
     * Ações Sobre Função
     */
    $scope.adicionarFuncao = function () {
        console.log('adicionar funcao');
        $scope.servidor.funcoes.push(angular.copy($scope.funcao));
        $scope.funcao = {};
    };

    $scope.editarFuncao = function ($index) {
        console.log('Editar Função',$index)
    };

    $scope.removerFuncao = function ($index) {
        console.log('Remover Função',$index)

    };
    //End ações sobre Função

    $scope.abrirModal = function (id) {
        var dialog = $(id).data('dialog');
        dialog.open();
    };

    $scope.fecharModal = function (id) {
        var dialog = $(id).data('dialog');
        dialog.close();
    };
});

