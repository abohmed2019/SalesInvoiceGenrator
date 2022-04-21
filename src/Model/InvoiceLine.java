/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ahmed dell
 */
public class InvoiceLine {
    
    
    private String item;
    private double cost;
    private int quantity;
    private InvoiceHeader header;
    
    public InvoiceLine(){
        
    }

    public InvoiceLine(String item, double cost, int quantity, InvoiceHeader header) {
        this.item = item;
        this.cost = cost;
        this.quantity = quantity;
        this.header = header;
    }
    

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }
     public double getLineTotal() {
        return cost * quantity;
    }
     
     @Override
    public String toString() {
        return header.getInvoiceNumber() + "," + item+ "," + cost + "," + quantity ;
    }
    
}
