<%@page import="modelo.usuario.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null && usuario instanceof Usuario) {
%>
<%@include file="cabecalho.jsp" %>
<div class="w-50 m-auto">
    <div class="alert alert-info alert-dismissible fade show mt-2" role="alert">
        <strong>P�gina Protegida!</strong> Ol�, <%= usuario.getNome() %>!
    </div>
    <div class="mb-3 text-center">
        <a role="button" class="btn btn-primary" href="ListarProdutos">Cadastro de Produtos</a>
        <a role="button" class="btn btn-danger" href="Logout">Sair</a>
    </div>
</div>
<%@include file="rodape.jsp" %>
<%
    } else {
        request.setAttribute("mensagem", "Voc� n�o tem permiss�o para acessar este recurso");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
%>