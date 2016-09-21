<%-- 
    Document   : arquivo
    Created on : 08/09/2016, 21:50:41
    Author     : Tiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="arquivo">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Arquivo Morto</title>
        <link href="/sgejs/css/metro-icons.min.css" rel="stylesheet">
        <link href="/sgejs/css/metro.min.css" rel="stylesheet">

        <script src="/sgejs/js/jquery.min.js"></script>
        <script src="/sgejs/js/metro.min.js"></script>
        <script src="/sgejs/js/angular.min.js"></script>
        <script src="/sgejs/js/angular-route.min.js"></script>
        <script src="arquivo.js"></script>

        <style type="text/css">
            .table thead {
                border-bottom: 1px solid #999;
            }
            .alt-content{
                height: 90%;
            }
            .table thead td, .table thead th {
                font-weight: normal;
            }
            .table tbody td {
                padding: .720rem .85rem;
            }
            .table tbody tr{
                cursor: pointer;
            }
            .notify{
                padding: 30px!important;
            }
            .selecionada{
                background-color: #0072c6 !important;
                color: white;
            }
            .capitaliar{
                text-transform: capitalize
            }
            .paddingP{
                padding: 5px!important;
            }
        </style>
    </head>
    <body ng-controller="ArquivoController">
        <!-- App Bar -->
        <div class="app-bar flex-grid">
            <div class="row cell-auto-size">
                <div class="cell size2">
                    <a class="app-bar-element" id="toggle-tiles-dropdown">
                        <span  class="mif-apps mif-2x"></span>
                        <div class="app-bar-drop-container"
                             data-role="dropdown"
                             data-toggle-element="#toggle-tiles-dropdown"
                             data-no-close="false" style="width: 324px;">
                            <div class="tile-container bg-white">
                                <div class="tile-small bg-cyan">
                                    <div class="tile-content iconic">
                                        <span class="icon mif-onedrive"></span>
                                    </div>
                                </div>
                                <div class="tile-small bg-yellow">
                                    <div class="tile-content iconic">
                                        <span class="icon mif-google"></span>
                                    </div>
                                </div>
                                <div class="tile-small bg-red">
                                    <div class="tile-content iconic">
                                        <span class="icon mif-facebook"></span>
                                    </div>
                                </div>
                                <div class="tile-small bg-green">
                                    <div class="tile-content iconic">
                                        <span class="icon mif-twitter"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                    <a href="#/" class="app-bar-element" ng-click="carregarArquivos()"> Arquivo Morto</a>
                </div>

                <div class="cell" style="margin-left: 1.25rem">
                    <div style="width: 550px;" class="app-bar-element">
                        <div class="input-control text full-size">
                            <input type="text" placeholder="Localizar Aluno no Arquivo" ng-keypress="autoComplete($event)" ng-model="aluno.nome"/>
                            <button class="button" ng-click="listarArquivosDoAluno()"><span class="mif-search"></span></button>
                            <table class="table hovered bg-lighterGray fg-dark capitaliar no-margin-top" style="z-index: 1000;position: absolute">
                                <tbody>
                                    <tr ng-repeat="r in resultado" ng-click="pegarValor($index)">
                                        <td class="paddingP">{{r.nome}}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="cell">
                    <div class="app-bar-element place-right text-light">
                        C.E. Maria Cândida de Jesus - E.F.M. <img class="icon" style="border-radius: 50%" src="/sgejs/img/icone.jpg"/>
                    </div>
                </div>
            </div>
        </div><!-- End App Bar -->

        <div class="flex-grid alt-content">
            <div class="row cell-auto-size" style="height: 100%;">
                <!-- Filtro de exibição de arquivo -->
                <div class="cell size2 padding5 text-light no-padding-top bg-lighterGray">
                    <h4 class="bg-lighterGray padding5 no-padding-left">Opções de Filtro</h4>
                    <div class="padding5 bg-lightBlue fg-white">Curso do Arquivo</div>
                    <label class="input-control radio small-check full-size no-margin">
                        <input type="radio" value="Fundamental_Anos_Iniciais" name="curso" ng-model="filtro.curso"/>
                        <span class="check"></span>
                        <span class="caption">Fundamental Anos Iniciais</span>
                    </label>
                    <label class="input-control radio small-check full-size no-margin">
                        <input type="radio" value="Fundamental_Anos_Finais" name="curso" ng-model="filtro.curso"/>
                        <span class="check"></span>
                        <span class="caption">Fundamental Anos Finais</span>
                    </label>
                    <label class="input-control radio small-check full-size no-margin">
                        <input type="radio" value="Ensino_Médio" name="curso" ng-model="filtro.curso"/>
                        <span class="check"></span>
                        <span class="caption">Ensino Médio</span>
                    </label>
                    <label class="input-control radio small-check full-size no-margin">
                        <input type="radio" value="Supletivo" name="curso" ng-model="filtro.curso"/>
                        <span class="check"></span>
                        <span class="caption">Supletivo</span>
                    </label>
                    <label class="input-control radio small-check full-size no-margin">
                        <input type="radio" value="Técnico_em_Contabilidade" name="curso" ng-model="filtro.curso"/>
                        <span class="check"></span>
                        <span class="caption">Técnico em Contabilidade</span>
                    </label>
                    <label class="input-control radio small-check full-size no-margin">
                        <input type="radio" value="Correção_de_Fluxo" name="curso" ng-model="filtro.curso"/>
                        <span class="check"></span>
                        <span class="caption">Correção de Fluxo</span>
                    </label>
                    <label class="input-control radio small-check full-size no-margin">
                        <input type="radio" value="Educação_Geral" name="curso" ng-model="filtro.curso"/>
                        <span class="check"></span>
                        <span class="caption">Educação Geral</span>
                    </label>
                    <div class="padding5"></div>
                    <div class="padding5 bg-lightBlue full-size fg-white">Tipo do Arquivo</div>
                    <label class="input-control radio small-check full-size no-margin">
                        <input type="radio" value="Inativo" name="tipo" ng-model="filtro.tipo"/>
                        <span class="check"></span>
                        <span class="caption">Inativo</span>
                    </label>
                    <label class="input-control radio small-check full-size no-margin">
                        <input type="radio" value="Concluintes" name="tipo" ng-model="filtro.tipo"/>
                        <span class="check"></span>
                        <span class="caption">Concluinte</span>
                    </label>
                    <div class="padding5"></div>
                    <div class="padding5 bg-lightBlue full-size fg-white">Ano</div>
                    <div class="input-control full-size text">
                        <input type="text" name="ano" ng-model="filtro.ano" ng-keypress="verificaAno($event)"/>
                    </div>
                    <div class="padding5"></div>
                    <button class="button" ng-click="filtrar()" ng-disabled="filtro.curso == null && filtro.tipo == null && filtro.ano == null"><span></span> Buscar</button>
                    <button class="button" ng-click="limparFiltro()" ng-disabled="filtro.curso == null && filtro.tipo == null && filtro.ano == null"><span></span> Limpar</button>
                </div>

                <!--Conteúdo/Arquivos -->
                <div class="cell margin20 no-margin-top no-margin-right">
                    <!-- Opções de arquivo -->
                    <div class="button-group padding5 no-padding-top no-padding-left" >
                        <button class="button bg-blue fg-white" ng-click="carregarArquivos()"><span class="mif-home"></span> Principal</button>
                        <button class="button" ng-click="novoArquivo(true)"><span class="mif-plus"></span> Novo</button>
                        <button ng-show="selected != null" class="button" ng-click="novoArquivo(false)"><span class="mif-pencil"></span> Editar</button>
                        <button ng-show="selected != null" class="button" ng-click="removerArquivo()"><span class="mif-bin"></span> Remover</button>
                        <button ng-show="selected != null" class="button" ng-click="visualizarArquivo()"><span class="mif-organization"></span> Alunos</button>
                    </div>
                    <!-- Relação de Arquivo Morto -->
                    <h3 class="text-light">Relação de Arquivo Morto</h3>
                    <div class="row" style="height: 90%;overflow-y: scroll">
                        <table class="table striped hovered full-size">
                            <thead>
                                <tr>
                                    <td>Curso</td>
                                    <td>Tipo do Arquivo</td>
                                    <td>Ano</td>
                                    <td>Caixa</td>
                                    <td>Data de Criação</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="a in arquivos" ng-click="selecionarLinha($index)" ng-class="{'selecionada':selected == $index}">
                                    <td>{{a.curso}}</td>
                                    <td>{{a.tipo}}</td>
                                    <td>{{a.ano}}</td>
                                    <td>{{a.caixa}}</td>
                                    <td>{{a.criacao| date:'dd/MM/yyyy'}}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div ng-include="'/sgejs/views/arquivo/alunos.html'"></div>
                    <div ng-include="'/sgejs/views/arquivo/novo.html'"></div>
                    <div ng-include="'/sgejs/views/arquivo/aluno_arquivos.html'"></div>
                </div>
            </div>
        </div>
    </body>
</html>
