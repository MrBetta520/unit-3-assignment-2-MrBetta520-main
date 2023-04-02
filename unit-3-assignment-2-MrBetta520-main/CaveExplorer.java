import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CaveExplorer {
	// Step 1: Zero parameter constructor to create the specified cave
	static char matrix[][];
	static int row, col;
	static ArrayList<Character> storePath = new ArrayList<Character>();
	static ArrayList<String> fileData = new ArrayList<String>();

	public CaveExplorer() {
		// Create a 2D array of CaveSquares
		// Populate the array with CaveSquares
		// Set the starting and ending squares
		// Set the neighbors of each CaveSquare
		char matrix[][] = {{'R', 'R', 'R', 'R', 'R', 'R'},
	                       {'R', '.', '.', 'S', 'R', 'R'},
				           {'R', '.', 'R', 'R', 'R', 'R'},
				           {'R', '.', 'M', 'R', 'R', 'R'},
			               {'R', 'R', 'R', 'R', 'R', 'R'}};

		this.matrix = matrix;
		this.row = 5;
		this.col = 6;
		this.storePath.clear();

	}
	
	// Step 2: toString method

	public String toString() {
		String s = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				s += matrix[i][j];
			}
			s += "\n";
		}

		return s;
	}
	
	// Step 3: solve method
	public static boolean solve() {
		boolean visited[][] = new boolean[row][col];

		boolean result = false;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 'S' && !visited[i][j]) {
					if(isPath(matrix, i, j, visited)) {
						result = true;
						break;
					}
				}
			}
		}
		if(result) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isSafe(int row, int col, char matrix[][]) {
		if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length) {
			return true;
		}

		return false;
	}

	private static boolean isPath (char matrix[][], int row, int col, boolean visited[][]) {
		if (isSafe(row, col, matrix) && matrix[row][col] != 'R' && !visited[row][col]) {
			visited[row][col] = true;

			if(matrix[row][col] == 'M') {
				return true;
			}
			
			boolean up = isPath(matrix, row - 1, col, visited);
	
			if (up) {
				storePath.add('N');
				return true;
			}
	
			boolean left = isPath(matrix, row, col - 1, visited);
	
			if (left) {
				storePath.add('W');
				return true;
			}
	
			boolean down = isPath(matrix, row + 1, col, visited);
	
			if (down) {
				storePath.add('S');
				return true;
			}
	
			boolean right = isPath(matrix, row, col + 1, visited);
	
			if (right) {
				storePath.add('E');
				return true;
			}
		}
		
		return false;
	}


	// Step 4: getPath method
	public String getPath() {
		if(storePath.size() == 0) {
			solve();
		}
		String path = "";
		Collections.reverse(storePath);
		for (Character s:storePath) {
			path += s;
		}
		return path;
	}
	
	// Step 5: One parameter constructor to read from a file
	
	public CaveExplorer (String fname) throws Exception {
	      Scanner in = new Scanner(new File(fname));

	      int rows = in.nextInt();
	      int cols = in.nextInt();  
    	  String s = in.nextLine(); // Skip newline character
    	   	  
    	  // Construct cave and populate with rest of data in the file
        
		  char matrix[][] = new char[rows][cols];
		  int index = 0;

		  for (int i = 0; i < rows; i++) {
			  fileData.add(in.nextLine().trim());
		  }

		  for (String data:fileData) {
			 if(data.length() == cols) {
				matrix[index++] = data.toCharArray();
			 } else {
				 throw new Exception("Can not find file.");
			 }
		  }

		  this.matrix = matrix;
		  this.row = rows;
		  this.col = cols;
		  this.storePath.clear();
	}
	

	
	public static void main(String[] args) {
		System.out.println("Starting CaveExplorer");
		
		// Create a CaveExplorer object and print the starting layout
		
		// Call solve
		
		// Print the final layout, whether there is a path, and if so, what it is.
		
		// Step 5/6: Repeat for a different CaveExplorer object read from a file
		// Uncomment code below to start testing your 1-parameter constructor

		
		String fileName = "C:\\Users\\Yang Yu\\Desktop\\CS352\\unit-3-assignment-2-MrBetta520-main\\unit-3-assignment-2-MrBetta520-main\\testdat.txt"; 
		try { 
			CaveExplorer ce1 = new CaveExplorer(); 

			System.out.println(ce1.toString());
			System.out.println(ce1.solve());
			System.out.println(ce1.getPath());


			System.out.println("*************************************");

			CaveExplorer ce2 = new CaveExplorer(fileName); 

			System.out.println(ce2.toString());
			System.out.println(ce2.solve());
			System.out.println(ce2.getPath());

		} 
		catch (FileNotFoundException e ) {
			System.out.println("Can't find file " + fileName); 
		}
		catch (Exception e) {
			System.out.println("Other error: " + e.getMessage());
		}
		
		System.out.println("Finished CaveExplorer");
	}

}
