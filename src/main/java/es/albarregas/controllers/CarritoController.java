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
 * @author alberto
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

        String boton = request.getParameter("accion");
        String url = "";
        if (request.getParameter("seguir") != null) {
            url = "/JSP/tiendaVista.jsp";
        } else {

            HttpSession sesion = request.getSession();
            ArrayList vinilos = (ArrayList) sesion.getAttribute("vinilos");
            String[] partes = boton.split("\\+");
            String accion = partes[0];
            String elemento = partes[1];

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
            url = "/JSP/carritoVista.jsp";
            sesion.setAttribute("vinilos", vinilos);
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
