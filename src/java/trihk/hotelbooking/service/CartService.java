/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.service;

import java.util.List;
import trihk.hotelbooking.bean.BookingCartBean;
import trihk.hotelbooking.bean.BookingCartItemBean;
import trihk.hotelbooking.entity.HotelRoom;

/**
 *
 * @author TriHuynh
 */
public class CartService {

    public BookingCartBean addItemToCart(BookingCartItemBean item, BookingCartBean cart) {
        if (cart == null) {
            cart = new BookingCartBean();
        }
        cart.addItem(item);
        return cart;
    }

    public BookingCartBean addItemToCart(int itemId, BookingCartBean cart) {
        if (cart == null) {
            cart = new BookingCartBean();
        }
        BookingCartItemBean item = new BookingCartItemBean();
        RoomService service = new RoomService();
        HotelRoom room = service.getOne(itemId);
        item.setId(itemId);
        item.setPrice(room.getPrice());
        cart.addItem(item);
        return cart;
    }

    public BookingCartBean removeItemFromCart(BookingCartItemBean item, BookingCartBean cart) {
        if (cart != null) {
            cart.removeItem(item);
        }
        return cart;
    }

    public BookingCartBean removeItemFromCart(int itemId, BookingCartBean cart) {
        if (cart != null) {
            BookingCartItemBean item = new BookingCartItemBean();
            item.setId(itemId);
            cart.removeItem(item);
        }
        return cart;
    }

    public BookingCartBean updateItemFromCart(int itemId, BookingCartBean cart, int quantity) {
        if (cart != null) {
            BookingCartItemBean item = new BookingCartItemBean();
            item.setId(itemId);
            cart.updateItem(item, quantity);
        }
        return cart;
    }

    public BookingCartBean removeItemsFromCart(BookingCartBean cart, List<BookingCartItemBean> items) {
        if (cart != null) {
            int size = items.size();
            for (int i = 0; i < size; i++) {
                cart.removeItem(items.get(i));
            }
        }
        return cart;
    }
}
