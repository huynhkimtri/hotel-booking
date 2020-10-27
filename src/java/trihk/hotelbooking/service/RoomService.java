/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.service;

import java.text.SimpleDateFormat;
import java.util.List;
import trihk.hotelbooking.dao.HotelRoomDAO;
import trihk.hotelbooking.dao.RoomTypeDAO;
import trihk.hotelbooking.entity.HotelRoom;
import trihk.hotelbooking.entity.RoomType;
import trihk.hotelbooking.entity.Account;
import trihk.hotelbooking.helper.Constants;

/**
 *
 * @author TriHuynh
 */
public class RoomService {

    private final HotelRoomDAO cakeDao = new HotelRoomDAO();
    private final RoomTypeDAO typeDao = new RoomTypeDAO();
    private final String patternDateTime = "yyyy-MM-dd";

    public HotelRoom insert(String name, String description, String imgUrl, int price, int typeId,
            int quantity, String sCreateDate, String sExpDate, Account account) {
        HotelRoom cake = new HotelRoom();
        SimpleDateFormat formater = new SimpleDateFormat(patternDateTime);
        cake.setPrice(price);
        cake.setRoomType(typeDao.getOne(typeId));
        cake.setIsAvailable(Boolean.TRUE);
        return cakeDao.insert(cake);
    }

    public List<RoomType> getListRoomType() {
        List<RoomType> list = typeDao.getListRoomType();
        return list;
    }

    public List<HotelRoom> getListAll(boolean isActive) {
        List<HotelRoom> cakes = cakeDao.getListHotelRoom(isActive, Constants.SIZE_OF_PAGE, 0);
        return cakes;
    }

    public List<HotelRoom> getListAll() {
        List<HotelRoom> cakes = cakeDao.getListHotelRoom(Constants.SIZE_OF_PAGE, 0);
        return cakes;
    }

    public List<HotelRoom> getListAll(int limit, int page) {
        List<HotelRoom> cakes = cakeDao.getListHotelRoom(limit, page);
        return cakes;
    }

    public List<HotelRoom> getListAll(String likeName, int minPrice, int maxPrice, int categoryId, boolean isActive, int limit, int page) {
        List<HotelRoom> cakes;
        if (categoryId > 0) {
            cakes = cakeDao.getListHotelRoom(likeName, minPrice, maxPrice, categoryId, isActive, limit, page);
        } else {
            cakes = cakeDao.getListHotelRoom(likeName, minPrice, maxPrice, isActive, limit, page);
        }
        return cakes;
    }

    public HotelRoom updateCake(int id, String name, String imgUrl,
            int price, int typeId, int quantity, String sCreateDate,
            String sExpDate, boolean status, Account account) {
        HotelRoom updatedRoom = cakeDao.getOne(id);
        if (updatedRoom != null) {
            SimpleDateFormat formater = new SimpleDateFormat(patternDateTime);
            updatedRoom.setPrice(price);
            updatedRoom.setRoomType(typeDao.getOne(typeId));
            return cakeDao.update(updatedRoom);
        }
        return updatedRoom;
    }

    public HotelRoom getOne(int id) {
        return cakeDao.getOne(id);
    }

    public int countForDashboard() {
        return cakeDao.getCount();
    }

    public int countForHome(boolean isActive) {
        return cakeDao.getCount(isActive);
    }

    public int countForSearch(String likeName, int minPrice, int maxPrice, int categoryId, boolean isActive) {
        if (categoryId > 0) {
            return cakeDao.getCount(likeName, minPrice, maxPrice, categoryId, isActive);
        } else {
            return cakeDao.getCount(likeName, minPrice, maxPrice, isActive);
        }
    }

}
