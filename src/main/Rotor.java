package main;
import util.alphabet;
import util.array;

public class Rotor {
    private final int[] map;
    private final int rotorNumber;
    private int position;
	private int incrementCount;

	public Rotor (int[] map, int startPosition, int rotorNumber) {
		this.map = map;
		this.position = startPosition;
		this.rotorNumber = rotorNumber;
		this.incrementCount = 0;
	}

	private void incrementPosition() {
        incrementCount++;

		if ((incrementCount % (Math.pow(26,rotorNumber))) == 0) {
			if (position >= 25) {
				position = 0;
				incrementCount = 0;
			} else {
				position++;
			}

        }
    }

	public char translateForwards(char c) {
		int charPos = alphabet.charToPosition(c);
		int translatedCharPos = map[(charPos + position) % 26];
		return alphabet.positionToChar(translatedCharPos);
	}

	public char translateBackwards(char c) {
		int charPos = alphabet.charToPosition(c);
		int translatedCharPos = ((array.search(map, charPos) - position + 26) % 26);
		incrementPosition();
		return alphabet.positionToChar(translatedCharPos);
	}
}