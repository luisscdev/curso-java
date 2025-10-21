package org.app.demo.services.ejb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ejb.Stateless;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import org.app.demo.config.JsonDateDeserializer;
import org.app.demo.model.UsuarioSession;
import org.app.demo.services.interfaces.AppService;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class AppEjb implements AppService {
    @Inject
    private UsuarioSession us;
    protected Gson gson;
    protected GsonBuilder builder;

    public AppEjb() {
        builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Date.class, new JsonDateDeserializer()).disableHtmlEscaping();
        gson = builder.create();
    }

    @Override
    public Object methodGET(String url, Class resultClazz) {
        try {
            System.out.println("url: " + url);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.GET, entity, resultClazz);
            if (response != null) {
                return response.getBody();
            }
            return null;
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);
        }
        return null;
    }

    @Override
    public List methodListGET(String url, Class resultClazz) {
        try {
            System.out.println("url: " + url);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Object[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity, resultClazz);
            if (response != null) {
                return Arrays.asList(response.getBody());
            } else {
                return null;
            }
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);
        }
        return null;
    }

    @Override
    public Object methodPOST(Object data, String url, Class resultClass) {
        System.out.println("url: " + url);
        System.out.println("gson.toJson(data): " + gson.toJson(data));
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<Object> entity = new HttpEntity<>(data, headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.POST, entity, resultClass);
            if (response != null) {
                return response.getBody();
            }
            return null;
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);

        }
        return null;
    }


    @Override
    public List methodListPOST(List data, String url, Class resultClass) {
        System.out.println("url: " + url);
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<Object> entity = new HttpEntity<>(data, headers);
            ResponseEntity<Object[]> response = restTemplate.exchange(uri, HttpMethod.POST, entity, resultClass);
            if (response != null) {
                return Arrays.asList(response.getBody());
            } else {
                return null;
            }
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);

        }
        return null;
    }

    @Override
    public List methodListPOST(Object data, String url, Class resultClass) {
        System.out.println("url: " + url);
        System.out.println("gson.toJsonList(data): " + gson.toJson(data));
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<Object> entity = new HttpEntity<>(data, headers);
            ResponseEntity<Object[]> response = restTemplate.exchange(uri, HttpMethod.POST, entity, resultClass);
            if (response != null) {
                return Arrays.asList(response.getBody());
            } else {
                return null;
            }
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);

        }
        return null;
    }

    @Override
    public Object methodPUT(Object data, String url, Class resultClass) {
        System.out.println("url: " + url);
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();


            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<Object> entity = new HttpEntity<>(data, headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, resultClass);
            if (response != null) {
                return response.getBody();
            }
            return null;
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);

        }
        return null;
    }

    @Override
    public List methodListPUT(List data, String url, Class resultClass) {
        System.out.println("url: " + url);
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            //accessToken can be the secret key you generate.


            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<String> entity = new HttpEntity<>(gson.toJson(data), headers);
            ResponseEntity<Object[]> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, resultClass);
            if (response != null) {
                return Arrays.asList(response.getBody());
            } else {
                return null;
            }
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);

        }
        return null;
    }

    @Override
    public Object methodPOST(String url, Class resultClass) {
        System.out.println("url: " + url);
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            //accessToken can be the secret key you generate.


            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.POST, entity, resultClass);
            if (response != null) {
                return response.getBody();
            }
            return null;
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);

        }
        return null;
    }

    @Override
    public List methodListPOST(String url, Class resultClass) {
        //System.out.println("url: " + url);
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            //accessToken can be the secret key you generate.


            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Object[]> response = restTemplate.exchange(uri, HttpMethod.POST, entity, resultClass);
            if (response != null) {
                return Arrays.asList(response.getBody());
            } else {
                return null;
            }
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);

        }
        return null;
    }



    private Object getObject(Object data, Class resultClass, RestTemplate restTemplate, HttpHeaders headers, URI uri) {
        try {
            HttpEntity<Object> entity = new HttpEntity<>(data, headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.POST, entity, resultClass);
            if (response != null) {
                return response.getBody();
            }
        } catch (HttpServerErrorException.InternalServerError ex) {
            System.out.println("Error " + ex.getMessage());
            return null;
        }
        return null;
    }


    @Override
    public Object methodPOSTJs(String data, String url, Class resultClass) {
        System.out.println("url: " + url);
        //System.out.println("gson.toJson(data): " + data);
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            //accessToken can be the secret key you generate.


            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(url);
            HttpEntity<String> entity = new HttpEntity<>(data, headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.POST, entity, resultClass);
            if (response != null) {
                return response.getBody();
            }
            return null;
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);

        }
        return null;
    }

    public Object methodPOST(Object data, String url, Class resultClass, ExternalContext ec) {
        System.out.println("url: " + url);
        try {


            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            URI uri = new URI(url);

            return getObject(data, resultClass, restTemplate, headers, uri);
        } catch (HttpServerErrorException.InternalServerError ex) {
            System.out.println("Error " + ex.getMessage());
            return null;
        } catch (URISyntaxException | RestClientException e) {
            Logger.getLogger(AppEjb.class.getName()).log(Level.SEVERE, "{0}", e);

        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return null;
    }


}
