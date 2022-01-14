package src.bfs;

import src.basic.Graph;

import java.util.*;

// Unweighted Single Source Shortest path
public class USSSPath {
    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;
    private int[] dis;

    public USSSPath(Graph G, int s) {
        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dis = new int[G.V()];
        Arrays.fill(pre, -1);
        Arrays.fill(dis, -1);

        bfs(s);

//        for(int i = 0; i < G.V(); i ++) {
//            System.out.print(dis[i] + " ");
//        }
//        System.out.println();
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);

        return visited[t];
    }

    public int dis(int t) {
        G.validateVertex(t);

        return dis[t];
    }

    public Iterable<Integer> path(int t) {
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
        USSSPath ussspath = new USSSPath(g, 0);
        System.out.println("0 -> 6 : " + ussspath.path(6));
        System.out.println("0 -> 6 : " + ussspath.dis(6));

        System.out.println();

        Graph g2 = new Graph("g2.txt");
        USSSPath ussspath2 = new USSSPath(g2, 0);
        System.out.println("0 -> 6 : " + ussspath2.path(6));
        System.out.println("0 -> 6 : " + ussspath2.dis(6));
    }
}
