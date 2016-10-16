package main;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Main {

    private static String[] rotorFiles;
    private static String plugboardFile;

    private static int[][] rotorMaps;
    private static int[] plugboardMap;

    public static void main(String[] args) {
        rotorFiles = Arrays.copyOf(args, args.length - 1);
        plugboardFile = args[args.length -1];
        try {
            parseFiles();
        } catch (IOException e) {
            System.out.println("Couldn't read file: " + e.getMessage());
        }


    }

    private static void parseFiles() throws IOException {
        for (int i = 0; i < rotorFiles.length; i++) {
            rotorMaps[i] = parseFile(rotorFiles[i]);
        }
        plugboardMap = parseFile(plugboardFile);
    }

    private static int[] parseFile(String fileName) throws IOException {
        InputStream f = new FileInputStream(fileName);
        InputStreamReader fr = new InputStreamReader(f, Charset.forName("UTF-8"));
        BufferedReader fbr = new BufferedReader(fr);

        String line = fbr.readLine();
        System.out.println(line);
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
