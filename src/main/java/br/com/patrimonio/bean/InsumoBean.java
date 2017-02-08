package br.com.patrimonio.bean;

import br.com.patrimonio.dao.GrupoDao;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.InsumoDao;
import br.com.patrimonio.dao.UnidadeDao;
import br.com.patrimonio.domain.Grupo;
import br.com.patrimonio.domain.Insumo;
import br.com.patrimonio.domain.ItemMarca;
import br.com.patrimonio.domain.Marca;
import br.com.patrimonio.domain.Unidade;
import br.com.patrimonio.util.JSFUtil;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "MBInsumo")
@ViewScoped
public class InsumoBean {

    ArrayList<Insumo> itens;
    ArrayList<Insumo> itensFiltrados;
    ArrayList<Grupo> listaGrupo;
    ArrayList<Unidade> listaUnidade;
    ArrayList<Marca> listaMarca;
    ArrayList<ItemMarca> itensMarca;
    InsumoDao dao = new InsumoDao();
    Insumo insumo = new Insumo();
    Grupo grupo = new Grupo();
    Unidade unidade = new Unidade();

    public void prepararPesquisa() {
        try {

            InsumoDao dao = new InsumoDao();
            itens = dao.listar();

        } catch (Exception e) {

            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    @PostConstruct
    public void prepararSalvar() {

        try {
           insumo=new Insumo();
            GrupoDao grupoDao = new GrupoDao();
            listaGrupo = grupoDao.listar();

            UnidadeDao unidadeDao = new UnidadeDao();
            listaUnidade = unidadeDao.listar();

            if (itensMarca == null) {
                itensMarca = new ArrayList<>();

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {
            InsumoDao insumoDao = new InsumoDao();
            insumoDao.salvar(insumo, itensMarca);
            itens = insumoDao.listar();

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

            dao = new InsumoDao();
            dao.alterar(insumo);

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

            InsumoDao dao = new InsumoDao();
            dao.excluir(insumo);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    public void adicionarItemMarca(ActionEvent evento) {
        Marca marca = (Marca) evento.getComponent().getAttributes().get("marcaSelecionada");

        int achou = -1;

        for (int posicao = 0; posicao < itensMarca.size(); posicao++) {
            if (itensMarca.get(posicao).getMarca().equals(marca)) {
                achou = posicao;

            }
        }

        if (achou < 0) {
            ItemMarca itemMarca = new ItemMarca();
            
            itemMarca.setInsumo(insumo);
            itemMarca.setMarca(marca);
            itensMarca.add(itemMarca);
        } else {

            JSFUtil.adicionaMensagemErro("Marca já adicionada!");

        }

    }

    public void removeItemMarca(ActionEvent evento) {

        ItemMarca itemMarca = (ItemMarca) evento.getComponent().getAttributes().get("ItemMarcaSelecionada");

        int achou = -1;

        for (int posicao = 0; posicao < itensMarca.size(); posicao++) {
            if (itensMarca.get(posicao).getMarca().equals(itemMarca.getMarca())) {
                achou = posicao;
            }
        }
        if (achou > -1) {

            itensMarca.remove(achou);

        }
    }

    /*   public void adicionarItemMarca(ActionEvent evento) {

    Marca marca = (Marca) evento.getComponent().getAttributes().get("marcaSelecionada");

    int achou = -1;

    for (int posicao = 0; posicao < itens.size(); posicao++) {
    if (itens.get(posicao).getMarca().equals(marca)) {
    achou = posicao;
    }
    }
    if (achou < 0) {
    Insumo insumo = new Insumo();
    itemMarca.setInsumo(insumo);
    itemMarca = new ItemMarca();
    itemMarca.setCodigo(marca.getCodigo());
    itemMarca.setMarca(marca);
    itens.add(itemMarca);


    } else {

    JSFUtil.adicionaMensagemErro("Marca já adicionada!");
    }
    }*/
    public ArrayList<Insumo> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Insumo> itens) {
        this.itens = itens;
    }

    public ArrayList<Insumo> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Insumo> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Grupo> getListaGrupo() {
        return listaGrupo;
    }

    public void setListaGrupo(ArrayList<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public ArrayList<Unidade> getListaUnidade() {
        return listaUnidade;
    }

    public void setListaUnidade(ArrayList<Unidade> listaUnidade) {
        this.listaUnidade = listaUnidade;
    }

    public ArrayList<Marca> getListaMarca() {
        return listaMarca;
    }

    public void setListaMarca(ArrayList<Marca> listaMarca) {
        this.listaMarca = listaMarca;
    }

    public InsumoDao getDao() {
        return dao;
    }

    public void setDao(InsumoDao dao) {
        this.dao = dao;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public ArrayList<ItemMarca> getItensMarca() {
        return itensMarca;
    }

    public void setItensMarca(ArrayList<ItemMarca> itensMarca) {
        this.itensMarca = itensMarca;
    }

}
