package org.app.demo.common;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

import java.io.IOException;

public class JsfUtils {

    public static void redirect(String url) {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + url);
        } catch (IOException ex) {
            System.out.printf("error IOException: %s%n", ex.getMessage());
        } catch (Exception ex) {
            System.out.printf("error Exception: %s%n", ex.getMessage());
        }
    }

    public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public static void redirectNewTab(String url) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String ruta = externalContext.getRequestContextPath() + url;
        PrimeFaces.current().executeScript("window.open('" + ruta + "', '_blank');");
    }
}
