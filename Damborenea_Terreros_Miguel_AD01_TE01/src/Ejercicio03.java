import java.io.*;

public class Ejercicio03 {

	public static void main(String[] args) {
        // Definir la ruta del archivo que queremos verificar
        String archivoPDF = "Damborenea_Terreros_Miguel_AD01_TE01.pdf";

        // Comprobar si el archivo es un PDF válido
        if (esPDFValido(archivoPDF)) {
            System.out.println("El archivo es un PDF válido.");
        } else {
            System.out.println("El archivo no es un PDF válido.");
        }
    }

    // Método para verificar si el archivo comienza con la cabecera de un PDF
    public static boolean esPDFValido(String rutaArchivo) {
        // La secuencia de bytes que representa "%PDF"
        byte[] cabeceraPDF = {37, 80, 68, 70};  // Bytes que corresponden a "%PDF"

        try (InputStream is = new FileInputStream(new File(rutaArchivo))) {
            // Leer los primeros 4 bytes del archivo
            byte[] cabeceraLeida = new byte[4];
            if (is.read(cabeceraLeida) != 4) {
                // Si no se pueden leer 4 bytes, el archivo es muy pequeño para ser un PDF
                return false;
            }

            // Comparar los bytes leídos con la cabecera del PDF
            for (int i = 0; i < cabeceraPDF.length; i++) {
                if (cabeceraLeida[i] != cabeceraPDF[i]) {
                    return false;  // Si algún byte no coincide, no es un PDF válido
                }
            }

            return true;  // Todos los bytes coinciden, es un PDF válido

        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Si hay una excepción (archivo no encontrado, etc.), no es válido
        }
    }
}
