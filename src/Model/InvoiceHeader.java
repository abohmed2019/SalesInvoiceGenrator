/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ahmed dell
 */
public class InvoiceHeader {
    
    private int invoiceNumber;
    private String customerName;
    private Date invoiceDate;
    private ArrayList<InvoiceLine> Lines;
    private DateFormat datee = new SimpleDateFormat("dd-mm-yyyy");

    public ArrayList<InvoiceLine> getLines() {
        if (Lines == null){
        Lines = new ArrayList<>();
        }
        return Lines;
    }

    public void setLines(ArrayList<InvoiceLine> Lines) {
        this.Lines = Lines;
    }

    public InvoiceHeader() {
    }

    public InvoiceHeader(int invoiceNumber, String customerName, Date invoiceDate) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
        
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public double getInvoiceTotal() {
        double total = 0.0;
        
        for (int i = 0; i < getLines().size(); i++) {
            total += getLines().get(i).getLineTotal();
        }
        
        return total;
    }

    @Override
    public String toString() {
        return invoiceNumber + "," + datee.format(invoiceDate)+ "," + customerName ;
    }
    
}
