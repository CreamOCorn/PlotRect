/*Date: November 9, 2022
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
		String explain = "", choice = "", rectChoice = ""; //just variables to hold user input
		Scanner input = new Scanner (System.in);

		//introduce the program and explain what it does. users may skip it
		System.out.println("Welcome to PlotRect!\n");
		while (!(explain.equals("n") || explain.equals("no") || explain.equals("y") || explain.equals("yes"))) {
			System.out.println("Do you need an explanation about how PlotRect works? (Y/N)");
			explain = input.nextLine().trim().toLowerCase(); 
			if (explain.equals("y") || explain.equals("yes")) {
				System.out.println("\nThis is a cartesian plane with numbers ranging from 1-10.\n");
				fillPlane(plane, dimensions);
				displayPlane(plane, dimensions);
				System.out.println("- You will be able to change the dimensions of this grid (to sizes between 3 and 40).");
				System.out.println("- Then, you will plot 2 rectangles out on this grid."); 
				System.out.println("- You will input the coordinates of their bottom left corner, their length, and their width.");
				System.out.println("\nSo, let's get started.");
			} else if (explain.equals("n") || explain.equals("no")) {
				System.out.println("Ok! Let's get into it then.");
			} else {
				System.out.println("Sorry, \"" + explain + "\" is not a valid answer. Please select from \"Y\" or \"N\".");
				input = new Scanner (System.in);
			}
			System.out.println();
		}
		
		//initialize cartesian plane
		dimensions = 0;
		while (dimensions < 3 || dimensions > 40) {
			System.out.println("How long/wide do you want the dimensions of the grid to be? (For example, inputting the number 10 will create a 10 x 10)");
			dimensions = input.nextInt();
			if (dimensions < 3) {
				System.out.println("You cannot have a grid that small. Please try a larger number.");
			} else if (dimensions > 40) {
				System.out.println("This grid might be too large to display in your console. Please try a smaller number.");
			} else {
				System.out.println("You have made a " + dimensions + " x " + dimensions + " grid.\n");
			}
		}
		dimensions += 2; //the +2 to account for the x and y axes
		plane = new String [dimensions][dimensions];
		fillPlane(plane, dimensions);
		displayPlane(plane, dimensions);

		//initialize the two rectangles based on what corner they start from, their length, and width
		System.out.println("Rectangle #1:");
		setRect(r1, dimensions);
		drawRect(plane, r1, dimensions);
		displayPlane(plane, dimensions);
		System.out.println("Rectangle #2:");
		setRect(r2, dimensions);
		drawRect(plane, r2, dimensions);
		
		//menu that shows the rectangles, their individual information, and information on the plane. Then, allows the users to modify the rectangles.
		while (!rectChoice.equals("3")) {
			//display the plane
			fillPlane(plane, dimensions);
			drawRect(plane, r1, dimensions);
			drawRect(plane, r2, dimensions);
			displayPlane(plane, dimensions);
			//give information about the shapes shown on the plane
			System.out.println("Rectangle #1:\n" + r1.toString());
			System.out.println("Rectangle #2:\n" + r2.toString());
			if (Rectangle.overlap(r1, r2)) {
				Rectangle.setOverlapDimensions(r1, r2);
				System.out.println("\nDimensions of Overlapped Rectangle: " + Rectangle.getOverlapDimensions());
			}
			if (Rectangle.sideBySide(r1, r2)) {
				System.out.println("\nLength of Touching Sides: " + Rectangle.sideBySideLength(r1, r2) + "cm");
			}
			System.out.println("\nTotal Area Occupied On Plane: " + Rectangle.getArea(r1, r2) + "cm^2");
			System.out.println("Total Shape Perimeter On Plane: " + Rectangle.getPerim(r1, r2) + "cm");
			//let the user either modify a rectangle or exit the program
			input = new Scanner (System.in);
			rectChoice = "0";
			while (!(rectChoice.equals("1") || rectChoice.equals("2") || rectChoice.equals("3"))) { 
				System.out.println("\n1) Change Rectangle #1\n"
								+ "2) Change Rectangle #2\n"
								+ "3) Exit the program");
				rectChoice = input.next().trim();
				if (rectChoice.equals("1") || rectChoice.equals("2")) { //choose to modify a rectangle
					choice = "0";
					while (!(choice.equals("1") || choice.equals("2") || choice.equals("3"))) {
						System.out.println("1) Change Starting Coordinate (The entire rectangle placement)\n"
							+ "2) Change Length\n"
							+ "3) Change Width");
						choice = input.next().trim();
						if (choice.equals("1")) { //change coordinates
							if (rectChoice.equals("1")) {
								System.out.println("\nChanging Rectangle #1:");
								setRect(r1, dimensions);
							} else {
								System.out.println("\nChanging Rectangle #2:");
								setRect(r2, dimensions);
							}
						} else if (choice.equals("2")){ //change length
							if (rectChoice.equals("1")) {
								System.out.println("\nChanging Rectangle #1:");
								setRectLength(r1, dimensions);
								System.out.println();
							} else {
								System.out.println("\nChanging Rectangle #2:");
								setRectLength(r2, dimensions);
								System.out.println();
							}
						} else if (choice.equals("3")){ //change width
							if (rectChoice.equals("1")) {
								System.out.println("\nChanging Rectangle #1:");
								setRectWidth(r1, dimensions);
								System.out.println();
							} else {
								System.out.println("\nChanging Rectangle #2:");
								setRectWidth(r2, dimensions);
								System.out.println();
							}
						} else { //keep looping if they didn't choose an option
							System.out.println("That is not a valid input. Please select option 1, 2, or 3.");
						}
					}
				} else if (rectChoice.equals("3")) { //exit program
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

	/* Pre: Rectangle temp
	 * Post: 2D Array
	 * Action: Returns a 2D array containing the coordinates of a Rectangle's four courners*/
	public static int[][] getRectCoors (Rectangle temp) {
		//our array
		int[][] rectCoors = new int [4][2];
	
		//bottom left corner
		rectCoors[0][0] = temp.getBotLeftX();
		rectCoors[0][1] = temp.getBotLeftY();
		//top left corner
		rectCoors[1][0] = temp.getTopLeftX();
		rectCoors[1][1] = temp.getTopLeftY();
		//top right corner
		rectCoors[2][0] = temp.getTopRightX();
		rectCoors[2][1] = temp.getTopRightY();
		//bottom right corner
		rectCoors[3][0] = temp.getBotRightX();
		rectCoors[3][1] = temp.getBotRightY();

		return rectCoors;
	}

	/* Pre: Cartesian Plane 2D Array, Rectangle temp, int dimensions
	 * Post: Void
	 * Action: Plots rectangle on the cartesian plane 2D array*/
	public static void drawRect (String[][]plane, Rectangle temp, int dimensions) {
		//get 2d array of coordinates from other method
		int[][] coors = getRectCoors(temp);

		//set corners (account for any overlaps)
		if (plane[coors[0][1]][coors[0][0]].equals(" ╔═") || plane[coors[0][1]][coors[0][0]].equals(" ║ ")) {
			plane[coors[0][1]][coors[0][0]] = " ╠═";
		} else if (plane[coors[0][1]][coors[0][0]].equals("═╝ ") || plane[coors[0][1]][coors[0][0]].equals("═══")) {
				plane[coors[0][1]][coors[0][0]] = "═╩═";
		} else if (plane[coors[0][1]][coors[0][0]].equals("═╗ ")) {
			plane[coors[0][1]][coors[0][0]] = "═╬═";
		} else {
			plane[coors[0][1]][coors[0][0]] = " ╚═";
		}
		
		if (plane[coors[1][1]][coors[1][0]].equals(" ╚═") || plane[coors[1][1]][coors[1][0]].equals(" ║ ")) {
			plane[coors[1][1]][coors[1][0]] = " ╠═";
		} else if (plane[coors[1][1]][coors[1][0]].equals("═╗ ") || plane[coors[1][1]][coors[1][0]].equals("═══")) {
			plane[coors[1][1]][coors[1][0]] = "═╦═";
		} else if (plane[coors[1][1]][coors[1][0]].equals("═╝ ")) {
			plane[coors[1][1]][coors[1][0]] = "═╬═";
		} else {
			plane[coors[1][1]][coors[1][0]] = " ╔═";
		}

		if (plane[coors[2][1]][coors[2][0]].equals("═╝ ") || plane[coors[2][1]][coors[2][0]].equals(" ║ ")) {
			plane[coors[2][1]][coors[2][0]] = "═╣ ";
		} else if (plane[coors[2][1]][coors[2][0]].equals(" ╔═") || plane[coors[2][1]][coors[2][0]].equals("═══")) {
				plane[coors[2][1]][coors[2][0]] = "═╦═";
		} else if (plane[coors[2][1]][coors[2][0]].equals(" ╚═")) {
			plane[coors[2][1]][coors[2][0]] = "═╬═";
		} else {
			plane[coors[2][1]][coors[2][0]] = "═╗ ";
		}

		if (plane[coors[3][1]][coors[3][0]].equals("═╗ ") || plane[coors[3][1]][coors[3][0]].equals(" ║ ")) {
			plane[coors[3][1]][coors[3][0]] = "═╣ ";
		} else if (plane[coors[3][1]][coors[3][0]].equals(" ╚═") || plane[coors[3][1]][coors[3][0]].equals("═══")) {
				plane[coors[3][1]][coors[3][0]] = "═╩═";
		} else if (plane[coors[3][1]][coors[3][0]].equals(" ╔═")) {
			plane[coors[3][1]][coors[3][0]] = "═╬═";
		} else {
			plane[coors[3][1]][coors[3][0]] = "═╝ ";
		}
		
		//set vertical lines of the rectangle (account for any overlaps)
		for (int i = coors[0][1] + 1; i < coors[1][1]; i ++) {
			if (plane[i][coors[0][0]].equals("═══")) {
				plane[i][coors[0][0]] = "═╬═";
			} else if (plane[i][coors[0][0]].equals(" ╔═") || plane[i][coors[0][0]].equals(" ╚═")) {
				plane[i][coors[0][0]] = " ╠═";
			} else if (plane[i][coors[0][0]].equals("═╗ ") || plane[i][coors[0][0]].equals("═╝ ")) {
				plane[i][coors[0][0]] = "═╣ ";
			} else {
				plane[i][coors[0][0]] = " ║ ";
			}

			if (plane[i][coors[3][0]].equals("═══")) {
				plane[i][coors[3][0]] = "═╬═";
			} else if (plane[i][coors[3][0]].equals(" ╔═") || plane[i][coors[3][0]].equals(" ╚═")) {
				plane[i][coors[3][0]] = " ╠═";
			} else if (plane[i][coors[3][0]].equals("═╗ ") || plane[i][coors[3][0]].equals("═╝ ")) {
				plane[i][coors[3][0]] = "═╣ ";
			} else {
				plane[i][coors[3][0]] = " ║ ";
			}
		}

		//set the horizontal lines of the rectangle (account for any overlaps)
		for (int i = coors[1][0] + 1; i < coors[2][0]; i ++) {
			if (plane[coors[1][1]][i].equals(" ║ ")) {
				plane[coors[1][1]][i] = "═╬═";
			} else if (plane[coors[1][1]][i].equals(" ╔═") || plane[coors[1][1]][i].equals("═╗ ")) {
				plane[coors[1][1]][i] = "═╦═";
			} else if (plane[coors[1][1]][i].equals("═╝ ") || plane[coors[1][1]][i].equals(" ╚═")) {
				plane[coors[1][1]][i] = "═╩═";
			} else {
				plane[coors[1][1]][i] = "═══";
			}
			
			if (plane[coors[0][1]][i].equals(" ║ ")) {
				plane[coors[0][1]][i] = "═╬═";
			} else if (plane[coors[0][1]][i].equals(" ╔═") || plane[coors[0][1]][i].equals("═╗ ")) {
				plane[coors[0][1]][i] = "═╦═";
			} else if (plane[coors[0][1]][i].equals("═╝ ") || plane[coors[0][1]][i].equals(" ╚═")) {
				plane[coors[0][1]][i] = "═╩═";
			} else {
				plane[coors[0][1]][i] = "═══";
			}
		}
	}

	/* Pre: String[][] plane, int dimensions 
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
	
	/* Pre: Rectangle temp, int dimensions 
	 * Post: Void
	 * Action: Allows the user to set rectangle's length*/
	public static void setRectLength (Rectangle temp, int dimensions) {
		//variable declaration
		int value = 0;
		Scanner input = new Scanner (System.in);

		while (value > (dimensions-3) - temp.getBotLeftY() || value < 1) {
			System.out.println("What is the length of the Rectangle? (vertical distance)");
			value = input.nextInt();
			if (value > (dimensions-3) - temp.getBotLeftY()) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! That length will cause the rectangle to go off of the grid. Please try a smaller number. "
									+ "(Less than or equal to " + ((dimensions-3) - temp.getBotLeftY())  + ")");
			} else if (value < 1) {
				System.out.println("Sorry! A rectangle cannot have a length less than 1. Please try again.");
			} else {
				temp.setLength(value);
			}
		}
		temp.setRect();
	}

	/* Pre: Rectangle temp, int dimensions 
	 * Post: Void
	 * Action: Allows the user to set rectangle's width*/
	public static void setRectWidth (Rectangle temp, int dimensions) {
		//variable declaration
		int value = 0;
		Scanner input = new Scanner (System.in);

		while (value > (dimensions-3) - temp.getBotLeftX() || value < 1) {
			System.out.println("What is the width of the Rectangle? (horizontal distance)");
			value = input.nextInt();
			if (value > (dimensions-3) - temp.getBotLeftX()) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! That width will cause the rectangle to go off of the grid. Please try a smaller number. "
									+ "(Less than or equal to " + ((dimensions-3) - temp.getBotLeftX())  + ")");
			} else if (value < 1) {
				System.out.println("Sorry! A rectangle cannot have a length less than 1. Please try again.");
			} else {
				temp.setWidth(value);
			}
		}
		temp.setRect();
	}
	/* Pre: Rectangle temp, int dimensions
	 * Post: Void
	 * Action: Allows the user to set rectangle's bottom left corner's coordinates, length, and width*/
	public static void setRect (Rectangle temp, int dimensions) {
		//variable declaration
		int value = 0;
		Scanner input = new Scanner (System.in);
		
		while (value < 1 || value > dimensions-3) {
			System.out.println("What is the x coordinate of the Rectangle's bottom left corner?");
			value = input.nextInt();
			if (value < 1 || value > dimensions-3) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! The grid only allows for an x-value between 1-" + (dimensions-3) + " to make a valid rectangle. Please try again.");
			} else {
				temp.setBotLeftX(value);
			}
		}
		value = 0;
		while (value < 1 || value > dimensions-3) {
			System.out.println("What is the y coordinate of the Rectangle's bottom left corner?");
			value = input.nextInt();
			if (value < 1 || value > dimensions-3) { //alerts the user if their input is invalid, then lets them try again
				System.out.println("Sorry! The grid only allows for a y-value between 1-" + (dimensions-3) + " to make a valid rectangle. Please try again.");
			} else {
				temp.setBotLeftY(value);
			}
		}
		
		setRectWidth(temp, dimensions);
		setRectLength(temp, dimensions);
		
		temp.setRect();
		System.out.println();
	}
}