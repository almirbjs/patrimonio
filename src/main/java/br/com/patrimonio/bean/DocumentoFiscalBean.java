package br.com.patrimonio.bean;

import br.com.patrimonio.dao.DocumentoFiscalDao;
import br.com.patrimonio.dao.FornecedorDao;
import br.com.patrimonio.domain.DocumentoFiscal;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.util.JSFUtil;
import com.mysql.jdbc.Messages;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.AccessibleAttribute;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele o criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBDocumentoFiscal") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class DocumentoFiscalBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<DocumentoFiscal> itens;
    private ArrayList<DocumentoFiscal> itensFiltrados;// Vai armazenar os itens filtrados
    private ArrayList<Fornecedor> itensFornecedor;
    private DocumentoFiscalDao dao = new DocumentoFiscalDao();
    private DocumentoFiscal documentoFiscal = new DocumentoFiscal();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            DocumentoFiscalDao dao = new DocumentoFiscalDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            documentoFiscal = new DocumentoFiscal();
            FornecedorDao fornecedorDao = new FornecedorDao();
            itensFornecedor = fornecedorDao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {
       

        try {

            DocumentoFiscalDao dao = new DocumentoFiscalDao();
         DocumentoFiscal documentoFiscalRetorno = dao.salvar(documentoFiscal);

            if (!"Danfe".equals(documentoFiscal.getTipoDocFiscal())) {

                if (documentoFiscal.getCaminhoTemporario() == null) {
                    JSFUtil.adicionaMensagemErro("É obrigatório adicionar um documento fiscal!");

                    return;
                }
                Path origem = Paths.get(documentoFiscal.getCaminhoTemporario());
                Path destino = Paths.get("C:/Users/AlmirRicardo/Documents/Uploads/"
                        + documentoFiscal.getNumeroDocumentoFiscal() + documentoFiscal.getFornecedor().getCodigo()+ ".png");
                Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

            }
            itens = dao.listar();
            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");

            // Quando salvar um novo objeto ele vai atualizar a minha tabela
            // automaticamente
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemErro("Erro ao tentar salvar.");
        }
    }

    public void prepararEditar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            documentoFiscal = new DocumentoFiscal();
            FornecedorDao fornecedorDao = new FornecedorDao();

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            DocumentoFiscalDao dao = new DocumentoFiscalDao();

           
                Path origem = Paths.get(documentoFiscal.getCaminhoTemporario());
                Path destino = Paths.get("C:/Users/AlmirRicardo/Documents/Uploads/"
                        + documentoFiscal.getNumeroDocumentoFiscal() + documentoFiscal.getFornecedor().getCodigo() + ".png");
                Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
 if (!"Danfe".equals(documentoFiscal.getTipoDocFiscal())) {

                if (documentoFiscal.getCaminhoTemporario() == null) {
                    JSFUtil.adicionaMensagemErro("É obrigatório adicionar um documento fiscal!");

                    return;
                }
                
            }

            JSFUtil.adicionaMensagemSucesso("Alterado com sucesso!");

            // Quando salvar um novo objeto ele vai atualizar a minha tabela
            // automaticamente
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemSucesso("Alterado com sucesso!");
        }

    }

    public void excluir() {

        try {

            DocumentoFiscalDao dao = new DocumentoFiscalDao();
            Path arquivo = Paths.get("C:/Users/AlmirRicardo/Documents/Uploads/" + documentoFiscal.getNumeroDocumentoFiscal() + documentoFiscal.getFornecedor().getCodigo() + ".png");
            dao.excluir(documentoFiscal);
            Files.deleteIfExists(arquivo);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (RuntimeException | IOException e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        } catch (Exception ex) {
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }
    }

    public void UploadFileDocumento(FileUploadEvent event) {

        try {
            UploadedFile arquivoUpload = event.getFile();
            Path arquivoTemp = Files.createTempFile(null, null);
            Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
            documentoFiscal.setCaminhoTemporario(arquivoTemp.toString());

            JSFUtil.adicionaMensagemSucesso("Upload realizado com sucesso!");
        } catch (IOException ex) {
            JSFUtil.adicionaMensagemErro("Ocorreu um erro ao tentar realizar o upload do arquivo");
            ex.printStackTrace();
        }

    }

    public void fornecedorSelecionado(SelectEvent event) {
        Fornecedor fornecedor = (Fornecedor) event.getObject();
        documentoFiscal.setFornecedor(fornecedor);
        FornecedorDao fornecedorDao = new FornecedorDao();
        itensFornecedor = fornecedorDao.listar();

    }

    public ArrayList<DocumentoFiscal> getItens() {
        return itens;
    }

    public void setItens(ArrayList<DocumentoFiscal> itens) {
        this.itens = itens;
    }

    public ArrayList<DocumentoFiscal> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<DocumentoFiscal> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Fornecedor> getComboFornecedor() {
        return itensFornecedor;
    }

    public void setComboFornecedor(ArrayList<Fornecedor> comboFornecedor) {
        this.itensFornecedor = comboFornecedor;
    }

    public DocumentoFiscalDao getDao() {
        return dao;
    }

    public void setDao(DocumentoFiscalDao dao) {
        this.dao = dao;
    }

    public DocumentoFiscal getDocumentoFiscal() {
        return documentoFiscal;
    }

    public void setDocumentoFiscal(DocumentoFiscal documentoFiscal) {
        this.documentoFiscal = documentoFiscal;
    }

}
