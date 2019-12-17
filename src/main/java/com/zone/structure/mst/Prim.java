package com.zone.structure.mst;

import com.zone.structure.WeightGraph;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName: Prim
 * @Date 2019-12-13 15:17
 * @Author duanxin
 **/
public class Prim {

    private WeightGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim(WeightGraph G){

        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if (cc.count() > 1)return;

        //Prim
        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        //较低效 O(VE)
//        for (int i = 1 ; i < G.V() ; i++){
//
//            WeightedEdge minEdge = new WeightedEdge(-1,-1,Integer.MAX_VALUE);
//            for (int v = 0 ; v <G.V(); v++)
//                if(visited[v])
//                    for (int w : G.adj(v))
//                        if (!visited[w] && G.getWeight(v,w) < minEdge.getWeight())
//                            minEdge = new WeightedEdge(v,w,G.getWeight(v,w));
//            mst.add(minEdge);
//            visited[minEdge.getV()] = true;
//            visited[minEdge.getW()] = true;
//        }

        //O(ElogE)
        Queue pq = new PriorityQueue<WeightedEdge>();
        for (int w : G.adj(0))
            pq.add(new WeightedEdge(0,w,G.getWeight(0,w)));

        while (!pq.isEmpty()){

            WeightedEdge minEdge = (WeightedEdge) pq.remove();
            if (visited[minEdge.getV()] && visited[minEdge.getW()])
                continue;

            mst.add(minEdge);

            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newV] = true;
            for (int w : G.adj(newV))
                if (!visited[w])
                    pq.add(new WeightedEdge(newV,w,G.getWeight(newV,w)));
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightGraph G =new WeightGraph("g3.txt");
        Prim prim = new Prim(G);
        System.out.println(prim.result());
    }
}
