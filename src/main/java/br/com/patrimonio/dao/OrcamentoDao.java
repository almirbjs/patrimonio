package br.com.patrimonio.dao;

import br.com.patrimonio.domain.Orcamento;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.patrimonio.domain.Patrimonio;
import br.com.patrimonio.util.HibernateUtil;
import java.util.List;

public class OrcamentoDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public void salvar(Orcamento e) throws Exception {

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // save: Salva a opera��o.
            sessao.save(e);
            // comfimar � opera��o.
            transacao.commit();

        } catch (Exception ex) {
            // Mensagem de erro
            if (transacao != null) {
                // Se algo der errado eu utilizo rollback para disfazer a
                // transa��o.
                transacao.rollback();

            }
            throw ex;// For�a o usuario escrever a mensagem de erro para ser
            // exibida na tela.

            // finally{} � o finalizador
        } finally {
            // Fecha sess�o.
            sessao.close();
        }

    }

    @SuppressWarnings("unchecked")
    public ArrayList<Orcamento> BuscaPorCodigoDoOrcamento(int codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        ArrayList<Orcamento> o = null;
        try {

            //
            org.hibernate.Query consulta = sessao.getNamedQuery("Orcamento.buscarPorCodigoOrcamento");
            // consulta.setInteger("codigo", codigo);o primeiro codigo � o da
            // @NamedQuery ou segundo � o da entidade.
            consulta.setInteger("codigo", codigo);
            // uniqueResult() busca apenas um resultado
            o= (ArrayList<br.com.patrimonio.domain.Orcamento>) consulta.list();

        } catch (RuntimeException ex) {
            throw ex;

        } finally {
            sessao.close();
        }

        return o;

    }

    public void excluir(Orcamento e) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // delete: deleta a opera��o.
            sessao.delete(e);
            // comfimar � opera��o.
            transacao.commit();

        } catch (Exception ex) {
            // Mensagem de erro
            if (transacao != null) {
                // Se algo der errado eu utilizo rollback para disfazer a
                // transa��o.
                transacao.rollback();

            }
            throw ex;// For�a o usuario escrever a mensagem de erro para ser
            // exibida na tela.

            // finally{} � o finalizador
        } finally {
            // Fecha sess�o.
            sessao.close();
        }

    }

    public void alterar(Orcamento e) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // update: altera os dados da entidade.
            sessao.update(e);
            // comfimar � opera��o.
            transacao.commit();

        } catch (Exception ex) {
            // Mensagem de erro
            if (transacao != null) {
                // Se algo der errado eu utilizo rollback para disfazer a
                // transa��o.
                transacao.rollback();

            }
            throw ex;// For�a o usuario escrever a mensagem de erro para ser
            // exibida na tela.

            // finally{} � o finalizador
        } finally {
            // Fecha sess�o.
            sessao.close();
        }

    }

    // Lista de entidades
    @SuppressWarnings("unchecked")
    public ArrayList<Orcamento> listar() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque n�o tenhos entidades ainda listada
        ArrayList<Orcamento> Orcamento = null;

        try {

            Query consulta = sessao.getNamedQuery("Patrimonio.listar");
            Orcamento= (ArrayList<br.com.patrimonio.domain.Orcamento>) consulta.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // retorna entidades
        return Orcamento;

    }
// Lista de entidades
    @SuppressWarnings("unchecked")
    public ArrayList<Orcamento> listarOrcamento() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque nao tenhos entidades ainda listada
        ArrayList<Orcamento> Orcamento = null;

        try {

            Query consulta = sessao.getNamedQuery("Patrimonio.listarFuncionando");
            Orcamento = (ArrayList<br.com.patrimonio.domain.Orcamento>) consulta.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // retorna entidades
        return Orcamento;

    }
   
    
    
    
}
