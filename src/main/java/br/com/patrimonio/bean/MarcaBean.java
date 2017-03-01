package br.com.patrimonio.bean;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.MarcaDao;
import br.com.patrimonio.domain.Marca;
import br.com.patrimonio.util.JSFUtil;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBMarca") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class MarcaBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    ArrayList<Marca> itens;
    ArrayList<Marca> itensFiltrados;// Vai armazenar os itens
    MarcaDao dao = new MarcaDao();
    Marca marca = new Marca();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            MarcaDao dao = new MarcaDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void salvar() {

        try {
            MarcaDao dao = new MarcaDao();
            dao.salvar(marca);
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

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            marca = new Marca();;

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new MarcaDao();
            dao.alterar(marca);

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

            MarcaDao dao = new MarcaDao();
            dao.excluir(marca);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    public ArrayList<Marca> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Marca> itens) {
        this.itens = itens;
    }

    public ArrayList<Marca> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Marca> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public MarcaDao getDao() {
        return dao;
    }

    public void setDao(MarcaDao dao) {
        this.dao = dao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
