package puzzle.state;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Represents the state of the puzzle.
 */
public class PuzzleState {

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
    private boolean isOnBoard(Position position){
        return 0 <= position.row() && position.row() < BOARD_SIZE
                & 0 <= position.col() && position.col() < BOARD_SIZE;
    }

    private Position[] deepClone(Position[] positions){
        Position[] copy = positions.clone();
        for (var i = 0; i < positions.length; i++){
            copy[i] = copy[i].clone();
        }
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
}