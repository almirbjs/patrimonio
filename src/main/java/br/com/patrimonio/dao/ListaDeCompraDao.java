package br.com.patrimonio.dao;

import br.com.patrimonio.domain.Insumo;
import br.com.patrimonio.domain.ItemMarca;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.patrimonio.domain.ListaDeCompra;
import br.com.patrimonio.domain.Marca;
import br.com.patrimonio.util.HibernateUtil;
import java.util.List;

public class ListaDeCompraDao {

    Session sessao = HibernateUtil.getSessionFactory().openSession();
    Transaction transacao = null;

    public void salvar(ListaDeCompra c) throws Exception {

        try {

            transacao = sessao.beginTransaction();

            sessao.save(c);

            transacao.commit();

        } catch (Exception e) {

            if (transacao != null) {

                transacao.rollback();

            }
            throw e;

        } finally {

            sessao.close();
        }

    }

    public ListaDeCompra BuscaPorCodigo(int codigo) {

        ListaDeCompra c = null;
        try {

            org.hibernate.Query consulta = sessao.getNamedQuery("ListaDeCompra.buscarPorCodigo");

            consulta.setInteger("codigo", codigo);

            c = (ListaDeCompra) consulta.uniqueResult();

        } catch (RuntimeException ex) {
            throw ex;

        } finally {
            sessao.close();
        }

        return c;

    }

    public void excluir(ListaDeCompra c) throws Exception {

        try {

            transacao = sessao.beginTransaction();

            sessao.delete(c);

            transacao.commit();

        } catch (Exception e) {

            if (transacao != null) {

                transacao.rollback();

            }
            throw e;

        } finally {

            sessao.close();
        }

    }

    public void alterar(ListaDeCompra c) throws Exception {

        try {

            transacao = sessao.beginTransaction();

            sessao.update(c);

            transacao.commit();

        } catch (Exception e) {

            if (transacao != null) {

                transacao.rollback();

            }

            throw e;

        } finally {

            sessao.close();
        }

    }

    @SuppressWarnings("unchecked")
    public ArrayList<ListaDeCompra> listar() {

        ArrayList<ListaDeCompra> ListaDeCompra = null;

        try {

            Query consulta = sessao.getNamedQuery("ListaDeCompra.listar");
            ListaDeCompra = (ArrayList<br.com.patrimonio.domain.ListaDeCompra>) consulta.list();

        } catch (Exception e) {

            e.printStackTrace();

        }
        // retorna entidades
        return ListaDeCompra;

    }

    public void salvarItemMarca(List<ItemMarca> itensMarca) {
        
        
        try {
            transacao = sessao.beginTransaction();

            for (int posicao = 0; posicao < itensMarca.size(); posicao++) {
                ItemMarca itemMarca = itensMarca.get(posicao);
                

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

}
