package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.patrimonio.dao.FornecedorDao;
import br.com.patrimonio.dao.InsumoDao;
import br.com.patrimonio.dao.RequisicaoDao;
import br.com.patrimonio.dao.SetorDao;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.domain.Insumo;
import br.com.patrimonio.domain.ItemInsumo;
import br.com.patrimonio.domain.Requisicao;
import br.com.patrimonio.domain.Setor;
import br.com.patrimonio.util.JSFUtil;
import java.util.List;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "MBRequisicao")
@ViewScoped
public class RequisicaoBean {

    ArrayList<Requisicao> itens;
    ArrayList<Requisicao> itensFiltrados;
    ArrayList<Insumo> ListaInsumos;
    List<ItemInsumo> itensInsumo;
    ArrayList<ItemInsumo> itensInsumoFiltrados;
    ArrayList<Fornecedor> ListaFornecedores;
    ArrayList<Setor> ListaSetores;

    ItemInsumo itemInsumo = new ItemInsumo();
    RequisicaoDao dao = new RequisicaoDao();
    Requisicao requisicao = new Requisicao();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            RequisicaoDao dao = new RequisicaoDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            SetorDao setorDao = new SetorDao();

            if (itensInsumo == null) {
                itensInsumo = new ArrayList<>();
            }
            ListaSetores = setorDao.listar();

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {
            RequisicaoDao dao = new RequisicaoDao();
            dao.salvar(requisicao, itensInsumo);
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

            dao = new RequisicaoDao();
            dao.alterar(requisicao);

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

            RequisicaoDao requisicaoDao = new RequisicaoDao();
            requisicaoDao.excluir(requisicao);
            itens = requisicaoDao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    public void fornecedorSelecionado(SelectEvent event) {
        Fornecedor fornecedor = (Fornecedor) event.getObject();
        requisicao.setFornecedor(fornecedor);
        FornecedorDao fornecedorDao = new FornecedorDao();
        ListaFornecedores = fornecedorDao.listar();

    }

    public void setorSelecionado(SelectEvent event) {
        Setor setor = (Setor) event.getObject();
        requisicao.setSetor(setor);
        SetorDao setorDao = new SetorDao();
        ListaSetores = setorDao.listar();

    }

    public void insumoSelecionado(SelectEvent event) {
        Insumo insumo = (Insumo) event.getObject();
        requisicao.setInsumo(insumo);
        InsumoDao insumoDao = new InsumoDao();
        ListaInsumos = insumoDao.listar();

    }

    public void adicionarItemInsumo(Insumo insumo) {

        itemInsumo.setInsumo(insumo);
        itemInsumo.setRequisicao(requisicao);
        itensInsumo.add(itemInsumo);
        requisicao = new Requisicao();
        itemInsumo = new ItemInsumo();

    }

    public ArrayList<Requisicao> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Requisicao> itens) {
        this.itens = itens;
    }

    public ArrayList<Requisicao> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Requisicao> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Insumo> getListaInsumos() {
        return ListaInsumos;
    }

    public void setListaInsumos(ArrayList<Insumo> ListaInsumos) {
        this.ListaInsumos = ListaInsumos;
    }

    public List<ItemInsumo> getItensInsumo() {
        return itensInsumo;
    }

    public void setItensInsumo(List<ItemInsumo> itensInsumo) {
        this.itensInsumo = itensInsumo;
    }

    public ArrayList<ItemInsumo> getItensInsumoFiltrados() {
        return itensInsumoFiltrados;
    }

    public void setItensInsumoFiltrados(ArrayList<ItemInsumo> itensInsumoFiltrados) {
        this.itensInsumoFiltrados = itensInsumoFiltrados;
    }

    public ArrayList<Fornecedor> getListaFornecedores() {
        return ListaFornecedores;
    }

    public void setListaFornecedores(ArrayList<Fornecedor> ListaFornecedores) {
        this.ListaFornecedores = ListaFornecedores;
    }

    public ArrayList<Setor> getListaSetores() {
        return ListaSetores;
    }

    public void setListaSetores(ArrayList<Setor> ListaSetores) {
        this.ListaSetores = ListaSetores;
    }

    public ItemInsumo getItemInsumo() {
        return itemInsumo;
    }

    public void setItemInsumo(ItemInsumo itemInsumo) {
        this.itemInsumo = itemInsumo;
    }

    public RequisicaoDao getDao() {
        return dao;
    }

    public void setDao(RequisicaoDao dao) {
        this.dao = dao;
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

}
