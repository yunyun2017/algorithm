package 剑指offer.no41_50;

/*
    思路：
    1、先将整个句子的所有字符都旋转过来，这时句子中的单词和每个单词的顺序都翻转了；
    2、将句子中的每个单词都翻转过来，即可得到题目所要求的。

    举例： 句子：I am a student.  第一步之后：.tneduts a ma I  第二步之后：student. a am I
 */
public class ReverseSentence_44 {
    public String ReverseSentence(String str) {
        if (str.length() == 0)
            return str;

        //将整个句子翻转
        char[] strToChar = str.toCharArray();
        Reverse(strToChar, 0, str.length() - 1);

        int start = 0;
        for (int i = 0; i <= strToChar.length; i++) {
            if (i == strToChar.length || strToChar[i] == ' ') {
                Reverse(strToChar, start, i - 1);
                start = i + 1;
            }
        }

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
        ReverseSentence_44 reverse = new ReverseSentence_44();
        String str = "I am a student.";
        String result = reverse.ReverseSentence(str);
        System.out.println(result);
    }
}
