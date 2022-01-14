package src.bfs;

import src.basic.Graph;

import java.util.*;

public class Path {
    private Graph G;
    private int s, t;

    private boolean[] visited;
    private int[] pre;

    public Path(Graph G, int s, int t) {
        this.G = G;
        G.validateVertex(s);
        this.s = s;
        G.validateVertex(t);
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;
        pre[s] = s;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);

        return visited[t];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();

        if (!isConnectedTo(t)) return res;

        int cur = t;

        while (cur != s) {
            res.add(cur);

            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        Path path = new Path(g, 0, 6);
        System.out.println("0 -> 6 : " + path.path());

        Path path2 = new Path(g, 0, 1);
        System.out.println("0 -> 1 : " + path2.path());

        Path path3 = new Path(g, 0, 5);
        System.out.println("0 -> 5 : " + path3.path());
    }
}
