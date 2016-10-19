package main;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

import static main.EnigmaMachine.helpMessage;
class Main {
  
  static final int ALPHABET_LENGTH = 26;
  
  // Various Error Codes
  static final int EXIT_NOERR = 0;
  static final int EXIT_HUMAN_ERR = 1;
  static final int EXIT_PROGRAM_ERR = 2;
  
  // The strings of the paths to the configuration files given on the command
  // line.
  private static String[] rotorFiles;
  private static String plugboardFile;
  
  // The various configurations after they have been parsed into int[] arrays
  private static int[][] rotorMaps;
  private static int[] plugboardMap;
  
  /*
   * This function starts the parsing of the input files and then hands off
   * to the main EnigmaMachine object.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("There must be at least 1 configuration file to act" +
              "as the plugboard. Showing help text and exiting.");
      System.out.println(helpMessage);
      System.exit(EXIT_HUMAN_ERR);
    }
    
    rotorFiles = Arrays.copyOf(args, args.length - 1);
    plugboardFile = args[args.length - 1];
    try {
      parseFiles();
    } catch (IOException e) {
      System.out.println("Couldn't read file: " + e.getMessage());
      System.out.println("Showing help message and exiting...");
      System.out.println(helpMessage);
      System.exit(EXIT_HUMAN_ERR); // This is raised if the file cannot be read.
      // This is therefore the user's error, not ours.
    }
    new EnigmaMachine(rotorMaps, plugboardMap);
    
  }
  
  /*
   * This program iterates through the provided filenames, passing them off
   * to the parseFile() function and storing the result in the appropriate
   * Array.
   */
  private static void parseFiles() throws IOException {
    rotorMaps = new int[rotorFiles.length][];
    for (int i = 0; i < rotorMaps.length; i++) {
      rotorMaps[i] = new int[ALPHABET_LENGTH];
    }
    for (int i = 0; i < rotorFiles.length; i++) {
      System.out.print("Parsing rotor number: " + i + ": ");
      rotorMaps[i] = parseFile(rotorFiles[i]);
    }
    System.out.print("Parsing plugboard: ");
    plugboardMap = parseFile(plugboardFile);
  }
  
  /*
   * This function takes a file name and attempts to read a rotor or plugboard
   * mapping into an Array out of it. Examples of mappings may be seen in the
   * RotorTests.java file in the test package.
   */
  private static int[] parseFile(String fileName) throws IOException {
    InputStream f = new FileInputStream(fileName);
    InputStreamReader fr = new InputStreamReader(f, Charset.forName("UTF-8"));
    BufferedReader fbr = new BufferedReader(fr);
    
    String line = fbr.readLine();
    if (line == null) { // Edge case for identity-mapped plugboard (see null.pb)
      return new int[0];
    }
    String[] splitLine = line.split(" ");
    int[] resultMap = new int[splitLine.length];
    System.out.print("{");
    for (int i = 0; i < splitLine.length; i++) {
      
      // Parse ints from the given input Strings. If Exception if thrown, inform
      // user of a syntax error and abort.
      try {
        resultMap[i] = Integer.parseInt(splitLine[i]);
      } catch (NumberFormatException e) {
        System.err.println("\nThere was an error parsing the file at " +
                "the point shown. Please check " +
                "the syntax and try again.\nEnsure there is one space between " +
                "each number, that there are no extra spaces,\n" +
                "and that only numbers between 0 and " +
                (ALPHABET_LENGTH - 1) + " are present.\n Showing help and " +
                "exiting...");
        System.out.println(helpMessage);
        System.exit(EXIT_HUMAN_ERR);
      }
      System.out.print(resultMap[i] + ",");
    }
    System.out.print("}\n");
    
    return resultMap;
    
  }
  
  
}
