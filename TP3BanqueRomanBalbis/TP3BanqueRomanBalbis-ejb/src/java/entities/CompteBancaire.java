/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author balbis
 */
@Entity
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;

    public CompteBancaire(ArrayList<Client> listProprietaires, double balance) {
        this.listProprietaires = listProprietaires;
        this.balance = balance;
    }
    
     public CompteBancaire(Client client, double balance) {
        ArrayList<Client> listCli =  new ArrayList<Client>();
        listCli.add(client);
        this.listProprietaires = listCli;

        this.balance = balance;
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToMany(mappedBy = "listComptes")
    private Collection<Client> listProprietaires = new ArrayList<Client>();
    
    
    private double balance;
    
    private List<Operation> transaction;

    
    
    CompteBancaire(){
    }

    public Collection<Client> getListProprietaires() {
        return listProprietaires;
    }
    
    public ArrayList<Client> getArrayProprietaires() {
        return (ArrayList<Client>) listProprietaires;
    }

    public void setListProprietaires(Collection<Client> listProprietaires) {
        this.listProprietaires = listProprietaires;
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
    
    public double calculTauxInteret(int capital, int tauxAnnuel, int duree){
        double res = (capital + tauxAnnuel * duree)/100;
        return res;
    }

    /**
     * Set the value of balance
     *
     * @param balance new value of balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
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
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
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
