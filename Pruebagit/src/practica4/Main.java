package practica4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * Clase main que simula la actuacion del robot en la central.
 * @author Adrian Herrera Arcila, Andres Heredia Canales y Asier Lopez Uriona.
 */
public class Main
{
	public static void main(String[] args)
	{
		Central c = new Central("central.txt");
		c.pintaCentral();

		Robot r = new Robot(c);

		Vector<Casilla> sol = r.getSolucion();
		Vector<Casilla> mejorSol = r.getMejorSolucion();

		try {
			System.out.println("Solucion: ");
			pintaSolucion(sol);

			System.out.println("Mejor solucion: ");
			pintaSolucion(mejorSol);

			guardaSolucion("UnaSolucion.txt", sol);
			guardaSolucion("MejorSolucion.txt", mejorSol);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException n) {
			System.out.println("\nNo se han guardado las soluciones");
		}

	}

	private static void guardaSolucion(String nombreArchivo, Vector<Casilla> v)
			throws IOException {
		String str;
		FileWriter fstream;
		BufferedWriter out;

		fstream = new FileWriter(nombreArchivo);
		out = new BufferedWriter(fstream);

		out.append("{\n");
		for (Casilla c : v) {

			str = "(" + c.getX() + ", " + c.getY() + ")\n";
			out.append(str);

		}
		out.append("}");
		out.close();
	}

	private static void pintaSolucion(Vector<Casilla> v) {
		if (v != null) {
			System.out.println("{");
			for (Casilla cas : v) {
				System.out.println("(" + cas.getX() + ", " + cas.getY() + ")");
			}
			System.out.println("}");
		} else {
			System.out.println("No tiene solucion.");
		}
	}
}
