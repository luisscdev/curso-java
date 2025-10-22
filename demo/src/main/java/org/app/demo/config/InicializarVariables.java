package org.app.demo.config;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class InicializarVariables {

    @PostConstruct
    public void onStartup() {
        System.out.println("--->    EJB Singleton de inicio ejecutado. Realizando tareas de arranque...");
    }
}
