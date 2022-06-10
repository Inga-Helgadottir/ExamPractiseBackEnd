package facades;

import entities.ManySide;
import entities.OneSide;
import entities.OtherManySide;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeTest {

    private static EntityManagerFactory emf;
    private static FacadeExample facade;

    public FacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = FacadeExample.getFacadeExample(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("ManySide.deleteAllRows").executeUpdate();
            em.createNativeQuery("ALTER TABLE ManySide AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("OtherManySide.deleteAllRows").executeUpdate();
            em.createNativeQuery("ALTER TABLE OtherManySide AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("OneSide.deleteAllRows").executeUpdate();
            em.createNativeQuery("ALTER TABLE OneSide AUTO_INCREMENT = 1").executeUpdate();
            em.getTransaction().commit();

            em.getTransaction().begin();
            ManySide ms = new ManySide("first many side");
            ManySide ms2 = new ManySide("second many side");
            ManySide ms3 = new ManySide("third many side");
            OtherManySide oms = new OtherManySide("first other many side");
            OtherManySide oms2 = new OtherManySide("second other many side");
            OtherManySide oms3 = new OtherManySide("third other many side");
            OneSide os = new OneSide("one side");

            ms.setOneSide(os);
            ms2.setOneSide(os);
            ms3.setOneSide(os);

            ms.addToOtherManySides(oms);
            ms.addToOtherManySides(oms2);
            ms2.addToOtherManySides(oms2);
            ms2.addToOtherManySides(oms3);
            ms3.addToOtherManySides(oms3);
            ms3.addToOtherManySides(oms);

            em.persist(ms);
            em.persist(ms2);
            em.persist(ms3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    

}
