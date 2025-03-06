<%@page import="utils.Utils"%>
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
            <% if (produto.getFoto() != null && produto.getFoto().length() > 0) { %>
            <img class="card-img-top" src="<%= request.getContextPath() %>/MostrarFotoProduto?id=<%= produto.getId() %>" alt="Card image cap">
            <% } %>
            <div class="card-body">
                <h5 class="card-title"><%= produto.getDescricao()%></h5>
                <p class="card-text">Preço: <%= produto.getPreco()%></p>
                <button type="button" class="btn btn-secondary"><%= produto.getCategoria() != null ? produto.getCategoria().getDescricao() : ""%></button>
            </div>
            <div class="card-footer">
                <small class="text-muted"><a role="button" class="btn btn-primary" href="<%= request.getContextPath() %>/AdicionarItemCarrinho?produtoId=<%= produto.getId()%>&quantidade=1">Adicionar ao carrinho</a></small>
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
            double totalCarrinhoCompras = 0;
            int totalDeProdutosComprados = 0;
    %>
    <div class="clearfix"></div>
    <h1 class="fw-bolder fs-4 mt-3">Carrinho de Compras</h1>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Foto</th>
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
                    <td>
                        <% if (item.getProduto().getFoto() != null && item.getProduto().getFoto().trim().length() > 0) { %>
                        <img src="<%= request.getContextPath() %>/MostrarFotoProduto?id=<%= item.getProduto().getId() %>" class="product-img-list" />
                        <% } %>
                    </td>
                    <td><%= item.getProduto().getDescricao()%></td>
                    <td>R$ <%= Utils.formatarMoeda(item.getProduto().getPreco())%></td>
                    <td><%= item.getQuantidade()%></td>
                    <td class="d-flex justify-content-end"><a role="button" class="btn btn-danger" href="<%= request.getContextPath() %>/RemoverItemCarrinho?produtoId=<%= item.getProduto().getId() %>">Remover<a/></td>
                </tr>
                <%
                        totalCarrinhoCompras += (item.getQuantidade() * item.getProduto().getPreco());
                        totalDeProdutosComprados += item.getQuantidade();
                    }
                %>
            <tbody>
        </table>
    </div>
    <h1 class="fw-bolder fs-4 mb-3">Total R$: <%= Utils.formatarMoeda(totalCarrinhoCompras) %></h1>
    <% if (usuario != null && usuario instanceof Usuario && !usuario.isAdministrador()) {
           System.out.println("3");
           session.setAttribute("itensCarrinhoCompras", itensCarrinhoCompras);
    %>
    <form action="<%= request.getContextPath() %>/InserirCompraServlet" method="post">
    <input type="hidden" name="totalCarrinhoCompras" value="<%= totalCarrinhoCompras %>">
    <input type="hidden" name="totalDeProdutosComprados" value="<%= totalDeProdutosComprados %>">
    <button type="submit" class="btn btn-success btn-lg">Comprar</button>
    </form>
    <% } %>

    <%
        }
    %>
</div>
<%@include file="rodape.jsp" %>