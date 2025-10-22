package org.app.demo.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.app.demo.common.ReporteFormato;
import org.app.demo.config.VariablesInicio;
import org.app.demo.model.ServletSession;
import org.app.demo.model.UsuarioSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ReporteWS", urlPatterns = {"/ReporteWS"})
public class ReporteWS extends HttpServlet {
    @Inject
    private ServletSession ss;
    @Inject
    private UsuarioSession us;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        Map parametros;
        try {
            response.addHeader("secure-av", "Secure");
            response.addHeader("httponly-av", "HttpOnly");
            response.addHeader("X-UA-Compatible", "IE=edge,chrome=1");
            request.setCharacterEncoding("UTF-8");
            parametros = ss.getParametros();
            if (parametros == null) {
                parametros = new HashMap();
            }

            response.setContentType("application/pdf; filename=" + ss.getNombreDocumento() + "; filename*= UTF-8''" + ss.getNombreDocumento() + "");

            System.out.println("----> parametros Reporte " + ss.getDatosReporte());
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(ss.getDatosReporte(), headers);
            InputStream is = new ByteArrayInputStream(restTemplate.exchange(new URI(VariablesInicio.wsDemo + "reporte/generar"),
                    HttpMethod.POST, requestEntity, byte[].class).getBody());
            System.out.println("//VariablesInicio.wsDemo : " + VariablesInicio.wsDemo);


            try (OutputStream os = response.getOutputStream()) {
                IOUtils.copy(is, os);
                os.flush();
            }
        } catch (IOException | URISyntaxException | RestClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
