/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesercipherassignment;

import java.util.Scanner;

/**
 *
 * @author lhava
 */
public class CaeserCipherAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner Keyboard = new Scanner(System.in);

        String message = "";
        int shift;

        System.out.print("Encode (1) Decode (2) BruteForce (3) Quit (q): ");
        int option = Keyboard.nextInt();
        Keyboard.nextLine();

        if (option == 1) {
            System.out.print("Phrase to encode: ");
            message = Keyboard.nextLine();
            System.out.print("Shift right by?: ");
            shift = Keyboard.nextInt();
            System.out.print("Encoded is: " + encode(message, shift));
        } else if (option == 2) {
            System.out.print("Phrase to decode: ");
            message = Keyboard.nextLine();
            System.out.print("Shift left by?: ");
            shift = Keyboard.nextInt();
        } else if (option == 3) {
            System.out.print("Phrase to Brute Force: ");
            message = Keyboard.nextLine();
        } else {
            System.out.print("Please write an integer 1-3 inclusive or 'q' to quit.");
        }

    }

    public static String encode(String msgIn, int shift) {
        String ciphText = "";
        char letter;
        for (int a = 0; a < msgIn.length(); a++) {
            letter = msgIn.charAt(a);

            if (letter >= 'A' && letter <= 'Z') {
                letter = (char) (letter + shift);

                if (letter > 'Z') {
                    letter = (char) (letter + 'A' - 'Z' - 1);
                } else if (letter < 'A') {
                    letter = (char) (letter - 'a' + 'z' + 1);
                }
                ciphText = ciphText + letter;
            } else if (letter >= 'a' && letter <= 'z') {
                letter = (char) (letter + shift);
                if (letter > 'z') {
                    letter = (char) (letter + 'a' - 'z' - 1);
                } else if (letter < 'a') {
                    letter = (char) (letter - 'a' + 'z' + 1);
                }
                ciphText = ciphText + letter;
            } else {
                ciphText = ciphText + letter;
            }
        }
        return ciphText;

    }
}
