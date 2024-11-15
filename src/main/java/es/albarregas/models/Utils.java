package es.albarregas.models;

import es.albarregas.beans.ViniloBean;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alberto
 */
public class Utils {

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

    public static void aniadirProducto(ArrayList<ViniloBean> lista, ViniloBean vinilo, String mensaje) {
        
        boolean existe = false;
        if (!lista.isEmpty()) {

            for (ViniloBean viniloBuscar : lista) {

                if (viniloBuscar.getTitulo().equals(vinilo.getTitulo())) {
                    viniloBuscar.setCantidad(viniloBuscar.getCantidad() + vinilo.getCantidad());
                    existe = true;
                }
            }

        }
        
        if (!existe) {
            lista.add(vinilo);
            mensaje = "Se ha añadido " + vinilo.getCantidad() + " unidades de " + vinilo.getTitulo();
        }
        
    }

    public static void transformarArray(ArrayList<ViniloBean> lista, StringBuilder valorCookie) {
        for (ViniloBean vinilo : lista) {
            valorCookie.append(vinilo.toString());
        }
        //return valorCookie;
    }

    public static void aumentarCantidad(ViniloBean vinilo) {

        vinilo.setCantidad(vinilo.getCantidad() + 1);

    }

    public static void disminuirCantidad(ViniloBean vinilo) {

        if (vinilo.getCantidad() > 1) {
            vinilo.setCantidad(vinilo.getCantidad() - 1);
        }
    }
}
