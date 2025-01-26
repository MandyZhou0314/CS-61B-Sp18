package lab11.graphs;

/**
 *  @author Mandy Zhou
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private Maze maze;
    private boolean cycleFound = false;
    private int[] pathTo;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = 0;
        pathTo = new int[maze.V()];
    }

    private void cycles(int v) {
        if (cycleFound) return;

        // mark all visited vertices
        marked[v] = true;

        for (int u : maze.adj(v)) {
            if (cycleFound) {
                return;
            }

            // if u is marked, and it's not the parent vertex, then we found the cycle
            if (marked[u] && pathTo[v] != u) {
                /* change the path v -> u
                    originally, u is connected to its coming path
                    now, we change it to v, which forms a cycle
                */
                pathTo[u] = v;

                /* save cycle to edgeTo
                    starting from v, go to its parent, stop when we meet v again.
                 */
                int cur = v;
                do {
                    // draw edge: (parent of cur) -> cur
                    edgeTo[cur] = pathTo[cur];

                    // go to cur's parent
                    cur = pathTo[cur];
                } while(cur != v);

                cycleFound = true;
            }
            else if (!marked[u]) {
                pathTo[u] = v;
                cycles(u);
            }
        }
    }


    @Override
    public void solve() {
        cycles(s);
        announce();
    }
}

