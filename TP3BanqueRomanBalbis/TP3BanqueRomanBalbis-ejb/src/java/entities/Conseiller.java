/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author balbis
 */
@Entity
public class Conseiller extends Personne implements Serializable {
    
    public Conseiller(){
        
    }

    public Conseiller(ArrayList<Client> listClients, String nom, String prenom, typeCompteEnum typeCompte, String username, String password) {
        super(nom, prenom, typeCompte, username, password);
        this.listClients = listClients;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(cascade={CascadeType.REFRESH,CascadeType.PERSIST}, fetch=FetchType.LAZY)  
    ArrayList<Client> listClients;
    
    typeCompteEnum typeCompte = typeCompteEnum.CLIENT;

    public typeCompteEnum getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(typeCompteEnum typeCompte) {
        this.typeCompte = typeCompte;
    }
    public ArrayList<Client> getListClients() {
        return listClients;
    }

    public void setListClients(ArrayList<Client> listClients) {
        this.listClients = listClients;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Conseiller)) {
            return false;
        }
        Conseiller other = (Conseiller) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Conseiller[ id=" + id + " ]";
    }
    
}
