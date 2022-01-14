package src.bfs;

import src.basic.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartitionDetection {
    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        Arrays.fill(colors, -1);

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;
        colors[s] = 0;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    colors[w] = 1 - colors[v];
                } else if (colors[w] == colors[v]) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        src.dfs.BipartitionDetection bipartitionDetection = new src.dfs.BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite());

        Graph g2 = new Graph("g2.txt");
        src.dfs.BipartitionDetection bipartitionDetection2 = new src.dfs.BipartitionDetection(g2);
        System.out.println(bipartitionDetection2.isBipartite());
    }
}
