package main;

import java.util.Scanner;

public class SolveMazes {

	static MazeSolver mmaze = new MazeSolver();
	static String fileName;
	static boolean stop = false;
	private static String filePath = "/Users/maxwellmendenhall/eclipse-workspace/MazeSolver/src/main/";

	public static void main(String[] args) throws Exception {	

		Scanner s = new Scanner(System.in);

		while (true) {
			System.out.print("Enter file name or stop to stop the program: ");
			fileName = s.nextLine();
			if (fileName.equals("stop")) {
				System.out.println("Stopping");
				break;
			}
			//Mazes MAZE = new Mazes();
			GridReader reader = new GridReader(filePath + fileName);

			char[][] maze = reader.getGrid();

			System.out.println(reader);
			
			if (mmaze.solveMaze(maze)) {
				System.out.println("Solution Found");
				System.out.println("Num of cells visited: " + mmaze.getNumCellsVisited());
				for (int i = 0; i < maze.length; i++) {
					System.out.println(maze[i]);
				}
			} else {
				System.out.println("No Solution");
				for (int i = 0; i < maze.length; i++) {
					System.out.println(maze[i]);
				}
			}
		}
		mmaze.getPerformance();
		s.close();

	}
}
