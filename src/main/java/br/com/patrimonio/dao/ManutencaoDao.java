package br.com.patrimonio.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.patrimonio.domain.Manutencao;
import br.com.patrimonio.domain.Patrimonio;
import br.com.patrimonio.util.HibernateUtil;

public class ManutencaoDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public void salvar(Manutencao c) throws Exception {

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

    public Manutencao
            BuscaPorCodigo(int codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Manutencao c = null;
        try {
            //
            org.hibernate.Query consulta = sessao.getNamedQuery("Manutencao.buscarPorCodigo");
            // consulta.setInteger("codigo", codigo);o primeiro codigo � o da
            // @NamedQuery ou segundo � o da entidade.
            consulta.setInteger("codigo", codigo);
            // uniqueResult() busca apenas um resultado
            c = (Manutencao) consulta.uniqueResult();

        } catch (RuntimeException ex) {
            throw ex;

        } finally {
            sessao.close();
        }

        return c;

    }

    public void excluir(Manutencao c) throws Exception {
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

    public void alterar(Manutencao c) throws Exception {
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
    public ArrayList<Manutencao> listar() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque n�o tenhos entidades ainda listada
        ArrayList<Manutencao> Manutencao = null;

        try {

            Query consulta = sessao.getNamedQuery("Manutencao.listar");

            Manutencao = (ArrayList<br.com.patrimonio.domain.Manutencao>) consulta.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // retorna entidades
        return Manutencao;

    }

    // Lista de entidades
    @SuppressWarnings("unchecked")
    public ArrayList<Patrimonio> listarFuncionando() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque nao tenhos entidades ainda listada
        ArrayList<Patrimonio> Patrimonio = null;

        try {

            Query consulta = sessao.getNamedQuery("Patrimonio.listarFuncionando");
            Patrimonio = (ArrayList<br.com.patrimonio.domain.Patrimonio>) consulta.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // retorna entidades
        return Patrimonio;

    }

}
