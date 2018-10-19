/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Personne;
import entities.typeCompteEnum;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    

    /**
     * Creates a new instance of RegisterManagedBean
     */
    public RegisterManagedBean() {
    }
    
    public void createUser(){
        gestionnaireUtilisateur.createUtilisateur(nom, prenom, username, password, typeCompte);
        Personne mich = gestionnaireUtilisateur.getUserWithUsername("mich");
        System.out.println(mich.getNom());
    }
    
}