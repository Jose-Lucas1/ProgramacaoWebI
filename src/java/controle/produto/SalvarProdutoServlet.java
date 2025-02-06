package controle.produto;

import config.Constantes;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.ProdutoDAO;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;


/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para implementar a ação de inserir ou atualizar um produto
 */
public class SalvarProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                boolean sucesso = false;

                int id = -1;
                String descricao = null;
                double preco = -1;
                int quantidade = -1;
                FileItem foto = null;

                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(new File(Constantes.UPLOAD_FOTO_PRODUTO_DIRETORIO));
                ServletFileUpload fileUpload = new ServletFileUpload(factory);
                List<FileItem> fileItems = fileUpload.parseRequest(new ServletRequestContext(request));
                for (int i = 0; fileItems != null && i < fileItems.size(); i++) {
                    FileItem item = (FileItem) fileItems.get(i);
                    if (item.isFormField() && item.getFieldName().equals("id")) {
                        id = Integer.parseInt(item.getString());
                        continue;
                    }
                    if (item.isFormField() && item.getFieldName().equals("descricao")) {
                        descricao = item.getString();
                        continue;
                    }
                    if (item.isFormField() && item.getFieldName().equals("preco")) {
                        preco = Double.parseDouble(item.getString());
                        continue;
                    }
                    if (item.isFormField() && item.getFieldName().equals("quantidade")) {
                        quantidade = Integer.parseInt(item.getString());
                        continue;
                    }
                    if (!item.isFormField() && item.getFieldName().equals("foto")) {
                        foto = item;
                        continue;
                    }
                }
                ProdutoDAO produtoDAO = new ProdutoDAO();
                if (id != -1) {
                    sucesso = produtoDAO.atualizar(descricao, preco, quantidade, foto, id);
                } else {
                    sucesso = produtoDAO.inserir(descricao, preco, quantidade, foto);
                }
                request.setAttribute("mensagem", (sucesso ? "Produto salvo com sucesso" : "Não foi possível salvar o produto"));
            } catch (Exception ex) {
                request.setAttribute("mensagem", ex.getMessage());
            }
        } else {
            request.setAttribute("mensagem", "Transferência de arquivo não suportada");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/administrador/ListarProdutos");
        dispatcher.forward(request, response);
    }

}
