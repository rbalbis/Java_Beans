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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.PostLoad;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author geoffreyroman
 */
@Named(value = "accountManagedBean")
@ViewScoped
public class AccountManagedBean implements Serializable {

    private List<CompteBancaire> compteBancaires;

    private int montant;
    private long compteId;

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;

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

    public void changeAccount(Long id) {
        Long x = id;
        Long y = id;

    }

    public void deleteAccount(Long id) {
        System.out.println(id);
        gestionnaireDeCompteBancaire.remove(id);
        getcompteBancaires();
    }

    public void sendForm() {
        int x = montant;
        int y = montant;
        long a = compteId;
        long b = compteId;

    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public long getCompteId() {
        return compteId;
    }

    public void setCompteId(long compteId) {
        this.compteId = compteId;
    }

}
