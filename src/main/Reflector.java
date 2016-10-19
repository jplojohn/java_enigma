package main;

import util.Alphabet;

import static main.Main.ALPHABET_LENGTH;

@SuppressWarnings("ALL")
public enum Reflector {
  ;
  
  public static char reflect(char c) {
    int i = Alphabet.charToPosition(c);
    int res = (i + (ALPHABET_LENGTH / 2)) % ALPHABET_LENGTH;
    return Alphabet.positionToChar(res);
  }
}