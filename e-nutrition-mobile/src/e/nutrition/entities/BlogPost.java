/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

import java.util.Date;

/**
 *
 * @author Ayoub
 */
public class BlogPost {
    private Integer id ;
    private String title;
    private String body ;
    private Date date;
    private double nb_raiting;

    public double getNb_raiting() {
        return nb_raiting;
    }

    public void setNb_raiting(double nbRaiting) {
        this.nb_raiting = nbRaiting;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BlogPost(){ }
        
    public BlogPost(String title, String body, Date date , double nb_raiting) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.nb_raiting = nb_raiting;
    }

    @Override
    public String toString() {
        return "BlogPost{" + "id=" + id + ", title=" + title + ", body=" + body + ", date=" + date + '}';
    }
    
   
    
    
    
}
