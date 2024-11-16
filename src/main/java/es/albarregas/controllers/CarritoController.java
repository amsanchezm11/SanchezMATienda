package es.albarregas.controllers;

import es.albarregas.models.Utils;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

        // Comprobamos si se ha pulsado el botón de Seguir Comprando
        if (request.getParameter("seguir") != null) {
            url = "/JSP/tiendaVista.jsp";
        } else if (request.getParameter("vaciar") != null) {
            
                Utils.vaciarCarrito(vinilos);              
                
                sesion.setAttribute("vinilos", vinilos);
                url = "/JSP/carritoVista.jsp";
            

        } else if (request.getParameter("comprar") != null) {
            double totalCarrito = Utils.calcularTotal(vinilos);
            sesion.setAttribute("totalCarrito", totalCarrito);
            url = "/JSP/resumenVista.jsp";
        } else {

            // Dividimos el value del boton ya que nos entrará un valor tipo --> "sumar+TituloVinilo"
            String[] partes = boton.split("\\+");
            // Almaceno la primera palabra --> Ej:"sumar"
            String accion = partes[0];
            // Almacenamos la segunda palabra --> Ej:"TituloVinilo"
            String elemento = partes[1];
            // Compruebo que se haya pulsado el boton accion
            if (accion != null) {
                /* Valoramos el resultado que hemos obtenido (sumar / restar / eliminar) y según el que nos entre 
            nuestro controlador llamará al método correspondiente */
                switch (accion) {
                    case "sumar":
                        Utils.aumentarCantidad(elemento, vinilos);
                        break;
                    case "restar":
                        Utils.disminuirCantidad(elemento, vinilos);
                        break;
                    case "eliminar":
                        Utils.eliminarProducto(elemento, vinilos);
                        
                        break;
                }
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
