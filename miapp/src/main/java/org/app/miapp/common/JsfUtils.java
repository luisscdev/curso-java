package org.app.miapp.common;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import java.io.IOException;

public class JsfUtils {

    public static void redirect(String url) {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(url);
        } catch (IOException ex) {
            System.out.printf("error IOException: %s%n", ex.getMessage());
        } catch (Exception ex) {
            System.out.printf("error Exception: %s%n", ex.getMessage());
        }
    }
}
