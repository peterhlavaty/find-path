import inputReader.FileInReader;
import inputReader.InputReader;
import maze.Maze;
import org.junit.Test;
import solver.Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Functionality {

    public static void main(String args[]){
        try {
            fileInTest();
            pathTests();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void fileInTest() throws IOException {
        final String mazeText = "S.#....X";
        final String filePath = "test.txt";
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(mazeText);
        fileWriter.close();

        FileInReader fileInReader = new FileInReader(filePath);
        Maze maze = fileInReader.read();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maze.getXSize(); i++) {
            for (int j = 0; j < maze.getYSize(); j++) {
                stringBuilder.append(maze.getMazeField(i, j));
            }
        }

        assertEquals(mazeText, stringBuilder.toString());
    }

    @Test
    public static void pathTests() throws IOException {
        pathTest("....." + System.lineSeparator() +
                          "S###." + System.lineSeparator() +
                          "...#." + System.lineSeparator() +
                          "##.#." + System.lineSeparator() +
                          "X#.#." + System.lineSeparator() +
                          "..#.." + System.lineSeparator() +
                          "#...#" + System.lineSeparator()
                ,"u,r,r,r,r,d,d,d,d,d,l,d,l,l,u,l,u");
        pathTest("....." + System.lineSeparator() +
                        "S###." + System.lineSeparator() +
                        "...#." + System.lineSeparator() +
                        "##.#." + System.lineSeparator() +
                        "X..#." + System.lineSeparator() +
                        "..#.." + System.lineSeparator() +
                        "#...#" + System.lineSeparator()
                ,"d,r,r,d,d,l,l");
        pathTest("SX", "r");
        pathTest("S..X", "r,r,r");
        pathTest("S#X", "Path does not exist");
    }

    private static void pathTest(String mazeText, String result) throws IOException {
        final String filePath = "test.txt";
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(mazeText);
        fileWriter.close();

        InputReader inputReader = new FileInReader(filePath);
        Maze maze = null;
        try {
            maze = inputReader.read();
            new Solver().solve(maze);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read input");
        }

        assertEquals(result, maze.getPath());
    }
}
