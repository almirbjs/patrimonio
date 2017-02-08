package br.com.patrimonio.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.patrimonio.domain.Insumo;
import br.com.patrimonio.domain.ItemMarca;
import br.com.patrimonio.domain.Marca;

import br.com.patrimonio.util.HibernateUtil;
import java.util.List;

public class InsumoDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public void salvar(Insumo insumo, List<ItemMarca> itensMarca) {

        try {
            transacao = sessao.beginTransaction();

  
			sessao.save(insumo);
			
			for(int posicao = 0; posicao < itensMarca.size(); posicao++){
				ItemMarca itemMarca = itensMarca.get(posicao);
				itemMarca.setInsumo(insumo);
				
				sessao.save(itemMarca);
			}

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
        Session sessao = HibernateUtil.getSessionFactory().openSession();

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

    public void excluir(Insumo c) throws Exception {
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

    public void alterar(Insumo c) throws Exception {
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
    public ArrayList<Insumo> listar() {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

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
