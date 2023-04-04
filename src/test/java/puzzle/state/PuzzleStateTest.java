package puzzle.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleStateTest {

    PuzzleState state1 = new PuzzleState(); // the original initial state

    PuzzleState state2 = new PuzzleState(
            new Position(1, 1),
            new Position(1, 1),
            new Position(1, 1),
            new Position(1,2)); // a goal state

    PuzzleState state3 = new PuzzleState(new Position(1, 1),
            new Position(2, 0),
            new Position(1, 1),
            new Position(0, 2)); // a non-goal state

    PuzzleState state4 = new PuzzleState(new Position(0,0),
            new Position(1,0),
            new Position(0, 1),
            new Position(0, 0)); // a dead end-state;
    @Test
    void isGoal() {
        assertFalse(state1.isGoal());
        assertTrue(state2.isGoal());
        assertFalse(state3.isGoal());
        assertFalse(state4.isGoal());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new PuzzleState(new Position(0, 0)));
        assertThrows(IllegalArgumentException.class,
                () -> new PuzzleState(new Position(0, 0),
                        new Position(0, 1),
                        new Position(0, 2),
                        new Position(0, 3)));
        assertThrows(IllegalArgumentException.class, () -> new PuzzleState(
                new Position(1, 1),
                new Position(1, 1),
                new Position(1, 1),
                new Position(1, 1)
        ));
    }
    @Test
    void canMove() {
        assertFalse(state1.canMove(Direction.UP));
        assertTrue(state1.canMove(Direction.RIGHT));
        assertTrue(state1.canMove(Direction.DOWN));
        assertFalse(state1.canMove(Direction.LEFT));
    }

    @Test
    void move() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testClone() {
    }

    @Test
    void testToString() {
    }
}