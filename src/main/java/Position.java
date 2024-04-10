import java.util.Objects;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public Position getLeft() {
        return new Position(row, col - 1);
    }

    public Position getRight() {
        return new Position(row, col + 1);
    }

    public Position getUp() {
        return new Position(row - 1, col);
    }

    public Position getDown() {
        return new Position(row + 1, col);
    }
}
