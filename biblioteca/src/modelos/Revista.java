package modelos;

public class Revista extends Material {
    private int numero;

    public Revista(String id, String titulo, int numero) {
        super(id, titulo);
        this.numero = numero;
    }

    public int getNumero() { return numero; }

    @Override
    public String toString() {
        return super.toString() + " | Número: " + numero;
    }
}
