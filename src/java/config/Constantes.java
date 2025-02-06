package config;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe para carregar as constantes da aplicação
 */
public final class Constantes {

    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/pw1trabalhofinalV2";
    public static final String JDBC_USUARIO = "postgres";
    public static final String JDBC_SENHA = "engenharia2";

    public static final String COOKIE_CARRINHO_COMPRAS_CHAVE = "smdecommerce.carrinhocompras";
    public static final String CARRINHO_COMPRAS_SEPARADOR_ITENS = "@";
    public static final String CARRINHO_COMPRAS_SEPARADOR_CAMPOS = "#";
    
    public static final String UPLOAD_FOTO_PRODUTO_DIRETORIO = "C:\\Users\\josep\\Downloads\\Fotospw1";
    //C:\Users\josep\Downloads\Fotospw1
    
    private Constantes() {

    }

}
