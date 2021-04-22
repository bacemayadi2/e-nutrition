/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.Aliment;
import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.Tag;
import e.nutrition.Models.TagNourriture;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bacem
 */
public class ServiceTag {
    Connection cnx = DataSource.getInstance().getCnx();
    ServiceContenuMultimedia sCM=new ServiceContenuMultimedia();

    
    public void Add(Tag t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Delete(Tag t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Update(Tag t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Tag> Display(String type,int id ) {
               ObservableList<Tag> oblist = FXCollections.observableArrayList();
        Tag tag=null;
        try
        {
            String req=null;
               if(type=="tagnourriture")
        {
                         req = "SELECT t1.id AS id, t1.contenu_multimedia_id AS contenu_multimedia_id, t0.nourriture_id AS nourriture_id, t1.dtype FROM tag_nourriture t0 INNER JOIN tag t1 ON t0.id = t1.id WHERE t0.nourriture_id = ?";

        }

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            //if (rs != null)
            {
                while (rs.next())
                {                                           

                   tag=new TagNourriture(rs.getInt("id"), sCM.rechercher(rs.getInt("contenu_multimedia_id")));
                   oblist.add(tag);
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return oblist;   
    }
     

    }
    
    
