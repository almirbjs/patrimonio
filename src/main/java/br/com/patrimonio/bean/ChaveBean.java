package br.com.patrimonio.bean;

import br.com.patrimonio.dao.ChaveDao;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.patrimonio.dao.SetorDao;
import br.com.patrimonio.domain.Chave;
import br.com.patrimonio.domain.Setor;
import br.com.patrimonio.util.JSFUtil;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBChave") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class ChaveBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Chave> itens;
    private ArrayList<Chave> itensFiltrados;// Vai armazenar os itens filtrados
    private ArrayList<Setor> comboSetor; // Carrega o combox com o nome dos
    // privilegios
    ChaveDao dao = new ChaveDao();
    private Chave chave = new Chave();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            ChaveDao dao = new ChaveDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public ArrayList<Chave> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Chave> itens) {
        this.itens = itens;
    }

    public ArrayList<Chave> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Chave> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Setor> getComboSetor() {
        return comboSetor;
    }

    public void setComboSetor(ArrayList<Setor> comboSetor) {
        this.comboSetor = comboSetor;
    }

    public ChaveDao getDao() {
        return dao;
    }

    public void setDao(ChaveDao dao) {
        this.dao = dao;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            chave = new Chave();
            SetorDao dao = new SetorDao();
            comboSetor = dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {

            ChaveDao dao = new ChaveDao();
            dao.salvar(chave);
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
            //usuario = new Usuario();
            SetorDao daoSetor = new SetorDao();
            comboSetor = daoSetor.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new ChaveDao();
            dao.alterar(chave);

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

            ChaveDao dao = new ChaveDao();
            dao.excluir(chave);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    @Override
    public String toString() {
        return "ChaveBean{" + "itens=" + itens + ", itensFiltrados=" + itensFiltrados + ", comboSetor=" + comboSetor + ", dao=" + dao + ", chave=" + chave + '}';
    }

}
