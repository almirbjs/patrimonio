package br.com.patrimonio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.patrimonio.domain.Usuario;
import br.com.patrimonio.util.HibernateUtil;

public class GenericaDao<E> {
    // Esta linha ela captura no hibernateUtil a nossa fabrica de SessionFactory
    // e na fabrica de sess�o pega uma sessao aberta e armazena na minha
    // variavel sessao que � o do tipo Session.

    Session sessao = HibernateUtil.getSessionFactory().openSession();

    Transaction transacao = null;

    private Usuario usuario;

    // throws Exception :quem Chamar esse metodo � obrigado a tratar
    public void salvar(E Entidade) {

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // save: Salva a opera��o.
            sessao.save(Entidade);
            // comfimar � opera��o.
            transacao.commit();

        } catch (RuntimeException e) {
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

    // Cria uma lista com todos os usuario
    @SuppressWarnings("unchecked")
    public List<Usuario> listar() {
        // Criar um variavel para armazenar a minha lista
        // Ela esta iniciando nula.

        List<Usuario> usuarios = null;
        try {
            Query consulta = sessao.getNamedQuery("Usuario.listar");
            usuarios = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }
        return usuarios;
    }

    public Usuario BuscarPorCodigo(int codigo) {
        usuario = null;
        try {
            //
            org.hibernate.Query consulta = sessao.getNamedQuery("Usuario.buscarPorCodigo");
            // consulta.setInteger("codigo", codigo);o primeiro codigo � o da
            // @NamedQuery ou segundo � o da entidade.
            consulta.setInteger("codigo", codigo);
            // uniqueResult() busca apenas um resultado
            usuario = (Usuario) consulta.uniqueResult();

        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }

        return usuario;

    }

    public void excluir(E Entidade) {

        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();
            // save: Salva a opera��o.
            sessao.delete(Entidade);
            // comfimar � opera��o.
            transacao.commit();

        } catch (RuntimeException e) {
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

    public void alterar(E Entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            // beginTransaction(): inicia a transa��o.
            transacao = sessao.beginTransaction();

            // save: updade a opera��o.
            sessao.update(Entidade);
            // comfimar � opera��o.
            transacao.commit();

        } catch (RuntimeException e) {
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

}
