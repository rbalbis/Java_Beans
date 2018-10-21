/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Client;
import entities.Conseiller;
import entities.Personne;
import entities.typeCompteEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import session.GestionnaireUtilisateur;

/**
 *
 * @author balbis
 */
@Named(value = "registerManagedBean")
@SessionScoped
public class RegisterManagedBean implements Serializable{
    
    @EJB
    GestionnaireUtilisateur gestionnaireUtilisateur;

    public GestionnaireUtilisateur getGestionnaireUtilisateur() {
        return gestionnaireUtilisateur;
    }

    private String prenom;
    private String nom;
    private typeCompteEnum typeCompte;
    private String username;
    private String password;
    
    private long client;

    private long conseiller;

    
    public long getClient() {
        return client;
    }

    public void setClient(long client) {
        this.client = client;
    }

    public long getConseiller() {
        return conseiller;
    }

    public void setConseiller(long conseiller) {
        this.conseiller = conseiller;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
   

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public typeCompteEnum getTypeCompte() {
        return typeCompte;
    }
    
    public ArrayList<typeCompteEnum> getTypeCompteList() {
        ArrayList<typeCompteEnum> lt = new ArrayList<>();
        lt.add(typeCompteEnum.ADMINISTRATEUR);
        lt.add(typeCompteEnum.CLIENT);
        lt.add(typeCompteEnum.CONSEILLER);
        return lt;
    }


    public void setTypeCompte(typeCompteEnum typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

   
    
    public void affecterClientEtConseiller(){
        gestionnaireUtilisateur.affecterClientaConseiller(client, conseiller);
    }
    
    /**
     * Creates a new instance of RegisterManagedBean
     */
    public RegisterManagedBean() {
    }
    
    public void createUser(){
        gestionnaireUtilisateur.createUtilisateur(nom, prenom, username, password, typeCompte);        
    }
    
    public Conseiller getConseillerWithUsername(String username){
        return gestionnaireUtilisateur.getConseillerWithUsername(username);
    }
    
    public Client getClientWithUsername(String username){
        return gestionnaireUtilisateur.getClientWithUsername(username);
    }
    
    public List<Personne> getAllPersonne(){
        return gestionnaireUtilisateur.getAllPersonne();
    }
    
     public ArrayList<Client> getAllClient(){
        List<Personne> l= this.getAllPersonne();
        ArrayList<Client> res = new ArrayList<Client>();
        for(Personne p : l){
            if(p.getTypeCompte() == typeCompteEnum.CLIENT){
                res.add((Client) p);
            }
        }
        return res;
    }
    
    public ArrayList<Conseiller> getAllConseiller(){
        List<Personne> l= this.getAllPersonne();
        ArrayList<Conseiller> res = new ArrayList<Conseiller>();
        for(Personne p : l){
            if(p.getTypeCompte() == typeCompteEnum.CONSEILLER){
                res.add((Conseiller) p);
            }
        }
        return res;
    }

    
}
