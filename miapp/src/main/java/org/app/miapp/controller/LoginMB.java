package org.app.miapp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.app.miapp.common.JsfUtils;


import java.io.Serializable;

@Named
@RequestScoped
public class LoginMB implements Serializable {

    private String username;
    private String password;


    @PostConstruct
    public void init() {

    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }


    public void validarLogin() {
        if (username.equals("") || password.equals("")) {
            addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Los campos estan vacios");
            return;
        }

        System.out.println("Validando el login " + username + " " + password);
        addMessage(FacesMessage.SEVERITY_INFO, "", "Login exitoso");

        JsfUtils.redirect("procesos/home.xhtml");
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
}

