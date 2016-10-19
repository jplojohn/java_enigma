package main;

import util.alphabet;

import java.util.Arrays;

public class Plugboard {
  final static int[] IDENTITY_MAP = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
          13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
  private int[] map;
  
  public Plugboard(int[] map) {
    setUpMap(map);
  }
  
  private void setUpMap(int[] inputMap) {
    if (inputMap.length % 2 != 0) {
      throw new RuntimeException("Plugboard config must have even number of items");
    }
    this.map = Arrays.copyOf(IDENTITY_MAP, 26);
    for (int i = 0; i < inputMap.length; i = i + 2) {
      int swp1 = inputMap[i];
      int swp2 = inputMap[i + 1];
      
      map[swp1] = swp2;
      map[swp2] = swp1;
    }
  }
  
  public char translate(char c) {
    int charPos = alphabet.charToPosition(c);
    int translatedPos = this.map[charPos];
    return alphabet.positionToChar(translatedPos);
  }
}
