/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class Board {

    private int[] board;
    private int n = 0;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) { throw new NullPointerException(); }
        n = tiles[0].length;
        board = new int[n * n];
        for (int row = 0; row < n; row++)
            for (int col = 0; col < n; col++) {
                board[row * n + col] = tiles[row][col];
            }
    }

    // string representation of this board
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(n + "\n");
        for (int i = 0, ln = 0; i < board.length; i++) {
            if (ln == n) {
                string.append("\n");
                ln = 0;
            }
            string.append(" " + board[i]);
            ln++;
        }
        String printString = string.toString();
        return printString;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int wrong = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != i+1 && board[i] != 0) {
                wrong++;
            }
        }
        return wrong;
    }

    // sum of Manhattan distances between tiles and goal
    private int manhattan(int goal, int current) {
        int row, col;
        row = Math.abs((goal - 1) / n - current / n);
        col = Math.abs((goal - 1) % n - current % n);
        return row + col;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] != i+1)
                return false;
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this)  return true;
        if (y == null)  return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        return Arrays.equals(this.board, that.board);
    }

    // // all neighboring boards
    public Iterable<Board> neighbors() {
        int zero = 0;
        Board neighbour;
        Stack<Board> neighbours = new Stack<Board>();

        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                zero = i;
            }
        }

        if (zero % n != 0) {
            neighbour = this;
            neighbour.board[zero] = neighbour.board[zero - 1];
            neighbour.board[zero - 1] = 0;
            neighbours.push(neighbour);
        }

        if ((zero % n) != n - 1) {
            neighbour = this;
            neighbour.board[zero] = neighbour.board[zero + 1];
            neighbour.board[zero + 1] = 0;
            neighbours.push(neighbour);
        }

        if (zero / n != 0) {
            neighbour = this;
            neighbour.board[zero] = neighbour.board[zero - n];
            neighbour.board[zero - n] = 0;
            neighbours.push(neighbour);
        }

        if (zero / n != n - 1) {
            neighbour = this;
            neighbour.board[zero] = neighbour.board[zero + n];
            neighbour.board[zero + n] = 0;
            neighbours.push(neighbour);
        }

        return neighbours;
    }

    // // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board twin = this;
        if (twin.board[0] != 0 && twin.board[1] != 0) {
            int val1 = twin.board[0];
            twin.board[0] = twin.board[1];
            twin.board[1] = val1;
            return twin;
        } else {
            int val1 = twin.board[n];
            twin.board[n] = twin.board[1 + n];
            twin.board[1 + n] = val1;
            return twin;
        }
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        Iterable<Board> iter = initial.neighbors();

    }

}
