package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GridReader {
	
		private String fileName;
		private char[][] grid;
		
		public GridReader(String fileName) throws Exception{
			/*
			 * Stores the file name from GameFileTester
			 * Read the file and convert to a 2d Array
			 * and store it in the grid field
			 */	
			this.fileName=fileName;
			File fn = new File(fileName); //puts the file variable into the file class and set it to fn
			Scanner s = new Scanner(fn); //puts the fn variable into the scanner class and ser it to s
			
			
			//Initialize the grid and gird size with two variables
			int totalRow = 0;
			int totalColumn = 0;
			//int totalRow =  Integer.parseInt(s.nextLine());; 
	        //int totalColumn = Integer.parseInt(s.nextLine());;
	        
			
			while(s.hasNextLine()) {
				String inputline = s.nextLine();
				String[] inArray = inputline.split("");
	        	totalRow++;
	        	//System.out.println(totalRow);
	        	totalColumn = inArray.length;
	        	//System.out.println(inArray.length);
	        	
	        }
			s.close();
	        setGrid(new char[totalRow][totalColumn]);
	        
			Scanner sn = new Scanner(fn);
	 
	        //Takes the file reads each line and converts it into a 2d char array
	        for (int row = 0; sn.hasNextLine() && row < totalRow; row++) {
	            char[] chars = sn.nextLine().toCharArray();
	            for (int i = 0; i < totalColumn && i < chars.length; i++) {
	                grid[row][i] = chars[i];
	            }
	        }
	        sn.close();
	        
	        /*
	         * This code below outputs the objects ID
	         * this is here to make sure that the getCopy method works
	         * and deep clones the grid variable 
	         * 
	         * System.out.println(grid);    
	         * char[][] test = getCopy();
	         * System.out.println(test);
	         */


		}
		public String toString(){
			
			//Returns the contents of the file as a string
			String fileString = fileName;
			
			FileReader file = null;
			try {
				file = new FileReader(fileString);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			char[] chars = new char[10000];
			try {
				@SuppressWarnings("unused")
				int n = file.read(chars, 0 , chars.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String contents = new String(chars);
			
			return contents;
		}
		public char[][] getCopy(){
			//This will return a deep copy of the grid or null if the file cannot be found
			
			//Checks to see if the grid is null
			if (getGrid() == null) {
		        return null;
		    }
			
			//Deep clones the grid variable into a variable called res
		    final char[][] res = new char[getGrid().length][];
		    for (int i = 0; i < getGrid().length; i++) {
		        res[i] = Arrays.copyOf(getGrid()[i], getGrid()[i].length);
		     }
		    return res;
		}
		public String getFileName(){
			/*
			 * This returns the named that will be used in the construction
			 * It gets the name by asking the user for the location
			 * path of the file 
			 */
			return fileName;
		}
		public char[][] getGrid() {
			return grid;
		}
		public void setGrid(char[][] grid) {
			this.grid = grid;
		}
}
