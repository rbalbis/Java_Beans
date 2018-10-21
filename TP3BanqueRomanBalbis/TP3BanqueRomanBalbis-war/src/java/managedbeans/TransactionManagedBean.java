/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import session.GestionnaireDeCompteBancaire;
import session.GestionnaireDeTransaction;
import session.GestionnaireUtilisateur;

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
    private double solde;

    /**
     * Creates a new instance of TransactionManagedBean
     */
    public TransactionManagedBean() {
    }
    
    public void onload() { 
        idCompteDonneur = gestionnaireDeCompteBancaire.getAllComptes().get(0).getId();        
        getMontantCompteBancaire();
    }
    
    public void send(){
        
        gestionnaireDeTransaction.createTransaction(gestionnaireDeCompteBancaire.getComptes(idCompteDonneur), gestionnaireDeCompteBancaire.getComptes(idCompteReceveur), montant);
        gestionnaireDeCompteBancaire.addSoldeCompteBancaire(idCompteReceveur, montant);
        gestionnaireDeCompteBancaire.removeSoldeCompteBancaire(idCompteDonneur, montant);
    }
    
      public double getMontantCompteBancaire() {
        if(idCompteDonneur == null){
            solde = 0;
            return 0;
        }
        solde = gestionnaireDeCompteBancaire.getComptes(idCompteDonneur).getBalance();
        return solde;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
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
