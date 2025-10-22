package org.app.demo.controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.app.demo.common.JsfUtils;
import org.app.demo.common.Utils;
import org.app.demo.model.UsuarioSession;

import java.io.Serializable;

@Named
@ViewScoped
public class LoginMB implements Serializable {
    @Inject
    private UsuarioSession us;
    private String username;
    private String password;

    @PostConstruct
    public void init() {


    }



    public void validarLogin() {
        if (username.equals("") || password.equals("")) {
            JsfUtils.addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Los campos estan vacios");
            return;
        }

        System.out.println("Validando el login " + username + " " + password);
        JsfUtils.addMessage(FacesMessage.SEVERITY_INFO, "", "Login exitoso");
        us.setUsername(username);
        us.setNombres(username.toUpperCase());
        JsfUtils.redirect("/procesos/home.xhtml");
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
