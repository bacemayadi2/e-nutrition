/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Abdelhamid
 */
public class RendezVous implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Date date;
    private String description;

    public RendezVous() {
    }

    public RendezVous(Integer id) {
        this.id = id;
    }

    public RendezVous(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public RendezVous(Integer id, Date date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
    }

    public RendezVous(Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof RendezVous)) {
            return false;
        }
        RendezVous other = (RendezVous) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ id=" + id + " ][ date=" + date + " ][ description=" + description + " ]";
    }
    
}
