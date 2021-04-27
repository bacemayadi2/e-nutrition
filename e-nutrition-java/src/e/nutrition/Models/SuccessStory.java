
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package e.nutrition.Models;

import e.nutrition.Models.tags.TagNourriture;
import e.nutrition.Models.tags.TagSuccessStory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abdelhamid
 */

public class SuccessStory implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String titre;
    private String text;
    private Date dateCreation;
    private Integer likeStory;
    
    private Collection<Comments> commentsCollection;
    private List <TagSuccessStory> tags = new ArrayList();

    
    public void ajoutertag(TagSuccessStory t) {
      if (t != null)
      {
          tags.add(t);
      }
    }

    public void supprimertag(TagSuccessStory t) {
     if (t!=null)
      {
          tags.remove(t);
      }
    }
    
    public SuccessStory() {
    }

    public SuccessStory(Integer id) {
        this.id = id;
    }

    public SuccessStory(Integer id, String titre, String text) {
        this.id = id;
        this.titre = titre;
        this.text = text;
    }

    public SuccessStory(String titre, String text) {
        this.id = id;
        this.titre = titre;
        this.text = text;
    }

    public SuccessStory(String titre, String text, Date dateCreation, Integer likeStory) {
        this.titre = titre;
        this.text = text;
        this.dateCreation = dateCreation;
        this.likeStory = likeStory;
    }

    public SuccessStory(int id, String titre, String text, Date dateCreation, Integer likeStory) {
        this.id = id;
        this.titre = titre;
        this.text = text;
        this.dateCreation = dateCreation;
        this.likeStory = likeStory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getLikeStory() {
        return likeStory;
    }

    public void setLikeStory(Integer likeStory) {
        this.likeStory = likeStory;
    }

    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    public List<TagSuccessStory> getTags() {
        return tags;
    }

    public void setTags(List<TagSuccessStory> tags) {
        this.tags = tags;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuccessStory)) {
            return false;
        }
        SuccessStory other = (SuccessStory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SuccessStory[ id=" + id + " ][ titre=" + titre + " ][ text=" + text + " ]";
    }
    
}
