package e.nutrition.gui;

import e.nutrition.Models.User;
import e.nutrition.Services.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
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

    User user;
    
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
    }
    
}
