<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/sgejs/css/metro.css" rel="stylesheet">
<link href="/sgejs/css/metro-icons.css" rel="stylesheet">
<script src="/sgejs/js/jquery.min.js"></script>
<script src="/sgejs/js/metro.js"></script>
<script src="/sgejs/js/angular.min.js"></script>

<style>
    .d-menu li a{
        padding-left: .5rem !important;
    }
</style>
<c:set var="contexto" value="${pageContext.request.contextPath}"/>
<div class="app-bar">
    <a class="app-bar-element no-hovered" href="<c:url value="/"/>"> SGE</a>
    <span class="app-bar-divider"></span>
    <!-- Arquivo Morto -->
    <ul class="app-bar-menu">
        <li>
            <a href="#" class="dropdown-toggle"><span class="mif-school mif-2x"></span> Arquivo Morto</a>
            <ul class="d-menu" data-role="dropdown">
                <li><a href="${contexto}/arquivo/novo">Novo</a></li>
                <li><a href="${contexto}/arquivo/visualizar">Visualizar</a></li>
                <li><a href="${contexto}/arquivo/localizar">Localizar</a></li>
            </ul>
        </li>
    </ul>
    <span class="app-bar-divider"></span>
    <!-- Recursos Humanos -->
    <ul class="app-bar-menu">
        <li>
            <a href="#" class="dropdown-toggle"><span class="mif-users mif-2x"></span> Servidores</a>
            <ul class="d-menu" data-role="dropdown">
                <li><a href="#"> Novo</a></li>
                <li><a href="#">Listar</a></li>
                <li><a href="#">Consultar</a></li>
            </ul>
        </li>
    </ul>
    <span class="app-bar-divider"></span>
    <!-- Livro Ponto -->
    <ul class="app-bar-menu">
        <li>
            <a href="#" class="dropdown-toggle"><span class="mif-books mif-2x"></span> Livro Ponto</a>
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
            <a href="#" class="dropdown-toggle"><span class="mif-drafts mif-2x"></span> Ofício/Comunicado</a>
            <ul class="d-menu" data-role="dropdown">
                <li><a href="#">Novo</a></li>
                <li><a href="#">Consultar</a></li>
                <li><a href="#">Listar</a></li>
            </ul>
        </li>
    </ul>
</div>