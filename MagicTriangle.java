/******************************************************************************
 *MagicTriangle:
 * Magic Triangle program. User is asked to input 3 numbers into the magic triangle
 * [ ]'s. The program computes the values of the ( )'s. The [ ]'s are the sums of
 * the ( )'s around them.
 * 
 * g3muse
 * 05/10/2016
 * Example:
 * 
 * Welcome to the Magic Triangle!
 *         ( ? )
 *    [ 0 ]     [ 0 ]
 * ( ? )   [ 0 ]    ( ? )
 *
 * Enter an integer between -40 and 40 for the magic triangle [ ]'s:
 * 3
 *         ( ? )
 *    [ 3 ]     [ 0 ]
 * ( ? )   [ 0 ]    ( ? )
 * 
 * Enter an integer between -40 and 40 for the magic triangle [ ]'s:
 * 5
 *         ( ? )
 *    [ 3 ]     [ 5 ]
 * ( ? )   [ 0 ]    ( ? )
 * 
 * Enter an integer between -40 and 40 for the magic triangle [ ]'s:
 * 10
 *         ( -1 )
 *    [ 3 ]     [ 5 ]
 * ( 4 )   [ 10 ]    ( 6 )
 *
 * You entered: [3, 5, 10]
 * Answers:
 * Top:   -1
 * Left:  4
 * Right: 6
 * 
 * Would you like to try again? (1=Yes, 2=No)
 * 2
 * Thank you for playing!
 * 	
 * 
 ******************************************************************************/

import java.util.*;

public class MagicTriangle {
	
	final static int SIZE = 3;
	
	public static void main(String[] agrs){
	
		Scanner sc = new Scanner(System.in);
		
		int[] sumsInput = new int[SIZE];
		int[] solutions = new int[SIZE];
		int tryAgainInput;
		
		System.out.println("Welcome to the Magic Triangle!");
			
		do{
			
			//Clears sumsInput[]  when program is re-run using tryAgain() module
			Arrays.fill(sumsInput, 0);
				
			//Loop to display Triangle graphic and to get user's input for 3 numbers stored in sumsInput[] 
			for (int i = 0; i < SIZE; i++){
			
				//Displays ASQII triangle graphic
				displayUnsolvedTriangle(sumsInput);
				
				//Validation do/while loop
				do{		
					System.out.println("Enter an integer between -40 and 40 for the magic triangle [ ]'s:");
					while(!sc.hasNextInt()){
						System.out.println("Input Error - Make sure to only enter an integer, no letters!");
						sc.next();
					}
					//Gets user input for the magic triangle
					sumsInput[i] = sc.nextInt();
				}while(sumsInput[i] > 40 || sumsInput[i] < -40);
			}
				
			//Checks if the inputs have a solution
			if(hasSolution(sumsInput)){
				//Calculates solutions, displays a solved triangle graphic, and displays solutions.
				solutions = calculateSolution(sumsInput);
				displaySolvedTriangle(sumsInput, solutions);
				displaySolutions(sumsInput, solutions);
				
			}else{
				displayUnsolvedTriangle(sumsInput);
				System.out.println("There is no solution! :(");
			}
			
			//Validation do/while loop
			do{
				System.out.println("Would you like to try again? (1=Yes, 2=No)");
				while(!sc.hasNextInt()){
					System.out.println("Invalid input - Make sure to only enter 1 or 2");
					sc.next();
				}
				//Starts program over if user wants to try again by inputting a 1
				tryAgainInput = sc.nextInt();
			}while(tryAgainInput != 1 && tryAgainInput != 2);
				
		}while(tryAgain(tryAgainInput));	
		
		//Exit message
		System.out.println("Thank you for playing!");	
		
	}
	
	/**********************************
	 *       METHODS/FUNCTIONS        *
	 **********************************/
	
	//Displays an ASCII graphic of the magic triangle that is used as the user inputs numbers
	public static void displayUnsolvedTriangle(int[] sumsInput){
		int x = sumsInput[0];
		int y = sumsInput[1];
		int z = sumsInput[2];
		
		System.out.println("         ( ? )");
		System.out.println("    [ "+x+" ]     [ "+y+" ]");
		System.out.println("( ? )    [ "+z+" ]    ( ? )");
		System.out.println();
	}
	
	//Displays solved graphic of magic triangle
	public static void displaySolvedTriangle(int[] sumsInput, int[] solutions ){
		int x = sumsInput[0];
		int y = sumsInput[1];
		int z = sumsInput[2];
		
		int T = solutions[0];
		int L = solutions[1];
		int R = solutions[2];
		
		System.out.println("         ( "+T+" )");
		System.out.println("    [ "+x+" ]     [ "+y+" ]");
		System.out.println("( "+L+" )   [ "+z+" ]    ( "+R+" )");
		System.out.println();
	}
	
	//Checks to see if the sumOfSums is an odd number. If it is, there is no solution.
	public static boolean hasSolution(int[] sumsInput){
		boolean hasSolution;
		int sumOfSums = sumsInput[0] + sumsInput[1] + sumsInput[2];
		
		if(sumOfSums % 2 == 1){
			hasSolution = false;
		}else{
			hasSolution = true;
		}
		return hasSolution;
	}
	
	
	public static int[] calculateSolution(int[] sumsInput){
		int solutions[] = new int[SIZE];
		
		//Sum of the [ ]'s within the magic triangle
		int sumOfSums = sumsInput[0] + sumsInput[1] + sumsInput[2];
		
		//Sum of the ( )'s is = sum of the [ ]'s divided by 2
		int sumOfSolutions = sumOfSums/2;
		
		//Variables created for formula readability. T = The ( ) at the Top of the triangle, L = Left, R = Right
		int T = sumOfSolutions - sumsInput[2];
		int L = sumOfSolutions - sumsInput[1];
		int R = sumOfSolutions - sumsInput[0];
		
		//Setting the solutions into the array for one output
		solutions[0] = T;
		solutions[1] = L;
		solutions[2] = R;
		
		return solutions;
	}
	
	public static void displaySolutions(int[] sumsInput, int[] solutions){
		System.out.println("You entered: "+Arrays.toString(sumsInput));
		System.out.println("Answers:");
		System.out.println("Top:   " +solutions[0]);
		System.out.println("Left:  " +solutions[1]);
		System.out.println("Right: " +solutions[2]);
		System.out.println();
	}
	
	//Used within do/while loop to start program from the beginning
	public static boolean tryAgain(int inputAgain){
		boolean again;
		if (inputAgain == 1){
			again = true;
		}else{
			again = false;
		}
		return again;
	}
	
}