package 剑指offer.no31_40;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
思路一：最直接的做法：先求出这个数组中所有数字的全排列，然后把每个排列拼接起来，最后求出拼起来的数字的最小值。
求数组的排列和28题【字符串的排列】非常类似可以参考它。

思路二：题目其实希望我们可以找到一个排序规则，数组根据这个规则排序之后能排成一个最小的数字。要确定排序规则，
就要比较两个数字，也就是要比较给出的两个数字m和n哪个应该排在前面，而不是仅仅比较这两个数字的值哪个更大。
根据题意，两个数字m和n可以拼接成mn和nm。如果mn<nm，则打印出mn，也就是m应该排在n的前面，我们定义此时m小于n；反正，
如果nm<mn，我们定义n小于m。如果mn=nm，m等于n。

另外要注意：m和n都在int范围内，但是把它们拼接起来的数字mn和nm可能会超过int范围，所以这是一个隐形的大数问题。
一个直观的解决大数问题的方法就是把数字转换成字符串。由于拼接起来的mn和nm位数相同，因此可以直接按照字符串大小的
比较规则进行比较即可。
 */

public class PrintMinNumber_32 {

    //思路：将list中的元素转换成string，然后对这些元素进行排序，最后将排好序的元素拼接起来输出
    public String PrintMinNumber(int[] numbers) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int length = numbers.length;
        String s = "";

        for (int i = 0; i < length; i++) {
            list.add(numbers[i]);
        }

        //将list中的元素拼接成字符串，然后比较字符串的大小。
        //自定义比较器，用于比较两个元素拼接成的字符串的大小，从而确定这两个元素转换成字符串后的大小
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer s1, Integer s2) {
                String str1 = s1 + "" + s2;
                String str2 = s2 + "" + s1;
                return str1.compareTo(str2);
            }
        });

        //将list中的元素拼接成一个字符串，然后返回
        for (int i : list) {
            s += i;
        }

        return s;
    }
}
