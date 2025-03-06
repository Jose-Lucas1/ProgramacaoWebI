<%@page import="modelo.compra.Compra"%>
<%@page import="utils.Utils"%>
<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@include file="../../../cabecalho.jsp" %>
<div class="w-100 m-auto">
    <h1 class="fw-bolder fs-4">Compras dos clientes</h1>
<%
    List<Compra> compras = (List<Compra>) request.getAttribute("compras");
    List<String> nomesUsuarios = (List<String>) request.getAttribute("nomesUsuarios");
    
    if (compras != null && !compras.isEmpty()) {
%>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Id do Usuario</th>
                    <th scope="col">Total de Produtos Comprados</th>
                    <th scope="col">Total Pago</th>
                    <th scope="col">Data</th>
                    <th scope="col">&nbsp;</th>
                </tr>
            </thead>
            <tbody>
    <%
        int i = 1;
        for (int n = 0; n < compras.size(); n++) {
    %>
                <tr>
                    <th scope="row"><%= i++%></th>
                    <td><%= nomesUsuarios.get(n)%></td>
                    <td><%= compras.get(n).getId_usuario()%></td>
                    <td><%= compras.get(n).getQuantidade_total_de_produtos_comprados()%></td>
                    <td>R$ <%= Utils.formatarMoeda(compras.get(n).getValortotal())%></td>
                    <td><%= compras.get(n).getData()%></td>
                    <td class="d-flex justify-content-end gap-2"><a href="<%= request.getContextPath() %>/RemoverCompraServlet?id=<%= compras.get(n).getId() %>" role="button" class="btn btn-danger">Remover</a></td>
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
    <p>Nenhuma compra foi realizada</p>
<%
    }    
%>
</div>
<%@include file="../../../rodape.jsp" %>