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
import trihk.hotelbooking.entity.Account;
import trihk.hotelbooking.helper.DBHelper;

/**
 *
 * @author TriHuynh
 */
public class AccountDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TheHotelPU");

    public Account insert(Account acc) {
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

    public Account update(Account acc) {
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

    public Account getByAccountnameAndPassword(String email, String password) {
        EntityManager em = DBHelper.getEntityManager();
        Account acc = null;
        try {
            em.getTransaction().begin();
            List<Account> list = em.createNamedQuery("Account.findByEmailAndPassword")
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
            Account acc = (Account) em.createNamedQuery("Account.findByEmail")
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

    public Account getByEmail(String email) {
        Account acc = null;
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            acc = (Account) em.createNamedQuery("Account.findByEmail")
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
