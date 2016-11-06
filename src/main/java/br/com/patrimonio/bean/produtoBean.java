package br.com.patrimonio.bean;

import br.com.patrimonio.dao.CategoriaDao;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.patrimonio.dao.ProdutoDao;
import br.com.patrimonio.dao.FornecedorDao;
import br.com.patrimonio.domain.Categoria;
import br.com.patrimonio.domain.Produto;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.util.JSFUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBProduto") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class produtoBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Produto> itens;
    private ArrayList<Produto> itensFiltrados;// Vai armazenar os itens filtrados
    private ArrayList<Fornecedor> itensFornecedores; // Carrega o combox com o nome dos
    private ArrayList<Categoria> comboCategoria;										// paises
    private ProdutoDao dao = new ProdutoDao();
    private Produto produto = new Produto();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            ProdutoDao dao = new ProdutoDao();
            setItens(dao.listar());

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            setProduto(new Produto());
            FornecedorDao dao = new FornecedorDao();
            setComboFornecedores(dao.listar());
            CategoriaDao daoCategoria = new CategoriaDao();
            setComboCategoria(daoCategoria.listar());
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

            
        
        try {

            ProdutoDao dao = new ProdutoDao();
            Produto ProdutoRetorno = dao.salvar(getProduto());

            Path origem = Paths.get(getProduto().getCaminhoTemporarioProduto());
            Path destino = Paths.get("C:/Users/AlmirRicardo/Documents/Produtos/"
                    + ProdutoRetorno.getCodigo() + ".png");
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

            setItens(dao.listar());
            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");

            // Quando salvar um novo objeto ele vai atualizar a minha tabela
            // automaticamente
        } catch (RuntimeException e) {
            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");
            setItens(dao.listar());
        } catch (IOException ex) {
            Logger.getLogger(produtoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void prepararEditar() {
        // metodo criado para resolver o problema do objeto= null

        try {
            setProduto(new Produto());
            FornecedorDao dao = new FornecedorDao();
            setComboFornecedores(dao.listar());
            CategoriaDao daoCategoria = new CategoriaDao();
            setComboCategoria(daoCategoria.listar());
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            ProdutoDao dao = new ProdutoDao();
            dao.alterar(getProduto());

            Path origem = Paths.get(getProduto().getCaminhoTemporarioProduto());
            Path destino = Paths.get("C:/Users/AlmirRicardo/Documents/Produtos/"
                    + produto.getCodigo() + ".png");
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

            setItens(dao.listar());
            JSFUtil.adicionaMensagemSucesso("Alterado com sucesso!");

            // Quando salvar um novo objeto ele vai atualizar a minha tabela
            // automaticamente
        } catch (RuntimeException e) {
            JSFUtil.adicionaMensagemSucesso("Alterado com sucesso!");
            setItens(dao.listar());
        } catch (IOException ex) {
            Logger.getLogger(produtoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void excluir() {

        try {

            ProdutoDao dao = new ProdutoDao();
            Path arquivo = Paths.get("C:/Users/AlmirRicardo/Documents/Produtos/" + produto.getCodigo() + ".png");
            dao.excluir(produto);
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

    public void UploadImagem(FileUploadEvent event) {

        try {
            UploadedFile arquivoUpload = event.getFile();
            Path arquivoTemp = Files.createTempFile(null, null);
            Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
            getProduto().setCaminhoTemporarioProduto(arquivoTemp.toString());

            JSFUtil.adicionaMensagemSucesso("Upload realizado com sucesso!");
        } catch (IOException ex) {
            JSFUtil.adicionaMensagemErro("Ocorreu um erro ao tentar realizar o upload do arquivo");
            ex.printStackTrace();
        }

    }
    
    public void produtoSelecionado(SelectEvent event) {
        Fornecedor fornecedor = (Fornecedor) event.getObject();
        produto.setFornecedor(fornecedor);
        FornecedorDao fornecedorDao = new FornecedorDao();
        itensFornecedores = fornecedorDao.listar();

    }


    public ArrayList<Produto> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Produto> itens) {
        this.itens = itens;
    }

    public ArrayList<Produto> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Fornecedor> getComboFornecedores() {
        return itensFornecedores;
    }

    public void setComboFornecedores(ArrayList<Fornecedor> comboFornecedores) {
        this.itensFornecedores = comboFornecedores;
    }

    public ArrayList<Categoria> getComboCategoria() {
        return comboCategoria;
    }

    public void setComboCategoria(ArrayList<Categoria> comboCategoria) {
        this.comboCategoria = comboCategoria;
    }

    public ProdutoDao getDao() {
        return dao;
    }

    public void setDao(ProdutoDao dao) {
        this.dao = dao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
