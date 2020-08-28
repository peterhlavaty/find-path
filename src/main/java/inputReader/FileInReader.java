package inputReader;

import maze.Maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInReader extends InputReader {

    private String filePath;

    public FileInReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Maze read() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            processLine();
        }
        return super.read();
    }
}
