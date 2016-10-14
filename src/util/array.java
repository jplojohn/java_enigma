package util;

public class array {

	public static int[] reverseIntArray(int[] a) {
		int[] res = new int[a.length];
		for(int i = 0; i < a.length / 2; i++)
		{
	    	int temp = a[i];
	    	res[i] = res[a.length - i - 1];
	    	res[a.length - i - 1] = temp;
		}
		return res;
	}
	
	/*
	 * Searches an int array for the index of a specific element
	 */
	public static int search(int[] dict, int key) {
		for (int index = 0; index < dict.length; index++) {
			if ( dict[index] == key ) {
				return index;
			}
	    }
	    return -1;
	}
}
