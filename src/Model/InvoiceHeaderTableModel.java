/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import view.InvoiceUI;

/**
 *
 * @author ahmed dell
 */
public class InvoiceHeaderTableModel extends AbstractTableModel{
    
    private ArrayList<InvoiceHeader> arrayOfInvoices;
    private String[] columnsTitles = {"No.","Invoice-date","customer-name","total"};

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> arrayOfInvoices) {
        this.arrayOfInvoices = arrayOfInvoices;
    }
    
    

    @Override
    public int getRowCount() {
        return arrayOfInvoices.size();
    }

    @Override
    public int getColumnCount() {
        return columnsTitles.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        InvoiceHeader invoice_Obj = arrayOfInvoices.get(i);
        switch (i1){
            case 0 : return invoice_Obj.getInvoiceNumber();
            case 1 : return InvoiceUI.simpleDateFormat.format(invoice_Obj.getInvoiceDate());
            case 2 : return invoice_Obj.getCustomerName();
            case 3 : return invoice_Obj.getInvoiceTotal();
        }
        
        return "";
    }

    @Override
    public String getColumnName(int i) {
        return columnsTitles[i];
    }}
