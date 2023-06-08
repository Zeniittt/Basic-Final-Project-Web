/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Cart implements Serializable {

    private List<LineItem> items;
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<LineItem> items, double totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item) {
        //If the item already exists in the cart, only the quantity is changed.       
//        int id;
//        id = item.getBook().getId();
//        int quantity = item.getQuantity();
//        for (int i = 0; i < items.size(); i++) {
//            LineItem lineItem = items.get(i);
//            if (lineItem.getBook().getId() == id) {
//                lineItem.setQuantity(quantity);
//                return;
//            }
//        }
//        items.add(item);     
//        totalPrice += item.getTotal();
        boolean check = false;
        int id;
        id = item.getBook().getId();
        System.out.println("ID cua book co lay duoc khong " + id);
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getBook().getId() == id) {
                check = true;
                int quantity = lineItem.getQuantity();
                quantity += 1;
                lineItem.setQuantity(quantity);
                totalPrice += lineItem.getBook().getPrice();
                return;
            }
        }
        if (check == false) {
            item.setQuantity(1);
            int i = item.getQuantity();
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " + i);
            items.add(item);
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " + item);
            totalPrice += item.getTotal();
        }        
    }

    public void increaseQuantity(int idBook, int quantity) {
        double temp = totalPrice;
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getBook().getId() == idBook) {
                lineItem.setQuantity(quantity);
                temp += lineItem.getBook().getPrice();
            }
        }
        totalPrice = temp;
    }

    public void decreaseQuantity(int idBook, int quantity) {
        double temp = totalPrice;
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getBook().getId() == idBook) {
                lineItem.setQuantity(quantity);
                temp -= lineItem.getBook().getPrice();
            }
        }
        totalPrice = temp;
    }

    public void removeItem(LineItem item) {
        int id = item.getBook().getId();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getBook().getId() == id) {
                totalPrice = totalPrice - (lineItem.getBook().getPrice() * lineItem.getQuantity());
                items.remove(i);
            }
        }

    }
}
