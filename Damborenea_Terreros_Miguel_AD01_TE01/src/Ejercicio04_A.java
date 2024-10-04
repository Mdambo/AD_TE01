import java.io.*;

public class Ejercicio04_A {

    public static void main(String[] args) {
        // Arrays con los datos de los personajes
        int[] ids = {1, 2, 3, 4, 5, 6, 7};
        String[] dnis = {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
        String[] noms = {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
        String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe", "James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
        String[] tipos = {"heroe", "villano", "heroe", "heroe", "villano", "heroe", "villano"};
        int[] pesos = {76, 84, 66, 136, 78, 102, 70};
        int[] alturas = {178, 183, 156, 152, 177, 182, 188};

        // Ruta del archivo
        String archivo = "Marvel.dat";

        // Llamar al método para guardar los personajes
        if (guardarPersonajes(archivo, ids, dnis, noms, identidades, tipos, pesos, alturas)) {
            System.out.println("La carga de los personajes ha terminado correctamente.");
        } else {
            System.out.println("Ha ocurrido un error durante la carga de los personajes.");
        }
    }

    public static boolean guardarPersonajes(String archivo, int[] ids, String[] dnis, String[] noms,
                                            String[] identidades, String[] tipos, int[] pesos, int[] alturas) {
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            // Recorrer todos los personajes y escribir en el archivo
            for (int i = 0; i < ids.length; i++) {
                // Escribir el id
                raf.writeInt(ids[i]);

                // Escribir el DNI
                raf.writeUTF(formatString(dnis[i], 9));

                // Escribir el nombre
                raf.writeUTF(formatString(noms[i], 10));

                // Escribir la identidad secreta
                raf.writeUTF(formatString(identidades[i], 20));

                // Escribir el tipo
                raf.writeUTF(formatString(tipos[i], 10));

                // Escribir el peso
                raf.writeInt(pesos[i]);

                // Escribir la altura
                raf.writeInt(alturas[i]);
            }
            return true; // Si se completó sin excepciones
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Si ocurre una excepción, devolver falso
        }
    }

    // Método para formatear cadenas y asegurarse de que tienen una longitud fija
    private static String formatString(String input, int length) {
        // Rellenar con espacios si es necesario
        StringBuilder sb = new StringBuilder(input);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
}