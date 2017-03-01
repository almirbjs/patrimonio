package br.com.patrimonio.dao;

import br.com.patrimonio.domain.DocumentoFiscal;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.patrimonio.domain.Usuario;
import br.com.patrimonio.util.HibernateUtil;
import br.com.patrimonio.util.JSFUtil;
import java.io.IOException;

public class DocumentoFiscalDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public DocumentoFiscal salvar(DocumentoFiscal u) {
        try {

            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // save: Salva a operação.
            DocumentoFiscal retorno = (DocumentoFiscal) sessao.merge(u);
            // comfimar a operação.
            transacao.commit();
            return retorno;

        } catch (RuntimeException e) {
            // Mensagem de erro
            if (transacao != null) {
                // Se algo der errado eu utilizo rollback para disfazer a
                // transação.
                transacao.rollback();

            }
            throw e;// Força o usuario escrever a mensagem de erro para ser
            // exibida na tela.

            // finally{} � o finalizador
        } finally {
            // Fecha sess�o.
            sessao.close();
        }

    }

    public Usuario BuscaPorCodigo(int codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Usuario u = null;
        try {
            //
            org.hibernate.Query consulta = sessao.getNamedQuery("DocumentoFiscal.buscarPorCodigo");
            // consulta.setInteger("codigo", codigo);o primeiro codigo � o da
            // @NamedQuery ou segundo  o da entidade.
            consulta.setInteger("codigo", codigo);
            // uniqueResult() busca apenas um resultado
            u = (Usuario) consulta.uniqueResult();

        } catch (RuntimeException ex) {
            throw ex;

        } finally {
            sessao.close();
        }

        return u;

    }

    public void excluir(DocumentoFiscal u) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // delete: deleta a opera��o.
            sessao.delete(u);
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

    public void alterar(DocumentoFiscal u) {

    }

    // Lista de entidades
    @SuppressWarnings("unchecked")
    public ArrayList<DocumentoFiscal> listar() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque n�o tenhos entidades ainda listada
        ArrayList<DocumentoFiscal> DocumentoFiscal = null;

        try {

            Query consulta = sessao.getNamedQuery("DocumentoFiscal.listar");
            DocumentoFiscal = (ArrayList<br.com.patrimonio.domain.DocumentoFiscal>) consulta.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // retorna entidades
        return DocumentoFiscal;

    }

}
