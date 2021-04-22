/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models;

import e.nutrition.Models.tags.Tag;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bacem
 */
public class ContenuMultimedia 
{
    private int id;
    private String nomFile;
    public final String dtype="contenumultimedia";
    private String Description;
    private Date updatedAt;
    private List<Tag> tags =new ArrayList() ;

    public ContenuMultimedia(int id, String nomFile, String Description, Date updatedAt) {
        this.id = id;
        this.nomFile = nomFile;
        this.Description = Description;
        this.updatedAt = updatedAt;
    }

    public ContenuMultimedia(String nomFile, String Description, Date updatedAt) {
        this.nomFile = nomFile;
        this.Description = Description;
        this.updatedAt = updatedAt;
    }


    public String getNomFile() {
        return nomFile;
    }

    public void setNomFile(String nomFile) {
        this.nomFile = nomFile;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public int getId() {
        return id;
    }

    public String getDtype() {
        return dtype;
    }

         
    public void ajoutertag(Tag t)
    {
        if (t != null)
        {
            tags.add(t);
        }
    } 
    public void supprimertag (Tag t)
    {
        if (t != null)
        {
            tags.remove(t);
        }
    }

    
    
    
            
}