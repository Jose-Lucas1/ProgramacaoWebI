<%@page import="modelo.categoria.Categoria"%>
<%@page import="utils.Utils"%>
<%@page import="java.util.List"%>
<%@include file="../../../cabecalho.jsp" %>
<div class="w-100 m-auto">
    <h1 class="fw-bolder fs-4">Cadastro de Categorias</h1>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    if (categorias != null && !categorias.isEmpty()) {
%>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">&nbsp;</th>
                </tr>
            </thead>
            <tbody>
    <%
        int i = 1;
        for (Categoria categoria : categorias) {
    %>
                <tr>
                    <th scope="row"><%= i++%></th>
                    <td><%= categoria.getDescricao()%></td>
                    <td class="d-flex justify-content-end gap-2"><a href="<%= request.getContextPath() %>/administrador/FormCategoria?id=<%= categoria.getId() %>" role="button" class="btn btn-primary">Atualizar</a><a href="<%= request.getContextPath() %>/administrador/RemoverCategoria?id=<%= categoria.getId() %>" role="button" class="btn btn-danger">Remover</a></td>
                </tr>
    <%
        }
    %>
            <tbody>
        </table>
    </div>
<%
    } else {
%>
    <p>Não existem categorias disponíveis</p>
<%
    }    
%>
    <div class="clearfix"></div>
    <a href="<%= request.getContextPath() %>/administrador/FormCategoria" class="btn btn-primary">Nova Categoria</a>
    <div class="clearfix mb-3"></div>
</div>
<%@include file="../../../rodape.jsp" %>