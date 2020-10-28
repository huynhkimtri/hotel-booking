/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import trihk.hotelbooking.entity.User;
import trihk.hotelbooking.helper.DBHelper;

/**
 *
 * @author TriHuynh
 */
public class UserDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TheHotelPU");

    public User insert(User acc) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(acc);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return acc;
    }

    public User update(User acc) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(acc);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return acc;
    }

    public User getByUsernameAndPassword(String email, String password) {
        EntityManager em = DBHelper.getEntityManager();
        User acc = null;
        try {
            em.getTransaction().begin();
            List<User> list = em.createNamedQuery("User.findByEmailAndPassword")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getResultList();
            if (!list.isEmpty()) {
                acc = list.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return acc;
    }

    public boolean checkByEmail(String email) {
        boolean isFound = false;
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            User acc = (User) em.createNamedQuery("User.findByEmail")
                    .setParameter("email", email)
                    .getSingleResult();
            if (acc != null) {
                isFound = true;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return isFound;
    }

    public User getByEmail(String email) {
        User acc = null;
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            acc = (User) em.createNamedQuery("User.findByEmail")
                    .setParameter("email", email)
                    .getSingleResult();

            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return acc;
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
