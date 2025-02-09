<%@page import="utils.Utils"%>
<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@include file="../../../cabecalho.jsp" %>
<div class="w-100 m-auto">
    <h1 class="fw-bolder fs-4">Cadastro de Produtos</h1>
<%
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    if (produtos != null && !produtos.isEmpty()) {
%>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Foto</th>
                    <th scope="col">Descrição</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Preço</th>
                    <th scope="col">Quantidade</th>
                    <th scope="col">&nbsp;</th>
                </tr>
            </thead>
            <tbody>
    <%
        int i = 1;
        for (Produto produto : produtos) {
    %>
                <tr>
                    <th scope="row"><%= i++%></th>
                    <td>
                        <% if (produto.getFoto() != null && produto.getFoto().trim().length() > 0) { %>
                        <img src="<%= request.getContextPath() %>/MostrarFotoProduto?id=<%= produto.getId() %>" class="product-img-list" />
                        <% } %>
                    </td>
                    <td><%= produto.getDescricao()%></td>
                    <td><%= produto.getCategoria() != null ? produto.getCategoria().getDescricao() : ""%></td>
                    <td>R$ <%= Utils.formatarMoeda(produto.getPreco())%></td>
                    <td><%= produto.getQuantidade()%></td>
                    <td class="d-flex justify-content-end gap-2"><a href="<%= request.getContextPath() %>/administrador/FormProduto?id=<%= produto.getId() %>" role="button" class="btn btn-primary">Atualizar</a><a href="<%= request.getContextPath() %>/administrador/RemoverProduto?id=<%= produto.getId() %>" role="button" class="btn btn-danger">Remover</a></td>
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
    <p>Não existem produtos disponíveis</p>
<%
    }    
%>
    <div class="clearfix"></div>
    <a href="<%= request.getContextPath() %>/administrador/FormProduto" class="btn btn-primary">Novo Produto</a>
    <div class="clearfix mb-3"></div>
</div>
<%@include file="../../../rodape.jsp" %>