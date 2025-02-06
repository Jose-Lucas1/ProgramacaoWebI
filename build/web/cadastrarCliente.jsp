<%@include file="cabecalho.jsp" %>
<div class="w-50 m-auto">
    <h1 class="fw-bolder fs-4">Cadastro de Cliente</h1>
    <form action="<%= request.getContextPath() %>/InserirCliente" method="post" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="nomeInput" class="form-label">Nome</label>
            <input type="text" class="form-control" id="nomeInput" name="nome" placeholder="Entre com seu nome" required>
            <div class="invalid-feedback">Por favor digite seu nome</div>
        </div>
        <div class="mb-3">
            <label for="enderecoInput" class="form-label">Endereço</label>
            <input type="text" class="form-control" id="enderecoInput" name="endereco" placeholder="Entre com seu endereço" required>
            <div class="invalid-feedback">Por favor digite seu endereço</div>
        </div>
        <div class="mb-3">
            <label for="emailInput" class="form-label">Email</label>
            <input type="email" class="form-control" id="emailInput" name="email" placeholder="Entre com seu email" required>
            <div class="invalid-feedback">Por favor digite seu email</div>
        </div>
        <div class="mb-3">
            <label for="loginInput" class="form-label">Login</label>
            <input type="text" class="form-control" id="loginInput" name="login" placeholder="Entre com seu login" required>
            <div class="invalid-feedback">Por favor digite seu login</div>
        </div>
        <div class="mb-3">
            <label for="senhaInput" class="form-label">Senha</label>
            <input type="password" class="form-control" id="senhaInput" name="senha" placeholder="Entre com sua senha" required>
            <div class="invalid-feedback">Por favor digite sua senha</div>            
        </div>
        <div class="mb-3 text-center">
            <input type="submit" class="btn btn-primary" value="Inserir"/>
        </div>
    </form>
</div>
<%@include file="rodape.jsp" %>
