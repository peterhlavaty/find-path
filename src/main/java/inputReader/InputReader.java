package inputReader;

import maze.Maze;
import maze.MazeField;

import java.io.FileNotFoundException;

public abstract class InputReader {

    protected Maze maze = new Maze();
    protected int lineNumber = 0;
    protected String line;

    public Maze read() throws FileNotFoundException {
        return maze;
    }

    protected void processLine(){
        for (int i = 0; i < line.length(); i++) {
            maze.setMazeField(new MazeField(line.charAt(i), lineNumber, i));
        }
        lineNumber++;
    }
}
