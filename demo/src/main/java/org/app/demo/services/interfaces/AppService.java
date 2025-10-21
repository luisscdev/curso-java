package org.app.demo.services.interfaces;

import jakarta.ejb.Local;
import jakarta.faces.context.ExternalContext;

import java.util.List;

@Local
public interface AppService {
    public Object methodGET(String url, Class resultClazz);

    public List methodListGET(String url, Class resultClazz);

    public Object methodPOST(Object data, String url, Class resultClass);

    public List methodListPOST(Object data, String url, Class resultClass);

    public List methodListPOST(List data, String url, Class resultClass);

    public Object methodPUT(Object data, String url, Class resultClass);

    public List methodListPUT(List data, String url, Class resultClass);

    public Object methodPOST(String url, Class resultClass);

    public List methodListPOST(String url, Class resultClass);


    public Object methodPOSTJs(String data, String url, Class resultClass);


    public Object methodPOST(Object data, String url, Class respuestaWsClass, ExternalContext ec);
}
