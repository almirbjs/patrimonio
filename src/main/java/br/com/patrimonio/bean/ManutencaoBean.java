package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.patrimonio.dao.ManutencaoDao;
import br.com.patrimonio.dao.PatrimonioDao;
import br.com.patrimonio.domain.Manutencao;
import br.com.patrimonio.domain.Patrimonio;
import br.com.patrimonio.util.JSFUtil;
import java.util.List;
import org.primefaces.event.SelectEvent;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBManutencao") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class ManutencaoBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Manutencao> itens;
    private ArrayList<Manutencao> itensFiltrados;// Vai armazenar os itens filtrados
    private ArrayList<Patrimonio> itensPatrimonio; // Carrega o combox com o nome dos
    private ManutencaoDao dao = new ManutencaoDao();
    private Manutencao manutencao = new Manutencao();
    private Patrimonio patrimonio = new Patrimonio();

    @PostConstruct
    public void prepararPesquisa() {
        try {
            ManutencaoDao dao = new ManutencaoDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            manutencao = new Manutencao();
            PatrimonioDao dao = new PatrimonioDao();
            itensPatrimonio = dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {
           patrimonio.getCodigo();
            ManutencaoDao dao = new ManutencaoDao();
            manutencao.setStatus("Aguardando reparo...");
            dao.salvar(manutencao);
            patrimonio = manutencao.getPatrimonio();
            PatrimonioDao patrimonioDao = new PatrimonioDao();
           patrimonio.setFuncionando("N");
           
            patrimonioDao.alterar(patrimonio);

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
            //manutencao = new Manutencao();
            PatrimonioDao daoPatrimonio = new PatrimonioDao();
            itensPatrimonio = daoPatrimonio.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new ManutencaoDao();
            dao.alterar(manutencao);

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

            ManutencaoDao dao = new ManutencaoDao();
            dao.excluir(manutencao);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    public void patrimonioSelecionado(SelectEvent event) {
        Patrimonio p = (Patrimonio) event.getObject();
        manutencao.setPatrimonio(p);
        PatrimonioDao patrimonioDao = new PatrimonioDao();
        itensPatrimonio = patrimonioDao.listar();

    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public ArrayList<Patrimonio> listaFuncionando() {
 itensPatrimonio = null;
        
       {
           if (itensPatrimonio==null) {
               
                    
           PatrimonioDao patrimonioDao = new PatrimonioDao();
        itensPatrimonio = patrimonioDao.listarFuncionando();

           }
        
        return itensPatrimonio;
    }
    }
  
    
    
    public ArrayList<Manutencao> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Manutencao> itens) {
        this.itens = itens;
    }

    public ArrayList<Manutencao> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Manutencao> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Patrimonio> getItensPatrimonio() {
        return itensPatrimonio;
    }

    public void setItensPatrimonio(ArrayList<Patrimonio> itensPatrimonio) {
        this.itensPatrimonio = itensPatrimonio;
    }

    public ManutencaoDao getDao() {
        return dao;
    }

    public void setDao(ManutencaoDao dao) {
        this.dao = dao;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

}
