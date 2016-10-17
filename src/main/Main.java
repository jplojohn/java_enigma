package main;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Main {

    private static final int ALPHABET_LENGTH = 26;

    private static String[] rotorFiles;
    private static String plugboardFile;

    private static int[][] rotorMaps;
    private static int[] plugboardMap;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide paths to rotor configuration files, in " +
                    "the order the machine would place them\n" +
                    "followed by the path to the plugboard configuration file\n" +
                    "Quitting...");
            System.exit(0);
        }

        rotorFiles = Arrays.copyOf(args, args.length - 1);
        plugboardFile = args[args.length -1];
        try {
            parseFiles();
        } catch (IOException e) {
            System.out.println("Couldn't read file: " + e.getMessage());
            System.out.println("Quitting...");
            System.exit(1);
        }
        new EnigmaMachine(rotorMaps, plugboardMap);

    }

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

    private static int[] parseFile(String fileName) throws IOException {
        InputStream f = new FileInputStream(fileName);
        InputStreamReader fr = new InputStreamReader(f, Charset.forName("UTF-8"));
        BufferedReader fbr = new BufferedReader(fr);

        String line = fbr.readLine();
//        System.out.println(line);
        if (line == null) { // Edge case for identity-mapped plugboard (see null.pb)
            return new int[0];
        }
        String[] splitLine = line.split(" ");
        int[] resultMap = new int[splitLine.length];
        System.out.print("{");
        for (int i = 0; i < splitLine.length; i++) {
            resultMap[i] = Integer.parseInt(splitLine[i]);
            System.out.print(resultMap[i] + ",");
        }
        System.out.print("}\n");

        return resultMap;

    }



}
