package es.albarregas.controllers;

import es.albarregas.beans.ViniloBean;
import es.albarregas.models.Utils;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alberto
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Creamos la sesión
        HttpSession sesion = request.getSession();

        //sesion.setAttribute("carrito", carrito);
        // Creamo la lista de vinilos que nos servirá para almacenar el carrito del usuario
        ArrayList<ViniloBean> vinilos = new ArrayList<>();
        Cookie carritoCookie = Utils.buscarCoockie(request);
        if (carritoCookie != null) {
            // Almaceno la lista creada con el contenido de la cookie
            ArrayList listaAux = Utils.crearListaCookie(request, "carrito");
            // Y la añadimos en el carro(vinilos) del cliente
            vinilos.addAll(listaAux);
        }

        sesion.setAttribute("vinilos", vinilos);
        sesion.setAttribute("carritoCookie", carritoCookie);

        // Reenviamos al usuario a "tiendaVista.jsp"
        request.getRequestDispatcher("JSP/tiendaVista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        // Lista de vinilos de la tienda

        // Buscamos la cookie y almacenamos el valor de la busqueda de la cookie 
        //Cookie carritoCookie = Utils.buscarCoockie(request);
        Cookie carritoCookie = (Cookie) sesion.getAttribute("carritoCookie");

        // En caso de que la cookie no exista obtendremos la lista de la sesión
        ArrayList<ViniloBean> vinilos = (ArrayList<ViniloBean>) sesion.getAttribute("vinilos");
        // Mensaje que se le va a mostrar al usuario tanto de avisos como de que se ha añadido en el carrito
        StringBuilder mensaje = new StringBuilder();
        // Url para el requestDispacher
        String url = "";
        /* Variable que almacena la cantidad en int para poder hacer la comprobación de que el usuario haya
           ingresado al menos 1 en la cantidad */
        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(request.getParameter("cantidad"));
        } catch (NumberFormatException e) {
            mensaje.append("*La cantidad debe ser al menos 1");
        }
        /* Comprobamos si el usuario ha ingresado menos de 1 unidad o ha dejado el campo de los vinilos
           vacios */
        if (cantidad < 1 || request.getParameter("vinilos") == null) {
            // Limpiamos previamente el mensaje
            mensaje.setLength(0);
            // En caso de que la cantidad sea <1 o null personalizamos el mensaje
            if (cantidad < 1) {
                // Personalizamos el mensaje
                mensaje.append("*La cantidad debe ser al menos 1");
                // Pasamos el mensaje por petición
                request.setAttribute("mensaje", mensaje.toString());
            } else {
                mensaje.append("*Por favor, elija un vinilo");
                request.setAttribute("mensaje", mensaje.toString());
            }
            // Redirigimos al usuario a la tienda de nuevo
            url = "JSP/tiendaVista.jsp";
        } else {
            // Comprobamos si el botón aniadir ha sido pulsado
            if (request.getParameter("aniadir") != null) {
                // Creamos el vinilo
                ViniloBean vinilo = Utils.crearProducto(request.getParameter("vinilos"), request.getParameter("cantidad"));
                // Añadimos el vinilo al carrito
                Utils.aniadirProducto(vinilos, vinilo, mensaje);
                // Añadimos el carrito en la sesión y el mensaje lo pasamos por petición
                sesion.setAttribute("vinilos", vinilos);
                request.setAttribute("mensaje", mensaje.toString());
                // Parte array cookie
                StringBuilder valorCookie = new StringBuilder();
                /* Rellenamos el StringBuilder "valorCookie" con los valores del ArrayList y Almacenamos en un String
                   el valor del StringBuilder que lo usaremos para pasarselo a la cookie */
                String valor = Utils.transformarArray(vinilos, valorCookie);
                // Si la cookie es null la creamos y si no modificamos su valor
                if (carritoCookie == null) {
                    //Creamos cookie
                    carritoCookie = Utils.crearCookie("carrito", valorCookie);
                    response.addCookie(carritoCookie);
                    //response.addCookie(carritoCookie);
                } else {
                    carritoCookie.setValue(URLEncoder.encode(valor, "UTF-8"));
                }
                // Ponemos que la cookie pueda ser accesible en todo el dominio de la web 
                carritoCookie.setPath("/");
                // Añadimos la cookie
                response.addCookie(carritoCookie);
                // Reedirigimos el usuario a la tienda
                url = "JSP/tiendaVista.jsp";
            }
        }
        // Si se ha pulsado ver carrito reedirigimos al usuario a la vista del contenido del carrito
        if (request.getParameter("verCarrito") != null) {
            url = "JSP/carritoVista.jsp";
        } else if (request.getParameter("volver") != null) {
            /* En caso de pulsar el botón final del resumen de la compra invalidamos la sesion, la lista de vinilos
               y reedirigimos al usuario al index */
            sesion.invalidate();
            vinilos = null;
            url = ".";
        } else if (request.getParameter("finalizar") != null) {
            // Calculamos el total y lo pasamos por petición
            double totalCarrito = Utils.calcularTotal(vinilos);
            request.setAttribute("totalCarrito", totalCarrito);
            // Borramos la cookie
            if (carritoCookie == null) {
                carritoCookie = Utils.buscarCoockie(request);
                carritoCookie.setMaxAge(0);
                carritoCookie.setPath("/");
                response.addCookie(carritoCookie);
            } else {
                carritoCookie.setMaxAge(0);
                carritoCookie.setPath("/");
                response.addCookie(carritoCookie);
            }
            // Reedirigimos al usuario al resumen de su compra
            url = "/JSP/resumenVista.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
