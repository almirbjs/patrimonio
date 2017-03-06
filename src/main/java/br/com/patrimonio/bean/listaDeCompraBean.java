package br.com.patrimonio.bean;

import br.com.patrimonio.dao.FornecedorDao;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.ListaDeCompraDao;
import br.com.patrimonio.dao.SetorDao;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.domain.ItemFornecedor;
import br.com.patrimonio.domain.ListaDeCompra;
import br.com.patrimonio.domain.Setor;
import br.com.patrimonio.util.JSFUtil;
import java.util.List;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "MBListaDeCompra")
@ViewScoped
public class listaDeCompraBean {

    ArrayList<ListaDeCompra> itens;
    ArrayList<ListaDeCompra> itensFiltrados;
    ArrayList<Setor> listaSetores;
    ArrayList<Fornecedor> listaFornecedores;
    
    List<ItemFornecedor> itensFornecedores;
    
    ListaDeCompraDao listaDeCompraDao = new ListaDeCompraDao();
    SetorDao setorDao = new SetorDao();
    FornecedorDao fornecedorDao = new FornecedorDao();

    ListaDeCompra listaDeCompra = new ListaDeCompra();
    ItemFornecedor itemFornecedor=new ItemFornecedor();

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

            listaDeCompra = new ListaDeCompra();

            listaSetores = setorDao.listar();
            
            listaFornecedores = fornecedorDao.listar();
            
            if (itensFornecedores==null) {
                itensFornecedores=new ArrayList<>();
                
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            JSFUtil.adicionaMensagemErro(ex.getMessage());

        }

    }

    public void salvar() {

        try {

            listaDeCompraDao = new ListaDeCompraDao();
            listaDeCompraDao.salvar(listaDeCompra);
            itens = listaDeCompraDao.listar();
            
            

            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");

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
        
        itemFornecedor.setFornecedor(fornecedor);
        itensFornecedores.add(itemFornecedor);
        
        JSFUtil.adicionaMensagemSucesso("Adicionado com sucesso :)");
        

    }

    public ArrayList<ListaDeCompra> getItens() {
        return itens;
    }

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setItens(ArrayList<ListaDeCompra> itens) {
        this.itens = itens;
    }

    public ArrayList<ListaDeCompra> getItensFiltrados() {
        return itensFiltrados;
    }

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setItensFiltrados(ArrayList<ListaDeCompra> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ListaDeCompraDao getListaDeCompraDao() {
        return listaDeCompraDao;
    }

    public void setListaDeCompraDao(ListaDeCompraDao listaDeCompraDao) {
        this.listaDeCompraDao = listaDeCompraDao;
    }

    public ListaDeCompra getListaDeCompra() {
        return listaDeCompra;
    }

    public void setListaDeCompra(ListaDeCompra listaDeCompra) {
        this.listaDeCompra = listaDeCompra;
    }

    public ArrayList<Setor> getListaSetores() {
        return listaSetores;
    }

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setListaSetores(ArrayList<Setor> listaSetores) {
        this.listaSetores = listaSetores;
    }

    public ArrayList<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setListaFornecedores(ArrayList<Fornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
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

    public List<ItemFornecedor> getItensFornecedores() {
        return itensFornecedores;
    }

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setItensFornecedores(List<ItemFornecedor> itensFornecedores) {
        this.itensFornecedores = itensFornecedores;
    }

    public ItemFornecedor getItemFornecedor() {
        return itemFornecedor;
    }

    public void setItemFornecedor(ItemFornecedor itemFornecedor) {
        this.itemFornecedor = itemFornecedor;
    }
    
    
    
    }
