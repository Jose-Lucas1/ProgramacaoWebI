<%@page import="modelo.compra.Compra"%>
<%@page import="utils.Utils"%>
<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@include file="../../../cabecalho.jsp" %>
<div class="w-100 m-auto">
    <h1 class="fw-bolder fs-4">Histórico de Compras</h1>
<%
    List<Compra> compras = (List<Compra>) request.getAttribute("compras");
    if (compras != null && !compras.isEmpty()) {
%>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Data</th>
                    <th scope="col">Quantidade de produtos comprados</th>
                    <th scope="col">ValorTotalPago</th>
                    <th scope="col">&nbsp;</th>
                </tr>
            </thead>
            <tbody>
    <%
        int i = 1;
        for (Compra compra : compras) {
    %>
                <tr>
                    <th scope="row"><%= i++%></th>
                    <td><%= compra.getData()%></td>
                    <td><%= compra.getQuantidade_total_de_produtos_comprados()%></td>
                    <td>R$ <%= Utils.formatarMoeda(compra.getValortotal())%></td>
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