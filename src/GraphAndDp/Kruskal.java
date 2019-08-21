package GraphAndDp;

import java.util.ArrayList;
import java.util.List;

// 边的结构体
class ENode {
    char start; // 边的起点
    char end; // 边的终点
    int weight; // 边的权重

    public ENode(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

//邻接矩阵对应的结构体
class Graph {
    private static final int INF = Integer.MAX_VALUE; // 最大值

    char[] vertexs; // 顶点集合
    int[][] matrix; // 邻接矩阵

    // 从邻接矩阵中得到当前有向图中的所有边信息
    public List<ENode> getEdges() {
        List<ENode> edges = new ArrayList<ENode>();
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {  //为了避免一条边算两次
                if (matrix[i][j] != INF && matrix[i][j] != 0) {
                    ENode edge = new ENode(vertexs[i], vertexs[j], matrix[i][j]);
                    edges.add(edge);
                }
            }
        }

        return edges;
    }
}

public class Kruskal {
    private static final int INF = Integer.MAX_VALUE; // 最大值

    /**
     * 对边的权重按从小到大进行排序，利用快排的思想
     *
     * @param edges 存放边的list
     * @param low
     * @param high
     */
    public static void qSort(List<ENode> edges, int low, int high) {
        if (low >= high)
            return;

        int small = low - 1;
        int cur = low;
        while (cur < high) {
            ENode edge = edges.get(cur);
            if (edge.weight <= edges.get(high).weight) {
                swapEdge(edges, cur++, ++small);
            } else {
                cur++;
            }
        }

        swapEdge(edges, ++small, high);

        qSort(edges, low, small - 1);
        qSort(edges, small + 1, high);

    }

    public static void swapEdge(List<ENode> edges, int m, int n) {
        ENode tmp = edges.get(m);
        edges.set(m, edges.get(n));
        edges.set(n, tmp);
    }


    /**
     * kruskal算法实现方法
     *
     * @param G 图G
     *          return 最小生成树的权值
     */
    public static int kruskal(Graph G) {
        // 1.拿到有向图中所有边
        List<ENode> edges = G.getEdges();
        int edgeNum = edges.size(); //图的边数

        // 2.对所有有向边进行排序
        qSort(edges, 0, edgeNum - 1);

        ENode[] minTree = new ENode[G.vertexs.length - 1]; // 结果数组，保存kruskal最小生成树的边
        int index = 0; // minTree数组的索引

        // 用于保存"已有最小生成树"中每个顶点（以数组下标表示） 与 其经过“最短边”的邻接顶点 （以对应下标的值表示）的并查集
        int[] start2end = new int[G.vertexs.length];


        // 3.依次将最短且不与T构成回路的边加入T集合
        for (int i = 0; i < edgeNum; i++) {
            //得到当前最短边 在有向图G中的起始顶点与终结顶点的 下标
            int p1 = getIndex(G, edges.get(i).start); // 获取第i条边的"起点"的序号
            int p2 = getIndex(G, edges.get(i).end); // 获取第i条边的"终点"的序号

            //分别得到在T集合中沿当前最短边的“起点”与“终点”遍历到的最后节点，
            //若加入当前最短边后T集合存在回路，则“起点”与“终点”遍历到的最后节点一定是同一节点
            int m = getEnd(start2end, p1);                 // 获取p1在"已有的最小生成树"中的终点
            int n = getEnd(start2end, p2);                 // 获取p2在"已有的最小生成树"中的终点

            //当前最短边加入T集合后没有回路 则将当前最短边加入T集合，并且记录当前最短边的“起点”与“终点”
            if (m != n) {
                start2end[m] = n;               // “起点”即vends的数组下标与“终点”即vends的对应下标的值
                minTree[index++] = edges.get(i);           // 保存结果
            }
        }

        int sum = 0;

        if (index == G.vertexs.length - 1) {
            //生成了最小生成树，获取最小生成树的权值
            for (int i = 0; i < minTree.length; i++) {
                sum += minTree[i].weight;
            }

            return sum;
        } else {
            System.out.println("没有生成最小生成树！");
            return 0;
        }


    }

    /**
     * 获取某个顶点在顶点数组中的下标
     *
     * @param G  图G
     * @param ch 顶点字符
     * @return 该顶点的下标
     */
    public static int getIndex(Graph G, char ch) {
        int i = 0;
        for (; i < G.vertexs.length; i++)
            if (G.vertexs[i] == ch)
                return i;
        return -1;
    }

    /**
     * 获取i在已生成的最小生成树中的终点
     *
     * @param start2end 用于保存"已有最小生成树"中每个顶点（以数组下标表示） 与 其经过“最短边”的邻接顶点 （以对应下标的值表示）的并查集
     * @param i         某个下标
     * @return i在已生成的最小生成树中的终点
     */
    public static int getEnd(int start2end[], int i) {
        while (start2end[i] != 0)
            i = start2end[i];
        return i;
    }


    public static void main(String[] args) {
        Graph g = new Graph();
        g.vertexs = new char[]{'A', 'B', 'C', 'D', 'E', 'F'}; //这里不能和初始化一样，直接赋值，需要声明一个char数组，同时进行初始化操作
        g.matrix = new int[][]{
                {0, 7, 4, INF, INF, INF},
                {7, 0, 6, 2, INF, 4},
                {4, 6, 0, INF, 9, 8},
                {INF, 2, INF, 0, INF, 7},
                {INF, INF, 9, INF, 0, 1},
                {INF, 4, 8, 7, 1, 0}
        };

        int res = kruskal(g);

        System.out.println(res);
    }


}
