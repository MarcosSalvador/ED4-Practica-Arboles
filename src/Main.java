import java.io.File;

import mazmorra.Arbol;
import mazmorra.Sala;
import mazmorra.DiccionarioABB;

public class Main {
    public static void main(String[] args) {
        int[][] datos = Utilidades.leerMazmorra("mazmorra1.txt");

        Arbol mazmorra = new Arbol(datos);

        System.out.println("Ruta al mejor tesoro: " + mazmorra.rutaMejorTesoro());
        System.out.println("Ruta más fácil: " + mazmorra.rutaMasFacil());

        DiccionarioABB monstruos = mazmorra.getMonstruos();
        DiccionarioABB tesoros = mazmorra.getTesoros();

        System.out.println("Árbol de monstruos:");
        monstruos.mostrar2D();

        System.out.println("Árbol de tesoros:");
        tesoros.mostrar2D();

        DiccionarioABB[] partes = monstruos.partir(5);
        System.out.println("Parte menor:");
        partes[0].mostrar2D();
        System.out.println("Parte mayor:");
        partes[1].mostrar2D();
    }
}