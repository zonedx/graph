package com.zone.structure.dfs;

import com.zone.structure.Graph;

import java.util.ArrayList;

/**
 * @ClassName: CC
 * @Date 2019-12-06 16:23
 * @Author duanxin
 **/
public class CC {

    private Graph G;
    private int[] visited;
    private int cccount = 0;

    public CC(Graph G) {
        this.G = G;
        visited = new int[G.V()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, cccount);
                cccount++;
            }
        }
    }

    private void dfs(int v, int ccid) {

        visited[v] = ccid;
        for (int w : G.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public int count() {
//        for (int e :visited){
//            System.out.print(e + " ");
//        }
//        System.out.println();

        return cccount;
    }

    public boolean isConnected(int v,int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    /**
     * 查询此图有多少个联通分量以及每个联通分量对应的顶点是哪些
     *
     * @return
     */
    public ArrayList<Integer>[] components(){

        ArrayList<Integer>[] res = new ArrayList[cccount];

        for (int i = 0 ; i < cccount ; i++){
            res[i] = new ArrayList<>();
        }

        for (int v = 0 ; v <G.V(); v++){
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CC cc = new CC(graph);
        System.out.println(cc.count());
        System.out.println(cc.isConnected(0,5));

        ArrayList<Integer>[] comp = cc.components();
        for (int i = 0 ; i < comp.length; i++){
            System.out.print("ccid "+ i +" : ");
            for (int w : comp[i]){
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
