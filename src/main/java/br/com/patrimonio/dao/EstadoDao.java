package br.com.patrimonio.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.patrimonio.domain.Estado;
import br.com.patrimonio.util.HibernateUtil;

public class EstadoDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public void salvar(Estado e) throws Exception {

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
    public ArrayList<Estado> BuscaPorCodigoDoPais(int codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        ArrayList<Estado> estado = null;
        try {

            //
            org.hibernate.Query consulta = sessao.getNamedQuery("Estado.buscarPorCodigoPais");
            // consulta.setInteger("codigo", codigo);o primeiro codigo � o da
            // @NamedQuery ou segundo � o da entidade.
            consulta.setInteger("codigo", codigo);
            // uniqueResult() busca apenas um resultado
            estado = (ArrayList<br.com.patrimonio.domain.Estado>) consulta.list();

        } catch (RuntimeException ex) {
            throw ex;

        } finally {
            sessao.close();
        }

        return estado;

    }

    public void excluir(Estado e) throws Exception {
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

    public void alterar(Estado e) throws Exception {
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
    public ArrayList<Estado> listar() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque n�o tenhos entidades ainda listada
        ArrayList<Estado> Estado = null;

        try {

            Query consulta = sessao.getNamedQuery("Estado.listar");
            Estado = (ArrayList<br.com.patrimonio.domain.Estado>) consulta.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // retorna entidades
        return Estado;

    }

}
