import java.util.Arrays;

public class main {

	public static void main(String[] args) {
		
		System.out.println("Creating list");
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		System.out.println("Adding 15 elements to list");
		for(int i=0;i<15;i++)
			list.add(i);
				
		String msg = list.isEmpty() ? "List is empty" : list.toString();
		System.out.println(msg);
		
		System.out.println("Adding new elements from a new collection");
		Integer[] array= new Integer[] {90,39,49};
		list.addAll(Arrays.asList(array));
		System.out.println(list);
		
		System.out.println("Adding number 50 at index 5");
		list.add(5,50);
		System.out.println(list);
		
		System.out.println("Adding new elements from previous collection at index 10");
		list.addAll(10, Arrays.asList(array));
		System.out.println(list);
		
		System.out.println("Checking if 5 is at this list");
		msg = list.contains(5) ?  "5 found" : "5 not found";
		System.out.println(msg);
		
		System.out.println("Getting first occurence of element 90");
		msg = list.indexOf(90)<0 ? "90 not found" : "90 at index " + list.indexOf(90);
		System.out.println(msg);
		
		System.out.println("Getting last occurence of element 90");
		msg = list.lastIndexOf(90)<0 ? "90 not found" : "90 at index " + list.lastIndexOf(90);
		System.out.println(msg);
		
		System.out.println("Getting item at index 11");
		System.out.println(list.get(11));
		
		System.out.println("Checking if previous collection is in this list");
		msg = list.containsAll(Arrays.asList(array)) ?  "Collection inside" : "Collection not found";
		System.out.println(msg);
		
		System.out.println("Getting list as array");
		System.out.println(Arrays.asList(list.toArray()));
		
		System.out.println("Creating new array to test toArray");
		Integer[] test = new Integer[30];
		System.out.println("Before: " + Arrays.asList(test));
		list.toArray(test);
		System.out.println("After calling toArray: " + Arrays.asList(test));
		
		System.out.println("Getting a view from index 5 to index 9");
		System.out.println(list.subList(5, 10));
		
		System.out.println("Removing 1st element");
		list.remove(0);
		System.out.println(list);
		
		System.out.println("Removing 1st occurence of 90");
		list.remove((Integer)90);
		System.out.println(list);
		
		/*System.out.println("Removing all elements from collection");
		list.removeAll(Arrays.asList(array));
		System.out.println(list);*/
		
		System.out.println("Retaining all elements from collection");
		list.retainAll(Arrays.asList(array));
		System.out.println(list);
		
		System.out.println("Clearing list");
		list.clear();
		System.out.println(list);
		
	}

}
