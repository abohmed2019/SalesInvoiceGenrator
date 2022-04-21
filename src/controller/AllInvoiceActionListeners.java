/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceLine;
import Model.InvoiceLinesTableModel;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.Files.list;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.rmi.Naming.list;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import view.InvoiceHeaderDialog;
import view.InvoiceLineDialog;
import view.InvoiceUI;

/**
 *
 * @author ahmed dell
 */
public class AllInvoiceActionListeners implements ActionListener{

  private InvoiceUI Myframe;
  private InvoiceHeaderDialog hDialog;
  private InvoiceLineDialog lDialog;
    
  public AllInvoiceActionListeners(InvoiceUI frame){
      this.Myframe = frame;
  }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()){
            
            case "Load File":
                LoadFileAction();
                break;
            
            case "Save File":
                SaveFileAction();
                break;
                
                
                /***********************/
            case "Create New Invoice":
                CreateNewInvoiceAction();
                break;
            case "okCreateNewInvoice":
                clickOkayForNewInvoiceAdded();
                break;
            case "cancelInvoice":
                clickCancelForInvoiceDialog();
                break;
                
                /*********************/
            case "Delete Invoice":
                DeleteInvoiceAction();
                break;
             /*************************/
            case "Save":
                SaveAction();
                break;
            case "ok_new_Line": 
                 lineDialogOk();
                 break;
            case "cancel_new_Line":
                 lineDialohCancel();
                 break;
                 
                 
              /**********************/
            case "Cancel":
                CancelAction();
                break;      
        }}

    private void LoadFileAction() {
        JFileChooser choosenFile = new JFileChooser();
        
        try{
        int outCome = choosenFile.showOpenDialog(Myframe);
        if (outCome == JFileChooser.APPROVE_OPTION) {
           File headerFile = choosenFile.getSelectedFile();
           Path headerFilePath = Paths.get(headerFile.getAbsolutePath());
           java.util.List<String> headerLinesList = Files.readAllLines(headerFilePath);
           ArrayList<InvoiceHeader> allInvoiceHeaders = new ArrayList();
           for (String headerLine : headerLinesList){
               String [] array = headerLine.split(",");
               String segment1 = array[0];
               String segment2 = array[1];
               String customerName = array[2];
               int id = Integer.parseInt(segment1);
               java.util.Date invoiceDate = InvoiceUI.simpleDateFormat.parse(segment2);
               InvoiceHeader oneHeader = new InvoiceHeader(id, customerName, invoiceDate);
               allInvoiceHeaders.add(oneHeader);
           }
           Myframe.setArrayOfInvoices(allInvoiceHeaders);
           
           outCome = choosenFile.showOpenDialog(Myframe);
        if (outCome == JFileChooser.APPROVE_OPTION) {
           File lineFile = choosenFile.getSelectedFile();
           Path lineFilePath = Paths.get(lineFile.getAbsolutePath());
           java.util.List<String> linesLineList = Files.readAllLines(lineFilePath);
           ArrayList<InvoiceLine> allInvoiceLines = new ArrayList();
           for (String lines : linesLineList){
               String[] linesArray = lines.split(",");
               String segment1 = linesArray[0];
               String segment2 = linesArray[1];
               String segment3 = linesArray[2];
               String segment4 = linesArray[3];
               int invoiceID = Integer.parseInt(segment1);
               double cost = Double.parseDouble(segment3);
               int quantity = Integer.parseInt(segment4);
               InvoiceHeader invHeadObj = Myframe.getInvHeaderObj(invoiceID);
               InvoiceLine linee= new InvoiceLine(segment2, cost, quantity, invHeadObj);
               invHeadObj.getLines().add(linee);
           }}
                InvoiceHeaderTableModel header_Table_Model_Obj = new InvoiceHeaderTableModel(allInvoiceHeaders);
                Myframe.setHeaderTableModelObj(header_Table_Model_Obj);
                Myframe.getInvoiceHeaderTBL().setModel(header_Table_Model_Obj);
        }}
        
         catch (IOException e){
         JOptionPane.showMessageDialog(Myframe, e.getMessage(), "Error-msg", JOptionPane.ERROR_MESSAGE);

         } catch (ParseException ex) {
          JOptionPane.showMessageDialog(Myframe, ex.getMessage(), "Error-msg", JOptionPane.ERROR_MESSAGE);
      }}
/***************************************************************/         
    private void SaveFileAction() {
        ArrayList<InvoiceHeader> lastArray = Myframe.getArrayOfInvoices();
        JFileChooser SF = new JFileChooser();
        try {
            int result = SF.showSaveDialog(Myframe);
        if (result == JFileChooser.APPROVE_OPTION) {
            File filee = SF.getSelectedFile();
            FileWriter fileWriter = new FileWriter(filee);
            String textOfHeader = "";
            String textOfLine = "";
            for (InvoiceHeader invv : lastArray){
                textOfHeader += invv.toString();
                textOfHeader += "\n";
                for (InvoiceLine linee : invv.getLines()) {
                    textOfLine += linee.toString();
                    textOfLine += "\n";
                }
            }  
            textOfHeader = textOfHeader.substring(0, textOfHeader.length()-1);
            textOfLine = textOfLine.substring(0, textOfLine.length()-1);
            result = SF.showSaveDialog(Myframe);
            File linefilee = SF.getSelectedFile();
            FileWriter LinefileWriter = new FileWriter(linefilee);
            
          
            
            fileWriter.write(textOfHeader);
            LinefileWriter.write(textOfLine);
            fileWriter.close();
            LinefileWriter.close();
            
            
        }
        } catch (IOException e) {
           JOptionPane.showMessageDialog(Myframe, e.getMessage(), "Error-msg", JOptionPane.ERROR_MESSAGE);

        }
        
    }
/***************************************************************/
    private void CreateNewInvoiceAction() {
        hDialog = new InvoiceHeaderDialog(Myframe);
        hDialog.setVisible(true);
    }
    private void clickOkayForNewInvoiceAdded() {
        hDialog.setVisible(false);
        
        String customerName = hDialog.getCustNameField().getText();
        String givenNewDate = hDialog.getInvDateField().getText();
        
       
      java.util.Date new_Date = new java.util.Date();         
      try {
          new_Date = InvoiceUI.simpleDateFormat.parse(givenNewDate);
      } catch (ParseException ex) {
          JOptionPane.showMessageDialog(Myframe, ex.getMessage(), "Wrong date", JOptionPane.ERROR_MESSAGE);
      }
      int invoice_Num = 0;
      for (InvoiceHeader inv : Myframe.getArrayOfInvoices()){
          if (inv.getInvoiceNumber()>invoice_Num) invoice_Num = inv.getInvoiceNumber();}
      invoice_Num++;
      InvoiceHeader invoice_New = new InvoiceHeader(invoice_Num, customerName, new_Date);
      Myframe.getArrayOfInvoices().add(invoice_New);
      Myframe.getHeaderTableModelObj().fireTableDataChanged();
        
        hDialog.dispose();
        hDialog = null;
    }
    private void clickCancelForInvoiceDialog() {
        hDialog.setVisible(false);
        hDialog.dispose();
        hDialog = null;
    }
/***************************************************************/
    private void DeleteInvoiceAction() {
        int invoiceChoosenIndex = Myframe.getInvoiceHeaderTBL().getSelectedRow();
        if (invoiceChoosenIndex != -1){
            Myframe.getArrayOfInvoices().remove(invoiceChoosenIndex);
            Myframe.getHeaderTableModelObj().fireTableDataChanged();
            Myframe.getInvoiceLineTBL().setModel(new InvoiceLinesTableModel(null));
         
        Myframe.setArrayOfLines(null);
        Myframe.getInvoiceNumLab().setText("");
        Myframe.getInvoiceDateLab().setText("");
        Myframe.getInvoiceTotalLab().setText("");
        Myframe.getCustomerNameLab().setText("");
        
        }
        
    }
/***************************************************************/
    private void SaveAction() {
        lDialog = new InvoiceLineDialog(Myframe);
        lDialog.setVisible(true);
    }
    private void lineDialogOk() {
        lDialog.setVisible(false);
        String item_name = lDialog.getItemNameField().getText();
        String item_count = lDialog.getItemCountField().getText();
        String item_cost = lDialog.getItemPriceField().getText();
        
        int count = 1;
        double cost = 1.0;
        
        try{
            count = Integer.parseInt(item_count);
        }catch(NumberFormatException e){
              JOptionPane.showMessageDialog(Myframe,"invalid operation", "Wrong number format", JOptionPane.ERROR_MESSAGE);
        }
        try{
            cost = Double.parseDouble(item_cost);
        }catch(NumberFormatException e){
              JOptionPane.showMessageDialog(Myframe,"invalid operation", "Wrong number format", JOptionPane.ERROR_MESSAGE);
        }
        int choosenHeader = Myframe.getInvoiceHeaderTBL().getSelectedRow();
        if (choosenHeader != -1){
        InvoiceHeader invv = Myframe.getArrayOfInvoices().get(choosenHeader);
        InvoiceLine new_Line = new InvoiceLine(item_name, cost, count, invv);
        invv.getLines().add(new_Line);
        //Myframe.getArrayOfLines().add(new_Line);
        InvoiceLinesTableModel xxx = (InvoiceLinesTableModel) Myframe.getInvoiceLineTBL().getModel();
        xxx.fireTableDataChanged();
        Myframe.getHeaderTableModelObj().fireTableDataChanged();
        }
        Myframe.getInvoiceHeaderTBL().setRowSelectionInterval(choosenHeader, choosenHeader);

        
        lDialog.dispose();
        lDialog = null;    }
    private void lineDialohCancel() {
        lDialog.setVisible(false);
        lDialog.dispose();
        lDialog = null;    }
/***************************************************************/
    private void CancelAction() {
        int invoiceLineChoosenIndex = Myframe.getInvoiceLineTBL().getSelectedRow();
        int choosenInvoiceIndex = Myframe.getInvoiceHeaderTBL().getSelectedRow();
        if (invoiceLineChoosenIndex != -1){
            Myframe.getArrayOfLines().remove(invoiceLineChoosenIndex);
            InvoiceLinesTableModel LTM = (InvoiceLinesTableModel) Myframe.getInvoiceLineTBL().getModel();
            LTM.fireTableDataChanged();
            
            Myframe.getInvoiceTotalLab().setText(""+Myframe.getArrayOfInvoices().get(choosenInvoiceIndex).getInvoiceTotal());
            Myframe.getHeaderTableModelObj().fireTableDataChanged();   
            Myframe.getInvoiceHeaderTBL().setRowSelectionInterval(choosenInvoiceIndex, choosenInvoiceIndex);

        }
        
    }}

   
   
