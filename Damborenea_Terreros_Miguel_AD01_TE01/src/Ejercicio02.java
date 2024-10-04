import java.io.*;

public class Ejercicio02 {

    public static void main(String[] args) {
        // Definir las rutas del archivo de entrada y salida
        String archivoEntrada = "nombres.txt";  // Archivo con nombres y apellidos
        String archivoSalida = "salidaNombres.txt";  // Archivo con nombres de 5 letras

        // Lectura y escritura de archivos dentro del try-with-resources
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {

            String linea;

            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Separar la línea en palabras (asumiendo que la primera palabra es el nombre)
                String[] palabras = linea.split(" ");

                // Si hay al menos un nombre (primera palabra) en la línea
                if (palabras.length > 0) {
                    String nombre = palabras[0]; // La primera palabra es el nombre

                    // Si el nombre tiene exactamente 5 letras, escribirlo en el archivo de salida
                    if (nombre.length() == 5) {
                        bw.write(nombre);
                        bw.newLine();  // Escribir cada nombre en una nueva línea
                    }
                }
            }

            System.out.println("Se han escrito los nombres con 5 letras en " + archivoSalida);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}