package puzzle.state;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParameterizedPositionTest {

    void assertPosition(int expectedRow, int expectedCol, Position position) {
        assertAll("position",
                () -> assertEquals(expectedRow, position.row()),
                () -> assertEquals(expectedCol, position.col())
        );
    }

    static Stream<Position> positionProvider() {
        return Stream.of(new Position(0, 0),
                new Position(0, 2),
                new Position(2, 0),
                new Position(2, 2));
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void getPosition(Position position) {
        assertPosition(position.row() - 1, position.col(), position.getPosition(Direction.UP));
        assertPosition(position.row(), position.col() + 1, position.getPosition(Direction.RIGHT));
        assertPosition(position.row() + 1, position.col(), position.getPosition(Direction.DOWN));
        assertPosition(position.row(), position.col() - 1, position.getPosition(Direction.LEFT));
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void getUp(Position position) {
        assertPosition(position.row() - 1, position.col(), position.getUp());
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void getRight(Position position) {
        assertPosition(position.row(), position.col() + 1, position.getRight());
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void getDown(Position position) {
        assertPosition(position.row() + 1, position.col(), position.getDown());
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void getLeft(Position position) {
        assertPosition(position.row(), position.col() - 1, position.getLeft());
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void setTo_up(Position position) {
        var row = position.row();
        var col = position.col();
        position.setTo(Direction.UP);
        assertPosition(row - 1, col, position);
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void setTo_right(Position position) {
        var row = position.row();
        var col = position.col();
        position.setTo(Direction.RIGHT);
        assertPosition(row, col + 1, position);
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void setTo_down(Position position) {
        var row = position.row();
        var col = position.col();
        position.setTo(Direction.DOWN);
        assertPosition(row + 1, col, position);
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void setTo_left(Position position) {
        var row = position.row();
        var col = position.col();
        position.setTo(Direction.LEFT);
        assertPosition(row, col - 1, position);
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void setUp(Position position) {
        var row = position.row();
        var col = position.col();
        position.setUp();
        assertPosition(row - 1, col, position);
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void setRight(Position position) {
        var row = position.row();
        var col = position.col();
        position.setRight();
        assertPosition(row, col + 1, position);
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void setDown(Position position) {
        var row = position.row();
        var col = position.col();
        position.setDown();
        assertPosition(row + 1, col, position);
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void setLeft(Position position) {
        var row = position.row();
        var col = position.col();
        position.setLeft();
        assertPosition(row, col - 1, position);
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void testEquals(Position position) {
        assertTrue(position.equals(position));
        assertTrue(position.equals(new Position(position.row(), position.col())));
        assertFalse(position.equals(new Position(Integer.MIN_VALUE, position.col())));
        assertFalse(position.equals(new Position(position.row(), Integer.MAX_VALUE)));
        assertFalse(position.equals(new Position(Integer.MIN_VALUE, Integer.MAX_VALUE)));
        assertFalse(position.equals(null));
        assertFalse(position.equals("Hello, World!"));
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void testHashCode(Position position) {
        assertTrue(position.hashCode() == position.hashCode());
        assertTrue(position.hashCode() == new Position(position.row(), position.col()).hashCode());
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void testClone(Position position) {
        var clone = position.clone();
        assertTrue(clone.equals(position));
        assertNotSame(position, clone);
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void testToString(Position position) {
        assertEquals(String.format("(%d,%d)", position.row(), position.col()), position.toString());
    }

}
