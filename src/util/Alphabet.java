package util;

public class Alphabet {
  
  /*
   * Returns the numeric position of a character in the latin
   * uppercase Alphabet. Other characters, including lowercase
   * latin characters will be rejected.
   */
	public static int charToPosition(char c) throws IndexOutOfBoundsException { 
	//Upper case only!

	if(c <= 'Z' && c >= 'A') { // Checks the char is in the range A-Z
		return c - 'A';
	} else {
		throw new IndexOutOfBoundsException("The specified char is not "
				+ "in the range A-Z");
	}
	}
	
	
	/*
     * Performs the reverse function of charToPosition; it takes a position in
	 * the English alphabet (0-25) and returns the corresponding Capital letter
	 * as a char.
	 */
	public static char positionToChar(int i) throws IndexOutOfBoundsException {
		if (0 > i || i >= 26 ) {
			throw new IndexOutOfBoundsException("The specified int " + i + ", is"
					+ "not in the range 0-25");
		}
		return (char) (i+'A');
	}
	
}

