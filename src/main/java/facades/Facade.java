package facades;

import dtos.ManySideDTO;
import entities.ManySide;
import entities.OneSide;
import entities.OtherManySide;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;

public class Facade {
    private static Facade instance;
    private static EntityManagerFactory emf;

    private Facade() {
    }

    public static Facade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private ManySideDTO create(ManySide newManySide){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            ManySide ms = new ManySide(newManySide.getName());

            for (int i = 0; i < newManySide.getOtherManySides().size(); i++) {
                OtherManySide goms = new OtherManySide(newManySide.getOtherManySides().get(i).getName());
                ms.addToOtherManySides(goms);
            }
            OneSide os = new OneSide(newManySide.getOneSide().getName());

            ms.setOneSide(os);

            em.persist(ms);
            em.getTransaction().commit();
            return new ManySideDTO(ms);
        }finally{
            em.close();
        }
    }

    private List<ManySideDTO> read(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<ManySide> query = em.createQuery("SELECT m FROM ManySide m", ManySide.class);
            List<ManySide> manySides = query.getResultList();

            List<ManySideDTO> cdtos = ManySideDTO.getDtos(manySides);
            return cdtos;
        }finally {
            em.close();
        }
    }

    private ManySideDTO update(ManySide changedManySide){
        EntityManager em = emf.createEntityManager();
        try{
            ManySide ms = em.find(ManySide.class, changedManySide.getId());
            ms.setName(changedManySide.getName());
            ms.setOneSide(changedManySide.getOneSide());

            for (int i = 0; i < changedManySide.getOtherManySides().size(); i++) {
                OtherManySide goms = new OtherManySide(changedManySide.getOtherManySides().get(i).getName());
                ms.addToOtherManySides(goms);
            }
            return new ManySideDTO(ms);
        }finally {
            em.close();
        }
    }
    private ManySideDTO delete(int id){
        EntityManager em = getEntityManager();
        try{
            ManySide ms = em.find(ManySide.class, id);
            if (ms == null)
                throw new EntityNotFoundException("Could not remove User with id: " + id);
            em.getTransaction().begin();
            em.remove(ms);
            em.getTransaction().commit();
            return new ManySideDTO(ms);
        }finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        Facade f = getFacadeExample(emf);
    }

}
