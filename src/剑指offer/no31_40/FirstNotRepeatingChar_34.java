package 剑指offer.no31_40;

import java.util.HashMap;

/**
 * 思路一：时间复杂度为O(n^2)
 * 从头开始扫描字符串中的每个字符。当访问到某字符时，拿这个字符和后面的每个字符进行比较，如果在后面没有发现重复
 * 的字符，则该字符就是只出现一次的字符。
 *
 * 思路二：由于题目与字符出现的次数有关，我们可以统计每个字符在该字符串中出现的次数。时间复杂度为O(n)
 * 因此我们需要一个数据容器来存放每个字符出现的次数，在这个数据容器中查找每个字符出现次数的时间是O(1)，也就是说
 * 这个容器的作用是把一个字符映射成一个数字。所以我们选择用哈希表。
 * 我们定义哈希表的key为字符，对应的value为该字符出现的次数。同时我们需要扫描字符串两次，第一次扫描字符串时，
 * 得到每个字符在该字符串中出现的次数。第二次扫描字符串时，每扫描一个字符，在哈希表中就能得到它出现的次数，从而
 * 找到第一个中出现一次的字符。
 */
public class FirstNotRepeatingChar_34 {
    public int FirstNotRepeatingChar(String str) {
        if(str.length()==0)
            return -1;
        //注意：这里hashmap里面的类型不能用基础数据类型，要用引用数据类型，这里用<char,int>就会报错
        HashMap<Character,Integer> charNumber = new HashMap<Character,Integer>();
        //遍历一遍字符串，获取每个字符出现的次数，并存入hashmap中
        for(int i=0;i<str.length();i++){
            if(charNumber.containsKey(str.charAt(i))){
                int count = charNumber.get(str.charAt(i));
                charNumber.put(str.charAt(i),count+1);
            }else{
                charNumber.put(str.charAt(i),1);
            }
        }

        //遍历字符串，找到第一个只出现一次的字符
        for(int i=0;i<str.length();i++){
            if(charNumber.get(str.charAt(i))==1)
                return i;
        }

        return -1;

    }
}
