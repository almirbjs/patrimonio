package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.patrimonio.dao.UsuarioDao;
import br.com.patrimonio.dao.PrivilegioDao;
import br.com.patrimonio.domain.Usuario;
import br.com.patrimonio.domain.Privilegio;
import br.com.patrimonio.util.JSFUtil;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBUsuario") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class UsuarioBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Usuario> itens;
    private ArrayList<Usuario> itensFiltrados;// Vai armazenar os itens filtrados
    private ArrayList<Privilegio> comboPrivilegio; // Carrega o combox com o nome dos
    // privilegios
    UsuarioDao dao = new UsuarioDao();
    private Usuario usuario = new Usuario();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            UsuarioDao dao = new UsuarioDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public ArrayList<Usuario> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Usuario> itens) {
        this.itens = itens;
    }

    public ArrayList<Usuario> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Usuario> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Privilegio> getComboPrivilegio() {
        return comboPrivilegio;
    }

    public void setComboPrivilegio(ArrayList<Privilegio> comboPrivilegio) {
        this.comboPrivilegio = comboPrivilegio;
    }

    public UsuarioDao getDao() {
        return dao;
    }

    public void setDao(UsuarioDao dao) {
        this.dao = dao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            usuario = new Usuario();
            PrivilegioDao dao = new PrivilegioDao();
            comboPrivilegio = dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {

            UsuarioDao dao = new UsuarioDao();
            dao.salvar(usuario);
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
            PrivilegioDao daoPrivilegio = new PrivilegioDao();
            comboPrivilegio = daoPrivilegio.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new UsuarioDao();
            dao.alterar(usuario);

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

            UsuarioDao dao = new UsuarioDao();
            dao.excluir(usuario);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    @Override
    public String toString() {
        return "UsuarioBean{" + "itens=" + itens + ", itensFiltrados=" + itensFiltrados + ", comboPrivilegio=" + comboPrivilegio + ", dao=" + dao + ", usuario=" + usuario + '}';
    }

}
