/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.Aliment;
import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.tags.Tag;
import e.nutrition.Models.tags.TagNourriture;
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
                         req = "SELECT t1.id AS id, t1.contenu_multimedia_id AS contenu_multimedia_id, t0.nourriture_id AS nourriture_id, t1.dtype FROM tag_nourriture t0 INNER JOIN tag t1 ON t0.id = t1.id WHERE t0.nourriture_id = ?";
               else if (type=="tagutilisateur")    
                         req = " SELECT t1.id AS id, t0.is_photo_de_profile AS is_photo_de_profile, t1.contenu_multimedia_id AS contenu_multimedia, t0.utilisateur_id AS utilisateur, t1.dtype FROM tag_utilisateur t0 INNER JOIN tag t1 ON t0.id = t1.id WHERE t0.utilisateur_id = ? ";
               else if (type=="tagficheconsultation")
                        req =" SELECT t1.id AS id, t1.contenu_multimedia_id AS contenu_multimedia_id, t0.fiche_consultation_id AS fiche_consultation_id, t1.dtype FROM tag_fiche_consultation t0 INNER JOIN tag t1 ON t0.id = t1.id WHERE t0.fiche_consultation_id = ?";
               else if (type=="challengetag")
                        req="SELECT t1.id AS id, t1.contenu_multimedia_id AS contenu_multimedia_id, t0.challenge_id AS challenge_id, t0.user_id AS user_id, t1.dtype FROM challenge_tag t0 INNER JOIN tag t1 ON t0.id = t1.id WHERE t0.challenge_id = ?";
               else if (type =="tagsuccesstory")
                        req="SELECT t1.id AS id, t1.contenu_multimedia_id AS contenu_multimedia_id, t0.success_story_id AS success_story_id, t1.dtype FROM tag_success_story t0 INNER JOIN tag t1 ON t0.id = t1.id WHERE t0.success_story_id =  ?";
               else if (type == "tagblogpost")
                        req="SELECT t1.id AS id, t1.contenu_multimedia_id AS contenu_multimedia_id, t0.blog_post_id AS blog_post_id, t1.dtype FROM tag_blog_post t0 INNER JOIN tag t1 ON t0.id = t1.id WHERE t0.blog_post_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            //if (rs != null)
            {
                while (rs.next())
                {                                           
                    if(type=="tagnourriture") 
                        tag=new TagNourriture(rs.getInt("id"), sCM.rechercher(rs.getInt("contenu_multimedia_id")));
                    else if (type=="tagutilisateur")   
                        System.out.println("implement tag l 63");
                    else if (type=="tagficheconsultation")
                        System.out.println("implement tag l 63");

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
    
    
