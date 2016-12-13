package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.OrcamentoDao;
import br.com.patrimonio.domain.Orcamento;
import br.com.patrimonio.util.JSFUtil;

@ManagedBean(name = "MBOrcamento") // Nome do meu managedBean ele � usado para

@ViewScoped
public class OrcamentoBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Orcamento> itens;
    private ArrayList<Orcamento> itensFiltrados;// Vai armazenar os itens
    // filtrados

    private OrcamentoDao dao = new OrcamentoDao();
    private Orcamento orcamento = new Orcamento();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            OrcamentoDao dao = new OrcamentoDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public ArrayList<Orcamento> getItens() {
        return itens;
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            orcamento = new Orcamento();

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {
            OrcamentoDao dao = new OrcamentoDao();
            dao.salvar(orcamento);
            itens = dao.listar();
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
            // categoria = new Categoria();

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new OrcamentoDao();
            dao.alterar(orcamento);

            itens = dao.listar();
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

            OrcamentoDao dao = new OrcamentoDao();
            dao.excluir(orcamento);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    public void setItens(ArrayList<Orcamento> itens) {
        this.itens = itens;
    }

    public ArrayList<Orcamento> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Orcamento> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public OrcamentoDao getDao() {
        return dao;
    }

    public void setDao(OrcamentoDao dao) {
        this.dao = dao;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
    
    
}
