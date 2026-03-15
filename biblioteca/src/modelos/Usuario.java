package modelos;

import excepciones.MaterialNoDisponibleException;
import excepciones.MaximoPrestamosException;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static final int MAX_PRESTAMOS = 3;

    private int id;
    private String nombre;
    private List<Material> prestamos;

    public Usuario(int id, String nombre) {
        this.id       = id;
        this.nombre   = nombre;
        this.prestamos = new ArrayList<>();
    }

    public int getId()       { return id; }
    public String getNombre(){ return nombre; }
    public int cantidadPrestamos() { return prestamos.size(); }

    public void prestarMaterial(Material material)
            throws MaximoPrestamosException, MaterialNoDisponibleException {

        if (prestamos.size() >= MAX_PRESTAMOS) {
            throw new MaximoPrestamosException(
                "El usuario '" + nombre + "' ya tiene " + MAX_PRESTAMOS + " préstamos activos.");
        }
        material.prestar();
        prestamos.add(material);
    }

    public void devolverMaterial(Material material) {
        material.devolver();
        prestamos.remove(material);
    }

    public List<Material> getPrestamos() { return prestamos; }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " (Préstamos activos: " + prestamos.size() + ")";
    }
}
