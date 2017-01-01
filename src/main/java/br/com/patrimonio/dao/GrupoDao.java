package br.com.patrimonio.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.patrimonio.domain.Grupo;
import br.com.patrimonio.util.HibernateUtil;

public class GrupoDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public void salvar(Grupo c) throws Exception {

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // save: Salva a opera��o.
            sessao.save(c);
            // comfimar � opera��o.
            transacao.commit();

        } catch (Exception e) {
            // Mensagem de erro
            if (transacao != null) {
                // Se algo der errado eu utilizo rollback para disfazer a
                // transa��o.
                transacao.rollback();

            }
            throw e;// For�a o usuario escrever a mensagem de erro para ser
            // exibida na tela.

            // finally{} � o finalizador
        } finally {
            // Fecha sess�o.
            sessao.close();
        }

    }

    public Grupo BuscaPorCodigo(int codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Grupo c = null;
        try {
            //
            org.hibernate.Query consulta = sessao.getNamedQuery("Grupo.buscarPorCodigo");
            // consulta.setInteger("codigo", codigo);o primeiro codigo � o da
            // @NamedQuery ou segundo � o da entidade.
            consulta.setInteger("codigo", codigo);
            // uniqueResult() busca apenas um resultado
            c = (Grupo) consulta.uniqueResult();

        } catch (RuntimeException ex) {
            throw ex;

        } finally {
            sessao.close();
        }

        return c;

    }

    public void excluir(Grupo c) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // delete: deleta a opera��o.
            sessao.delete(c);
            // comfimar � opera��o.
            transacao.commit();

        } catch (Exception e) {
            // Mensagem de erro
            if (transacao != null) {
                // Se algo der errado eu utilizo rollback para disfazer a
                // transa��o.
                transacao.rollback();

            }
            throw e;// For�a o usuario escrever a mensagem de erro para ser
            // exibida na tela.

            // finally{} � o finalizador
        } finally {
            // Fecha sess�o.
            sessao.close();
        }

    }

    public void alterar(Grupo c) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // update: altera os dados da entidade.
            sessao.update(c);
            // comfimar � opera��o.
            transacao.commit();

        } catch (Exception e) {
            // Mensagem de erro
            if (transacao != null) {
                // Se algo der errado eu utilizo rollback para disfazer a
                // transa��o.
                transacao.rollback();

            }
            throw e;// For�a o usuario escrever a mensagem de erro para ser
            // exibida na tela.

            // finally{} � o finalizador
        } finally {
            // Fecha sess�o.
            sessao.close();
        }

    }

    // Lista de entidades
    @SuppressWarnings("unchecked")
    public ArrayList<Grupo> listar() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque n�o tenhos entidades ainda listada
        ArrayList<Grupo> Grupo = null;

        try {

            Query consulta = sessao.getNamedQuery("Grupo.listar");
            Grupo = (ArrayList<br.com.patrimonio.domain.Grupo>) consulta.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // retorna entidades
        return Grupo;

    }

}
