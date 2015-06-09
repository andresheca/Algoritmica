package practica4;

/**
 * Interfaz de una pila
 * @author Adrian Herrera Arcila, Andres Heredia Canales y Asier Lopez Uriona
 *
 * @param <T>
 */

public interface StackIF <T>
{
	/**
	 * Devuelve la cima de la pila.
	 * @return la cima de la pila.
	 */
	public T getTop ();
	/**
	 * Inserta un elemento a la pila.
	 * @param element El elemento a ser a?adido.
	 * @return la pila incluyendo el elemento.
	 */
	public StackIF<T> push (T element);
	/**
	 * Extrae de la pila el elemento en la cima.
	 * @return la pila excluyendo la cabeza.
	 */
	public StackIF<T> pop ();
	/**
	 * Devuelve cierto si la pila esta vacia.
	 * @return cierto si la pila esta vacia.
	 */
	public boolean isEmpty ();


	public int hashCode ();
	public String toString(); 

}