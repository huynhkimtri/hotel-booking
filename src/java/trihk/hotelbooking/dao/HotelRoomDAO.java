/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import trihk.hotelbooking.entity.HotelRoom;
import trihk.hotelbooking.helper.DBHelper;

/**
 *
 * @author TriHuynh
 */
public class HotelRoomDAO implements Serializable {

    public HotelRoom insert(HotelRoom room) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return room;
    }

    public HotelRoom update(HotelRoom room) {
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(room);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return room;
    }

    public HotelRoom getOne(int id) {
        EntityManager em = DBHelper.getEntityManager();
        HotelRoom room = null;
        try {
            em.getTransaction().begin();
            room = em.find(HotelRoom.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return room;
    }

    public List<HotelRoom> getListHotelRoom(int limit, int index) {
        List<HotelRoom> list = new ArrayList<>();
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("HotelRoom.findAll")
                    .setFirstResult(limit * index)
                    .setMaxResults(limit)
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

    public List<HotelRoom> getListHotelRoom(int areaId, Date checkin, Date checkout, int limit, int index) {
        List<HotelRoom> list = new ArrayList<>();
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("HotelRoom.findAll")
                    .setFirstResult(limit * index)
                    .setMaxResults(limit)
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

    public List<HotelRoom> getListHotelRoom(boolean isActive, int limit, int index) {
        List<HotelRoom> list = new ArrayList<>();
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("HotelRoom.findAllByIsActive")
                    .setParameter("isActive", isActive)
                    .setFirstResult(limit * index)
                    .setMaxResults(limit)
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

    public List<HotelRoom> getListHotelRoom(String likeName, int minPrice, int maxPrice, int categoryId) {
        List<HotelRoom> list = new ArrayList<>();
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("HotelRoom.findByNameAndPriceAndCategory")
                    .setParameter("name", "N'%" + likeName + "%")
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .setParameter("cateId", categoryId)
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

    public List<HotelRoom> getListHotelRoom(String likeName, int minPrice, int maxPrice, boolean isActive, int limit, int index) {
        List<HotelRoom> list = new ArrayList<>();
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("HotelRoom.findByNameAndPrice")
                    .setParameter("name", "%" + likeName + "%")
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .setParameter("isActive", isActive)
                    .setFirstResult(limit * index)
                    .setMaxResults(limit)
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

    public List<HotelRoom> getListHotelRoom(String likeName, int minPrice, int maxPrice, int categoryId, boolean isActive, int limit, int index) {
        List<HotelRoom> list = new ArrayList<>();
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("HotelRoom.findByNameAndPriceAndCategory")
                    .setParameter("name", "%" + likeName + "%")
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .setParameter("cateId", categoryId)
                    .setParameter("isActive", isActive)
                    .setFirstResult(limit * index)
                    .setMaxResults(limit)
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

    public int getCount() {
        int count = 0;
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            List<HotelRoom> list = em.createNamedQuery("HotelRoom.findAll").getResultList();
            count = list.size();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return count;
    }

    public int getCount(boolean isActive) {
        int count = 0;
        EntityManager em = DBHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            List<HotelRoom> list = em.createNamedQuery("HotelRoom.findAllByIsActive")
                    .setParameter("isActive", isActive)
                    .getResultList();
            count = list.size();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return count;
    }

    public int getCount(String likeName, int minPrice, int maxPrice, int categoryId, boolean isActive) {
        int count = 0;
        EntityManager entityManager = DBHelper.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<HotelRoom> list = entityManager.createNamedQuery("HotelRoom.findByNameAndPriceAndCategory")
                    .setParameter("name", "%" + likeName + "%")
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .setParameter("cateId", categoryId)
                    .setParameter("isActive", isActive)
                    .getResultList();
            count = list.size();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return count;
    }

    public int getCount(String likeName, int minPrice, int maxPrice, boolean isActive) {
        int count = 0;
        EntityManager entityManager = DBHelper.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<HotelRoom> list = entityManager.createNamedQuery("HotelRoom.findByNameAndPrice")
                    .setParameter("name", "%" + likeName + "%")
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .setParameter("isActive", isActive)
                    .getResultList();
            count = list.size();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return count;
    }
}
