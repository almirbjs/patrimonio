package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.SetorDao;
import br.com.patrimonio.dao.PrivilegioDao;
import br.com.patrimonio.domain.Setor;
import br.com.patrimonio.domain.Privilegio;
import br.com.patrimonio.util.JSFUtil;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBSetor") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class setorBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Setor> itens;
    private ArrayList<Setor> itensFiltrados;// Vai armazenar os itens filtrados
    private ArrayList<Privilegio> comboPrivilegio; // Carrega o combox com o nome dos
    // privilegios
    SetorDao dao = new SetorDao();
    private Setor setor = new Setor();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            SetorDao dao = new SetorDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public ArrayList<Setor> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Setor> itens) {
        this.itens = itens;
    }

    public ArrayList<Setor> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Setor> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Privilegio> getComboPrivilegio() {
        return comboPrivilegio;
    }

    public void setComboPrivilegio(ArrayList<Privilegio> comboPrivilegio) {
        this.comboPrivilegio = comboPrivilegio;
    }

    public SetorDao getDao() {
        return dao;
    }

    public void setDao(SetorDao dao) {
        this.dao = dao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            setor = new Setor();
            PrivilegioDao dao = new PrivilegioDao();
            comboPrivilegio = dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {
            SetorDao dao = new SetorDao();
            dao.salvar(setor);
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
            //setor = new Setor();
            PrivilegioDao daoPrivilegio = new PrivilegioDao();
            comboPrivilegio = daoPrivilegio.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new SetorDao();
            dao.alterar(setor);

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

            SetorDao dao = new SetorDao();
            dao.excluir(setor);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }
}
