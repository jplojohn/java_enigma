package util;

public class alphabet {
	
	/*
	 * Returns the numeric position of a character in the latin
	 * uppercase alphabet. Other characters, including lowercase
	 * latin characters will be rejected.
	 * 
	 *  @param	c	A Character in the range A-Z
	 *  @return The integer position of that character in the range 0-25.
	 */
	public static int charToPosition(char c) throws IndexOutOfBoundsException { 
	//Upper case only!
	int temp = (int)c;
	if(temp<=90 && temp>=65) { // Checks the char is in the range A-Z
		return temp-65;
	} else {
		throw new IndexOutOfBoundsException("The specified char is not "
				+ "in the range A-Z");
	}}
	
	
	/*
	 * Performs the reverse function of util.alphabet.charToPosition
	 * 
	 * @param i The integer position in the alphabet to be converted to a char
	 * @return The character that position (@link i) responds to
	 */
	public static char positionToChar(int i) throws IndexOutOfBoundsException {
		if (0 > i || i >= 26 ) {
			throw new IndexOutOfBoundsException("The specified int " + i + ", is"
					+ "not in the range 0-25");
		}
		return (char) (i+65);
	}
	
}

