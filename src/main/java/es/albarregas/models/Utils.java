package es.albarregas.models;

import es.albarregas.beans.ViniloBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alberto
 */
public class Utils {

    // Método para buscar la cookie
    public static Cookie buscarCoockie(Cookie[] cookies) {

        Cookie cookie = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("nombreCookie")) {
                    cookie = cookies[i];
                    break;
                }
            }
        }
        return cookie;
    }

    // Método para crear el vinilo que ha seleccionado el usuario
    public static ViniloBean crearProducto(String valorInput, String cantidadTexto) {

        ViniloBean vinilo = new ViniloBean();
        String[] partes = valorInput.split("\\+");
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

    public static void transformarArray(ArrayList<ViniloBean> lista, StringBuilder valorCookie) {
        // Por cada elemento en la lista lo añado al StringBuilder
        for (ViniloBean vinilo : lista) {
            valorCookie.append(vinilo.toString());
        }
    }

    public static void aumentarCantidad(String titulo, ArrayList<ViniloBean> lista) {

        for (ViniloBean vinilo : lista) {
            if (vinilo.getTitulo().equals(titulo)) {
                vinilo.setCantidad(vinilo.getCantidad() + 1);
            }
        }

    }

    public static void disminuirCantidad(String titulo, ArrayList<ViniloBean> lista) {

        for (ViniloBean vinilo : lista) {
            if (vinilo.getTitulo().equals(titulo)) {
                vinilo.setCantidad(vinilo.getCantidad() - 1);
            }
        }

        // Si es mayor de 1 seguirá restando
        //if (vinilo.getCantidad() > 1) {
        //    vinilo.setCantidad(vinilo.getCantidad() - 1);
        //}
    }

    public static void eliminarProducto(String titulo, ArrayList<ViniloBean> lista) {
        Iterator<ViniloBean> iterator = lista.iterator();
        while (iterator.hasNext()) {
            ViniloBean vinilo = iterator.next();
            if (vinilo.getTitulo().equals(titulo)) {
                iterator.remove();
            }
        }
    }

}
