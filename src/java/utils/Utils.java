package utils;

import config.Constantes;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Leonardo Oliveira Moreira
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

    /**
     * Método utilizado para formatar um valor double no formato de moeda
     * brasileira
     *
     * @param valor
     * @return
     */
    public static final String formatarMoeda(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(valor);
    }

    /**
     * Método utilizado para gerar o MD5 de uma senha
     *
     * @param senha
     * @return
     * @throws Exception
     */
    public static String gerarMD5(String senha) throws Exception {
        String senhaCriptografada = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = messageDigest.digest(senha.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashInBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            senhaCriptografada = stringBuilder.toString();
            if (senhaCriptografada == null || senhaCriptografada.trim().length() != 32) {
                throw new Exception("Falha ao criar MD5 para essa senha");
            }
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception("Falha ao criar MD5 para essa senha");
        }
        return senhaCriptografada;
    }
    
}
