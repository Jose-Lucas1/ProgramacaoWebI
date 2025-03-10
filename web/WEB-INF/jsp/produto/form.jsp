<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@page import="modelo.categoria.Categoria"%>
<%@include file="../../../cabecalho.jsp" %>
<div class="w-50 m-auto">
    <h1 class="fw-bolder fs-4">Cadastro de Produtos</h1>
    <%
        Produto produto = (Produto) request.getAttribute("produto");
        List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
        
        // debug categorias existentes
        if (categorias != null) {
            for (Categoria categoria : categorias) {
                getServletContext().log("Categoria ID: " + categoria.getId() + " - Descri��o: " + categoria.getDescricao());
            }
        } else {
            getServletContext().log("Categorias n�o encontradas.");
        }
    %>
    <form action="<%= request.getContextPath() %>/administrador/SalvarProduto" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
        <% if (produto != null) {%>
        <input type="hidden" name="id" value="<%= (produto == null ? "" : produto.getId())%>" />
        <% }%>
        <div class="mb-3">
            <label for="descricaoInput" class="form-label">Descri��o</label>
            <input type="text" class="form-control" id="descricaoInput" name="descricao" placeholder="Entre com a descri��o" value="<%= (produto == null ? "" : produto.getDescricao())%>" required>
            <div class="invalid-feedback">Por favor digite a descri��o</div>
        </div>
        <div class="mb-3">
            <label for="precoInput" class="form-label">Pre�o</label>
            <input type="text" class="form-control" id="precoInput" name="preco" placeholder="Entre com o pre�o" value="<%= (produto == null ? "" : produto.getPreco())%>" required>
            <div class="invalid-feedback">Por favor digite o pre�o</div>
        </div>
        <div class="mb-3">
            <label for="quantidadeInput" class="form-label">Quantidade</label>
            <input type="text" class="form-control" id="quantidadeInput" name="quantidade" placeholder="Entre com a quantidade" value="<%= (produto == null ? "" : produto.getQuantidade())%>" required>
            <div class="invalid-feedback">Por favor digite a quantidade</div>
        </div>
        <div class="mb-3">
            <label for="categoriaSelect" class="form-label">Categoria</label>
            <select class="form-control" id="categoriaSelect" name="categoriaId" required>
                <option value="" disabled <%= (produto == null ? "selected" : "") %>>Selecione a categoria</option>
                <% if (categorias != null) { 
                    for (Categoria categoria : categorias) { %>
                        <option value="<%= categoria.getId() %>" 
                            <%= (produto != null && produto.getCategoria() != null && 
                                produto.getCategoria().getId() == categoria.getId()) ? "selected" : "" %>>
                            <%= categoria.getDescricao() %>
                        </option>                    <% } 
                } %>
            </select>
            <div class="invalid-feedback">Por favor selecione a categoria</div>
        </div>
        <div class="mb-3">
            <label for="fotoInput" class="form-label">Foto</label>
            <input type="file" class="form-control" id="fotoInput" name="foto">
            <div class="invalid-feedback">Por favor informe a foto</div>
        </div>
        <div class="mb-3 text-center">
            <a href="<%= request.getContextPath() %>/administrador/ListarProdutos" role="button" class="btn btn-secondary">Voltar</a>
            <input type="submit" class="btn btn-primary" value="Salvar"/>
        </div>
    </form>
</div>
<%@include file="../../../rodape.jsp" %>
