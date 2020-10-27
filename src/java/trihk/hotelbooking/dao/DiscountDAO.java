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
import trihk.hotelbooking.entity.Discount;
import trihk.hotelbooking.helper.DBHelper;

/**
 *
 * @author TriHuynh
 */
public class DiscountDAO {

    public Discount getById(int id) {
        EntityManager em = DBHelper.getEntityManager();
        Discount discount = null;
        try {
            em.getTransaction().begin();
            discount = em.find(Discount.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return discount;
    }

    public Discount getByCode(String code) {
        EntityManager em = DBHelper.getEntityManager();
        Discount discount = null;
        try {
            em.getTransaction().begin();
            List<Discount> list = em.createNamedQuery("Discount.findByCode")
                    .getResultList();
            if (list.size() > 0) {
                discount = list.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return discount;
    }

}
