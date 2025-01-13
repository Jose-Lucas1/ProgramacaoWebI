<%@include file="cabecalho.jsp" %>
<div class="w-50 m-auto">
    <h1 class="fw-bolder fs-4">Identificação do Usuário</h1>
    <form action="Login" method="post" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="inputLogin" class="form-label">Login</label>
            <div class="input-group">
                <div class="input-group-text">@</div>
                <input type="text" name="login" class="form-control" id="inputLogin" aria-describedby="loginHelp" placeholder="Login" required>
                <div class="invalid-feedback">Por favor digite seu login</div>
            </div>
        </div>
        <div class="mb-3">
            <label for="inputPassword" class="form-label">Senha</label>
            <input type="password" name="senha" class="form-control" id="inputPassword" placeholder="Senha" required>
            <div class="invalid-feedback">Por favor digite sua senha</div>
        </div>
        <div class="mb-3 text-center">
            <button type="submit" class="btn btn-primary">Entrar</button>
        </div>
    </form>
</div>
<%@include file="rodape.jsp" %>