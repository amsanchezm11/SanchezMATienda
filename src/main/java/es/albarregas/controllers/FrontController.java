package es.albarregas.controllers;

import es.albarregas.beans.ViniloBean;
import es.albarregas.models.Utils;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
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
        // Almaceno el valor de la busqueda de la cookie
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
        String mensaje = "";
        String url = "";      

        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        if (cantidad < 1) {
            mensaje = "*La cantidad debe ser al menos 1";
            request.setAttribute("mensaje", mensaje);
            url = "JSP/tiendaVista.jsp";
        } else {

            if (request.getParameter("aniadir") != null) {

                // Creamos el vinilo
                ViniloBean vinilo = Utils.crearProducto(request.getParameter("vinilos"), request.getParameter("cantidad"));

                // Añadimos el vinilo al carrito
                Utils.aniadirProducto(vinilos, vinilo, mensaje);
                sesion.setAttribute("vinilos", vinilos);
                request.setAttribute("mensaje", mensaje);

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