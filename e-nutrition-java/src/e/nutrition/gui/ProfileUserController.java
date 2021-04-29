package e.nutrition.gui;

import e.nutrition.Models.User;
import e.nutrition.Services.UserSession;
import e.nutrition.Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class ProfileUserController implements Initializable {

    @FXML
    private ImageView profile_image;
    @FXML
    private Label profile_nomPrenom;
    @FXML
    private Label profile_email;
    @FXML
    private Label profile_tel;
    @FXML
    private Label profile_dateNaiss;
    @FXML
    private Label profile_adresse;
    @FXML
    private Label profile_ville;
    @FXML
    private Label profile_sexe;
    @FXML
    private Label profile_style_title;
    @FXML
    private Label profile_style;
    
    private User user;
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        user = UserSession.getUser();
        profile_nomPrenom.setText(user.getNom() + " " + user.getPrenom());
        profile_email.setText(user.getEmail());
        profile_tel.setText(String.valueOf(user.getTel()));
        profile_dateNaiss.setText(user.getDateNaiss().toString());
        profile_adresse.setText(user.getAdresse());
        profile_ville.setText(user.getVille());
        profile_sexe.setText(user.getSexe());
        if(user.getStringRoles().contains("ROLE_PATIENT"))
        {
            try 
            {
                String style="";
                PreparedStatement ps = cnx.prepareStatement("SELECT style_de_vie FROM patient WHERE id=?");
                ps.setInt(1, user.getId());
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    style = rs.getString(1);
                }
                profile_style_title.setVisible(true);
                profile_style.setVisible(true);
                profile_style.setText(style);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ProfileUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
}
