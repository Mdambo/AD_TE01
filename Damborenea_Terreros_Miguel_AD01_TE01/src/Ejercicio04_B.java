import java.io.*;
import java.util.*;

public class Ejercicio04_B {

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Solicitar el DNI del personaje
	        System.out.println("Introduzca el DNI (con letra) del personaje para control de peso: ");
	        String dni = scanner.nextLine().trim();  // DNI introducido por el usuario

	        String archivo = "Marvel.dat";  // Archivo donde están los personajes

	        // Verificar si el personaje existe en el archivo
	        if (verificarExistenciaPersonaje(archivo, dni) == null) {
	            System.out.println("No se encontró un personaje con el DNI proporcionado.");
	            scanner.close();
	            return;  // Terminar la ejecución si el personaje no existe
	        }

	        // Solicitar el nuevo peso del personaje
	        System.out.println("Introduzca su peso actual: ");
	        int nuevoPeso = scanner.nextInt();  // Peso introducido por el usuario

	        modificarPesoPersonaje(archivo, dni, nuevoPeso);

	        scanner.close();  // Cerrar el scanner
	    }

	    // Método que verifica si un personaje existe y devuelve su nombre
	    public static Boolean verificarExistenciaPersonaje(String archivo, String dni) {
	        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
	            String dniPersonaje;

	            // Recorrer el archivo buscando el personaje con el DNI
	            while (raf.getFilePointer() < raf.length()) {
	                // Leer el id (ignorar)
	                raf.readInt();

	                // Leer el DNI
	                dniPersonaje = raf.readUTF();

	                // Leer el resto de los datos (ignorarlos)
	                raf.readUTF(); // nombre
	                raf.readUTF(); // identidad secreta
	                raf.readUTF(); // tipo
	                raf.readInt(); // peso
	                raf.readInt(); // altura

	                // Verificar si el DNI coincide
	                if (dniPersonaje.trim().equals(dni)) {
	                    return true;  // Devuelve el nombre si coincide el DNI
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;  // Si no se encuentra el personaje, devuelve null
	    }

	    // Método que modifica el peso de un personaje
	    public static boolean modificarPesoPersonaje(String archivo, String dni, int nuevoPeso) {
	        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
	            int pesoActual;
	            String dniPersonaje;
	            String nombre;

	            // Recorrer el archivo buscando el personaje con el DNI
	            while (raf.getFilePointer() < raf.length()) {
	                // Leer el id
	                raf.readInt();

	                // Leer el DNI
	                dniPersonaje = raf.readUTF();

	                // Leer el nombre
	                nombre = raf.readUTF();

	                // Leer la identidad
	                raf.readUTF();

	                // Leer el tipo
	                raf.readUTF();

	                // Leer el peso actual
	                pesoActual = raf.readInt();

	                // Leer la altura
	                raf.readInt();

	                // Verificar si el DNI coincide
	                if (dniPersonaje.trim().equals(dni)) {
	                    // Mostrar el cambio de peso
	                    int diferenciaPeso = nuevoPeso - pesoActual;
	                    if (diferenciaPeso > 0) {
	                        System.out.println(nombre.trim() + " ha engordado " + diferenciaPeso + " kilos.");
	                    } else if (diferenciaPeso < 0) {
	                        System.out.println(nombre.trim() + " ha adelgazado " + Math.abs(diferenciaPeso) + " kilos.");
	                    } else {
	                        System.out.println(nombre.trim() + " se mantiene en su peso anterior.");
	                    }

	                    // Posicionar el puntero justo donde se guarda el peso y actualizarlo
	                    raf.seek(raf.getFilePointer() - 8);  // Ir al inicio del campo "peso"
	                    raf.writeInt(nuevoPeso);  // Actualizar el peso

	                    return true;  // Modificación exitosa
	                }
	            }

	            return false;  // Si no se encuentra el personaje, devolver falso
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;  // Ocurrió un error
	        }
	    }
	}

