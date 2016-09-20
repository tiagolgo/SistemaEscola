/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("arquivo", ["ngRoute"]);

app.controller('ArquivoController', function ($scope, $rootScope, $http, $location) {
    //$scope.arquivo = {id: null, ano: null, curso: null, tipo: null, caixa: null, criacao: null};
    function arq() {
        return{id: null, ano: null, caixa: null, tipo: null, curso: null, criacao: null};
    }

    function al() {
        return {id: null, nome: null};
    }

    $scope.arquivoSelecionado = arq();
    $scope.arquivo = arq();
    $scope.filtro = {curso: null, tipo: null, ano: null};
    $scope.arquivos = [];
    $scope.aluno = al();
    $scope.alunos = [];
    $scope.selected = null;
    $scope.resultado;

    /* ARQUIVO */
    $scope.carregarArquivos = function () {
        $http.get('/sgejs/arquivo/listarArquivos').then(function (response) {
            $scope.arquivos = response.data.arquivo;
        });
    };

    $scope.visualizarArquivo = function () {
        $scope.titulo = $scope.arquivoSelecionado.curso + " - " + $scope.arquivoSelecionado.ano + " - " + $scope.arquivoSelecionado.tipo;
        $scope.carregarAlunos();
        toggleMetroCharm('#charm_alunos', 'left');
    };

    $scope.carregarAlunos = function () {
        if ($scope.arquivoSelecionado.id !== "") {
            $http.get("/sgejs/arquivo/getArquivo?id=" + $scope.arquivoSelecionado.id).then(function (response) {
                $scope.alunos = response.data.arquivo.alunos;
            }, function (response) {
                console.log('erro ao carregar alunos');
            });
        }
    };

    $scope.novoArquivo = function (novo) {
        if (novo) {//adicionar
            $scope.arquivo = arq();
        } else {//editar
            $scope.arquivo = $scope.arquivoSelecionado;
        }
        var dialog = $("#modal_arquivo").data('dialog');
        dialog.open();
    };

    $scope.salvarArquivo = function ($event) {
        $http.post("/sgejs/arquivo/verificarDuplicidade", {"arquivo": $scope.arquivo}).then(function (response) {
            if (response.data.existe) {
                alert("Você está tentando criar um arquivo que já existe!!!");
            } else {
                $http.post("/sgejs/arquivo/salvarArquivo", {"arquivo": $scope.arquivo}).then(function (response) {
                    $.Notify({caption: '', content: ' O Arquivo foi criado com sucesso!!! ', type: 'success'});
                    carregarArquivos();
                }, function () {
                    $.Notify({caption: '', content: ' Ocorreu um erro ao tentar salvar o arquivo!!! ', type: 'error'});
                });
            }
        });
    };

    $scope.removerArquivo = function () {
        if (confirm('Confirma a Exclusão do Arquivo?')) {
            $http.post("/sgejs/arquivo/removerArquivo", {"id": $scope.arquivoSelecionado.id}).then(function (response) {
                $.Notify({caption: '', content: ' Arquivo removido com sucesso!!! ', type: 'success'});
                carregarArquivos();
            }, function (reponse) {
                $.Notify({caption: '', content: ' Erro ao tentar remover Arquivo!!! ', type: 'error'});
            });
        }
    };
    /* End Arquivo */

    /* ALUNO */
    $scope.adicionaAluno = function () {
        var existe = false;
        angular.forEach($scope.alunos, function (item, index) {
            if ($scope.aluno.nome == item.nome) {
                existe = true;
            }
        });
        if (!existe) {
            $http.post("/sgejs/aluno/salvarAluno", {"arquivo": $scope.arquivoSelecionado.id, "aluno": $scope.aluno}).then(function (response) {
                $scope.aluno.nome = null;
                $scope.carregarAlunos();
            }, function (response) {
                alert('Erro ao tentar salvar o aluno!');
            });
        } else {
            alert("Já existe um aluno com este nome no arquivo!!!");
        }
        return false;
    };

    $scope.removerAluno = function ($index) {
        var a = $scope.alunos[$index];
        if (confirm("Deseja excluir o Aluno?")) {
            $http.post("/sgejs/aluno/removerAluno", {arquivo: $scope.arquivoSelecionado.id, aluno: a}).then(function (response) {
                $scope.carregarAlunos();
                $scope.selecionarLinha(null);
            }, function () {
                alert('Erro ao tentar remover aluno');
            });
        }
    };

    $scope.editarAluno = function ($index) {
        var a = $scope.alunos[$index];
        angular.copy(a, $scope.aluno);
    };
    /* End aluno */

    //Buscar arquivos de acordo com seleção
    $scope.filtrar = function () {
        $http.post("/sgejs/arquivo/filtrar", {arq: $scope.filtro}).then(function (response) {
            var ret = response.data.arquivos;
            if (ret.length > 0) {
                $scope.arquivos = ret;
            } else {
                alert('Não há arquivos!!!');
            }
        });
    };

    $scope.fecharModal = function () {
        var dialog = $("#modal_arquivo").data('dialog');
        dialog.close();
    };

    $scope.selecionarLinha = function ($index) {
        $scope.selected = $index;
        $scope.arquivoSelecionado = $scope.arquivos[$index];
    };

    $scope.autoComplete = function ($event) {
        var tecla = $event.which;
        if (String.fromCharCode(tecla).match("[A-Z a-z]") || tecla === 8) {
            var size = $($event.currentTarget).val().length;
            if (size >= 3) {
                $http.post("/sgejs/aluno/autoComplete", {nome: $scope.aluno.nome}).then(function (response) {
                    $scope.resultado = response.data.nomes;
                });
            } else if (tecla === 8 & size < 3) {
                $scope.resultado = null;
            }
        }else{
            $event.preventDefault();
        }
    };

    $scope.pegarValor = function ($index) {
        angular.forEach($scope.resultado, function (item, index) {
            if ($index == index) {
                $scope.aluno = item;
                $scope.resultado = null;
            }
        });
    };

    $scope.listarArquivosDoAluno = function () {
        $http.post("/sgejs/arquivo/getArquivoQueAlunoEsta", {id: $scope.aluno.id}).then(function (response) {
            toggleMetroCharm('#charm_arquivos', 'right');
            $scope.aluno = al();
        });
    };

    $scope.limparFiltro = function () {
        $scope.filtro.ano = null;
        $scope.filtro.curso = null;
        $scope.filtro.tipo = null;
    };

    $scope.somenteTexto = function ($event) {
        var tecla = $event.which;
        if (!String.fromCharCode(tecla).match("[A-Z a-z]")) {
            $event.preventDefault();
        }
    };

    $scope.verificaAno = function verificaAno($event) {
        var tecla = $event.which;
        if (String.fromCharCode(tecla).match("[0-9]")) {
            if ($($event.currentTarget).val().length > 3) {
                $event.preventDefault();
            }
        } else {
            $event.preventDefault();
        }
    };
    $scope.carregarArquivos();
});

