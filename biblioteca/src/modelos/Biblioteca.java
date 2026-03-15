package modelos;

import excepciones.*;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Material> materiales;
    private List<Usuario>  usuarios;

    public Biblioteca() {
        materiales = new ArrayList<>();
        usuarios   = new ArrayList<>();
    }

    //  Registro

    public void registrarMaterial(Material material) {
        materiales.add(material);
        System.out.println("   Material registrado: " + material);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("   Usuario registrado: " + usuario);
    }

    // Búsqueda

    public Material buscarMaterial(String id) throws MaterialNoEncontradoException {
        return materiales.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MaterialNoEncontradoException(
                        "No se encontró el material con ID: " + id));
    }

    public Usuario buscarUsuario(int id) throws UsuarioNoEncontradoException {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                        "No se encontró el usuario con ID: " + id));
    }

    //  Operaciones

    public void prestarMaterial(int usuarioId, String materialId)
            throws UsuarioNoEncontradoException,
                   MaterialNoEncontradoException,
                   MaterialNoDisponibleException,
                   MaximoPrestamosException {

        Usuario  usuario  = buscarUsuario(usuarioId);
        Material material = buscarMaterial(materialId);
        usuario.prestarMaterial(material);
        System.out.println("   Préstamo exitoso: " + usuario.getNombre()
                + " ← " + material.getTitulo());
    }

    public void devolverMaterial(int usuarioId, String materialId)
            throws UsuarioNoEncontradoException, MaterialNoEncontradoException {

        Usuario  usuario  = buscarUsuario(usuarioId);
        Material material = buscarMaterial(materialId);
        usuario.devolverMaterial(material);
        System.out.println("   Devolución exitosa: " + usuario.getNombre()
                + " → " + material.getTitulo());
    }
}
