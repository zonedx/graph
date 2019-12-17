package com.zone.structure.adj;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @ClassName: AdjSet
 * @Date 2019-12-06 13:29
 * @Author duanxin
 **/
public class AdjSet {

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    public AdjSet(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file);) {

            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
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

                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }

                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }
                adj[a].add(b);
                adj[b].add(a);
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
        return adj[v].contains(w);
    }

    /**
     * 求顶点v的相邻顶点
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {

        validateVertex(v);
        return adj[v];
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

    private void validateVertex(int v) {
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
            for (int w : adj[v]) {

                stringBuilder.append(String.format("%d ", w));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        AdjSet adjMatrix = new AdjSet("g.txt");
        System.out.println(adjMatrix);
    }
}