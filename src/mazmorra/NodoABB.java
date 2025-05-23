package mazmorra;

public class NodoABB {

	private int clave;
	private Sala dato;
	private NodoABB izquierdo, derecho;

	public NodoABB(int clave, Sala dato) {
		this.clave = clave;
		this.dato = dato;
		this.izquierdo = null;
		this.derecho = null;
	}

	public Sala getDato() { return dato; }

	public void setDato(Sala dato) { this.dato = dato; }

	public int getClave() {return clave; }

	public void setClave(int clave) { this.clave = clave; }

	public NodoABB getIzquierdo() { return izquierdo; }

	public void setIzquierdo(NodoABB izquierdo) { this.izquierdo = izquierdo; }

	public NodoABB getDerecho() { return derecho; }

	public void setDerecho(NodoABB derecho) { this.derecho = derecho;}

}