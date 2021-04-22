/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
/*
package e.nutrition.Services;

import e.nutrition.Models.SuccessStory;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdelhamid
 */
/*
public class SuccessStoryService implements IService<SuccessStory>{
    
    private static final String INSERT_STATEMENT = "Insert into pidev3a.success_story(titre, text, date_creation) values (?,?,?)";
    private static final String DELETE_STATEMENT = "DELETE FROM pidev3a.success_story WHERE id = ? ";
    private static final String UPDATE_STATEMENT = "UPDATE pidev3a.success_story SET titre = ?, text = ?, date_creation = ?, like_story = ?  WHERE id = ? ";
    private static final String SELECT_STATEMENT = "SELECT * FROM pidev3a.success_story";
    private static final String SELECT_STATEMENT_BY_ID = "SELECT * FROM pidev3a.success_story where id = ?";
    private static final String SELECT_STATEMENT_BY_TITRE = "SELECT * FROM pidev3a.success_story where titre = ?";
    private final Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public List<SuccessStory> display(){
        List<SuccessStory> successStories = new ArrayList<SuccessStory>();
        try {
            PreparedStatement pre = cnx.prepareStatement(SELECT_STATEMENT);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                SuccessStory success = new SuccessStory(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5));
                successStories.add(success);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuccessStoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return successStories;
    }
    
    public SuccessStory getById(Integer id){
        try {
            PreparedStatement pre = cnx.prepareStatement(SELECT_STATEMENT_BY_ID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while(rs.next())
                return new SuccessStory(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5));
        } catch (SQLException ex) {
            Logger.getLogger(SuccessStoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public SuccessStory getByTitle(String titre){
        try {
            PreparedStatement pre = cnx.prepareStatement(SELECT_STATEMENT_BY_TITRE);
            pre.setString(1, titre);
            ResultSet rs = pre.executeQuery();
            while(rs.next())
                return new SuccessStory(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5));
        } catch (SQLException ex) {
            Logger.getLogger(SuccessStoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    public int addAndGet(final SuccessStory t) {
        try {
            PreparedStatement pre = cnx.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, t.getTitre());
            pre.setString(2, t.getText());
            pre.setDate(3, new Date(t.getDateCreation().getTime()));
            pre.executeUpdate();
            System.out.println("SuccessStory ajoutée !");
            ResultSet rs = pre.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuccessStoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    @Override
    public void add(final SuccessStory t) {
        try {
            PreparedStatement pre = cnx.prepareStatement(INSERT_STATEMENT);
            pre.setString(1, t.getTitre());
            pre.setString(2, t.getText());
            pre.setDate(3, new Date(t.getDateCreation().getTime()));
            pre.executeUpdate();
            System.out.println("SuccessStory ajoutée !");
        } catch (SQLException ex) {
            Logger.getLogger(SuccessStoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete(final SuccessStory t) {
        try {
            PreparedStatement pre = cnx.prepareStatement(DELETE_STATEMENT);
            pre.setInt(1, t.getId());
            pre.executeUpdate();
            System.out.println("SuccessStory supprimée !");
        } catch (SQLException ex) {
            Logger.getLogger(SuccessStoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(final SuccessStory t) {
        try {
            PreparedStatement pre = cnx.prepareStatement(UPDATE_STATEMENT);
            pre.setString(1, t.getTitre());
            pre.setString(2, t.getText());
            pre.setDate(3, new Date(t.getDateCreation().getTime()));
            pre.setInt(4, t.getLikeStory());
            pre.setInt(5, t.getId());
            pre.executeUpdate();
            System.out.println("SuccessStory mis à jour !");
        } catch (SQLException ex) {
            Logger.getLogger(SuccessStoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

*/