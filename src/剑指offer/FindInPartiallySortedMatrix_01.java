package 剑指offer;

/**
 * 01 二维数组中的查找
 */

public class FindInPartiallySortedMatrix_01 {

    public boolean Find(int target, int [][] array) {
        int x = 0;
        int y = array[0].length-1;

        while(x < array.length && y >= 0){
            if (target < array[x][y]){
                y--;
            }else if (target > array[x][y]){
                x++;
            }else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FindInPartiallySortedMatrix_01 f1 = new FindInPartiallySortedMatrix_01();
        int[][] array = {{0,1,2},{1,2,3},{4,5,7}};
        System.out.println(f1.Find(7,array));

    }
}
