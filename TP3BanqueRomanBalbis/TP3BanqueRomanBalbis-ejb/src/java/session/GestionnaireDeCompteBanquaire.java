/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

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
public class GestionnaireDeCompteBanquaire {

    @PersistenceContext(unitName = "TP3BanqueRomanBalbis-ejbPU")
    private EntityManager em;

    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void creerCompte(CompteBanquaire c) {
        em.persist(c);
    }

    List<CompteBanquaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBanquaire.findAll");
        return query.getResultList();
    }

    public void creerComptesTest() {
        creerCompte(new CompteBanquaire("John Lennon", 150000));
        creerCompte(new CompteBanquaire("Paul McCartney", 950000));
        creerCompte(new CompteBanquaire("Ringo Starr", 20000));
        creerCompte(new CompteBanquaire("Georges Harrisson", 100000));
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void persist1(Object object) {
        em.persist(object);
    }
}
