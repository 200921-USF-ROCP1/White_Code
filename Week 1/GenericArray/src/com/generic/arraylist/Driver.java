package com.generic.arraylist;

import java.util.Arrays;

public class Driver {

	public static void main(String[] args) {

		ArrayList<Integer> arr = new ArrayList(5);

		Integer[] entries = { 5, 6, 7, 8, 9, 10, 22, 45, 56, 1, 2 };
		arr.add(entries);

		System.out.println(Arrays.deepToString(arr.get())); // original array

		System.out.println(Arrays.deepToString(arr.split(4))); // split array

	}

}
