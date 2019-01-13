package 剑指offer.no11_20;

public class reOrderArray_13 {
    public void reOrderArray(int [] array) {

        for(int i = 0;i<array.length;i++){
            if(array[i] % 2 == 0){
                for(int j = i+1;j<array.length;j++){
                    if(array[j] % 2 == 1){
                        int firstOdd = array[j];
                        //i~j-1 后移一位
                        int temp = j-1;
                        while(temp >= i){
                            array[temp+1] = array[temp];
                            temp--;
                        }
                        array[i] = firstOdd;
                        break;
                    }
                }
            }
        }
    }
}
