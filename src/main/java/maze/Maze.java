package maze;

import java.util.HashMap;
import java.util.Map;

public class Maze {

    public static final Character START_POSITION_CHAR = 'S';
    public static final Character TARGET_POSITION_CHAR = 'X';
    public static final Character FREE_POSITION_CHAR = '.';
    public static final Character BLOCKED_POSITION_CHAR = '#';

    private Map<Integer,Map<Integer,MazeField>> maze = new HashMap<>();
    private MazeField startField;
    private MazeField targetField;

    private String result;

    public MazeField getMazeField(int x, int y){
        if(maze.get(x)==null){
            return null;
        }
        return maze.get(x).get(y);
    }

    public void setMazeField(MazeField mazeField){
        if(maze.get(mazeField.getX())==null){
            maze.put(mazeField.getX(), new HashMap<>());
        }

        maze.get(mazeField.getX()).put(mazeField.getY(), mazeField);

        if(mazeField.getCharacter().equals(START_POSITION_CHAR)){
            startField = mazeField;
        }

        if(mazeField.getCharacter().equals(TARGET_POSITION_CHAR)){
            targetField = mazeField;
        }
    }

    public String getPath(){
        if(result==null){
            if(getTargetField().getDistance()==Integer.MAX_VALUE){
                result = "Path does not exist";
            }
            else{
                StringBuilder sb = new StringBuilder();
                findPath(sb, getTargetField());
                result = sb.reverse().delete(0, 1).toString();
            }
        }
        return result;
    }

    private void findPath(StringBuilder path, MazeField currentField){
        if(currentField.getCharacter().equals(Maze.START_POSITION_CHAR)) return;
        path.append(currentField.getDirection().getName()).append(",");
        findPath(path, currentField.getParent());
    }

    public int getXSize(){
        return maze.size();
    }

    public int getYSize(){
        return maze.get(0) == null ? null : maze.get(0).size();
    }

    public MazeField getStartField() {
        return startField;
    }

    public MazeField getTargetField() {
        return targetField;
    }
}
