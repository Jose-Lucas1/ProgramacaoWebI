<%@page import="modelo.usuario.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null && usuario instanceof Usuario) {
%>
<%@include file="cabecalho.jsp" %>
<div class="w-50 m-auto">
    <div class="alert alert-info alert-dismissible fade show mt-2" role="alert">
        Voce tem certeza que quer <strong>Deletar</strong>  sua conta <%= usuario.getNome() %>?
    </div>
    <div class="mb-3 text-center">
        <a role="button" class="btn btn-primary" href="Inicio">Voltar Inicio</a>
        <form action="deletarUsuarioServlet" method="post" style="display: inline;">
        <button type="submit" class="btn btn-danger">
            Deletar sua conta
        </button>
        </form>
    </div>
</div>
<%@include file="rodape.jsp" %>
<%
    } else {
        request.setAttribute("mensagem", "Você não tem permissão para acessar este recurso");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
%>