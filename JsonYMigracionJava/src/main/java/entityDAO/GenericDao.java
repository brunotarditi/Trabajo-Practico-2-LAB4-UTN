package entityDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Generic Dao se encarga de crear un EntityManagerFactory y a través de este un EntityManager
 * @author Bruno Tarditi
 */
public abstract class GenericDao {
    protected static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static final String PU = "PersistencePU";

    /**
     * En el constructor iniciamos el EntityManagerFactory que básicamente es la clase que se encarga de
     * abrir la conexión a la base de datos
     */
    public GenericDao() {
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory(PU);
        }
    }

    /**
     * Creado ya el EntityManagerFactory se procede a crear el EntityManager que interactua con los datos y
     * representa un contexto de persistencia
     * @return EntityManager
     */
    protected  EntityManager getEntityManager(){
        if (entityManager == null){
            entityManager = entityManagerFactory.createEntityManager();
        }
        return  entityManager;
    }
}
