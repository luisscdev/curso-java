package org.app.apidemo.config;

import lombok.extern.slf4j.Slf4j;
import org.app.apidemo.common.SisVars;
import org.app.apidemo.common.Utils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class LoadVariables implements InitializingBean {
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${SISTEMA_RUTA_DOCUMENTOS}")
    private String sistemaRutaDocumentos;
    @Value("${app.reportes}")
    private String reportes;
    @Value("${app.imagenes}")
    private String imagenes;
    @Autowired
    private Environment environment;

    @Override
    public void afterPropertiesSet() throws Exception {

        log.info("--------->    ruta reportes {}", reportes);
        log.info("---------> ruta imagenes {}", imagenes);
        SisVars.RUTA_DOCUMENTOS = sistemaRutaDocumentos;
        Utils.createDirectory(SisVars.RUTA_DOCUMENTOS);
        SisVars.LOGO_ENTIDAD = imagenes + "cabecera.png";
        SisVars.MARCA_AGUA_ENTIDAD = imagenes + "marca_agua.png";
        SisVars.PIE_PAGINA_ENTIDAD = imagenes + "pie_pagina.png";
        SisVars.RUTA_REPORTES = reportes;
        log.info("--------->    ruta reportes {}", reportes);
        for (String profileName : environment.getActiveProfiles()) {
            switch (profileName) {
                case "dev":
                    SisVars.LOGO_ENTIDAD = resourceLoader.getResource(imagenes + "cabecera.png").getURI().getPath();
                    SisVars.MARCA_AGUA_ENTIDAD = resourceLoader.getResource(imagenes + "marca_agua.png").getURI().getPath();
                    SisVars.PIE_PAGINA_ENTIDAD = resourceLoader.getResource(imagenes + "pie_pagina.png").getURI().getPath();
                    SisVars.RUTA_REPORTES = resourceLoader.getResource(reportes).getURI().getPath();
                    break;
            }
        }


        SisVars.reportes = reportes;
        SisVars.imagenes = imagenes;

        log.info("VARIBALES CARGADAS");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
