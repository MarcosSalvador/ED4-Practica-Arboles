import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utilidades {
    public static int[][] leerMazmorra(String rutaArchivo) {
        int[][] datosSalas;
        // Primero contamos el número de líneas para crear el array
        int numLineas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            while (br.readLine() != null) {
                numLineas++;
            }
        } catch (IOException e) {
            System.err.println("Error al contar líneas del archivo: " + e.getMessage());
            return null;
        }
        
        // Ahora leemos el archivo y llenamos el array
        datosSalas = new int[numLineas][3]; // Cada sala tiene 3 datos (hijoIzq, hijoDer, valor)
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int indiceSala = 0;
            while ((linea = br.readLine()) != null) {
                String[] datosStr = linea.trim().split("\\s+");
                for (int i = 0; i < datosStr.length; i++) {
                    datosSalas[indiceSala][i] = Integer.parseInt(datosStr[i]);
                }
                indiceSala++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }

        return datosSalas;
    }

}
