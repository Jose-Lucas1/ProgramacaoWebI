<%@page import="modelo.carrinhocompras.CarrinhoItem"%>
<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@include file="cabecalho.jsp" %>
<div class="w-100 m-auto">
    <%        
        List<Produto> produtosEmEstoque = (List<Produto>) request.getAttribute("produtosEmEstoque");
        List<CarrinhoItem> itensCarrinhoCompras = (List<CarrinhoItem>) request.getAttribute("itensCarrinhoCompras");
    %>
    <h1 class="fw-bolder fs-4">Produtos Disponíveis</h1>
    <% 
        if (produtosEmEstoque != null && !produtosEmEstoque.isEmpty()) { 
    %>
    <div class="card-group">
        <%
            for (Produto produto : produtosEmEstoque) {
        %>
        <div class="card">
            <!--<img class="card-img-top" src="..." alt="Card image cap">-->
            <div class="card-body">
                <h5 class="card-title"><%= produto.getDescricao()%></h5>
                <p class="card-text">Preço: <%= produto.getPreco()%></p>
            </div>
            <div class="card-footer">
                <small class="text-muted"><a role="button" class="btn btn-primary" href="AdicionarItemCarrinho?produtoId=<%= produto.getId()%>&quantidade=1">Adicionar ao carrinho</a></small>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <% 
        } else { 
    %>
    <p>Não existem produtos disponíveis em estoque</p>
    <%
        }
        if (itensCarrinhoCompras != null && !itensCarrinhoCompras.isEmpty()) { 
    %>
    <div class="clearfix"></div>
    <h1 class="fw-bolder fs-4 mt-3">Carrinho de Compras</h1>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Descrição</th>
                    <th scope="col">Preço</th>
                    <th scope="col">Quantidade</th>
                    <th scope="col">&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int i = 1;
                    for (CarrinhoItem item : itensCarrinhoCompras) {
                %>
                <tr>
                    <th scope="row"><%= i++%></th>
                    <td><%= item.getProduto().getDescricao()%></td>
                    <td><%= item.getProduto().getPreco()%></td>
                    <td><%= item.getQuantidade()%></td>
                    <td class="d-flex align-items-end flex-column"><a role="button" class="btn btn-danger" href="">Remover<a/></td>
                </tr>
                <%
                    }
                %>
            <tbody>
        </table>
    </div>
    <%
        }
    %>
</div>
<%@include file="rodape.jsp" %>