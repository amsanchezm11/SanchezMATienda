package es.albarregas.controllers;

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
 * @author alberto Controlador encargado de gestionar la parte de la vista
 * "carritoVista"
 */
@WebServlet(name = "CarritoController", urlPatterns = {"/CarritoController"})
public class CarritoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtenemos el valor del input que el usuario ha pulsado (+ / - / eliminar)
        String boton = request.getParameter("accion");
        // Url a donde vamos a dirigir al usuario(De primeras vacía)
        String url = "";
        // Recuperamos la sesión
        HttpSession sesion = request.getSession();
        // Recuperamos la lista que tenemos almacenada en la sesión
        ArrayList vinilos = (ArrayList) sesion.getAttribute("vinilos");
        // Obtengo la cookie de la sesión
        //Cookie carritoCookie = (Cookie) sesion.getAttribute("carritoCookie");
        Cookie carritoCookie = Utils.buscarCoockie(request);

        // Comprobamos si se ha pulsado el botón de Seguir Comprando
        if (request.getParameter("seguir") != null) {
            url = "/JSP/tiendaVista.jsp";
        } else if (request.getParameter("vaciar") != null) {
            // Vaciamos el carrito
            Utils.vaciarCarrito(vinilos);
            carritoCookie.setMaxAge(0);
            carritoCookie.setPath("/");
            response.addCookie(carritoCookie);
            sesion.setAttribute("vinilos", vinilos);
            url = "/JSP/carritoVista.jsp";
        } else if (request.getParameter("comprar") != null) {
            // Calculamos el total del carrito
            double totalCarrito = Utils.calcularTotal(vinilos);
            // Enviamos el total 
            sesion.setAttribute("totalCarrito", totalCarrito);
            carritoCookie.setMaxAge(0);
            carritoCookie.setPath("/");
            response.addCookie(carritoCookie);
            url = "/JSP/resumenVista.jsp";
        } else {
            // Dividimos el value del boton ya que nos entrará un valor tipo --> "sumar+TituloVinilo"
            String[] partes = boton.split("\\+");
            // Almaceno la primera palabra --> Ej:"sumar"
            String accion = partes[0];
            // Almacenamos la segunda palabra --> Ej:"TituloVinilo"
            String elemento = partes[1];
            // Almacenamos su valor en un String para despues pasarlo a la cookie
            StringBuilder valorCookie = new StringBuilder();
            String valor;

            // Compruebo que se haya pulsado el boton accion
            if (accion != null) {
                /* Valoramos el resultado que hemos obtenido (sumar / restar / eliminar) y según el que nos entre 
            nuestro controlador llamará al método correspondiente */
                switch (accion) {
                    case "sumar":
                        // Sumamos +1 a la cantidad del vinilo seleccionado
                        Utils.aumentarCantidad(elemento, vinilos);
                        // Rellenamos el StringBuilder y almacenamos en "valor" su contenido
                        valor = Utils.transformarArray(vinilos, valorCookie);
                        // Modificamos el valor de la cookie
                        carritoCookie.setValue(URLEncoder.encode(valor, "UTF-8"));
                        // Añadimos sustituimos la cookie antigua por la nueva
                        carritoCookie.setPath("/");
                        response.addCookie(carritoCookie);
                        break;
                    case "restar":
                        // Restamos -1 a la cantidad del vinilo seleccionado
                        Utils.disminuirCantidad(elemento, vinilos);
                        // Rellenamos el StringBuilder y almacenamos en "valor" su contenido
                        valor = Utils.transformarArray(vinilos, valorCookie);
                        // Modificamos el valor de la cookie
                        carritoCookie.setValue(URLEncoder.encode(valor, "UTF-8"));
                        // Añadimos sustituimos la cookie antigua por la nueva
                        carritoCookie.setPath("/");
                        response.addCookie(carritoCookie);
                        break;
                    case "eliminar":
                        // Eliminamos el vinilo seleccionado
                        Utils.eliminarProducto(elemento, vinilos);
                        // Rellenamos el StringBuilder y almacenamos en "valor" su contenido
                        valor = Utils.transformarArray(vinilos, valorCookie);
                        // Modificamos el valor de la cookie
                        carritoCookie.setValue(URLEncoder.encode(valor, "UTF-8"));
                        // Añadimos sustituimos la cookie antigua por la nueva
                        carritoCookie.setPath("/");
                        response.addCookie(carritoCookie);
                        if (vinilos.isEmpty()) {
                            carritoCookie.setMaxAge(0);
                            carritoCookie.setPath("/");
                            response.addCookie(carritoCookie);
                        }
                        break;
                }
                // Añadimos el StringBuilder a la sesión
                sesion.setAttribute("valorCookie", valorCookie);
                // Añadimos la cookie a la sesión
                sesion.setAttribute("carritoCookie", carritoCookie);
            }

            // Cambiamos el valor de la ur para redirigir al usuario a la vista de carrito
            url = "/JSP/carritoVista.jsp";
            // Almacenamos de nuevo la lista de vinilos para que se apliquen los cambios de la acción que se haya realizado
            sesion.setAttribute("vinilos", vinilos);

        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
