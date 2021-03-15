import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {

        private final Board board;
        private final int moves;
        private final SearchNode previous;
        private final int manhattan;

        SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.manhattan = board.manhattan();
        }

        @Override
        public int compareTo(SearchNode o) {
            int s1Manhattan = this.manhattan;
            int s2Manhattan = o.manhattan;
            int s1Priority = this.moves + s1Manhattan;
            int s2Priority = o.moves + s2Manhattan;

            if (s1Priority < s2Priority) {
                return -1;
            }

            if (s1Priority > s2Priority) {
                return 1;
            }

            return Integer.compare(s1Manhattan, s2Manhattan);
        }
    }

    private final Stack<Board> solution = new Stack<>();
    private boolean solvable = true;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();


        MinPQ<SearchNode> mainSearch = new MinPQ<>();

        MinPQ<SearchNode> parallelSearch = new MinPQ<>();
        parallelSearch.insert(new SearchNode(initial.twin(), 0, null));

        SearchNode currentNode = new SearchNode(initial, 0, null);

        boolean mainSearchRunning = true;

        while (true) {

            if (currentNode.board.isGoal()) {

                if (!mainSearchRunning) {
                    solvable = false;
                    return;
                }

                while (currentNode.previous != null) {
                    solution.push(currentNode.board);
                    currentNode = currentNode.previous;
                }
                solution.push(initial);
                return;

            }

            for(Board neighbor: currentNode.board.neighbors()){
                if (currentNode.previous == null ||
                        !currentNode.previous.board.equals(neighbor)) {

                    SearchNode nodeToInsert =
                            new SearchNode(neighbor, currentNode.moves + 1,
                                    currentNode);

                    (mainSearchRunning ? mainSearch : parallelSearch).insert(nodeToInsert);
                }
            }

            mainSearchRunning = !mainSearchRunning;

            if (mainSearchRunning) {
                if (!mainSearch.isEmpty()) {
                    currentNode = mainSearch.delMin();
                } else {
                    mainSearchRunning = false;
                    currentNode = parallelSearch.delMin();
                }
            } else {
                if (!parallelSearch.isEmpty()) {
                    currentNode = parallelSearch.delMin();
                } else {
                    mainSearchRunning = true;
                    currentNode = mainSearch.delMin();
                }
            }

        }


    }


    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solvable ? solution.size() - 1 : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return isSolvable() ? solution : null;
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In("puzzle04.txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        StdOut.println(initial.toString());

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }


}
