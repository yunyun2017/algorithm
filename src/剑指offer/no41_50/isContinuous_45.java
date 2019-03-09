package 剑指offer.no41_50;

/*
    题目：求给定的数组中的元素是否是顺子
    思路：要组成顺子，需要满足以下的几个条件：
    1、数组中除了元素0，其他非零元素不能重复出现，只能最多出现一次
    2、最大的非零元素和最小的非零元素差 < 5
    3、数组中包含5个非空元素

    max记录：非零元素的最大值
    min记录：非零元素的最小值
 */
public class isContinuous_45 {
    public boolean isContinuous(int[] numbers) {
        if (numbers.length < 5) return false;

        int min = 14;
        int max = 0;

        int[] allCard = new int[14];    //记录数组中非零元素出现的次数，由于非零元素是1-13，所以数组下标最大为13

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0)
                continue;

            allCard[numbers[i]]++;

            if (allCard[numbers[i]] > 1)
                return false;

            if (numbers[i] > max)
                max = numbers[i];

            if (numbers[i] < min)
                min = numbers[i];
        }

        if (max - min < 5)
            return true;

        return false;
    }

    public static void main(String[] args) {
        isContinuous_45 cont = new isContinuous_45();
        int[] numbers = {0, 0, 3, 2, 8};
        boolean res = cont.isContinuous(numbers);
        System.out.println(res);
    }
}
