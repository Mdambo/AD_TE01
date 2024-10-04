import java.io.*;
import java.util.*;

public class Ejercicio04_C {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduzca el tipo de personaje: ");
        String tipoSolicitado = scanner.nextLine().trim().toLowerCase();

        // Método que buscará personajes de ese tipo
        visualizarPersonajesPorTipo("Marvel.dat", tipoSolicitado);
        scanner.close();
    }

    public static void visualizarPersonajesPorTipo(String archivo, String tipoSolicitado) {
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            boolean tipoEncontrado = false;
            int contadorPersonajes = 0;

            // Contar los personajes del tipo solicitado
            long posicionInicial = raf.getFilePointer();
            while (raf.getFilePointer() < raf.length()) {
                // Leer el ID
                raf.readInt();

                // Leer el DNI
                raf.readUTF().trim();

                // Leer el nombre
                raf.readUTF().trim();

                // Leer la identidad
                raf.readUTF().trim();

                // Leer el tipo
                String tipo = raf.readUTF().trim();

                // Leer el peso y la altura
                raf.readInt(); // Peso
                raf.readInt(); // Altura

                // Si el tipo coincide con el solicitado
                if (tipo.equals(tipoSolicitado)) {
                    tipoEncontrado = true;
                    contadorPersonajes++;
                }
            }

            // Mostrar mensaje antes de listar los personajes
            if (!tipoEncontrado) {
                System.out.println("No existen " + tipoSolicitado + "s en el fichero.");
                return;
            } else {
                System.out.println("Se han encontrado " + contadorPersonajes + " " + tipoSolicitado + "s.");
            }

            // Listar los personajes
            raf.seek(posicionInicial); // Volver al inicio del archivo
            while (raf.getFilePointer() < raf.length()) {
                // Leer el ID
                raf.readInt();

                // Leer el DNI
                String dni = raf.readUTF().trim();

                // Leer el nombre
                String nombre = raf.readUTF().trim();

                // Leer la identidad secreta
                String identidad = raf.readUTF().trim();

                // Leer el tipo
                String tipo = raf.readUTF().trim();

                // Leer el peso
                int peso = raf.readInt();

                // Leer la altura
                int altura = raf.readInt();

                // Comparar el tipo del personaje con el tipo solicitado
                if (tipo.equals(tipoSolicitado)) {
                    // Mostrar los datos del personaje
                    System.out.println("Personaje [dni: " + dni +
                            ", nombre: " + nombre + ", identidad: " + identidad +
                            ", tipo: " + tipo + ", peso: " + peso + ", altura: " + altura + "]");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}