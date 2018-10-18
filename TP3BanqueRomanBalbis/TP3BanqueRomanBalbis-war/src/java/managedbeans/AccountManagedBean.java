/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.CompteBancaire;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.PostLoad;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author geoffreyroman
 */
@Named(value = "accountManagedBean")
@SessionScoped
public class AccountManagedBean implements Serializable {

    private List<CompteBancaire> compteBancaires;

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
    private Long idCompte;
    private double montant;
    private double solde;

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void onload() { 
        idCompte = gestionnaireDeCompteBancaire.getAllComptes().get(0).getId();        
        getMontantCompteBancaire();
    }
    /**
     * Creates a new instance of AccountManagedBean
     */
    public AccountManagedBean() {
    }

    @PostConstruct
    public void testUsers() {
        gestionnaireDeCompteBancaire.creerComptesTest();
    }

    /**
     * Renvoie la liste des compte pour affichage dans une DataTable
     *
     * @return
     */
    public List<CompteBancaire> getcompteBancaires() {
        if (compteBancaires == null) {
            compteBancaires = gestionnaireDeCompteBancaire.getAllComptes();
        }
        return this.compteBancaires;
    }

    public CompteBancaire getcompteBancaire() {
        return gestionnaireDeCompteBancaire.getComptes(idCompte);
    }

    public double getMontantCompteBancaire() {
        if(idCompte == null){
            solde = 0;
            return 0;
        }
        solde = gestionnaireDeCompteBancaire.getComptes(idCompte).getBalance();
        return solde;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    

    public void setSoldeCompteBancaire() {
        gestionnaireDeCompteBancaire.addSoldeCompteBancaire(idCompte, montant);
        this.montant = 0.0;
    }

    public void setSoldeCompteBancaireNegatif() {
        this.montant = -this.montant;
        setSoldeCompteBancaire();
    }

    public void deleteAccount(Long id) throws IOException {
        
        gestionnaireDeCompteBancaire.remove(id);
        compteBancaires = gestionnaireDeCompteBancaire.getAllComptes();
        FacesContext.getCurrentInstance().getExternalContext().redirect("listCompteBancaire.xhtml");
        
    }
}
