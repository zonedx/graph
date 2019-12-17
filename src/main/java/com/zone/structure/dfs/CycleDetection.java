package com.zone.structure.dfs;

import com.zone.structure.Graph;

/**
 * @ClassName: CycleDetection
 * @Date 2019-12-09 09:11
 * @Author duanxin
 **/
public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;


    public CycleDetection(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0 ; v < G.V(); v ++){
            if (!visited[v]){
                if (dfs(v,v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    //从顶点v开始，判断图中是否有环
    private boolean dfs(int v,int parent){

        visited[v] = true;
        for (int w :G.adj(v)){
            if (!visited[w]) {
                if (dfs(w,v)) {
                    return true;
                }
            }else if (w != parent){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }


    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle);
//        graphDFS = new GraphDFS(new Graph("g2.txt"));
//        System.out.println(graphDFS.pre());
    }
}
