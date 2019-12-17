package com.zone.structure.mst;

import com.zone.structure.WeightGraph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName: Kruscal
 * @Date 2019-12-13 14:09
 * @Author duanxin
 **/
public class Kruscal {

    private WeightGraph G;
    private ArrayList<WeightedEdge> mst;

    private Kruscal(WeightGraph G) {
        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if (cc.count() > 1) return;

        //Kruscal
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v < w)
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));

        Collections.sort(edges);

        UF uf = new UF(G.V());
        for (WeightedEdge edge : edges){
            int v = edge.getV();
            int w = edge.getW();
            if (!uf.isConnected(v,w)) {
                mst.add(edge);
                uf.unionElements(v,w);
            }
        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("g3.txt");
        Kruscal kruscal = new Kruscal(weightGraph);

        System.out.println(kruscal.result());
    }
}
