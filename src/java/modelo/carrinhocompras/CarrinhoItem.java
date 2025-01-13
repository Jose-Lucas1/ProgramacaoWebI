package modelo.carrinhocompras;

import modelo.produto.Produto;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que representa um item do carrinho de compras
 */
public class CarrinhoItem {

    private Produto produto;
    private int quantidade;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
