/*
 * Author: Sierra Andersen
 * Date: 10 Jan 2023
 * 
 * This program sorts an ArrayList
 */

import java.util.ArrayList;

public class SortArrayList {

	public static void main(String[] args) {
	    ArrayList<Integer> list = new ArrayList<Integer>();
	    list.add(14);
	    list.add(24);
	    list.add(4);
	    list.add(42);
	    list.add(5);
	    sort(list);
	    
	    System.out.print(list);
	  }
	
	public static <E extends Comparable<E>> void sort(ArrayList<E> list) {

		for(int i = 0; i<list.size(); i++){
			E currentMin = list.get(i);
			int currentMinIndex = i;
			for(int j = i+1; j<list.size(); j++) {
				if(currentMin.compareTo(list.get(j))>0) {
					currentMin = list.get(j);
					currentMinIndex = j;
				}	
			}
			list.set(currentMinIndex,list.get(i));
			list.set(i, currentMin);

		}
		
	}
}
