package 剑指offer.no31_40;

/**
 * 思路一：常规解法——判断每个整数是不是丑数(时间效率不够高)
 * 根据丑数定义，丑数只能被2，3，5整除。即：如果一个数能被2整除，我们把它连续除以2；如果能被3整除，连续除以3；
 * 如果能被5整除，连续除以5.最后得到的商如果是1，那么这个数就是丑数，否则不是。
 * <p>
 * 思路二：时间效率更好的算法，利用空间换时间
 * 由题意知道，丑数=丑数*丑数，即某一个丑数必然是由另一个丑数*2或者*3或者*5得到的。则我们可以假设有3个队列，分别是存放*2，*3和*5结果
 * 的队列，每次都取这三个队头元素中的最小元素存入丑数数组中，将这个最小元素分别*2，*3，*5放入对应的队列中，然后更新这三个队列指向队头元素的
 * 指针。直到找到第N个丑数为止。
 */
public class GetUglyNumber_Solution_33 {
    public int GetUglyNumber_Solution1(int index) {
        int count = 0;
        int start = 1;
        while (count < index) {
            if (isUglyNumber(start))
                count++;
            start++;
        }
        return --start;
    }

    //判断一个数是否是丑数
    public boolean isUglyNumber(int num) {
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        //判断最后的商是否为1，为1则为丑数，否则不是丑数
        return num == 1 ? true : false;
    }

    public int GetUglyNumber_Solution2(int index) {
        if(index<=0)
            return 0;
        //1-6的丑数就是其自身
        if(index<7)
            return index;
        //定义一个存丑数的数组
        int[] uglyNum = new int[index];
        uglyNum[0]=1;
        int p2=0,p3=0,p5=0;
        for(int i=1;i<index;i++){
            uglyNum[i] = Math.min(uglyNum[p2]*2,Math.min(uglyNum[p3]*3,uglyNum[p5]*5));
            if(uglyNum[i] == uglyNum[p2]*2) p2++;
            if(uglyNum[i] == uglyNum[p3]*3) p3++;
            if(uglyNum[i] == uglyNum[p5]*5) p5++;
        }

        return uglyNum[index-1];

    }

    public static void main(String[] args) {
        GetUglyNumber_Solution_33 uglyNum = new GetUglyNumber_Solution_33();
        int result1 = uglyNum.GetUglyNumber_Solution1(12);
        int result2 = uglyNum.GetUglyNumber_Solution1(12);
        System.out.println(result1);
        System.out.println(result2);
    }
}
