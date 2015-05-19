package practica4;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que representa una central nuclear
 * 
 * @author Adrian Herrera Arcila, Andres Heredia Canales y Asier Lopez Uriona
 *
 */

public class Central {

	//largo de la central
	private int largo;
	//ancho de la central
	private int ancho;
	//casillas inicial y final
	private Casilla inicial, fin;

	public Casilla getInicial() {
		return inicial;
	}

	public Casilla getFin() {
		return fin;
	}

	/** Matriz que representa las casillas de la central. */
	private Casilla tablero[][];

	/**
	 * Constructor de la clase central que inicializa las variables y crea la
	 * matriz de casillas estableciendolas inicialmente todas como "Libres".
	 */
	public Central(int l, int a) {
		this.largo = l;
		this.ancho = a;
		this.tablero = new Casilla[largo][ancho];

		for (int i = 0; i < l; i++) {
			for (int j = 0; j < a; j++) {
				this.tablero[i][j] = new Casilla(i, j, 'L');
			}
		}
	}

	public Central(String txtFile) {
		try {
			leerCentral(txtFile);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void leerCentral(String fichero) throws NumberFormatException,
	IOException {
		inicial = new Casilla(0, 0, 'L');

		FileInputStream fstream = new FileInputStream(fichero);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		// Cogemos el tamaa�o de la central.
		String strLine = br.readLine();
		String[] str = strLine.split(" ");
		// Primera linea del fichero
		this.fin = new Casilla(Integer.parseInt(str[0]) - 1,
				Integer.parseInt(str[1]) - 1, 'L');

		// Segunda linea y en adelante
		if (strLine != null) {
			strLine = br.readLine();

			str = strLine.split(" ");

			this.ancho = str.length;

			this.largo = 1;
			while ((strLine = br.readLine()) != null) {
				this.largo++;
			}
		}
		in.close();
		System.out.println("Largo tomado: " + this.largo + "\nAncho tomado: "
				+ this.ancho + "\nObjetivo : " + this.fin.getX() + ", "
				+ this.fin.getY());

		// Inicializo el tablero
		this.tablero = new Casilla[this.largo][this.ancho];
		// Leo de nuevo
		fstream = new FileInputStream(fichero);
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));

		// Cogemos el tama√±ao de la central.
		strLine = br.readLine();

		if (strLine != null) {
			str = strLine.split(" ");

			int i = 0, j;
			char contenido;
			while ((strLine = br.readLine()) != null) {
				str = strLine.split(" ");

				for (j = 0; j < this.ancho; j++) {
					if (str[j].compareTo("0") == 0) {
						contenido = 'O';
					} else {
						contenido = 'L';
					}
					this.tablero[i][j] = new Casilla(i, j, contenido);
				}
				i++;
			}
		}
		in.close();
	}

	/**
	 * Metodo que devuelve si existe en la central una casilla basandose en las
	 * coordenadas pasadas como argumento.
	 */
	public Casilla getCasilla(int x, int y) {
		if (x < 0 || y < 0 || x >= this.largo || y >= this.ancho) {
			return null;
		}
		return this.tablero[x][y];
	}

	/**
	 * Metodo que indica si hay obstaculo en una casilla
	 */
	public boolean hayObstaculo(Casilla c) {
		return tablero[c.getX()][c.getY()].getContenido() == 'O';
	}

	/**
	 * Metodo para poner una casilla de la central con un obstaculo y ocupada.
	 */
	public void setObstaculo(int x, int y) {
		this.tablero[x][y].setContenido('O');
	}

	/**
	 * Metodo para pintar las casillas de la central.
	 */
	public void pintaCentral() {
		for (int i = 0; i < this.largo; i++) {
			// Imprimimos linea horizontal de separacion.
			for (int j = 0; j < this.ancho; j++) {
				System.out.print("----");
			}
			System.out.print("-\n");

			for (int j = 0; j < this.ancho; j++) {
				System.out.print("| " + tablero[i][j].getContenido() + " ");
			}
			System.out.print("|\n");
		}

		// Imprimimos ultima linea horizontal.
		for (int j = 0; j < this.ancho; j++) {
			System.out.print("----");
		}
		System.out.print("\n");
	}
}

