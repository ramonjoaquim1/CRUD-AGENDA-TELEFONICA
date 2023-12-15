package br.com.senac.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	private static final EntityManagerFactory emf;
	
	/**
     * Static block for creating EntityManagerFactory. The Persistence class looks for META-INF/persistence.xml in the classpath.
     */
    static {
        emf = Persistence.createEntityManagerFactory("ProjetoExemploPU");
    }

   
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();

    }
}
