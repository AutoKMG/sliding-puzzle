package puzzle.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Represents the state of the puzzle.
 */
public class PuzzleState implements Cloneable {

    /**
     * The size of the board.
     */
    public static final int BOARD_SIZE = 3;

    /**
     * The index of the block.
     */
    public static final int BLOCK = 0;

    /**
     * The index of the red shoe.
     */
    public static final int RED_SHOE = 1;

    /**
     * The index of the blue shoe.
     */
    public static final int BLUE_SHOE = 2;

    /**
     * The index of the black shoe.
     */
    public static final int BLACK_SHOE = 3;

    private Position[] positions;

    public PuzzleState(){
        this(new Position(0,0),
                new Position(2, 0),
                new Position(1, 1),
                new Position(0, 2));
    }
    public PuzzleState(Position... positions){
        checkPositions(positions);
        this.positions = deepClone(positions);
    }

    public boolean isGoal(){
        return haveSamePositions(RED_SHOE, BLUE_SHOE);
    }

    public boolean canMove(Direction direction){
        return switch (direction){
            case UP -> canMoveUp();
            case RIGHT -> canMoveRight();
            case DOWN -> canMoveDown();
            case LEFT -> canMoveLeft();
        };
    }

    private boolean canMoveUp(){
        return positions[BLOCK].row() > 0 &&  isEmpty(positions[BLOCK].getUp());
    }
    private boolean canMoveRight(){
        if (positions[BLOCK].col() == BOARD_SIZE - 1){
            return false;
        }
        var right = positions[BLOCK].getRight();
        return isEmpty(right) || (positions[BLACK_SHOE].equals(right) && haveSamePositions(BLOCK, BLUE_SHOE));
    }
    private boolean canMoveDown(){
        if(positions[BLOCK].row() == BOARD_SIZE - 1){
            return false;
        }
        var down = positions[BLOCK].getDown();
        if (isEmpty(down)){
            return true;
        }
        if (haveSamePositions(BLOCK, BLACK_SHOE) || positions[BLACK_SHOE].equals(down)){
            return false;
        }
        return (positions[BLUE_SHOE].equals(down) || positions[RED_SHOE].equals(down)) && !haveSamePositions(BLOCK, BLUE_SHOE);

    }
    private boolean canMoveLeft(){
        return positions[BLOCK].col() > 0 && isEmpty(positions[BLOCK].getLeft());
    }

    private void checkPositions(Position[] positions){
        if (positions.length != 4){
            throw new IllegalArgumentException();
        }
        for (Position position: positions){
            if (!isOnBoard(position)){
                throw new IllegalArgumentException();
            }
        }
        if (positions[BLUE_SHOE].equals(positions[BLACK_SHOE])){
            throw new IllegalArgumentException();
        }
    }


    public void move(Direction direction){
        switch (direction){
            case UP -> moveUp();
            case RIGHT -> moveRight();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
        }
    }

    private void moveUp() {
        if (haveSamePositions(BLOCK, BLACK_SHOE)){
            if (haveSamePositions(BLOCK, RED_SHOE)){
                positions[RED_SHOE].setUp();
            }
        }
        positions[BLOCK].setUp();
    }
    private void moveRight() {
        move(Direction.RIGHT, RED_SHOE, BLUE_SHOE, BLACK_SHOE);
    }
    private void moveDown() {
        move(Direction.DOWN, RED_SHOE, BLUE_SHOE, BLACK_SHOE);
    }
    private void moveLeft() {
        move(Direction.LEFT, RED_SHOE, BLUE_SHOE);
    }

    private void move(Direction direction, int... shoes){
        for (var i : shoes){
            if (haveSamePositions(i, BLOCK)){
                positions[i].setTo(direction);
            }
        }
        positions[BLOCK].setTo(direction);
    }
    private boolean isOnBoard(Position position){
        return 0 <= position.row() && position.row() < BOARD_SIZE
                & 0 <= position.col() && position.col() < BOARD_SIZE;
    }


    private boolean haveSamePositions(int i, int j){
        return positions[i].equals(positions[j]);
    }

    private boolean isEmpty(Position position){
        for (var p : positions){
            if (p.equals(position)){
                return false;
            }
        }
        return true;
    }
    private Position[] deepClone(Position[] positions){
        Position[] copy = positions.clone();
        for (var i = 0; i < positions.length; i++){
            copy[i] = copy[i].clone();
        }
        return copy;
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        return (o instanceof PuzzleState other) && Arrays.deepEquals(positions, other.positions);
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(positions);
    }

    @Override
    public PuzzleState clone(){
        PuzzleState copy = null;
        try {
            copy = (PuzzleState) super.clone();
        } catch (CloneNotSupportedException e) {
            // Never happens
        }
        copy.positions = deepClone(positions);
        return copy;
    }
    @Override
    public String toString(){
        var sj = new StringJoiner(",", "[", "]");
        for (var position: positions){
            sj.add(position.toString());
        }
        return sj.toString();
    }

    public static void main(String[] args) {
        var state = new PuzzleState();
        System.out.println(state);
    }
}
