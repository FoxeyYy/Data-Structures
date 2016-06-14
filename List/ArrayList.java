import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.omg.CORBA.FREE_MEM;

/**
 * @author Hector Del Campo
 *
 */
public class ArrayList<E> implements Collection<E>, Iterable<E>, List<E>, Serializable{
	
	private static final long serialVersionUID = 1L;
	private E[] array;
	int numberElements;
	
	/**
	 * Creates a new ArrayList instance with default size 10
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(){
		array = (E[])new Object[10];
		numberElements=0;
	}

	/**
	 * Appends the specified element to the end of this list
	 * @return true, as specified by {@link java.util.Collection#add}
	 */
	@Override
	public boolean add(E element) {
		if (numberElements==array.length)
			array = Arrays.copyOf(array,array.length*2);
		array[numberElements++]=element;
		return true;
	}

	/**
	 * Inserts the specified element at the specified index, shifting all elements from the specified index on this list to the right(increases their indices).
	 * @param index - index at which the specified element is to be inserted
	 * @param element - element to be inserted
	 * @throws IndexOutOfBoundsException - if the index is out of range (index {@literal <} 0 || index {@literal >} size()) 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException{
		
		if (index < 0 || index >= numberElements) throw new IndexOutOfBoundsException();
		
		E[] aux = Arrays.copyOf(array, numberElements);
		array = (E[])new Object[array.length];
		numberElements=0;
		for(int i = 0;i<index;i++)
			add(aux[i]);
		add(element);
		for(int i=index+1;i<=aux.length;i++)
			add(aux[index++]);
		
	}

	/**
	 * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator. 
	 * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
	 * @param collection containing elements to be added to this list
	 * @return true if this list changed as a result of the call
	 * @throws NullPointerException if the specified collection is null
	 */
	@Override
	public boolean addAll(Collection<? extends E> collection) throws NullPointerException{
		if (collection==null) throw new NullPointerException();
		for(E e : collection){
			add(e);
		}
		return true;
	}

	/**
	 * Inserts all of the elements of the specified collection into this list at the specified position. Shifts all elements from the specified index on this list to the right(increases their indices). 
	 * The new elements will appear in this list in the order that they are returned by the specified collection's operator. The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
	 * <br>
	 * <br>
	 * Note that this operation is not efficient, many implementations will override this method.
	 * @param index - index at which to insert the first element from the specified collection
	 * @param collection - collection containing elements to be added to this list
	 * @return true if this list changed as result of the call
	 * @throws IndexOutOfBoundsException - if the index is out of range (index {@literal <} 0 || index {@literal >} size()) 
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> collection) throws IndexOutOfBoundsException {
		if (index < 0 || index >= numberElements) throw new IndexOutOfBoundsException();

		for(E obj : collection)
			add(index++,obj);
		return true;
	}

	/**
	 * Removes all of the elements of this list, the list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		for(int i=0;i<numberElements;i++)
			array[i]=null;
		numberElements=0;
	}

	/**
	 * Returns true if this list contains the specified element.
	 * @param element - element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 * 
	 */
	@Override
	public boolean contains(Object element) {
		for(E obj : array) if (element.equals(obj)) return true;
		return false;
	}

	/**
	 * Returns true if all of the elements of the specified collection are contained in this list
	 * @param collection - collection whose element's presence in this list is to be tested
	 * @return true if this list contains all elements given in the collection
	 */
	@Override
	public boolean containsAll(Collection<?> collection) {
		int j;
		for(Object obj : collection){
			for(j=0;j<numberElements;j++)
				if(obj.equals(array[j])) break;
			if(!obj.equals(array[j])) return false;
		}return true;
	}

	/**
	 * Returns the element at the specified position in this list.
	 * @param index - index of the element to return
	 * @throws IndexOutOfBoundsException - if the index is out of range
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= numberElements) throw new IndexOutOfBoundsException();
		return array[index];
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or {@literal -}1 if this list does not contain the element.
	 * @param element - element to search for
	 * @return the index of the last occurrence of the specified element in this list, or {@literal -}1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(Object element) {
		for(int i=0;i<numberElements;i++)
			if(element.equals(array[i])) return i;
		return -1;
	}

	/**
	 * Returns true if this list contains no elements
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return numberElements==0 ? true : false;
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * @return an iterator over the elements in this list in proper sequence
	 */
	@Override
	public Iterator<E> iterator() {
		return  new Iterator<E>() {
			
            private int currentIndex = 0;

			@Override
			public boolean hasNext() {
                return currentIndex < numberElements;
			}

			@Override
			public E next() {
				return array[currentIndex++];
			}
			
		};
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or {@literal -}1 if this list does not contain the element.
	 * @param element - element to search for
	 * @return the index of the last occurrence of the specified element in this list, or {@literal -}1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(Object element) {
		for(int i=numberElements-1;i>=0;i--)
			if(array[i].equals(element)) return i;
		return -1;
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper sequence). 
	 * @return a list iterator over the elements in this list (in proper sequence). 
	 */
	@Override
	public ListIterator<E> listIterator() {
		return listIterator(0);
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list. 
	 * The specified index indicates the first element that would be returned by an initial call to next. 
	 * An initial call to previous would return the element with the specified index minus one. 
	 * @return	a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list
	 */
	@Override
	public ListIterator<E> listIterator(int arg0) {
		return new ListIterator<E>() {
			
			private int currentIndex = 0;

			@Override
			public void add(E e) {
				ArrayList.this.add(e);
			}

			@Override
			public boolean hasNext() {
				return currentIndex < numberElements;
			}

			@Override
			public boolean hasPrevious() {
				return currentIndex > 0;
			}

			@Override
			public E next() {
				return array[++currentIndex];
			}

			@Override
			public int nextIndex() {
				return currentIndex+1;
			}

			@Override
			public E previous() {
				return array[--currentIndex];
			}

			@Override
			public int previousIndex() {
				return currentIndex-1;
			}

			@Override
			public void remove() {
				ArrayList.this.remove(currentIndex);
			}

			@Override
			public void set(E e) {
				ArrayList.this.set(currentIndex, e);
			}
		};
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any subsequent elements to the left.
	 * @return the element that was removed from the list
	 * @throws IndexOutOfBoundsException - if the index is out of range
	 */
	@Override
	public E remove(int index) {
		if(index < 0 || index >= numberElements) throw new IndexOutOfBoundsException();
		E element = array[index];
		for(int i=index;i<numberElements-1;)
			array[i]=array[++i];
		array[--numberElements]=null;
		return element;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if its present. If the list does not contain the element, it is unchanged. 
	 * Shifts any subsequent elements to the left.
	 * @return true if list changed as a result of the call
	 */
	@Override
	public boolean remove(Object object) {
		int index = indexOf(object);
		if(index>-1){
			remove(index);
			return true;
		}return false;
	}

	/**
	 * Removes from this list all of its elements that are contained in the specified collection. If the list does not contain the element, it is unchanged. 
	 * Shifts any subsequent elements to the left.
	 * @return true if this contained the specified element
	 */
	@Override
	public boolean removeAll(Collection<?> collection) {
		for(Object o : collection)
			while(remove(o));
		return true;
	}

	/**
	 * Retains only the elements in this list that are contained in the specified collection. In other words, removes from this list all of its elements that are not contained in the specified collection.
	 * @param	c - collection containing elements to be retained in this list
	 * @return true if this list changed as a result of the call
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		E[] aux = Arrays.copyOf(array, numberElements);
		for(Object o : aux)
			if(!c.contains(o))
				while(remove(o));
		return true;
	}

	/**
	 * Replaces the item at the specified index in this list with the specified element.
	 * @param index - index of the element to replace
	 * @param element - element to be stored at the specified index
	 * @throws IndexOutOfBoundsException - if the index is out of range
	 * @return the element previously at the specified position
	 */
	@Override
	public E set(int index, E element) throws IndexOutOfBoundsException{
		if(index < 0 || index >= numberElements) throw new IndexOutOfBoundsException();
		E aux = array[index];
		array[index]=element;
		return aux;
	}

	/**
	 * Returns the number of elements in this list.
	 * @return Number of elements in this list.
	 */
	@Override
	public int size() {
		return numberElements-1;
	}

	/**
	 * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
	 * @return a view of the specified range within this list
	 * @throws IndexOutOfBoundsException - if an endpoint index value is out of range
	 * @throws IllegalArgumentException - if the endpoint indices are out of order
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException, IllegalArgumentException {
		if(fromIndex < 0 || fromIndex > numberElements) throw new IndexOutOfBoundsException();
		if(fromIndex > toIndex) throw new IllegalArgumentException();
		E[] aux =(E[]) new Object[toIndex-fromIndex];
		for(int i=fromIndex, j=0;i<toIndex;i++,j++)
			aux[j]=array[i];
		return Arrays.asList(aux);
	}

	/**
	 * Returns an array containing all of the elements of this list in the same order as stored.
	 * <br>
	 * <br>
	 * The returned array will be a new array, so it's "safe" to modify it as this list won't be modified.
	 * @return An array containing all of the elements in this list in proper sequence
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, numberElements);
	}

	/**
	 * Returns an array containing all of the elements in this list in the same order as stored. If the list fits in the specified array, it is returned therein. 
	 * Otherwise, a new array is allocated with the runtime type of the specified array and the size of the list.
	 * @param a - the array into which the elements of the list are to be stored.
	 * @return an array containing the elements of the list
	 * @throws NullPointerException - if the specified array is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) throws NullPointerException{
		if(a==null) throw new NullPointerException();
		if(a.length>=numberElements){
			for(int i=0;i<numberElements;i++)
				a[i]=(T) array[i];
			return a;
		}else
			return (T[])toArray();
	}
	
	/**
	 * Returns a string representing this collection, representation begins with {@literal "["} and ends with {@literal "]"}, elements from this collection are separated by {@literal ", "} (comma and space).
	 */
	@Override
	public String toString(){
		String msg = "[";
		
		for(E element : array)
			msg += element + " ,";
		
		return msg.substring(0, msg.length()-2) + "]";
	}

}
