package main;

import org.apache.commons.lang3.ArrayUtils;
import util.alphabet;
import util.array;

import static main.Main.*;

public class Rotor {
  private final int[] map;
  private final int rotorNumber;
  private int position;
  private int incrementCount;
  
  public Rotor(int[] map, int startPosition, int rotorNumber) {
    this.map = map;
    { // This section checks the validity of the map.
      
      // First check the map is of the correct length (26, the length
      // of the alphabet).
      if (map.length != ALPHABET_LENGTH) {
        System.err.println("Error: Rotor number " + rotorNumber + "has an " +
                "invalid configuration with the number of entries not equal" +
                "to 26.\n Exiting....");
        System.exit(EXIT_HUMAN_ERR);
      }
      
      for (int i : map) {
        // Check that each element in the map is valid (ie between 0 and 25).
        if (i > ALPHABET_LENGTH - 1 || i < 0) {
          System.err.println("Error: Rotor number " + rotorNumber + " has an invalid" +
                  "configuration with one or more entries outside of the range 0-25\n" +
                  "Exiting...");
          System.exit(EXIT_HUMAN_ERR);
        }
      }
      
      // Check that each element in the map appears only once, by verifying
      // that they ALL appear;
      for (int i = 0; i < ALPHABET_LENGTH; i++) {
        boolean contains = ArrayUtils.contains(map, i);
        if (!contains) {
          System.err.println("Error: Rotor number " + rotorNumber + "has an" +
                  "invalid configuration, which is either missing or has more" +
                  "than one of the number" + i + ".\n Exiting...");
          System.exit(EXIT_HUMAN_ERR);
        }
      }
  
    }
    
    this.position = startPosition;
    if (this.position < 0 || this.position >= ALPHABET_LENGTH) {
      System.err.println("Error: Rotor number " + rotorNumber + "has start " +
              "position of " + startPosition + ", which is invalid as it must" +
              " be between 0 and " + ALPHABET_LENGTH + ".\n Exiting...");
      System.exit(EXIT_PROGRAM_ERR); // Program Error (for now) as customising
      // initial rotor position is not supported - but can be done by modifying
      // the rotor configuration file being loaded.
    }
    this.rotorNumber = rotorNumber;
    this.incrementCount = 0;
  }
  
  private void incrementPosition() {
    incrementCount++;
    
    if ((incrementCount % (Math.pow(26, rotorNumber))) == 0) {
      if (position >= ALPHABET_LENGTH - 1) {
        position = 0;
        incrementCount = 0;
      } else {
        position++;
      }
      
    }
  }
  
  public char translateForwards(char c) {
    int charPos = alphabet.charToPosition(c);
    int translatedCharPos = map[(charPos + position) % ALPHABET_LENGTH];
    return alphabet.positionToChar(translatedCharPos);
  }
  
  public char translateBackwards(char c) {
    int charPos = alphabet.charToPosition(c);
    int translatedCharPos = ((array.search(map, charPos) - position
            + ALPHABET_LENGTH) % ALPHABET_LENGTH);
    incrementPosition();
    return alphabet.positionToChar(translatedCharPos);
  }
}