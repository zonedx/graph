package com.zone.structure.dijkstra;

import com.zone.structure.WeightGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @ClassName: Dijkstra
 * @Date 2019-12-13 16:50
 * @Author duanxin
 **/
public class Dijkstra {

    private WeightGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;
    private int[] pre;

    private class Node implements Comparable<Node> {

        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }


        @Override
        public int compareTo(Node another) {
            return dis - another.dis;
        }
    }

    public Dijkstra(WeightGraph G, int s) {
        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        pre = new int[G.V()];
        Arrays.fill(pre,-1);
        pre[s] = s;

        visited = new boolean[G.V()];


        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {

            //O(ElogE)
            int cur = pq.remove().v;
            if (visited[cur]) continue;
            //O(v^2)
//            int curDis = Integer.MAX_VALUE;
//            int cur = -1;
//            for (int v = 0 ; v < G.V() ; v ++){
//                if (!visited[v] && dis[v] < curDis){
//                    curDis = dis[v];
//                    cur = v;
//                }
//            }
//            if (cur == -1)break;

            visited[cur] = true;
            for (int w : G.adj(cur))
                if (!visited[w]) {
                    if (dis[cur] + G.getWeight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w, dis[w]));
                        pre[w] = cur;
                    }
                }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < G.V(); i++) {
                sb.append(i).append("     ");
            }
            System.out.println(sb.toString());

            for (int v = 0; v < G.V(); v++)
                System.out.print(visited[v] + " ");
            System.out.println("");
        }
    }

    public boolean isConnetedTo(int v) {
        G.validateVertex(v);
        return visited[v];
    }

    public int disTo(int v) {
        G.validateVertex(v);

        return dis[v];
    }

    public Iterable<Integer> path(int t){

        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnetedTo(t))return res;

        int cur = t;
        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightGraph g = new WeightGraph("g4.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);
        for (int v = 0; v < g.V(); v++)
            System.out.print(dijkstra.disTo(v) + " ");

        System.out.println(" ");
        System.out.println(dijkstra.path(5));
    }
}

