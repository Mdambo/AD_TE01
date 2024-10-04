import java.io.*;

public class Ejercicio01 {

    public static void main(String[] args) {
        // Definir las rutas del archivo de entrada y salida
        String archivoEntrada = "entrada.txt";
        String archivoSalida = "salida.txt";

        // Lectura del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {

            // Leer la primera línea del archivo
            String linea = br.readLine();

            if (linea != null) {
                // Invertir las palabras de la línea
                String lineaInvertida = invertirPalabras(linea);

                // Escribir la línea invertida en el archivo de salida
                bw.write(lineaInvertida);

                System.out.println("La línea ha sido invertida y escrita en " + archivoSalida);
            } else {
                System.out.println("El archivo de entrada está vacío.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para invertir cada palabra individualmente en una línea de texto
    public static String invertirPalabras(String linea) {
        String[] palabras = linea.split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            String palabraInvertida = new StringBuilder(palabra).reverse().toString();
            resultado.append(palabraInvertida).append(" ");
        }

        return resultado.toString().trim(); // Eliminar espacio final innecesario
    }
}