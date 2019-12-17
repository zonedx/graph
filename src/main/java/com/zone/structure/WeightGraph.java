package com.zone.structure;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @ClassName: WeightGraph
 * @Date 2019-12-13 13:34
 * @Author duanxin
 **/

//暂时是无向带权图
public class WeightGraph {

    private int V;
    private int E;
    private TreeMap<Integer, Integer>[] adj;

    public WeightGraph(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }
            E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                int weight = scanner.nextInt();

                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }

                if (adj[a].containsKey(b)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }
                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    /**
     * 求顶点v的相邻顶点
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {

        validateVertex(v);
        return adj[v].keySet();
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w))
            return adj[v].get(w);
        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
    }

    /**
     * 求一个节点的度
     *
     * @param v
     * @return
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + "is invalid");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("V = %d , E = %d\n", V, E));
        for (int v = 0; v < adj.length; v++) {
            stringBuilder.append(String.format("%d：", v));
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {

                stringBuilder.append(String.format("(%d: %d)", entry.getKey(), entry.getValue()));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        WeightGraph adjMatrix = new WeightGraph("g3.txt");
        System.out.println(adjMatrix);
    }
}
