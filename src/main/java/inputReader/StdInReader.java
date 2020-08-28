package inputReader;

import maze.Maze;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class StdInReader extends InputReader {

    @Override
    public Maze read() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        do{
            line = scanner.nextLine();
            processLine();
        }
        while (!line.isEmpty());

        return super.read();
    }
}
