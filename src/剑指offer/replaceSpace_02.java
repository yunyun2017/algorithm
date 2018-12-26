package 剑指offer;

import java.util.Scanner;

public class replaceSpace_02 {

    public String replaceSpace(StringBuffer str) {
        int length = str.length();
        char[] input = str.toString().toCharArray();

        int blankNum = 0;
        //找到有几个空格
        for (int i = 0; i < length; i++) {
            if (input[i] == ' ') {
                blankNum++;
            }
        }

        //替换之后的字符串长度
        int newLength = length + blankNum * 2;
        char[] string = new char[newLength];
        for (int i = 0; i < length; i++) {
            string[i] = input[i];
        }
        int p1 = length - 1;
        int p2 = newLength - 1;

        while (p1 >= 0 && p1 < p2) {
            if (string[p1] == ' ') {
                string[p2--] = '0';
                string[p2--] = '2';
                string[p2--] = '%';

            } else {
                string[p2--] = string[p1];
            }
            p1--;
        }

        return new String(string);

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        System.out.println("str:"+str);

        replaceSpace_02 rs = new replaceSpace_02();
        System.out.println(rs.replaceSpace(new StringBuffer(str)));

    }
}
