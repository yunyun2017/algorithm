package 剑指offer.no51_60;

public class FirstAppearingOnce_54 {
    int[] readArray = new int[256];
    int index = 0;

    public FirstAppearingOnce_54() {
        //初始化readArray数组
        for (int i = 0; i < readArray.length; i++) {
            readArray[i] = -1;
        }
    }

    //Insert one char from stringstream
    public void Insert(char ch) {
        if (readArray[ch] == -1) {
            readArray[ch] = index;
        } else {
            readArray[ch] = -2;
        }
        index++;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        int minIndex = 256;
        char firstAppear = ' ';
        for (int i = 0; i < 256; i++) {
            if (readArray[i] >= 0 && readArray[i] < minIndex) {
                firstAppear = (char) i;
                minIndex = readArray[i];
            }
        }

        return firstAppear;
    }
}
