package br.com.patrimonio.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.patrimonio.domain.Patrimonio;
import br.com.patrimonio.util.HibernateUtil;

public class PatrimonioDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public void salvar(Patrimonio e) throws Exception {

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
    public ArrayList<Patrimonio> BuscaPorCodigoDoFornecedor(int codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        ArrayList<Patrimonio> estado = null;
        try {

            //
            org.hibernate.Query consulta = sessao.getNamedQuery("Patrimonio.buscarPorCodigoFornecedor");
            // consulta.setInteger("codigo", codigo);o primeiro codigo � o da
            // @NamedQuery ou segundo � o da entidade.
            consulta.setInteger("codigo", codigo);
            // uniqueResult() busca apenas um resultado
            estado = (ArrayList<br.com.patrimonio.domain.Patrimonio>) consulta.list();

        } catch (RuntimeException ex) {
            throw ex;

        } finally {
            sessao.close();
        }

        return estado;

    }

    public void excluir(Patrimonio e) throws Exception {
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

    public void alterar(Patrimonio e) throws Exception {
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
    public ArrayList<Patrimonio> listar() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque n�o tenhos entidades ainda listada
        ArrayList<Patrimonio> Patrimonio = null;

        try {

            Query consulta = sessao.getNamedQuery("Patrimonio.listar");
            Patrimonio = (ArrayList<br.com.patrimonio.domain.Patrimonio>) consulta.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // retorna entidades
        return Patrimonio;

    }

}
