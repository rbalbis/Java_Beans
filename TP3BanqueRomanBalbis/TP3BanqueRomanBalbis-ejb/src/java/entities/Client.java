/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author balbis
 */
@Entity
public class Client extends Personne implements Serializable {
    
      private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private Collection<CompteBancaire> listComptes = new ArrayList<CompteBancaire>();
    
    @ManyToOne
    private Conseiller conseiller;
    private typeCompteEnum typeCompte = typeCompteEnum.CLIENT;

    
    
    public Client(){
    }

    public Client(String nom, String prenom, String username, String password) {
        super(nom, prenom, typeCompteEnum.CLIENT, username, password);
        
        listComptes = new ArrayList<CompteBancaire>();
        CompteBancaire cb = new CompteBancaire(this , 0);
        listComptes.add(cb);
        this.conseiller = null;
    } 

    public typeCompteEnum getTypeCompte() {
        return typeCompte;
    }

   

    public Collection<CompteBancaire> getListComptes() {
        return listComptes;
    }

    public void setListComptes(ArrayList<CompteBancaire> listComptes) {
        this.listComptes = listComptes;
    }
    
    public void addComptes(CompteBancaire compte) {
        this.listComptes.add(compte);
    }

    public Conseiller getConseiller() {
        return conseiller;
    }

    public void setConseiller(Conseiller conseiller) {
        this.conseiller = conseiller;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ id=" + id + " ]";
    }
    
}
