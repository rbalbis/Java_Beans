/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Administrateur;
import entities.Client;
import entities.CompteBancaire;
import entities.typeCompteEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author geoffreyroman
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire implements Serializable {

    @PersistenceContext(unitName = "TP3BanqueRomanBalbis-ejbPU")
    private EntityManager em;
    
    GestionnaireUtilisateur gestionnaireUtilisateur;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createAccount(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createQuery("select c from CompteBancaire c");
        return query.getResultList();
    }

    public CompteBancaire getComptes(long id) {
        return em.find(CompteBancaire.class, id);      
    }

    public void update(CompteBancaire compte) {
        CompteBancaire compteBancaire = em.merge(compte);
    }

    public void remove(CompteBancaire compte) {
        em.remove(compte);
    }

    public void remove(Long id) {
        String selectQuery = "SELECT c FROM CompteBancaire c WHERE c.id = " + id;
        List<CompteBancaire> compteToRemove = em.createQuery(selectQuery).getResultList();
        int x = 5;
        for (CompteBancaire compte : compteToRemove) {
            em.remove(compte);
        }
    }

    
    public void creerComptesTest() {
        
//        gestionnaireUtilisateur.createUtilisateur("admin", "admin", "admin1", "root", typeCompteEnum.ADMINISTRATEUR);
       
      //  Client john1 = (Client) gestionnaireUtilisateur.createUtilisateur("Lennon", "John", "John1", "pass", typeCompteEnum.CLIENT);
        //Client john1 = (Client) gestionnaireUtilisateur.getUserWithUsername("John1");
     //   CompteBancaire compteJohn = john1.getListComptes().get(0);
      //  compteJohn.deposer(1589);
      //  em.persist(compteJohn);
       /* createAccount(new CompteBancaire("John Lennon", 150000));
        createAccount(new CompteBancaire("Paul McCartney", 950000));
        createAccount(new CompteBancaire("Ringo Starr", 20000));
        createAccount(new CompteBancaire("Georges Harrisson", 100000));*/
       
       System.out.println("Creation des comptes test ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void addSoldeCompteBancaire(Long id, double montant) {
        Query query = em.createQuery("update CompteBancaire c set c.balance = c.balance +  :montant where c.id = :id");
        query.setParameter("montant", montant);
        query.setParameter("id", id);
        query.executeUpdate();

    }

    public void removeSoldeCompteBancaire(Long idCompteDonneur, double montant) {
        addSoldeCompteBancaire(idCompteDonneur, -montant);
    }


}
