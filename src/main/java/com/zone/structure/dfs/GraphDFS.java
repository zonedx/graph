package com.zone.structure.dfs;

import com.zone.structure.Graph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @ClassName: GraphDFS
 * @Date 2019-12-06 15:04
 * @Author duanxin
 **/
public class GraphDFS {

    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();


    public GraphDFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0 ; v < G.V(); v ++){
            if (!visited[v]){
                dfs(v);
            }
        }
    }

    private void dfs(int v){

        visited[v] = true;
        pre.add(v);
        for (int w :G.adj(v)){
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    private void dfs2(int v){
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;
        pre.add(v);
        while (!stack.isEmpty()){
            stack.pop();
            for (int w : G.adj(v)){
                if (!visited[w]) {
                    stack.push(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.pre());
//        System.out.println(graphDFS.post());
//        graphDFS = new GraphDFS(new Graph("g2.txt"));
//        System.out.println(graphDFS.pre());
    }
}
