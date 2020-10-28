/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import trihk.hotelbooking.dao.BookingDetailsDAO;
import trihk.hotelbooking.dao.HotelAreaDAO;
import trihk.hotelbooking.dao.HotelDAO;
import trihk.hotelbooking.entity.BookingDetails;
import trihk.hotelbooking.entity.Hotel;
import trihk.hotelbooking.entity.HotelArea;
import trihk.hotelbooking.entity.HotelRoom;

/**
 *
 * @author TriHuynh
 */
public class HotelService {

    private final SimpleDateFormat formaterYMD = new SimpleDateFormat("yyyy-MM-dd");

    public List<HotelArea> getListHotelArea() {
        return new HotelAreaDAO().getListAll();
    }

    public List<Hotel> getHotelsWithAvailableHotelRoom(int areaId, String checkinDate, String checkoutDate, int amount) {
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> hotels = hotelDAO.getAllHotels(areaId);
        try {
            Date checkin = formaterYMD.parse(checkinDate);
            Date checkout = formaterYMD.parse(checkoutDate);

            BookingDetailsDAO detailsDAO = new BookingDetailsDAO();
            List<BookingDetails> details = detailsDAO.getBookingDetailsInPeriod(checkin, checkout);

            List<HotelRoom> roomsToRemove = new ArrayList<>();
            List<Hotel> hotelsToRemove = new ArrayList<>();

            // find rooms hotel with insufficient rooms amount
            hotels.forEach((hotel) -> {
                for (HotelRoom room : hotel.getHotelRoomCollection()) {
                    if (room.getAmount() < amount) {
                        roomsToRemove.add(room);
                    }
                }
            });

            // find unavailable rooms from hotels
            for (BookingDetails detail : details) {
                boolean isBreak = false;
                for (Hotel hotel : hotels) {
                    if (isBreak) {
                        break;
                    }
                    for (HotelRoom room : hotel.getHotelRoomCollection()) {
                        if (room.equals(detail.getRoomId())) {
                            int roomAmount = room.getAmount();
                            room.setAmount(roomAmount - detail.getAmount());

                            if (room.getAmount() < amount) {
                                roomsToRemove.add(room);
                            }
                            isBreak = true;
                            break;
                        }
                    }
                }
            }

            // remove
            hotels.forEach((hotel) -> {
                hotel.getHotelRoomCollection().removeAll(roomsToRemove);
                if (hotel.getHotelRoomCollection().isEmpty()) {
                    hotelsToRemove.add(hotel);
                }
            });
            hotels.removeAll(hotelsToRemove);

        } catch (ParseException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hotels;
    }
}
