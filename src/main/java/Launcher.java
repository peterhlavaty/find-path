import inputReader.FileInReader;
import inputReader.InputReader;
import inputReader.StdInReader;
import maze.Maze;
import solver.Solver;

import java.io.FileNotFoundException;

public class Launcher {

    public static void main(String[] args){
        InputReader inputReader;
        if(args.length>0){
            inputReader = new FileInReader(args[0]);
        }
        else {
            inputReader = new StdInReader();
        }
        try {
            Maze maze = inputReader.read();
            new Solver().solve(maze);
            System.out.println(maze.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read input");
        }
    }
}
