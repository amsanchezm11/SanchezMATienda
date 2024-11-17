package es.albarregas.models;

import es.albarregas.beans.ViniloBean;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author alberto
 */
public class Utils {

    // Método para buscar la cookie
    public static Cookie buscarCoockie(HttpServletRequest request) {
        
        Cookie cookie = null;        
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("carrito")) {
                    cookie = cookies[i];
                    return cookie;
                    //break;
                }
            }
        }
        return null;
    }
    // Método para crear la coockie
    public static Cookie crearCookie(String nombre, StringBuilder valor) {
        try {
            String valorCookie = valor.toString();
            // Codifica el valor de la cookie para evitar problemas con caracteres especiales
            Cookie cookie = new Cookie(nombre, URLEncoder.encode(valorCookie, "UTF-8"));
            cookie.setMaxAge(24 * 60 * 60);// 1 día
            cookie.setPath("/");           
            return cookie;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Método para obtener el valor de la cookie
    public static String obtenerCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return null;
    }
    
    
    // Método para crear rellenar la lista con los valores de la cookie
    public static ArrayList<ViniloBean> crearListaCookie(HttpServletRequest request, String cookieName) {
        // Obetenemos el valor de la cookie
        String cookieValue = obtenerCookie(request, cookieName);
        ArrayList<ViniloBean> vinilos = new ArrayList<>();

        if (cookieValue != null) {
            // Remplazamos los + por espacios
            String valores = cookieValue.replace("+", " ");
            // Separamos cada "elemento" despues del signo "*"
            String[] objetos = valores.split("\\*");
            // Recorremos
            for (String objeto : objetos) {
                // Separar atributos por "_"
                String[] atributos = objeto.split("\\_");
                if (atributos.length == 3) {
                    String titulo = atributos[0];
                    double precio = Double.parseDouble(atributos[1]);
                    int cantidad = Integer.parseInt(atributos[2]);
                    ViniloBean vinilo = new ViniloBean(titulo, precio, cantidad);
                    vinilos.add(vinilo);
                }
            }
        }

        return vinilos;
    }

    // Método para crear el vinilo que ha seleccionado el usuario
    public static ViniloBean crearProducto(String valorInput, String cantidadTexto) {

        ViniloBean vinilo = new ViniloBean();
        String[] partes = valorInput.split("\\_");
        String titulo = partes[0];
        double precio = Double.parseDouble(partes[1]);
        int cantidad = Integer.parseInt(cantidadTexto);

        vinilo.setTitulo(titulo);
        vinilo.setPrecio(precio);
        vinilo.setCantidad(cantidad);

        return vinilo;
    }

    // Método que añade el vinilo a la lista de vinilos(carrito) y asigna el mensaje que se le va a mostrar al usuario
    public static void aniadirProducto(ArrayList<ViniloBean> lista, ViniloBean vinilo, StringBuilder mensaje) {

        boolean existe = false;
        // Si la lista(carrito) no está vacia
        if (!lista.isEmpty()) {
            for (ViniloBean viniloBuscar : lista) {
                // Busco en la lista(carrito) el vinilo que tenga el mismo nombre que el que quiero sumar la cantidad
                if (viniloBuscar.getTitulo().equals(vinilo.getTitulo())) {
                    viniloBuscar.setCantidad(viniloBuscar.getCantidad() + vinilo.getCantidad());
                    existe = true;
                }
            }
        }
        // Si el vinilo no existe en la lista lo añado
        if (!existe) {
            // Añadimos el vinilo a la lista de vinilos(carrito)
            lista.add(vinilo);
            // Mensaje personalizado que indica al usuario qué se ha añadido al carrito

        }
        // Limpio previamente el mensaje
        mensaje.setLength(0);
        mensaje.append("Se ha añadido ").append(vinilo.getCantidad()).append(" unidades de ").append(vinilo.getTitulo());
    }

    /* Método que rellena el StringBuilder con el contenido de la lista de vinilos y devuelve el StringBuilder
       convertido a String */
    public static String transformarArray(ArrayList<ViniloBean> lista, StringBuilder valorCookie) {
        // Por cada elemento en la lista lo añado al StringBuilder
        for (ViniloBean vinilo : lista) {
            valorCookie.append(vinilo.toString());
        }
        return valorCookie.toString();
    }
    // Método que aumenta la cantidad del vinilo que ha seleccionado el usuario
    public static void aumentarCantidad(String titulo, ArrayList<ViniloBean> lista) {
        for (ViniloBean vinilo : lista) {
            if (vinilo.getTitulo().equals(titulo)) {
                vinilo.setCantidad(vinilo.getCantidad() + 1);
            }
        }
    }
    // Método que disminuye la cantidad del vinilo que ha seleccionado el usuario
    public static void disminuirCantidad(String titulo, ArrayList<ViniloBean> lista) {

        for (ViniloBean vinilo : lista) {
            if (vinilo.getTitulo().equals(titulo)) {
                vinilo.setCantidad(vinilo.getCantidad() - 1);
            }
        }
    }
    // Método que elimina el vinilo que ha seleccionado el usuario
    public static void eliminarProducto(String titulo, ArrayList<ViniloBean> lista) {
        Iterator<ViniloBean> iterator = lista.iterator();
        while (iterator.hasNext()) {
            ViniloBean vinilo = iterator.next();
            if (vinilo.getTitulo().equals(titulo)) {
                iterator.remove();
            }
        }

    }
    // Método que vacia la lista de vinilos
    public static void vaciarCarrito(ArrayList<ViniloBean> lista) {
        if (lista != null) {
            lista.clear();
        }
    }
    // Método que calcula el total por cada elemento que hay en la lista
    public static double calcularTotal(ArrayList<ViniloBean> lista) {
        double total = 0;
        for (ViniloBean vinilo : lista) {
            total += (vinilo.getPrecio() * vinilo.getCantidad());
        }
        return total;
    }
}
