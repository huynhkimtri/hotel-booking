/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.service;

import java.util.List;
import trihk.hotelbooking.dao.HotelAreaDAO;
import trihk.hotelbooking.dao.HotelDAO;
import trihk.hotelbooking.dao.HotelRoomDAO;
import trihk.hotelbooking.entity.Hotel;
import trihk.hotelbooking.entity.HotelArea;

/**
 *
 * @author TriHuynh
 */
public class HotelService {

    private final HotelAreaDAO areaDao = new HotelAreaDAO();
    private final HotelDAO hotelDao = new HotelDAO();
    private final HotelRoomDAO roomDao = new HotelRoomDAO();

    public List<Hotel> getListHotel(int areaId, String checkinDate, String checkoutDate) {
        List<Hotel> list = null;
        return list;
    }

    public List<HotelArea> getListHotelArea() {
        return areaDao.getListAll();
    }
}
