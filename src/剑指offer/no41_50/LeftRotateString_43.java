package 剑指offer.no41_50;

/*
    思路一：遍历这个字符串，对于前n位，存到一个字符数组中，后length-n位存到另一个数组中，
    最后将这两个数组拼接成一个字符串返回

    思路二：利用第44题的思路，启发我们，我们可以先将前n位翻转，再将后length-n位翻转，最后再将整个字符串翻转，
    就可以得到题目所求的了
 */
public class LeftRotateString_43 {
    public String LeftRotateString1(String str, int n) {
        if (str.length() == 0 || str.length() < n) return "";

        char[] rotateStr = new char[n];
        char[] restStr = new char[str.length() - n];

        for (int i = 0; i < str.length(); i++) {
            if (i < n) {
                rotateStr[i] = str.charAt(i);
            } else {
                restStr[i - n] = str.charAt(i);
            }
        }

        return String.valueOf(restStr) + String.valueOf(rotateStr);
    }

    public String LeftRotateString2(String str, int n) {
        if (str.length() == 0 || str.length() < n) return "";

        char[] strToChar = str.toCharArray();
        Reverse(strToChar, 0, n - 1);
        Reverse(strToChar, n, strToChar.length - 1);
        Reverse(strToChar,0,strToChar.length-1);

        return String.valueOf(strToChar);
    }

    //从start到end翻转这部分
    public void Reverse(char[] str, int start, int end) {

        while (start < end) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }

    }

    public static void main(String[] args) {
        LeftRotateString_43 rotate = new LeftRotateString_43();

        String str = "abcXYZdef";
        String rotateStr = rotate.LeftRotateString1(str, 3);
        System.out.println(rotateStr);

    }
}
