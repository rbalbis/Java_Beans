/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.CompteBancaire;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.SessionScoped;
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
        return gestionnaireDeCompteBancaire.getComptes(idCompte).getBalance();
    }

    public void setSoldeCompteBancaire() {
        gestionnaireDeCompteBancaire.setSoldeCompteBancaire(idCompte, montant);
    }

    public void setSoldeCompteBancaireNegatif() {
        this.montant = -this.montant;
        setSoldeCompteBancaire();
    }

    public void deleteAccount(Long id) {
        
        gestionnaireDeCompteBancaire.remove(id);
        
        getcompteBancaires();
        
    }
}
