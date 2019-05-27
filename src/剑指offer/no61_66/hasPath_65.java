package 剑指offer.no61_66;

/*
    思路：这是一个典型的可以用回溯法解决的问题。
    首先，从矩阵中任选一个格子作为路径的起点，假设矩阵中某个格子的字符为c，对应路径上的第i个字符。
    如果路径上第i个字符不是c，那么这个格子不可能在路径上的第i个位置。如果路径上的第i个字符正好为c，那么继续往相邻的
    格子寻找路径上的第i+1个字符。注意边界格子的边界判断。重复这个过程，直至路径上所有字符都在矩阵中找到对应位置。
    另外，还需要一个大小和矩阵一样的布尔值矩阵，用来标识路径是否已经走过该格子了。
 */
public class hasPath_65 {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix.length == 0 || str.length == 0)
            return false;

        //创建一个标记数组
        boolean[] visited = new boolean[matrix.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        int strIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathJudge(matrix, rows, cols, str, i, j, strIndex, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasPathJudge(char[] matrix, int rows, int cols, char[] str, int row, int col,
                                int strIndex, boolean[] visited) {
        if (strIndex == str.length) {
            return true;
        }
        boolean hasPath = false;

        if (row >= 0 && row < rows && col >= 0 && col < cols && matrix[row * cols + col] == str[strIndex]
                && !visited[row * cols + col]) {
            visited[row * cols + col] = true;
            strIndex++;
            //访问与它相邻的下一个格子
            hasPath = hasPathJudge(matrix, rows, cols, str, row, col - 1, strIndex, visited) ||
                    hasPathJudge(matrix, rows, cols, str, row - 1, col, strIndex, visited) ||
                    hasPathJudge(matrix, rows, cols, str, row, col + 1, strIndex, visited) ||
                    hasPathJudge(matrix, rows, cols, str, row + 1, col, strIndex, visited);

            //如果没找到路径，则回退到上一步
            if (!hasPath) {
                visited[row * cols + col] = false;
                strIndex--;
            }
        }
        return hasPath;
    }

    public static void main(String[] args) {
        char[] matrix = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
        char[] str = {'b', 'c', 'c', 'e', 'd'};

        hasPath_65 path = new hasPath_65();
        boolean res = path.hasPath(matrix, 3, 4, str);
        System.out.println(res);
    }
}
