/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.Nourriture;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author bacem
 */
public class ServiceNourriture implements IService<Nourriture>{

        Connection cnx = DataSource.getInstance().getCnx();
        
    @Override
    public void Add(Nourriture n) {
        try{
    String reqmere = "INSERT INTO nourriture (nom, lipides, glucides, proteines, poid, nutritionniste_id, dtype) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement mere = cnx.prepareStatement(reqmere);
            mere.setString(1, n.getNom());
            mere.setFloat(2, n.getLipidies());
            mere.setFloat(3, n.getGlucides());
            mere.setFloat(4, n.getProteines());
            mere.setFloat(5, n.getPoid());
            mere.setInt(6, n.getUser()); //get from session in futur 
            mere.setString(7,n.getDType());
            mere.executeUpdate();   
        }
                catch(SQLException e)
        {
            System.out.println("nourriture non ajouter !!");
            System.out.println(e.getMessage());
        }
    }
        

    @Override
    public void Delete(Nourriture n) {
        try{
    String merereq = "DELETE FROM nourriture WHERE id=?";
            PreparedStatement mere = cnx.prepareStatement(merereq);
            mere.setInt(1, n.getId());
            mere.executeUpdate();
    }
               catch(SQLException e)
        {
            System.out.println("nourriture non supprimer !!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Update(Nourriture n) {
        try
        {
             String merereq = "UPDATE  nourriture SET nom=? ,lipides=? ,glucides=?, proteines=? , poid=? WHERE id=?";
            PreparedStatement mere = cnx.prepareStatement(merereq);
            mere.setString(1, n.getNom());
            mere.setFloat(2, n.getLipidies());
            mere.setFloat(3, n.getGlucides());
            mere.setFloat(4, n.getProteines());
            mere.setFloat(5, n.getPoid());
            mere.setInt(6, n.getId());
            mere.executeUpdate();    
        }
         catch(SQLException e)
        {
            System.out.println("aliment non modifier !!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Nourriture> Display() {
        throw new UnsupportedOperationException("no need for it "); //To change body of generated methods, choose Tools | Templates.
    }
    
}
