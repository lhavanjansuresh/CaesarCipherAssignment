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

        String message, option;
        int shift = 0;
        boolean playAgain = true;

        do {
            System.out.print("Encode (e) Decode (d) BruteForce (b) Quit (q): ");
            option = Keyboard.nextLine();
            if (option.equals("q")) {
                playAgain = false;
            } else if (option.equals("e")) {
                System.out.print("Phrase to encode: ");
                message = Keyboard.nextLine();
                System.out.print("Shift right by?: ");
                shift = Keyboard.nextInt();
                System.out.println("Encoded is: " + encodeAndDecode(message, shift));
            } else if (option.equals("d")) {
                System.out.print("Phrase to decode: ");
                message = Keyboard.nextLine();
                System.out.print("Shift left by?: ");
                shift = Keyboard.nextInt();
                System.out.println("Encoded is: " + encodeAndDecode(message, shift * -1));
            } else if (option.equals("b")) {
                System.out.print("Phrase to Brute Force: ");
                message = Keyboard.nextLine();
                for (int b = 1; b <= 26; b++){
                    String[] ciphText = breakCode(message);
                    if (b<26){
                        //System.out.println(ciphText[b]);
                    System.out.println("For shift of " +b+ ", decoded is: " + ciphText[b]);
                    }
                   else if (b==26){
                       //System.out.println(ciphText[0]);
                        System.out.println("For shift of " +b+ ", decoded is: " + ciphText[0]);
                    }
                }

                //System.out.print("Please write an integer 1-3 inclusive or 'q' to quit.");
            }
        } while (playAgain == true);

    }

    public static String encodeAndDecode(String msgIn, int shift) {
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
    
     public static String [] breakCode(String msgIn) {
         String[] ciphText = new String [26];
        char letter;
        for (int b = 0; b < 26; b++){
        
        for (int a = 0; a < msgIn.length(); a++) {
            letter = msgIn.charAt(a);

            if (letter >= 'A' && letter <= 'Z') {
                letter = (char) (letter + b);

                if (letter > 'Z') {
                    letter = (char) (letter + 'A' - 'Z' - 1);
                } else if (letter < 'A') {
                    letter = (char) (letter - 'a' + 'z' + 1);
                }
                ciphText[b] = ciphText[b] + letter;
            } else if (letter >= 'a' && letter <= 'z') {
                letter = (char) (letter + b);
                if (letter > 'z') {
                    letter = (char) (letter + 'a' - 'z' - 1);
                } else if (letter < 'a') {
                    letter = (char) (letter - 'a' + 'z' + 1);
                }
                ciphText[b]= ciphText[b] + letter;
            } else {
                ciphText[b] = ciphText[b] + letter;
            }
        }
     }
        return ciphText;
}
}
