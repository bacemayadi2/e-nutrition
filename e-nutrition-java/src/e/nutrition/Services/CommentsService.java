/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package e.nutrition.Services;

import e.nutrition.Models.Comments;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Abdelhamid
 */
public class CommentsService implements IService<Comments>{
    
    private static final String INSERT_STATEMENT = "Insert into pidev3a.comments(success_id,content,active,email,nickname,date_at,rgpd,parent_id) values (?,?,?,?,?,?,?,?)";
    private static final String DELETE_STATEMENT = "DELETE FROM pidev3a.comments WHERE id = ? ";
    private static final String UPDATE_STATEMENT = "UPDATE pidev3a.comments SET `content` = ?, active = ?, email = ?, nickname = ?, date_at = ?, rgpd = ?  WHERE id = ? ";
    private static final String SELECT_STATEMENT = "SELECT * FROM pidev3a.comments";
    private static final String SELECT_STATEMENT_BY_ID = "SELECT * FROM pidev3a.comments where id = ?";
    private final Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public ObservableList<Comments> Display(){
        List<Comments> comments = new ArrayList<Comments>();
        try {
            PreparedStatement pre = cnx.prepareStatement(SELECT_STATEMENT);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                Comments comment = new Comments(rs.getInt(1), rs.getString(3), rs.getBoolean(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getBoolean(8));
                //TODO setSuccessStroy;
                comments.add(comment);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.observableArrayList(comments);
    }
    
    public Comments getById(Integer id){
        try {
            PreparedStatement pre = cnx.prepareStatement(SELECT_STATEMENT_BY_ID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while(rs.next())
                return new Comments(rs.getString(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6));
        } catch (SQLException ex) {
            Logger.getLogger(CommentsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void Add(final Comments t) {
        if( isNull(t.getSuccessId())){
            throw new RuntimeException("Les commentaires doivent être liés à une Success Story !");
        } else {
            try {
                PreparedStatement pre = cnx.prepareStatement(INSERT_STATEMENT);
                pre.setInt(1, t.getSuccessId().getId());
                pre.setString(2, t.getContent());
                pre.setBoolean(3, t.getActive());
                pre.setString(4, t.getEmail());
                pre.setString(5, t.getNickname());
                pre.setDate(6, new Date(t.getDateAt().getTime()));
                pre.setBoolean(7, t.getRgpd());
                if (nonNull(t.getParentId()))
                    pre.setInt(8, t.getParentId().getId());
                else
                    pre.setNull(8, 0);
                pre.executeUpdate();
                System.out.println("Commentaire ajouté !");
            } catch (SQLException ex) {
                Logger.getLogger(CommentsService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void Delete(final Comments t) {
        try {
            PreparedStatement pre = cnx.prepareStatement(DELETE_STATEMENT);
            pre.setInt(1, t.getId());
            pre.executeUpdate();
            System.out.println("Commentaire supprimé !");
        } catch (SQLException ex) {
            Logger.getLogger(CommentsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void Update(final Comments t) {
        try {
            PreparedStatement pre = cnx.prepareStatement(UPDATE_STATEMENT);
            pre.setString(1, t.getContent());
            pre.setBoolean(2, t.getActive());
            pre.setString(3, t.getEmail());
            pre.setString(4, t.getNickname());
            pre.setDate(5, new Date(t.getDateAt().getTime()));
            pre.setBoolean(6, t.getRgpd());
            pre.setInt(7, 20);
            pre.executeUpdate();
            System.out.println("Commentaire mis à jour !");
        } catch (SQLException ex) {
            Logger.getLogger(CommentsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
