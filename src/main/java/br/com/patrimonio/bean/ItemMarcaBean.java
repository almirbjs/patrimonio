package br.com.patrimonio.bean;

import br.com.patrimonio.dao.ItemMarcaDao;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.domain.ItemMarca;
import br.com.patrimonio.domain.Marca;
import br.com.patrimonio.util.JSFUtil;
import javax.faces.event.ActionEvent;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBItemMarca") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class ItemMarcaBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    ArrayList<ItemMarca> itens;
    ArrayList<ItemMarca> itensFiltrados;// Vai armazenar os itens
    ItemMarcaDao dao = new ItemMarcaDao();
    ItemMarca itemMarca = new ItemMarca();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            ItemMarcaDao dao = new ItemMarcaDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void salvar() {

        try {
            ItemMarcaDao dao = new ItemMarcaDao();
            dao.salvar(itemMarca);
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
            itemMarca = new ItemMarca();

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new ItemMarcaDao();
            dao.alterar(itemMarca);

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

            ItemMarcaDao dao = new ItemMarcaDao();
            dao.excluir(itemMarca);
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

        for (int posicao = 0; posicao < itens.size(); posicao++) {
            if (itens.get(posicao).getMarca().equals(marca)) {
                achou = posicao;
            }
        }
        if (achou < 0) {
            
            itemMarca = new ItemMarca();
            itemMarca.setCodigo(marca.getCodigo());
            itemMarca.setMarca(marca);
            itens.add(itemMarca);

        } else {
            
            JSFUtil.adicionaMensagemErro("Marca já adicionada!");
        }
    }

    public void removeItemMarca(ActionEvent evento) {
        
        itemMarca = (ItemMarca) evento.getComponent().getAttributes().get("ItemMarcaSelecionada");
        
        
         int achou = -1;

        for (int posicao = 0; posicao < itens.size(); posicao++) {
            if (itens.get(posicao).getMarca().equals(itemMarca.getMarca())) {
                achou = posicao;
            }
        }
        if (achou >-1) {
            
            itens.remove(achou);
            
        } else {
        }
        
    }

    public ArrayList<ItemMarca> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemMarca> itens) {
        this.itens = itens;
    }

    public ArrayList<ItemMarca> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<ItemMarca> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ItemMarcaDao getDao() {
        return dao;
    }

    public void setDao(ItemMarcaDao dao) {
        this.dao = dao;
    }

    public ItemMarca getItemMarca() {
        return itemMarca;
    }

    public void setItemMarca(ItemMarca itemMarca) {
        this.itemMarca = itemMarca;
    }

}
