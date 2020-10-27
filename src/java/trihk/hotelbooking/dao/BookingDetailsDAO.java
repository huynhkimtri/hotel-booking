/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import trihk.hotelbooking.entity.BookingDetails;
import static trihk.hotelbooking.entity.BookingDetails_.orderId;
import trihk.hotelbooking.helper.DBHelper;

/**
 *
 * @author TriHuynh
 */
public class BookingDetailsDAO {

    public BookingDetails insert(BookingDetails orderDetail) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(orderDetail);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return orderDetail;
    }

    public BookingDetails update(BookingDetails orderDetail) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(orderDetail);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return orderDetail;
    }

    public BookingDetails remove(BookingDetails orderDetail) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(orderDetail)) {
                orderDetail = em.merge(orderDetail);
            }
            em.remove(orderDetail);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return orderDetail;
    }

    public BookingDetails getOne(int id) {
        EntityManager em = DBHelper.getEntityManager();
        BookingDetails orderDetail = null;
        try {
            em.getTransaction().begin();
            orderDetail = em.find(BookingDetails.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return orderDetail;
    }

    public List<BookingDetails> getListBookingDetailsByOrderId(int orderId) {
        List<BookingDetails> list = new ArrayList<>();
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("BookingDetails.findByOrderId")
                    .setParameter("id", orderId)
                    .getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return list;
    }

    public List<BookingDetails> getListBookingDetailsInPeriod(Date checkin, Date checkout) {
        List<BookingDetails> list = new ArrayList<>();
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("BookingDetails.findByOrderId")
                    .setParameter("id", orderId)
                    .getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return list;
    }
}
