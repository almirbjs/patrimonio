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
import br.com.patrimonio.domain.Unidade;
import br.com.patrimonio.util.JSFUtil;

@ManagedBean(name = "MBInsumo")
@ViewScoped
public class InsumoBean {

    ArrayList<Insumo> itens;
    ArrayList<Insumo> itensFiltrados;
    ArrayList<Grupo> listaGrupo;
    ArrayList<Unidade> listaUnidade;
    InsumoDao dao = new InsumoDao();
    Insumo insumo = new Insumo();
    Grupo grupo = new Grupo();
    Unidade unidade = new Unidade();

    @PostConstruct
    public void prepararPesquisa() {
        
        try {

            InsumoDao insumoDao = new InsumoDao();
            
            itens = insumoDao.listar();

        } catch (Exception e) {

            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {

        try {
            insumo = new Insumo();

            GrupoDao grupoDao = new GrupoDao();
            listaGrupo = grupoDao.listar();

            UnidadeDao unidadeDao = new UnidadeDao();
            listaUnidade = unidadeDao.listar();

        } catch (Exception ex) {
            
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {

            InsumoDao insumoDao = new InsumoDao();
            
            insumoDao.salvar(insumo);
            
            itens = insumoDao.listar();

            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");

        } catch (Exception e) {
            
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }

    }

    public void prepararEditar() {

        try {

            insumo = new Insumo();

            GrupoDao grupoDao = new GrupoDao();
            listaGrupo = grupoDao.listar();

            UnidadeDao unidadeDao = new UnidadeDao();
            listaUnidade = unidadeDao.listar();

        } catch (Exception ex) {
                       
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

            JSFUtil.adicionaMensagemErro(e.getMessage());
            
        }

    }

    public void excluir() {

        try {

            InsumoDao insumoDao = new InsumoDao();

            insumoDao.excluir(insumo);
            itens = insumoDao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            
            JSFUtil.adicionaMensagemErro(e.getMessage());
            
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

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

}
