/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
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

    public void createTransaction(CompteBancaire compteEmetteur,CompteBancaire compteReceveur, int montant) {
        
        Operation t = new Operation(compteEmetteur,compteReceveur, montant);
        em.persist(t);
    }

    Operation Transaction(Long id) {
        Query query = em.createQuery("select t from Transaction t where t.id = :id");
        query.setParameter("id", id);
        return (Operation) query.getSingleResult();
    }

    List<Operation> getAllTransactionFromAccount(CompteBancaire c) {
        Query query = em.createQuery("select t from Transaction t where t.emetteur = :compte or t.receveur = :compte");
        query.setParameter("compte", c);
        return (List<Operation>) query.getSingleResult();
    }
    
    List<Operation> getReceivedTransactionFromAccount(CompteBancaire c) {
        Query query = em.createQuery("select t from Transaction t where t.receveur = :c");        
        query.setParameter("compte", c);
        return (List<Operation>) query.getSingleResult();
    }
    
    List<Operation> getSentTransactionFromAccount(CompteBancaire c) {
        Query query = em.createQuery("select t from Transaction t where t.emetteur = :compte");
        query.setParameter("compte", c);
        return (List<Operation>) query.getSingleResult();
    }
}
