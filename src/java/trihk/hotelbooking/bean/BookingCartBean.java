/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TriHuynh
 */
public class BookingCartBean {

    private int totalAmount;
    private Map<BookingCartItemBean, Integer> items;

    public Map<BookingCartItemBean, Integer> getItems() {
        return items;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void addItem(BookingCartItemBean item) {
        if (items == null) {
            items = new HashMap<>();
        }
        int quantity = 1;
        if (items.containsKey(item)) {
            quantity = items.get(item) + 1;
        }
        items.put(item, quantity);
        setTotalAmount();
    }

    public boolean removeItem(BookingCartItemBean item) {
        if (items == null) {
            return false;
        }
        if (items.containsKey(item)) {
            items.remove(item);
            if (items.isEmpty()) {
                items = null;
            } else {
                setTotalAmount();
            }
            return true;
        }
        return false;
    }

    public void updateItem(BookingCartItemBean item, int quantity) {
        if (items == null) {
            return;
        }
        if (items.containsKey(item)) {
            items.put(item, quantity);
            setTotalAmount();
        }
    }

    private void setTotalAmount() {
        totalAmount = 0;
        for (Map.Entry<BookingCartItemBean, Integer> entry : items.entrySet()) {
            BookingCartItemBean key = entry.getKey();
            totalAmount += entry.getValue() * key.getPrice();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CartBean{cart=").append(items.size());
        sb.append('}');
        return sb.toString();
    }

}
