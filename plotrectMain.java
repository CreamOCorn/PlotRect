/*Date: November 7, 2022
 *Author: An Ha
 *Course: ICS4U
 *Description: The program allows the user to make two rectangles and calculates 
 *the perimeter and area of the shape (overlapped/touching or not) then present it on a plane.
 */

package objectsProject;

import java.util.Scanner;

public class plotrectMain {
	public static void main(String[] args) {
		//variable declaration
		int dimensions = 12;
		String[][] plane = new String [dimensions][dimensions]; //the cartesian plane is initially a 10x10 2D array to show as example for tutorial 
		Rectangle r1 = new Rectangle(); //WOOOO THE RECTANGLES WOOO
		Rectangle r2 = new Rectangle();
		String explain, choice, rectChoice; //just variables to hold user input
		boolean loop = true, menuLoop = true, modify = true; //the variables that loop menus for validation checks
		Scanner input = new Scanner (System.in);

		//introduce the program and explain what it does. users may skip it
		System.out.println("Welcome to PlotRect!\n");
		while (loop) {
			System.out.println("Do you need an explanation about how PlotRect works? (Y/N)");
			explain = input.nextLine().trim().toLowerCase(); 
			if (explain.equals("y") || explain.equals("yes")) {
				System.out.println("\nThis is a cartesian plane with numbers ranging from 1-10.\n");
				fillPlane(plane, dimensions);
				displayPlane(plane, dimensions);
				System.out.println("You will be able to change the dimensions of this grid to any size you want (between 3 and 40).");
				System.out.println("Then, you will plot 2 rectangles out on this grid.\n" 
									+ "The rectangles will be created by you inputting the coordinates of their bottom left corner, their length, and their width.");
				System.out.println("You will be able to see:\n" 
									+ "\t- Information about the two rectangles (the attributes you input + their individual areas and perimeters)\n"
									+ "\t- Any overlaps (+ the dimensions of the overlapped rectangle if there are overlaps)\n"
									+ "\t- Any touching sides (+ the length of the rectangle's contact if there are side by side)\n"
									+ "\t- The total area/perimeter taken up on the grid (if both rectangles together were all one big shape)");
				System.out.println("And, you can modify the rectangles however you want. Feel free to play around!");
				System.out.println("\nSo, let's get started.");
				loop = false;
			} else if (explain.equals("n") || explain.equals("no")) {
				System.out.println("Ok! Let's get into it then.");
				loop = false;
			} else {
				System.out.println("Sorry, \"" + explain + "\" is not a valid answer. Please select from \"Y\" or \"N\".");
				input = new Scanner (System.in);
			}
			System.out.println();
		}
		
		//initialize cartesian plane
		loop = true;
		while (loop) {
			System.out.println("How long/wide do you want the dimensions of the grid to be? (For example, inputting the number 10 will create a 10 x 10)");
			dimensions = input.nextInt();
			if (dimensions < 3) {
				System.out.println("You cannot have a grid that small. Please try a larger number.");
			} else if (dimensions > 40) {
				System.out.println("This grid might be too large to display in your console. Please try a smaller number.");
			} else {
				System.out.println("You have made a " + dimensions + " x " + dimensions + " grid.\n");
				loop = false;
			}
		}
		dimensions += 2; //the +2 to account for the x and y axes
		plane = new String [dimensions][dimensions];
		fillPlane(plane, dimensions);
		displayPlane(plane, dimensions);

		//initialize the two rectangles based on what corner they start from, their length, and width
		setRect1(r1, dimensions);
		drawRect1(plane, r1, dimensions);
		displayPlane(plane, dimensions);
		setRect2(r2, dimensions);
		drawRect2(plane, r2, dimensions);
		
		//menu that shows the rectangles, their individual information, and information on the plane. Then, allows the users to modify the rectangles.
		loop = true;
		while (loop) {
			//display the plane
			fillPlane(plane, dimensions);
			drawRect1(plane, r1, dimensions);
			drawRect2(plane, r2, dimensions);
			displayPlane(plane, dimensions);
			//give information about the shapes shown on the plane
			System.out.println("Rectangle #1:\n" + r1.toString());
			System.out.println("Rectangle #2:\n" + r2.toString());
			System.out.println("\nOverlap: " + Rectangle.overlap(r1, r2));
			if (Rectangle.overlap(r1, r2)) {
				Rectangle.setOverlapDimensions(r1, r2);
				System.out.println("Dimensions of Overlapped Rectangle: " + Rectangle.getOverlapDimensions());
			}
			System.out.println("Side By Side: " + Rectangle.sideBySide(r1, r2));
			if (Rectangle.sideBySide(r1, r2)) {
				System.out.println("Length of Touching Sides: " + Rectangle.sideBySideLength(r1, r2));
			}
			System.out.println("\nTotal Area Occupied On Plane: " + Rectangle.getArea(r1, r2));
			System.out.println("Total Shape Perimeter On Plane: " + Rectangle.getPerim(r1, r2));
			//let the user either modify a rectangle or exit the program
			menuLoop = true;
			input = new Scanner (System.in);
			while (menuLoop) { 
				System.out.println("\n1) Change Rectangle #1\n"
								+ "2) Change Rectangle #2\n"
								+ "3) Exit the program");
				rectChoice = input.next().trim();
				if (rectChoice.equals("1") || rectChoice.equals("2")) { //choose to modify a rectangle
					menuLoop = false;
					modify = true;
					while (modify) {
						System.out.println("1) Change Starting Coordinate (The entire rectangle placement)\n"
							+ "2) Change Length\n"
							+ "3) Change Width");
						choice = input.next().trim();
						if (choice.equals("1")) { //change coordinates
							modify = false;
							if (rectChoice.equals("1")) {
								setRect1(r1, dimensions);
							} else {
								setRect2(r2, dimensions);
							}
						} else if (choice.equals("2")){ //change length
							modify = false;
							if (rectChoice.equals("1")) {
								setRect1Length(r1, dimensions);
								System.out.println();
							} else {
								setRect2Length(r2, dimensions);
								System.out.println();
							}
						} else if (choice.equals("3")){ //change width
							modify = false;
							if (rectChoice.equals("1")) {
								setRect1Width(r2, dimensions);
								System.out.println();
							} else {
								setRect2Width(r2, dimensions);
								System.out.println();
							}
						} else { //keep looping if they didn't choose an option
							System.out.println("That is not a valid input. Please select option 1, 2, or 3.");
						}
					}
				} else if (rectChoice.equals("3")) { //exit program
					menuLoop = false;
					loop = false;
					System.out.println("See you next time in PlotRect!");
				} else { //keep looping if they didn't choose an option
					System.out.println("That is not a valid answer. Please select option 1, 2, or 3.");
				}
			}
		}
	}

	/* Pre: Cartesian Plane 2D Array, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Fills the cartesian plane 2D array with a grid*/
	public static void fillPlane (String[][] plane, int dimensions) {
		//fill the entire thing with a grid
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
				plane[i][j] = "─┼─";
			}
		}
		//these two are blanks because this is where the cartesian plane stops
		plane[dimensions-2][0] = "  "; 
		plane[dimensions-1][dimensions-1] = "  ";
		//lay down the x axis
		for (int i = 1; i < dimensions ; i++) {
			plane[dimensions-2][i] = "─┼─";
			if (i < 10) { // allocates more space on x axis for 2-digit numbers
				plane[dimensions-1][i-1] = (i-1) + "  ";
			} else {
				plane[dimensions-1][i-1] = (i-1) + " ";
			}
		}
		plane[dimensions-1][0] = "  0  "; //the 0 needs extra space to align in the console
		plane[dimensions-2][1] = "└─"; //the bottom left corner where the x and y axes meet 
		//lay down the y axis
		for (int i = 0; i < dimensions-2; i++) {
			plane[i][dimensions-2] = "" + (i+1);	
			if (i > 8) { // allocates more space on y axis for 2-digit numbers
				plane[i][dimensions-1] = "┼─";	
			} else {
				plane[i][dimensions-1] = "─┼─";
			}
		}
	}

	/* Pre: Cartesian Plane 2D Array, rectangle 1, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Plots rectangle 1 on the cartesian plane 2D array*/
	public static void drawRect1 (String[][]plane, Rectangle r1, int dimensions) {
		//variable declaration
		int botLeftX = r1.getBotLeftX();
		int botLeftY = r1.getBotLeftY();
		int topLeftX = r1.getTopLeftX();
		int topLeftY = r1.getTopLeftY();
		int topRightX = r1.getTopRightX();
		int topRightY = r1.getTopRightY();
		int botRightX = r1.getBotRightX();
		int botRightY = r1.getBotRightY();

		//set corners
		plane[botLeftY][botLeftX] = " ╚═";
		plane[topLeftY][topLeftX] = " ╔═";
		plane[topRightY][topRightX] = "═╗ ";
		plane[botRightY][botRightX] = "═╝ ";

		//set vertical lines of the rectangle
		for (int i = botLeftY + 1; i < topLeftY; i ++) {
			plane[i][botLeftX] = " ║ ";
			plane[i][botRightX] = " ║ ";
		}

		//set the horizontal lines of the rectangle
		for (int i = topLeftX + 1; i < topRightX; i ++) {
			plane[topLeftY][i] = "═══";
			plane[botLeftY][i] = "═══";
		}
	}
	/* Pre: Cartesian Plane 2D Array, rectangle 2, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Plots rectangle 2 on the cartesian plane 2D array*/
	public static void drawRect2 (String[][]plane, Rectangle r2, int dimensions) {
		//variable declaration
		int botLeftX = r2.getBotLeftX();
		int botLeftY = r2.getBotLeftY();
		int topLeftX = r2.getTopLeftX();
		int topLeftY = r2.getTopLeftY();
		int topRightX = r2.getTopRightX();
		int topRightY = r2.getTopRightY();
		int botRightX = r2.getBotRightX();
		int botRightY = r2.getBotRightY();

		//set corners (account for any overlaps with rectangle 1)
		if (plane[botLeftY][botLeftX].equals(" ╔═") || plane[botLeftY][botLeftX].equals(" ║ ")) {
			plane[botLeftY][botLeftX] = " ╠═";
		} else if (plane[botLeftY][botLeftX].equals("═╝ ") || plane[botLeftY][botLeftX].equals("═══")) {
				plane[botLeftY][botLeftX] = "═╩═";
		} else if (plane[botLeftY][botLeftX].equals("═╗ ")) {
			plane[botLeftY][botLeftX] = "═╬═";
		} else {
			plane[botLeftY][botLeftX] = " ╚═";
		}
		
		if (plane[topLeftY][topLeftX].equals(" ╚═") || plane[topLeftY][topLeftX].equals(" ║ ")) {
			plane[topLeftY][topLeftX] = " ╠═";
		} else if (plane[topLeftY][topLeftX].equals("═╗ ") || plane[topLeftY][topLeftX].equals("═══")) {
			plane[topLeftY][topLeftX] = "═╦═";
		} else if (plane[topLeftY][topLeftX].equals("═╝ ")) {
			plane[topLeftY][topLeftX] = "═╬═";
		} else {
			plane[topLeftY][topLeftX] = " ╔═";
		}

		if (plane[topRightY][topRightX].equals("═╝ ") || plane[topRightY][topRightX].equals(" ║ ")) {
			plane[topRightY][topRightX] = "═╣ ";
		} else if (plane[topRightY][topRightX].equals(" ╔═") || plane[topRightY][topRightX].equals("═══")) {
				plane[topRightY][topRightX] = "═╦═";
		} else if (plane[topRightY][topRightX].equals(" ╚═")) {
			plane[topRightY][topRightX] = "═╬═";
		} else {
			plane[topRightY][topRightX] = "═╗ ";
		}

		if (plane[botRightY][botRightX].equals("═╗ ") || plane[botRightY][botRightX].equals(" ║ ")) {
			plane[botRightY][botRightX] = "═╣ ";
		} else if (plane[botRightY][botRightX].equals(" ╚═") || plane[botRightY][botRightX].equals("═══")) {
				plane[botRightY][botRightX] = "═╩═";
		} else if (plane[botRightY][botRightX].equals(" ╔═")) {
			plane[botRightY][botRightX] = "═╬═";
		} else {
			plane[botRightY][botRightX] = "═╝ ";
		}
		
		//set vertical lines of the rectangle (account for any overlaps with rectangle 1)
		for (int i = botLeftY + 1; i < topLeftY; i ++) {
			if (plane[i][botLeftX].equals("═══")) {
				plane[i][botLeftX] = "═╬═";
			} else if (plane[i][botLeftX].equals(" ╔═") || plane[i][botLeftX].equals(" ╚═")) {
				plane[i][botLeftX] = " ╠═";
			} else if (plane[i][botLeftX].equals("═╗ ") || plane[i][botLeftX].equals("═╝ ")) {
				plane[i][botLeftX] = "═╣ ";
			} else {
				plane[i][botLeftX] = " ║ ";
			}

			if (plane[i][botRightX].equals("═══")) {
				plane[i][botRightX] = "═╬═";
			} else if (plane[i][botRightX].equals(" ╔═") || plane[i][botRightX].equals(" ╚═")) {
				plane[i][botRightX] = " ╠═";
			} else if (plane[i][botRightX].equals("═╗ ") || plane[i][botRightX].equals("═╝ ")) {
				plane[i][botRightX] = "═╣ ";
			} else {
				plane[i][botRightX] = " ║ ";
			}
		}

		//set the horizontal lines of the rectangle (account for any overlaps with rectangle 1)
		for (int i = topLeftX + 1; i < topRightX; i ++) {
			if (plane[topLeftY][i].equals(" ║ ")) {
				plane[topLeftY][i] = "═╬═";
			} else if (plane[topLeftY][i].equals(" ╔═") || plane[topLeftY][i].equals("═╗ ")) {
				plane[topLeftY][i] = "═╦═";
			} else if (plane[topLeftY][i].equals("═╝ ") || plane[topLeftY][i].equals(" ╚═")) {
				plane[topLeftY][i] = "═╩═";
			} else {
				plane[topLeftY][i] = "═══";
			}
			
			if (plane[botLeftY][i].equals(" ║ ")) {
				plane[botLeftY][i] = "═╬═";
			} else if (plane[botLeftY][i].equals(" ╔═") || plane[botLeftY][i].equals("═╗ ")) {
				plane[botLeftY][i] = "═╦═";
			} else if (plane[botLeftY][i].equals("═╝ ") || plane[botLeftY][i].equals(" ╚═")) {
				plane[botLeftY][i] = "═╩═";
			} else {
				plane[botLeftY][i] = "═══";
			}
		}
	}

	/* Pre: Cartesian Plane 2D Array, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Displays the contents of the 2D array in the cartesian plane format*/
	public static void displayPlane (String[][] plane, int dimensions) {
		//print out the y axis alongside the cartesian plane (backwards so numbers descend down)
		for (int i = dimensions-3; i >= 0; i--) {
			System.out.print(plane[i][dimensions-2]);
			System.out.print(plane[i][dimensions-1]);
			for (int j = 0; j < dimensions-2; j++) {
				System.out.print(plane[i][j]);
			}
			System.out.println();
		}
		//print out the x axis after printing out the plane
		for (int i = 0; i < dimensions; i++) {
			System.out.print(plane[dimensions-2][i]);
		}
		System.out.println();
		//print out the x-axis' numbers at the very last line
		for (int i = 0; i < dimensions; i++) {
			System.out.print(plane[dimensions-1][i]);
		}
		System.out.println("\n");
	}
	
	/* Pre: rectangle 1, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Allows the user to set rectangle 1's length*/
	public static void setRect1Length (Rectangle r1, int dimensions) {
		//variable declaration
		int value;
		boolean loop = true;
		Scanner input = new Scanner (System.in);

		while (loop) {
			System.out.println("What is the length of Rectangle #1? (vertical distance)");
			value = input.nextInt();
			if (value > (dimensions-3) - r1.getBotLeftY()) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! That length will cause the rectangle to go off of the grid. Please try a smaller number. "
									+ "(Less than or equal to " + ((dimensions-3) - r1.getBotLeftY())  + ")");
			} else if (value < 1) {
				System.out.println("Sorry! A rectangle cannot have a length less than 1. Please try again.");
			} else {
				r1.setLength(value);
				loop = false;
			}
		}
		r1.setRect();
	}

	/* Pre: Rectangle 1, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Allows the user to set rectangle 1's width*/
	public static void setRect1Width (Rectangle r1, int dimensions) {
		//variable declaration
		int value;
		boolean loop = true;
		Scanner input = new Scanner (System.in);

		while (loop) {
			System.out.println("What is the width of Rectangle #1? (horizontal distance)");
			value = input.nextInt();
			if (value > (dimensions-3) - r1.getBotLeftX()) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! That width will cause the rectangle to go off of the grid. Please try a smaller number. "
									+ "(Less than or equal to " + ((dimensions-3) - r1.getBotLeftX())  + ")");
			} else if (value < 1) {
				System.out.println("Sorry! A rectangle cannot have a length less than 1. Please try again.");
			} else {
				r1.setWidth(value);
				loop = false;
			}
		}
		r1.setRect();
	}
	/* Pre: Rectangle 1, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Allows the user to set rectangle 1's bottom left corner's coordinates, length, and width*/
	public static void setRect1 (Rectangle r1, int dimensions) {
		//variable declaration
		int value;
		boolean loop = true;
		Scanner input = new Scanner (System.in);
		
		while (loop) {
			System.out.println("What is the x coordinate of Rectangle #1's bottom left corner?");
			value = input.nextInt();
			if (value < 1 || value > dimensions-3) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! The grid only allows for an x-value between 1-" + (dimensions-3) + " to make a valid rectangle. Please try again.");
			} else {
				r1.setBotLeftX(value);
				loop = false;
			}
		}
		loop = true;
		while (loop) {
			System.out.println("What is the y coordinate of Rectangle #1's bottom left corner?");
			value = input.nextInt();
			if (value < 1 || value > dimensions-3) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! The grid only allows for a y-value between 1-" + (dimensions-3) + " to make a valid rectangle. Please try again.");
			} else {
				r1.setBotLeftY(value);
				loop = false;
			}
		}
		
		setRect1Width(r1, dimensions);
		setRect1Length(r1, dimensions);
		
		r1.setRect();
		System.out.println();
	}
	
	/* Pre: Rectangle 2, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Allows the user to set rectangle 2's length*/
	public static void setRect2Length (Rectangle r2, int dimensions) {
		//variable declaration
		int value;
		boolean loop = true;
		Scanner input = new Scanner (System.in);

		while (loop) {
			System.out.println("What is the length of Rectangle #2? (vertical distance)");
			value = input.nextInt();
			if (value > (dimensions-3) - r2.getBotLeftY()) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! That length will cause the rectangle to go off of the grid. Please try a smaller number. "
									+ "(Less than or equal to " + ((dimensions-3) - r2.getBotLeftY())  + ")");
			} else if (value < 1) {
				System.out.println("Sorry! A rectangle cannot have a length less than 1. Please try again.");
			} else {
				r2.setLength(value);
				loop = false;
			}
		}
		r2.setRect();
	}
	/* Pre: Rectangle 2, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Allows the user to set rectangle 2's width*/
	public static void setRect2Width (Rectangle r2, int dimensions) {
		//variable declaration
		int value;
		boolean loop = true;
		Scanner input = new Scanner (System.in);

		while (loop) {
			System.out.println("What is the width of Rectangle #1? (horizontal distance)");
			value = input.nextInt();
			if (value > (dimensions-3) - r2.getBotLeftX()) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! That width will cause the rectangle to go off of the grid. Please try a smaller number. "
									+ "(Less than or equal to " + ((dimensions-3) - r2.getBotLeftX())  + ")");
			} else if (value < 1) {
				System.out.println("Sorry! A rectangle cannot have a length less than 1. Please try again.");
			} else {
				r2.setWidth(value);
				loop = false;
			}
		}
		r2.setRect();
	}
	/* Pre: Rectangle 2, dimensions of the cartesian plane
	 * Post: Void
	 * Action: Allows the user to set rectangle 2's bottom left corner's coordinates, length, and width*/
	public static void setRect2 (Rectangle r2, int dimensions) {
		//variable declaration
		int value;
		boolean loop = true;
		Scanner input = new Scanner (System.in);
		
		while (loop) {
			System.out.println("What is the x coordinate of Rectangle #2's bottom left corner?");
			value = input.nextInt();
			if (value < 1 || value > dimensions-3) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! The grid only allows for an x-value between 1-" + (dimensions-3) + " to make a valid rectangle. Please try again.");
			} else {
				r2.setBotLeftX(value);
				loop = false;
			}
		}
		loop = true;
		while (loop) {
			System.out.println("What is the y coordinate of Rectangle #2's bottom left corner?");
			value = input.nextInt();
			if (value < 1 || value > dimensions-3) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! The grid only allows for a y-value between 1-" + (dimensions-3) + " to make a valid rectangle. Please try again.");
			} else {
				r2.setBotLeftY(value);
				loop = false;
			}
		}

		setRect2Width(r2, dimensions);
		setRect2Length(r2, dimensions);
		
		r2.setRect();
		System.out.println();
	}
}
