<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@page import="modelo.categoria.Categoria"%>
<%@include file="../../../cabecalho.jsp" %>
<div class="w-50 m-auto">
    <h1 class="fw-bolder fs-4">Relat�rios Gerais</h1>

    <form action="<%= request.getContextPath() %>/administrador/RelatorioProdutosFaltantesEmEstoque" method="post" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="dataInicio" class="form-label">Selecione o per�odo na qual o relat�rio ser� gerado</label>
        </div>
        
        <div class="mb-3">
            <label for="dataInicio" class="form-label">Data In�cio</label>
            <input type="date" class="form-control" id="dataInicio" name="dataInicio" required>
            <div class="invalid-feedback">Por favor, selecione uma data inicial</div>
        </div>

        <div class="mb-3">
            <label for="dataFim" class="form-label">Data Fim</label>
            <input type="date" class="form-control" id="dataFim" name="dataFim" required>
            <div class="invalid-feedback">Por favor, selecione uma data final</div>
        </div>

        <div class="mb-3 text-center">
            <input type="submit" class="btn btn-primary" value="Gerar Relat�rio"/>
        </div>
    </form>
</div>
<%@include file="../../../rodape.jsp" %>
