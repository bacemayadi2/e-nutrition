/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package e.nutrition.Models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Abdelhamid
 */

public class Comments implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String content;
    private boolean active = false;
    private String email;
    private String nickname;
    private Date dateAt;
    private boolean rgpd;
    private Collection<Comments> commentsCollection;
    private Comments parentId;
    private SuccessStory successId;
    
    public Comments() {
    }
    
    public Comments(Integer id) {
        this.id = id;
    }
    
    public Comments(Integer id, String content, boolean active, String email, String nickname, Date dateAt, boolean rgpd) {
        this.id = id;
        this.content = content;
        this.active = active;
        this.email = email;
        this.nickname = nickname;
        this.dateAt = dateAt;
        this.rgpd = rgpd;
    }
    
    public Comments(String content, boolean active, String email, String nickname, Date dateAt, boolean rgpd) {
        this.content = content;
        this.active = active;
        this.email = email;
        this.nickname = nickname;
        this.dateAt = dateAt;
        this.rgpd = rgpd;
    }

        
    
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public boolean getActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public Date getDateAt() {
        return dateAt;
    }
    
    public void setDateAt(Date dateAt) {
        this.dateAt = dateAt;
    }
    
    public boolean getRgpd() {
        return rgpd;
    }
    
    public void setRgpd(boolean rgpd) {
        this.rgpd = rgpd;
    }
    
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }
    
    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }
    
    public Comments getParentId() {
        return parentId;
    }
    
    public void setParentId(Comments parentId) {
        this.parentId = parentId;
    }
    
    public SuccessStory getSuccessId() {
        return successId;
    }
    
    public void setSuccessId(SuccessStory successId) {
        this.successId = successId;
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
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Comments[ id=" + id + " ][ content=" + content + " ][ email=" + email + " ][ nickname=" + nickname + " ]";
    }
    
}
