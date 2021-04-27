/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.Aliment;
import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.tags.ChallengeTag;
import e.nutrition.Models.tags.Tag;
import e.nutrition.Models.tags.TagBlogPost;
import e.nutrition.Models.tags.TagFicheConsultation;
import e.nutrition.Models.tags.TagNourriture;
import e.nutrition.Models.tags.TagSuccessStory;
import e.nutrition.Models.tags.TagUtilisateur;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bacem
 */
public class ServiceTag {
    Connection cnx = DataSource.getInstance().getCnx();
    ServiceContenuMultimedia sCM=new ServiceContenuMultimedia();
    public String type(Tag t)
    {
        String type=null;
        if (t instanceof TagUtilisateur) type="tagutilisateur";
        if (t instanceof TagNourriture) type="tagnourriture";
        if (t instanceof TagFicheConsultation) type="tagficheconsultation";
        if (t instanceof TagBlogPost)  type="tagblogpost";
        if (t instanceof TagSuccessStory)  type="tagsuccessstory";
        return type;
    }
    
    public void Add(Tag t ,int idElmentOftheTag ) {
        try
        {
            //determin tag type 
            String type =this.type(t);  
             
            int generatedID= sCM.Add(t.getContenuMultimedia());
            //tag mere
            String reqTagMere ="INSERT INTO tag (contenu_multimedia_id, dtype) VALUES (?, ?)";
            PreparedStatement psTagMere=cnx.prepareStatement(reqTagMere, Statement.RETURN_GENERATED_KEYS);
            psTagMere.setInt(1, generatedID);
            int idmere=0;
            String reqfille=null;
            PreparedStatement psTagFille=null;
               if(type=="tagnourriture")
               {
                         psTagMere.setString(2, "tagnourriture");
                         psTagMere.executeUpdate();
                         ResultSet rS2=psTagMere.getGeneratedKeys();
                         if (rS2.next()) idmere=rS2.getInt(1);
                         
                         reqfille = "INSERT INTO tag_nourriture (id, nourriture_id ) VALUES (?, ?) ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, idmere);
                         psTagFille.setInt(2, idElmentOftheTag);
               }
                else if (type=="tagutilisateur")    
                {
                         psTagMere.setString(2, "tagutilisateur");
                         psTagMere.executeUpdate();
                         ResultSet rS2=psTagMere.getGeneratedKeys();
                         if (rS2.next()) idmere=rS2.getInt(1);
                         
                         reqfille = "INSERT INTO tag_utilisateur (id, utilisateur_id , is_photo_de_profile) VALUES (?, ?, ?) ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, idmere);
                         psTagFille.setInt(2, idElmentOftheTag);
                         psTagFille.setBoolean(3, ((TagUtilisateur)t).is_photo_de_profile());
                }
                else if (type=="tagficheconsultation")
                {
                        psTagMere.setString(2, "tagficheconsultation");
                         psTagMere.executeUpdate();
                         ResultSet rS2=psTagMere.getGeneratedKeys();
                         if (rS2.next()) idmere=rS2.getInt(1);

                        reqfille ="INSERT INTO tag_fiche_consultation (id, fiche_consultation_id) VALUES (?, ?) ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, idmere);
                         psTagFille.setInt(2, idElmentOftheTag);
                }
                else if (type=="challengetag")
                {
                        psTagMere.setString(2, "challengetag");
                         psTagMere.executeUpdate();
                         ResultSet rS2=psTagMere.getGeneratedKeys();
                         if (rS2.next()) idmere=rS2.getInt(1);

                        reqfille="INSERT INTO challenge_tag (id, challenge_id, user_id) VALUES (?, ?, ?) ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, idmere);
                         psTagFille.setInt(2, idElmentOftheTag);
                         psTagFille.setInt(3, ((ChallengeTag)t).getUser_id() ) ;
                }
                else if (type =="tagsuccessstory")
                {
                        psTagMere.setString(2, "tagsuccessstory");
                         psTagMere.executeUpdate();
                         ResultSet rS2=psTagMere.getGeneratedKeys();
                         if (rS2.next()) idmere=rS2.getInt(1);

                        reqfille="INSERT INTO tag_success_story (id, success_story_id) VALUES (?, ?) ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, idmere);
                         psTagFille.setInt(2, idElmentOftheTag);
                }
                else if (type == "tagblogpost")
                {
                        psTagMere.setString(2, "tagblogpost");
                         psTagMere.executeUpdate();
                         ResultSet rS2=psTagMere.getGeneratedKeys();
                         if (rS2.next()) idmere=rS2.getInt(1);
                         
                        reqfille="INSERT INTO tag_blog_post (id, blog_post_id) VALUES (?, ?) ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, idmere);
                         psTagFille.setInt(2, idElmentOftheTag);
                }
                else 
                   System.err.println("type de tag (string) non conforme voir serviceTag pour info");

             psTagFille.executeQuery();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public void Delete(Tag t ) {
 // Contenu multimedia 
            String type=this.type(t);
            String reqfille=null;
            PreparedStatement psTagFille=null;
            try
            {
               if(type=="tagnourriture")
               {
                         
                         reqfille = "DELETE FROM tag_nourriture WHERE id=? ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, t.getId());
                       
               }
                else if (type=="tagutilisateur")    
                {
                         
                         reqfille = "DELETE FROM  tag_utilisateur WHERE id=? ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, t.getId());
                }
                else if (type=="tagficheconsultation")
                {
                    

                        reqfille ="DELETE FROM  tag_fiche_consultation  WHERE id=? ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, t.getId());
                }
                else if (type=="challengetag")
                {
                        

                        reqfille="DELETE FROM  challenge_tag id WHERE id=? ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, t.getId());
                
                }
                else if (type =="tagsuccessstory")
                {
                 

                        reqfille="DELETE FROM  tag_success_story WHERE id= ? ";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, t.getId());
                }
                else if (type == "tagblogpost")
                {
                         
                        reqfille="DELETE FROM tag_blog_post WHERE id=?";
                         psTagFille= cnx.prepareStatement(reqfille);
                         psTagFille.setInt(1, t.getId());
                }
                else 
                   System.err.println("type de tag (string) non conforme voir serviceTag pour info");

            psTagFille.executeQuery();
            
            // supprimer tag mere 
            String reqMere= "DELETE FROM tag where id=?";
            PreparedStatement psMere =cnx.prepareStatement(reqMere);
            psMere.setInt(1, t.getId());
            psMere.executeQuery();
                System.out.println("delete tag");
            //delete contenu multimedia 
            sCM.Delete(t.getContenuMultimedia());
            
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
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
               else if (type =="tagsuccessstory")
                        req="SELECT t1.id AS id, t1.contenu_multimedia_id AS contenu_multimedia_id, t0.success_story_id AS success_story_id, t1.dtype FROM tag_success_story t0 INNER JOIN tag t1 ON t0.id = t1.id WHERE t0.success_story_id =  ?";
               else if (type == "tagblogpost")
                        req="SELECT t1.id AS id, t1.contenu_multimedia_id AS contenu_multimedia_id, t0.blog_post_id AS blog_post_id, t1.dtype FROM tag_blog_post t0 INNER JOIN tag t1 ON t0.id = t1.id WHERE t0.blog_post_id = ?";
                else 
                        System.err.println("type de tag (string) non conforme voir serviceTag pour info");
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
                     else 
                        System.err.println("type de tag (string) non conforme voir serviceTag pour info");
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
    
    
