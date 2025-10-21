package org.app.demo.common;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * @author User
 */
public class Utils {

    private static final int[] PATTERN = {2, 1, 2, 1, 2, 1, 2, 1, 2};
    private static final int[] CASO_9 = {4, 3, 2, 7, 6, 5, 4, 3, 2};
    private static final int[] CASO_6 = {3, 2, 7, 6, 5, 4, 3, 2};
    private static final String NUMERIC_REGEX = "^[0-9]+$";
    private static final String DECIMAL_REGEX = "^[+]?\\d+([.]\\d+)?$";
    private static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)*(\\.[A-Za-z]{1,})$";
    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    public static BigDecimal bigdecimalTo2Decimals(BigDecimal inNumber) {
        String temp = inNumber.toString();
        BigDecimal outNumber;
        int indice = temp.indexOf('.');
        if (((inNumber.toString().length() - 1) - indice) > 2) {
            String tempNew = temp.substring(0, indice + 3);
            outNumber = new BigDecimal(tempNew);
            if (((temp.length()) - (indice + 1)) >= 3) {
                if (Integer.parseInt(temp.substring(tempNew.length(), tempNew.length() + 1)) >= 5) {
                    outNumber = outNumber.add(new BigDecimal("0.01"));
                }
            }
        } else {
            outNumber = inNumber;
        }
        return outNumber;
    }

    public static List<String> separadorComas(String correos) {
        List<String> correosResulList = new ArrayList<>();
        String temp = correos;
        int indice = temp.indexOf(',');
        if (indice > 0) {
            do {
                String correo1 = temp.substring(0, indice);
                correosResulList.add(correo1);
                String correoRestante = temp.substring(indice + 1, temp.length());
                temp = correoRestante;
                indice = temp.indexOf(',');

            } while (indice > 0);
            correosResulList.add(temp);
        } else {
            correosResulList.add(correos);
        }
        return correosResulList;
    }

    public static Boolean isRepetido(Collection<String> val, Object nuevo) {
        boolean i = false;
        for (String x : val) {
            if (x.equals(nuevo)) {
                i = true;
                return i;
            }
        }
        return i;
    }

    public static synchronized boolean validarEmailConExpresion(String email) {
        return validatePattern(EMAIL_REGEX, email);
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


    public static synchronized boolean validateNumberPattern(final String valor) {
        return validatePattern(NUMERIC_REGEX, valor);
    }

    public static synchronized boolean validateDecimalPattern(final String valor) {
        return validatePattern(DECIMAL_REGEX, valor);
    }

    public static synchronized boolean validatePattern(final String patron, final String valor) {
        final Pattern patter = Pattern.compile(patron);
        final Matcher matcher = patter.matcher(valor);
        return matcher.matches();
    }


    public static Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public static Date sumarDiasFechaSinWeekEnd(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int hoy;
        do {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            hoy = cal.get(Calendar.DAY_OF_WEEK);
            if (hoy != Calendar.SATURDAY && hoy != Calendar.SUNDAY) {
                dias--;
            }
        } while (dias > 0);
        return cal.getTime();
    }

    public static Date sumarDiasFecha(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int hoy;
        do {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            hoy = cal.get(Calendar.DAY_OF_WEEK);
            dias--;
        } while (dias > 0);
        return cal.getTime();
    }

    public static String pasarUtf(String cadena) throws UnsupportedEncodingException {
        if (cadena != null) {
            byte[] bytes = cadena.getBytes("ISO-8859-1");
            cadena = new String(bytes, "UTF-8");
        }
        return cadena;
    }

    public static String randomNumericString() {
        int i = (int) (Math.random() * 100000);
        return String.valueOf(i);
    }

    public static Integer getDateValues(String formatValue, Date value) {
        Integer res = 0;
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        f.format(value);
        switch (formatValue.toUpperCase()) {
            case "Y":
                res = f.getCalendar().get(Calendar.YEAR);
                break;
            case "M":
                res = f.getCalendar().get(Calendar.MONTH);
                break;
            case "D":
                res = f.getCalendar().get(Calendar.DAY_OF_MONTH);
                break;
        }
        return res;
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

    public static String convertirMesALetra(Integer fechames) {
        String mes;
        switch (fechames) {
            case 0:
                mes = "Enero";
                break;
            case 1:
                mes = "Febrero";
                break;
            case 2:
                mes = "Marzo";
                break;
            case 3:
                mes = "Abril";
                break;
            case 4:
                mes = "Mayo";
                break;
            case 5:
                mes = "Junio";
                break;
            case 6:
                mes = "Julio";
                break;
            case 7:
                mes = "Agosto";
                break;
            case 8:
                mes = "Septiembre";
                break;
            case 9:
                mes = "Octubre";
                break;
            case 10:
                mes = "Noviembre";
                break;
            case 11:
                mes = "Diciembre";
                break;
            default:
                mes = "";
        }
        return mes;
    }

    public static String quitarSaltos(String cadena) {
        return cadena.replace("\r", "").replace("\n", "");
    }

    public static boolean isDecimal(String cad) {
        try {
            Double.parseDouble(cad);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidDate(Object inDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(inDate.toString().trim());
            } catch (ParseException pe) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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

    /**
     * Si el String es nulo returna vacio, caso contrario el mismo valor.
     *
     * @param nombres Object
     * @return Object
     */
    public static String isEmpty(String nombres) {
        if (nombres == null || nombres.trim().isEmpty()) {
            return "";
        }
        return nombres;
    }

    /**
     * Verifica que el valor numerico no sea nulo <code>value</code> y retorna
     * el mismo valor de <code>value</code> caso contrario retorna -1.
     *
     * @param <T>   Object
     * @param value Valor a verificar.
     * @return si el valor de <code>value</code> es nulo retorna -1 caso
     * contrario el valor de <code>value</code>
     */
    public static <T> T isNull(T value) {
        if (value == null || value.toString().trim().length() < 0) {
            return (T) new BigInteger("-1");
        }
        return (T) value;
    }

    /**
     * Verifica si <code>value</code> es nulo y retorna <code>true</code>, caso
     * contrario retorna <code>false</code>.
     *
     * @param value Tipo de Dato Númerico de cualquier tipo primitivo o objecto.
     * @return True si el null caso contrario false.
     */
    public static Boolean isNumberNull(Number value) {
        if (value == null || value.longValue() < 0L) {
            return true;
        }
        return false;
    }

    public static <T> T get(final List<T> values, int idx) {
        if (values.size() > idx) {
            return values.get(idx);
        }
        return null;
    }

    public static <T> T get(final Collection<T> values, int idx) {
        if (values.size() > idx) {
            List<T> result = new ArrayList<>(values);
            return result.get(idx);
        }
        return null;
    }

    public static Date validateDate(String fecha) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = parser.parse(fecha);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        date = formatter.parse(formattedDate);
        return date;
    }

    /**
     * Le da formato a la fecha con el pattern que se le pasa como parametro
     *
     * @param pattern  Formato que se desea obtener.
     * @param fechaFin Fecha a dar formato.
     * @return Fecha con el formato esperado.
     */
    public static String dateFormatPattern(String pattern, Date fechaFin) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(fechaFin);
    }

    public static boolean isNum(String nom) {
        try {
            Long.parseLong(nom);
        } catch (Exception ex) {
            return false;
        }
        return true;
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

    public static Date getFechaInit() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }


    public static int boolean2int(Boolean x) {
        if (x) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @param <T>            Object
     * @param object         Lista de objecto principal
     * @param previousValues lista a retornar
     * @param duplicateArray Lista a comparar si estan repetidos.
     * @param compare        0 para realizar comparacion en binario, para hacer la
     *                       comparacion como texto 1
     * @return Object
     */
    public static <T> List<T> verificarRepetidos(final List<T> object, final List<T> previousValues, final List<T> duplicateArray, final int compare) {
        Iterator<T> iterator = object.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            int count = 0;
            for (T t : duplicateArray) {
                if (compare == 0) {
                    if (next.equals(t)) {
                        count++;
                    }
                } else {
                    if (String.valueOf(next).equalsIgnoreCase(String.valueOf(t))) {
                        count++;
                    }
                }
            }
            if (count == 0 && !previousValues.contains(next)) {
                previousValues.add(next);
            }

        }
        return previousValues;
    }

    public static String getValorUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String quitarTildes(String cadena) {
        if (cadena == null) {
            return "";
        } else {
            cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
            cadena = cadena.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            return cadena;
        }

    }

    /**
     * TRANSFORMA LA PRIMERA LETRA EN MAYUSCULA
     *
     * @param text Texto a procesar.
     * @return Texto con la primera letra en mayucula y despues de cada _ la
     * pasa a mayucula
     */
    public static String transformUpperCase(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        String[] aux = text.split("_");
        if (aux.length > 1) {
            return text;
        }
        String result = "";
        int count = 0;
        for (String string : aux) {
            if (count > 0) {
                result += string.substring(0, 1).toUpperCase().concat(string.substring(1));
            } else {
                result = string;
            }
            count++;
        }
        return result;
    }

    public static List<String> separarCaracteresList(String texto, String split) {
        if (texto == null) {
            return null;
        }
        String[] array = texto.split(split);
        List<String> resultList = new ArrayList<>();
        for (String car : array) {
            resultList.add(car);
        }
        return resultList;
    }

    /**
     * Realiza un split a los apellidos si encuenta un de o del lo ubica como
     * parte de uno de los apellidos.
     *
     * @param apellidos
     * @return
     */
    public static List<String> obtenerApellidos(String apellidos) {
        if (apellidos == null) {
            return null;
        }
        // Reemplazamos todos los espacion en blanco por uno solo
        apellidos = reemplazarEspacionEnBlanco(apellidos, " ");
        String[] split = apellidos.split(" ");
        int countApellidos = 2;
        List<String> result = new LinkedList<>();
        String cadenaAux = "";

        for (String cadena : split) {
            if (countApellidos != 0) {
                switch (split.length) {
                    case 2:
                        result.add(cadena);
                        countApellidos--;
                        break;
                    default:
                        if (cadena.equalsIgnoreCase("del") || cadena.equalsIgnoreCase("de")) {
                            cadenaAux = cadena + " ";
                            continue;
                        }
                        result.add(cadenaAux + cadena);
                        cadenaAux = "";
                        countApellidos--;
                        break;
                }
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Busca los apellidos en toda la cadena si existe en la cadena 'de' o 'del'
     * los concatena a apellidos siguiente.
     *
     * @param nombresCompletos Nombres y apellidos completos
     * @param inicioApellidos0 Si es false indica que empezara a recorrer desde
     *                         la ultima cadena hacia atras
     * @return Los 2 apellidos
     */
    public static List<String> obtenerApellidos(String nombresCompletos, boolean inicioApellidos0) {
        if (nombresCompletos == null) {
            return null;
        }
        // Reemplazamos todos los espacion en blanco por uno solo
        nombresCompletos = reemplazarEspacionEnBlanco(nombresCompletos, " ");
        String[] split = nombresCompletos.split(" ");
        int countApellidos = 2;
        List<String> result = new LinkedList<>();
        String cadenaAux = "";
        if (inicioApellidos0) {
            for (String cadena : split) {
                if (countApellidos != 0) {
                    switch (split.length) {
                        case 4:
                            result.add(cadena);
                            countApellidos--;
                            break;
                        default:
                            if (cadena.equalsIgnoreCase("del") || cadena.equalsIgnoreCase("de")) {
                                cadenaAux = cadena + " ";
                                continue;
                            }
                            result.add(cadenaAux + cadena);
                            cadenaAux = "";
                            countApellidos--;
                            break;
                    }
                } else {
                    break;
                }
            }
        } else {
            for (int i = split.length - 1; i >= 0; i--) {
                if (countApellidos > -1 || (split[i].equalsIgnoreCase("del") || split[i].equalsIgnoreCase("de"))) {
                    switch (split.length) {
                        case 4:
                            if (countApellidos > 0) {
                                result.add(0, split[i]);
                                countApellidos--;
                            }
                            break;
                        default:
                            if (split[i].equalsIgnoreCase("del") || split[i].equalsIgnoreCase("de")) {
                                cadenaAux = split[i] + " ";
                                System.out.println("Cadena " + cadenaAux);
                                if (result.size() > 0) {
                                    result.set(0, cadenaAux + result.get(0));
                                    cadenaAux = "";
                                    countApellidos--;
                                }
                            } else {
                                result.add(0, cadenaAux + split[i]);
                                cadenaAux = "";
                                countApellidos--;
                            }
                            break;
                    }
                } else {
                    break;
                }
            }
        }
        return result;
    }

    public static String reemplazarEspacionEnBlanco(String cadena, String caracterNuevo) {
        return cadena.replaceAll("\\s+", caracterNuevo);
    }

    public static String convertirFechaLetra(Date fecha) {
        String mesLetra = Utils.convertirMesALetra(Utils.getMes(fecha));
        String fechaLetra = Utils.getDia(fecha).toString() + " de "
                + mesLetra.substring(0, 1).toUpperCase() + mesLetra.substring(1)
                + " del " + Utils.getAnio(fecha).toString();
        return fechaLetra;
    }


    public static String createDirectoryIfNotExist(String directorio) {
        Path path = Paths.get(directorio);
        if (path.toFile().exists()) {
            return directorio;
        } else {
            try {
                Files.createDirectories(path);
            } catch (IOException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return directorio;
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

    public static String completarCadenaRigth(String cadena, int longitud, String addCadena) {
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


    public static String colorRandom() {
        Random random = new Random();
        int nextInt = random.nextInt(0xffffff + 1);
        return String.format("#%06x", nextInt);
    }

    public static Integer convertirLetraAMes(String mes) {
        Integer mesNumerito;
        switch (mes) {
            case "Ene":
            case "ENERO":
                mesNumerito = 1;
                break;
            case "Feb":
            case "FEBRERO":
                mesNumerito = 2;
                break;
            case "Mar":
            case "MARZO":
                mesNumerito = 3;
                break;
            case "Abr":
            case "ABRIL":
                mesNumerito = 4;
                break;
            case "May":
            case "MAYO":
                mesNumerito = 5;
                break;
            case "Jun":
            case "JUNIO":
                mesNumerito = 6;
                break;
            case "Jul":
            case "JULIO":
                mesNumerito = 7;
                break;
            case "Ago":
            case "AGOSTO":
                mesNumerito = 8;
                break;
            case "Sep":
            case "SEPTIEMBRE":
                mesNumerito = 9;
                break;
            case "Oct":
            case "OCTUBRE":
                mesNumerito = 10;
                break;
            case "Nov":
            case "NOVIEMBRE":
                mesNumerito = 11;
                break;
            default:
                mesNumerito = 12;
        }
        return mesNumerito;
    }

    public static String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }

    public static List<Field> getPrivateFields(Class<?> theClass) {
        List<Field> privateFields = new ArrayList<>();

        Field[] fields = theClass.getDeclaredFields();

        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                privateFields.add(field);
            }
        }
        return privateFields;
    }

    public static String quitarTildeEspacioToMinuscula(String cadena) {
        if (cadena == null) {
            return "";
        } else {
            cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
            return cadena.replaceAll("\\s", "").replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toLowerCase();
        }
    }


    public static String replaceRutaArchivo(String ruta) {
        ruta = Base64.getEncoder().encodeToString(ruta.getBytes());
        return ruta;
    }

    public static Boolean isAjaxRequest() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.isPostback();
    }


    public static int anioActual() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public static boolean validacionCorreos(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    public static String conversionBase64(String fileName) {
        try {
            File file = new File(fileName);
            byte[] bytes = loadFile(file);
            Base64.Encoder e = Base64.getEncoder();
            byte[] encoded = e.encode(bytes);
            String encodedString = new String(encoded);
            return encodedString;
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] loadFile(File file) {
        try {
            InputStream is = new FileInputStream(file);

            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                // File is too large
            }
            byte[] bytes = new byte[(int) length];

            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }

            is.close();
            return bytes;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }


    /**
     * Este metodo recorre todos los campos de la clase y permite armar la URL
     * para ser enviada al servidor solo con los campos que sean diferente de
     * null =) se debe usar despues del path de la URL x ejemplo persona/find
     *
     * @param obj        Es la clase inicializada
     * @param clazz      es el .class del Objecto
     * @param startIndex Sirve para iniciar la busqueda con null caso contrario
     *                   es un objecto dentro de la clase
     * @return los parametros para la url
     */
    public static String armarUrlCamposObj(Object obj, Class clazz, String startIndex) {
        try {
            if (obj != null) {

                String urlParametros = startIndex == null ? "?" : "";
                List<Field> fields = Utils.getPrivateFields(clazz);
//            Collections.sort(fields, (a, b) -> {
//                return (b.getType().getPackageName().substring(0) ).compareTo((a.getType().getPackageName()).substring(0));
//            });
                //  fields.sort((Field a, Field b) -> b.getType().getPackageName().compareTo(a.getType().getPackageName()));
                Object value;
                for (Field field : fields) {
                    field.setAccessible(true);
                    value = field.get(obj);
                    if (!field.getType().equals(List.class)) {
                        if (value != null) {
                            if (field.getType().getPackage().getName().startsWith("java.lang")) {
                                if (startIndex == null) {
                                    urlParametros = urlParametros + field.getName() + "=" + value + "&";
                                } else {
                                    urlParametros = urlParametros + (startIndex + "." + field.getName()) + "=" + value + "&";
                                }
                            } else {//ES UN OBJECTO
                                urlParametros = urlParametros + armarUrlCamposObj(value, value.getClass(), (startIndex == null ? "" : (startIndex + ".")) + field.getName());
                            }
                        }
                    }
                }
                if (urlParametros.length() > 1) {//SIGNIFICA QUE NINGUN CAMPO ES NULO
                    if (urlParametros.substring(0, urlParametros.length() - 1).contains("&") && startIndex == null) {
                        urlParametros = Utils.removeLastChar(urlParametros).replace(" ", "%20");//ELIMINA EL ULTIMO & DE LA URL =o

                    }
                    return urlParametros;
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static String getDate(Date fecha) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return dateFormat.format(fecha);
    }


    public static Map<String, String> getDatos(String valor) {
        Map<String, String> map = new HashMap<>();
        String apellidos = "";
        String nombres = "";
        String[] datos = valor.split(" ");
        for (int i = 0; i < datos.length; i++) {
            switch (i) {
                case 0:
                case 1:
                    apellidos = apellidos + datos[i] + " ";
                    break;
                default:
                    nombres = nombres + datos[i] + " ";
            }
        }
        map.put("nombre", nombres.trim());
        map.put("apellido", apellidos.trim());
        return map;
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static void setEnv(Map<String, String> newenv) throws Exception {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(newenv);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(newenv);
        } catch (NoSuchFieldException e) {
            Class[] classes = Collections.class.getDeclaredClasses();
            Map<String, String> env = System.getenv();
            for (Class cl : classes) {
                if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                    Field field = cl.getDeclaredField("m");
                    field.setAccessible(true);
                    Object obj = field.get(env);
                    Map<String, String> map = (Map<String, String>) obj;
                    map.clear();
                    map.putAll(newenv);
                }
            }
        }
    }

    public static HttpEntity<String> headers(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }

    public static HttpEntity<String> headersPDF(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_PDF);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }


    public static Boolean isValidPassword(String password) {
        // Regex to check valid password.
        String regex = "^(?=.*[0-9a-zA-Z])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=\\.\\*\\/-\\_\\(\\)]).{8,40}$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        // If the password is empty return false
        if (password == null) {
            return false;
        }
        // Pattern class contains matcher() method
        // to find matching between given password and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password matched the ReGex
        return m.matches();
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

    public static Integer getRestarDias(Date inicio, Date fin) {
        int res = (int) ((fin.getTime() - inicio.getTime()) / (1000 * 60 * 60 * 24));
        return res;
    }


    public static List<String> meses() {
        List<String> meses = new ArrayList<>();
        meses.add("ENERO");
        meses.add("FEBRERO");
        meses.add("MARZO");
        meses.add("ABRIL");
        meses.add("MAYO");
        meses.add("JUNIO");
        meses.add("JULIO");
        meses.add("AGOSTO");
        meses.add("SEPTIEMBRE");
        meses.add("OCTUBRE");
        meses.add("NOVIEMBRE");
        meses.add("DICIEMBRE");
        return meses;
    }


    public static Object gsonTransformDto(String data, Class clase) {
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping();
        Gson gson = builder.create();
        return gson.fromJson(data, clase);
    }

    public static ArrayList<Object> gsonTransformDtoList(String data) {
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping();
        Gson gson = builder.create();
        return gson.fromJson(data, ArrayList.class);
    }

    public static String toJson(Object data) {
        GsonBuilder builder = new GsonBuilder().enableComplexMapKeySerialization()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setObjectToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER);
        builder.excludeFieldsWithModifiers(Modifier.STATIC);

        return builder.create().toJson(data);
    }

    public static Object toObjecttoHashMap(Object json) {
        if (json == null) {
            return null;
        }
        try {
            GsonBuilder builder = new GsonBuilder();
            Class finalParentClazz = null;
            Type typeOfHashMap = null;
            if (json instanceof Collection || json instanceof List || json instanceof ArrayList) {
                List l = (List) json;
                if (Utils.isNotEmpty(l)) {
                    finalParentClazz = ((List) json).get(0).getClass();
                } else {
                    finalParentClazz = Object.class;
                }
                typeOfHashMap = new TypeToken<List<Map<String, Object>>>() {
                }.getType();
            } else {
                typeOfHashMap = new TypeToken<Map<String, Object>>() {
                }.getType();
                finalParentClazz = json.getClass();
            }
            builder.enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .excludeFieldsWithModifiers(Modifier.STATIC)
                    .setObjectToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER);

            Gson gson2 = builder.create();
            String js = gson2.toJson(json);
            return gson2.fromJson(js, typeOfHashMap);
        } catch (Exception e) {
            Logger.getLogger("Utils").log(Level.SEVERE, "Transformar json a objeto.", e);
            return null;
        }
    }

    public static List<String> getDateList(Date startDate, Date endDate) {
        List<String> dateList = new ArrayList<>();
        // Formatter for the output
        DateFormat outputFormatter = new SimpleDateFormat("MMMM-yyyy");

        // Calendar to start with
        Calendar startWith = Calendar.getInstance();
        startWith.setTime(startDate);
        startWith.set(Calendar.DAY_OF_MONTH, 1);

        for (Calendar calendar = startWith; calendar.getTime().getTime() <= endDate.getTime(); calendar
                .add(Calendar.MONTH, 1)) {
            dateList.add(outputFormatter.format(calendar.getTime()).toUpperCase());
        }

        return dateList;
    }

    public static String formatDolar(Double valor) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(valor);
    }

    public static Date devolverFecha(Date fecha, String formato) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(formato);
        String dateString = formatter.format(fecha);
        Date date = formatter.parse(dateString);
        return date;
    }


    public static String encodeBase64(String text) {
        if (text == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public static String decodeBase64(String text) {
        if (text == null) {
            return null;
        }
        byte[] decode = Base64.getDecoder().decode(text);
        return new String(decode);
    }


    public static List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {

        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

    public static String getMes(Integer mes) {
        String mesString;
        switch (mes) {
            case 1:
                mesString = "Enero";
                break;
            case 2:
                mesString = "Febrero";
                break;
            case 3:
                mesString = "Marzo";
                break;
            case 4:
                mesString = "Abril";
                break;
            case 5:
                mesString = "Mayo";
                break;
            case 6:
                mesString = "Junio";
                break;
            case 7:
                mesString = "Julio";
                break;
            case 8:
                mesString = "Agosto";
                break;
            case 9:
                mesString = "Septiembre";
                break;
            case 10:
                mesString = "Octubre";
                break;
            case 11:
                mesString = "Noviembre";
                break;
            case 12:
                mesString = "Diciembre";
                break;
            default:
                mesString = "Invalid month";
                break;
        }
        return mesString;
    }

    public static Double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String generarID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public static List<Integer> getAnios(Integer desde, Integer hasta) {
        List<Integer> anioList = new ArrayList<>();
        try {
            for (int i = desde; i <= hasta; i++) {
                anioList.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return anioList;
    }


    public static String fechaDeCoretDelMes(Integer mes, Integer anio) {
        YearMonth yearMonth = YearMonth.of(anio, mes);
        LocalDate ultimoDiaDelMes = yearMonth.atEndOfMonth();
        String nombreMes = getMes(mes);
        return String.format("Al %d  de %s %d", ultimoDiaDelMes.getDayOfMonth(), nombreMes, anio);
    }

    public static String corregirTextEditor(String value) {
        try {
            return value.replace("<em>", "<i>").replace("</em>", "</i>")
                    .replace("<strong>", "<b>").replace("</strong>", "</b>");
        } catch (Exception e) {
            System.out.println("e " + e.getMessage());
            return null;
        }
    }

    public static String obtenerExtensionDoc(String archivo) {
        String lastOne = "";
        try {
            String[] tipo = archivo.split("\\.");
            System.out.println("// tipo[tipo.length - 1] " + tipo[tipo.length - 1]);
            lastOne = tipo[tipo.length - 1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ".".concat(lastOne);
    }




}

