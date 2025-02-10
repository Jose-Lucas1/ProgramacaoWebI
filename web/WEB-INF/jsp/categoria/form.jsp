<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@page import="modelo.categoria.Categoria"%>
<%@include file="../../../cabecalho.jsp" %>
<div class="w-50 m-auto">
    <h1 class="fw-bolder fs-4">Cadastro de Categorias</h1>
    <%
        Categoria categoria = (Categoria) request.getAttribute("categoria");
    %>
    <form action="<%= request.getContextPath() %>/administrador/SalvarCategoria" method="post" class="needs-validation" novalidate>
        <% if (categoria != null) {%>
        <input type="hidden" name="id" value="<%= (categoria == null ? "" : categoria.getId())%>" />
        <% }%>
        <div class="mb-3">
            <label for="descricao" class="form-label">Descrição</label>
            <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Entre com a descrição" value="<%= (categoria == null ? "" : categoria.getDescricao())%>" required>
            <div class="invalid-feedback">Por favor digite a descrição</div>
        </div>
        <div class="mb-3 text-center">
            <a href="<%= request.getContextPath() %>/administrador/ListarCategorias" role="button" class="btn btn-secondary">Voltar</a>
            <input type="submit" class="btn btn-primary" value="Salvar"/>
        </div>
    </form>
</div>
<%@include file="../../../rodape.jsp" %>
