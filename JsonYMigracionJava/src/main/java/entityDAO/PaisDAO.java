package entityDAO;

import entity.Pais;

/**
 * La claes PaisDAO extiende de GenericDao que nos proporciona el EntityManagerFactory y el EntityManager
 * @author Bruno Tarditi
 */
public class PaisDAO extends GenericDao {

    /**
     * Busca si en la clase Pais el código no es nulo
     * @param pais Pais
     * @return true si el codigo no es nulo, false si lo es
     */
    public boolean buscarPorCodigo(Pais pais) {
        entityManager = getEntityManager();
        return entityManager.find(Pais.class, pais.getCodigoPais()) != null;
    }

    /**
     * Este método se encarga de persistir nuestra entidad Pais en la base de datos
     * @param pais Pais
     */
    public void insertar(Pais pais) {

        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(pais);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Este método se encarga de actualizar nuestra entidad Pais en la base de datos
     * @param pais Pais
     */
    public void actualizar(Pais pais) {

        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(pais);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
