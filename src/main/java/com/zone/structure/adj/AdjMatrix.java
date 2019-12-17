package com.zone.structure.adj;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName: AdjMatrix
 * @Date 2019-12-06 10:20
 * @Author duanxin
 **/
public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file);) {

            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new int[V][V];
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

                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }
                adj[a][b] = 1;
                adj[b][a] = 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    /**
     * 求一个顶点的相邻顶点
     * @param v
     * @return
     */
    public ArrayList<Integer> adj(int v){

        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0 ; i < V ; i ++){
            if (adj[v][i] == 1){
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 求一个节点的度
     * @param v
     * @return
     */
    public int degree(int v){
        return adj(v).size();
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
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj.length; j++) {
                stringBuilder.append(String.format("%d ", adj[i][j]));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
    }
}
