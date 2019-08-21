package GraphAndDp;

public class Prim {
    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[][] graph = {
                {0, 7, 4, INF, INF, INF},
                {7, 0, 6, 2, INF, 4},
                {4, 6, 0, INF, 9, 8},
                {INF, 2, INF, 0, INF, 7},
                {INF, INF, 9, INF, 0, 1},
                {INF, 4, 8, 7, 1, 0}
        };

        int res = prim(graph, vertex, 0);
        System.out.println(res);
    }

    /**
     * @param graph  图的邻接矩阵
     * @param vertex 图的顶点数组
     * @param start  prim算法开始的顶点下标
     * @return 最小生成树的权值
     */
    public static int prim(int[][] graph, char[] vertex, int start) {
        int sum = 0; //最新生成树的权值
        int index; //指向当前加入最小生成树的那个顶点
        boolean[] visited = new boolean[vertex.length]; //标记哪些顶点是已访问的
        int[] minDist = new int[vertex.length]; //记录当前最小生成树所有结点到剩下结点的最短距离
        char[] minVertex = new char[vertex.length]; //记录最小生成树的结果数组
        int minIndex = 0; //记录最小生成树的结果数组的当前下标

        //对visited和minDist数组进行初始化
        for (int i = 0; i < vertex.length; i++) {
            visited[i] = false;
            minDist[i] = graph[start][i];
        }

        //对start这个结点进行处理
        visited[start] = true;
        index = start;
        minVertex[minIndex++] = vertex[start];

        for (int i = 1; i < vertex.length; i++) {
            //这里的i就是为了计数的，记录当前加入最小生成树的顶点数。从1开始是因为，start在循环外面已经处理了，
            // 还剩下vertex.length-1个顶点了
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < vertex.length; j++) {
                if (!visited[j] && minDist[j] < min) {
                    min = minDist[j];
                    index = j;
                }
            }
            sum += min;
            visited[index] = true;
            minVertex[minIndex++] = vertex[index];

            //执行更新，如果剩余顶点距离当前顶点的距离更近，就更新minDist
            for (int j = 0; j < vertex.length; j++) {
                if (!visited[j] && graph[index][j] < minDist[j]) {
                    minDist[j] = graph[index][j];
                }
            }

        }

        //打印出依次添加进最小生成树的顶点
        for (int i = 0; i < vertex.length; i++) {
            System.out.print(minVertex[i] + " ");
        }

        return sum;

    }

    /**
     * @param vertex 图的顶点数组
     * @param c      待查询的顶点字符
     * @return c在vertex数组中的下标
     */
    public static int getPos(int[] vertex, char c) {
        for (int i = 0; i < vertex.length; i++) {
            if (c == vertex[i])
                return i;
        }

        return -1;
    }
}
