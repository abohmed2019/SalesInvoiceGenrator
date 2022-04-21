/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class InvoiceLDialog extends JDialog{
    
    private JTextField quantityOfItemField;
    private JTextField costOfItemFiled;
    private JTextField nameOfItemField;

    private JLabel nameOfItemLabel;
    private JLabel itemQuantityLabel;
    private JLabel itemCostLabel;
    
    private JButton cancelButton;
    private JButton okayButton;
    
    public InvoiceLDialog(InvoiceUI Myframe) {
        nameOfItemField = new JTextField(20);
        nameOfItemLabel = new JLabel("Item Name");
        
        quantityOfItemField = new JTextField(20);
        itemQuantityLabel = new JLabel("Item Count");
        
        costOfItemFiled = new JTextField(20);
        itemCostLabel = new JLabel("Item Price");
        
        okayButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okayButton.setActionCommand("ok_new_Line");
        cancelButton.setActionCommand("cancel_new_Line");
        
        okayButton.addActionListener(Myframe.getMyActionListner());
        cancelButton.addActionListener(Myframe.getMyActionListner());
        setLayout(new GridLayout(4, 2));
        
        add(nameOfItemLabel);
        add(nameOfItemField);
        add(itemQuantityLabel);
        add(quantityOfItemField);
        add(itemCostLabel);
        add(costOfItemFiled);
        add(okayButton);
        add(cancelButton);
        
        pack();
    }

   

    public JTextField getQuantityOfItemField() {
        return quantityOfItemField;
    }

    public JTextField getCostOfItemFiled() {
        return costOfItemFiled;
    }
    
    public JTextField getNameOfItemField() {
        return nameOfItemField;
    }
}
