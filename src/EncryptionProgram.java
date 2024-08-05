import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EncryptionProgram {
	
	// making things private to practice security w/ coding.
	
	private Scanner scanner;
	private ArrayList<Character> list; // see the characters that we use from the ascii table
	private ArrayList<Character> shuffledList; // shuffle and hold the used ascii values
	private char character; 
	private char[] letters; 

	
	
	//constructor
	EncryptionProgram(){
		
		//initialize the variable
		scanner = new Scanner(System.in);
		list = new ArrayList();
		shuffledList = new ArrayList();
		character = ' ';

		//calling members
		newKey();
		askQuestion();
	}
	
	// want members to be private
	//ask question method
	private void askQuestion(){
		while(true) {
			System.out.println("*************************************************************");
			System.out.println("What do you want to do? ");
			System.out.println("N = new key, G = get key, E = encrypt, D = decrypt, Q = quit");
			char response = Character.toUpperCase(scanner.nextLine().charAt(0)); // we take their first letter in their response and change to upper case as well as store in in response variable 
			
			switch (response) {
			case 'N':
				newKey();
				break;
			case 'G':
				getKey();
				break;
			case 'E':
				encrypt();
				break;
			case 'D':
				decrypt();
				break;
			case 'Q':
				quit();
				break;
			default:
				System.out.println("Not a valid answer.");
			}
		}
	}
	
	// get new key (reshuffle)
	private void newKey() {
		// this member is used to get a new key to be used to encrypt the messages 
		character = ' '; // if i were to do (character = ' '+1;) it would become whatever the next ascii value is with one value above the space 
		list.clear();
		shuffledList.clear();
		
		for (int i = 32; i < 127; i++) { // 32 = space 
			list.add(Character.valueOf(character));
			character++;
		}
		
		// making a copy of list
		shuffledList = new ArrayList(list); 
		Collections.shuffle(shuffledList); // this is one way to shuffle a list 
		System.out.println("* A new key has been generated *");
	}
	
	// get the key we are using. (shows all the characters we will be using. 
	private void getKey() {
		
		System.out.println("Key: ");
		
		// normal ascii list from value 32 (space) to 126 (~)
		for(Character x: list) {
			System.out.print(x);
		}
		
		System.out.println();
		
		// shuffled ascii values from 32 -> 126
		for(Character x: shuffledList) {
			System.out.print(x);
		}
		System.out.println();
	}
	
	// takes plain text to cipher text
	private void encrypt() {
		// get user message 
		System.out.println("Enter a message to be encrypted: ");
		String message = scanner.nextLine();
		
	//	toCharArray() method is used to convert the string message into an array of characters and set the array letters to it.
		letters = message.toCharArray(); 
		
	//  iterate through both the letters array and list arrayList until you the letters match for both. 
		for (int i = 0; i < letters.length; i++) {
			
			for (int j = 0; j < list.size(); j++) {
				if(letters[i]==list.get(j)) {
					letters[i] = shuffledList.get(j);
					break;
				}
			}
		}
		System.out.println("Encrypted: ");
		for (char x: letters) {
			System.out.print(x);
		}
		System.out.println();
	}
	
	// takes cipher text to plain text
	private void decrypt() {
		
		System.out.println("Enter a message to be decrypted: ");
		String message = scanner.nextLine();
		
		letters = message.toCharArray();
		
	//  swapped shuffledList and list in the for loop compared to before^
		for (int i = 0; i < letters.length; i++) {
			
			for (int j = 0; j < shuffledList.size(); j++) {
				if(letters[i]==shuffledList.get(j)) {
					letters[i] = list.get(j);
					break;
				}
			}
		}
		System.out.println("Decrypted: ");
		for (char x: letters) {
			System.out.print(x);
		}
		System.out.println();
	}
	
	// exit program 
	private void quit() {
		
		System.out.println("Thank you, have a nice day bro!");
		System.exit(0);
	}
}
