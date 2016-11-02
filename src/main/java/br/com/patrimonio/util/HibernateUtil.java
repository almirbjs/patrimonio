package br.com.patrimonio.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    /**
     * Tem que ser esta configura��o no projeto sen�o da erro no listar as
     * entidades.
     *
     * @author Almir Ricardo
     */
    /**
     * private static final SessionFactory sessionFactory;
     *
     * static { try {
     *
     * Configuration configuration =new Configuration();
     * configuration.configure(); // Cria��o da SessionFactory para padr�o
     * (hibernate.cfg.xml) // Aquivo de configura��o. sessionFactory =
     * configuration.buildSessionFactory(); } catch (Throwable ex) { // Mensagem
     * de erro. System.err.println("Não foi possivel inicializar a criação da
     * SessionFactory" + "(Initial SessionFactory creation failed." + ex); throw
     * new ExceptionInInitializerError(ex); } }
     *
     * public static SessionFactory getSessionFactory() { return sessionFactory;
     *
     * }
     *
     *
     */
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Criação da SessionFactory para padrão (hibernate.cfg.xml)
            // Aquivo de configuração.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Mensagem de erro.
            System.err.println("Não foi possivel inicializar a criação da SessionFactory"
                    + "(Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;

    }
}
