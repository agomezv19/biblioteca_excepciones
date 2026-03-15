package modelos;

import excepciones.MaterialNoDisponibleException;
import interfaces.Prestable;

public abstract class Material implements Prestable {
    private String id;
    private String titulo;
    private boolean prestado;

    public Material(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.prestado = false;
    }

    public String getId()          { return id; }
    public String getTitulo()      { return titulo; }
    public boolean estaPrestado()  { return prestado; }
    public void setPrestado(boolean prestado) { this.prestado = prestado; }

    @Override
    public void prestar() throws MaterialNoDisponibleException {
        if (prestado) {
            throw new MaterialNoDisponibleException(
                "El material '" + titulo + "' (ID: " + id + ") ya está prestado.");
        }
        prestado = true;
    }

    @Override
    public void devolver() {
        prestado = false;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + titulo + " - " + (prestado ? "Prestado" : "Disponible");
    }
}
