'use strict';

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("servidores", ["ui.router"]);
/*app.config(function ($routeProvider, $httpProvider) {
 /*$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';*
 $routeProvider.when('/', {
 templateUrl: "views/servidores/lista.html"
 }).when("/search/:id/:nome", {
 templateUrl: "views/arquivo/arquivo.html",
 controller: "BuscaController"
 });
 });*/

app.config(function ($stateProvider, $urlRouterProvider) {
    //$locationProvider.html5Mode(true);
    //$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
    $urlRouterProvider.otherwise("/lista");
    $stateProvider.state('lista', {
        url: "/lista",
        templateUrl: "views/servidores/lista.html",
        controller: "ListaController"
    }).state('cadastro', {
        url: "/cadastrar",
        templateUrl: "views/servidores/formulario.html",
        controller: "CadastroController"
    });
});

app.controller('CadastroController', function ($scope, $rootScope, $http, $location) {
    console.log('cadastro controller');

    function serv() {
        return {
            id: null, nascimento: null, sexo: null, raca: null, ufNascimento: null, estadoCivil: null, instrucao: null, posGraduacao: null, nome: null, cidadeNascimento: null, grupoSanguineo: null, linhaFuncional: null,
            dependentes: [],
            graduacoes: [],
            documento: {
                id: null,
                identidade: {id: null, numero: null, emissao: null, pais: null, emissor: null, uf: null},
                pis: {id: null, numero: null, inclusao: null, banco: null},
                titulo: {id: null, numero: null, zona: null, secao: null, emissao: null, pais: null, uf: null},
                reservista: {id: null, numero: null, categoria: null},
                ctps: {id: null, emissao: null, numero: null, serie: null, uf: null},
                cnh: {id: null, numero: null, categoria: null, emissao: null, vencimento: null},
                cpf: null},
            endereco: {id: null, rua: null, bairro: null, municipio: null, numero: null, estado: null, cep: null},
            contato: {id: null, celular: null, fixo: null, email: null},
            filiacao: {id: null, pai: null, mae: null},
            cc: {id: null, banco: null, agencia: null, numero: null}
        };
    }
    $scope.servidor = serv();

    function graduacao() {
        return{id: null, curso: null, instituicao: null, ano: null};
    }
    $scope.graduacao = graduacao();
    
    function dependente() {
        return {id: null, nome: null, rg: null, dependencia: null, estadoCivil: null, situacaoFinanceira: null};
    }
    $scope.dependente = dependente();


    $scope.salvarServidor = function () {
        console.log('cadastrar servidor', $scope.servidor);
        $http.post("/sgejs/rh/salvar", {servidor: $scope.servidor}).then(function (response) {
            console.log('sucesso ao salvar servidor')
        }, function () {
            console.log('erro ao tentar salvar servidor')
        });
    };

    $scope.adicionarGraduacao = function () {
        console.log('adicionar graduacao',$scope.graduacao);
    };

    $scope.adicionarDependente = function () {
        console.log('adicionar dependente', $scope.dependente);
    };

    $scope.abrirModal = function (id) {
        var dialog = $(id).data('dialog');
        dialog.open();
    };

    $scope.fecharModal = function (id) {
        var dialog = $(id).data('dialog');
        dialog.close();
    };
});


app.controller('ListaController', function ($scope, $rootScope, $http, $location) {
    $scope.servidores = [];
    $scope.carregarServidores = function () {
        $http.get("/sgejs/rh/listar").then(function (response) {
            console.log('Lista carregada com sucesso!')
            $scope.servidores = response.data.lista;
        }, function (response) {
            console.log('Erro ao tentar carregar servidores!');
        });
    };
    $scope.carregarServidores();

    $scope.editarServidor = function () {
        console.log('editar servidor');
    };

    $scope.excluirServidor = function () {
        console.log('editar servidor');
    };

    $scope.fecharModal = function () {
        var dialog = $("#modal_arquivo").data('dialog');
        dialog.close();
    };

    $scope.selecionarLinha = function ($index) {
        $scope.selected = $index;
        //$scope.arquivoSelecionado = $scope.arquivos[$index];
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

});

