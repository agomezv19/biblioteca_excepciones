package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroErrores {

    private static final String ARCHIVO = "errores.log";
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static void registrar(Exception e) {
        String linea = "[" + LocalDateTime.now().format(FORMATO) + "] "
                + e.getClass().getSimpleName() + " - " + e.getMessage();


        System.out.println("   LOG: " + linea);


        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(linea);
        } catch (IOException ex) {
            System.err.println("  [RegistroErrores] No se pudo escribir en el log: "
                    + ex.getMessage());
        }
    }
}
