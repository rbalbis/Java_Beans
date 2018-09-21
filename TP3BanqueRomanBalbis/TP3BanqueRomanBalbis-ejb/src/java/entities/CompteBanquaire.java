/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author balbis
 */
@Entity
public class CompteBanquaire implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ownerName;
    private double balance;
    
    
    CompteBanquaire(String ownerName, double balance){
        this.ownerName = ownerName;
        this.balance = balance;
    }
    
    CompteBanquaire(){
    }

    /**
     * Get the value of ownerName
     *
     * @return the value of ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    

    /**
     * Get the value of balance
     *
     * @return the value of balance
     */
    public double getBalance() {
        return balance;
    }

    public void deposer(int montant) {
        balance += montant;
    }

    public int retirer(int montant) {
        if (montant < balance) {
            balance -= montant;
            return montant;
        } else {
            return 0;
        }
    }

    /**
     * Set the value of balance
     *
     * @param balance new value of balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Set the value of ownerName
     *
     * @param ownerName new value of ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
        if (!(object instanceof CompteBanquaire)) {
            return false;
        }
        CompteBanquaire other = (CompteBanquaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tp3.CompteBanquaire[ id=" + id + " ]";
    }

}
