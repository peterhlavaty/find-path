package maze;

import java.util.Objects;

public class MazeField {
    private Character character;
    private int x;
    private int y;
    private int distance = Integer.MAX_VALUE;
    private boolean processed = false;
    private boolean inQueue = false;
    private Direction direction;
    private MazeField parent;

    public MazeField(Character character, int x, int y){
        this.character = character;
        this.x = x;
        this.y = y;
    }

    public Character getCharacter() {
        return character;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public MazeField getParent() {
        return parent;
    }

    public void setParent(MazeField parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return character.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeField mazeField = (MazeField) o;
        return x == mazeField.x &&
                y == mazeField.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
