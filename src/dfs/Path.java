package src.dfs;

import src.basic.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class Path {
    private Graph G;
    private int s, t;

    private boolean[] visited;
    private int[] pre;

    public Path(Graph G, int s, int t) {
        G.validateVertex(s);
        G.validateVertex(t);

        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        dfs(s, s);

//        for (boolean e : visited)
//            System.out.print(e + " ");
//        System.out.println();
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;

        if (v == t) return true;

        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isConnected(int t) {
        return visited[t];
    }

    public ArrayList<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();

        if (!isConnected(t)) return res;

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

        Path path2 = new Path(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());

        Path path3 = new Path(g, 0, 1);
        System.out.println("0 -> 1 : " + path3.path());
    }
}
