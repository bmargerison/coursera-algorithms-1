import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final static int TOP = 0;
    private boolean[] openOrClosed;
    private final int size;
    private final int bottom;
    private final WeightedQuickUnionUF grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        bottom = n * n + 1;
        openOrClosed = new boolean[n * n + 2];
        for (int i = 0; i < (n * n + 2); i++) openOrClosed[i] = false;
        grid = new WeightedQuickUnionUF(n * n + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            openOrClosed[(findIndex(row, col))] = true;
            if (row == 1) {
                grid.union(findIndex(row, col), TOP);
            }
            if (row == size) {
                grid.union(findIndex(row, col), bottom);
            }
            if (row > 1 && isOpen(row - 1, col)) {
                grid.union(findIndex(row, col), findIndex(row - 1, col));
            }
            if (row < size && isOpen(row + 1, col)) {
                grid.union(findIndex(row, col), findIndex(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                grid.union(findIndex(row, col), findIndex(row, col - 1));
            }
            if (col < size && isOpen(row, col + 1)) {
                grid.union(findIndex(row, col), findIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IndexOutOfBoundsException();
        } else {
            return openOrClosed[findIndex(row, col)];
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IndexOutOfBoundsException();
        } else {
            return grid.find(TOP) == grid.find(findIndex(row, col));
        }
    };

    // returns the number of open sites
    public int numberOfOpenSites() {
        int number = 0;
        for (int i = 0; i < (size * size + 2); i++)
            if (openOrClosed[i])
                number++;
        return number;
    };

    private int findIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.find(TOP) == grid.find(bottom);
    };

}
