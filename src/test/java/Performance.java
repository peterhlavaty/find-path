import inputReader.FileInReader;
import inputReader.InputReader;
import maze.Maze;
import solver.Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Performance {

    public static int MEASUREMENTS = 3;
    public static int STEP = 400;
    public static int STEPS = 5;

    public static void main(String args[]){
        try {
            for (int i = 1; i <= STEPS; i++) {
                long time = 0;
                for (int j = 0; j < MEASUREMENTS; j++) {
                    time += pathTest(i*STEP);
                }
                time = time/MEASUREMENTS;
                System.out.println("Size: " + i*STEP);
                System.out.println("Time: " + time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long pathTest(int size) throws IOException {
        StringBuilder mazeText = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mazeText.append(".");
            }
            mazeText.append(System.lineSeparator());
        }
        mazeText.replace(0,1,"S");
        mazeText.reverse().replace(0,3,"X").reverse();
        final String filePath = "test.txt";
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(mazeText.toString());
        fileWriter.close();

        long startTime = System.currentTimeMillis();
        InputReader inputReader = new FileInReader(filePath);
        Maze maze = null;
        try {
            maze = inputReader.read();
            new Solver().solve(maze);
            System.out.println(maze.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read input");
        }

        return System.currentTimeMillis()-startTime;
    }
}
