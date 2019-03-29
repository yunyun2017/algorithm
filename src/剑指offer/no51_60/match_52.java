package 剑指offer.no51_60;

public class match_52 {
    public boolean match(char[] str, char[] pattern) {
        if (str.length == 0 && pattern.length == 0) {
            return true;
        }

        return match(str, pattern, 0, 0);
    }

    //递归匹配字符串
    public boolean match(char[] str, char[] pattern, int s1, int s2) {
        if (s1 == str.length && s2 == pattern.length)
            return true;

        if (s1 != str.length && s2 >= pattern.length)
            return false;

        if (s2 + 1 < pattern.length && pattern[s2 + 1] == '*') {
            if (s1 < str.length && (str[s1] == pattern[s2] || pattern[s2] == '.')) {
                //*代表：出现多次，出现一次，出现0次
                return match(str, pattern, s1 + 1, s2) ||
                        match(str, pattern, s1 + 1, s2 + 2) || match(str, pattern, s1, s2 + 2);

            } else {
                return match(str, pattern, s1, s2 + 2);
            }
        } else {
            if (s1 != str.length && (str[s1] == pattern[s2] || pattern[s2] == '.'))
                return match(str, pattern, s1 + 1, s2 + 1);
        }

        return false;

    }

    public static void main(String[] args) {
        String str = "";
        String pattern = ".*";

        match_52 mat = new match_52();
        System.out.println(mat.match(str.toCharArray(), pattern.toCharArray()));
    }
}
