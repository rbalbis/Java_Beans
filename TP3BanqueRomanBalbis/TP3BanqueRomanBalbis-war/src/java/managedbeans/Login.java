/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author geoffreyroman
 */
package managedbeans;

import entities.Personne;
import entities.typeCompteEnum;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.GestionnaireDeCompteBancaire;
import session.GestionnaireUtilisateur;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    @EJB
    GestionnaireUtilisateur gestionnaireUtilisateur;
    
    GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;


    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;
    private typeCompteEnum typeCompte;

    public typeCompteEnum getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(typeCompteEnum typeCompte) {
        this.typeCompte = typeCompte;
    }

    private Boolean isLoged = false;

    public Boolean getIsLoged() {
        return isLoged;
    }

    public void testUsersStratup() {
        System.out.println("initialisation des comptes de tests ------------------------------------------------------------------");
        gestionnaireDeCompteBancaire.creerComptesTest();
    }


    public void setIsLoged(Boolean isLoged) {
        this.isLoged = isLoged;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void seDeconnecter() {
        this.isLoged = false;
        this.pwd = null;
        this.user = null;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public boolean validate() throws IOException {
        Personne personne = gestionnaireUtilisateur.getUserWithUsername(user);
        if (personne != null) {
            if (personne.getPassword().equals(this.pwd)) {
                this.isLoged = true;
                this.user = personne.getUsername();
                this.typeCompte = personne.getTypeCompte();
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                return true;
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username or Passowrd",
                                "Please enter correct username and Password"));
                return false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Utilisateur inconnu",
                                "Please enter correct username and Password"));
            return false;
        }
    }

}
