/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.service;

import java.util.Date;
import java.util.List;
import trihk.hotelbooking.dao.BookingDetailsDAO;
import trihk.hotelbooking.dao.HotelRoomDAO;
import trihk.hotelbooking.dao.RoomTypeDAO;
import trihk.hotelbooking.entity.Booking;
import trihk.hotelbooking.entity.BookingDetails;
import trihk.hotelbooking.entity.HotelRoom;
import trihk.hotelbooking.entity.RoomType;

/**
 *
 * @author TriHuynh
 */
public class RoomService {

    public List<RoomType> getListRoomType() {
        List<RoomType> list = new RoomTypeDAO().getListRoomType();
        return list;
    }

    public HotelRoom getRoomByIdInPeriod(int roomId, Date checkinDate, Date checkoutDate) {
        HotelRoomDAO roomDAO = new HotelRoomDAO();
        HotelRoom room = roomDAO.getOne(roomId);

        BookingDetailsDAO detailsDAO = new BookingDetailsDAO();
        List<BookingDetails> details = detailsDAO.getRoomBookingDetailsInPeriod(room, checkinDate, checkoutDate);

        for (BookingDetails detail : details) {
            room.setAmount(room.getAmount() - detail.getAmount());
        }

        return room;
    }

    public boolean confirmRoom(BookingDetails detail) {
        int roomId = detail.getRoomId().getId();
        Date checkinDate = detail.getCheckinDate();
        Date checkoutDate = detail.getCheckoutDate();

        HotelRoom room = this.getRoomByIdInPeriod(roomId, checkinDate, checkoutDate);

        return detail.getAmount() <= room.getAmount();
    }

    public boolean confirmRooms(Booking booking) {
        for (BookingDetails detail : booking.getBookingDetailsCollection()) {
            if (!this.confirmRoom(detail)) {
                return false;
            }
        }
        return true;
    }

}
