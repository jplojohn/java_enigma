package main;

import util.alphabet;

public class Reflector {
  public static char reflect(char c) {
    int i = alphabet.charToPosition(c);
    int res = (i + 13) % 26;
    return alphabet.positionToChar(res);
  }
}
