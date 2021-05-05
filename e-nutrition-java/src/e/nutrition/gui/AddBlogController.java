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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class AddBlogController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField body;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton btn_add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addBlog(ActionEvent event) {
        try {
            Blog_Post_Service service = new Blog_Post_Service();
            BlogPost blog = new BlogPost();
            blog.setTitle(title.getText());
            blog.setBody(body.getText());
            blog.setDate(Date.valueOf(date.getValue()));
            service.Add_Blog_Post(blog);
            this.finalize();
        } catch (SQLException ex) {
            ex.getMessage();
        } catch (Throwable ex) {
            Logger.getLogger(AddBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
