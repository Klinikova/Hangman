import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman {
	/* Main method */
	public static void main(String[] args) throws IOException {
            // Get File from local folder and open the file.
            File file = new File("C:/Users/PolinaYoga/Documents/NetBeansProjects/Hangman/words.txt");
            // Read in the File
            Scanner input = new Scanner(file);
            // Create new list of words to store contents of the file
            List<String> words = new ArrayList<>();
            //String words = words.equalsIgnoreCase();
            // Add all words from File to new List
            while (input.hasNextLine()) {
                words.add(input.nextLine()); 
              }
            
                    
            // Parse List to new Array
              String[] arr = words.toArray(new String[0]);
             
            // Capture random Word from Array and set to upperCase
              final String word = (arr[(int) (Math.random() * arr.length)]).toUpperCase();
              
            //System.out.println(word);
            // Convert "Word" to ['W', 'o', 'r', 'd'] (array of chars)
              char[] wordToChar = word.toCharArray();
            
              //System.out.println(wordToChar);
              // Replace all chars in Word with ****
              char[] blanks = fillAsterisksCharArray(wordToChar);
              System.out.println(blanks);
              
              // While there are **** in blanks keep guessing
              while(!isWordFinish(blanks))
              {
                 //Get Input from user and set to upperCase
                char guess = Character.toUpperCase(getGuess(blanks));
                
                //System.out.println(guess);
                // Is the Guessed letter found in the Word?
                boolean isCorrect = isCorrectGuess(wordToChar, blanks, guess);
                
                if(isCorrect)
                {
                    int index = word.indexOf(guess);
                   while (index >= 0) {
                       
                //System.out.println(index);
                       blanks[index] = guess;
                       index = word.indexOf(guess, index + 1);
                   }
                   // Print: user guessed successfully
                   System.out.println("Congratulations! Your guess is correct!"); 
                   
                }
                else
                {
              // Print: user guess incorrectly
                System.out.println("Your guess is incorrect!"); 
                }
                System.out.println(blanks);   
              }
              // Print: congratulations
              System.out.println("Congratulations! You win the game!"); 
	}

	/** fillAsterisks initializes a list with asterisks */
	public static void fillAsterisks(char[] list) {
		for (int i = 0; i < list.length; i++) {
			list[i] = '*';
		}
                
	}
        
        public static char[] fillAsterisksCharArray(char[] list) {
            char[] asterisks = new char[list.length];
            for (int i = 0; i < list.length; i++) {
                    asterisks[i] = '*';
            }
            return asterisks;
	}

	// checkGuess tests if the users guess was correct 
	public static boolean isCorrectGuess(char[] word, char[] blanks, char guess) {
		boolean correct = false;
		int message = 2;
		for (int i = 0; i < word.length; i++) {
			if (word[i] == guess) {
				correct = true;
				if (blanks[i] == guess)
					message = 1;
				else { 
					blanks[i] = guess; // the actual letter is then displayed.
					message = 0;
				}
			}
		}
		if (message > 0)
			print(message, guess);
		return correct;
	}

    /** isWordFinish
     * @param blanks
     * @return  boolean
     */
	public static boolean isWordFinish(char[] blanks) {
		for (char e: blanks) {
			if (e == '*')
                            return false;
		}
		return true;
	}

    /** print overloaded
     * @param word
     * @param missed 
     */   
	public static void print(char[] word, int missed) {
		System.out.print("The word is ");
		System.out.print(word);
		System.out.println(" You missed " + missed + " time");
	}

    /** print overloaded
     * @param m
     * @param guess 
     */
	public static void print(int m, char guess) {
		System.out.print("\t" + guess);
		switch (m) {
			case 1 : System.out.println(" is already in the word"); break;
			case 2 : System.out.println(" is wrong letter. Please try one more time.");
		}
	}

    /** getGuess prompts the user to guess one letter at a time
     * @param asterisks
     * @return  
     */
	public static char getGuess(char[] asterisks){
                
                
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a letter in word ");
		System.out.print(asterisks);
		System.out.print(" -> ");
		String g = input.next();
		return g.charAt(0);
                 
	}

    private static char[] getWord() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
