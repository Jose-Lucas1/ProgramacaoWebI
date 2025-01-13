package utils;

import config.Constantes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * 
 *
 * Classe para disponibilizar métodos utilitários para a aplicação
 */
public final class Utils {

    private Utils() {

    }

    /**
     * Método utilizado para recuperar o objeto cookie do carrinho de compras
     * por meio de uma requisição
     *
     * @param request
     * @return
     */
    public static final Cookie obterCookieCarrrinhoCompras(HttpServletRequest request) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(Constantes.COOKIE_CARRINHO_COMPRAS_CHAVE)) {
                cookie = cookies[i];
                break;
            }
        }
        return cookie;
    }

}
