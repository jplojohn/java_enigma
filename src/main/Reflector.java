package main;

import util.Alphabet;

public class Reflector {
  public static char reflect(char c) {
    int i = Alphabet.charToPosition(c);
    int res = (i + 13) % 26;
    return Alphabet.positionToChar(res);
  }
}
