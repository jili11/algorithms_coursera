import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF qfFull;
    private final boolean[][] sites;
    private int openSites;
    private final int size;
    private final int virtualTop;
    private final int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        size = n;
        int fullSize = n * n;
        qfFull = new WeightedQuickUnionUF(fullSize + 2); //+ top and bottom
        sites = new boolean[n][n];
        virtualTop = fullSize;
        virtualBottom = fullSize + 1;

    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        //stop if already open
        if (isOpen(row, col)) {
            return;
        }

        int shiftRow = row - 1;
        int shiftCol = col - 1;
        sites[shiftRow][shiftCol] = true;
        openSites++;
        //connect to virtual top
        if (row == 1)
            qfFull.union(virtualTop, getNodeNumber(row, col));
        //connect to virtual bottom
        if (row == size)
            qfFull.union(virtualBottom, getNodeNumber(row, col));
        //check up
        if (row != 1 && isOpen(row - 1, col))
            qfFull.union(getNodeNumber(row, col), getNodeNumber(row - 1, col));
        //check down
        if (row != size && isOpen(row + 1, col))
            qfFull.union(getNodeNumber(row, col), getNodeNumber(row + 1, col));
        //check left
        if (col != 1 && isOpen(row, col - 1))
            qfFull.union(getNodeNumber(row, col), getNodeNumber(row, col - 1));
        //check right
        if (col != size && isOpen(row, col + 1))
            qfFull.union(getNodeNumber(row, col), getNodeNumber(row, col + 1));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateSites(row, col);
        return sites[--row][--col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateSites(row, col);
        return qfFull.find(virtualTop) == qfFull.find(getNodeNumber(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return qfFull.find(virtualTop) == qfFull.find(virtualBottom);
    }

    private int getNodeNumber(int row, int col) {
        return size * (row - 1) + (col - 1);
    }

    private void validateSites(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= size || col >= size)
            throw new IllegalArgumentException();
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
