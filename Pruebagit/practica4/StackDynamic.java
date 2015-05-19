package practica4;

/**
 * Implementacion de la pila
 * @author Adrian Herrera Arcila, Andres Heredia Canales y Asier Lopez Uriona
 *
 * @param <T>
 */

public class StackDynamic <T>  implements StackIF<T>
{
	private T element;
	private StackDynamic<T> next;
	public StackDynamic ()
	{
		element = null;
		next = null;
	}
	public StackDynamic (StackIF<T> stack)
	{
		this ();
		if (stack != null)
			if (!stack.isEmpty ())
			{
				element = stack.getTop ();
				next = new StackDynamic<T> (stack.pop ());
				stack.push (element);
			}
	}

	@Override
	public T getTop () 
	{
		return element;
	}
	@Override
	public StackIF<T> push (T element)
	{
		if (element != null) {
			StackDynamic<T> stack = new StackDynamic<T> ();
			stack.element = this.element;
			stack.next = this.next;
			this.element = element;
			this.next = stack;
		} 
		return this;
	}
	@Override
	public StackIF<T> pop ()
	{
		if (!isEmpty ()) {
			element = next.element;
			next = next.next;
		}
		return this;
	}

	@Override
	public boolean isEmpty ()
	{
		return element == null &&
				next == null;
	}


	@Override
	public int hashCode ()
	{
		return 31 * ((element == null) ? 0 : element.hashCode ()) +
				((next == null) ? 0 : next.hashCode ());
	}
	@SuppressWarnings("unchecked")

	/*@Override
	public boolean equals (Object o) {
//TODO
	}*/

	@Override
	public String toString () {
		StringBuffer buf = new StringBuffer();
		StackIF<T> stack = new StackDynamic<T>(this);
		buf.append("Pila: [");
		while (!stack.isEmpty()) {
			T element=stack.getTop();
			stack.pop();
			buf.append(element.toString());
			if (!stack.isEmpty()){
				buf.append(", ");
			}
		}
		buf.append("]");
		return buf.toString();
	}

}
