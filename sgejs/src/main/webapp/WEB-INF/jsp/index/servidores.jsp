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

        <script src="/sgejs/js/jquery.min.js"></script>
        <script src="/sgejs/js/metro.min.js"></script>
        <script src="/sgejs/js/angular.min.js"></script>
        <script src="/sgejs/js/angular-ui-router.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-animate.min.js"></script>
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
            .ng-exibir {
                transition: all linear 0.5s;
                background-color: lightblue;
                height: 100px;
                width: 100%;
                position: relative;
                top: 0;
                left: 0;
            }

            .ng-hide {
                height: 0;
                width: 0;
                background-color: transparent;
                top:-200px;
                left: 200px;
            }
        </style>
    </head>
    <body>
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
                            </div>
                        </div>
                    </a>
                    <a href="#/" class="app-bar-element"> Servidores</a>
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
                    <!--
                    <div class="button-group padding5 no-padding-left" >
                        <button class="button bg-lightBlue" ui-sref="cadastro"><span class="mif-plus"></span> Novo</button>
                        <button class="button bg-lightOrange" ng-click="editarServidor()"><span class="mif-pencil"></span> Editar</button>
                        <button class="button bg-lightRed" ng-click="excluirServidor()"><span class="mif-bin"></span> Remover</button>
                        <div class="dropdown-button">
                            <button class="button dropdown-toggle">Menu</button>
                            <ul class="split-content d-menu" data-role="dropdown">
                                <li><a href="#">Reply</a></li>
                                <li><a href="#">Reply All</a></li>
                                <li><a href="#">Forward</a></li>
                            </ul>
                        </div>
                    </div>
                    -->
                </div>

                <div class="cell">
                    <div class="app-bar-element place-right text-light">
                        C.E. Maria Cândida de Jesus - E.F.M. <img class="icon" style="border-radius: 50%" src="/sgejs/img/icone.jpg"/>
                    </div>
                </div>
            </div>
        </div><!-- End App Bar -->

        <div ui-view style="height: 90%!important"></div>

    </body>
</html>
