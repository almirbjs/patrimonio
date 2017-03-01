package br.com.patrimonio.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.patrimonio.domain.Produto;
import br.com.patrimonio.util.HibernateUtil;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProdutoDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public Produto salvar(Produto u) {

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();

            if (u.getCaminhoTemporarioProduto() == null) {
                u.setExisteImagem("nao");
            }
            // save: Salva a operação.
            Produto retorno = (Produto) sessao.merge(u);
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

    @SuppressWarnings("unchecked")
    public ArrayList<Produto> BuscaPorCodigoDoFornecedor(int codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        ArrayList<Produto> estado = null;
        try {

            //
            org.hibernate.Query consulta = sessao.getNamedQuery("Produto.buscarPorCodigoFornecedor");
            // consulta.setInteger("codigo", codigo);o primeiro codigo � o da
            // @NamedQuery ou segundo � o da entidade.
            consulta.setInteger("codigo", codigo);
            // uniqueResult() busca apenas um resultado
            estado = (ArrayList<br.com.patrimonio.domain.Produto>) consulta.list();

        } catch (RuntimeException ex) {
            throw ex;

        } finally {
            sessao.close();
        }

        return estado;

    }

    public void excluir(Produto e) throws Exception {
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

    public void alterar(Produto u) {
        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            if (u.getCaminhoTemporarioProduto() != null) {
                u.setExisteImagem("sim");
            }

            // save: Salva a operação.
            sessao.update(u);
            // comfimar a operação.
            transacao.commit();

        } catch (RuntimeException e) {
            // Mensagem de erro
            if (transacao != null) {
                // Se algo der errado eu utilizo rollback para disfazer a
                // transação.
                transacao.rollback();

            }
        } finally {
            // Fecha sess�o.
            sessao.close();
        }

    }

    // Lista de entidades
    @SuppressWarnings("unchecked")
    public ArrayList<Produto> listar() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque n�o tenhos entidades ainda listada
        ArrayList<Produto> Produto = null;

        try {

            Query consulta = sessao.getNamedQuery("Produto.listar");
            Produto = (ArrayList<br.com.patrimonio.domain.Produto>) consulta.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // retorna entidades
        return Produto;

    }

}
