package 剑指offer.no61_66;

public class movingCount_66 {
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0)
            return 0;

        //一个和寻找范围一样大的矩阵，用于标识走过的格子
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited[i][j] = false;
            }
        }
        //从（0，0）开始，利用回溯法开始寻找机器人可以走的格子
        return findMovingPath(threshold, rows, cols, 0, 0, visited);
    }

    //计算一个数字的各个数位和
    public int calNum(int num) {
        int sum = 0;
        while (num / 10 > 0) {
            sum += num % 10;
            num /= 10;
        }
        sum += num;

        return sum;
    }

    public int findMovingPath(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
        int count = 0;

        if (row >= 0 && row < rows && col >= 0 && col < cols &&
                (calNum(row) + calNum(col)) <= threshold && !visited[row][col]) {
            visited[row][col] = true;
            count++;
            count += findMovingPath(threshold, rows, cols, row, col - 1, visited)
                    + findMovingPath(threshold, rows, cols, row - 1, col, visited)
                    + findMovingPath(threshold, rows, cols, row, col + 1, visited)
                    + findMovingPath(threshold, rows, cols, row + 1, col, visited);
        }
        return count;
    }

    public static void main(String[] args) {
        movingCount_66 move = new movingCount_66();
        int count = move.movingCount(3, 3, 4);
        System.out.println(count);
    }
}
