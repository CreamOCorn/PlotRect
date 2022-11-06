/*Date: November 7, 2022
 *Author: An Ha
 *Course: ICS4U
 *Description: This objects links to the "plotrectmain" program that calculates the coordinates of a rectangle's 
 4 corners, gives information about a rectangle, shows overlap between the two rectangles, shows contact between 
 the two rectangles, shows the total area on a grid, and also shows the total perimeter on a grid. 
 */

package objectsProject;

public class Rectangle {
	//variable declaration
	private int botLeftX;
	private int botLeftY;
	private int topLeftX;
	private int topLeftY;
	private int botRightX;
	private int botRightY;
	private int topRightX;
	private int topRightY;
	private int length;
	private int width;
	private static int overlapWidth;
	private static int overlapLength;
	
	//constructor 
	public Rectangle() {
		botLeftX = 0;
		botLeftY = 0;
		topLeftX = 0;
		topLeftY = 0;
		botRightX = 0;
		botRightY = 0;
		topRightX = 0;
		topRightY = 0;
		length = 0;
		width = 0;
	}

	/* Pre: New width
	 * Post: Void
	 * Action: Sets the rectangle's width into the new inputted width*/
	public void setWidth(int newWidth) {
		width = newWidth;
	}

	/* Pre: Null
	 * Post: Width
	 * Action: Returns the rectangle's width to the user*/
	public int getWidth() {
		return width;
	}

	/* Pre: New length
	 * Post: Void
	 * Action: Sets the rectangle's length into the new inputted length*/
	public void setLength(int newLength) {
		length = newLength;
	}

	/* Pre: Null
	 * Post: Length
	 * Action: Returns the rectangle's length to the user*/
	public int getLength() {
		return length;
	}

	/* Pre: Bottom left corner x coordinate
	 * Post: Void
	 * Action: Set the bottom left corner's x coordinates to the inputted value*/
	public void setBotLeftX(int xValue) {
		botLeftX = xValue-1;
	}

	/* Pre: Bottom left corner y coordinate
	 * Post: Void
	 * Action: Set the bottom left corner's y coordinates to the inputted value*/
	public void setBotLeftY(int yValue) {
		botLeftY = yValue-1;
	}

	/* Pre: Null
	 * Post: Void
	 * Action: Set the top left, top right, and bottom right coordinates of a rectangle*/
	public void setRect() {
		topLeftX = botLeftX;
		topLeftY = botLeftY + length;
		topRightX = botLeftX + width;
		topRightY = botLeftY + length;
		botRightX = botLeftX + width;
		botRightY = botLeftY;
	}

	/* Pre: Null
	 * Post: Bottom left corner x coordinate
	 * Action: Returns the bottom left corner's x coordinates*/
	public int getBotLeftX() {
		return botLeftX;
	}
	/* Pre: Null
	 * Post: Bottom left corner y coordinate
	 * Action: Returns the bottom left corner's y coordinates*/
	public int getBotLeftY() {
		return botLeftY;
	}

	/* Pre: Null
	 * Post: Top left corner x coordinate
	 * Action: Returns the top left corner's x coordinates*/
	public int getTopLeftX() {
		return topLeftX;
	}
	/* Pre: Null
	 * Post: Top left corner x coordinate
	 * Action: Returns the top left corner's y coordinates*/
	public int getTopLeftY() {
		return topLeftY;
	}

	/* Pre: Null
	 * Post: Top right corner x coordinate
	 * Action: Returns the top right corner's x coordinates*/
	public int getTopRightX() {
		return topRightX;
	}
	/* Pre: Null
	 * Post: Top right corner y coordinate
	 * Action: Returns the top right corner's y coordinates*/
	public int getTopRightY() {
		return topRightY;
	}

	/* Pre: Null
	 * Post: Bottom right corner x coordinate
	 * Action: Returns the bottom right corner's x coordinates*/
	public int getBotRightX() {
		return botRightX;
	}
	/* Pre: Null
	 * Post: Bottom left corner x coordinate
	 * Action: Returns the bottom right corner's x coordinates*/
	public int getBotRightY() {
		return botRightY;
	}

	/* Pre: Null
	 * Post: Bottom left corner's coordinates, length, width, area, and perimeter
	 * Action: Returns a string of a rectangle's information*/
	public String toString() {
		String info = "Bottom Left Coordinate: (" + (botLeftX+1) + ", " + (botLeftY+1) + "), Length: " + length + 
						", Width: " + width + ", Area: " + (length*width) + ", Perimeter: " + ((2*length) + (2*width));
		return info;
	}

	/* Pre: Rectangle 1 and Rectangle 2
	 * Post: Boolean of whether the two rectangles overlap or not
	 * Action: Checks if the rectangles overlap. Returns true if they do.*/
	public static boolean overlap(Rectangle r1, Rectangle r2) {
		//if the two rectangles never cross over each other or are just side by side, they are not overlapping
		if (r1.getTopRightY() <= r2.getBotLeftY()
		  || r1.getBotLeftY() >= r2.getTopRightY()
		  || r1.getTopRightX() <= r2.getBotLeftX()
		  || r1.getBotLeftX() >= r2.getTopRightX()) {
			return false;
		}
		return true;
	}

	/* Pre: Rectangle 1 and Rectangle 2
	 * Post: Void
	 * Action: Sets the length and width of the mini-rectangle caused by an overlap*/
	public static void setOverlapDimensions (Rectangle r1, Rectangle r2) {
		//sets the length and width of the rectangle created by overlap depending on if...

		//r1 is inside of r2
		if (r1.getBotLeftX() >= r2.getBotLeftX() && 
			r1.getBotLeftY() >= r2.getBotLeftY() &&
			r1.getTopLeftX() >= r2.getTopLeftX() &&
			r1.getTopLeftY() <= r2.getTopLeftY() &&
			r1.getTopRightX() <= r2.getTopRightX() &&
			r1.getTopRightY() <= r2.getTopRightY() &&
			r1.getBotRightX() <= r2.getBotRightX() &&
			r1.getBotRightY() >= r2.getBotRightY()) {
			overlapWidth = r1.getWidth();
			overlapLength = r1.getLength();
		//r2 is inside of r1
		} else if  (r2.getBotLeftX() >= r1.getBotLeftX() && 
					r2.getBotLeftY() >= r1.getBotLeftY() &&
					r2.getTopLeftX() >= r1.getTopLeftX() &&
					r2.getTopLeftY() <= r1.getTopLeftY() &&
					r2.getTopRightX() <= r1.getTopRightX() &&
					r2.getTopRightY() <= r1.getTopRightY() &&
					r2.getBotRightX() <= r1.getBotRightX() &&
					r2.getBotRightY() >= r1.getBotRightY()) {
			overlapWidth = r2.getWidth();
			overlapLength = r2.getLength();
		//r1 is hortizontally intersecting through r2
		} else if (r1.getBotLeftX() < r2.getBotLeftX() && r1.getBotRightX() > r2.getBotRightX()) {
			overlapWidth = r2.getTopRightX() - r2.getTopLeftX();
			//r1 is higher up
			if (r1.getTopLeftY() > r2.getTopLeftY()) {
				overlapLength = r2.getTopLeftY() - r1.getBotLeftY();
			//r1 is lower down
			} else if (r1.getBotLeftY() < r2.getBotLeftY()) {
				overlapLength = r1.getTopLeftY() - r2.getBotLeftY();
			//r1 is contained within r2
			} else {
				overlapLength = r1.getTopLeftY() - r1.getBotLeftY();
			}
		//r2 is hortizontally intersecting through r1
		} else if (r2.getBotLeftX() < r1.getBotLeftX() && r2.getBotRightX() > r1.getBotRightX()) {
			overlapWidth = r1.getTopRightX() - r1.getTopLeftX();
			//r2 is higher up
			if (r2.getTopLeftY() > r1.getTopLeftY()) {
				overlapLength = r1.getTopLeftY() - r2.getBotLeftY();
			//r2 is lower down
			} else if (r2.getBotLeftY() < r2.getBotLeftY()) {
				overlapLength = r2.getTopLeftY() - r1.getBotLeftY();
			//r2 is contained within r1
			} else {
				overlapLength = r2.getTopLeftY() - r2.getBotLeftY();
			}
		//r1 is on the left
		} else if (r1.getTopRightX() < r2.getTopRightX()) {
			//r1 engulfs r2
			if (r1.getTopRightY() > r2.getTopLeftY() && r1.getBotRightY() < r2.getBotLeftY()) {
				overlapWidth = r1.getTopRightX() - r2.getTopLeftX();
				overlapLength = r2.getTopLeftY() - r2.getBotLeftY();
			//r2 engulfs r1
			} else if (r2.getTopLeftY() > r1.getTopRightY() && r2.getBotLeftY() < r1.getBotRightY()) {
				overlapWidth = r2.getTopLeftX() - r1.getTopRightX();
				overlapLength = r1.getTopRightY() - r2.getBotRightY();
			//r1 top right corner overlaps with r2 bottom left corner
			} else if (r1.getTopRightX() > r2.getBotLeftX() && r1.getTopRightY() > r2.getBotLeftY()) {
				overlapWidth = r1.getTopRightX() - r2.getBotLeftX();
				overlapLength = r1.getTopRightY() - r2.getBotLeftY();
			//r1 bottom right corner overlaps with r2 top left corner
			} else if (r1.getBotRightX() > r2.getBotLeftX() && r1.getBotRightY() < r2.getTopLeftY()) {
				overlapWidth = r1.getBotRightX() - r2.getTopLeftX();
				overlapLength = r2.getTopLeftY() - r1.getBotRightY();
			
			}
		//r2 is on the left
		} else if (r1.getTopRightX() > r2.getTopRightX()) {
			//r1 engulfs r2
			if (r1.getTopLeftY() > r2.getTopRightY() && r1.getBotLeftY() < r2.getBotRightY()) {
				overlapWidth = r2.getTopRightX() - r1.getTopLeftX();
				overlapLength = r2.getTopRightY() - r2.getBotRightY();
			//r2 engulfs r1
			} else if (r2.getTopRightY() > r1.getTopLeftY() && r2.getBotRightY() < r1.getBotLeftY()) {
				overlapWidth = r2.getTopRightX() - r1.getTopLeftX();
				overlapLength = r1.getTopLeftY() - r1.getBotLeftY();
			//r2 top right corner overlaps with r1 bottom left corner
			} else if (r2.getTopRightX() > r1.getBotLeftX() && r2.getTopRightY() > r1.getBotLeftY()) {
				overlapWidth = r2.getTopRightX() - r1.getBotLeftX();
				overlapLength = r2.getTopRightY() - r1.getBotLeftY();
			//r2 bottom right corner overlaps with r1 top left corner
			} else if (r2.getBotRightX() > r1.getBotLeftX() && r2.getBotRightY() < r1.getTopLeftY()) {
				overlapWidth = r2.getBotRightX() - r1.getTopLeftX();
				overlapLength = r1.getTopLeftY() - r2.getBotRightY();
			}
		}
	}
	/* Pre: null
	 * Post: Length and width of the mini-rectangle caused by overlap
	 * Action: Returns string of the mini-rectangle's dimensions*/
	public static String getOverlapDimensions () {
		String dimensions = "Length: " + overlapLength + ", Width: " + overlapWidth;
		return dimensions;
	}

	/* Pre: Rectangle 1 and Rectangle 2
	 * Post: Boolean of whether the two rectangles are side by side or not
	 * Action: Checks if the rectangles are side by side. Returns true if they are.*/
	public static boolean sideBySide (Rectangle r1, Rectangle r2) {
		if (sideBySideLength(r1, r2) == 0) {
			return false;
		}
		return true;
	}
	/* Pre: Rectangle 1 and Rectangle 2
	 * Post: Length of the line where rectangles 1 and 2 make contact
	 * Action: Calculates and returns the length of the line where rectangles 1 and 2 make contact*/
	public static int sideBySideLength(Rectangle r1, Rectangle r2) {
		int length = 0; //length of the line where rectangles 1 and 2 make contact
		
		//if r1 is directly to r2's left 
		if (r1.getTopRightX() == r2.getTopLeftX())  {
			//if r1 is within r2's top and bottom corners
			if ((r1.getTopRightY() <= r2.getTopLeftY()) && (r1.getBotRightY() >= r2.getBotLeftY())) {
				length = r1.getTopRightY() - r1.getBotRightY();
			//if r1 is higher than r2's top corner
			} else if ((r1.getTopRightY() > r2.getTopLeftY()) && (r1.getBotRightY() <= r2.getTopLeftY())) {
				length = r2.getTopLeftY() - r1.getBotRightY();
			//if r1 is lower than r2's bottom corner
			} else if ((r1.getTopRightY() <= r2.getTopLeftY()) && (r1.getBotRightY() < r2.getBotLeftY())) {
				length = r1.getTopRightY() - r2.getBotLeftY();
			//if r1 is bigger than r2
			} else if ((r1.getTopRightY() >= r2.getTopLeftY()) && (r1.getBotRightY() <= r2.getBotLeftX())) {
				length = r2.getTopLeftY() - r2.getBotLeftY();
			}
		//if r1 is directly to r2's right
		} else if (r1.getTopLeftX() == r2.getTopRightX()) { 
			//if r1 is within r2's top and bottom corners
			if ((r1.getTopLeftY() <= r2.getTopRightY()) && (r1.getBotLeftY() >= r2.getBotRightY())) {
				length = r1.getTopLeftY() - r1.getBotRightY();
			//if r1 is higher than r2's top corner
			} else if ((r1.getTopLeftY() > r2.getTopRightY()) && (r1.getBotLeftY() <= r2.getTopRightY())) {
				length = r2.getTopRightY() - r1.getBotLeftY();
			//if r1 is lower than r2's bottom corner
			} else if ((r1.getTopLeftY() <= r2.getTopRightY()) && (r1.getBotLeftY() < r2.getBotRightY())) {
				length = r1.getTopLeftY() - r2.getBotRightY();
			//if r1 is bigger than r2
			} else if ((r1.getTopLeftY() >= r2.getTopRightY()) && (r1.getBotLeftY() <= r2.getBotRightX())) {
				length = r2.getTopRightY() - r2.getBotRightY();
			}
		//if r1 is directly above r2
		} else if (r1.getBotLeftY() == r2.getTopLeftY()) { 
			//if r1 is within r2's left and right corners
			if ((r1.getBotLeftX() >= r2.getTopLeftX()) && (r1.getBotRightX() <= r2.getTopRightX())) {
				length = r1.getBotRightX() - r1.getBotLeftX();
			//if r1 is further right than r2's top corner
			} else if ((r1.getBotLeftX() >= r2.getTopLeftX()) && (r1.getBotRightX() > r2.getTopRightX())) {
				length = r2.getTopRightY() - r1.getBotLeftY();
			//if r1 is further left than r2's top corner
			} else if ((r1.getBotLeftX() < r2.getTopLeftX()) && (r1.getBotRightX() <= r2.getTopRightX())) {
				length = r1.getBotRightX() - r2.getTopLeftX();
			//if r1 is bigger than r2
			} else if ((r1.getBotLeftX() < r2.getTopLeftX()) && (r1.getBotRightX() > r2.getTopRightX())) {
				length = r2.getTopRightX() - r2.getTopLeftX();
			}
		//if r1 is directly below r2	
		} else if (r1.getTopLeftY() == r2.getBotLeftY()) { 
			//if r1 is within r2's left and right corners
			if ((r1.getTopLeftX() >= r2.getBotLeftX()) && (r1.getTopRightX() <= r2.getBotRightX())) {
				length = r1.getTopRightX() - r1.getTopLeftX();
			//if r1 is further right than r2's bottom corner
			} else if ((r1.getTopLeftX() >= r2.getBotLeftX()) && (r1.getTopRightX() > r2.getBotRightX())) {
				length = r2.getBotRightY() - r1.getTopLeftY();
			//if r1 is further left than r2's bottom corner
			} else if ((r1.getTopLeftX() < r2.getBotLeftX()) && (r1.getTopRightX() <= r2.getBotRightX())) {
				length = r1.getTopRightX() - r2.getBotLeftX();
			//if r1 is bigger than r2
			} else if ((r1.getTopLeftX() < r2.getBotLeftX()) && (r1.getTopRightX() > r2.getBotRightX())) {
				length = r2.getBotRightX() - r2.getBotLeftX();
			}
		}
		return length;
	}

	/* Pre: Rectangle 1 and Rectangle 2
	 * Post: Area of shape occupying the plane
	 * Action: Calculates and returns the area that the rectangles take up on the plane*/
	public static int getArea(Rectangle r1, Rectangle r2) {
		int area = (r1.getLength()*r1.getWidth()) + (r2.getLength()*r2.getWidth());
		if (overlap(r1, r2)) { //subtracts the mini-rectangle caused by overlap from the total area
			area -= (overlapLength*overlapWidth);
		} 
		return area;
	}
	/* Pre: Rectangle 1 and Rectangle 2
	 * Post: Perimeter of shape occupying the plane
	 * Action: Calculates and returns the perimeter of the shape that the rectangles take up on the plane*/
	public static int getPerim(Rectangle r1, Rectangle r2) {
		int perim = (2*r1.getLength()) + (2*r1.getWidth()) + (2*r2.getLength()) + (2*r2.getWidth()); 
		if (overlap(r1, r2)) { //subtracts the mini-rectangle caused by overlap from the total perimeter
			perim -= 2*(overlapLength + overlapWidth);
		} else if (sideBySide(r1, r2)) { //subtracts the line caused by contact from the total perimeter
			perim -= 2*(sideBySideLength(r1, r2));
		}
		return perim;
	}

}		
