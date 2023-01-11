/*
Author: 
Date: 
Description: 
 */
import java.util.ArrayList;
public class DistinctElements {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(14);
		list.add(24);
		list.add(14);
		list.add(42);
		list.add(25);

		ArrayList<Integer> newList = removeDuplicates(list);

		System.out.print(newList);
	}

public static <E>ArrayList<E> removeDuplicates(ArrayList<E> list){

	ArrayList<E> distinctList = new ArrayList<>();

	distinctList.add(list.get(0));

	for(int i = 1; i<list.size(); i++) {
		if(!distinctList.contains(list.get(i))) {
			distinctList.add(list.get(i));
		}
	}
	return distinctList;
}
}
