package util;

public final class Array {
  /*
   * Searches an int Array for the index of a specific element
   */
  public static int search(int[] dict, int key) {
    for (int index = 0; index < dict.length; index++) {
      if (dict[index] == key) {
        return index;
      }
    }
    return -1;
  }
  
  public static boolean contains(int[] dict, int key) {
    for (int i : dict) {
      if (i == key) {
        return true;
      }
    }
    return false;
  }
}
