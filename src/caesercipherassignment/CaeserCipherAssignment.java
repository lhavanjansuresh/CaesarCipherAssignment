package caesercipherassignment;

import java.util.Scanner;

public class CaeserCipherAssignment {

    public static void main(String[] args) {
        Scanner Keyboard = new Scanner(System.in);

        String message, option;
        String shiftStr;
        int shift = 0;
        boolean playAgain = true, forcePlayAgain = false;

        do {
            System.out.print("Encode (e) Decode (d) BruteForce (b) Quit (q): ");
            option = Keyboard.nextLine();
            if (option.equals("q")) {
                playAgain = false;
            } else if (option.equals("e")) {
                do {
                    System.out.print("Phrase to encode: ");
                    message = Keyboard.nextLine();
                    if (typeVerify(message, 's') == true) {
                        forcePlayAgain = false;
                    } else {
                        System.out.println("Please do not enter an empty string.");
                        forcePlayAgain = true;
                    }
                } while (forcePlayAgain == true);
                do {
                    System.out.print("Shift right by?: ");
                    shiftStr = Keyboard.nextLine();
                    if (typeVerify(shiftStr, 'i') == true) {
                        shift = Integer.parseInt(shiftStr);
                        System.out.println("Encoded is: " + encodeAndDecode(message, shift));
                        forcePlayAgain = false;
                    } else {
                        forcePlayAgain = true;
                        System.out.println("Please write an integer 0 to 25 inclusive.");
                    }
                } while (forcePlayAgain == true);
            } else if (option.equals("d")) {
                do {
                    System.out.print("Phrase to decode: ");
                    message = Keyboard.nextLine();
                    if (typeVerify(message, 's') == true) {
                        forcePlayAgain = false;
                    } else {
                        System.out.println("Please do not enter an empty string.");
                        forcePlayAgain = true;
                    }
                } while (forcePlayAgain == true);

                do {
                    System.out.print("Shift left by?: ");
                    shiftStr = Keyboard.nextLine();
                    if (typeVerify(shiftStr, 'i') == true) {
                        shift = Integer.parseInt(shiftStr);
                        System.out.println("Decoded is: " + encodeAndDecode(message, shift * -1));
                        forcePlayAgain = false;
                    } else {
                        forcePlayAgain = true;
                        System.out.println("Please write an integer 0 to 25 inclusive.");
                    }
                } while (forcePlayAgain == true);
            } else if (option.equals("b")) {
                do {
                    System.out.print("Phrase to Brute Force: ");
                    message = Keyboard.nextLine();
                    if (typeVerify(message, 's') == true) {
                        forcePlayAgain = false;
                    } else {
                        System.out.println("Please do not enter an empty string.");
                        forcePlayAgain = true;
                    }
                } while (forcePlayAgain == true);
                for (int b = 1; b <= 26; b++) {
                    String[] ciphText = bruteForce(message);
                    if (b < 26) {
                        System.out.println(ciphText[b]);
                    } else if (b == 26) {
                        System.out.println(ciphText[0]);
                        System.out.println(ciphText[26]);
                    }
                }
            } else if (option.equals("") || !option.equals("e") || !option.equals("d") || !option.equals("b") || !option.equals("q")) {
                System.out.println("Please write either 'e', 'd', 'b', or 'q'.");
                playAgain = true;
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

    public static String[] bruteForce(String msgIn) {
        int bestArray = 0, score, bestScore = 0, loc;
        String bruteForceArray[] = new String[27];

        String dictionary[] = {"the ", "and ", "you ", "that ", "was ", "for ", 
            "are ", "with", "his", "they", "this", "have", "from", "what", "when"};

        for (int a = 0; a < 26; a++) {
            String printShift = encodeAndDecode(msgIn, a * -1);

            if (a == 0) {
                bruteForceArray[a] = ("Shifted right 26: " + printShift);
            } else {
                bruteForceArray[a] = ("Shifted right " + a + ": " + printShift);
            }
        }
        for (int b = 0; b < 26; b++) {
            score = 0;
            for (int c = 0; c < dictionary.length; c++) {
                loc = 0;
                while (bruteForceArray[b].toLowerCase().indexOf(dictionary[c], loc) > -1) {
                    score++;
                    if (score > bestScore) {
                        bestArray = b;
                    }
                    loc = bruteForceArray[b].toLowerCase().indexOf(dictionary[c], loc) + dictionary[c].length();
                }
            }
        }
        if (bestArray == 0) {
            bruteForceArray[26] = "The best decode was with key 26\nDecoded message is: " + encodeAndDecode(msgIn, 0 * -1);
        } else {
            bruteForceArray[26] = "The best decode was with key " + bestArray + "\nDecoded message is: " + encodeAndDecode(msgIn, bestArray * -1);
        }
        return bruteForceArray;
    }

    public static boolean typeVerify(String token, char type) {
        boolean verified = true;
        if (type == 's') {
            if (token.equals("")) {
                verified = false;
            } else {
                verified = true;
            }

        } else if (type == 'i') {
            try {
                Integer.parseInt(token);
                verified = true;
            } catch (NumberFormatException e) {
                verified = false;
            }
            if (verified == true) {
                if (Integer.parseInt(token) >= 0 && Integer.parseInt(token) <= 25) {
                    verified = true;
                } else {
                    verified = false;
                }
            }
        }
        return verified;
    }
}
