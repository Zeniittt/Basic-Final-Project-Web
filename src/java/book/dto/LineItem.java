/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.dto;

/**
 *
 * @author ADMIN
 */
public class LineItem {
    private int lineItemId;
    private Book book;
    private int quantity;
    
     public LineItem() {}

    public int getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(int lineItemId) {
        this.lineItemId = lineItemId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getTotal() {
        System.out.println("Xam chiem trai dat ");
        double total = book.getPrice() * quantity;       
        return total;
    }
}
