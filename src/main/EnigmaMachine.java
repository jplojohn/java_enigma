package main;

import java.io.IOException;

class EnigmaMachine {
  
  static final String helpMessage
          = "THE ENIGMA MACHINE\n\n This program assumes you already understand\n" +
          "how the famous Enigma machine from World War II works. For\n" +
          "information on that, please see https://en.wikipedia.org/" +
          "wiki/Enigma_machine\n\nINTRODUCTION\n\n" +
          "This program is very simple. It may be called with a plugboard\n" +
          "and 0 or more rotor configurations on the command line, in the\n" +
          "order of the 1st rotor, the 2nd rotor and so on, and finally,\n" +
          "the plugboard configuration (which can be an empty file to\n" +
          "indicate that the plugboard has no wires attached).\n\n" +
          "" +
          "SYNTAX OF THE ROTOR CONFIGURATION FILES\n\n" +
          "The Rotor file is on one line and lists the mapping of inputs to\n" +
          "outputs with the first number in the file representing an input on\n" +
          "'A', and the number in it indicating the output. For example, 0\n" +
          "corresponds to the letter 'A', 1 to the letter 'B', and 25 to the" +
          "letter 'Z'. For example:\n" +
          "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 0\n" +
          "represents a mapping up one place in the Alphabet. With the rotor in\n" +
          "the initial position, an 'A' will be mapped to 'B', a 'B' to 'C',\n" +
          "and 'Z' to 'A'.\n" +
          "Remember, this mapping will not hold after the first translation as\n" +
          "the rotor will advance one position forward, with 'A' mapping to 'C'\n" +
          "and so on.\nFinally, the rotor file must contain exactly one each of" +
          "the numbers 0-25, separated by a single space each. Any other\n" +
          "combination will result in a syntax error and the program will" +
          "abort\n\n" +
          "" +
          "SYNTAX OF THE PLUGBOARD FILE\n\n" +
          "" +
          "The syntax of the plugboard file is similar, but lists pairs of\n" +
          "numbers. These numbers again represent letters of the Alphabet, and\n" +
          "represent which pairs of letters will be joined together on the\n" +
          "virtual plugboard of the enigma machine in this program. Duplicates,\n" +
          "an odd number of numbers, incorrect spacing or numbers in the wrong\n" +
          "range (0-25) will result in a syntax error or unexpected behaviour." +
          "\n\n" +
          "USING THE PROGRAM\n\n" +
          "" +
          "Using the program is quite simple. Enter the message you want to\n" +
          "translate through your chosen configuration of rotors and plugboard\n" +
          "and press ENTER. The result will then be shown below. You may enter\n" +
          "further messages in the same way.\n" +
          "All messages must contain only the capital letters A-Z, and spaces." +
          "If a message contains any other characters, they will be replaced\n" +
          "by a dot (.) in the result. Note that invalid characters do not\n" +
          "cause the rotors to change positions, as no translation has taken\n" +
          "place.\n" +
          "Enter 'h' at any time to show this help message again,\n" +
          "or enter 'q' to exit the program.\n\n" +
          "Remember, you can always decrypt your messages by entering the\n" +
          "encrypted version into an *identically* configured enigma machine.\n" +
          "Remember also that the configuration of the machine includes the\n" +
          "position of the rotors, which will change with every valid character\n" +
          "entered.\n\n" +
          "" +
          "ENJOY USING THE ENIGMA MACHINE\n" +
          "Source code at https://github.com/jplojohn/java_enigma";
  private final Rotor[] rotors;
  private final Plugboard plugboard;
  
  EnigmaMachine(int[][] rotorMaps, int[] plugboardMap) {
    this.rotors = new Rotor[rotorMaps.length];
    this.plugboard = new Plugboard(plugboardMap);
    for (int i = 0; i < rotorMaps.length; i++) {
      rotors[i] = new Rotor(rotorMaps[i], 0, i);
    }
    startMachine();
  }
  
  private void startMachine() {
    System.out.println("ENIGMA MACHINE\n================\n" +
            "This machine accept CAPITAL A-Z letters only and spaces.\n" +
            "Other (invalid) characters will print out a dot (.).\n" +
            "Type 'h' to see the help message, and 'q' at any time\n" +
            "to exit the program\n\n" +
            "To begin, type below and press ENTER to see the result.\n");
    
    do {
      char c = '0';
      try {
        c = (char) System.in.read();
      } catch (IOException e) {
        System.out.println("There was a problem reading that character");
        System.out.println(e.getMessage());
        System.out.println("Exiting...");
        System.exit(-1);
      }
      System.out.print(translate(c));
      
    } while (true);
  }
  
  private char translate(char c) {
    if (c == ' ' || c == '\n') {
      return c;
    }
    if (c == 'q') {
      System.out.println("Caught exit character, 'q'. Exiting...");
      System.exit(0);
    }
    if (c < 'A' || c > 'Z') {
      return '.';
    }
    char temp = c;
    temp = plugboard.translate(temp);
    for (Rotor r : rotors) {
      temp = r.translateForwards(temp);
    }
    temp = Reflector.reflect(temp);
    for (int i = rotors.length - 1; i >= 0; i--) {
      temp = rotors[i].translateBackwards(temp);
    }
    temp = plugboard.translate(temp);
    return temp;
  }
}
