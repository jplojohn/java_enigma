package main;

import java.io.IOException;

class EnigmaMachine {

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
                "This machine accept CAPITAL A-Z letters only\n" +
                "Other (invalid) characters will print out a dot (.).\n" +
                "Note that invalid characters will not advance the rotor positions,\n" +
                "and may leave your encrypted message non-decryptable.\n" +
                "Enter 'q' to quit the machine at any time.");
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
