package com.zone.structure.bfs;

import com.zone.structure.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: GraphBFS
 * @Date 2019-12-09 10:23
 * @Author duanxin
 **/
public class GraphBFS {

    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> order = new ArrayList<>();
    public GraphBFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];

        for (int v = 0 ; v < G.V(); v++){
            if (!visited[v])
                bfs(v);
        }

    }

    private void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()){
            int v = queue.remove();
            order.add(v);

            for (int w : G.adj(v)){
                if (!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g3.txt");

        GraphBFS graphBFS = new GraphBFS(graph);
        System.out.println("BFS ORDER: " + graphBFS.order());

    }
}
