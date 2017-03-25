package br.com.patrimonio.bean;

import br.com.patrimonio.dao.FornecedorDao;
import br.com.patrimonio.dao.InsumoDao;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.ListaDeCompraDao;
import br.com.patrimonio.dao.SetorDao;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.domain.Insumo;
import br.com.patrimonio.domain.ItemFornecedor;
import br.com.patrimonio.domain.ItemInsumo;
import br.com.patrimonio.domain.ItemMarca;
import br.com.patrimonio.domain.ListaDeCompra;
import br.com.patrimonio.domain.Marca;
import br.com.patrimonio.domain.Setor;
import br.com.patrimonio.util.JSFUtil;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "MBListaDeCompra")
@ViewScoped
public class listaDeCompraBean {

    ArrayList<ListaDeCompra> itens;
    ArrayList<ListaDeCompra> itensFiltrados;
    ArrayList<Setor> listaSetores;
    ArrayList<Fornecedor> listaFornecedores;
    ArrayList<Insumo> listaInsumo;

    List<ItemFornecedor> itensFornecedores;
    List<ItemMarca> itensMarcas;
    List<ItemInsumo> itensInsumos;

    ListaDeCompraDao listaDeCompraDao = new ListaDeCompraDao();
    SetorDao setorDao = new SetorDao();
    FornecedorDao fornecedorDao = new FornecedorDao();

    ListaDeCompra listaDeCompra = new ListaDeCompra();
    Insumo insumo = new Insumo();

    ItemFornecedor itemFornecedor = new ItemFornecedor();
    ItemMarca itemMarca = new ItemMarca();
    ItemInsumo itemInsumo = new ItemInsumo();

    @PostConstruct
    public void prepararPesquisa() {
        try {

            listaDeCompraDao = new ListaDeCompraDao();
            itens = listaDeCompraDao.listar();

            listaSetores = setorDao.listar();

        } catch (Exception e) {

            e.printStackTrace();

            JSFUtil.adicionaMensagemErro(e.getMessage());

        }
    }

    public void prepararSalvar() {

        try {

            InsumoDao insumoDao = new InsumoDao();
            listaInsumo = insumoDao.listar();

            listaSetores = setorDao.listar();

            listaFornecedores = fornecedorDao.listar();

            if (listaDeCompra == null) {
                listaDeCompra = new ListaDeCompra();
            }

            if (itensFornecedores == null) {
                itensFornecedores = new ArrayList<>();

            }

            if (itensMarcas == null) {
                itensMarcas = new ArrayList<>();

            }

            if (itensInsumos == null) {
                itensInsumos = new ArrayList<>();

            }

        } catch (Exception ex) {

            ex.printStackTrace();

            JSFUtil.adicionaMensagemErro(ex.getMessage());

        }

    }

    public void salvar() {

        try {

            
            listaDeCompraDao = new ListaDeCompraDao();
            listaDeCompraDao.salvar(listaDeCompra, itensFornecedores, itensInsumos);
            itens = listaDeCompraDao.listar();

            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();

            JSFUtil.adicionaMensagemErro(e.getMessage());
        }

    }

    public void salvarItemMarca() {

        try {

            
            listaDeCompra.setItemInsumo(itemInsumo);
            listaDeCompraDao = new ListaDeCompraDao();
            listaDeCompraDao.salvarItemMarca(itensMarcas);

            JSFUtil.adicionaMensagemSucesso("Salvo com item marca sucesso!");

        } catch (Exception e) {

            e.printStackTrace();

            JSFUtil.adicionaMensagemErro(e.getMessage());
        }

    }

    public void prepararEditar() {

        try {

        } catch (Exception ex) {

            ex.printStackTrace();

            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            listaDeCompraDao = new ListaDeCompraDao();
            listaDeCompraDao.alterar(listaDeCompra);
            itens = listaDeCompraDao.listar();

            JSFUtil.adicionaMensagemSucesso("Alterado com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();

            JSFUtil.adicionaMensagemErro(e.getMessage());
        }

    }

    public void excluir() {

        try {

            listaDeCompraDao = new ListaDeCompraDao();
            listaDeCompraDao.excluir(listaDeCompra);
            itens = listaDeCompraDao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {

            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");

        }

    }

    public void adicionaItemFornecedor(ActionEvent evento) {

        Fornecedor fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");

        int achou = -1;

        for (int posicao = 0; posicao < itensFornecedores.size(); posicao++) {
            if (itensFornecedores.get(posicao).getFornecedor().equals(fornecedor)) {

                achou = posicao;

            }

        }

        if (achou < 0) {

            itemFornecedor = new ItemFornecedor();
            itemFornecedor.setFornecedor(fornecedor);
            itensFornecedores.add(itemFornecedor);

            JSFUtil.adicionaMensagemSucesso("Adicionado com sucesso :)");

        } else {

            JSFUtil.adicionaMensagemErro("Fornecedor já adicionado!");

        }

    }

    public void adicionaItemMarca(ActionEvent evento) {

        Marca marca = (Marca) evento.getComponent().getAttributes().get("marcaSelecionada");

        int achou = -1;

        for (int posicao = 0; posicao < itensMarcas.size(); posicao++) {
            if (itensMarcas.get(posicao).getMarca().equals(marca)) {

                achou = posicao;

            }

        }

        if (achou < 0) {

            itemMarca = new ItemMarca();
            itemMarca.setInsumo(insumo);
            itemMarca.setMarca(marca);
            itensMarcas.add(itemMarca);

            JSFUtil.adicionaMensagemSucesso("Adicionado com sucesso :)");

        } else {

            JSFUtil.adicionaMensagemErro("Marca já adicionada!");

        }

    }

    public void removeItemFornecedor(ActionEvent evento) {

        ItemFornecedor itemFornecedores = (ItemFornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionadoRemove");

        int achou = -1;

        for (int posicao = 0; posicao < itensFornecedores.size(); posicao++) {

            if (itensFornecedores.get(posicao).getFornecedor().equals(itemFornecedores.getFornecedor())) {

                achou = posicao;
            }
        }

        if (achou > -1) {
            itensFornecedores.remove(achou);

            JSFUtil.adicionaMensagemSucesso("Fornecedor removido da lista com sucesso :)");

        }

    }

    public void insumoSelecionado(SelectEvent event) {

        insumo = (Insumo) event.getObject();

        itemInsumo.setInsumo(insumo);

        InsumoDao insumoDao = new InsumoDao();

        listaInsumo = insumoDao.listar();

    }

    public void removeItemMarca(ActionEvent evento) {

        itemMarca = (ItemMarca) evento.getComponent().getAttributes().get("marcaSelecionadaRemove");

        int achou = -1;

        for (int posicao = 0; posicao < itensMarcas.size(); posicao++) {

            if (itensMarcas.get(posicao).getMarca().equals(itemMarca.getMarca())) {

                achou = posicao;
            }
        }

        if (achou > -1) {

            itensMarcas.remove(achou);

            JSFUtil.adicionaMensagemSucesso("Removido da lista com sucesso :)");

        }

    }

    public void adicionaItemInsumo(ActionEvent evento) {

        /* insumo = (Insumo) evento.getComponent().getAttributes().get("insumoItemSelecionado");*/
        int achou = -1;

        for (int posicao = 0; posicao < itensInsumos.size(); posicao++) {
            if (itensInsumos.get(posicao).getInsumo().equals(insumo)) {

                achou = posicao;

            }

        }

        if (achou < 0) {

            if (itemInsumo.getQuantMaxAltaTemp() <= itemInsumo.getQuantMinAltaTemp()) {
                JSFUtil.adicionaMensagemErro("Quantidade maxima para alta temporada menor ou igual á minima!");
                return;

            }

            if (itemInsumo.getQuantMaxBaixaTemp() <= itemInsumo.getQuantMinBaixaTemp()) {
                JSFUtil.adicionaMensagemErro("Quantidade maxima para baixa temporada menor ou igual á minima!");
                return;

            }

            
            itensInsumos.add(itemInsumo);
            itemInsumo = new ItemInsumo();

            itensMarcas.removeAll(itensMarcas);
            insumo = null;//limpar campo insumo
            insumo = new Insumo();

            JSFUtil.adicionaMensagemSucesso("Adicionado com sucesso :)");

        } else {

            JSFUtil.adicionaMensagemErro("Item já adicionado!");

        }

    }

    public void removeItemInsumo(ActionEvent evento) {

        itemInsumo = (ItemInsumo) evento.getComponent().getAttributes().get("insumoSelecionadoRemove");

        int achou = -1;

        for (int posicao = 0; posicao < itensInsumos.size(); posicao++) {

            if (itensInsumos.get(posicao).getInsumo().equals(itemInsumo.getInsumo())) {

                achou = posicao;
            }
        }

        if (achou > -1) {

            itensInsumos.remove(achou);

            JSFUtil.adicionaMensagemSucesso(" Removido da lista com sucesso :)");

        }

    }

    public ArrayList<ListaDeCompra> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ListaDeCompra> itens) {
        this.itens = itens;
    }

    public ArrayList<ListaDeCompra> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<ListaDeCompra> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Setor> getListaSetores() {
        return listaSetores;
    }

    public void setListaSetores(ArrayList<Setor> listaSetores) {
        this.listaSetores = listaSetores;
    }

    public ArrayList<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(ArrayList<Fornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public ArrayList<Insumo> getListaInsumo() {
        return listaInsumo;
    }

    public void setListaInsumo(ArrayList<Insumo> listaInsumo) {
        this.listaInsumo = listaInsumo;
    }

    public List<ItemFornecedor> getItensFornecedores() {
        return itensFornecedores;
    }

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setItensFornecedores(List<ItemFornecedor> itensFornecedores) {
        this.itensFornecedores = itensFornecedores;
    }

    public List<ItemMarca> getItensMarcas() {
        return itensMarcas;
    }

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setItensMarcas(List<ItemMarca> itensMarcas) {
        this.itensMarcas = itensMarcas;
    }

    public List<ItemInsumo> getItensInsumos() {
        return itensInsumos;
    }

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setItensInsumos(List<ItemInsumo> itensInsumos) {
        this.itensInsumos = itensInsumos;
    }

    public ListaDeCompraDao getListaDeCompraDao() {
        return listaDeCompraDao;
    }

    public void setListaDeCompraDao(ListaDeCompraDao listaDeCompraDao) {
        this.listaDeCompraDao = listaDeCompraDao;
    }

    public SetorDao getSetorDao() {
        return setorDao;
    }

    public void setSetorDao(SetorDao setorDao) {
        this.setorDao = setorDao;
    }

    public FornecedorDao getFornecedorDao() {
        return fornecedorDao;
    }

    public void setFornecedorDao(FornecedorDao fornecedorDao) {
        this.fornecedorDao = fornecedorDao;
    }

    public ListaDeCompra getListaDeCompra() {
        return listaDeCompra;
    }

    public void setListaDeCompra(ListaDeCompra listaDeCompra) {
        this.listaDeCompra = listaDeCompra;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public ItemFornecedor getItemFornecedor() {
        return itemFornecedor;
    }

    public void setItemFornecedor(ItemFornecedor itemFornecedor) {
        this.itemFornecedor = itemFornecedor;
    }

    public ItemMarca getItemMarca() {
        return itemMarca;
    }

    public void setItemMarca(ItemMarca itemMarca) {
        this.itemMarca = itemMarca;
    }

    public ItemInsumo getItemInsumo() {
        return itemInsumo;
    }

    public void setItemInsumo(ItemInsumo itemInsumo) {
        this.itemInsumo = itemInsumo;
    }

}
