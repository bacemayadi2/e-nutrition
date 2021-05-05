/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
//import e.nutrition.Utils.DataSource;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
//import e.nutrition.Services.ServiceMedicament;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class Pdf {
        private Connection con;
        private Statement ste;
    public Pdf()  {
  //     con = DataSource.getInstance().getCnx();
          
    
}
    public void add(String file,String N) throws FileNotFoundException, SQLException, DocumentException{
        
        /* Create Connection objects */
//                con = DataBase.getInstance().getConnection();
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(2);
                //create a cell object
                PdfPCell table_cell;
                                
            /*                          
                ServiceMedicament sm = new ServiceMedicament();
             ObservableList<Medicament> list = sm.DisplayByFiche(Integer.valueOf(N));
        for (Medicament aux : list)
        {
                                table_cell=new PdfPCell(new Phrase("Nom"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(aux.getNom()));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Quantite"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(aux.getQuantite()));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Duree"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(aux.getDuree()));
                                my_report_table.addCell(table_cell);
        }


                               
                                
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */

        
    }
     
}
