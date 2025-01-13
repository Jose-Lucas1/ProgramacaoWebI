package config;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe para carregar as constantes da aplicação
 */
public final class Constantes {

    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/pw1trabalhofinal";
    public static final String JDBC_USUARIO = "postgres";
    public static final String JDBC_SENHA = "engenharia2";

    public static final String COOKIE_CARRINHO_COMPRAS_CHAVE = "smdecommerce.carrinhocompras";
    public static final String CARRINHO_COMPRAS_SEPARADOR_ITENS = "@";
    public static final String CARRINHO_COMPRAS_SEPARADOR_CAMPOS = "#";

    private Constantes() {

    }

}
