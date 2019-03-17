package 剑指offer.no41_50;

public class StrToInt_49 {
    public int StrToInt(String str) {
        if (str.length() == 0)
            return 0;

        char[] strArray = str.toCharArray();
        boolean minus = strArray[0] == '-' ? true : false;
        int start = (strArray[0] == '-' || strArray[0] == '+') ? 1 : 0;
        int res = 0;

        for (int i = start; i < strArray.length; i++) {
            if (strArray[i] < '0' || strArray[i] > '9') {
                return 0;
            }

            res = res * 10 + strArray[i] - 48;
        }

        if (minus) return -1 * res;

        return res;
    }
}
