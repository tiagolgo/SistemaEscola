<%-- 
    Document   : arquivo
    Created on : 08/09/2016, 21:50:41
    Author     : Tiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="servidores">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servidores</title>
        <link href="/sgejs/css/metro-icons.min.css" rel="stylesheet">
        <link href="/sgejs/css/metro.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="/sgejs/css/print.css"  media="print" />

        <script src="/sgejs/js/jquery.min.js"></script>
        <script src="/sgejs/js/metro.min.js"></script>
        <script src="/sgejs/js/angular.min.js"></script>
        <script src="/sgejs/js/angular-ui-router.min.js"></script>
        <!--<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-animate.min.js"></script>-->
        <script src="servidores.js"></script>
        <style type="text/css">
            .table thead {
                border-bottom: 1px solid #999;
            }
            .alt-content{
                height: 90%!important;
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
            .v-menu-select{
                background-color: white!important;
                color: black!important;
            }
            input{
                text-transform: capitalize
            }
            .upper-case{
                text-transform: uppercase;
                font-weight: bold
            }
        </style>
    </head>
    <body ng-controller="HomeController">
        <!-- App Bar -->
        <div class="app-bar flex-grid no-print">
            <div class="row cell-auto-size" style="border-bottom: .5px solid white">
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
                            </div>
                        </div>
                    </a>
                    <a href="#/" class="app-bar-element text-bold" style="font-size: 20px">Maria Cândida</a>
                </div>

                <div class="cell">
                    <div class="input-control text full-size">
                        <input type="text" placeholder="Localizar Servidor" ng-keypress="autoComplete($event)"/>
                        <button class="button"><span class="mif-search"></span></button>
                        <table class="table hovered bg-lighterGray fg-dark capitaliar no-margin-top" style="z-index: 1000;position: absolute">
                            <!--<tbody>
                                <tr>
                                    <td class="paddingP"></td>
                                </tr>
                            </tbody>-->
                        </table>
                    </div>
                </div>

                <div class="cell">
                    <div class="app-bar-element place-right text-light">
                        <img class="icon" style="border-radius: 50%" src="/sgejs/img/icone.jpg"/>
                    </div>
                </div>
            </div>
            <div class="row cell-auto-size">
                <div class="cell size2">
                    <!--<h3 class="text-light">Servidores</h3>-->
                    <ul class="app-bar-menu">
                        <li>
                            <a href="" class="dropdown-toggle" style="font-size: 20px">Servidores</a>
                            <ul class="d-menu" data-role="dropdown">
                                <li><a href="">Trocar Período Letivo</a></li>
                                <li class="divider"></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="cell">
                    <div class="button-group padding5 no-padding-left" >
                        <button class="button" ng-click="exibirCharm('#charm_servidor', 'right')"><span class="mif-plus"></span> Novo</button>
                        <button class="button bg-lightOrange" ng-show="selected != null" ng-click="editarServidor()"><span class="mif-pencil"></span> Editar</button>
                        <button class="button bg-lightRed" ng-show="selected != null" ng-click="excluirServidor()"><span class="mif-bin"></span> Remover</button>
                        <button class="button" ng-show="selected != null" ng-click="visualizarServidor()"><span class=""></span> Detalhar</button>
                    </div>
                </div>
            </div>
        </div><!-- End App Bar -->

        <div class="flex-grid padding5" style="height: 84%!important">

            <div class="row cell-auto-size" style="height: 100%!important">
                <!-- Filtro de exibição de arquivo -->
                <div class="cell size2 padding5 text-light no-padding-top bg-lighterGray">
                    <ul class="v-menu full-size">
                        <li class="menu-title">Página Inicial</li>
                        <li><a href="#"><span class="mif-home icon"></span> Home</a></li>
                        <li class="divider"></li>
                        <li class="menu-title">Opções de Exibição</li>
                        <li><a href="#"><span class="mif-user icon"></span> Ativos</a></li>
                        <li><a href="#"><span class="mif-calendar icon"></span> Afastados</a></li>
                        <li class="divider"></li>
                        <li class="menu-title">Opções de Relatórios</li>
                        <li>
                            <a href="#" class="dropdown-toggle"><span class="mif-printer icon"></span> Relatórios</a>
                            <ul class="d-menu" data-role="dropdown">
                                <li class="menu-title">Gerar Relatório</li>
                                <li><a href="#">Todos os Servidores</a></li>
                                <li><a href="#">Professores</a></li>
                                <li><a href="#">Agentes Educacionais I</a></li>
                                <li><a href="#">Agentes Educacionais II</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>

                <!--Conteúdo/Arquivos -->
                <div class="cell margin20 no-margin-top no-margin-right no-margin-bottom" style="height: 100%!important;overflow-y: scroll">

                    <ui-view>
                        <!--
                        <div class="row cell-auto-size">
                            <div class="cell">
                                <h3 class="text-light">Relação de Servidores 2015</h3>
                            </div>                   
                        </div>
                        -->
                        <div class="tabcontrol2" data-role="tabcontrol">
                            <ul class="tabs">
                                <li class="active"><a href="#frame_1">Principal</a></li>
                                <li class=""><a href="#frame_2">Docentes</a></li>
                                <li class=""><a href="#frame_3">Agente Educadional 1</a></li>
                                <li class=""><a href="#frame_4">Agente Educadional 2</a></li>
                                <li class=""><a href="#frame_5">Equipe Pedagógica</a></li>
                                <li class=""><a href="#frame_6">Direção</a></li>
                            </ul>
                            <div class="frames">
                                <div id="frame_1" class="frame" style="display: block">
                                    <!-- Lista de Servidores -->
                                    <div class="row">
                                        <table class="table striped hovered full-size">
                                            <thead>
                                                <tr>
                                                    <td></td>
                                                </tr>
                                            </thead>
                                            <tbody class="capitaliar">
                                                <!---<tr ng-click="selecionarLinha($index)" ng-class="{'selecionada':selected == $index}">-->
                                                <tr ng-repeat="s in servidores" ng-click="selecionarLinha($index)" ng-class="{'selecionada':selected == $index}">
                                                    <td>{{s.nome}}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div id="frame_2" class="frame" style="display: none"></div>
                                <div id="frame_3" class="frame" style="display: none"></div>
                                <div id="frame_4" class="frame" style="display: none"></div>
                                <div id="frame_5" class="frame" style="display: none"></div>
                                <div id="frame_6" class="frame" style="display: none"></div>
                            </div>
                        </div>

                    </ui-view>

                </div>
            </div>
        </div>

        <div ng-include="'views/servidores/charm_servidor.html'"></div>


    </body>
</html>
