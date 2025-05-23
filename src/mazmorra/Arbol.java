package mazmorra;

import java.util.ArrayDeque;

public class Arbol {
    private NodoArbol raiz;

    public Arbol() {
        raiz = null;
    }

    public Arbol(Sala dato) {
        raiz = new NodoArbol(dato);
    }

    public Arbol(Sala dato, Arbol izquierdo, Arbol derecho) {
        NodoArbol nodoIzq = null;
        NodoArbol nodoDer = null;
        if (izquierdo != null) {
            nodoIzq = izquierdo.raiz;
        }
        if (derecho != null) {
            nodoDer = derecho.raiz;
        }
        raiz = new NodoArbol(dato, nodoIzq, nodoDer);
    }

    /**
     * Recorrido en preorden
     */
    public void preOrden() {
        System.out.print("Preorden: ");
        this.preOrdenRec(raiz);
        System.out.println();
    }

    private void preOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            System.out.println(nodo.getDato());
            this.preOrdenRec(nodo.getIzquierdo());
            this.preOrdenRec(nodo.getDerecho());
        }
    }

    /**
     * Recorrido en preorden indicando además el nivel de cada nodo en el árbol
     */
    public void preOrdenNivel() {
        System.out.println("Preorden con niveles: ");
        preOrdenNivelRec(raiz, 1);
    }

    private void preOrdenNivelRec(NodoArbol nodo, int nivel) {
        if (nodo != null) {
            System.out.println(nodo.getDato() + " en el nivel " + nivel);
            preOrdenNivelRec(nodo.getIzquierdo(), nivel + 1);
            preOrdenNivelRec(nodo.getDerecho(), nivel + 1);
        }
    }

    /**
     * Recorrido en orden central
     */
    public void ordenCentral() {
        System.out.print("Orden Central: ");
        this.ordenCentralRec(raiz);
        System.out.println();
    }

    private void ordenCentralRec(NodoArbol nodo) {
        if (nodo != null) {
            this.ordenCentralRec(nodo.getIzquierdo());
            System.out.println(nodo.getDato());
            this.ordenCentralRec(nodo.getDerecho());
        }
    }

    /**
     * Recorrido en postorden
     */
    public void postOrden() {
        System.out.print("Postorden: ");
        this.postOrdenRec(raiz);
        System.out.println();
    }

    private void postOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            this.postOrdenRec(nodo.getIzquierdo());
            this.postOrdenRec(nodo.getDerecho());
            System.out.println(nodo.getDato());
        }
    }

    /**
     * Recorrido en amplitud con una cola de nodos del árbol
     */
    public void amplitud() {
        ArrayDeque<NodoArbol> cola = new ArrayDeque<>();
        System.out.print("Amplitud: ");
        if (raiz != null) {
            cola.add(raiz);
            while (!cola.isEmpty()) {
                NodoArbol nodo = cola.remove();
                System.out.println(nodo.getDato());
                if (nodo.getIzquierdo() != null) {
                    cola.add(nodo.getIzquierdo());
                }
                if (nodo.getDerecho() != null) {
                    cola.add(nodo.getDerecho());
                }
            }
        }
        System.out.println();
    }

    public Arbol(int[][] datosSalas) {
        if (datosSalas.length == 0) return;
        NodoArbol[] nodos = new NodoArbol[datosSalas.length];

        for (int i = 0; i < datosSalas.length; i++) {
            nodos[i] = new NodoArbol(new Sala(i, datosSalas[i][2]));
        }
        for (int i = 0; i < datosSalas.length; i++) {
            int izq = datosSalas[i][0];
            int der = datosSalas[i][1];
            if (izq >= 0 && izq < datosSalas.length)
                nodos[i].setIzquierdo(nodos[izq]);
            if (der >= 0 && der < datosSalas.length)
                nodos[i].setDerecho(nodos[der]);
        }

        raiz = nodos[0];
    }

    public String rutaMejorTesoro() {
        return rutaMejorTesoroRec(raiz, "", 0).ruta;
    }

    private class ResultadoRuta {
        String ruta;
        int valor;

        ResultadoRuta(String ruta, int valor) {
            this.ruta = ruta;
            this.valor = valor;
        }
    }

    private ResultadoRuta rutaMejorTesoroRec(NodoArbol nodo, String rutaParcial, int direccion) {
        if (nodo == null) return new ResultadoRuta("", -1);

        String nuevoRuta = rutaParcial;
        if (!rutaParcial.isEmpty()) {
            if (direccion == -1) nuevoRuta += " / ";
            else if (direccion == 1) nuevoRuta += " \\ ";
            else nuevoRuta += " | ";
        }
        nuevoRuta += nodo.getDato().getId();

        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null && nodo.getDato().getValor() > 0) {
            return new ResultadoRuta("-> " + nuevoRuta + " (" + nodo.getDato().getValor() + ") X", nodo.getDato().getValor());
        }

        ResultadoRuta izq = rutaMejorTesoroRec(nodo.getIzquierdo(), nuevoRuta, -1);
        ResultadoRuta der = rutaMejorTesoroRec(nodo.getDerecho(), nuevoRuta, 1);

        return (izq.valor >= der.valor) ? izq : der;
    }

    public String rutaMasFacil() {
        ResultadoFacil resultado = new ResultadoFacil("", Integer.MAX_VALUE);
        rutaMasFacilRec(raiz, "", 0, 0, resultado, 0);
        return resultado.ruta;
    }

    private class ResultadoFacil {
        String ruta;
        int minSuma;

        ResultadoFacil(String ruta, int minSuma) {
            this.ruta = ruta;
            this.minSuma = minSuma;
        }
    }

    private void rutaMasFacilRec(NodoArbol nodo, String ruta, int suma, int direccion, ResultadoFacil resultado, int profundidad) {
        if (nodo == null) return;

        String simbolo = "";
        if (!ruta.isEmpty()) {
            if (direccion == -1) simbolo = " / ";
            else if (direccion == 1) simbolo = " \\ ";
            else simbolo = " | ";
        }

        String parte = simbolo + nodo.getDato().getId();
        if (nodo.getDato().getValor() > 0) {
            parte += "[" + nodo.getDato().getValor() + "]";
            suma += nodo.getDato().getValor();
        }

        String nuevaRuta = ruta + parte;

        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null && nodo.getDato().getValor() > 0) {
            if (suma < resultado.minSuma) {
                resultado.minSuma = suma;
                resultado.ruta = "->" + nuevaRuta + " (" + nodo.getDato().getValor() + ") X";
            }
        }

        rutaMasFacilRec(nodo.getIzquierdo(), nuevaRuta, suma, -1, resultado, profundidad + 1);
        rutaMasFacilRec(nodo.getDerecho(), nuevaRuta, suma, 1, resultado, profundidad + 1);
    }


    public DiccionarioABB getMonstruos() {
        DiccionarioABB diccionario = new DiccionarioABB();
        almacenarEnDiccionario(raiz, diccionario, true);
        return diccionario;
    }

    public DiccionarioABB getTesoros() {
        DiccionarioABB diccionario = new DiccionarioABB();
        almacenarEnDiccionario(raiz, diccionario, false);
        return diccionario;
    }

    private void almacenarEnDiccionario(NodoArbol nodo, DiccionarioABB dic, boolean esMonstruo) {
        if (nodo == null) return;
        if ((esMonstruo && nodo.getDato().getValor() > 0) || (!esMonstruo && nodo.getDato().getValor() < 0)) {
            dic.insertar(nodo.getDato().getId(), nodo.getDato());
        }
        almacenarEnDiccionario(nodo.getIzquierdo(), dic, esMonstruo);
        almacenarEnDiccionario(nodo.getDerecho(), dic, esMonstruo);
    }

}
