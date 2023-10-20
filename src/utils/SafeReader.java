package utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class SafeReader {
    public static float checkFloat(Scanner scanner) {
        boolean correctinput = false;
        float newinput = 0f;
        while (!correctinput) {
            try {
                newinput = Float.valueOf(scanner.next());
                correctinput = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrez un nombre flottant : ");
                correctinput = false;
            }
        }
        return newinput;
    }


    public static int checkInt(Scanner scanner) {
        boolean correctinput = false;
        int newinput = 0;
        while (!correctinput) {
            try {
                newinput = Integer.valueOf(scanner.next());
                correctinput = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrez un entier : ");
                correctinput = false;
            }
        }
        return newinput;
    }

    public static ArrayList<String> inputList(Scanner scanner) {
        boolean finished = false;
        String newinput;
        ArrayList<String> inputs = new ArrayList<>();
        while (!finished) {
            newinput = scanner.nextLine();
            if (Objects.equals(newinput, "")) {
                finished = true;
            } else {
                inputs.add(newinput);
            }
        }
        return inputs;
    }
}
