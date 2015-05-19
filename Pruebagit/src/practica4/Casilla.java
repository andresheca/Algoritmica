package practica4;

/**
 * Casillas en las que se divide la central nuclear.
 * @author Adrian Herrera Arcila, Andres Heredia Canales y Asier Lopez Uriona.
 */
public class Casilla
{
	//Coordenadas:
	private int x, y;

	//Contenido: libre u ocupada (L/O).
	private char contenido;

	/**
	 * Constructor de la clase casilla. Necesita obligatoriamente las
	 * coordenadas que representa asi como su contenido.
	 */
	public Casilla(int x, int y, char p)
	{
		this.x = x;
		this.y = y;
		this.contenido = p;
	}

	/**
	 * Devuelve la coordenada X de la casilla.
	 */
	public int getX()
	{
		return this.x;
	}

	/**
	 * Devuelve la coordenada Y de la casilla.
	 */
	public int getY()
	{
		return this.y;
	}

	/**
	 * Devuelve el contenido de la casilla.
	 */
	public char getContenido()
	{
		return this.contenido;
	}

	/**
	 * Establece un nuevo contenido para la casilla.
	 */
	public void setContenido(char p)
	{
		this.contenido = p;
	}

	/**
	 * Metodo para comparar dos casillas.
	 */
	@Override
	public boolean equals(Object obj)
	{
		Casilla c = (Casilla) obj;
		return this.x == c.getX() && this.y == c.getY();
	}
}
