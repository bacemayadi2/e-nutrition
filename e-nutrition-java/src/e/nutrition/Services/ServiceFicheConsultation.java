/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.FicheConsultation;
import e.nutrition.Models.FicheConsultation;
import e.nutrition.Utils.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author Admin
 */
public class ServiceFicheConsultation implements IService <FicheConsultation> {
    
     Connection cnx = DataSource.getInstance().getCnx();
        private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    @Override
        public void Add(FicheConsultation t) 
    {
        try
        {
            String req = "INSERT INTO fiche_consultation (creation_date, poids, taille, symptome, apetit, description) VALUES "
                    + "(?,?,?,?,?,?)             ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, t.getCreation_date());
            ps.setFloat(2, t.getPoids());
            ps.setFloat(3, t.getTaille());
            ps.setString(4, t.getSymptome());
            ps.setString(5, t.getApetit());
            ps.setString(6, t.getDescription());
            ps.executeUpdate();
            System.out.println("Fiche ajoutée !!");
        }
        catch(SQLException e)
        {
            System.out.println("Fiche non ajoutée !!");
            System.out.println(e.getMessage());
        }
    }

    
    @Override
    public void Update(FicheConsultation t) {
     
          try
        {
            String req = "UPDATE fiche_consultation SET creation_date=?, poids=?, taille=?, symptome=?, apetit=?, description=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.setDate(2, t.getCreation_date());
            ps.setFloat(3, t.getPoids());
            ps.setFloat(4, t.getTaille());
            ps.setString(5, t.getSymptome());
            ps.setString(6, t.getApetit());
            ps.setString(7, t.getDescription());
            ps.executeUpdate();
    
            System.out.println("Success update !!");
        }
        catch (SQLException e)
        {
            System.out.println("Updated failed !!!");
            System.err.println(e.getMessage());
        } 
    }
    public ObservableList<String> DisplayDate() 
    {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        //List <Challenge> list = new ArrayList<>();
        try
        {
            String req = "select DISTINCT SUBSTRING(creation_date,1,7) from fiche_consultation";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                oblist.add(rs.getString(1));   
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return oblist;
    }
      @Override
    public ObservableList<FicheConsultation> Display() 
    {
        ObservableList<FicheConsultation> oblist = FXCollections.observableArrayList();
        //List <Challenge> list = new ArrayList<>();
        try
        {
            String req = "SELECT * FROM fiche_consultation";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                oblist.add(new FicheConsultation(rs.getInt(1), rs.getDate(2), rs.getFloat(3), rs.getFloat(4),rs.getString(5), rs.getString(6),rs.getString(7)));
                
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return oblist;
    }

      @Override
    public void Delete(FicheConsultation t) 
    {
        try
        {
            String req = "DELETE FROM fiche_consultation WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("fiche supprimée !!");
        }
        catch(SQLException e)
        {
            System.err.println("echec de la suppression!!");
            System.err.println(e.getMessage());
        }
    }
    
    
    
       public FicheConsultation getByDesc(String desc) {
          FicheConsultation a = null;
         String requete = " select* from fiche_consultation  where description='"+desc+"'" ;
        try {
           
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new FicheConsultation(res.getInt(1), res.getDate(2), res.getFloat(3), res.getFloat(4),res.getString(5), res.getString(6),res.getString(7));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFicheConsultation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
        private Connection con;

       public int calculer(String date) {
          int l = 0 ;
         String requete = "select COUNT(id) from fiche_consultation where  year(creation_date) = '"+date.substring(0,4)+"' and month(creation_date) = '"+date.substring(5,7)+"'";
        try {
           
            ste = cnx.createStatement();
           ResultSet rs=ste.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFicheConsultation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }
        

}
