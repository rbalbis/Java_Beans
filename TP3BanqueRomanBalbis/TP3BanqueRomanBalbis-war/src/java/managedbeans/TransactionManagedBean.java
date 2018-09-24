/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import session.GestionnaireDeCompteBancaire;
import session.GestionnaireDeTransaction;

/**
 *
 * @author geoffreyroman
 */
@Named(value = "transactionManagedBean")
@SessionScoped
public class TransactionManagedBean implements Serializable {

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
    
    @EJB
    private GestionnaireDeTransaction gestionnaireDeTransaction;

    private Long idCompteDonneur;
    private Long idCompteReceveur;
    private int montant;

    /**
     * Creates a new instance of TransactionManagedBean
     */
    public TransactionManagedBean() {
    }
    
    public void send(){
        gestionnaireDeTransaction.createTransaction(idCompteDonneur, idCompteReceveur, montant);
    }

    public Long getIdCompteDonneur() {
        return idCompteDonneur;
    }

    public void setIdCompteDonneur(Long idCompteDonneur) {
        this.idCompteDonneur = idCompteDonneur;
    }

    public Long getIdCompteReceveur() {
        return idCompteReceveur;
    }

    public void setIdCompteReceveur(Long idCompteReceveur) {
        this.idCompteReceveur = idCompteReceveur;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    
    
}
