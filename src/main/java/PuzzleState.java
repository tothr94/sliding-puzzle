import java.util.Objects;

public class PuzzleState {
    public static final int BLOCK = 0;
    public static final int RED_SHOE = 1;
    public static final int BLUE_SHOE = 2;
    public static final int BLACK_SHOE = 3;
    public static final int BOARD_SIZE = 3;

    private Position[] positions;

    public PuzzleState() {
        positions = new Position[]{
                new Position(0, 0), // block
                new Position(2, 0), // red
                new Position(1, 1), // blue
                new Position(0, 2) // black
        };
    }

    public boolean isGoal() {
        /*
        return positions[RED_SHOE]
                .equals(positions[BLUE_SHOE]);
         */
        return Objects.equals(
                positions[RED_SHOE],
                positions[BLUE_SHOE]
        );
    }

    public boolean canMoveLeft() {
        /*
        return positions[BLOCK].getCol() > 0
                && !(
                positions[RED_SHOE].getRow() == positions[BLOCK].getRow()
                        && positions[RED_SHOE].getCol() == positions[BLOCK].getCol() - 1
        )
                && !(
                positions[BLUE_SHOE].getRow() == positions[BLOCK].getRow()
                        && positions[BLUE_SHOE].getCol() == positions[BLOCK].getCol() - 1
        )
                && !(
                positions[BLACK_SHOE].getRow() == positions[BLOCK].getRow()
                        && positions[BLACK_SHOE].getCol() == positions[BLOCK].getCol() - 1
        );

         */

        /*
        return positions[BLOCK].getCol() > 0 && isEmpty(
                new Position(
                        positions[BLOCK].getRow(),
                        positions[BLOCK].getCol() - 1
                )
        );

         */

        return positions[BLOCK].getCol() > 0
                && isEmpty(positions[BLOCK].getLeft());
    }

    public boolean canMoveUp() {
        return positions[BLOCK].getRow() > 0
                && isEmpty(positions[BLOCK].getUp());
    }

    public boolean canMoveRight() {
        if (positions[BLOCK].getCol() == BOARD_SIZE - 1) {
            return false;
        }
        Position right = positions[BLOCK].getRight();
        return isEmpty(right) ||
                Objects.equals(right, positions[BLACK_SHOE])
                        && !Objects.equals(positions[BLOCK], positions[BLUE_SHOE]);
    }

    public boolean canMoveDown() {
        // utolsó sor
        if (positions[BLOCK].getCol() == BOARD_SIZE - 1) {
            return false;
        }

        Position down = positions[BLOCK].getDown();
        // ha üres
        if(isEmpty(down)) {
            return true;
        }
        // fekete patkó alattam
        if(Objects.equals(positions[BLACK_SHOE], down)) {
            return false;
        }
        // fekete patkó nálam van
        if(Objects.equals(positions[BLOCK], positions[BLACK_SHOE])) {
            return false;
        }

        // kékkel együtt a pirosba
        if(Objects.equals(positions[BLUE_SHOE], positions[BLOCK])
            && Objects.equals(down, positions[RED_SHOE])) {
            return false;
        }
        return true;
    }

    public boolean canMove(Direction direction) {
        return switch (direction){
            case UP -> canMoveUp();
            case RIGHT -> canMoveRight();
            case DOWN -> canMoveDown();
            case LEFT -> canMoveLeft();
        };
    }

    public void move(Direction direction, int... items) {
        for(int item: items) {
            positions[item] = switch (direction) {
                case UP -> positions[item].getUp();
                case RIGHT -> positions[item].getRight();
                case DOWN -> positions[item].getDown();
                case LEFT -> positions[item].getLeft();
            };
        }
    }


    public boolean isEmpty(Position position) {
        return !Objects.equals(positions[BLOCK], position)
                && !Objects.equals(positions[RED_SHOE], position)
                && !Objects.equals(positions[BLUE_SHOE], position)
                && !Objects.equals(positions[BLACK_SHOE], position);
    }
}
