package controle.produto;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.ProdutoDAO;

/**
 *
 * 
 *
 * Classe de controle para implementar a ação de inserir ou atualizar um produto
 */
public class SalvarProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada de dados */
        String id = request.getParameter("id");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        /* processamento de dados */
        boolean sucesso = false;
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (id != null && id.trim().length() > 0) {
            sucesso = produtoDAO.atualizar(descricao, preco, quantidade, "", Integer.parseInt(id));
        } else {
            sucesso = produtoDAO.inserir(descricao, preco, quantidade, "");
        }
        request.setAttribute("mensagem", (sucesso ? "Produto salvo com sucesso" : "Não foi possível salvar o produto"));
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListarProdutos");
        dispatcher.forward(request, response);
    }

}
