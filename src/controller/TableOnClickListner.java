/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.InvoiceLinesTableModel;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.InvoiceUI;

/**
 *
 * @author ahmed dell
 */
public class TableOnClickListner implements ListSelectionListener {
    
    private InvoiceUI frame;

    public TableOnClickListner(InvoiceUI frame) {
        this.frame = frame;
    }
    
    

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        int clicked_Invoice_Index = frame.getInvoiceHeaderTBL().getSelectedRow();
        System.out.println("invoice clicked" + clicked_Invoice_Index);
        if (clicked_Invoice_Index != -1) {
        InvoiceHeader clicked_Inv_Obj = frame.getArrayOfInvoices().get(clicked_Invoice_Index);
        ArrayList<InvoiceLine> allLines = clicked_Inv_Obj.getLines();
        InvoiceLinesTableModel line_Table_Model = new InvoiceLinesTableModel(allLines);
        
        
        frame.setArrayOfLines(allLines);  
        frame.getInvoiceLineTBL().setModel(line_Table_Model);
        frame.getInvoiceNumLab().setText(""+clicked_Inv_Obj.getInvoiceNumber());
        frame.getInvoiceDateLab().setText(InvoiceUI.simpleDateFormat.format(clicked_Inv_Obj.getInvoiceDate()));
        frame.getInvoiceTotalLab().setText(""+clicked_Inv_Obj.getInvoiceTotal());
        frame.getCustomerNameLab().setText(clicked_Inv_Obj.getCustomerName());


        
    }}
    
}
