package main;
import util.alphabet;
import util.array;

public class Rotor {
	private int[] map;
//	private int[] reverseMap;
	private int position;
	private boolean rotate;
	
	public Rotor (int[] map, int startPosition, boolean rotate) {
		this.map = map;
		this.position = startPosition;
		//this.reverseMap = array.reverseIntArray(this.map);
	}
	
	private void incrementPosition() {
		if (this.rotate == false) {
			return;
		} else if (this.position == 25) {
			this.position = 0;
		} else
			this.position++;
	}
	
	public char translateForwards(char c) {
		int charPos = alphabet.charToPosition(c);
		int translatedCharPos = (map[charPos] + position) % 26;
		return alphabet.positionToChar(translatedCharPos);
	}
	
	public char translateBackwards(char c) {
		int charPos = alphabet.charToPosition(c);
		int translatedCharPos = (array.search(map, charPos) + position) % 26;
		// System.out.println("charPos is: " + charPos + "\nPosition of charPos in reverse is " + array.search(map, charPos) + "\n");
		incrementPosition();
		return alphabet.positionToChar(translatedCharPos);
	}
}
