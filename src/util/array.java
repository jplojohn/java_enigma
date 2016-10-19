package util;

public class array {
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
