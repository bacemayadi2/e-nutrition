package e.nutrition.gui;

import e.nutrition.Models.Challenge;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class ChallengeDetailsController implements Initializable {

    @FXML
    private ImageView challenge_image;
    @FXML
    private Label challenge_title;
    @FXML
    private Label challenge_categorie;
    @FXML
    private Label challenge_dateDebut;
    @FXML
    private Label challenge_dateFin;
    @FXML
    private Label challenge_description;

    Challenge challenge;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        challenge = new Challenge();
        challenge_title.setText(challenge.getTitre());
        challenge_categorie.setText(challenge.getCategorie());
        challenge_dateDebut.setText(challenge.getDateDebut().toString());
        challenge_dateFin.setText(challenge.getDateFin().toString());
        challenge_description.setText(challenge.getDescription());
    }

    @FXML
    private void challenge_participate(ActionEvent event) 
    {
        
    }
    
}
