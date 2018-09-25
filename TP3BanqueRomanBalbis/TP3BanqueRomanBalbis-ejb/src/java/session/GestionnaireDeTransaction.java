/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import entities.Operation;
import java.io.Serializable;
import java.util.List;
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
public class GestionnaireDeTransaction implements Serializable{

    @PersistenceContext(unitName = "TP3BanqueRomanBalbis-ejbPU")
    private EntityManager em;

    public void createTransaction(Long emetteur, Long receveur, int montant) {
        
        Operation t = new Operation(emetteur, receveur, montant);
        em.persist(t);
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
