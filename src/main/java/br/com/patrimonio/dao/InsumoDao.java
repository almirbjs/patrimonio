package br.com.patrimonio.dao;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.patrimonio.domain.Insumo;
import br.com.patrimonio.util.HibernateUtil;

public class InsumoDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public void salvar(Insumo insumo) {

        try {

            transacao = sessao.beginTransaction();

            sessao.save(insumo);

            transacao.commit();

        } catch (RuntimeException erro) {

            if (transacao != null) {

                transacao.rollback();
            }

            throw erro;

        } finally {

            sessao.close();
        }

    }

    public Insumo BuscaPorCodigo(int codigo) {
        
        sessao = HibernateUtil.getSessionFactory().openSession();

        Insumo c = null;
        try {
            //
            org.hibernate.Query consulta = sessao.getNamedQuery("Insumo.buscarPorCodigo");
            // consulta.setInteger("codigo", codigo);o primeiro codigo � o da
            // @NamedQuery ou segundo � o da entidade.
            consulta.setInteger("codigo", codigo);
            // uniqueResult() busca apenas um resultado
            c = (Insumo) consulta.uniqueResult();

        } catch (RuntimeException ex) {
            throw ex;

        } finally {
            sessao.close();
        }

        return c;

    }

    public void excluir(Insumo insumo) throws Exception {

        try {

            transacao = sessao.beginTransaction();

            sessao.delete(insumo);

            transacao.commit();

            try {
                /*
                Query consulta = sessao.getNamedQuery("ItemMarca.buscarPorCodigoInsumo");
                insumo = (ArrayList<br.com.patrimonio.domain.Insumo>) consulta.list();*/

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

            if (transacao != null) {

                transacao.rollback();

            }
            throw e;

        } finally {

            sessao.close();
        }

    }

    public void alterar(Insumo c) throws Exception {
        
        sessao = HibernateUtil.getSessionFactory().openSession();
        
        transacao = null;

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
    public ArrayList<Insumo> listar() {

        sessao = HibernateUtil.getSessionFactory().openSession();

        // iniciamos a lista nula porque n�o tenhos entidades ainda listada
        ArrayList<Insumo> Insumo = null;

        try {

            Query consulta = sessao.getNamedQuery("Insumo.listar");
            Insumo = (ArrayList<br.com.patrimonio.domain.Insumo>) consulta.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // retorna entidades
        return Insumo;

    }

}
