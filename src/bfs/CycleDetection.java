package src.bfs;

import src.basic.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (bfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean bfs(int s, int parent) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                } else if (w == parent) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        src.dfs.CycleDetection cd = new src.dfs.CycleDetection(g);
        System.out.println(cd.hasCycle());

        Graph g2 = new Graph("g2.txt");
        src.dfs.CycleDetection cd2 = new src.dfs.CycleDetection(g2);
        System.out.println(cd2.hasCycle());
    }
}
