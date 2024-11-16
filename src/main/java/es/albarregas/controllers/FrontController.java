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

        // Buscamos la cookie
        Cookie[] cookies = request.getCookies();
        // Almacenamos el valor de la busqueda de la cookie
        Cookie carrito = Utils.buscarCoockie(cookies);
        sesion.setAttribute("carrito", carrito);

        ArrayList<ViniloBean> vinilos = new ArrayList<>();
        sesion.setAttribute("vinilos", vinilos);

        // Reenviamos al usuario a "tiendaVista.jsp"
        request.getRequestDispatcher("JSP/tiendaVista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        // Lista de vinilos de la tienda
        ArrayList<ViniloBean> vinilos = (ArrayList<ViniloBean>) sesion.getAttribute("vinilos");
        StringBuilder mensaje = new StringBuilder();
        String url = "";      

        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        if (cantidad < 1 || request.getParameter("vinilos")==null) {
            // Limpiamos previamente el mensaje
            mensaje.setLength(0);
            
            if (cantidad < 1) {        
                mensaje.append("*La cantidad debe ser al menos 1");
                request.setAttribute("mensaje", mensaje.toString());
            }else{
                mensaje.append("*Por favor, elija un vinilo");
                request.setAttribute("mensaje", mensaje.toString());
            }
            
            
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
                Utils.transformarArray(vinilos, valorCookie);
                sesion.setAttribute("valorCookie", valorCookie);
                
               
                url = "JSP/tiendaVista.jsp";
            }   
        }

        if(request.getParameter("verCarrito") != null){
            url = "JSP/carritoVista.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
