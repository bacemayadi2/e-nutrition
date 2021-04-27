/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.RendezVous;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Abdelhamid
 */

public class RendezVousService implements IService<RendezVous>{
    
    private static final String INSERT_STATEMENT = "Insert into pidev3a.rendez_vous(date, description) values (?,?)";
    private static final String DELETE_STATEMENT = "DELETE FROM pidev3a.rendez_vous WHERE id = ? ";
    private static final String UPDATE_STATEMENT = "UPDATE pidev3a.rendez_vous SET date = ?, description = ?  WHERE id = ? ";
    private static final String SELECT_STATEMENT = "SELECT * FROM pidev3a.rendez_vous";
    private static final String SELECT_STATEMENT_BY_ID = "SELECT * FROM pidev3a.rendez_vous where id = ?";
    private final Connection cnx = DataSource.getInstance().getCnx();

    
    public RendezVous getById(Integer id){
        try {
            PreparedStatement pre = cnx.prepareStatement(SELECT_STATEMENT_BY_ID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while(rs.next())
                return new RendezVous(rs.getInt(1), rs.getDate(2), rs.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   
    public void deleteById(final Integer t) {
        try {
            PreparedStatement pre = cnx.prepareStatement(DELETE_STATEMENT);
            pre.setInt(1, t);
            pre.executeUpdate();
            System.out.println("Rendez-vous supprimée !");
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    @Override
    public void Add(RendezVous t) {
            try {
                PreparedStatement pre = cnx.prepareStatement(INSERT_STATEMENT);
                pre.setString(2, t.getDescription());
                pre.setDate(1, new Date(t.getDate().getTime()));
                pre.executeUpdate();
                System.out.println("Rendez-vous ajoutée !");
            } catch (SQLException ex) {
                Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void Delete(RendezVous t) {
        try {
            PreparedStatement pre = cnx.prepareStatement(DELETE_STATEMENT);
            pre.setInt(1, t.getId());
            pre.executeUpdate();
            System.out.println("Rendez-vous supprimée !");
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(RendezVous t) {
        try {
            PreparedStatement pre = cnx.prepareStatement(UPDATE_STATEMENT);
                pre.setString(2, t.getDescription());
                pre.setDate(1, new Date(t.getDate().getTime()));
                pre.setInt(3, t.getId());
            pre.executeUpdate();
            System.out.println("Rendez-vous mis à jour !");
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<RendezVous> Display() {
 ObservableList<RendezVous> rendezVous = FXCollections.observableArrayList();
        try {
            PreparedStatement pre = cnx.prepareStatement(SELECT_STATEMENT);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                RendezVous rendezvous = new RendezVous(rs.getInt(1), rs.getDate(2), rs.getString(3));
                rendezVous.add(rendezvous);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rendezVous;
    }
}


