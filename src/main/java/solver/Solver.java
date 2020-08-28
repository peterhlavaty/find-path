package solver;

import maze.Direction;
import maze.Maze;
import maze.MazeField;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solver {

    private PriorityQueue<MazeField> queue;
    private Direction[] directions = new Direction[]{new Direction("u",-1, 0), new Direction("d",1, 0), new Direction("l",0, -1), new Direction("r",0, 1)};

    public void solve(Maze maze){
        queue = new PriorityQueue<>(maze.getXSize() * maze.getYSize(), Comparator.comparingInt(MazeField::getDistance));
        maze.getStartField().setDistance(0);
        maze.getStartField().setInQueue(true);
        queue.add(maze.getStartField());

        while(!queue.isEmpty()){
            MazeField currentField = queue.poll();
            if(currentField.getCharacter().equals(Maze.TARGET_POSITION_CHAR)) break;
            currentField.setProcessed(true);
            updateNeighbours(maze, currentField);
        }
    }

    private void updateNeighbours(Maze maze, MazeField currentField){
        for (Direction direction: directions){
            MazeField nextField = maze.getMazeField(currentField.getX()+direction.getX(), currentField.getY()+direction.getY());
            if(nextField!=null && !nextField.isProcessed() && nextField.getCharacter()!=Maze.BLOCKED_POSITION_CHAR){
                if(nextField.getDistance()>currentField.getDistance()+1){
                    nextField.setDistance(currentField.getDistance()+1);
                    nextField.setDirection(direction);
                    nextField.setParent(currentField);
                    if(nextField.isInQueue()){
                        queue.remove(nextField);
                    }
                    queue.add(nextField);
                    nextField.setInQueue(true);
                }
            }
        }
    }

}
