package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MazeSolver {

	private final char OPEN = '.';
	private final char BLOCKED = '#';
	private final char START = 'S';
	private final char GOAL = 'G';
	private final char MARKED = '+';
	private final char UNMARKED = '-';
	private char maze[][];
	private ArrayList<String> mazePath = new ArrayList<String>();
	private int numCellsVisited;
	private int mazesSolved = 0;
	private int mazesTried = 0;
	
	int r =0;

	public boolean solveMaze(char[][] maze) {
		mazePath.clear();
		this.maze = maze;

		numCellsVisited = -1;

		// find where 'S' is in the 2d array
		int startX = 0, startY = 0;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == START) {
					startX = j;
					startY = i;
					//System.out.println("S is at row " + startRow + " and col " + startCol);
				}
			}
		}
		maze[startY][startX] = OPEN;
		if (findPath(startY, startX) == true) {
			mazesTried++;
			mazesSolved++;
			maze[startY][startX] = START;
			System.out.println(Arrays.toString(getMoves()));
			int size = mazePath.size();
			System.out.println(size);
			return true;
		} else {
			maze[startY][startX] = START;
			mazesTried++;
			int size = mazePath.size();
			System.out.println(size);
			return false;
		}
	}

	private boolean findPath(int y, int x) {

		numCellsVisited++;
		
		// check for boundaries
		if (y < 0 || y >= maze.length)
			return false;
		if (x < 0 || x >= maze[0].length)
			return false;

		// checks for goal
		if (maze[y][x] == GOAL)
			return true;

		// checks for non open spaces
		if (maze[y][x] == BLOCKED || maze[y][x] == MARKED)
			return false;

		// marks the path
		maze[y][x] = MARKED;
		

		if ((findPath(y, x - 1)) == true)	{
			//System.out.println("Going North" + r++); //for debugging
			mazePath.add("West");
			return true;
		}

		if ((findPath(y + 1, x)) == true) 	{
			//System.out.println("Going East" + r++); //for debugging
			mazePath.add("South");
			return true;	
		}

		if ((findPath(y, x + 1)) == true)	{
			//System.out.println("Going South" + r++); //for debugging
			mazePath.add("East");
			return true;
		}

		if ((findPath(y - 1, x)) == true) 	{
			//System.out.println("Going West" + r++); //for debugging
			mazePath.add("North");
			return true;
		}

		// unmarks the path
		maze[y][x] = UNMARKED;

		return false;

	}

	/*
	 * This method should return mazePath as an array or null if the path was not
	 * found
	 */
	public String[] getMoves() {
		if (mazePath == null) {
			//System.out.println("Moves is null");
			mazePath.clear();
			return null;
		} else {
			//System.out.println("Moves is not null");
			
			String[] moves = new String[mazePath.size()];
			for (int i = 0; i < mazePath.size(); i++) {
				moves[i] = mazePath.get(i);
			}
			
			//converts to list, reverses, converts back to array
			List<String> list = Arrays.asList(moves);
			Collections.reverse(list);
			String[] reversedArray = list.toArray(moves);
			
			return reversedArray;
		}
	}

	/*
	 * This method should return the number of cells visited to the field variable
	 * numCellsVisited
	 */
	public int getNumCellsVisited() {
		return numCellsVisited;
	}

	/*
	 * This method should return the ratio of mazesTried/mazesSolved
	 */
	public double getPerformance() {
		double ratio = (((double) mazesSolved) / mazesTried);
		int r = (int) (ratio * 100);
		System.out.println("Percent Correct: " + r + "%");
		//System.out.println(ratio); //debugging
		return ratio;
	}
}
