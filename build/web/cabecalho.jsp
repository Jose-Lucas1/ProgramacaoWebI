<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.usuario.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Loja Geek</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="icon" type="image/x-icon" href="nerd.png">
    </head>
    <body>
        <div class="container">
            <header>
                <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="Inicio">
                            <img src="nerd.png" alt="" width="26" height="26" class="d-inline-block align-text-top">
                            Loja Geek
                        </a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerTop" aria-controls="navbarTogglerTop" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarTogglerTop"> 
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link" href="Inicio">Início</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="login.jsp">Login</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="cadastrarCliente.jsp">Cadastre-se</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="ListarProdutos">Cadastro de Produtos</a>
                                </li>
                                <%
                                    Usuario usuarioRepetido = (Usuario) session.getAttribute("usuario");
                                    if (usuarioRepetido != null && usuarioRepetido instanceof Usuario) {
                                %>
                                <li class="nav-item">
                                    <a class="nav-link" href="atualizarDados.jsp">Atualizar dados pessoais</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="deletarUsuario.jsp">Deletar sua conta</a>
                                </li>
                                <%   } %>
                                <%
                                    if (usuarioRepetido != null && usuarioRepetido instanceof Usuario) {
                                %>
                                <li class="nav-item">
                                    <a class="nav-link" href="Logout"><%= usuarioRepetido.getNome() %> Logout</a>
                                </li>
                                <%   } %>
                                
                            </ul>
                            <!--
                            <form class="d-flex" action="Logout" method="get">
                                <div class="form-control bg-transparent border-0 me-2">Hello, <b>User</b>!</div>
                                <button class="btn btn-outline-secondary" type="submit">Logout</button>
                            </form>
                            -->
                        </div>
                    </div>
                </nav>
            </header>
            <main>
                <%
                    String mensagem = (String) request.getAttribute("mensagem");
                    if (mensagem != null) {
                %>
                <div class="d-flex justify-content-center w-100 mt-2">
                    <div class="alert alert-info alert-dismissible fade show" role="alert">
                        <strong><%= mensagem%></strong>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </div>
                <%
                    }
                %>
                <div id="container" class="d-flex justify-content-center w-100">
