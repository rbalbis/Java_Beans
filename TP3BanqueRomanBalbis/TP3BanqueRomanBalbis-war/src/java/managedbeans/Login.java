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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;

    private Boolean isLoged = false;

    public Boolean getIsLoged() {
        return isLoged;
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

    //validate login
    public String validateUsernamePassword() {
        boolean valid = validate(user, pwd);
        isLoged = true;
        if(pwd == "out"){
            isLoged = false;
        }
        if (valid) {
            HttpSession session = getSession();
            session.setAttribute("username", user);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username or Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = getSession();
        session.invalidate();
        return "login";
    }

    public static boolean validate(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        /*try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}*/
        
        return true;
        //return false;
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userid");
        } else {
            return null;
        }
    }
}
