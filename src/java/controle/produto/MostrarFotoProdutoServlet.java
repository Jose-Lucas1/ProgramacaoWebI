package controle.produto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para exibir uma foto de um determinado produto
 */
public class MostrarFotoProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.obter(id);
        File foto = new File(produto.getFoto());

        // Verifica se a foto existe
        if (foto == null || !foto.exists() || !foto.isFile()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Foto não encontrada");
            return;
        }


        // Identifica o MIME type do arquivo
        String mimeType = Files.probeContentType(foto.toPath());
        if (mimeType == null || !mimeType.startsWith("image/")) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Tipo de arquivo inválido");
            return;
        }

        // Define o cabeçalho da resposta
        response.setContentType(mimeType);
        response.setContentLengthLong(foto.length());

        // Renderiza a foto no output stream da resposta
        try (InputStream fileInputStream = new FileInputStream(foto);
             OutputStream responseOutputStream = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                responseOutputStream.write(buffer, 0, bytesRead);
            }
        } 
    }
    
}
