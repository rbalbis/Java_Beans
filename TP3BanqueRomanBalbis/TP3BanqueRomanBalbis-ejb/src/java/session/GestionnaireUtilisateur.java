/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Administrateur;
import entities.Client;
import entities.Conseiller;
import entities.Operation;
import entities.typeCompteEnum;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entities.Personne;

/**
 *
 * @author balbis
 */
@Stateless
@LocalBean
public class GestionnaireUtilisateur {

    @PersistenceContext(unitName = "TP3BanqueRomanBalbis-ejbPU")
    private EntityManager em;
    private String prenom = "hello";
    private typeCompteEnum typeCompte;
    private String username;
    private String password;

    public boolean createUtilisateur(String nom, String prenom, String username, String password, typeCompteEnum typeCompte) {

        switch (typeCompte) {

            case ADMINISTRATEUR:
                Administrateur adm = new Administrateur(nom, prenom, username, password);
                try {
                    em.persist(adm);
                } catch (Exception e) {
                    return false;
                }
                return true;

            case CONSEILLER:
                Conseiller csl = new Conseiller(nom, prenom, username, password);
                try {
                    em.persist(csl);
                } catch (Exception e) {
                    return false;
                }
                return true;

            case CLIENT:
                Client clt = new Client(nom, prenom, username, password);
                try {
                    em.persist(clt);
                } catch (Exception e) {
                    return false;
                }
                return true;

            default:
                return false;
        }

    }

    public Personne getUserWithID(long id) {
        Query q = em.createQuery("select p from Personne p where p.id = :id");
        q.setParameter("id", id);
        return (Personne) q.getSingleResult();
    }

    public Personne getUserWithUsername(String username) {
        Query q = em.createQuery("select p from Personne p where p.username = :username");
        q.setParameter("username", username);
        Personne p;
        try {
            p = (Personne) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("impossible de trouver l'utilisateur ------------------------------------------------------------------------------------------");
            return null;
        }
        return p;
    }

}
