package main;

import util.Alphabet;

import java.util.Arrays;
import java.util.stream.IntStream;

import static main.Main.ALPHABET_LENGTH;
import static main.Main.EXIT_HUMAN_ERR;

public class Plugboard {
  private final static int[] IDENTITY_MAP = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
          13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
  private int[] map;
  
  public Plugboard(int[] map) {
    setUpMap(map);
  }
  
  private void setUpMap(int[] inputMap) {
    //Error checking on input map.

    // Checks the plugboard has pairs of entries with no single one on the end.
    if (inputMap.length % 2 != 0) {
      System.err.println("Plugboard configuration does not have even number of entries\nExiting...");
      System.exit(EXIT_HUMAN_ERR);
    } else if (inputMap.length >= ALPHABET_LENGTH) { // Checks that the given inputMap is of an appropriate length
      System.err.println("Plugboard configuration is too long, longer than " + ALPHABET_LENGTH + " entries." +
              "\nExiting...");
      System.exit(EXIT_HUMAN_ERR);
    }

    // Checks that any number appearing in it appears only once.
    if (IntStream.of(inputMap).distinct().toArray().length != inputMap.length) {
      System.err.println("The plugboard map contains an entry more than once.\nExiting...");
      System.exit(EXIT_HUMAN_ERR);
    }

    // The actual constructor
    this.map = Arrays.copyOf(IDENTITY_MAP, ALPHABET_LENGTH);
    for (int i = 0; i < inputMap.length; i += 2) {
      int swp1 = inputMap[i];
      int swp2 = inputMap[i + 1];
      
      map[swp1] = swp2;
      map[swp2] = swp1;
    }
  }
  
  public char translate(char c) {
    int charPos = Alphabet.charToPosition(c);
    int translatedPos = this.map[charPos];
    return Alphabet.positionToChar(translatedPos);
  }
}
