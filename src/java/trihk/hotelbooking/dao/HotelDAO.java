/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import trihk.hotelbooking.entity.Hotel;
import trihk.hotelbooking.helper.DBHelper;

/**
 *
 * @author TriHuynh
 */
public class HotelDAO {

    public Hotel insert(Hotel hotel) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hotel);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return hotel;
    }

    public Hotel update(Hotel hotel) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(hotel);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return hotel;
    }

    public Hotel getById(int hotelId) {
        EntityManager em = DBHelper.getEntityManager();
        Hotel hotel = null;
        try {
            em.getTransaction().begin();
            hotel = em.find(Hotel.class, hotelId);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return hotel;
    }

    public List<Hotel> getList() {
        EntityManager em = DBHelper.getEntityManager();
        List<Hotel> list = null;
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("Hotel.findAll").getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return list;
    }

    public List<Hotel> getList(int areaId) {
        EntityManager em = DBHelper.getEntityManager();
        List<Hotel> list = null;
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("Hotel.findByArea").setParameter("id", areaId).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return list;
    }

    public List<Hotel> getAllHotels() {
        EntityManager em = DBHelper.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<Hotel> hotels = em.createNamedQuery("Hotel.findAll")
                    .getResultList();

            transaction.commit();
            return hotels;
        } catch (Exception e) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return new ArrayList<>();
    }

    public List<Hotel> getAllHotels(String name, String location) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<Hotel> hotels = em.createNamedQuery("Hotel.findAllWith")
                    .setParameter("name", "%" + name + "%")
                    .setParameter("location", "%" + location + "%")
                    .getResultList();

            transaction.commit();
            return hotels;
        } catch (Exception e) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return new ArrayList<>();
    }

    public List<Hotel> getAllHotels(String name, int areaId) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<Hotel> hotels = em.createNamedQuery("Hotel.findAllWith")
                    .setParameter("name", "%" + name + "%")
                    .setParameter("areaId", areaId)
                    .getResultList();

            transaction.commit();
            return hotels;
        } catch (Exception e) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return new ArrayList<>();
    }

    public List<Hotel> getAllHotels(int areaId) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<Hotel> hotels = em.createNamedQuery("Hotel.findByAreaId")
                    .setParameter("areaId", areaId)
                    .getResultList();

            transaction.commit();
            return hotels;
        } catch (Exception e) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return new ArrayList<>();
    }

}
