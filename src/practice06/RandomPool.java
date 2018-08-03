package practice06;

import com.sun.glass.ui.Size;

import java.util.HashMap;

/**
 * 题目：设计一种结构， 在该结构中有如下三个功能：
 * insert(key)： 将某个key加入到该结构， 做到不重复加入。
 * delete(key)： 将原本在结构中的某个key移除。
 * getRandom()：等概率随机返回结构中的任何一个key。
 * <p>
 * 要求：Insert、 delete和getRandom方法的时间复杂度都是O(1)
 * <p>
 * 思路：
 * 用两个HashMap，一个里面的(key,value)=(要存入的关键词,关键词进入的次序)；
 * 另一个哈希表里面的(key,value)=(关键词进入的次序，要存入的关键词)；
 * 再用一个size记录HashMap的大小，这样就可以实现insert是O(1),getRandom是O(1)
 * 但是，在delete的时候，需要做些额外的处理，以保证在getRandom时是O(1),额外处理是：
 * 当删除某个key后，把最后一个key补到这个删除的key的位置，然后把最后一个key删除，size-1即可
 */
public class RandomPool {
    private HashMap<String, Integer> map1;
    private HashMap<Integer, String> map2;
    private int size;

    public RandomPool() {
        this.map1 = new HashMap<String, Integer>();
        this.map2 = new HashMap<Integer, String>();
        this.size = 0;
    }

    public void insert(String key) {
        if(!map1.containsKey(key)){
            map1.put(key, size);
            map2.put(size, key);
            size++;
        }
    }

    public String getRandom() {
        if (size == 0) {
            return null;
        }

        int randomIndex = (int) (Math.random() * size);
        return map2.get(randomIndex);
    }

    public void delete(String key) {
        if(map1.containsKey(key)){
            int deleteIndex = map1.get(key); //要删除的key的次序
            String lastKey = map2.get(size - 1); //最后一个key
            //用最后一个key来填充删除的key的位置
            map1.put(lastKey, deleteIndex);
            map2.put(deleteIndex, lastKey);
            map1.remove(key);
            map2.remove(size-1);
            size--;
        }

    }

    public static void main(String[] args) {
        RandomPool rp = new RandomPool();
        rp.insert("hu");
        rp.insert("yun");
        rp.insert("liu");
        rp.insert("niu");
        System.out.println(rp.getRandom());
        System.out.println(rp.getRandom());
        System.out.println(rp.getRandom());
        System.out.println(rp.getRandom());
        rp.delete("liu");
        System.out.println(rp.getRandom());
        System.out.println(rp.getRandom());
        System.out.println(rp.getRandom());
    }
}
