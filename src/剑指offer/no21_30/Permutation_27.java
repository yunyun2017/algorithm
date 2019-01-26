package 剑指offer.no21_30;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 字符串的全排列问题
 * 思路：将该字符串看成是由两部分组成：第一部分是它的第一个字符，第二部分是后面的所有字符。
 * 求字符串的全排列，可以看成两步：
 * 第一步：求所有可能出现在第一个位置的字符，即把第一个字符和后面所有的字符交换；
 * 第二步：固定第一个字符，求后面所有字符的排列。这时我们仍把后面的所有字符分成两部分：
 * 后面的第一个字符和第一个字符之后的所有字符。然后把第一个字符逐一和它后面的字符交换。
 */
public class Permutation_27 {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<String>();
        if (str == null || str.length() == 0) return list;

        char[] strArray = str.toCharArray();
        Permutation(strArray, 0, list);
        Collections.sort(list);//为了通过牛客的case，垃圾牛客case，答案不按照规律给出，按规律输入的答案还判断错误

        return list;
    }

    /**
     * 字符数组中从start开始到数组结尾的字符的排列
     *
     * @param strArray
     * @param start
     * @return
     */
    public void Permutation(char[] strArray, int start, ArrayList<String> list) {
        if (start == strArray.length) {
            //由于输入的字符串可能有重复字符，所以对list去重
            String str = new String(strArray);
            if (!list.contains(str))
                list.add(str);
        } else {
            for (int i = start; i < strArray.length; i++) {
                //交换temp和start位置的元素
                char temp = strArray[i];
                strArray[i] = strArray[start];
                strArray[start] = temp;

                Permutation(strArray, start + 1, list);
                //重置下strArray，使其恢复到最初的排列
                temp = strArray[i];
                strArray[i] = strArray[start];
                strArray[start] = temp;
            }
        }

    }

    public static void main(String[] args) {
        Permutation_27 p = new Permutation_27();
        ArrayList<String> list = p.Permutation("abc");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
