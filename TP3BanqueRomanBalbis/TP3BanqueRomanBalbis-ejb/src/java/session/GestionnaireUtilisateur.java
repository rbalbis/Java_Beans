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
    
    public void createUtilisateur(String nom, String prenom, String username, String password, typeCompteEnum typeCompte) {

        switch(typeCompte){
            
            case ADMINISTRATEUR: Administrateur adm = new Administrateur(nom, prenom, username, password);
            em.persist(adm);
                    break;

            case CONSEILLER: Conseiller csl = new Conseiller(nom, prenom, username, password);
                             em.persist(csl);
                             break;
                             
            case CLIENT : Client clt = new Client(nom, prenom, username, password);
                          em.persist(clt);
                          break;
            
            default : break;
        }
        
    }

    Operation Transaction(Long id) {
        Query query = em.createQuery("select t from Transaction t where t.id = :id");
        query.setParameter("id", id);
        return (Operation) query.getSingleResult();
    }

    List<Operation> getAllTransactionFromAccount(Long id) {
        Query query = em.createQuery("select t from Transaction t where t.idEmetteur = :id or t.idReceveur = :id");
        query.setParameter("id", id);
        return (List<Operation>) query.getSingleResult();
    }
    
    List<Operation> getReceivedTransactionFromAccount(Long id) {
        Query query = em.createQuery("select t from Transaction t where t.idReceveur = :id");
        query.setParameter("id", id);
        return (List<Operation>) query.getSingleResult();
    }
    
    List<Operation> getSentTransactionFromAccount(Long id) {
        Query query = em.createQuery("select t from Transaction t where t.idEmetteur = :id");
        query.setParameter("id", id);
        return (List<Operation>) query.getSingleResult();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
