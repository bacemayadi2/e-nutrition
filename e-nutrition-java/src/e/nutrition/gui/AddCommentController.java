/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package e.nutrition.gui;

import e.nutrition.Models.Comments;
import e.nutrition.Models.RendezVous;
import e.nutrition.Models.SuccessStory;
import e.nutrition.Services.CommentsService;
import e.nutrition.Services.RendezVousService;
import e.nutrition.Services.SuccessStoryService;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Abdelhamid
 */
/*
public class AddCommentController implements Initializable {

    @FXML
    private TextField nomComment;
    @FXML
    private TextField mailComment;
    @FXML
    private TextArea comment;
    @FXML
    private CheckBox rgpdComment;
    @FXML
    private Button addComment;
    @FXML
    private ComboBox successStoryChoice;

    /**
     * Initializes the controller class.
     */
/*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SuccessStoryService successStoryService = new SuccessStoryService();
        List<SuccessStory> allStories = successStoryService.display();
        for(SuccessStory story: allStories){
            successStoryChoice.getItems().add(story.getTitre());
        }
    }    

    @FXML
    private void addComment(ActionEvent event) {
        CommentsService commentsService = new CommentsService();
        Comments newComment = new Comments(comment.getText(), false, mailComment.getText(), nomComment.getText(), new Date(), Boolean.valueOf(rgpdComment.getText()));
        SuccessStoryService successStroyService = new SuccessStoryService();
        SuccessStory selectedStory = successStroyService.getByTitle(successStoryChoice.getValue().toString());
        newComment.setSuccessId(selectedStory);
        commentsService.add(newComment);
        comment.setText("");
        mailComment.setText("");
        nomComment.setText("");
        rgpdComment.setSelected(false);
        successStoryChoice.setValue("");
        JOptionPane.showMessageDialog(null, "Commentaire ajouté !");
        
    }

    @FXML
    private void getSuccessStroySelected(ActionEvent event) {
    }
    
}
*/