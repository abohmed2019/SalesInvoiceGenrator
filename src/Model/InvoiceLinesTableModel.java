/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import view.InvoiceUI;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ahmed dell
 */
public class InvoiceLinesTableModel extends AbstractTableModel {
    
    private ArrayList<InvoiceLine> arrayOfLineInvoices;
    private String[] columnsTitles = {"No.","Invoice-Item","item-price","Count","Item-total"};

    public InvoiceLinesTableModel(ArrayList<InvoiceLine> arrayOfInvoices) {
        this.arrayOfLineInvoices = arrayOfInvoices;
    }

    @Override
    public int getRowCount() {
        return arrayOfLineInvoices == null ? 0 : arrayOfLineInvoices.size();
    }

    @Override
    public int getColumnCount() {
        return columnsTitles.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
          InvoiceLine invoice_Obj = arrayOfLineInvoices.get(i);
        switch (i1){
            case 0 : return invoice_Obj.getHeader().getInvoiceNumber();
            case 1 : return invoice_Obj.getItem();
            case 2 : return invoice_Obj.getCost();
            case 3 : return invoice_Obj.getQuantity();
            case 4 : return invoice_Obj.getLineTotal();
        }
        
        return "";
    }

    @Override
    public String getColumnName(int i) {
        return columnsTitles[i];
    }
    
}
