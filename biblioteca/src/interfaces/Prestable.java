package interfaces;

import excepciones.MaterialNoDisponibleException;

public interface Prestable {
    void prestar() throws MaterialNoDisponibleException;
    void devolver();
    boolean estaPrestado();
}
