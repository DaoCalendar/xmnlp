package org.xm.xmnlp.dependency;


import org.xm.xmnlp.corpus.dependency.conll.CoNLLSentence;
import org.xm.xmnlp.corpus.dependency.conll.CoNLLWord;
import org.xm.xmnlp.corpus.tag.Nature;
import org.xm.xmnlp.dependency.common.Edge;
import org.xm.xmnlp.dependency.common.Node;
import org.xm.xmnlp.dependency.common.State;
import org.xm.xmnlp.seg.domain.Term;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 最小生成器
 */
public abstract class MinimumSpanningTreeParser extends AbstractDependencyParser {
    @Override
    public CoNLLSentence parse(List<Term> termList) {
        if (termList == null || termList.size() == 0) return null;
        termList.add(0, new Term("##核心##", Nature.begin));
        Node[] nodeArray = new Node[termList.size()];
        Iterator<Term> iterator = termList.iterator();
        for (int i = 0; i < nodeArray.length; ++i) {
            nodeArray[i] = new Node(iterator.next(), i);
        }
        Edge[][] edges = new Edge[nodeArray.length][nodeArray.length];
        for (int i = 0; i < edges.length; ++i) {
            for (int j = 0; j < edges[i].length; ++j) {
                if (i != j) {
                    edges[j][i] = makeEdge(nodeArray, i, j);
                }
            }
        }
        // 最小生成树Prim算法
        int max_v = nodeArray.length * (nodeArray.length - 1);
        float[] mincost = new float[max_v];
        Arrays.fill(mincost, Float.MAX_VALUE / 3);
        boolean[] used = new boolean[max_v];
        Arrays.fill(used, false);
        used[0] = true;
        PriorityQueue<State> que = new PriorityQueue<State>();
        // 找虚根的唯一孩子
        float minCostToRoot = Float.MAX_VALUE;
        Edge firstEdge = null;
        Edge[] edgeResult = new Edge[termList.size() - 1];
        for (Edge edge : edges[0]) {
            if (edge == null) continue;
            if (minCostToRoot > edge.cost) {
                firstEdge = edge;
                minCostToRoot = edge.cost;
            }
        }
        if (firstEdge == null) return null;
        que.add(new State(minCostToRoot, firstEdge.from, firstEdge));
        while (!que.isEmpty()) {
            State p = que.poll();
            int v = p.id;
            if (used[v] || p.cost > mincost[v]) continue;
            used[v] = true;
            if (p.edge != null) {
//                System.out.println(p.edge.from + " " + p.edge.to + p.edge.label);
                edgeResult[p.edge.from - 1] = p.edge;
            }
            for (Edge e : edges[v]) {
                if (e == null) continue;
                if (mincost[e.from] > e.cost) {
                    mincost[e.from] = e.cost;
                    que.add(new State(mincost[e.from], e.from, e));
                }
            }
        }
        CoNLLWord[] wordArray = new CoNLLWord[termList.size() - 1];
        for (int i = 0; i < wordArray.length; ++i) {
            wordArray[i] = new CoNLLWord(i + 1, nodeArray[i + 1].word, nodeArray[i + 1].label);
            wordArray[i].DEPREL = edgeResult[i].label;
        }
        for (int i = 0; i < edgeResult.length; ++i) {
            int index = edgeResult[i].to - 1;
            if (index < 0) {
                wordArray[i].HEAD = CoNLLWord.ROOT;
                continue;
            }
            wordArray[i].HEAD = wordArray[index];
        }
        return new CoNLLSentence(wordArray);
    }

    protected abstract Edge makeEdge(Node[] nodeArray, int from, int to);
}
