package com.zone.structure.dfs;

import com.zone.structure.Graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName: Path
 * @Date 2019-12-09 08:55
 * @Author duanxin
 **/
public class Path {

    private Graph G;
    private int s;
    private int t;

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
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }

        dfs(s, s);
    }

    private boolean dfs(int v, int parent) {

        visited[v] = true;
        pre[v] = parent;

        if (v == t) {
            return true;
        }
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isConnected() {
        return visited[t];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected()) {
            return res;
        }

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
        Path path = new Path(g, 0,6);
        System.out.println("0-->6 : " + path.path());
    }
}
