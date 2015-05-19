package practica4;

import java.util.Vector;

/**
 * Robot que se utiliza en una central nuclear en caso de catastrofe
 * 
 * @author Adrian Herrera Arcila, Andres Heredia Canales y Asier Lopez Uriona
 * 
 */

public class Robot {

	/** Casilla inicial donde empieza y debe terminar nuestro robot. */
	Casilla c_ini, c_fin;

	/** Central por donde se debe de mover. */
	Central central;

	/** Vector de decisiones. */
	Vector<Casilla> vectorDecisiones;

	/** Vector solucion. */
	Vector<Casilla> vectorSolucion;

	/** Numero de pasos de la mejor solucion actual. */
	int kMejor = Integer.MAX_VALUE;

	/**
	 * Constructor de la clase Robot. Necesita una casilla inicial una casilla
	 * fin y la central por la que se va a ir moviendo.
	 */
	public Robot(Casilla ini, Casilla fin, Central c) {
		this.c_ini = ini;
		this.c_fin = fin;
		this.central = c;
	}

	public Robot(Central c) {
		this.central = c;
		this.c_ini = this.central.getInicial();
		this.c_fin = this.central.getFin();
	}

	/**
	 * Metodo que devuelve el vector solucion para la central pasada como
	 * argumento al constructor.
	 */
	public Vector<Casilla> getSolucion() {
		this.vectorDecisiones = new Vector<Casilla>();
		vectorDecisiones.add(this.c_ini);

		if (vueltaAtras(1)) {
			return vectorSolucion;
		}
		return null;
	}

	public Vector<Casilla> getMejorSolucion() {
		this.vectorDecisiones = new Vector<Casilla>();
		vectorDecisiones.add(this.c_ini);

		kMejor = Integer.MAX_VALUE;
		vectorSolucion = null;

		if (backMejor(1)) {
			return vectorSolucion;
		}
		return null;
	}

	private boolean backMejor(int k) {
		int i = 0;
		Casilla c = vectorDecisiones.lastElement();
		Casilla cTemp;
		while (i < 4) {
			if (i == 0) {
				// Izquierda
				cTemp = this.central.getCasilla(c.getX(), c.getY() - 1);
			} else if (i == 1) {
				// Arriba
				cTemp = this.central.getCasilla(c.getX() - 1, c.getY());
			} else if (i == 2) {
				// Derecha
				cTemp = this.central.getCasilla(c.getX(), c.getY() + 1);
			} else {
				// Abajo
				cTemp = this.central.getCasilla(c.getX() + 1, c.getY());
			}

			if (this.esFactible(cTemp)) {
				/**
				 * System.out.println("Casilla (" + cTemp.getX() + ", " +
				 * cTemp.getY() + ") es factible");
				 */
				vectorDecisiones.add(cTemp);
				if (cTemp.equals(c_fin)) {

					vectorSolucion = new Vector<Casilla>(vectorDecisiones);
					kMejor = k;
				} else {
					if (k + 1 < kMejor) {
						/** Seguimos explorando. */
						backMejor(k + 1);
					}
				}
				vectorDecisiones.remove(vectorDecisiones.lastElement());
			}
			i++;

		}

		return vectorSolucion != null;

	}

	/**
	 * Metodo de backtracking que busca una solucion para el camino del robot.
	 */
	private boolean vueltaAtras(int k) {
		boolean b = false;
		int i = 0;
		Casilla c = vectorDecisiones.lastElement();
		Casilla cTemp;
		while (i < 4 && !b) {

			if (i == 0) {
				// Izquierda
				cTemp = this.central.getCasilla(c.getX(), c.getY() - 1);
			} else if (i == 1) {
				// Arriba
				cTemp = this.central.getCasilla(c.getX() - 1, c.getY());
			} else if (i == 2) {
				// Derecha
				cTemp = this.central.getCasilla(c.getX(), c.getY() + 1);
			} else {
				// Abajo
				cTemp = this.central.getCasilla(c.getX() + 1, c.getY());
			}

			if (this.esFactible(cTemp)) {
				vectorDecisiones.add(cTemp);
				if (cTemp.equals(c_fin)) {
					vectorSolucion = new Vector<Casilla>(vectorDecisiones);
					b = true;
				} else {
					b = vueltaAtras(k + 1);
					vectorDecisiones.remove(vectorDecisiones.lastElement());
				}
				i++;
			}
			return b;
		}

		/**
		 * Metodo que indica si una casilla es buena candidata para el movimiento
		 * del robot hacia ella.
		 */
		private boolean esFactible(Casilla casilla) {
			// Si no esta dentro de los limites de la central.
			if (casilla == null) {
				return false;
			}

			// Si la casilla en cuestion esta ocupada, no podemos pasar por ella.
			if (this.central.hayObstaculo(casilla)) {
				return false;
			}

			// Si ya hemos explorado dicha casilla.
			if (this.vectorDecisiones.contains(casilla)) {
				return false;
			}

			// La casilla es factible
			return true;
		}
	}


