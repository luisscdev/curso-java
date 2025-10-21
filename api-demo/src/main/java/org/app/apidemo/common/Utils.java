package org.app.apidemo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    public static Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public static Integer getAnio(Date fechaIngreso) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaIngreso);
        return c.get(Calendar.YEAR);
    }

    public static Integer getMes(Date fechaIngreso) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaIngreso);
        return c.get(Calendar.MONTH);
    }

    public static Integer getDia(Date fechaIngreso) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaIngreso);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static String codigoVerificacion(Integer longitud) {
        Random aleatorio = new Random(System.currentTimeMillis());
        int rangoCodigo = 999999;
        return completarCadenaConCeros(String.valueOf(aleatorio.nextInt(rangoCodigo)), longitud + 1);
    }

    public static String completarCadenaConCeros(String cadena, Integer longitud) {
        if (cadena == null) {
            return "";
        }
        if (cadena.length() > longitud) {
            return cadena.substring(0, longitud);
        }
        String ceros = "";
        for (int i = 0; i < longitud; i++) {
            ceros = ceros + "0";
        }
        int tamanio = cadena.length();
        ceros = ceros.substring(0, longitud - tamanio);
        cadena = ceros + cadena;
        return cadena;
    }

    public static Integer getHour(Date fechaIngreso) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaIngreso);
        return c.get(Calendar.HOUR);
    }

    public static Integer getMinute(Date fechaIngreso) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaIngreso);
        return c.get(Calendar.MINUTE);
    }

    public static Integer getSecond(Date fechaIngreso) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaIngreso);
        return c.get(Calendar.SECOND);
    }

    public static boolean isEmpty(Collection l) {
        return l == null || l.isEmpty();
    }

    public static boolean isNotEmpty(Collection l) {
        return !Utils.isEmpty(l);
    }

    public static boolean isEmptyString(String l) {
        return l == null || l.isEmpty();
    }

    public static boolean isNotEmptyString(String l) {
        return !Utils.isEmptyString(l);
    }

    public static String completarCadenaLeft(String cadena, int longitud, String addCadena) {
        if (cadena == null) {
            return "";
        }
        if (cadena.length() > longitud) {
            return cadena.substring(0, longitud);
        }
        String temp = addCadena;
        for (int i = 0; i < longitud; i++) {
            addCadena = addCadena + temp;
        }
        int tamanio = cadena.length();
        addCadena = addCadena.substring(0, longitud - tamanio);
        cadena = addCadena + cadena;
        return cadena;
    }

    public static String completarCadenaRight(String cadena, int longitud, String addCadena) {
        if (cadena == null) {
            return "";
        }
        if (cadena.length() > longitud) {
            return cadena.substring(0, longitud);
        }
        String temp = addCadena;
        for (int i = 0; i < longitud; i++) {
            addCadena = addCadena + temp;
        }
        int tamanio = cadena.length();
        addCadena = addCadena.substring(0, longitud - tamanio);
        cadena = cadena + addCadena;
        return cadena;
    }

    public static Object toObjectFromJson(Object json, Class clazz) {
        if (json == null) {
            return null;
        }
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .excludeFieldsWithModifiers(Modifier.STATIC).setObjectToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER).setPrettyPrinting();
            builder.setPrettyPrinting().setLenient();
            Gson gson2 = builder.create();
            if (json instanceof LinkedTreeMap) {
                Type fooType = new TypeToken<LinkedTreeMap>() {
                }.getType();
                String json1 = gson2.toJson(json, fooType);
                System.out.println("Json linkedTreMap toObjectFromJson:: " + json1);
                return gson2.fromJson(json1, clazz);
            } else if (json instanceof HashMap || json instanceof Map || json instanceof LinkedHashMap) {
                Type fooType = new TypeToken<Map>() {
                }.getType();
                String json1 = gson2.toJson(json, fooType);
                System.out.println("Json linkedTreMap toObjectFromJson:: " + json1);
                return gson2.fromJson(json1, clazz);
            } else {
                return gson2.fromJson(json.toString(), clazz);
            }
        } catch (Exception e) {
            System.out.println("Error al deserealizar js " + json.toString() + " a " + clazz + " Error " + e.getMessage());
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, "", e);
            return null;
        }
    }


    public static Object toObjectFromJson(Object json, Type fooType) {
        if (json == null) {
            return null;
        }
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .excludeFieldsWithModifiers(Modifier.STATIC).setObjectToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER).setPrettyPrinting();
            builder.setPrettyPrinting().setLenient();
            Gson gson2 = builder.create();
            if (json instanceof LinkedTreeMap) {
                Type fooType1 = new TypeToken<LinkedTreeMap>() {
                }.getType();
                String json1 = gson2.toJson(json, fooType1);
                System.out.println("Json linkedTreMap toObjectFromJson:: " + json1);
                return gson2.fromJson(json1, fooType);
            } else if (json instanceof HashMap || json instanceof Map || json instanceof LinkedHashMap) {
                Type fooType1 = new TypeToken<Map>() {
                }.getType();
                String json1 = gson2.toJson(json, fooType1);
                System.out.println("Json linkedTreMap toObjectFromJson:: " + json1);
                return gson2.fromJson(json1, fooType);
            } else {
                return gson2.fromJson(json.toString(), fooType);
            }
        } catch (Exception e) {
            System.out.println("Error al deserealizar js " + json.toString() + " a " + fooType + " Error " + e.getMessage());
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, "", e);
            return null;
        }
    }

    public static String getUltimaPosicion(String key, String regex) {
        try {
            String[] sp = key.split(regex);
            return sp[sp.length - 1];
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * To json string.
     *
     * @param obj the obj
     * @return the string
     */
    public static String toJson(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            Class parentClazz = obj.getClass();
            if (parentClazz.equals(Collection.class) || parentClazz.equals(ArrayList.class)) {
                List c = (List) obj;
                if (c.size() == 0) {
                    return null;
                }
                parentClazz = c.get(0).getClass();
            }
            GsonBuilder builder = new GsonBuilder();
            Class finalParentClazz = parentClazz;
            builder.enableComplexMapKeySerialization()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .excludeFieldsWithModifiers(Modifier.STATIC)
                    .setExclusionStrategies(new ExclusionStrategy() {
                        private Class clazz;

                        @Override
                        public boolean shouldSkipField(FieldAttributes field) {
                            return field.getDeclaredType().equals(finalParentClazz);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            this.clazz = clazz;
                            return false;
                        }
                    })
            /*.setPrettyPrinting()*/;
            builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
            Gson gson2 = builder.create();
            if (obj instanceof LinkedTreeMap) {
                Type fooType = new TypeToken<LinkedTreeMap>() {
                }.getType();
                String json = gson2.toJson(obj, fooType);
                System.out.println("Json linkedTreMap " + json);
                return json;
            } else if (obj instanceof HashMap || obj instanceof Map || obj instanceof LinkedHashMap) {
                Type fooType = new TypeToken<Map>() {
                }.getType();
                String json = gson2.toJson(obj, fooType);
                System.out.println("Json linkedTreMap " + json);
                return json;
            } else {
                return gson2.toJson(obj);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Generar Json.", e);
        }
        return null;
    }

    public static String gsonTransform(Object data) {
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .disableHtmlEscaping();
        builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = builder.create();
        return gson.toJson(data);
    }

    public static String gsonTransformList(List<Object> data) {
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .disableHtmlEscaping();
        Gson gson = builder.create();
        return gson.toJson(data);
    }

    public static Map<String, Object> getUrlsImagenes(String rutaImagenes) {
        Map<String, Object> map = new HashMap<>();
        map.put("HEADER_URL", rutaImagenes + "cabecera.png");
        map.put("BACKGROUND", rutaImagenes + "marca_agua.png");
        map.put("FOOTER_URL", rutaImagenes + "pie_pagina.png");
        map.put("TLP", rutaImagenes);
        return map;
    }

    public static String armarRutaJasper(String rutaReporte, String nombre) {
        return rutaReporte + nombre + ".jasper";
    }

    public static Integer getRestarDias(Date inicio, Date fin) {
        int res = (int) ((fin.getTime() - inicio.getTime()) / (1000 * 60 * 60 * 24));
        return res;
    }


    public static Long restarFechas(Date fechaMenor, Date fechaMayor) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fechaMenor);
        cal2.setTime(fechaMayor);
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();
        long diff = milis2 - milis1;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        /*
        long diffHour = diff / (60 * 60 * 1000);
        diffHour = diffHour - (diffDays * 24);
        if (diffHour > 0) {
            diffDays++;
        }
         */
        return diffDays;
    }

    public static String dateFormatPattern(String pattern, Date fechaFin) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(fechaFin);
    }

    /*public static Object toObjectFromMap(){
        try {

        } catch (Exception )
    }*/

    public static Object toObjecttoHashMap(Object json, List<String> ignoreFields, List<Class> ignoreClass) {
        if (json == null) {
            return null;
        }
        try {
            if (ignoreFields == null) {
                ignoreFields = new ArrayList<>();
            }
            if (ignoreClass == null) {
                ignoreClass = new ArrayList<>();
            }
            System.out.println("ignoreFields " + ignoreFields + " ignoreClass " + ignoreClass);

            GsonBuilder builder = new GsonBuilder();
            Class finalParentClazz = null;
            Type typeOfHashMap = null;
            if (json instanceof Collection || json instanceof List || json instanceof ArrayList) {
                finalParentClazz = ((List) json).get(0).getClass();
                typeOfHashMap = new TypeToken<List<Map<String, Object>>>() {
                }.getType();
            } else {
                typeOfHashMap = new TypeToken<Map<String, Object>>() {
                }.getType();
                finalParentClazz = json.getClass();
            }
            System.out.println("-> " + finalParentClazz);
            Class finalParentClazz1 = finalParentClazz;
            List<String> finalIgnoreFields = ignoreFields;
            List<Class> finalIgnoreClass = ignoreClass;
            builder.enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").excludeFieldsWithModifiers(Modifier.STATIC).setObjectToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER).registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).setExclusionStrategies(new ExclusionStrategy() {
                private Class clazz;

                @Override
                public boolean shouldSkipField(FieldAttributes field) {
                    if (field.getAnnotation(JsonIgnore.class) != null) {
                        return true;
                    }
                    if (finalIgnoreFields.contains(field.getName())) {
                        return true;
                    }
                    return field.getDeclaredClass().equals(finalParentClazz1);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    this.clazz = clazz;
                    if (finalIgnoreClass.contains(clazz)) {
                        return true;
                    }
                    return false;
                }
            });
            Gson gson2 = builder.create();
            String js = gson2.toJson(json);
            return gson2.fromJson(js, typeOfHashMap);
        } catch (Exception e) {
            //LOG.log(Level.SEVERE, "Transformar json a objeto.", e);
            e.printStackTrace();
            return null;
        }
    }

    public static void createDirectory(String newDirectory) {
        try {
            if (newDirectory != null) {
                Path path = Paths.get(newDirectory.endsWith("/") ? newDirectory : newDirectory.concat("/"));
                Path parent = path.getParent();
                if (parent != null) {
                    if (parent.toFile().exists()) {
                        if (!path.toFile().exists()) {
                            Files.createDirectory(path);
                        } else {
                            System.out.println("Exists directory " + newDirectory);
                        }
                    } else {
                        Files.createDirectories(path);
                    }
                } else {
                    Files.createDirectories(path);
                }
            }
        } catch (IOException ex) {
            System.out.println("Errror " + ex.getMessage());
        }
    }

}
