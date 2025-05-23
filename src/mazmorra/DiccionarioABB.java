package mazmorra;

public class DiccionarioABB {

	private NodoABB raiz;
	private int numElementos;

	public DiccionarioABB() {
		raiz = null;
		numElementos = 0;
	}

	public boolean vacio() {
		return raiz == null;
	}

	public int getNumElementos() {
		return numElementos;
	}

	public Sala getElemento(int clave) {
		return this.getElementoRec(raiz, clave);
	}

	private Sala getElementoRec(NodoABB nodo, int clave) {
		Sala resultado = null;
		if (nodo == null) {
			resultado = null;
		} else if (nodo.getClave() == clave) {
			resultado = nodo.getDato();
		} else if (nodo.getClave() > clave) {
			resultado = this.getElementoRec(nodo.getIzquierdo(), clave);
		} else {
			resultado = this.getElementoRec(nodo.getDerecho(), clave);
		}
		return resultado;
	}

	public boolean contiene(int clave) {
		return (getElemento(clave) != null);
	}


	public void insertar(int clave, Sala dato) {
		raiz = this.insertarRec(raiz, clave, dato);
	}

	private NodoABB insertarRec(NodoABB nodo, int clave, Sala dato){
		if (nodo == null) {     // Crear nuevo nodo
			nodo = new NodoABB(clave, dato);
			numElementos++;
		} else if (clave < nodo.getClave()) {    // Subárbol izquierdo
			NodoABB nuevoIzq = this.insertarRec(nodo.getIzquierdo(), clave, dato);
			nodo.setIzquierdo(nuevoIzq);
		} else if (clave > nodo.getClave()) {    // Subárbol derecho
			NodoABB nuevoDer = this.insertarRec(nodo.getDerecho(), clave, dato);
			nodo.setDerecho(nuevoDer);
		} else {      // Clave repetida
			System.out.println("Error. La clave " + clave + " ya existe");
		}
		return nodo;    // Devolver la nueva raíz del subárbol
	}


	public void borrar(int clave) {
		raiz = this.borrarRec(raiz, clave);
	}

	private NodoABB borrarRec(NodoABB nodo, int clave) {
		if (nodo == null) {
			System.out.println("la clave buscada no existe");
		} else if (nodo.getClave() > clave) {  // Subarbol izquierdo
			NodoABB nuevoIzq = this.borrarRec(nodo.getIzquierdo(), clave);
			nodo.setIzquierdo(nuevoIzq);
		} else if (nodo.getClave() < clave) {  // Subarbol derecho
			NodoABB nuevoDer = this.borrarRec(nodo.getDerecho(), clave);
			nodo.setDerecho(nuevoDer);
		} else {  // Borrar elemento en nodo
			if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
				nodo = null;  // Caso 1
			} else if (nodo.getDerecho() == null) {  // Caso 2
				nodo = nodo.getIzquierdo();
			} else if (nodo.getIzquierdo() == null) {  // Caso 2
				nodo = nodo.getDerecho();
			} else {    // Caso 3
				NodoABB nuevoIzq = this.cambiarPorMenor(nodo,
						nodo.getIzquierdo());
				nodo.setIzquierdo(nuevoIzq);
			}
			numElementos--;
		}
		return nodo;
	}

	private NodoABB cambiarPorMenor(NodoABB nodoBorrar, NodoABB nodoMenor) {
		if (nodoMenor.getDerecho() != null) {   // Subárbol derecho
			NodoABB nuevoDer = this.cambiarPorMenor(nodoBorrar, nodoMenor.getDerecho());
			nodoMenor.setDerecho(nuevoDer);
			return nodoMenor;
		} else {  // Encontrado nodo menor inmediato
			nodoBorrar.setClave(nodoMenor.getClave()); // Cambiar datos
			nodoBorrar.setDato(nodoMenor.getDato());
			return nodoMenor.getIzquierdo();
			// Devolver subarbol izquierdo de menor inmediato
		}
	}

	public String toString() {
		return this.toString(raiz, 1);
	}

	private String toString(NodoABB nodo, int nivel) {
		String resultado = "";
		if (nodo != null) {
			resultado = this.toString(nodo.getIzquierdo(), nivel + 1)
					+ nodo.getDato()
					+ " [Nivel " + nivel + "]\n"
					+ this.toString(nodo.getDerecho(), nivel+1);
		}
		return resultado;
	}

	public void mostrar2D() {
		mostrar2DRec(raiz, 0);
	}

	private void mostrar2DRec(NodoABB nodo, int espacio) {
		if (nodo == null) return;
		espacio += 10;
		mostrar2DRec(nodo.getDerecho(), espacio);
		System.out.println(" ".repeat(espacio) + nodo.getClave());
		mostrar2DRec(nodo.getIzquierdo(), espacio);
	}

	public DiccionarioABB[] partir(int clave) {
		DiccionarioABB menor = new DiccionarioABB();
		DiccionarioABB mayor = new DiccionarioABB();
		partirRec(raiz, clave, menor, mayor);
		return new DiccionarioABB[]{menor, mayor};
	}

	private void partirRec(NodoABB nodo, int clave, DiccionarioABB menor, DiccionarioABB mayor) {
		if (nodo == null) return;
		if (nodo.getClave() < clave) {
			menor.insertar(nodo.getClave(), nodo.getDato());
			partirRec(nodo.getDerecho(), clave, menor, mayor);
		} else {
			mayor.insertar(nodo.getClave(), nodo.getDato());
			partirRec(nodo.getIzquierdo(), clave, menor, mayor);
		}
	}
}
