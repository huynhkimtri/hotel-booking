/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import trihk.hotelbooking.bean.BookingCartBean;
import trihk.hotelbooking.bean.BookingCartItemBean;
import trihk.hotelbooking.dao.HotelRoomDAO;
import trihk.hotelbooking.dao.BookingDAO;
import trihk.hotelbooking.dao.BookingDetailsDAO;
import trihk.hotelbooking.dao.AccountDAO;
import trihk.hotelbooking.entity.HotelRoom;
import trihk.hotelbooking.entity.Booking;
import trihk.hotelbooking.entity.BookingDetails;

/**
 *
 * @author TriHuynh
 */
public class BookingService {

    private final BookingDAO bookingDao = new BookingDAO();
    private final BookingDetailsDAO detailDao = new BookingDetailsDAO();
    private final AccountDAO accountDao = new AccountDAO();
    private final HotelRoomDAO roomDao = new HotelRoomDAO();

    public List<Booking> getListBookingByAccount(String email) {
        List<Booking> listOfBooking = bookingDao.getListBookingByAccount(email);
        return listOfBooking;
    }

    public Booking getOrderById(int orderId) {
        Booking order = bookingDao.getOne(orderId);
        return order;
    }

    public List<BookingDetails> getBookingDetailsByOrderId(int orderId) {
        List<BookingDetails> listOfBookingDetails = detailDao.getListBookingDetailsByOrderId(orderId);
        return listOfBookingDetails;
    }

    public Booking saveOrder(BookingCartBean cart, String email, String name, String phone, String address, String payment) {
        Booking order = new Booking();
        order.setCreateDate(new Date());
        int paymentId = 0;
        try {
            paymentId = Integer.parseInt(payment.trim());
        } catch (NumberFormatException e) {
            paymentId = 2;
        }
        order.setAmount(cart.getTotalAmount());
        if (email != null) {
            order.setUserEmail(accountDao.getByEmail(email));
        }
        order = bookingDao.insert(order);
        for (Map.Entry<BookingCartItemBean, Integer> en : cart.getItems().entrySet()) {
            BookingCartItemBean item = en.getKey();
            int val = en.getValue();
            BookingDetails detail = new BookingDetails();
            detail.setRoomId(roomDao.getOne(item.getId()));
            detail.setCreateDate(new Date());
            detail.setOrderId(order);
            detail.setSinglePrice(item.getPrice());
            detail.setQuantity(val);
            detailDao.insert(detail);
            HotelRoom room = roomDao.getOne(item.getId());
        }
        return order;
    }
}
