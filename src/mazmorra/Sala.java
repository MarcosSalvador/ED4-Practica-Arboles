package mazmorra;

/**
 * Clase que representa una sala en la mazmorra
 */
public class Sala {

    private int id;
    private int valor;  // -1 si está vacía, número positivo para monstruo o tesoro

    /**
     * Constructor por defecto
     */
    public Sala() {
        this.id = -1;
        this.valor = -1;
    }

    /**
     * Constructor con id de sala
     * @param idSala Identificador de la sala
     */
    public Sala(int idSala) {
        this.id = idSala;
        this.valor = -1;
    }

    /**
     * Constructor completo
     * @param idSala Identificador de la sala
     * @param valor Valor asociado al contenido (-1 si vacío, valor positivo para monstruo o tesoro)
     */
    public Sala(int idSala, int valor) {
        this.id = idSala;
        this.valor = valor;
    }

    /**
     * Obtiene el identificador de la sala
     * @return Identificador de la sala
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la sala
     * @param id Identificador de la sala
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el valor del contenido de la sala
     * @return Valor del contenido (-1 si vacío, valor positivo para monstruo o tesoro)
     */
    public int getValor() {
        return valor;
    }

    /**
     * Establece el valor del contenido de la sala
     * @param valor Valor del contenido
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Determina si la sala está vacía
     * @return true si la sala está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return valor == -1;
    }

    /**
     * Determina si la sala contiene un monstruo o un tesoro
     * @return true si la sala contiene un monstruo o un tesoro, false en caso contrario
     */
    public boolean tieneContenido() {
        return valor != -1;
    }

    @Override
    public String toString() {
        if (valor == -1) {
            return "Sala " + id + ": VACÍA";
        } else {
            return "Sala " + id + ": Valor(" + valor + ")";
        }
    }
}