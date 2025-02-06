<%@page import="modelo.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<html>
    <head>
        <title>smd-ecommerce</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="<%= request.getContextPath() %>/css/index.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <header>
                <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="<%= request.getContextPath() %>/Inicio">
                            <!--<img src="images/icon.png" alt="" width="26" height="26" class="d-inline-block align-text-top">-->
                            smd-ecommerce
                        </a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerTop" aria-controls="navbarTogglerTop" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarTogglerTop"> 
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link" href="<%= request.getContextPath() %>/Inicio">Início</a>
                                </li>
                                <% if (usuario == null) { %>
                                <li class="nav-item">
                                    <a class="nav-link" href="<%= request.getContextPath() %>/login.jsp">Login</a>
                                </li>
                                <% } %>
                                <li class="nav-item">
                                    <a class="nav-link" href="<%= request.getContextPath() %>/cadastrarCliente.jsp">Cadastre-se</a>
                                </li>
                                <% if (usuario != null && usuario instanceof Usuario && usuario.isAdministrador()) { %>
                                <li class="nav-item">
                                    <a class="nav-link" href="<%= request.getContextPath() %>/administrador/ListarProdutos">Cadastro de Produtos</a>
                                </li>
                                <% } %>
                                <% if (usuario != null && usuario instanceof Usuario && usuario.isAdministrador()) { %>
                                <li class="nav-item">
                                    <a class="nav-link" href="<%= request.getContextPath() %>/administrador/RelatorioProdutosFaltantesEmEstoque">Produtos Faltantes em Estoque</a>
                                </li>
                                <% } %>
                                <%
                                    if (usuario != null && usuario instanceof Usuario) {
                                %>
                                <li class="nav-item">
                                    <a class="nav-link" href="atualizarDados.jsp">Atualizar dados pessoais</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="deletarUsuario.jsp">Deletar sua conta</a>
                                </li>
                                <%   } %>
                            </ul>
                            <% if (usuario != null && usuario instanceof Usuario) { %>
                            <form class="d-flex" action="<%= request.getContextPath() %>/Logout" method="get">
                                <div class="form-control bg-transparent border-0 me-2">Olá, <b><%= usuario.getNome().indexOf(" ") == -1 ? usuario.getNome() : usuario.getNome().substring(0, usuario.getNome().indexOf(" ")) %></b>! (<%= (usuario.isAdministrador() ? "Administrador" : "Cliente") %>)</div>
                                <button class="btn btn-outline-danger" type="submit">Logout</button>
                            </form>
                            <% } %>
                        </div>
                    </div>
                </nav>
            </header>
            <main>
                <%
                    String mensagem = (String) request.getAttribute("mensagem");
                    if (mensagem != null) {
                %>
                    <div class="alert alert-info alert-dismissible fade show mt-2" role="alert">
                        <strong><%= mensagem%></strong>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                <%
                    }
                %>
                <div id="container" class="d-flex justify-content-center">
