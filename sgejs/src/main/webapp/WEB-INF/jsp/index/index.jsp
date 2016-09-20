<%-- 
    Document   : index
    Created on : 01/10/2014, 17:44:58
    Author     : Tiago Luiz Gomes
    Página Inicial do Sistema
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html> 
<html lang="pt">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>SGEWEB</title>
        <link href="/sgejs/css/metro-icons.min.css" rel="stylesheet">
        <link href="/sgejs/css/metro.min.css" rel="stylesheet">
        <script src="/sgejs/js/jquery.min.js"></script>
        <script src="/sgejs/js/metro.min.js"></script>
        <script src="/sgejs/js/angular.min.js"></script>
    </head>
    <body>
        <div class="app-bar">
            <a class="app-bar-element no-hovered" href="<c:url value="/"/>"> SGE</a>
            <span class="app-bar-divider"></span>
            <!-- Arquivo Morto -->
            <ul class="app-bar-menu">
                <li><a href="/sgejs/arquivo"><span class="mif-school"></span> Arquivo Morto</a></li>
            </ul>
            <span class="app-bar-divider"></span>
            <!-- Recursos Humanos -->
            <ul class="app-bar-menu">
                <li><a href="/sgejs/servidores"><span class="mif-users"></span> Servidores</a></li>
            </ul>
            <span class="app-bar-divider"></span>
            <!-- Livro Ponto -->
            <ul class="app-bar-menu">
                <li>
                    <a href="#" class="dropdown-toggle"><span class="mif-books"></span> Livro Ponto</a>
                    <ul class="d-menu" data-role="dropdown">
                        <li><a href="#">Novo</a></li>
                        <li><a href="#">Consultar</a></li>
                        <li><a href="#">Listar</a></li>
                        <li><a href="#">Buscar Servidor</a></li>
                    </ul>
                </li>
            </ul>
            <span class="app-bar-divider"></span>
            <!-- Recursos Humanos -->
            <ul class="app-bar-menu">
                <li>
                    <a href="#" class="dropdown-toggle"><span class="mif-drafts"></span> Ofício/Comunicado</a>
                    <ul class="d-menu" data-role="dropdown">
                        <li><a href="#">Novo</a></li>
                        <li><a href="#">Consultar</a></li>
                        <li><a href="#">Listar</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="flex-grid padding100">
            <div class="row cell-auto-size">
                <div class="cell align-center">
                    <img class="icon" style="width:400px;height: 320px" src="/sgejs/img/logo.jpg"/>
                </div>
            </div>
        </div>
    </body>
</html>