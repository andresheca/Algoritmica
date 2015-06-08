package practica4;

/**
 * Casillas en las que se divide la central nuclear.
 * @author Adrian Herrera Arcila, Andres Heredia Canales y Asier Lopez Uriona.
 */
public class Casilla implements Comparable<Casilla>
{
	//Coordenadas:
	private int x, y;

	//Cota de la casilla:
	int cota;

	//Contenido: libre u ocupada (L/O).
	private char contenido;

	/** Constructor de la clase casilla; necesita obligatoriamente las coordenadas que representa asi como su contenido. */
	public Casilla(int x, int y, char p)
	{
		this.x = x;
		this.y = y;
		this.contenido = p;
	}

	/** Devuelve la coordenada X de la casilla. */
	public int getX()
	{
		return this.x;
	}

	/** Devuelve la coordenada Y de la casilla. */
	public int getY()
	{
		return this.y;
	}

	/** Devuelve el contenido de la casilla. */
	public char getContenido()
	{
		return this.contenido;
	}

	/** Establece un nuevo contenido para la casilla. */
	public void setContenido(char p)
	{
		this.contenido = p;
	}

	/** Metodo para establecer la cota de la casilla en un contexto en el que pertenezca a una central. */
	public void setCota(int cota)
	{
		this.cota=cota;
	}

	/** Metodo para obtener la cota. */
	public int getCota()
	{
		return this.cota;
	}

	@Override	
	/** Metodo para comparar casillas respecto a su cota. */
	public int compareTo(Casilla cas)
	{
		return (this.cota-cas.getCota());
	}

	@Override
	/** Metodo para comparar dos casillas. */
	public boolean equals(Object obj)
	{
		Casilla c = (Casilla) obj;
		return this.x == c.getX() && this.y == c.getY();
	}
}
