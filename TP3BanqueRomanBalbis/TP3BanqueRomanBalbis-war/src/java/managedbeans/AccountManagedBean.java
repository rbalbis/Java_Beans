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

}
