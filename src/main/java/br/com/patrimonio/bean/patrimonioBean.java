package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.DocumentoFiscalDao;
import br.com.patrimonio.dao.PatrimonioDao;
import br.com.patrimonio.dao.ProdutoDao;
import br.com.patrimonio.dao.SetorDao;
import br.com.patrimonio.domain.DocumentoFiscal;
import br.com.patrimonio.domain.Patrimonio;
import br.com.patrimonio.domain.Produto;
import br.com.patrimonio.domain.Setor;
import br.com.patrimonio.util.JSFUtil;
import org.primefaces.event.SelectEvent;

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
    private ArrayList<Patrimonio> itensFiltrados;
    private ArrayList<Setor> comboSetores; // Carrega o combox com o nome dos
    private ArrayList<Produto> itensProdutos;
    private ArrayList<DocumentoFiscal> itensDocumentoFiscal;// setores
    private PatrimonioDao dao = new PatrimonioDao();
    private Patrimonio patrimonio = new Patrimonio();
    private DocumentoFiscal documentoFiscal = new DocumentoFiscal();
    

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
            
            SetorDao dao = new SetorDao();
            
            setComboSetores(dao.listar());

           

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {

            

            PatrimonioDao dao = new PatrimonioDao();

            patrimonio.setFuncionando("S");
            dao.salvar(getPatrimonio());

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

    public void produtoSelecionado(SelectEvent event) {
        Produto p = (Produto) event.getObject();
       patrimonio.setProduto(p);
       ProdutoDao produtoDao= new ProdutoDao();
        itensProdutos = produtoDao.listar();

    }
    
     public void docSelecionado(SelectEvent event) {
        DocumentoFiscal f = (DocumentoFiscal) event.getObject();
        patrimonio.setDocumentoFiscal(f);
        DocumentoFiscalDao documentoFiscalDao = new DocumentoFiscalDao();
        itensDocumentoFiscal = documentoFiscalDao.listar();

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

    public ArrayList<Setor> getComboSetores() {
        return comboSetores;
    }

    public void setComboSetores(ArrayList<Setor> comboSetores) {
        this.comboSetores = comboSetores;
    }

    public ArrayList<Produto> getItensProdutos() {
        return itensProdutos;
    }

    public void setItensProdutos(ArrayList<Produto> itensProdutos) {
        this.itensProdutos = itensProdutos;
    }

    public PatrimonioDao getDao() {
        return dao;
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

    public DocumentoFiscal getDocumentoFiscal() {
        return documentoFiscal;
    }

    public void setDocumentoFiscal(DocumentoFiscal documentoFiscal) {
        this.documentoFiscal = documentoFiscal;
    }

    public ArrayList<DocumentoFiscal> getItensDocumentoFiscal() {
        return itensDocumentoFiscal;
    }

    public void setItensDocumentoFiscal(ArrayList<DocumentoFiscal> itensDocumentoFiscal) {
        this.itensDocumentoFiscal = itensDocumentoFiscal;
    }

   
}
