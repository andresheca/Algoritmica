package practica4;

/**
 * Clase que prueba las pilas
 * 
 * @author Adrian Herrera Arcila, Andres Heredia Canales y Asier Lopez Uriona
 *
 */

public class Prueba
{
	public static void main(String [] args){

		StackDynamic<Integer> p=new StackDynamic<Integer>();
		for(int i=1; i<=20; i++){ p.push(i);
		}
		System.out.println(p.toString());
		System.out.println (p.push(21));
		System.out.println (p.pop());
		System.out.println("Cima: "+p.getTop());
		System.out.println("Es Vacia: "+p.isEmpty());


		//Probamos que la clonación no copie punteros 
		StackDynamic<Integer> q=new StackDynamic<Integer>(p);
		System.out.println(q.toString());
		System.out.println(p.equals(q));
		System.out.println(p.pop().equals(q.pop()));

	}

}
