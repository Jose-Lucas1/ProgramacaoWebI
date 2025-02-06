package controle.carrinhocompras;

import config.Constantes;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinhocompras.CarrinhoCompras;
import utils.Utils;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para implementar a ação de remover um produto no carrinho
 * de compras
 */
public class RemoverItemCarrinhoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int produtoId = Integer.parseInt(request.getParameter("produtoId"));
        
        Cookie cookie = Utils.obterCookieCarrrinhoCompras(request);
        if (cookie == null) {
            cookie = new Cookie(Constantes.COOKIE_CARRINHO_COMPRAS_CHAVE, "");
        }
        cookie = CarrinhoCompras.removerItem(produtoId, cookie);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio");
        dispatcher.forward(request, response);
    }

}
