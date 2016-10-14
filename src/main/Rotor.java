package main;
import util.alphabet;
import util.array;

public class Rotor {
	private int[] map;
	private int position;
	private int rotorNumber;
	private int incrementCount;
	
	public Rotor (int[] map, int startPosition, int rotorNumber) {
		this.map = map;
		this.position = startPosition;
		this.rotorNumber = rotorNumber;
		this.incrementCount = 0;
	}
	
	private void incrementPosition() {
		incrementCount++;
		
		if (incrementCount % (26^rotorNumber) != 0) {
			if (position >= 25) {
				position = 0;
			} else {
				position++;
			}
		}
	}
	
	public char translateForwards(char c) {
		int charPos = alphabet.charToPosition(c);
		int translatedCharPos = (map[charPos] + position) % 26;
		return alphabet.positionToChar(translatedCharPos);
	}
	
	public char translateBackwards(char c) {
		int charPos = alphabet.charToPosition(c);
		int translatedCharPos = (array.search(map, charPos) + position) % 26;
/*		System.out.println("charPos is: " + charPos + "\nPosition of charPos " +
				"in reverse is " + array.search(map, charPos) + "\n" +
				"Position of charPos in reverse + rotor position is: " +
				array.search(map, charPos - position)); */
		incrementPosition();
		return alphabet.positionToChar(translatedCharPos);
	}
}
