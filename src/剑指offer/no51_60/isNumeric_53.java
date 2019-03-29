package 剑指offer.no51_60;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.omg.CORBA.PUBLIC_MEMBER;
import 剑指offer.no1_10.RectCover_10;

/*
    思路：找到表示数值的规律即可
 */
public class isNumeric_53 {
    public boolean isNumeric(char[] str) {
        if (str.length == 0) {
            return false;
        }

        int index = 0;
        if (str[0] == '+' || str[0] == '-') {
            ++index;
        }
        if (index == str.length) {
            return false;
        }

        boolean isNum = false;
        int pos = findNoNum(str, index);
        if (pos != str.length) {
            if (str[pos] == '.') {
                int pos2 = findNoNum(str, pos + 1);
                if (pos2 == str.length) {
                    isNum = true;
                } else {
                    if (str[pos2] == 'e' || str[pos2] == 'E')
                        isNum = isExponential(str, pos2);
                    else
                        isNum = false;
                }

            } else if (str[pos] == 'e' || str[pos] == 'E') {
                isNum = isExponential(str, pos);
            } else {
                isNum = false;
            }
        } else {
            isNum = true;
        }

        return isNum;

    }

    public int findNoNum(char[] str, int start) {
        for (int i = start; i < str.length; i++) {
            if (str[i] < '0' || str[i] > '9') {
                return i;
            }
        }

        return str.length;
    }

    //检查科学记数法的表示
    public boolean isExponential(char[] str, int start) {
        if (str.length == 0 || start >= str.length) {
            return false;
        }

        if (str[start] != 'e' && str[start] != 'E') {
            return false;
        }
        start++;
        if (start != str.length && (str[start] == '+' || str[start] == '-')) {
            start++;
        }

        if (start == str.length)
            return false;

        int index = findNoNum(str, start);
        return index == str.length;
    }

    public static void main(String[] args) {
        isNumeric_53 isNum = new isNumeric_53();
        String str = "12e+4.3";
        boolean res = isNum.isNumeric(str.toCharArray());
        System.out.println(res);

    }
}
