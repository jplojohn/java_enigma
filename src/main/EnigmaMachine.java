package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class EnigmaMachine {

    private static String[] rotorFiles;
    private static String plugboardFile;

    private static int[][] rotorMaps;
    private static int[] plugboardMap;

    private Rotor[] rotors;
    private Reflector reflector;
    private Plugboard plugboard;

    public static void main(String[] args) {
        rotorFiles = Arrays.copyOf(args, args.length - 1);
        plugboardFile = args[args.length -1];
        try {
            parseFiles();
        } catch (IOException e) {
            System.out.println("Couldn't read file: " + e.getMessage());
        }


     //   }
    }

    private static void parseFiles() throws IOException {
        for (int i = 0; i < rotorFiles.length; i++) {
            rotorMaps[i] = parseFile(rotorFiles[i]);
        }
        plugboardMap = parseFile(plugboardFile);
    }

    private static int[] parseFile(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        List<String> input = Files.readAllLines(filePath, Charset.defaultCharset());
        System.out.println(input.get(0));
        String inputline1 = input.get(0);
        String[] stringMap = inputline1.split("\\s");
        int[] resultMap = new int[stringMap.length];

        for (int i = 0; i < stringMap.length; i++) {
            resultMap[i] = Integer.parseInt(stringMap[i]);
        }

        return resultMap;
    }


}
