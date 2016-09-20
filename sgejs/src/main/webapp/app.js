/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('arquivo', ['ui.router']);
app.config(['$stateProvider', '$urlRouterProvider', '$httpProvider', function ($stateProvider, $urlRouterProvider, $httpProvider) {
        //$locationProvider.html5Mode(true);
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
        //$urlRouterProvider.otherwise("/");
        $stateProvider.state('arquivos', {
            url: "/arquivos",
            templateUrl: "view/arquivo/arquivo.html",
            controller: "ArquivoController"
        }).state('visualizar', {
            url: "/arquivo/:id",
            templateUrl: "view/arquivo/visualizar.html",
            controller: "AlunoController"
        });
    }
]);

app.controller('ArquivoController', function ($scope, $http, $state) {
    console.log('arquivo controler');
    $scope.idArquivoSelecionado = null;
    $scope.arquivos;
    $scope.arquivo = {id: '', ano: '', curso: '', tipo: '', caixa: '', criacao: ''};

    carregarArquivos();
    carregarDados();

    //Carregar Arquivos Morto
    function carregarArquivos() {
        $http.get('/sgejs/arquivo/listarArquivos').then(function (response) {
            $scope.arquivos = response.data.arquivo;
        });
        return false;
    }

    //Carrega informações de tipo e curso do arquivo
    function carregarDados() {
        $http.get('/sgejs/arquivo/getCursoTipo').then(function (response) {
            $scope.tipo = response.data.dados[0];
            $scope.curso = response.data.dados[1];
        });
    }

    //Exibir alunos do arquivo com duplo clique
    $scope.visualizarArquivo = function () {
        getIdArquivo();
        if ($scope.idArquivoSelecionado == null) {
            alert("Selecione um Arquivo!");
        } else {
            $state.go("visualizar", {'id': getArquivo().id});
        }
    };

    //Criar Arquivo
    $scope.salvarArquivo = function ($event) {
        if (verSeJaExiste()) {
            alert("Voce esta tentando criar um arquivo que ja existe!");
        } else {
            $http.post("/sgejs/arquivo/salvarArquivo", $($event.currentTarget).serialize()).then(function (response) {
                carregarArquivos();
                $("#modal_arquivo").modal('hide');
            });
        }
    };

    //Remover Arquivo
    $scope.removerArquivo = function () {
        getIdArquivo();
        if ($scope.idArquivoSelecionado == null) {
            alert('Selecione um Arquivo para remover!');
        } else {
            if (confirm("Confirma a exclusao do Arquivo?")) {
                if ($scope.idArquivoSelecionado !== "") {
                    $http.post("/sgejs/arquivo/removerArquivo", "arquivo.id=" + $scope.idArquivoSelecionado).then(function (response) {
                        carregarArquivos();
                    });
                }
            }
        }
    };

    //Editar Arquivo
    $scope.editarArquivo = function () {
        getIdArquivo();
        if ($scope.idArquivoSelecionado == null) {
            alert('Selecione um arquivo para Editar!');
        } else {
            angular.copy(getArquivo(), $scope.arquivo);
            $("#modal_arquivo").modal('show');
        }
    };

    function getArquivo() {
        var arquivo;
        angular.forEach($scope.arquivos, function (value, key) {
            if ($scope.idArquivoSelecionado == value.id) {
                arquivo = $scope.arquivos[key];
            }
        });
        return arquivo;
    }

    //Selecionar linha
    $scope.selecionarLinha = function ($event) {
        var linha = $event.currentTarget;
        var body = $(linha).closest('tbody');
        $($(body).find('tr')).each(function () {
            $(this).removeClass('ativo');
        });
        $(linha).addClass('ativo');
    };

    function getIdArquivo() {
        $scope.idArquivoSelecionado = null;
        var id = $("#table-arquivos").find('tr.ativo').find(".id_arquivo_tb").text();
        if (id != '') {
            $scope.idArquivoSelecionado = id;
        }
    }

    //Verificar se o arquivo ja existe
    function verSeJaExiste() {
        var existe = false;
        angular.forEach($scope.arquivos, function (value) {
            if (value.ano == $scope.arquivo.ano & value.curso == $scope.arquivo.curso & value.tipo == $scope.arquivo.tipo) {
                existe = true;
            }
        });
        return existe == true;
    }

});

app.controller('AlunoController', function ($scope, $http, $stateParams) {
    $scope.alunos = [];
    $scope.idAlunoSelecionado = null;
    $scope.aluno = {id: '', nome: ''};

    carregarAlunos();

    //Carregar alunos do arquivo
    function carregarAlunos() {
        if ($stateParams.id !== "") {
            console.log($scope.titulo)
            $http.get("/sgejs/arquivo/getArquivo?id=" + $stateParams.id).then(function (response) {
                var ret=response.data.arquivo;
                $scope.titulo=ret.curso+" "+ret.ano+" - "+ret.tipo;
                $scope.alunos = ret.alunos;
            }, function (response) {
                console.log('erro ao carregar alunos');
            });
        }
    }

    //Adicionar Aluno
    $scope.adicionaAluno = function ($event) {
        if (verificarNome()) {
            alert("Ja existe um aluno com esse nome no arquivo!!!");
        } else {
            var dados = "arquivo=" + $stateParams.id + "&" + $($event.currentTarget).serialize();
            console.log('salvar aluno: ' + dados)
            $http.post("/sgejs/aluno/salvarAluno", dados).then(function (response) {
                carregarAlunos();
                $("#modal_aluno").modal('hide');
            }, function (response) {
                alert('Erro ao tentar salvar o aluno!');
            });
        }
    };

    //Remover aluno do arquivo
    $scope.removerAluno = function () {
        if ($scope.idAlunoSelecionado == null) {
            alert("Selecione um Aluno");
        } else {
            var dados = "arquivo=" + $stateParams + "&aluno.id=" + $scope.idAlunoSelecionado;
            $http.post("/sgejs/aluno/removerAluno", dados).then(function (response) {
                carregarAlunos();
            }, function () {
                alert('Erro ao tentar remover aluno');
            });
        }
    };

    //Editar aluno
    $scope.editarAluno = function () {
        if ($scope.idAlunoSelecionado == null) {
            alert("Selecione um aluno");
        } else {
            angular.copy(getAluno(), $scope.aluno);
            $("#modal_aluno").modal('show');
        }
    };

    //Verificar se o aluno ja existe no arquivo
    function verificarNome() {
        var possui = true;
        angular.forEach($scope.alunos, function (value, key) {
            if ($scope.aluno.nome === value.nome) {
                possui = false;
            }
        });
        return possui === false;
    }

    //Selecionar linha
    $scope.selecionarLinha = function ($event) {
        var linha = $event.currentTarget;
        $scope.idAlunoSelecionado = $(linha).find(".id_aluno_tb").text();
        $($($(linha).closest('tbody')).find('tr')).each(function () {
            $(this).removeClass('ativo');
        });
        $(linha).addClass('ativo');
    };

    //Buscar aluno
    function getAluno() {
        var aluno;
        angular.forEach($scope.alunos, function (value, key) {
            if ($scope.idAlunoSelecionado == value.id) {
                aluno = $scope.alunos[key];
            }
        });
        return aluno;
    }
});


