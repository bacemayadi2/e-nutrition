/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import e.nutrition.Models.BlogPost;
import e.nutrition.Services.Blog_Post_Service;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class EditBlogController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField body;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton btn_edit;
    private BlogPost blogPost;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public void InitSelectedBlog(BlogPost c){
       blogPost=c;
       
       title.setText(blogPost.getTitle());
       body.setText(blogPost.getBody());
       
       
       
   }
    @FXML
    private void edit(ActionEvent event) throws SQLException {
        Blog_Post_Service service = new Blog_Post_Service();
        blogPost.setBody(body.getText());
        blogPost.setTitle(title.getText());
       
        service.Update_Blog_Post(blogPost.getId(), blogPost);
    }
    
}
