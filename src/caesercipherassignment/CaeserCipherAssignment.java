package caesercipherassignment;

import java.util.Scanner; //Imports scanner class

/*
Assignment: Caeser Cipher Assignment
Teacher/Course: Mr. Payne/ICS4U1-01
Created By: Lhavanjan Suresh
Date: October 06, 2022

A player inputs a message where it will then decode, encode or brute force the
message by shifting through letters of the alphabet. 
*/

public class CaeserCipherAssignment {

    public static void main(String[] args) {
        Scanner Keyboard = new Scanner(System.in); //Creates scanner class

        //Variables
        String message, option;
        String shiftStr;
        int shift = 0;
        boolean playAgain = true, forcePlayAgain = false;

        do {
            System.out.print("Type 'e' to Encode, 'd' to Decode, 'b' to BruteForce and 'q' to Quit: ");
            option = Keyboard.nextLine(); //Save player input to option
            //if option is equal to the letter 'q'
            if (option.equals("q")) {
                playAgain = false; //Set playAgain to false
            //else if option is equal to the letter 'e'
            } else if (option.equals("e")) {
                do {
                    System.out.print("Phrase to encode: ");
                    message = Keyboard.nextLine(); //Save player input to message
                    //if message is a string and returns true
                    if (typeVerify(message, 's') == true) {
                        forcePlayAgain = false; //Set forcePlayAgain to false
                    } else {
                        System.out.println("Please do not enter an empty string.");
                        forcePlayAgain = true; //Set forcePlayAgain to true
                    } 
                } while (forcePlayAgain == true); //Do-while forcePlayAgaub is equal to true
                do {
                    System.out.print("Shift right by?: ");
                    shiftStr = Keyboard.nextLine(); //Save player input to shiftStr
                    //if shiftStr is a valid integer and returns true
                    if (typeVerify(shiftStr, 'i') == true) {
                        shift = Integer.parseInt(shiftStr); //Parse shiftStr into integer to shift
                        //Send parameters message and shift to encodeAndDecode method and display encoded
                        System.out.println("Encoded is: " + encodeAndDecode(message, shift));
                        forcePlayAgain = false; //Set forcePlayAgain to false
                    } else {
                        forcePlayAgain = true; //Set forcePlayAgain to true
                        System.out.println("Please write an integer 0 to 25 inclusive.");
                    }
                } while (forcePlayAgain == true); //Do-while forcePlayAgain is equal to true
            //else if option is equal to the letter 'd'
            } else if (option.equals("d")) {
                do {
                    System.out.print("Phrase to decode: ");
                    message = Keyboard.nextLine(); //Save players input to message
                    //if message is a valid string and returns true
                    if (typeVerify(message, 's') == true) {
                        forcePlayAgain = false; //Set forcePlayAgain to false
                    } else {
                        System.out.println("Please do not enter an empty string.");
                        forcePlayAgain = true; //Set forcePlayAgain to true
                    }
                } while (forcePlayAgain == true);//Do-while forcePlayAgain equal to true

                do {
                    System.out.print("Shift left by?: ");
                    shiftStr = Keyboard.nextLine(); //Save players input to shiftStr
                    //if shiftStr is a valid integer and returns true
                    if (typeVerify(shiftStr, 'i') == true) {
                        shift = Integer.parseInt(shiftStr); //Parse shiftStr into integer to shift
                        //Send parameters message and shift to encodeAndDecode method and display encoded
                        System.out.println("Decoded is: " + encodeAndDecode(message, shift * -1));
                        forcePlayAgain = false; //Set forcePlayAgain to false
                    } else {
                        forcePlayAgain = true; //Set forcePlayAgain to true
                        System.out.println("Please write an integer 0 to 25 inclusive.");
                    }
                } while (forcePlayAgain == true); //Do-while forcePlayAgain is equal to true
            //else if option is equal to the letter 'b' 
            } else if (option.equals("b")) {
                do {
                    System.out.print("Phrase to Brute Force: ");
                    message = Keyboard.nextLine(); //Saves player input to message
                    //if message is a valid string and returns true
                    if (typeVerify(message, 's') == true) {
                        forcePlayAgain = false; //Set forcePlayAgain to false
                    } else {
                        System.out.println("Please do not enter an empty string.");
                        forcePlayAgain = true; //Set forcePlayAgain to true
                    }
                } while (forcePlayAgain == true); //Do-while forcePlayAgain is equal to true
                //Repeat while b is set to 1, b is less than or equal to 26, Add one to b
                for (int b = 1; b <= 26; b++) {
                    //Send parameter message to bruteForce method and save to ciphText array
                    String[] ciphText = bruteForce(message);
                    //if b is less than 26
                    if (b < 26) {
                        System.out.println(ciphText[b]); //Display ciphText with index of b
                    //else if b is equal to 26
                    } else if (b == 26) {
                        System.out.println(ciphText[0]); //Display ciphText with index of 0
                        System.out.println(ciphText[26]); //Display ciphText with index of 26
                    }
                }
            //else if option is blank, or not equal to 'e','d','b', or 'q'
            } else if (option.equals("") || !option.equals("e") || !option.equals("d") || !option.equals("b") || !option.equals("q")) {
                System.out.println("Please write either 'e', 'd', 'b', or 'q'.");
                playAgain = true; //Set playAgain to true
            }
        } while (playAgain == true); //Do-while playAgain is equal to true
    }
    
    //Encode and decode method with parameters of msgIn(String) and shift(integer) which returns a string
    public static String encodeAndDecode(String msgIn, int shift) {
        //Variables
        String ciphText = "";
        char letter;
        //Repeat while a is set to 0, a is less than msgIn's length, Add one to a
        for (int a = 0; a < msgIn.length(); a++) {
            letter = msgIn.charAt(a); //Save msgIn char at postion a to letter
            //if letter is greater than or equal to char value 'A' and letter is less than or equal to char value 'Z'
            if (letter >= 'A' && letter <= 'Z') {
                //Cast letter plus shift to char and save to letter
                letter = (char) (letter + shift);
                //if letter is greater than char value 'Z'
                if (letter > 'Z') {
                    //Cast letter plus 26 and save to letter
                    letter = (char) (letter + 'A' - 'Z' - 1);
                //else if letter is less than char value of 'A'
                } else if (letter < 'A') {
                    //Cast letter minus 26 and save to letter
                    letter = (char) (letter - 'a' + 'z' + 1);
                }
                //Save ciphText plus letter to ciphtext
                ciphText = ciphText + letter;
            //else if letter is greater than or equal to char value 'a' and letter is less than or equal to char value 'z'
            } else if (letter >= 'a' && letter <= 'z') {
                letter = (char) (letter + shift); //Save msgIn char at postion a to letter
                //if letter is greater than char value 'z'
                if (letter > 'z') {
                    //Cast letter plus 26 and save to letter
                    letter = (char) (letter + 'a' - 'z' - 1);
                //else if letter is less than char value of 'a'
                } else if (letter < 'a') {
                    //Cast letter minus 26 and save to letter
                    letter = (char) (letter - 'a' + 'z' + 1);
                }
                //Save ciphText plus letter to ciphtext
                ciphText = ciphText + letter;
            } else {
                //Save ciphText plus letter to ciphtext
                ciphText = ciphText + letter;
            }
        }
        return ciphText; //return ciphtext to main
    }

    //Brute force method with parameters of msgIn(String) which returns an array of strings
    public static String[] bruteForce(String msgIn) {
        //Variables
        int bestArray = 0, score, bestScore = 0, loc;
        String bruteForceArray[] = new String[27];
        String dictionary[] = {"the ", "and ", "you ", "that ", "was ", "for ", 
            "are ", "with", "his", "they", "this", "have", "from", "what", "when"};
        
        //Array to print out all shifts
        //Repeat while a is set to 0, a is less than 26, Add one to a
        for (int a = 0; a < 26; a++) {
            //Send parameter msgIn and a times -1 to bruteForce method and save to printShift
            String printShift = encodeAndDecode(msgIn, a * -1);
            //if a is equal to 0
            if (a == 0) {
                //Set bruteForceArray with index of a to printShift
                bruteForceArray[a] = ("Shifted right 26: " + printShift);
            } else {
                //Set bruteForceArray with index of a to printShift
                bruteForceArray[a] = ("Shifted right " + a + ": " + printShift);
            }
        }
        //See if sentence has the words in the dictionary
        //Repeat while b is set to 0, a is less than 26, Add one to b
        for (int b = 0; b < 26; b++) {
            score = 0; //Set score to 0
             //Repeat while c is set to 0, a is less than length of dictionary array, Add one to c
            for (int c = 0; c < dictionary.length; c++) {
                loc = 0;//Set loc to 0
                /*while lower case bruteForceArray with index of b has an index 
                of dictionary with index of c and starting at loc is greater than -1*/
                while (bruteForceArray[b].toLowerCase().indexOf(dictionary[c], loc) > -1) {
                    score++; //Add one to score
                    //if score is greater than to bestScore
                    if (score > bestScore) {
                        bestArray = b; //Set bestArray to b
                    }
                    //Set loc to where the last index left off
                    loc = bruteForceArray[b].toLowerCase().indexOf(dictionary[c], loc) + dictionary[c].length();
                }
            }
        }
        //if bestArray is equal to 0
        if (bestArray == 0) {
            //Save bruteForceArray with index of 26 to best decoded message
            bruteForceArray[26] = "The best decode was with key 26\nDecoded message is: " + encodeAndDecode(msgIn, 0 * -1);
        } else {
            //Save bruteForceArray with index of 26 to player inputted since there was no recognition
            bruteForceArray[26] = "The best decode was with key " + bestArray + "\nDecoded message is: " + encodeAndDecode(msgIn, bestArray * -1);
        }
        return bruteForceArray; //return bruteForceArray to main
    }

    //Type verify method with parameters of token(String) and type(char) which returns boolean 
    public static boolean typeVerify(String token, char type) {
        //Variables
        boolean verified = true;
        //if type is equal to 's'
        if (type == 's') {
            //if token is blank
            if (token.equals("")) {
                verified = false; //Set verified to false
            } else {
                verified = true; //Set verified to true
            }
        //else if type is equal to i
        } else if (type == 'i') {
            
            try {
                //Parse token
                Integer.parseInt(token);
                verified = true; //Set verified to true
            //if token cannot be parsed and throws NumberFormatException
            } catch (NumberFormatException e) {
                verified = false; //Set verified to false
            }
            //if verified is equal to true
            if (verified == true) {
                //if token is greater than or equal to 0 and is less than or equal to 25
                if (Integer.parseInt(token) >= 0 && Integer.parseInt(token) <= 25) {
                    verified = true; //Set verified to true
                } else {
                    verified = false; //Set verified to false
                }
            }
        }
        return verified; //return verified to main
    }
}
