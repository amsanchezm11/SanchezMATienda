package es.albarregas.beans;

public class ViniloBean {

    private String titulo;
    private double precio;
    private int cantidad;

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

    // Le añado al Bean un toString para así codificarlo a gusto con el patrón que quiero en la cookie
    @Override
    public String toString() {
         return titulo + '+' + precio + '+' + cantidad + ';';
    }
    
    
}
