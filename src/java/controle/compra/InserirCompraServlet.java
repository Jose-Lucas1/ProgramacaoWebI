package controle.compra;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.carrinhocompras.CarrinhoItem;
import modelo.compra.Compra;
import modelo.compra.CompraDAO;
import modelo.produto.ProdutoDAO;
import modelo.usuario.Usuario;


public class InserirCompraServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        int idUsuario = usuario.getId();
        int quantidade_total_de_produtos_comprados = Integer.parseInt(request.getParameter("totalDeProdutosComprados"));
        double valortotal = Double.parseDouble(request.getParameter("totalCarrinhoCompras"));
        
        /* processamento de dados */
        CompraDAO compraDAO = new CompraDAO();
        Compra novaCompra = new Compra(
                        idUsuario,
                        quantidade_total_de_produtos_comprados,
                        valortotal,
                        LocalDate.now()
                    );
        
        List<CarrinhoItem> itensCarrinhoCompras = (List<CarrinhoItem>) session.getAttribute("itensCarrinhoCompras");

        ProdutoDAO produtoDAO = new ProdutoDAO();
        for (CarrinhoItem item : itensCarrinhoCompras) {
            produtoDAO.atualizarQuantidadeProduto(item.getProduto().getId(),
                                                  item.getProduto().getQuantidade()-item.getQuantidade());
        }   
        
        boolean sucesso = compraDAO.adicionarCompra(novaCompra);
        request.setAttribute("mensagem", (sucesso ? "Compra realizada com sucesso" : "Não foi possível realizar a compra"));
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio");
        
        dispatcher.forward(request, response);
    }

}
