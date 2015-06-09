package practica4;

import java.util.Vector;
import java.util.PriorityQueue;

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

	/** Metodo que devuelve una solucion basada en ramificacion y poda. */
	public Vector<Casilla> getSolucionRYP()
	{
		this.vectorDecisiones = new Vector<Casilla>();
		vectorDecisiones.add(this.c_ini);
		if(ramYPoda(1))
		{
			return vectorSolucion;
		}
		return null;
	}

	public Vector<Casilla> getSolucionIterativo() {
		this.vectorDecisiones = new Vector<Casilla>();
		vectorDecisiones.add(this.c_ini);

		if (BTIterativo(1)) {
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
	/**
	 * Metodo que devuelve el mejor camino encontrado (si le hay).
	 * @param int representa el numero de decisiones tomadas (numero de casillas avanzadas)
	 * @return true si encuentra solucion
	 */
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
	 * 
	 * @param int k entero que representa el numero de decisiones tomadas
	 * @return boolean true si ha encontrado la solucion
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

			}
			i++;
		}
		return b;
	}

	/**
	 * Version iterativa del metodo de backtracking
	 * 
	 * @param int k representa el numero de decisiones tomadas.
	 * @return boolean true si hay solucion
	 */
	private boolean BTIterativo(int k) {
		boolean encontrado = false;
		// Preparar recorrido nivel k
		int i = 0;
		// Pila donde vamos a ir guardando los pasos que vamos dando
		StackIF<Casilla> pila = new StackDynamic<Casilla>();
		//Casilla de inicio
		Casilla c = vectorDecisiones.lastElement();
		//Lo metemos a la pila
		pila.push(c);
		Casilla cTemp;
		boolean factible = false;
		// Si la pila esta vacia significa que no hay solucion al problema
		while (!pila.isEmpty() && !encontrado) {
			c = pila.getTop();
			//Variables de control
			i = 0;
			factible = false;
			while (i < 4 && !factible) {

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
				//Si es factible aniadimos el movimiento, si no, volvemos a la casilla anterior.
				if (this.esFactible(cTemp)) {
					k++;
					vectorDecisiones.add(cTemp);
					pila.push(cTemp);
					//Si hemos llegado al final, salimos, si no, salimos del bucle (ya hemos encontrado una casilla factible).
					if (cTemp.equals(c_fin)) {
						vectorSolucion = new Vector<Casilla>(vectorDecisiones);
						encontrado = true;
					} else {
						factible = true;
					}
				} else {
					pila.pop();
				}
				i++;

			}

		}
		return encontrado;
	}
	
	/** Algoritmo basado en ramificacion y poda; se diferencia de backtracking en que se basa en una cota asignada a cada casilla en runtime para elegir el camino a seguir. */
	private boolean ramYPoda(int k) {
		PriorityQueue<Casilla> adyacentes = new PriorityQueue<Casilla>();
		Casilla c = vectorDecisiones.lastElement();
		Casilla temp;
		// Generamos los adyacentes y los metemos en la cola; esta cola los ordena por su cota, de menor a mayor:
		temp = central.getCasilla(c.getX() + 1, c.getY());
		if (temp != null) {
			cota(temp);
			adyacentes.offer(temp);
		}
		temp = central.getCasilla(c.getX() - 1, c.getY());
		if (temp != null) {
			cota(temp);
			adyacentes.offer(temp);
		}
		temp = central.getCasilla(c.getX(), c.getY() + 1);
		if (temp != null) {
			cota(temp);
			adyacentes.offer(temp);
		}
		temp = central.getCasilla(c.getX(), c.getY() - 1);
		if (temp != null) {
			cota(temp);
			adyacentes.offer(temp);
		}
		//Mientras existan nodos en la cola, evaluamos:
		while (!adyacentes.isEmpty()) {
			temp = adyacentes.poll();
			//Si es factible y el numero de movimientos es menor que el mejor:
			if (esFactible(temp) && k < kMejor) {
				//Si es la casilla final almacenamos la solucion:
				if (temp.equals(c_fin)) {
					vectorSolucion = new Vector<Casilla>(vectorDecisiones);
					kMejor = k;
				//Si no bajamos un nivel en el arbol de busqueda:
				} else {
					ramYPoda(k + 1);
				}
			}
		}
		//Finalizamos retornando la solucion obtenida:
		return (vectorSolucion != null);
	}

	/** Metodo que calcula la cota sobre la que se basara ramificacion y poda; esta basada en la distancia a la casilla final y el numero de obstaculos alrededor de la casilla. */
	private void cota(Casilla cas) {
		int cota = 0;
		int Xactual = cas.getX();
		int Yactual = cas.getY();
		cota += Math.abs((c_fin.getX() - Xactual) + (c_fin.getY() - Yactual));
		// Derecha:
		if (central.getCasilla(Xactual + 1, Yactual) != null
				&& central.getCasilla(Xactual + 1, Yactual).getContenido() == 'O')
			cota += 1;
		// Izquierda:
		if (central.getCasilla(Xactual - 1, Yactual) != null
				&& central.getCasilla(Xactual - 1, Yactual).getContenido() == 'O')
			cota += 1;
		// Arriba:
		if (central.getCasilla(Xactual, Yactual + 1) != null
				&& central.getCasilla(Xactual, Yactual + 1).getContenido() == 'O')
			cota += 1;
		// Abajo:
		if (central.getCasilla(Xactual, Yactual - 1) != null
				&& central.getCasilla(Xactual, Yactual - 1).getContenido() == 'O')
			cota += 1;
		cas.setCota(cota);
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
