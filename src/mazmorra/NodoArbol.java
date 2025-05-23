package mazmorra;

class NodoArbol {

    private Sala dato;
    private NodoArbol izquierdo, derecho;

    public NodoArbol(Sala dato) {
        this.dato = dato;
        this.izquierdo = this.derecho = null;
    }

    public NodoArbol(Sala dato, NodoArbol izquierdo, NodoArbol derecho) {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public Sala getDato() {
        return dato;
    }

    public void setDato(Sala dato) {
        this.dato = dato;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }

}
