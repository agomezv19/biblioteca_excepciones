import excepciones.BibliotecaException;
import excepciones.MaterialNoEncontradoException;
import excepciones.UsuarioNoEncontradoException;
import modelos.*;
import util.RegistroErrores;

public class Main {

    // biblioteca
    private static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║            Sistema de Biblioteca             ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // Registrar materiales y usuarios
        System.out.println("=== Registrando materiales y usuarios ===");
        biblioteca.registrarMaterial(new Libro("L001", "Clean Code", "Robert C. Martin", "978-0132350884"));
        biblioteca.registrarMaterial(new Libro("L002", "El Quijote", "Miguel de Cervantes", "978-8491050490"));
        biblioteca.registrarMaterial(new Libro("L003", "Effective Java", "Joshua Bloch", "978-0134685991"));
        biblioteca.registrarMaterial(new Libro("L004", "Design Patterns", "GoF", "978-0201633610"));
        biblioteca.registrarMaterial(new Revista("R001", "National Geographic", 350));

        biblioteca.registrarUsuario(new Usuario(1, "Ana García"));
        biblioteca.registrarUsuario(new Usuario(2, "Luis Pérez"));

        // Escenario 1: Préstamo exitoso
        ejecutarEscenario(" 1 — Préstamo exitoso",
                () -> biblioteca.prestarMaterial(1, "L001"));

        // Escenario 2: Material ya prestado
        ejecutarEscenario(" 2 — Intentar prestar material ya prestado",
                () -> biblioteca.prestarMaterial(2, "L001"));   // L001 ya fue prestado

        //  Escenario 3: Material inexistente
        ejecutarEscenario(" 3 — Material inexistente",
                () -> biblioteca.prestarMaterial(1, "L999"));

        //  Escenario 4: Usuario inexistente
        ejecutarEscenario(" 4 — Usuario inexistente",
                () -> biblioteca.prestarMaterial(99, "L002"));

        // Escenario 5: Superar máximo de préstamos
        ejecutarEscenario(" 5 — Superar máximo de préstamos (máx. 3)",
                () -> {
                    biblioteca.prestarMaterial(1, "L002");
                    biblioteca.prestarMaterial(1, "L003");
                    biblioteca.prestarMaterial(1, "L004");  // el 4.º debe fallar
                });

        // Devolución exitosa
        ejecutarEscenario(" 6 Devolución exitosa",
                () -> biblioteca.devolverMaterial(1, "L001"));

        // Devolución de material con usuario inexistente
        ejecutarEscenario(" 7 Devolución con usuario inexistente",
                () -> biblioteca.devolverMaterial(99, "L001"));

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║   Fin del programa, errores.log actualizado  ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    //

    @FunctionalInterface
    interface Operacion {
        void ejecutar() throws Exception;
    }

    private static void ejecutarEscenario(String titulo, Operacion operacion) {
        System.out.println("\n--- " + titulo + " ---");
        try {
            operacion.ejecutar();
        } catch (BibliotecaException e) {
            System.out.println("   Error de biblioteca: " + e.getMessage());
            RegistroErrores.registrar(e);
        } catch (Exception e) {
            System.out.println("   Error inesperado: " + e.getMessage());
            RegistroErrores.registrar(e);
        } finally {
            System.out.println("  → Operación finalizada.");
        }
    }
}
