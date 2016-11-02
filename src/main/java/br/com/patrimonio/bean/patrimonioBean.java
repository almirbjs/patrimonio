package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.patrimonio.dao.DocumentoFiscalDao;
import br.com.patrimonio.dao.FornecedorDao;
import br.com.patrimonio.dao.PatrimonioDao;
import br.com.patrimonio.dao.ProdutoDao;
import br.com.patrimonio.dao.SetorDao;
import br.com.patrimonio.domain.DocumentoFiscal;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.domain.Patrimonio;
import br.com.patrimonio.domain.Produto;
import br.com.patrimonio.domain.Setor;
import br.com.patrimonio.util.JSFUtil;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBPatrimonio") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class patrimonioBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Patrimonio> itens;
    private ArrayList<Patrimonio> itensFiltrados;// Vai armazenar os itens
    // filtrados
    private ArrayList<Setor> comboSetores; // Carrega o combox com o nome dos
    private ArrayList<Fornecedor> comboFornecedores;										// setores

    private ArrayList<Produto> comboProduto;//
    private PatrimonioDao dao = new PatrimonioDao();
    private Patrimonio patrimonio = new Patrimonio();
    private DocumentoFiscal documentoFiscal = new DocumentoFiscal();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    public PatrimonioDao getDao() {
        return dao;
    }

    public ArrayList<Produto> getComboProduto() {
        return comboProduto;
    }

    public void setComboProduto(ArrayList<Produto> comboProduto) {
        this.comboProduto = comboProduto;
    }

    public ArrayList<Setor> getComboSetores() {
        return comboSetores;
    }

    public void setComboSetores(ArrayList<Setor> comboSetores) {
        this.comboSetores = comboSetores;
    }

    public ArrayList<Patrimonio> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Patrimonio> itens) {
        this.itens = itens;
    }

    public ArrayList<Patrimonio> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Patrimonio> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public void setDao(PatrimonioDao dao) {
        this.dao = dao;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    @PostConstruct
    public void prepararPesquisa() {
        try {
            PatrimonioDao dao = new PatrimonioDao();
            setItens(dao.listar());

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            setPatrimonio(new Patrimonio());
            FornecedorDao fornecedorDao = new FornecedorDao();
            comboFornecedores = fornecedorDao.listar();
            SetorDao dao = new SetorDao();
            setComboSetores(dao.listar());

            ProdutoDao daoProduto = new ProdutoDao();
            setComboProduto(daoProduto.listar());
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {

            DocumentoFiscalDao documentoFiscalDao = new DocumentoFiscalDao();
            patrimonio.setDocumentoFiscal(documentoFiscal);
            documentoFiscalDao.salvar(documentoFiscal);

            PatrimonioDao dao = new PatrimonioDao();

            dao.salvar(patrimonio);

            setItens(dao.listar());
            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");

            // Quando salvar um novo objeto ele vai atualizar a minha tabela
            // automaticamente
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }

    }

    public void prepararEditar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            // patrimonio = new Patrimonio();
            SetorDao daoSetor = new SetorDao();
            setComboSetores(daoSetor.listar());
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            setDao(new PatrimonioDao());
            getDao().alterar(getPatrimonio());

            setItens(getDao().listar());
            JSFUtil.adicionaMensagemSucesso("Alterado com sucesso!");

            // Quando salvar um novo objeto ele vai atualizar a minha tabela
            // automaticamente
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }

    }

    public void excluir() {

        try {

            PatrimonioDao dao = new PatrimonioDao();
            dao.excluir(getPatrimonio());
            setItens(dao.listar());

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    /**
     * @return the comboFornecedores
     */
    public ArrayList<Fornecedor> getComboFornecedores() {
        return comboFornecedores;
    }

    /**
     * @param comboFornecedores the comboFornecedores to set
     */
    public void setComboFornecedores(ArrayList<Fornecedor> comboFornecedores) {
        this.comboFornecedores = comboFornecedores;
    }

    /**
     * @return the documentoFiscal
     */
    public DocumentoFiscal getDocumentoFiscal() {
        return documentoFiscal;
    }

    /**
     * @param documentoFiscal the documentoFiscal to set
     */
    public void setDocumentoFiscal(DocumentoFiscal documentoFiscal) {
        this.documentoFiscal = documentoFiscal;
    }
}
