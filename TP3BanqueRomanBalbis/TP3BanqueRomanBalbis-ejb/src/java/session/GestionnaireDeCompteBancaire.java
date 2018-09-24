/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import java.io.Serializable;
import java.util.List;
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
public class GestionnaireDeCompteBancaire implements Serializable{

    @PersistenceContext(unitName = "TP3BanqueRomanBalbis-ejbPU")
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createAccount(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createQuery("select c from CompteBancaire c");
        return query.getResultList();
    }
    
    public CompteBancaire getComptes(Long id) {
        Query query = em.createQuery("select c from CompteBancaire c where c.id = :id");
        query.setParameter("id", id);
        CompteBancaire x = (CompteBancaire)query.getSingleResult();
        return (CompteBancaire)query.getSingleResult();
    }
    
    public void update(CompteBancaire compte){
        CompteBancaire compteBancaire = em.merge(compte);
    }
    
    public void remove(CompteBancaire compte){
        em.remove(compte);
    }

    public void creerComptesTest() {
        createAccount(new CompteBancaire("John Lennon", 150000));
        createAccount(new CompteBancaire("Paul McCartney", 950000));
        createAccount(new CompteBancaire("Ringo Starr", 20000));
        createAccount(new CompteBancaire("Georges Harrisson", 100000));
    }

    public void setSoldeCompteBancaire(Long id, double montant) {
        Query query = em.createQuery("update CompteBancaire c set c.balance = c.balance +  :montant where c.id = :id");
        query.setParameter("montant", montant);
        query.setParameter("id", id);
        query.executeUpdate();  
        
    }
}
