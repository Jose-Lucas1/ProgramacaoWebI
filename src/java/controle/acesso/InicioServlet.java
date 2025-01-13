package controle.acesso;

import config.Constantes;
import utils.Utils;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinhocompras.CarrinhoCompras;
import modelo.carrinhocompras.CarrinhoItem;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para implementar a ação de montar a página inicial
 */
public class InicioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* obtenção dos produtos disponíveis em estoque */
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtosEmEstoque = produtoDAO.obterProdutosEmEstoque();
        request.setAttribute("produtosEmEstoque", produtosEmEstoque);
        /* obtenção dos itens do carrinho de compras */
        Cookie cookie = Utils.obterCookieCarrrinhoCompras(request);
        if (cookie == null) {
            cookie = new Cookie(Constantes.COOKIE_CARRINHO_COMPRAS_CHAVE, "");
            response.addCookie(cookie);
        }
        List<CarrinhoItem> itensCarrinhoCompras = CarrinhoCompras.obter(cookie);
        request.setAttribute("itensCarrinhoCompras", itensCarrinhoCompras);
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

}
