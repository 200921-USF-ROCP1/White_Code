package com.generic.arraylist;

public class ArrayList<T> {
	private T[] arr;
	
	public ArrayList() {
		// intialize at size 10
		arr = (T[])(new Object[10]);
	}
	public ArrayList(int intitialSize) {
		// intialize at custom size
		arr = (T[])(new Object[intitialSize]);
	}
	
	public void add(T newEntry) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				arr[i] = newEntry;
				return;
			}
		}
		T[] temp = (T[])(new Object[arr.length+1]);
		System.arraycopy(arr, 0, temp, 0, arr.length);
		arr = temp;
		arr[arr.length-1] = newEntry;
		
	} 
	public void add(T[] newEntries) {
		for (int i = 0; i < newEntries.length; i++) {
			add(newEntries[i]);
		}
	}
	
	public T get(int i) {
		return arr[i];
	}
	public T[] get() {
		return arr;
	}
	
	public int getSize() {
		return arr.length;
	}
	/*
	 * Split should take arr and split it into a number
	 * of new subarrays
	 * The number is given by numberOfNewArrays
	 * 
	 * e.g. if [1, 5, 6, 5, 7]
	 * so the output would be:
	 * 
	 * [
	 * 	[1, 5]
	 * 	[6, 5]
	 * `[7, null]
	 * ]
	 */
	public T[][] split(int numberOfNewArrays) {

		int perArray = (int) Math.ceil( (double)arr.length/ (double)numberOfNewArrays);
		T[][] sarr = (T[][])(new Object[numberOfNewArrays][perArray]);
		
		int itr = 0; //keep track of arr value indexes
		
		for (int i = 0; i < sarr.length; i++) {
			for (int j = 0; j < sarr[0].length; j++) {
				if ((itr) <= arr.length-1) {
					sarr[i][j] = arr[itr];
				} else {
					sarr[i][j] = null;
				}
				itr++;	
			}
		}
		
		return sarr;
		
	}
}


