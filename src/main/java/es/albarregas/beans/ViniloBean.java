package es.albarregas.beans;

import java.util.Objects;

/**
 *
 * @author alberto
 */
public class ViniloBean {

    private String titulo;
    private double precio;
    private int cantidad;

    public ViniloBean() {
    }

    public ViniloBean(String titulo, double precio) {
        this.titulo = titulo;
        this.precio = precio;
        this.cantidad = 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    @Override
    public String toString() {
         return titulo + '+' + precio + '+' + cantidad + ';';
    }
    
    
}
