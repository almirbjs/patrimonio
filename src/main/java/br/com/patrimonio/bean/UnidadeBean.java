package br.com.patrimonio.bean;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.UnidadeDao;
import br.com.patrimonio.domain.Unidade;
import br.com.patrimonio.util.JSFUtil;

@ManagedBean(name = "MBUnidade")

@ViewScoped

public class UnidadeBean {

    ArrayList<Unidade> itens;
    ArrayList<Unidade> itensFiltrados;

    UnidadeDao dao = new UnidadeDao();
    Unidade unidade = new Unidade();

    @PostConstruct
    public void prepararPesquisa() {
        try {
            UnidadeDao unidadeDao = new UnidadeDao();
            itens = unidadeDao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {

        try {
            unidade = new Unidade();

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {
            UnidadeDao dao = new UnidadeDao();
            dao.salvar(unidade);
            itens = dao.listar();
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

            dao = new UnidadeDao();
            dao.alterar(unidade);

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

            UnidadeDao dao = new UnidadeDao();
            dao.excluir(unidade);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }
    }

    public ArrayList<Unidade> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Unidade> itens) {
        this.itens = itens;
    }

    public ArrayList<Unidade> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Unidade> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public UnidadeDao getDao() {
        return dao;
    }

    public void setDao(UnidadeDao dao) {
        this.dao = dao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

}
