/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import trihk.hotelbooking.dao.BookingDAO;
import trihk.hotelbooking.dao.DiscountDAO;
import trihk.hotelbooking.entity.Booking;
import trihk.hotelbooking.entity.BookingDetails;
import trihk.hotelbooking.entity.Discount;

/**
 *
 * @author TriHuynh
 */
public class CartService {

    private final HttpSession session;

    public CartService(HttpSession session) {
        this.session = session;
    }

    public boolean addRoomToCart(BookingDetails detail) {
        if (!this.ensureCartExist()) {
            return false;
        }

        Booking cart = this.getCart();

        boolean isFound = false;
        for (BookingDetails d : cart.getBookingDetailsCollection()) {
            if (Objects.equals(d.getRoomId().getId(), detail.getRoomId().getId())) {
                d.setAmount(d.getAmount() + detail.getAmount());
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            detail.setBookingId(cart);
            cart.getBookingDetailsCollection().add(detail);
        }
        return true;
    }

    public Booking getCart() {
        if (session == null) {
            return null;
        }
        Booking cart = (Booking) session.getAttribute("CART");
        return cart;
    }

    public boolean ensureCartExist() {
        if (session == null) {
            return false;
        }
        Booking cart = this.getCart();
        if (cart == null) {
            cart = new Booking();
            cart.setBookingDetailsCollection(new ArrayList<>());
            this.setCart(cart);
        }
        return true;
    }

    public boolean setCart(Booking cart) {
        if (session == null) {
            return false;
        }
        session.setAttribute("CART", cart);
        return true;
    }

    public boolean saveCart(String discountCode) {
        DiscountDAO discountDAO = new DiscountDAO();
        Discount discount = discountDAO.getDiscountByCode(discountCode);

        Booking cart = this.getCart();
        if (discount != null) {
            cart.setDiscountPercent(discount.getDiscountPercent());
        }

        cart.setCreateDate(Date.from(Instant.now()));

        BookingDAO bookingDAO = new BookingDAO();
        Booking result = bookingDAO.insert(cart);

        return result != null;
    }
}
