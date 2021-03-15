import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Board {

    private final int[] board1d;
    private final int[][] board2d;
    private final int dimension;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null)
            throw new IllegalArgumentException("Array is empty!");
        dimension = tiles.length;
        board1d = get1d(tiles);
        board2d = tiles.clone();

    }

    private int[] get1d(int[][] tiles) {
        int k = 0;
        int[] tmp = new int[tiles.length * tiles.length];
        for (int[] tile : tiles) {
            for (int i : tile) {
                tmp[k] = i;
                k++;
            }
        }
        return tmp;
    }


    private int[][] get2d(int[] tiles) {
        int k = 0;
        int[][] tmp = new int[dimension][dimension];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                tmp[i][j] = tiles[k];
                k++;
            }
        }
        return tmp;
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dimension).append("\n");
        for (int i = 0; i < board2d.length; i++) {
            for (int j = 0; j < board2d[i].length; j++) {
                sb.append(board2d[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of tiles out of place
    public int hamming() {
        int outOfPlace = 0;
        for (int i = 0; i < board1d.length; i++) {
            if (board1d[i] != 0 && board1d[i] != i + 1) {
                outOfPlace++;
            }
        }
        return outOfPlace;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < board1d.length; i++) {
            if (board1d[i] != 0 && board1d[i] != i + 1) {
                manhattan += Math.abs(row(board1d[i]-1) - row(i));
                manhattan += Math.abs(col(board1d[i]-1) - col(i));
            }
        }
        return manhattan;

    }

    private int row(int i) {
        return (int) Math.ceil((i + 1) / (double) dimension);
    }

    private int col(int i) {
        if ((i + 1) % dimension == 0) return dimension;
        return (i + 1) % dimension;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < board1d.length; i++) {
            if (board1d[i] != 0 && board1d[i] != i + 1) {
                return false;
            }
        }
        return true;
    }


    // does this board equal y?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return dimension == board.dimension &&
                Arrays.equals(board1d, board.board1d);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        Stack<Board> neighbors = new Stack<>();
        int indexBlank = 0;
        for (int i = 0; i < board1d.length; i++) {
            if (board1d[i] == 0) indexBlank = i;
        }
        //move above
        if (row(indexBlank) != 1) {
            int[] tmp = board1d.clone();
            tmp[indexBlank] = tmp[indexBlank - dimension];
            tmp[indexBlank - dimension] = 0;
            neighbors.push(new Board(get2d(tmp)));
        }
        //move below
        if (row(indexBlank) != dimension) {
            int[] tmp = board1d.clone();
            tmp[indexBlank] = tmp[indexBlank + dimension];
            tmp[indexBlank + dimension] = 0;
            neighbors.push(new Board(get2d(tmp)));
        }
        //move left
        if (col(indexBlank) != 1) {
            int[] tmp = board1d.clone();
            tmp[indexBlank] = tmp[indexBlank - 1];
            tmp[indexBlank - 1] = 0;
            neighbors.push(new Board(get2d(tmp)));
        }
        //move right
        if (col(indexBlank) != dimension) {
            int[] tmp = board1d.clone();
            tmp[indexBlank] = tmp[indexBlank + 1];
            tmp[indexBlank + 1] = 0;
            neighbors.push(new Board(get2d(tmp)));
        }

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        boolean twinCreated = false;
        int[] twin = board1d.clone();

        while (!twinCreated) {
            int indexToSwap = StdRandom.uniform(1, board1d.length);
            int direction = StdRandom.uniform(4);
            switch (direction) {
                case 0: // move above
                    if (row(indexToSwap) == 1 || board1d[indexToSwap - dimension] == 0) {
                        twinCreated = false;
                    } else {
                        int tmp = board1d[indexToSwap];
                        board1d[indexToSwap] = board1d[indexToSwap - dimension];
                        board1d[indexToSwap - dimension] = tmp;
                        twinCreated = true;
                    }
                    break;
                case 1: // move below
                    if (row(indexToSwap) == dimension || board1d[indexToSwap + dimension] == 0) {
                        twinCreated = false;
                    } else {
                        int tmp = board1d[indexToSwap];
                        board1d[indexToSwap] = board1d[indexToSwap + dimension];
                        board1d[indexToSwap + dimension] = tmp;
                        twinCreated = true;
                    }
                    break;
                case 2: // move left
                    if (col(indexToSwap) == 1 || board1d[indexToSwap - 1] == 0) {
                        twinCreated = false;
                    } else {
                        int tmp = board1d[indexToSwap];
                        board1d[indexToSwap] = board1d[indexToSwap - 1];
                        board1d[indexToSwap - 1] = tmp;
                        twinCreated = true;
                    }
                    break;
                case 3: // move right
                    if (col(indexToSwap) == dimension || board1d[indexToSwap + 1] == 0) {
                        twinCreated = false;
                    } else {
                        int tmp = board1d[indexToSwap];
                        board1d[indexToSwap] = board1d[indexToSwap + 1];
                        board1d[indexToSwap + 1] = tmp;
                        twinCreated = true;
                    }
                    break;
            }
        }
        return new Board(get2d(twin));
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}


